package cards;

import java.io.FileWriter;
import java.io.IOException;
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
    private Player winningPlayer;
    private GameStatus status;

    
    /**
     * CardGame constructor. Initialises ArrayLists
     * and proceeds to populate them with players,
     * decks and cards.
     * 
     * Use this constructor to play a game with a specific
     * pack (pack file needed).
     * 
     * @param n the number of players.
     * @param pn the filename of the pack.
     * @author Miles Edwards
     * @version 1.1
     * 
    */
    public CardGame(int n, String pn) {
        numPlayers = n;
        playerList = new ArrayList<Player>();
        deckList = new ArrayList<CardDeck>();
        pack = new ArrayList<Card>();

        for (int i = 0; i < n; i++) {
            playerList.add(new Player(this));
            deckList.add(new CardDeck());
        }
        validatePack(pn);
        pack = getPackFrom(pn);
    }


    /**
     * CardGame constructor. Initialises ArrayLists
     * and proceeds to populate them with players,
     * decks and cards. 
     * 
     * Use this constructor to play a game with a random
     * pack (no pack file needed).
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


    public void generatePackFile(String n) {
        try {
            FileWriter fw = new FileWriter(String.format("src/packs/%s.txt", n));
            for (Card card : pack) {
                System.out.println(card.getDenomination().getValue());
                fw.write(String.format("%d\n", card.getDenomination().getValue()));
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * getPackFrom generates a pack of cards from a given file.
     * 
     * @author Miles Edwards
     * @version 1.0
     * 
     * @param n file name.
     * 
     */
    private ArrayList<Card> getPackFrom(String n) {
        /**
         * Call validate pack.
         * Read from file named "n"
         * File packFile = new File(n);
         * Read one line at a time.
         * Scanner fileScanner = new Scanner(packFile);
         * Create a Card object with the integer per line.
         * while (fileScanner.hasNextLine()) { anArray.add(new Card(fileScanner.nextLine())) }
         * Return a generated ArrayList of these card objects.
         */
        return new ArrayList<Card>();
    }


    /**
     * validatePack performs validation checks on a given
     * pack file.
     * 
     * @author Miles Edwards
     * @version 1.0
     * 
     * @param n file name.
     * 
     */
    private boolean validatePack(String n) {    
        /**
         * File packFile = new File(n);
         * Scanner fileScanner = new Scanner(packFile);
         * Iterate through each line
         * while (fileScanner.hasNextLine()) {
         *          String nextLine = fileScanner.nextLine()
         *      if (check nextLine is an integer and is in range)
         *      else { return false }
         * }
         * Ensure each line only contains an integer between 1 and 13 (inclusive)
         * Check that there are 8n lines.
         */
        return true;
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
     * @return the current status of this game.
     * 
     */
    public GameStatus getStatus() {
        return status;
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
            for (int i = 0; i < numPlayers; i++) {
                playerList.get(i).locateDecks();
            }
            status = GameStatus.SETUP_IDLE;
            return true;
        } catch (Exception e) {
            // -- Log exception here -- //
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
        status = GameStatus.GAME_WON;
        return true;
    }

    public void startGame() {
        if (getStatus() == GameStatus.SETUP_IDLE) {
            status = GameStatus.SETUP_ACTIVE;
            for (Player player : playerList) {
                player.start();
            }
        }
    }


    public void quickStart() {
        if (setupGame()) { startGame(); }
    }


    public static void main(String[] args) throws Exception {
          /**
         * This is the executable method.
         * 
         */
    }
}
