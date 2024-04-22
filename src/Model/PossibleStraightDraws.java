package Model;

import java.util.ArrayList;

public class PossibleStraightDraws {
    private ArrayList<Integer> drawSums;
    private ArrayList<int[]> drawList; 

    
    
    public PossibleStraightDraws(){
        drawList = new ArrayList<>();
        drawSums = new ArrayList<>();
        generateCombinations();
        generateCombinationCounts();
    }
    //Schleife zur Generierung der Kombinationen
    public void generateCombinations(){
           for (int i = 1; i <= 10; i++) { 
            if(i == 10){
                int[] combination = new int[5];
                int value = i;
                for (int j = 0; j < 5; j++) {
                    if(j == 4){
                        combination[j] = getValueForCard(1);
                    }else{
                        combination[j] = getValueForCard(value + j); 
                    }
                    
                }
                drawList.add(combination); 
            }else{
                int[] combination = new int[5];
                int value = i;
                for (int j = 0; j < 5; j++) {
                    
                    combination[j] = getValueForCard(value + j); 
                }
                drawList.add(combination); 
            }
            
        }
    }
    // Schleife zur Generierung von der Summe der Kombinationen
    public void generateCombinationCounts() {
        for (int i = 0; i < drawList.size(); i++) { 
            int[] combination = drawList.get(i);
            int combinationValue = 0;

            for (int j = 0; j < combination.length; j++) {
                combinationValue += combination[j]; 
            }
            drawSums.add(combinationValue); 
        }
    }
    
    // Methode zum Abrufen des Werts einer Karte anhand ihrer Value
    public int getValueForCard(int value) {
        switch (value) {
            case 1: // ACE
                return CardEnum.ACE.getValue();
            case 2: // TWO
                return CardEnum.TWO.getValue();
            case 3: // THREE
                return CardEnum.THREE.getValue();
            case 4: // FOUR
                return CardEnum.FOUR.getValue();
            case 5: // FIVE
                return CardEnum.FIVE.getValue();
            case 6: // SIX
                return CardEnum.SIX.getValue();
            case 7: // SEVEN
                return CardEnum.SEVEN.getValue();
            case 8: // EIGHT
                return CardEnum.EIGHT.getValue();
            case 9: // NINE
                return CardEnum.NINE.getValue();
            case 10: // TEN
                return CardEnum.TEN.getValue();
            case 11: // JACK
                return CardEnum.JACK.getValue();
            case 12: // QUEEN
                return CardEnum.QUEEN.getValue();
            case 13: // KING
                return CardEnum.KING.getValue();
            default:
                return 0; 
        }
    }
    public ArrayList<Integer> getDrawSums() {
        return drawSums;
    }
    public ArrayList<int[]> getDrawList() {
        return drawList;
    }
}
