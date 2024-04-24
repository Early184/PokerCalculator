package Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Model.CardPanel;
import Model.Deck;
import Model.Playground;
import Model.PossibleStraightDraws;
import View.CustomCompontents.CustomObserver;

public class PokerCalculator implements CustomObserver{
    
    private Playground playground;
    private Deck deck;
    private CardPanel[] cardsForCalc;
    private PossibleStraightDraws possibleStraightDraws; 
    private int sizeDeck;
    private CardPanel[] realCardsOnPlayground;
    private int deckCount;
    private int cardCount;

    public PokerCalculator(Playground playground){
        this.playground = playground;
        cardsForCalc = playground.getCardSlots(); 
        playground.addObserver(this);
        this.deck = Deck.getInstance();
        this.sizeDeck = deck.getAmountCards();
        possibleStraightDraws = new PossibleStraightDraws();

        getActualCardsOnPlayground();
        sortPlaygroundByValues();
        chanceStraight2();
        findMissingCardsForStraight();
        
    }
    // STRAIGHT SECTION #######################################################################################################################STRAIGHT SECTION#######
    public void chanceStraight2(){
        possibleStraightDraws = new PossibleStraightDraws();
        ArrayList<Integer> possibleDraws = possibleStraightDraws.getDrawSums();
        
        //Summe der Karten auf dem Spielfeld Binär
        int sumOfCardsOnPlayground = 0;
        ArrayList <Integer> playgroundValues = new ArrayList<>();
        //Playground Karten in ein Array geben als Binäre Werte
        for (CardPanel cardPanel : realCardsOnPlayground) {
            int bitValue = possibleStraightDraws.getValueForCard(cardPanel.getValue());
            playgroundValues.add(bitValue);
            sumOfCardsOnPlayground += bitValue;
        }
        //Ausgabe der fehlenden Karten für alle Kombinationen
        ArrayList<ArrayList<Integer>> missingDrawCards = new ArrayList<>();
        for(int[]combination : possibleStraightDraws.getDrawList()){
            ArrayList<Integer> combinationsParsed = new ArrayList<>();
            for(int number : combination){
                combinationsParsed.add(number);
            }
            combinationsParsed.removeAll(playgroundValues);
            missingDrawCards.add(combinationsParsed);
            System.out.println("Überbleibende Karten für Kombi: " +combinationsParsed);
        }
       
        System.out.println("Summe der Karten auf dem Spielfeld:" + sumOfCardsOnPlayground);
        System.out.println("Aktuell auf dem Feld: " + playgroundValues);

        System.out.println(missingDrawCards.get(0));
        ArrayList<Integer> smallestList = missingDrawCards.stream().min(Comparator.comparingInt(ArrayList::size)).orElse(null);
        int outs = 0;
        switch(smallestList.size()){
            case 1 -> { outs = 4;
                        chanceForStraightMath(outs);
                      }
            case 2 -> { outs = 8;
                        chanceForStraightMath(outs);
                        }     
            case 3 -> { outs = 12;
                        chanceForStraightMath(outs);
                        }
            case 4 -> { outs = 16;
                        chanceForStraightMath(outs);
                        }
                      
        }
        
        // nächsthöhere Gesamtzahl zum erreichen einer Straße holen
        int nextHigherValue = 0;
        for(int i = 0; i < possibleDraws.size(); i++){
            if(sumOfCardsOnPlayground < possibleDraws.get(i)){
                nextHigherValue = possibleDraws.get(i);
                break;
            }
        }

        //Differenz zwischen Feld und nächster Kombination
        if(nextHigherValue != 0){
            int difference = nextHigherValue - sumOfCardsOnPlayground;
            System.out.println(String.format("Nächsthöhere Gesamtzahl: %d, Differenz zu Playground: %d", nextHigherValue, difference));
        }

        //Fehlende Karten für eine Straße bestimmen

    }
    
    public Double chanceForStraightMath(int outs){
        
        double straightChance = ((double)outs/sizeDeck)*100;
        System.out.println(straightChance + "%");
        return straightChance;

    }
    
    public void findMissingCardsForStraight() {
        HashMap<Integer, Integer> missingCardsCount = countMissingCards();
        displayMissingCards(missingCardsCount);
        int nearestCombinationSum = findNearestCombination(missingCardsCount);
        System.out.println("Die Summe der Binären Kombination welche am nähesten liegt ist: " + nearestCombinationSum + ". Anzahl fehlender Karten: " + missingCardsCount.get(nearestCombinationSum));
    }

    private HashMap<Integer, Integer> countMissingCards() {
        ArrayList<int[]> combinations = possibleStraightDraws.getDrawList();
        HashMap<Integer, Integer> missingCardsCount = new HashMap<>();
        for (int[] combination : combinations) {
            int missingCount = countMissingCardsInCombination(combination);
            missingCardsCount.put(sumCardValues(combination), missingCount);
        }
        return missingCardsCount;
    }

    private int countMissingCardsInCombination(int[] combination) {
        int missingCount = 0;
        for (int cardValue : combination) {
            if (!isCardPresent(cardValue)) {
                missingCount++;
            }
        }
        return missingCount;
    }

    private void displayMissingCards(HashMap<Integer, Integer> missingCardsCount) {
        for (Map.Entry<Integer, Integer> entry : missingCardsCount.entrySet()) {
            int combinationValue = entry.getKey();
            int missingCount = entry.getValue();
            System.out.println("Für die Kombination " + combinationValue + " fehlen " + missingCount + " Karten.");
        }
    }

    private int findNearestCombination(HashMap<Integer, Integer> missingCardsCount) {
        int nearestCombinationMissingCardsCount = missingCardsCount.values().stream().min(Comparator.naturalOrder()).get();
        int nearestCombinationSum = findNearestCombinationSum(missingCardsCount, nearestCombinationMissingCardsCount);
        return nearestCombinationSum;
    }

    private int findNearestCombinationSum(HashMap<Integer, Integer> missingCardsCount, int nearestCombinationMissingCardsCount) {
        int nearestCombinationSum = 0;
        for (Map.Entry<Integer, Integer> entry : missingCardsCount.entrySet()) {
            if (entry.getValue() == nearestCombinationMissingCardsCount) {
                nearestCombinationSum = entry.getKey();
                break;
            }
        }
        return nearestCombinationSum;
    }
    
    // Überprüfen ob eine Karte auf dem Spielfeld vorhanden ist
    private boolean isCardPresent(int cardValue) {
        for (CardPanel cardPanel : realCardsOnPlayground) {
            if (possibleStraightDraws.getValueForCard(cardPanel.getValue()) == cardValue) {
                return true;
            }
        }
        return false;
    }
    
    // Summe der Kartenwerte in einer Kombination berechnen
    private int sumCardValues(int[] combination) {
        int sum = 0;
        for (int cardValue : combination) {
            sum += cardValue;
        }
        return sum;
    }

    // STRAIGHT SECTION #######################################################################################################################STRAIGHT SECTION#######
    private void sortPlaygroundByValues() {
        // Karten im Deck
        deckCount = sizeDeck - realCardsOnPlayground.length;
        System.out.println("Karten im Deck: " + deckCount);

        // Sortiere die Karten nach Wert
        Arrays.sort(realCardsOnPlayground, Comparator.comparingInt(CardPanel::getValue));
    }

    private CardPanel[] getActualCardsOnPlayground() {
        cardCount = 0;
        for(CardPanel cardPanel : cardsForCalc){
            if(cardPanel.getValue() != 0){
                cardCount++;
            }
        }
        
        // Erstelle ein Array, um nur die Karten im Playground zu halten
        realCardsOnPlayground = new CardPanel[cardCount];
        int index = 0;
        for(CardPanel cardPanel : cardsForCalc){
            if(cardPanel.getValue() != 0){
                realCardsOnPlayground[index] = cardPanel;
                index++;
            }
        }
        return realCardsOnPlayground;
    }

    public Playground getPlayground() {
        return playground;
    }

    @Override
    public void update() {
        getActualCardsOnPlayground();
        sortPlaygroundByValues();
        chanceStraight2();
        findMissingCardsForStraight();
        
    }

}