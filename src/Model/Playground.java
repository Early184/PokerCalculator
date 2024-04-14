package Model;

import java.util.ArrayList;
import java.util.List;


import View.CustomCompontents.CustomObserver;

public class Playground {

    private static CardPanel[]cardSlots = new CardPanel[7];
    private static int countedCards = 0;

    private List<CustomObserver> observers = new ArrayList<>();

    public void addObserver(CustomObserver observer){
        observers.add(observer);
    }
    public void removeObserver(CustomObserver observer){
        observers.remove(observer);
    }
    private void notifyObservers(){
        for(CustomObserver observer : observers){
            observer.update();
        }
    }
   
   

    public Playground(){
        createEmptyCards();
        
    }

    public void createEmptyCards(){
        for(int i= 0; i < cardSlots.length; i++){
            cardSlots[i] = new CardPanel(0, "");
        }
    }
    public CardPanel getCard(int i){
        return cardSlots[i];
    }
    public void setCard(CardPanel cardPanel, int i){
        cardSlots[i] = cardPanel;
    }
    
    public CardPanel[] getCardSlots() {
        return cardSlots;
    }
    
    public CardPanel getCardSlot(int i){
        return cardSlots[i];
    }
    public void addCard(CardPanel card, int place){
        cardSlots[place] = card;
        notifyObservers();
    }
    public void removeCards(){
        //createEmptyCards();
        for(CardPanel cardPanel : cardSlots){
            removeCard(cardPanel);
        }
        notifyObservers();
        System.out.println();
    }
    public void removeCard(CardPanel clickedCard){
        for (int i = 0; i < cardSlots.length; i++) {
            if (cardSlots[i].equals(clickedCard)) {
                
                cardSlots[i].setImagePath("null");
                cardSlots[i].setSuit("");
                cardSlots[i].setValue(0);
                break; 
            }
        }
        notifyObservers();
    }

    public CardPanel getNextEmptyCardPanel(){
        for (CardPanel panel: cardSlots){
            
            if(panel.getSuit() == ""){
                return panel;
            }
            
        }
        notifyObservers();
        return null;
    }
    public Playground getPlayground() {
        return this;
    }
    public int getCountedCards() {
        return countedCards;
    }
    public static void setCountedCards(int countedCards) {
        Playground.countedCards = countedCards;
    }
    public void addCountedCards(){
        Playground.countedCards ++;
        notifyObservers();
    }
    public static void subCountedCards(){
        Playground.countedCards--;
    }
    
}
