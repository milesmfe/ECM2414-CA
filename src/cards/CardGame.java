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
    private static int numPlayers;
    private static ArrayList<Player> playerList;
    private static ArrayList<CardDeck> deckList;
    private static ArrayList<Card> pack;
    private static int playerTurn = 0;

    
    /**
     * CardGame constructor. Initialises ArrayLists
     * and proceeds to populate them with players,
     * decks and cards.
     * 
     * @param n The number of players
     * @author Miles Edwards
     * @version 1.0
     * 
    */
    public CardGame(int n) {
        numPlayers = n;
        playerList = new ArrayList<Player>();
        deckList = new ArrayList<CardDeck>();
        pack = new ArrayList<Card>();

        for (int i = 0; i < n; i++) {
            playerList.add(new Player());
            deckList.add(new CardDeck());
        }
        for (int i = 0; i < 8*n; i++) {
            /**
             * Once file loaded packs are implemented replace
             * line so that the next card in the file pack is
             * appended.
             */
            pack.add(new Card());
        }
    }


    // Get methods (Used for testing)
    public ArrayList<Player> getPlayers() {
        return playerList;
    }

    public ArrayList<CardDeck> getDecks() {
        return deckList;
    }

    public ArrayList<Card> getPack() {
        return pack;
    }

    public int getPlayerTurn() {
        return playerTurn;
    }


    /**
     * dealHands method. Iterates 4 times representing the size
     * of each player's hand. Each iteration iterates through
     * playerList, setting a card for the current index in each
     * player's hand.
     * 
     * @author Miles Edwards
     * @version 1.0
     * 
     */
    public void dealHands() {
        for (int i = 0; i < 4; i++) {  
            for (Player player : playerList) {
                Card card = pack.get(pack.size()-1);
                pack.remove(pack.size()-1);
                player.setCardAt(i, card);
            }
        }
    }

    /**
     * populateDecks method. Iterates 4 times representing the size
     * of each deck. Each iteration iterates through deckList,
     * setting a card for the current index in each pack.
     * 
     * @author Miles Edwards
     * @version 1.0
     * 
     */
    public void populateDecks() {
        for (int i = 0; i < 4; i++) {  
            for (CardDeck deck : deckList) {
                Card card = pack.get(pack.size() - 1);
                pack.remove(pack.size() - 1);
                deck.addCard(card);
            }
        }
    }


    /**
     * nextTurn method. Determines which player's turn it is.
     * Finds the current player object and the CardDeck objects
     * either side of the current player. Proceeds to play a
     * turn according to the game rules.
     * 
     * @author Miles Edwards
     * @version 1.0
     * @return flag indicating if current player has winning hand
     * 
     */
    public boolean nextTurn() {
        if (playerTurn == numPlayers - 1) {
            playerTurn = 0;
        } else {
            playerTurn++;
        }

        CardDeck rightDeck;
        CardDeck leftDeck = deckList.get(playerTurn);
        Player player = playerList.get(playerTurn);

        if (playerTurn + 1 == numPlayers) {
            rightDeck = deckList.get(0);
        } else {
            rightDeck = deckList.get(playerTurn + 1);
        }

        player.pickCardFrom(leftDeck);
        player.discardCardTo(rightDeck);
        player.updateHandVolatility();

        if (player.checkHand()) {
            return true;
        }
        return false;
    }


    public static void main(String[] args) throws Exception {
        /**
         * This is the executable method.
         * 
         */
    }
}
