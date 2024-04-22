package Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
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
    private int[] outs;
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
        int sumOfCardsOnPlayground = 0;
        for(int i = 0; i < realCardsOnPlayground.length; i++){
            int bitValue = possibleStraightDraws.getValueForCard(realCardsOnPlayground[i].getValue());
            sumOfCardsOnPlayground += bitValue;
        }
        System.out.println("Summe der Karten auf dem Spielfeld:" + sumOfCardsOnPlayground);

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

    }
    public void findMissingCardsForStraight() {
        
        ArrayList<int[]> combinations = possibleStraightDraws.getDrawList();
    
        // Erstellen einer Map zur Speicherung der Anzahl fehlender Karten für jede Kombination
        HashMap<Integer, Integer> missingCardsCount = new HashMap<>();
    
        // Zählen der fehlenden Karten für jede Kombination
        for (int[] combination : combinations) {
            int missingCount = 0;
            for (int cardValue : combination) {
                if (!isCardPresent(cardValue)) {
                    missingCount++;
                }
            }
            missingCardsCount.put(sumCardValues(combination), missingCount);
        }
    
        // Ausgabe der fehlenden Karten für jede Kombination
        for (Map.Entry<Integer, Integer> entry : missingCardsCount.entrySet()) {
            int combinationValue = entry.getKey();
            int missingCount = entry.getValue();
            System.out.println("Für die Kombination " + combinationValue + " fehlen noch "+ "also " + missingCount + " Karten.");
        }
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