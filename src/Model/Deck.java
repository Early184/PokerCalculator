package Model;

import java.util.HashMap;

public class Deck {
    
    private HashMap <Integer, CardPanel> cards = new HashMap<>();
    private int amountCards = cards.size();
    private static Deck instance;
    
    private Deck(){
        
    }
    public static Deck getInstance(){
        if(instance == null){
            instance = new Deck();
        }
        return instance;
    }



    public void addCard(CardPanel card){
        cards.put(cards.size() + 1, card);
        amountCards++;
    }
    
    public void pickCard(CardPanel cardPanel){
        
    }
    public void refillDeck(){

    }
    public void fillDeck(){

    }

    public int getAmountCards() {
        return amountCards;
    }
    
    public CardPanel getCard(int index) {
        return cards.get(index);
    }
    public CardPanel getLastCardPanel(){
        int highestKey = 0;
        for(Integer key : cards.keySet()){
            if(key > highestKey){
                highestKey = key;
            }
        }
        return cards.get(highestKey);
    }
    

    public HashMap<Integer, CardPanel> getCards() {
        return cards;
    }
    

    public void setCards(HashMap<Integer, CardPanel> cards) {
        this.cards = cards;
    }

    
}
