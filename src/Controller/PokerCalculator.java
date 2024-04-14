package Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import Model.CardPanel;
import Model.Deck;
import Model.Playground;
import View.CustomCompontents.CustomObserver;

public class PokerCalculator implements CustomObserver{
    
    private Playground playground;
    private Deck deck;
    private CardPanel[]cardsForCalc;
    
    private int sizeDeck;
    private CardPanel[] realCardsOnPlayground;
    private int deckCount;
    private int[] outs;
    private int cardCount;


    public PokerCalculator(){
        this.playground = new Playground();
        cardsForCalc = playground.getCardSlots(); 
        playground.addObserver(this);
        this.deck = Deck.getInstance();
        this.sizeDeck = deck.getAmountCards();

        getActualCardsOnPlayground();
        sortPlaygroundByValues();
        chanceStraight();
    }
    
    public void chanceStraight(){
        // Identifiziere aufeinanderfolgende Karten und bestimme die "Outs"
        outs = new int[0];
        for(int i = 0; i < realCardsOnPlayground.length - 1; i++){
            if(realCardsOnPlayground[i + 1].getValue() - realCardsOnPlayground[i].getValue() == 1){
                int missingCard = realCardsOnPlayground[i].getValue() + 1;
                System.out.println(missingCard);
                outs = Arrays.copyOf(outs, outs.length + 1);
                outs[outs.length - 1] = missingCard;
            }
        }

        // cards geoordnet ausgeben lassen + outs

        for(int i = 0; i < outs.length; i++){
            System.out.println("Outs:" + outs[i]);
        }
        
        for(int i = 0; i < realCardsOnPlayground.length; i++){
            System.out.println("Karte:" + realCardsOnPlayground[i].getValue() + realCardsOnPlayground[i].getSuit());
        }
        
    }

    private void sortPlaygroundByValues() {
        // Zähle die Anzahl der Karten im Playground
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
        chanceStraight();
    }

}
