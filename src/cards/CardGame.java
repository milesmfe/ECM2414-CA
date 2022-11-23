package cards;

import java.util.ArrayList;

/**
 * CardGame class.
 * 
 * @author Miles Edwards
 * @version 1.1
 * 
 */
public class CardGame {
    private ArrayList<Player> playerList;
    private ArrayList<CardDeck> deckList;
    private ArrayList<Card> pack;
    private int numPlayers = 0;
    private boolean setupComplete = false;
    private Player winningPlayer = null;

    
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
            playerList.add(new Player(this));
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


    /**
     * getNumPlayers method.
     * 
     * @author Miles Edwards
     * @version 1.0
     * @return the number of players in the game.
     * 
     */
    public int getNumPlayers() {
        return numPlayers;
    }


    /**
     * getStatus method.
     * 
     * @author Miles Edwards
     * @version 1.0
     * @return 0 if setup is not complete, 1 if setup is complete, 2 if game has a winner.
     * 
     */
    public int getStatus() {
        if (setupComplete) {
            if (winningPlayer == null) {
                return 1;
            } else {
                return 2;
            }
        } else {
            return 0;
        }
    }


    /**
     * getPlayers method.
     * 
     * @author Miles Edwards
     * @version 1.0
     * @return the list of players in the game.
     * 
     */
    public ArrayList<Player> getPlayers() {
        return playerList;
    }


    /**
     * getPlayerNumberOf method.
     * 
     * @author Miles Edwards
     * @version 1.0
     * @return the player number of a specified player in the game.
     * @param p a specific player in the game.
     * 
     */
    public int getPlayerNumberOf(Player p) {
        return playerList.indexOf(p);
    }


     /**
     * getPlayer method.
     * 
     * @author Miles Edwards
     * @version 1.0
     * @return the player object of a specified player in the game.
     * @param n a specific player's number
     * 
     */
    public Player getPlayer(int n) {
        return playerList.get(--n);
    }


    /**
     * getDecks method.
     * 
     * @author Miles Edwards
     * @version 1.0
     * @return the list of decks in the game.
     * 
     */
    public ArrayList<CardDeck> getDecks() {
        return deckList;
    }


    /**
     * getPack method.
     * 
     * @author Miles Edwards
     * @version 1.0
     * @return the pack for the game.
     * 
     */
    public ArrayList<Card> getPack() {
        return pack;
    }


    public Player getWinningPlayer() {
        return winningPlayer;
    }


    /**
     * setupGame method. Calls dealHands and populateDecks.
     * Any issue is caught as a generic Exception.
     * 
     * @author Miles Edwards
     * @version 1.0
     * @return flag indicating whether or not the setup was
     * successful.
     * 
     */
    public boolean setupGame() {
        try {
            dealHands();
            populateDecks();
            for (int i = 0; i < 4; i++) {
                playerList.get(i).locateDecks();
            }
            setupComplete = true;
            return true;
        } catch (Exception e) {
            return false;
        }
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
    private void dealHands() {
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
    private void populateDecks() {
        for (int i = 0; i < 4; i++) {  
            for (CardDeck deck : deckList) {
                Card card = pack.get(pack.size() - 1);
                pack.remove(pack.size() - 1);
                deck.addCard(card);
            }
        }
    }


    /**
     * deckLeftOf method
     * 
     * @author Miles Edwards
     * @version 1.0
     * @param p a specific player in the game.
     * @return the deck to the left of player p
     * 
     */
    public CardDeck deckLeftOf(int p) {
        return deckList.get(--p);
    }


    /**
     * deckRightOf method
     * 
     * @author Miles Edwards
     * @version 1.0
     * @param p a specific player in the game.
     * @return the deck to the right of player p
     * 
     */
    public CardDeck deckRightOf(int p) {
        if (p == numPlayers) {
            return deckList.get(0);
        } else {
            return deckList.get(p);
        }
    }


    /**
     * declareWinnerAs method. Player threads should use this to declare
     * that they have a winning hand.
     * 
     * @author Miles Edwards
     * @version 1.0
     * @param p a specific player in the game.
     * @return true if player p wins, otherwise false.
     * 
     */
    public boolean declareWinnerAs(int p) {
        winningPlayer = playerList.get(--p);
        return true;
    }


    public static void main(String[] args) throws Exception {
         /**
         * This is the executable method.
         * 
         */
    }
}
