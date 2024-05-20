package Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.swing.table.DefaultTableModel;

import CustomCompontents.CustomObserver;
import CustomCompontents.ProbabilityCellRenderer;
import Model.CardPanel;
import Model.Deck;
import Model.Playground;
import Model.PossibleStraightDraws;
import View.Mainwindow.MainFrame;

public class PokerCalculator implements CustomObserver{
    
    private Playground playground;
    private MainFrame mainFrame;
    private Deck deck;
    private CardPanel[] cardsForCalc;
    private PossibleStraightDraws possibleStraightDraws; 
    private int sizeDeck;
    private CardPanel[] realCardsOnPlayground;
    private int deckCount;
    private int cardCount;
    private DefaultTableModel defaultTableModelChances;
    private DefaultTableModel defaultTableModelSzenarios;
    private double straightChancePercentage;
    private double laterStraightChancePercentage;
    

    public PokerCalculator(Playground playground, MainFrame frame, ControllerMainwindow controllerMain) {
        this.playground = playground;
        this.mainFrame = frame;
        cardsForCalc = playground.getCardSlots(); 
        playground.addObserver(this);
        this.deck = Deck.getInstance();
        this.sizeDeck = deck.getAmountCards();
        defaultTableModelChances = controllerMain.getDefaultTableModelChances();
        defaultTableModelSzenarios = controllerMain.getDefaultTableModelSzenarios();
        
        
        straightChancePercentage = 0;
        laterStraightChancePercentage = 0;
        
        

        getActualCardsOnPlayground();
        sortPlaygroundByValues();
        chanceStraight2();
        findMissingCardsForStraight();
        
        
        
        setChancesForDrawTable();
        
        
    }
    
    // ROYAL FLUSH SECTION #######################################################################################################################ROYAL FLUSH SECTION#######
    public double chanceForRoyalFlushOnPlayground() {
        double royalFlushChancePercentage = 0;
       
        // Listen For Straight
        possibleStraightDraws = new PossibleStraightDraws();
        ArrayList<Integer> possibleDraws = possibleStraightDraws.getDrawSumsRoyalFlush();
        
        //Map für die Playground Karten
        ArrayList <Integer> playgroundValuesHearts = new ArrayList<>();
        ArrayList <Integer> playgroundValuesSpades = new ArrayList<>();
        ArrayList <Integer> playgroundValuesDiamonds = new ArrayList<>();
        ArrayList <Integer> playgroundValuesClubs = new ArrayList<>();
        //Playground Karten in ein Array geben als Binäre Werte
        for (CardPanel cardPanel : realCardsOnPlayground) {
            int bitValue = possibleStraightDraws.getValueForCard(cardPanel.getValue());
            switch (cardPanel.getSuit()) {
                case "heart": playgroundValuesHearts.add(bitValue); break;
                case "spades": playgroundValuesSpades.add(bitValue); break;
                case "diamonds": playgroundValuesDiamonds.add(bitValue); break;
                case "clubs": playgroundValuesClubs.add(bitValue); break;
            }
        }
        //Ausgabe der fehlenden Karten für alle Kombinationen
        ArrayList<ArrayList<Integer>> missingDrawCards = new ArrayList<>();

            int[] combination = possibleStraightDraws.getDrawListRoyalFlush().get(0);
            ArrayList<Integer> combinationsParsedHearts = new ArrayList<>();
            for(int number : combination){
                combinationsParsedHearts.add(number);
            }
            combinationsParsedHearts.removeAll(playgroundValuesHearts);
            missingDrawCards.add(combinationsParsedHearts);
            
        
        
            ArrayList<Integer> combinationsParsedSpades = new ArrayList<>();
            for(int number : combination){
                combinationsParsedSpades.add(number);
            }
            combinationsParsedSpades.removeAll(playgroundValuesSpades);
            missingDrawCards.add(combinationsParsedSpades);
            
        
        
            ArrayList<Integer> combinationsParsedDiamonds = new ArrayList<>();
            for(int number : combination){
                combinationsParsedDiamonds.add(number);
            }
            combinationsParsedDiamonds.removeAll(playgroundValuesDiamonds);
            missingDrawCards.add(combinationsParsedDiamonds);
            
        
        
            ArrayList<Integer> combinationsParsedClubs = new ArrayList<>();
            for(int number : combination){
                combinationsParsedClubs.add(number);
            }
            combinationsParsedClubs.removeAll(playgroundValuesClubs);
            missingDrawCards.add(combinationsParsedClubs);
            
        
        

        //Ausgabe der Kombinationen mit der geringsten Anzahl Karten
        ArrayList<Integer> smallestList = missingDrawCards.stream().min(Comparator.comparingInt(ArrayList::size)).orElse(null);
        List<ArrayList<Integer>> smallestCombinations = missingDrawCards.stream().filter(draw -> draw.size() == smallestList.size()).collect(Collectors.toList());
        Set<Integer> outSet = new HashSet<Integer>();
        
        for(ArrayList<Integer>comb : smallestCombinations){
            for(Integer number : comb){
                outSet.add(number);
            }
        }

        int outs = outSet.size();
        if(outs > 0 && cardCount == 7 || outs > 1 && cardCount == 6 || outs > 2 && cardCount == 5 || outs > 3 && cardCount == 4 || outs > 4 && cardCount == 3 || outs > 5 && cardCount == 2){
            royalFlushChancePercentage = 0;
        }else{
            royalFlushChancePercentage = chanceForCombinationsMath(outs, 1);
        }
        
       
        return royalFlushChancePercentage;
    }
    // ROYAL FLUSH SECTION #######################################################################################################################ROYAL FLUSH SECTION#######
    // STRAIGHT FLUSH SECTION #######################################################################################################################STRAIGHT FLUSH SECTION#######
    public double chanceForStraightFlushOnPlayground() {
        double straightFlushChancePercentage = 0;
       
        // Listen For Straight
        possibleStraightDraws = new PossibleStraightDraws();
        ArrayList<Integer> possibleDraws = possibleStraightDraws.getDrawSums();
        
        //Map für die Playground Karten
        ArrayList <Integer> playgroundValuesHearts = new ArrayList<>();
        ArrayList <Integer> playgroundValuesSpades = new ArrayList<>();
        ArrayList <Integer> playgroundValuesDiamonds = new ArrayList<>();
        ArrayList <Integer> playgroundValuesClubs = new ArrayList<>();
        //Playground Karten in ein Array geben als Binäre Werte
        for (CardPanel cardPanel : realCardsOnPlayground) {
            int bitValue = possibleStraightDraws.getValueForCard(cardPanel.getValue());
            switch (cardPanel.getSuit()) {
                case "heart": playgroundValuesHearts.add(bitValue); break;
                case "spades": playgroundValuesSpades.add(bitValue); break;
                case "diamonds": playgroundValuesDiamonds.add(bitValue); break;
                case "clubs": playgroundValuesClubs.add(bitValue); break;
            }
        }
        //Ausgabe der fehlenden Karten für alle Kombinationen
        ArrayList<ArrayList<Integer>> missingDrawCards = new ArrayList<>();

        for(int[]combination : possibleStraightDraws.getDrawList()){
            ArrayList<Integer> combinationsParsed = new ArrayList<>();
            for(int number : combination){
                combinationsParsed.add(number);
            }
            combinationsParsed.removeAll(playgroundValuesHearts);
            missingDrawCards.add(combinationsParsed);
            
        }
        for(int[]combination : possibleStraightDraws.getDrawList()){
            ArrayList<Integer> combinationsParsed = new ArrayList<>();
            for(int number : combination){
                combinationsParsed.add(number);
            }
            combinationsParsed.removeAll(playgroundValuesSpades);
            missingDrawCards.add(combinationsParsed);
            
        }
        for(int[]combination : possibleStraightDraws.getDrawList()){
            ArrayList<Integer> combinationsParsed = new ArrayList<>();
            for(int number : combination){
                combinationsParsed.add(number);
            }
            combinationsParsed.removeAll(playgroundValuesDiamonds);
            missingDrawCards.add(combinationsParsed);
            
        }
        for(int[]combination : possibleStraightDraws.getDrawList()){
            ArrayList<Integer> combinationsParsed = new ArrayList<>();
            for(int number : combination){
                combinationsParsed.add(number);
            }
            combinationsParsed.removeAll(playgroundValuesClubs);
            missingDrawCards.add(combinationsParsed);
            
        }
        

        //Ausgabe der Kombinationen mit der geringsten Anzahl Karten
        ArrayList<Integer> smallestList = missingDrawCards.stream().min(Comparator.comparingInt(ArrayList::size)).orElse(null);
        List<ArrayList<Integer>> smallestCombinations = missingDrawCards.stream().filter(draw -> draw.size() == smallestList.size()).collect(Collectors.toList());
        Set<Integer> outSet = new HashSet<Integer>();
        
        for(ArrayList<Integer>comb : smallestCombinations){
            for(Integer number : comb){
                outSet.add(number);
            }
        }

        int outs = outSet.size();
        System.out.println(outSet +" outs");
        if(outs > 0 && cardCount == 7 || outs > 2 && cardCount == 6 || outs == 3 && cardCount == 5 ){
            straightFlushChancePercentage = 0;
        }else{
            straightFlushChancePercentage = chanceForCombinationsMath(outs, 1);
        }
        
       
        return straightFlushChancePercentage;
    }
    // STRAIGHT FLUSH SECTION #######################################################################################################################STRAIGHT FLUSH SECTION#######
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

        //Ausgabe der Kombinationen mit der geringsten Anzahl Karten
        ArrayList<Integer> smallestList = missingDrawCards.stream().min(Comparator.comparingInt(ArrayList::size)).orElse(null);
        List<ArrayList<Integer>> smallestCombinations = missingDrawCards.stream().filter(draw -> draw.size() == smallestList.size()).collect(Collectors.toList());
        Set<Integer> outSet = new HashSet<Integer>();
        for(ArrayList<Integer>comb : smallestCombinations){
            for(Integer number : comb){
                outSet.add(number);
            }
        }
        System.out.println("Outs für momentane Karten und naheliegendste Kombination " + outSet);
        int outs = outSet.size();
        if(outs > 0 && cardCount == 7 || outs > 2 && cardCount == 6 || outs == 3 && cardCount == 5){
            straightChancePercentage = 0;
        }else{
            straightChancePercentage = chanceForCombinationsMath(outs, 4);
        }
        
        System.out.println("Chance:" + straightChancePercentage + "%");

        
        List<ArrayList<Integer>> secondSmallestCombinations = missingDrawCards.stream().filter(draw -> draw.size() == smallestList.size()+1).collect(Collectors.toList());
        Set<Integer> secondOutSet = new HashSet<Integer>();
        for(ArrayList<Integer>comb : secondSmallestCombinations){
            for(Integer number : comb){
                secondOutSet.add(number);
            }
        }
        System.out.println("Outs für zweit naheliegendste Kombination " + secondOutSet);
        int outs2 = secondOutSet.size();
        if(outs > 0 && cardCount == 7 || outs > 2 && cardCount == 6 || outs == 3 && cardCount == 5){
            straightChancePercentage = 0;
        }else{
        laterStraightChancePercentage = chanceForCombinationsMath(outs2, 4);
        }
        

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
    // FullHouse SECTION #######################################################################################################################FullHouse SECTION#######
    public double chanceForFullHouseOnPlayground(){
        double chanceForFullHouseOnPlayground = 0;
        int cardCount = 0;
        int equalsCounter = 0;
        boolean threeOfaKind = false;
        boolean twoOfaKind = false;
        
        for(int i = 0; i < realCardsOnPlayground.length;i++){
            int twoOfaKindInt = 0;
            int threeOfaKindInt = 0;
            for(int j = 0; j < realCardsOnPlayground.length; j++){
                if(realCardsOnPlayground[i].getValue() == realCardsOnPlayground[j].getValue()){
                    equalsCounter++;
                    cardCount++;
                    twoOfaKindInt++;
                    threeOfaKindInt++;
                    if(twoOfaKindInt == 2){
                        twoOfaKind = true;
                    }
                    if(threeOfaKindInt == 3){
                        threeOfaKind = true;
                    }
                }
                
            }
        }
        switch (equalsCounter) {
            case 1 -> {
                chanceForFullHouseOnPlayground = chanceForCombinationsMath(7, 1);
            }
            case 2 -> {
                chanceForFullHouseOnPlayground = chanceForCombinationsMath(6, 1);
            }
            case 3 -> {
                chanceForFullHouseOnPlayground = chanceForCombinationsMath(9, 1);
            }
            case 4 -> {
                if(twoOfaKind){
                    chanceForFullHouseOnPlayground =chanceForCombinationsMath(6,1);
                }else{
                    chanceForFullHouseOnPlayground = chanceForCombinationsMath(4, 3);
                }
                
            }
            
            case 5 -> {
                if(twoOfaKind){
                    chanceForFullHouseOnPlayground =chanceForCombinationsMath(5,1);
                }else{
                    chanceForFullHouseOnPlayground = 0;
                }
            }
            case 6 -> {
                if(twoOfaKind){
                    chanceForFullHouseOnPlayground =chanceForCombinationsMath(8,1);
                }else{
                    chanceForFullHouseOnPlayground = 0;
                }
            }
            case 7 -> {
                if(twoOfaKind){
                    chanceForFullHouseOnPlayground =chanceForCombinationsMath(3,3);
                }else{
                    chanceForFullHouseOnPlayground = 0;
                }
            }
            case 8 -> {
                if(twoOfaKind && cardCount ==4){
                    chanceForFullHouseOnPlayground = chanceForCombinationsMath(4, 1);
                }else if(twoOfaKind && cardCount ==6){
                    chanceForFullHouseOnPlayground = 0;
                }else{
                    chanceForFullHouseOnPlayground = 0;
                }
            }
            case 9 -> {
                if(threeOfaKind){
                    chanceForFullHouseOnPlayground =chanceForCombinationsMath(2,4);
                }else if(twoOfaKind){
                    chanceForFullHouseOnPlayground =chanceForCombinationsMath(4,1);
                }else{
                    chanceForFullHouseOnPlayground = 0;
                }
            }
            case 10 -> {
                if(threeOfaKind){
                    chanceForFullHouseOnPlayground =chanceForCombinationsMath(3,1);
                }else if(twoOfaKind){
                    chanceForFullHouseOnPlayground =chanceForCombinationsMath(4,1);
                }
                else{
                    chanceForFullHouseOnPlayground = 0;
                }
            }
            case 11 -> {
                if(threeOfaKind){
                    chanceForFullHouseOnPlayground =chanceForCombinationsMath(6,1);
                }else{
                    chanceForFullHouseOnPlayground = 0;
                }
            }
            case 12 -> {
                if(threeOfaKind && cardCount == 6){
                    chanceForFullHouseOnPlayground = chanceForCombinationsMath(3, 3);
                }else if(threeOfaKind){
                    chanceForFullHouseOnPlayground =chanceForCombinationsMath(9,2);
                }
                else {
                    chanceForFullHouseOnPlayground = 0;
                }
            }
            case 13 -> {
                if(threeOfaKind && cardCount == 5){
                    chanceForFullHouseOnPlayground = 100;
                }
                else{  
                    chanceForFullHouseOnPlayground = 0;
                }
            }
            default -> {
                chanceForFullHouseOnPlayground = chanceForCombinationsMath(8, 1);
            }
        }
        return chanceForFullHouseOnPlayground;
    }
    // FullHouse SECTION #######################################################################################################################FullHouse SECTION#######
    // FLUSH SECTION #######################################################################################################################FLUSH SECTION#######
    public double chanceForFlushOnPlayground(){
        double chanceForFlushOnPlayground = 0;
        ArrayList<String> playgroundCardSuits = new ArrayList<>();
        int countSpades = 0;
        int countHearts = 0;
        int countDiamonds = 0;
        int countClubs = 0;
        
        for(int i = 0; i < realCardsOnPlayground.length; i++){
            playgroundCardSuits.add(realCardsOnPlayground[i].getSuit());
        }
        for(String suit : playgroundCardSuits){
            if(suit.equals("spades")){
                countSpades++;
            }else if(suit.equals("heart")){
                countHearts++;
            }else if(suit.equals("diamonds")){
                countDiamonds++;
            }else if(suit.equals("clubs")){
                countClubs++;
            }
        }
        Map <String, Integer> counts = new HashMap<String, Integer>();
        counts.put("Spades", countSpades);
        counts.put("Hearts", countHearts);
        counts.put("Diamonds", countDiamonds);
        counts.put("Clubs", countClubs);

        Optional<Map.Entry<String, Integer>> suitWithMaxValue = counts.entrySet().stream()
        .max(Map.Entry.comparingByValue());

        int maxValue = suitWithMaxValue.map(Map.Entry::getValue).orElse(0);

        switch (maxValue) {
            case 5 -> chanceForFlushOnPlayground = 100;
            case 4 -> chanceForFlushOnPlayground = (realCardsOnPlayground.length == 7) ? 0 : chanceForCombinationsMath(9, 1);
            case 3 -> chanceForFlushOnPlayground = (realCardsOnPlayground.length >= 6) ? 0 : chanceForCombinationsMath(10, 1);
            case 2 -> chanceForFlushOnPlayground = (realCardsOnPlayground.length >= 5) ? 0 : chanceForCombinationsMath(11, 1);
            case 1 -> chanceForFlushOnPlayground = (realCardsOnPlayground.length >= 4) ? 0 : chanceForCombinationsMath(12, 1);
            case 0 -> chanceForFlushOnPlayground = chanceForCombinationsMath(13, 1);
            default -> chanceForFlushOnPlayground = chanceForCombinationsMath(13, 1);
        }

        return chanceForFlushOnPlayground;
    }
    // FLUSH SECTION #######################################################################################################################FLUSH SECTION#######
    // PAIR SECTION #######################################################################################################################PAIR SECTION#######
    public double chanceForPairOnPlayground(){
        double chanceForPairOnPlayground = 0;
        int cardCount = 0;
        int equalsCounter = 0;
        boolean twoOfaKind = false;
        
        for(int i = 0; i < realCardsOnPlayground.length;i++){
            int twoOfaKindInt = 0;
            for(int j = 0; j < realCardsOnPlayground.length; j++){
                if(realCardsOnPlayground[i].getValue() == realCardsOnPlayground[j].getValue()){
                    equalsCounter++;
                    cardCount++;
                    twoOfaKindInt++;
                    if(twoOfaKindInt == 2){
                        twoOfaKind = true;
                        break;
                    }
                }
                
            }
            
        }
        if(cardCount == 7 && !twoOfaKind){
            chanceForPairOnPlayground = 0;
        }else{
            switch (equalsCounter) {
                case 6 -> {
                    if(chanceForPairOnPlayground >= 100.0 || twoOfaKind){
                        chanceForPairOnPlayground =100.0;
                        break;
                    }
                    chanceForPairOnPlayground =chanceForCombinationsMath(3, 6);
                }
                case 5 -> {
                    if (chanceForPairOnPlayground >= 100.0 || twoOfaKind){
                        chanceForPairOnPlayground =100.0;
                        break;
                    }
                    chanceForPairOnPlayground =chanceForCombinationsMath(3, 5);
    
                }
                case 4 -> {
                    if (chanceForPairOnPlayground >= 100.0 || twoOfaKind){
                        chanceForPairOnPlayground =100.0;
                        break;
                    }
                    chanceForPairOnPlayground =chanceForCombinationsMath(3, 4);
                    
                }
                
                
                case 3 -> {
                    if(chanceForPairOnPlayground >= 100.0 || twoOfaKind){
                        chanceForPairOnPlayground =100.0;
                        break;
                    }
                    chanceForPairOnPlayground =chanceForCombinationsMath(3, 3);
                }
                case 2 -> {
                    if(chanceForPairOnPlayground >= 100.0 || twoOfaKind){
                        chanceForPairOnPlayground =100.0;
                        break;
                    }
                    chanceForPairOnPlayground =chanceForCombinationsMath(3, 2);
                }
                case 1 -> {
                    if(chanceForPairOnPlayground >= 100.0 || twoOfaKind){
                        chanceForPairOnPlayground =100.0;
                        break;
                    }
    
                    if (cardCount == 7){
                        chanceForPairOnPlayground = 0.0;
                    }else{
                        chanceForPairOnPlayground =chanceForCombinationsMath(1, 3);
                    }
                
                }
                case 0 -> {
                    if(chanceForPairOnPlayground >= 100.0 || twoOfaKind){ 
                        break;
                    }
                    if(cardCount == 7){
                        chanceForPairOnPlayground = 0.0;
                    }else{
                        chanceForPairOnPlayground =chanceForCombinationsMath(4, 1);
                    }
                    
                }
                default -> {
                    if(chanceForPairOnPlayground >= 100.0 || twoOfaKind){
                        chanceForPairOnPlayground =100.0;
                        break;
                    }
                }
            }
        }
        
        
        return chanceForPairOnPlayground ;
    }
    public double chanceForThreeOfAKindOnPlayground(){
        double chanceForThreeOfAKindOnPlayground = 0;
        int equalsCounter = 0;
        boolean threeOfAKindComb = false;
        int cardCount = 0;
        
        for(int i = 0; i < realCardsOnPlayground.length;i++){
            int threeOfAKind = 0;
            cardCount++;
            for(int j = 0; j < realCardsOnPlayground.length; j++){
                if(realCardsOnPlayground[i].getValue() == realCardsOnPlayground[j].getValue()){
                    equalsCounter++;
                    threeOfAKind ++;
                    
                    if(threeOfAKind == 3){
                        threeOfAKindComb = true;
                        break;
                    }
                }
                
            }
            
        }
        if(cardCount == 7 && !threeOfAKindComb){
            chanceForThreeOfAKindOnPlayground = 0.0;
        }else{
            switch (equalsCounter) {
                case 18 -> {
                    if (chanceForThreeOfAKindOnPlayground >= 100.0 || threeOfAKindComb){
                        chanceForThreeOfAKindOnPlayground = 100.0;
                    }
                }
                case 17 -> {
                    if (chanceForThreeOfAKindOnPlayground >= 100.0 || threeOfAKindComb){
                        chanceForThreeOfAKindOnPlayground = 100.0;
                    }
                }
                case 14 -> {
                    if (chanceForThreeOfAKindOnPlayground >= 100.0 || threeOfAKindComb){
                        chanceForThreeOfAKindOnPlayground = 100.0;
                    }
                }
                case 13 -> {
                    if (chanceForThreeOfAKindOnPlayground >= 100.0 || threeOfAKindComb){
                        chanceForThreeOfAKindOnPlayground = 100.0;
                    }else{
                        chanceForThreeOfAKindOnPlayground +=chanceForCombinationsMath(13, 1);
                    }
                }
                case 12 -> {
                    if (chanceForThreeOfAKindOnPlayground >= 100.0 || threeOfAKindComb){
                        chanceForThreeOfAKindOnPlayground = 100.0;
                    }else{
                        chanceForThreeOfAKindOnPlayground +=chanceForCombinationsMath(6, 1);
                    }
                }
                case 11 -> {
                    if (chanceForThreeOfAKindOnPlayground >= 100.0 || threeOfAKindComb){
                        chanceForThreeOfAKindOnPlayground = 100.0;
                    }
                }
                case 10 -> {
                    if (chanceForThreeOfAKindOnPlayground >= 100.0 || threeOfAKindComb){
                        chanceForThreeOfAKindOnPlayground = 100.0;
                    }else{
                        chanceForThreeOfAKindOnPlayground +=chanceForCombinationsMath(2, 1);
                        }
                }
                case 9 -> {
                    if (chanceForThreeOfAKindOnPlayground >= 100.0 || threeOfAKindComb){
                        chanceForThreeOfAKindOnPlayground = 100.0;
                    }else{
                        
                        chanceForThreeOfAKindOnPlayground +=chanceForCombinationsMath(7, 1);
                    }
                }
                case 8 -> {
                    if (chanceForThreeOfAKindOnPlayground >= 100.0 || threeOfAKindComb){
                        chanceForThreeOfAKindOnPlayground = 100.0;
                    }else{
                        if(cardCount == 6){
                            chanceForThreeOfAKindOnPlayground +=chanceForCombinationsMath(2, 1);
                        }else if(cardCount == 4){
                            chanceForThreeOfAKindOnPlayground +=chanceForCombinationsMath(4, 1);
                        }
                    }
                }
                case 7 -> {
                    if (chanceForThreeOfAKindOnPlayground >= 100.0 || threeOfAKindComb){
                        chanceForThreeOfAKindOnPlayground = 100.0;
                    }else{
                        chanceForThreeOfAKindOnPlayground +=chanceForCombinationsMath(11, 1);
                    }
                }
                case 6 -> {
                    if (chanceForThreeOfAKindOnPlayground >= 100.0 || threeOfAKindComb){
                        chanceForThreeOfAKindOnPlayground = 100.0;
                    }else{
                        chanceForThreeOfAKindOnPlayground +=chanceForCombinationsMath(8, 1);
                    }
                    break;
                }
                case 5 -> {
                    if (chanceForThreeOfAKindOnPlayground >= 100.0 || threeOfAKindComb){
                        chanceForThreeOfAKindOnPlayground = 100.0;
                    }else{
                        if(cardCount == 3){
                            chanceForThreeOfAKindOnPlayground +=chanceForCombinationsMath(5, 1);
                        }else if(cardCount == 5){
                            chanceForThreeOfAKindOnPlayground +=chanceForCombinationsMath(3, 1);
                        }
                        chanceForThreeOfAKindOnPlayground +=chanceForCombinationsMath(5, 1);
                    }
                }
                case 4 -> {
                    if (chanceForThreeOfAKindOnPlayground >= 100.0 || threeOfAKindComb){
                        chanceForThreeOfAKindOnPlayground = 100.0;
                    }else{
                        if(cardCount ==4){
                            chanceForThreeOfAKindOnPlayground +=chanceForCombinationsMath(12, 1);
                        }else if(equalsCounter == 4){
                            chanceForThreeOfAKindOnPlayground +=chanceForCombinationsMath(2, 1);
                        }
                        
                    }
                }
                case 3 -> {
                    if (chanceForThreeOfAKindOnPlayground >= 100.0 || threeOfAKindComb){
                        chanceForThreeOfAKindOnPlayground = 100.0;
                    }else{
                        if(cardCount ==3){
                            chanceForThreeOfAKindOnPlayground +=chanceForCombinationsMath(9, 1);
                        }
                    }
                    
                }
                case 2 -> {
                    if (chanceForThreeOfAKindOnPlayground >= 100.0 || threeOfAKindComb){
                        chanceForThreeOfAKindOnPlayground = 100.0;
                        break;
                    }else{
                        chanceForThreeOfAKindOnPlayground +=chanceForCombinationsMath(6, 1);
                    }
                }
                case 1 -> {
                    if (chanceForThreeOfAKindOnPlayground >= 100.0 || threeOfAKindComb){
                        chanceForThreeOfAKindOnPlayground = 100.0;
                    }else{
                        chanceForThreeOfAKindOnPlayground +=chanceForCombinationsMath(3, 1);
                    }
                }
                case 0 -> {
                    if (chanceForThreeOfAKindOnPlayground >= 100.0){
                        chanceForThreeOfAKindOnPlayground = 100.0;
                    }else{
                        chanceForThreeOfAKindOnPlayground +=chanceForCombinationsMath(4, 1);
                    }
                }
            }
        }
        
        
        return chanceForThreeOfAKindOnPlayground;
    }
    public double chanceForFourOfAKindOnPlayground(){
        double chanceForFourOfAKindOnPlayground = 0;
        int equalsCounter = 0;
        boolean fourOfAKindComb = false;
        int cardCount = 0;
        
        for(int i = 0; i < realCardsOnPlayground.length;i++){
            int fourOfAKind = 0;
            cardCount++;
            for(int j = 0; j < realCardsOnPlayground.length; j++){
                if(realCardsOnPlayground[i].getValue() == realCardsOnPlayground[j].getValue()){
                    equalsCounter++;
                    fourOfAKind ++;
                    
                    if(fourOfAKind == 4){
                        fourOfAKindComb = true;
                        break;
                    }
                }
                
            }
            
        }
        if(cardCount == 7 && !fourOfAKindComb){
            chanceForFourOfAKindOnPlayground = 0.0;
        }else{
            switch (equalsCounter) {
                case 19 -> {
                    if (chanceForFourOfAKindOnPlayground >= 100.0 || fourOfAKindComb){
                        chanceForFourOfAKindOnPlayground = 100.0;
                    }
                }
                case 18 -> {
                    if (chanceForFourOfAKindOnPlayground >= 100.0 || fourOfAKindComb){
                        chanceForFourOfAKindOnPlayground = 100.0;
                    }else{
                        chanceForFourOfAKindOnPlayground +=chanceForCombinationsMath(2, 1);
                    }
                }
                case 17 -> {
                    if (chanceForFourOfAKindOnPlayground >= 100.0 || fourOfAKindComb){
                        chanceForFourOfAKindOnPlayground = 100.0;
                    }
                }
                case 16 -> {
                    if (chanceForFourOfAKindOnPlayground >= 100.0 || fourOfAKindComb){
                        chanceForFourOfAKindOnPlayground = 100.0;
                    }
                }
                case 14 -> {
                    if(chanceForFourOfAKindOnPlayground >= 100.0 || fourOfAKindComb){
                        chanceForFourOfAKindOnPlayground = 100.0;
                    }else{
                        if(cardCount == 6){
                            chanceForFourOfAKindOnPlayground +=chanceForCombinationsMath(1, 1);
                        }else{
                            chanceForFourOfAKindOnPlayground +=chanceForCombinationsMath(6, 1);
                        }
                        
                    }
                }
                case 13 -> {
                    if (chanceForFourOfAKindOnPlayground >= 100.0 || fourOfAKindComb){
                        chanceForFourOfAKindOnPlayground = 100.0;
                    }else{
                        chanceForFourOfAKindOnPlayground +=chanceForCombinationsMath(3, 1);
                    }
                }
                case 12 -> {
                    if (chanceForFourOfAKindOnPlayground >= 100.0 || fourOfAKindComb){
                        chanceForFourOfAKindOnPlayground = 100.0;
                    }else{
                        if(cardCount == 6){
                            chanceForFourOfAKindOnPlayground +=chanceForCombinationsMath(1, 1);
                        }else{
                            chanceForFourOfAKindOnPlayground +=chanceForCombinationsMath(6, 1);
                        }
                        
                    }
                }
                case 11 -> {
                    if (chanceForFourOfAKindOnPlayground >= 100.0 || fourOfAKindComb){
                        chanceForFourOfAKindOnPlayground = 100.0;
                    }else{
                        chanceForFourOfAKindOnPlayground +=chanceForCombinationsMath(7, 1);
                    }
                }
                case 10 -> {
                    if (chanceForFourOfAKindOnPlayground >= 100.0 || fourOfAKindComb){
                        chanceForFourOfAKindOnPlayground = 100.0;
                    }else{
                        
                        if(cardCount == 6){
                            chanceForFourOfAKindOnPlayground = 0;
                        }else{
                            chanceForFourOfAKindOnPlayground +=chanceForCombinationsMath(4, 1);
                        }
                        }
                }
                case 9 -> {
                    if (chanceForFourOfAKindOnPlayground >= 100.0 || fourOfAKindComb){
                        chanceForFourOfAKindOnPlayground = 100.0;
                    }else{
                        if(cardCount == 6){
                            chanceForFourOfAKindOnPlayground +=chanceForCombinationsMath(4, 1);
                        }else {
                            chanceForFourOfAKindOnPlayground +=chanceForCombinationsMath(1, 1);
                        }
                    }
                        
                    
                }
                case 8 -> {
                    if (chanceForFourOfAKindOnPlayground >= 100.0 || fourOfAKindComb){
                        chanceForFourOfAKindOnPlayground = 100.0;
                    }else{
                        if(cardCount == 6){
                            chanceForFourOfAKindOnPlayground +=chanceForCombinationsMath(2, 1);
                        }else if(cardCount == 4){
                            chanceForFourOfAKindOnPlayground +=chanceForCombinationsMath(4, 1);
                        }
                        
                    }
                }
                case 7 -> {
                    if (chanceForFourOfAKindOnPlayground >= 100.0 || fourOfAKindComb){
                        chanceForFourOfAKindOnPlayground = 100.0;
                    }else{
                        if(cardCount == 7){
                            chanceForFourOfAKindOnPlayground = 0;
                        }else if(cardCount == 5){
                            chanceForFourOfAKindOnPlayground +=chanceForCombinationsMath(2, 1);
                        }
                        else{
                            chanceForFourOfAKindOnPlayground +=chanceForCombinationsMath(11, 1);
                        }
                        
                    }
                }
                case 6 -> {
                    if (chanceForFourOfAKindOnPlayground >= 100.0 || fourOfAKindComb){
                        chanceForFourOfAKindOnPlayground = 100.0;
                    }
                    else{
                        if(cardCount == 6){
                            chanceForFourOfAKindOnPlayground = 0;
                        }else{
                            chanceForFourOfAKindOnPlayground +=chanceForCombinationsMath(8, 1);
                        }
                        
                    }
                    
                    break;
                }
                case 5 -> {
                    if (chanceForFourOfAKindOnPlayground >= 100.0 || fourOfAKindComb){
                        chanceForFourOfAKindOnPlayground = 100.0;
                    }else{
                        if(cardCount ==5){
                            chanceForFourOfAKindOnPlayground = 0;
                        }else{
                            chanceForFourOfAKindOnPlayground +=chanceForCombinationsMath(5, 1);
                        }
                        
                    }
                }
                case 4 -> {
                    if (chanceForFourOfAKindOnPlayground >= 100.0 || fourOfAKindComb){
                        chanceForFourOfAKindOnPlayground = 100.0;
                    }else{
                        if(cardCount ==4){
                            chanceForFourOfAKindOnPlayground +=chanceForCombinationsMath(12, 1);
                        }else if(equalsCounter == 4){
                            chanceForFourOfAKindOnPlayground +=chanceForCombinationsMath(2, 1);
                        }
                    }
                    
                    
                }
                case 3 -> {
                    if (chanceForFourOfAKindOnPlayground >= 100.0 || fourOfAKindComb){
                        chanceForFourOfAKindOnPlayground = 100.0;
                    }else{
                        if(cardCount ==3){
                            chanceForFourOfAKindOnPlayground +=chanceForCombinationsMath(9, 1);
                        }
                    }
                    
                }
                case 2 -> {
                    if (chanceForFourOfAKindOnPlayground >= 100.0 || fourOfAKindComb){
                        chanceForFourOfAKindOnPlayground = 100.0;
                        break;
                    }else{
                        chanceForFourOfAKindOnPlayground +=chanceForCombinationsMath(6, 1);
                    }
                }
                case 1 -> {
                    if (chanceForFourOfAKindOnPlayground >= 100.0 || fourOfAKindComb){
                        chanceForFourOfAKindOnPlayground = 100.0;
                    }else{
                        chanceForFourOfAKindOnPlayground +=chanceForCombinationsMath(3, 1);
                    }
                }
                case 0 -> {
                    if (chanceForFourOfAKindOnPlayground >= 100.0){
                        chanceForFourOfAKindOnPlayground = 100.0;
                    }else{
                        chanceForFourOfAKindOnPlayground +=chanceForCombinationsMath(4, 1);
                    }
                }
            }
        }
        
        
        return chanceForFourOfAKindOnPlayground;
    }
    public double chanceForDoublePairsOnPlayground(){
        double chanceForDoublePairOnPlayground = 0;
        int equalsCounter = 0;
        boolean twoOfaKind = false;
        int cardCount = 0;
        
        
        for(int i = 0; i < realCardsOnPlayground.length;i++){
            int twoOfaKindInt = 0;
            cardCount = 0;
            for(int j = 0; j < realCardsOnPlayground.length; j++){
                cardCount++;
                if(realCardsOnPlayground[i].getValue() == realCardsOnPlayground[j].getValue()){
                    equalsCounter++;
                    twoOfaKindInt++;
                    if(twoOfaKindInt == 2){
                        twoOfaKind = true;
                        break;
                    }
                }
                
            }
            
        }
        
        switch (equalsCounter) {
            case 11 -> {
                chanceForDoublePairOnPlayground= 100;
            }
            case 10,9 ->{
                if(cardCount == 7){
                    chanceForDoublePairOnPlayground = 0;
                }else{
                    chanceForDoublePairOnPlayground = 100;
                }
                
            }
            
            case 8 -> {
                if(twoOfaKind && cardCount == 6){
                    chanceForDoublePairOnPlayground =chanceForCombinationsMath(4, 3);
                }else if(twoOfaKind && cardCount == 4){
                    chanceForDoublePairOnPlayground = 100;
                }         
            }
            case 7 -> {
                if(twoOfaKind){ 
                    chanceForDoublePairOnPlayground =chanceForCombinationsMath(3, 3);
                }else{
                    chanceForDoublePairOnPlayground = 0;
                }
            }
            case 6 -> {
                if(twoOfaKind){
                    chanceForDoublePairOnPlayground =chanceForCombinationsMath(3, 2);
                }else{
                    chanceForDoublePairOnPlayground = 0;
                }
                
            }
            case 5 -> {
                if (twoOfaKind){
                    chanceForDoublePairOnPlayground =chanceForCombinationsMath(3, 1);
                }else{
                    chanceForDoublePairOnPlayground =chanceForCombinationsMath(3, 5);
                }
                

            }
            case 4 -> {
                if (twoOfaKind){
                    chanceForDoublePairOnPlayground =chanceForCombinationsMath(4, 1);
                    
                }else{
                    chanceForDoublePairOnPlayground =chanceForCombinationsMath(3, 4);
                }
                
                
            }
            
            
            case 3 -> {
                chanceForDoublePairOnPlayground =chanceForCombinationsMath(3, 3);
            }
            case 2 -> {
                chanceForDoublePairOnPlayground =chanceForCombinationsMath(3, 2);
            }
            case 1 -> {
                chanceForDoublePairOnPlayground =chanceForCombinationsMath(7, 1);
            }
            case 0 -> {
                chanceForDoublePairOnPlayground =chanceForCombinationsMath(8, 1);
            }
            default -> {
                chanceForDoublePairOnPlayground = 0;
            }
        }
        return chanceForDoublePairOnPlayground;
    }
    public double chanceForPairSolo(){
        
        List<Integer> values = new ArrayList<>();
        for (CardPanel card : realCardsOnPlayground) {
            values.add(card.getValue());
        }
        
        Map<Integer, Integer> counts = new HashMap<>();
        for (int value : values) {
            counts.put(value, counts.getOrDefault(value, 0) + 1);
        }
        
        Optional<Map.Entry<Integer, Integer>> entryWithMaxValue = counts.entrySet().stream()
        .max(Map.Entry.comparingByValue());

        if (entryWithMaxValue.isPresent()) {
            Map.Entry<Integer, Integer> maxEntry = entryWithMaxValue.get();
            int keyWithMaxValue = maxEntry.getKey();
            int maxValue = maxEntry.getValue();
            System.out.println("Die Zahl mit den meisten Vorkommen ist: " + keyWithMaxValue + " mit " + maxValue + " Vorkommen.");
            if(maxValue== 2 || maxValue == 3 || maxValue == 4){
                return 100.0;
            }else{
                return chanceForCombinationsMath(4 - maxValue, 1);
            }
        } else {
            System.out.println("Es gibt keine Zahlen auf dem Feld.");
            return chanceForCombinationsMath(4,1);
            
        }
    }
    public double chanceForThreeOfAKindSolo(){
        List<Integer> values = new ArrayList<>();
        for (CardPanel card : realCardsOnPlayground) {
            values.add(card.getValue());
        }
        
        Map<Integer, Integer> counts = new HashMap<>();
        for (int value : values) {
            counts.put(value, counts.getOrDefault(value, 0) + 1);
        }
        
        Optional<Map.Entry<Integer, Integer>> entryWithMaxValue = counts.entrySet().stream()
        .max(Map.Entry.comparingByValue());

        if (entryWithMaxValue.isPresent()) {
            Map.Entry<Integer, Integer> maxEntry = entryWithMaxValue.get();
            int keyWithMaxValue = maxEntry.getKey();
            int maxValue = maxEntry.getValue();
            System.out.println("Die Zahl mit den meisten Vorkommen ist: " + keyWithMaxValue + " mit " + maxValue + " Vorkommen.");
            if(maxValue == 3 || maxValue == 4){
                return 100.0;
            }else{
                return chanceForCombinationsMath(4 - maxValue, 1);
            }
            
        } else {
            System.out.println("Es gibt keine Zahlen auf dem Feld.");
            return chanceForCombinationsMath(4,1);
            
        }
        
    }
    public double chanceForFourOfAKindSolo() {
        List<Integer> values = new ArrayList<>();
        for (CardPanel card : realCardsOnPlayground) {
            values.add(card.getValue());
        }
        
        Map<Integer, Integer> counts = new HashMap<>();
        for (int value : values) {
            counts.put(value, counts.getOrDefault(value, 0) + 1);
        }
        
        Optional<Map.Entry<Integer, Integer>> entryWithMaxValue = counts.entrySet().stream()
        .max(Map.Entry.comparingByValue());

        if (entryWithMaxValue.isPresent()) {
            Map.Entry<Integer, Integer> maxEntry = entryWithMaxValue.get();
            int keyWithMaxValue = maxEntry.getKey();
            int maxValue = maxEntry.getValue();
            System.out.println("Die Zahl mit den meisten Vorkommen ist: " + keyWithMaxValue + " mit " + maxValue + " Vorkommen.");
            if(maxValue== 4){
                return 100.0;
            }else{
                return chanceForCombinationsMath(4 - maxValue, 1);
            }
            
        } else {
            System.out.println("Es gibt keine Zahlen auf dem Feld.");
            return chanceForCombinationsMath(4,1);
            
        }
    }
    public double chanceForPairTotal() {
        double chanceForPairOnPlayground = chanceForPairOnPlayground() / 100.0;
        
        double chanceForPairInDeck = 0;
    
        for (int i = 0; i < sizeDeck - realCardsOnPlayground.length; i++) {
            int remainingCards = sizeDeck - realCardsOnPlayground.length - i;
            // Berechne die Wahrscheinlichkeit, dass eine der verbleibenden Karten ein Paar bildet
            chanceForPairInDeck += (chanceForCombinationsMath(1, remainingCards) / 100.0);
        }
    
        // Gesamtwahrscheinlichkeit berechnen, unter Berücksichtigung der Überlappung
        double totalChance = chanceForPairOnPlayground + chanceForPairInDeck - (chanceForPairOnPlayground * chanceForPairInDeck);
        if(chanceForPairOnPlayground() == 100.0){
            return totalChance = 100;
        }
        String formattedResult = String.format("%.2f", totalChance);

        return Double.parseDouble(formattedResult.replace(",", ".")); // Runde auf zwei Dezimalstellen
    }
    // PAIR SECTION #######################################################################################################################PAIR SECTION#######
    // TABLE SECTION #######################################################################################################################TABLE SECTION#######
    
    public void setChancesForDrawTable(){   
        
        defaultTableModelChances.addRow(new Object[]{"Royal Flush Chance" , chanceForRoyalFlushOnPlayground() + "%",});
        
        defaultTableModelChances.addRow(new Object[]{"Full House Chance for Board", chanceForFullHouseOnPlayground() + "%"});
        defaultTableModelChances.addRow(new Object[]{"Straight Flush Chance", chanceForStraightFlushOnPlayground() + "%"});
        defaultTableModelChances.addRow(new Object[]{"Flush Chance for Board", chanceForFlushOnPlayground()+ "%"});
        defaultTableModelChances.addRow(new Object[]{"Straight Chance",straightChancePercentage + "%"});
        defaultTableModelChances.addRow(new Object[]{"Later Straight", laterStraightChancePercentage + "%"});
        defaultTableModelChances.addRow(new Object[]{"Quad Chance for Board", chanceForFourOfAKindOnPlayground() + "%"});
        defaultTableModelChances.addRow(new Object[]{"Three of a Kind Chance for Board", chanceForThreeOfAKindOnPlayground() + "%"});
        defaultTableModelChances.addRow(new Object[]{"Two Pair Chance for Board", chanceForDoublePairsOnPlayground() + "%"});
        defaultTableModelChances.addRow(new Object[]{"Pair Chance for Board", chanceForPairOnPlayground() + "%"});
        
        defaultTableModelChances.addRow(new Object[]{"Quad Solo",chanceForFourOfAKindSolo() + "%"});
        defaultTableModelChances.addRow(new Object[]{"Triple Solo",chanceForThreeOfAKindSolo() + "%"});
        defaultTableModelChances.addRow(new Object[]{"Pair Solo",chanceForPairSolo() + "%"});
    }
    
    public void setEmptyTableRow(){
        defaultTableModelChances.addRow(new Object[]{"", ""});
    }
    // TABLE SECTION #######################################################################################################################TABLE SECTION#######
    public Double chanceForCombinationsMath(int outsCon, int multiplier){
        int outs = outsCon * multiplier;
        double straightChance = ((double)outs/deckCount)*100;
        String formattedResult = String.format("%.2f", straightChance);
        if(outs == 0){
            return 100.0;
        }
        return Double.parseDouble(formattedResult.replace(",", "."));
    }
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
        defaultTableModelChances.setRowCount(0);
        getActualCardsOnPlayground();
        sortPlaygroundByValues();
        chanceStraight2();
        findMissingCardsForStraight();
        setChancesForDrawTable();
        

        mainFrame.getCalcPanel().revalidate();
        mainFrame.getCalcPanel().repaint();
        
    }

}