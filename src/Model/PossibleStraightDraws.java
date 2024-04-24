package Model;

import java.util.ArrayList;
import java.util.Arrays;

public class PossibleStraightDraws {
    private ArrayList<Integer> drawSums;
    private ArrayList<int[]> drawList; 

    
    
    public PossibleStraightDraws(){
        drawList = new ArrayList<>();
        drawSums = new ArrayList<>();
        generateCombinations();
        generateCombinationSums();
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
    // Summe jeder Kombination
    public void generateCombinationSums() {
        for (int[] combination : drawList) {
            int sum = Arrays.stream(combination).sum();
            drawSums.add(sum);
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
