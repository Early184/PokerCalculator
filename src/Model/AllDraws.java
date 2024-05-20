package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllDraws {
    
    private static final int[] RANKS = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14}; // 2-10, J, Q, K, A represented as 11, 12, 13, 14
    private static final String[] SUITS = {"clubs", "diamonds", "hearts", "spades"}; // Clubs, Diamonds, Hearts, Spades
    private int combinationCounter = 1; // to keep track of the key
    private Map<Integer, String> pokerHands;

    public AllDraws() {
        pokerHands = new HashMap<>();
        generateAllHands();

    }

    public void generateAllHands() {
        generatePairs();
        generateTwoPairs();
        generateThreeOfAKind();
        generateStraights();
        generateFlushes();
        generateFullHouses();
        generateFourOfAKind();
        generateStraightFlushes();
        generateRoyalFlushes();
        
    }

    private void generatePairs() {
        for (int rank : RANKS) {
            for (int i = 0; i < SUITS.length; i++) {
                for (int j = i + 1; j < SUITS.length; j++) {
                    pokerHands.put(combinationCounter++, rank + SUITS[i] + "," + rank + SUITS[j]+ "; ");
                }
            }
        }
        for(int key : pokerHands.keySet()) {
            System.out.print(pokerHands.get(key));
        }
    }

    private void generateTwoPairs() {
        for (int i = 0; i < RANKS.length; i++) {
            for (int j = i + 1; j < RANKS.length; j++) {
                for (int k = 0; k < SUITS.length; k++) {
                    for (int l = k + 1; l < SUITS.length; l++) {
                        for (int m = 0; m < SUITS.length; m++) {
                            for (int n = m + 1; n < SUITS.length; n++) {
                                pokerHands.put(combinationCounter++, RANKS[i] + SUITS[k] + ", " + RANKS[i] + SUITS[l] + ", " + RANKS[j] + SUITS[m] + ", " + RANKS[j] + SUITS[n]);
                            }
                        }
                    }
                }
            }
        }
    }

    private void generateThreeOfAKind() {
        for (int rank : RANKS) {
            for (int i = 0; i < SUITS.length; i++) {
                for (int j = i + 1; j < SUITS.length; j++) {
                    for (int k = j + 1; k < SUITS.length; k++) {
                        pokerHands.put(combinationCounter++, rank + SUITS[i] + ", " + rank + SUITS[j] + ", " + rank + SUITS[k]);
                    }
                }
            }
        }
    }

    private void generateStraights() {
        for (int i = 0; i <= RANKS.length - 5; i++) {
            for (int j = 0; j < SUITS.length; j++) {
                pokerHands.put(combinationCounter++, RANKS[i] + SUITS[j] + ", " + RANKS[i + 1] + SUITS[j] + ", " + RANKS[i + 2] + SUITS[j] + ", " + RANKS[i + 3] + SUITS[j] + ", " + RANKS[i + 4] + SUITS[j]);
            }
        }
        
    }

    private void generateFlushes() {
        for (String suit : SUITS) {
            for (int i = 0; i <= RANKS.length - 5; i++) {
                pokerHands.put(combinationCounter++, RANKS[i] + suit + ", " + RANKS[i + 1] + suit + ", " + RANKS[i + 2] + suit + ", " + RANKS[i + 3] + suit + ", " + RANKS[i + 4] + suit);
            }
        }
    }

    private void generateFullHouses() {
        for (int i = 0; i < RANKS.length; i++) {
            for (int j = 0; j < RANKS.length; j++) {
                if (i != j) {
                    for (int k = 0; k < SUITS.length; k++) {
                        for (int l = k + 1; l < SUITS.length; l++) {
                            for (int m = l + 1; m < SUITS.length; m++) {
                                for (int n = 0; n < SUITS.length; n++) {
                                    for (int o = n + 1; o < SUITS.length; o++) {
                                        pokerHands.put(combinationCounter++, RANKS[i] + SUITS[k] + ", " + RANKS[i] + SUITS[l] + ", " + RANKS[i] + SUITS[m] + ", " + RANKS[j] + SUITS[n] + ", " + RANKS[j] + SUITS[o]);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void generateFourOfAKind() {
        for (int rank : RANKS) {
            for (int i = 0; i < SUITS.length; i++) {
                for (int j = i + 1; j < SUITS.length; j++) {
                    for (int k = j + 1; k < SUITS.length; k++) {
                        for (int l = k + 1; l < SUITS.length; l++) {
                            pokerHands.put(combinationCounter++, rank + SUITS[i] + ", " + rank + SUITS[j] + ", " + rank + SUITS[k] + ", " + rank + SUITS[l]);
                        }
                    }
                }
            }
        }
    }

    private void generateStraightFlushes() {
        for (String suit : SUITS) {
            for (int i = 0; i <= RANKS.length - 5; i++) {
                pokerHands.put(combinationCounter++, RANKS[i] + suit + ", " + RANKS[i + 1] + suit + ", " + RANKS[i + 2] + suit + ", " + RANKS[i + 3] + suit + ", " + RANKS[i + 4] + suit);
            }
        }
    }

    private void generateRoyalFlushes() {
        for (String suit : SUITS) {
            pokerHands.put(combinationCounter++, "10" + suit + ", " + "11" + suit + ", " + "12" + suit + ", " + "13" + suit + ", " + "14" + suit);
        }
    }

    public Map<Integer, String> getPokerHands() {
        return pokerHands;
    }
}