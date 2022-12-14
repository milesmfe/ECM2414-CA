package cards;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;

/**
 * CardGame class.
 * 
 * @author Miles Edwards
 * @version 1.1
 * 
 */
public class CardGame {
    private int playerCount = 0;
    private int deckCount = 0;
    private ArrayList<Player> playerList;
    private ArrayList<CardDeck> deckList;
    private ArrayList<Card> pack;
    private Player winningPlayer;
    private GameStatus status = GameStatus.NOT_SETUP_NO_PACK;


    /**
     * PackIncorrectLengthException class.
     * 
     * Defines an exception for file input system.
     * 
     * Throw when a pack has an incorrect length.
     * 
     * @author Miles Edwards
     * @version 1.0
     * 
     */
    private class PackIncorrectLengthException extends Exception {
        public PackIncorrectLengthException() {
            super();
        }
    }


    /**
     * InvalidDenominationException class.
     * 
     * Defines an exception for file input system.
     * 
     * Throw when a pack contains denominations out of range (1 to 13 inclusive).
     * 
     * @author Miles Edwards
     * @version 1.0
     * 
     */
    private class InvalidDenominationException extends Exception {
        public InvalidDenominationException() {
            super();
        }
    }


    /**
     * EmptyPathException class.
     * 
     * Defines an exception for file input system.
     * 
     * Throw when a pack name is entered as an empty string.
     * 
     * @author Miles Edwards
     * @version 1.0
     * 
     */
    private class EmptyPathException extends Exception {
        public EmptyPathException() {
            super();
        }
    }

    
    /**
     * CardGame constructor.
     * 
     * Initialises ArrayLists and proceeds to populate them with players, decks and cards.
     * 
     * Use this constructor to play a game with a specific pack (pack file name needed).
     * 
     * @param n the number of players.
     * @param pn the filename of the pack. Use pn="" to set pack later.
     * @author Miles Edwards
     * @version 1.1
     * 
    */
    public CardGame(int n, String pn) {
        initialise(n);
        // -- Try to get specified pack -- //
        if (pn != "") { setPackFrom(pn); }
    }


    /**
     * CardGame constructor.
     * 
     * Initialises ArrayLists and proceeds to populate them with players, decks and cards. 
     * 
     * Use this constructor to play a game with a random pack (no pack file name needed).
     * 
     * @param n The number of players
     * @author Miles Edwards
     * @version 1.0
     * 
    */
    public CardGame(int n) {
        initialise(n);
        // -- Create a pack of random cards -- //
        for (int i = 0; i < 8*n; i++) {
            pack.add(new Card());
        }
        status = GameStatus.NOT_SETUP_PACK_READY;
    }


    /**
     * initialise void. Runs compulsory initialisaiton.
     * 
     * Only called by constructor.
     * 
     * @author Miles Edwards
     * @version 1.0
     * @param n the number of players.
     * 
     */
    private void initialise(int n) {
        playerList = new ArrayList<Player>();
        deckList = new ArrayList<CardDeck>();
        pack = new ArrayList<Card>();

        for (int i = 0; i < n; i++) {
            playerList.add(new Player(this, ++playerCount));
            deckList.add(new CardDeck(this, ++deckCount));
        }
    }


    /**
     * generatePackFile method.
     * 
     * Generates a pack file from this game's pack.
     * 
     * Note: will only generate a pack file if the game has not been setup and a pack is
     * ready (most likely a randomly generated pack).
     * 
     * If called after setup, pack is already empty so file cannot be generated.
     * 
     * If called before pack is ready then there is no pack to generate a file from.
     * 
     * @author Miles Edwards
     * @version 1.0
     * 
     * @param n file name.
     * 
     */
    public void generatePackFile(String n) {
        if (status == GameStatus.NOT_SETUP_PACK_READY) {
            try {
                FileWriter fw = new FileWriter(String.format("packs/%s", n));
                for (Card card : pack) {
                    fw.write(String.format("%d", card.getDenomination().getValue()));
                    if (pack.indexOf(card) != pack.size() - 1) { fw.write(System.lineSeparator()); }
                }
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * setPackFrom method.
     * 
     * Generates a pack of cards from a given file.
     * 
     * @author Shuhui Chen
     * @author Miles Edwards
     * @version 1.2
     * @param n file name.
     * @return flag indicating whether or not pack was set successfully.
     * 
     */
    public boolean setPackFrom(String n) {
        if (status == GameStatus.NOT_SETUP_NO_PACK) {
            try {
                if (n.length() == 0) { throw new EmptyPathException(); }
                // -- Copy each line from the pack file into a list of strings -- //
                String[] packList = Files.readString(Path.of("packs/" + n)).split(System.lineSeparator());
                // -- Check that pack is correct length -- //
                if (packList.length != 8*playerCount) { throw new PackIncorrectLengthException(); }
                // -- Create new card objects with corresponding denominations & populate pack -- //
                for (int i = 0; i < 8*playerCount; i++) {
                    // -- Check that pack does not contain denominations out of range -- //
                    if(Integer.valueOf(packList[i]) <= 13 && Integer.valueOf(packList[i]) > 0) {
                        pack.add(new Card(Integer.valueOf(packList[i])));
                    // -- Throw error if any invalid denominations are found -- //
                    } else { throw new InvalidDenominationException(); }     
                }
                // -- No Errors -- //
                status = GameStatus.NOT_SETUP_PACK_READY;
                return true;
            
            // -- Error handling -- //
            } catch (Exception e) {
                // -- File doesn't exist -- //
                if (e instanceof NoSuchFileException) {
                    System.out.println("""
                        \n
                        ERR: Pack does not exist, remember:\n
                         1. Pack must be a text (.txt) file
                         2. Pack should be located at packs/pack-name.txt
                        \n"""
                    );
                }
                // -- File is incorrectly formatted -- //
                else if (e instanceof NumberFormatException) {
                    System.out.println("""
                        \n
                        ERR: Could not read pack: invalid contents. Remember:\n
                         1. Pack must be a text (.txt) file
                         2. Pack should only contain positive integers between 1 and 13 (inclusive)
                         3. Each integer should be on a separate line
                        \n"""
                    );
                }
                // -- Pack contains an incorrect number of cards -- //
                else if (e instanceof PackIncorrectLengthException) {
                    System.out.println("""
                        \n
                        ERR: Pack is not the correct length. Remember:\n
                         1. Pack must contain (number of players) * 8 cards.
                         2. Each integer should be on a separate line
                        \n"""
                    );
                }
                // -- Pack contains integers out of range -- //
                else if (e instanceof InvalidDenominationException) {
                    System.out.println("""
                        \n
                        ERR: Pack has integers out of range. Remember:\n
                         1. Pack should only contain positive integers between 1 and 13 (inclusive)
                         2. Each integer should be on a separate line
                        \n"""
                    );
                }
                // -- Pack name is an empty string -- //
                else if (e instanceof EmptyPathException) {
                    System.out.println("""
                        \n
                        ERR: Pack name was an empty string. Remember:\n
                         1. Enter the name of the pack you would like to use for this game
                         2. Enter the file extention (.txt) after the name without a space
                        \n"""
                    );
                }
                // -- Default response to error -- //
                else { System.out.println("ERR: Unknown error."); }     
            }
        }
        // -- Notify caller that error was encountered -- //
        return false;
    }


    /**
     * getPlayerCount method.
     * 
     * @author Miles Edwards
     * @version 1.0
     * @return the number of players in this game.
     * 
     */
    public int getPlayerCount() {
        return playerCount;
    }


     /**
     * getDeckCount method.
     * 
     * @author Miles Edwards
     * @version 1.0
     * @return the number of decks in this game.
     * 
     */
    public int getDeckCount() {
        return deckCount;
    }


    /**
     * getStatus method.
     * 
     * @author Miles Edwards
     * @version 1.0
     * @return the current status of this game.
     * 
     */
    public synchronized GameStatus getStatus() {
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
     * Use for testting.
     * 
     * @author Miles Edwards
     * @version 1.0
     * @return the pack for the game. Will 
     * 
     */
    public ArrayList<Card> getPack() {
        return pack;
    }


    /**
     * getWinningPlayer.
     * 
     * @author Miles Edwards
     * @version 1.0
     * @return the Player object of the winner.
     * 
     */
    public Player getWinningPlayer() {
        return winningPlayer;
    }


    /**
     * setupGame method.
     * 
     * Calls dealHands and populateDecks.
     * 
     * Any issue is caught as a generic Exception.
     * 
     * @author Miles Edwards
     * @version 1.0
     * @return flag indicating whether or not the setup was successful.
     * 
     */
    public boolean setupGame() {
        if (status == GameStatus.NOT_SETUP_PACK_READY) {
            try {
                dealHands();
                populateDecks();
                for (int i = 0; i < playerCount; i++) {
                    playerList.get(i).locateDecks();
                }
                status = GameStatus.SETUP_IDLE; return true;
            } catch (Exception e) {
                // -- There was an error in the code -- //
                return false;
            }
        }
        // -- Game was already setup or pack was not ready -- //
        return false;
    }


    /**
     * dealHands method.
     * 
     * Iterates 4 times representing the size of each player's hand.
     * 
     * Each iteration iterates through playerList, setting a card for the current index
     * in each player's hand.
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
     * populateDecks method.
     * 
     * Iterates 4 times representing the size of each deck.
     * 
     * Each iteration iterates through deckList, setting a card for the current index in each pack.
     * 
     * @author Miles Edwards
     * @version 1.0
     * 
     */
    private void populateDecks() {
        for (int i = 0; i < 4; i++) {  
            for (CardDeck deck : deckList) {
                final Card card = pack.get(pack.size() - 1);
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
        if (p == playerCount) {
            return deckList.get(0);
        } else {
            return deckList.get(p);
        }
    }


    /**
     * declareWinnerAs method.
     * 
     * Player threads should use this to declare that they have a winning hand.
     * 
     * @author Miles Edwards
     * @version 1.0
     * @param p a specific player in the game.
     * @return true if player p wins, otherwise false.
     * 
     */
    public boolean declareWinnerAs(Player p) {
        if (status != GameStatus.GAME_WON) {
            winningPlayer = p;
            status = GameStatus.GAME_WON;
            return true;
        }
        return false;
    }


     /**
     * startGame method.
     * 
     * Checks that game has been setup and is not already running.
     * 
     * Sets game status to SETUP_ACTIVE.
     * 
     * Shuffles playerList so that player threads start in a random order.
     * 
     * Starts each player thread.
     * 
     * @author Miles Edwards
     * @version 1.0
     * 
     */
    public void startGame() {
        if (getStatus() == GameStatus.SETUP_IDLE) {
            status = GameStatus.SETUP_ACTIVE;
            Collections.shuffle(playerList);
            for (Player player : playerList) {
                player.setName(String.format("Player-%d-Thread", player.getPlayerNumber()));
                player.start();
            }
        }
    }


    /**
     * quickStart method. Sets up and starts the game automatically.
     * 
     * @author Miles Edwards
     * @version 1.0
     * 
     */
    public void quickStart() {
        if (setupGame()) { startGame(); }
    }


    public static void main(String[] args) {
        try {
            int players = Integer.valueOf(
            System.console().readLine("Please enter the number of players: ")
            );
            while (players < 2) {
                players = Integer.valueOf(
                    System.console().readLine(
                        "Sorry, there must be at least 2 players.\nPlease enter the number of players: "
                    )
                );
            }
            CardGame game = new CardGame(players, "");
            String packName = System.console().readLine("Please enter a pack name: ");
            while (!game.setPackFrom(packName)) {
                packName = System.console().readLine("Sorry, please try again: ");
            }
            game.quickStart();
        } catch (Exception e) {
            // -- Error caught -- //
        }
    }
}
