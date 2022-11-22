package cards;

import java.util.ArrayList;

/**
 * CardGame class.
 * 
 * @author Miles Edwards
 * @version 1.0
 * 
 */
public class CardGame {
    private static ArrayList<Player> playerList;
    private static ArrayList<CardDeck> deckList;
    private static ArrayList<Card> pack;
    private static int playerTurn = 0;

    
    //Constructor
    public CardGame(int n) {
        /**
         * Create n players and n decks, adding each to their respective list.
         * Generate a "pack" with 8n cards.
         * 
         */
    }

    private ArrayList<Card> dealHands(ArrayList<Card> p) {
        /**
         * Use recursion to 1 card to each player hand.
         * Each cycle deals n cards.
         * Exit once pack contains 4n cards.
         * 
         */
        return p;
    }

    private void nextTurn() {
        /**
         * Set the value of "playerTurn" to the index of the 
         * next player in "players"
         * Remember if i+1 == n then set i=0
         * 
         */
    }
    

    private boolean turn(Player player) {
        /**
         * Perform a 'turn' for the "player":
         * Find leftDeck and rightDeck by getting the deck at index
         * of playerTurn index and index+1 respectively.
         * Remember if i+1 == n then set i=0.
         * Do the following operations on "player" in order:
         * pickCardFrom(leftDeck);
         * discardCardTo(rightDeck);
         * updateHandVolatility();
         * 
         */
        return false;
    }


    public static void main(String[] args) throws Exception {
        /**
         * This is the executable method.
         * 
         */
    }
}
