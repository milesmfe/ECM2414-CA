package cards;


/**
 * Player class. Each player instance has a prefferred
 * denomination and a hand of 4 cards.
 * 
 * @author Miles Edwards
 * @version 1.1
 * 
 */
public class Player extends Thread {
    private static int playerCount = 0;
    private int playerNumber;
    private CardDenomination preferredDenomination;
    private Card[] hand = new Card[4];
    private Card discard;


    /**
     * Player constructor. Increments static player counter.
     * Assigns self a unique player number.
     * Sets preferred Denomination to player number.
     * 
     * @author Miles Edwards
     * @version 1.0
     * 
     */
    public Player() {
        playerCount++;
        playerNumber = playerCount;
        preferredDenomination = CardDenomination.valueOf(playerNumber);
    }


    /**
     * getPlayerCount method.
     * 
     * @author Miles Edwards
     * @version 1.0
     * @return the global number of players.
     * 
     */
    public static int getPlayerCount() {
        return playerCount;
    }


    /**
     * getPlayerNumber method.
     * 
     * @author Miles Edwards
     * @version 1.0
     * @return this instance's unique player number.
     * 
     */
    public int getPlayerNumber() {
        return playerNumber;
    }


    /**
     * getPreferredDenomination method.
     * 
     * @author Miles Edwards
     * @version 1.0
     * @return this instance's preferred denomination.
     * 
     */
    public CardDenomination getPreferredDenomination() {
        return preferredDenomination;
    }

    
    /**
     * getCardAt method. Note: this method does not modify
     * the player's hand, only reads it.
     * 
     * @author Miles Edwards
     * @version 1.0
     * @param index specify an index in the hand.
     * @return the card at the specified index.
     * 
     */
    public Card getCardAt(int index) {
        return hand[index];
    }


    /**
     * setCardAt method. Use this method to modify the player's
     * hand.
     * 
     * @author Miles Edwards
     * @version 1.0
     * @param index specify an index in the hand
     * @param card the card that will be inserted at the 
     * specified index.
     * 
     */
    public void setCardAt(int index, Card card) {
        hand[index] = card;
    }


    /**
     * checkHand method. Checks if hand contains four cards of the
     * same value.
     * 
     * @author Miles Edwards
     * @version 1.0
     * @return flag indicating whether or not this player has won.
     * 
     */
    public boolean checkHand() {
        CardDenomination denomination = hand[0].getDenomination();
        for (int i = 1; i < 4; i++) {
            if (hand[i].getDenomination() != denomination) {
                return false;
            }
        }
        return true;
    }


    /**
     * pickCardFrom method. Pick up a card from a specified deck.
     * Calls findMostVolatileCard to choose the card to replace.
     * Checks if picked up card is preferred and sets its volatility
     * to -1.
     * 
     * @author Miles Edwards
     * @version 1.0
     * @param deck specify the deck to pick a card from.
     * @throws Exception
     * 
     */
    public void pickCardFrom(CardDeck deck) {
        findMostVolatileCard();
        int index = 0;
        for (int i = 0; i < 4; i++) { if (hand[i] == discard) { index = i; } }
        Card card = deck.popFromTop();
        if (card.getDenomination() == preferredDenomination) { card.setVolatility(-1); }
        hand[index] = card;
    }

    
    /**
     * discardCardTo method. Discards a card to a specified deck.
     * Resets this player's "discard" card.
     * 
     * @author Miles Edwards
     * @version 1.0
     * @param deck specify the deck to discard a card to.
     * 
     */
    public void discardCardTo(CardDeck deck) {
        deck.appendToBottom(discard);
        discard = null;
    }


    /**
     * findMostVolatileCard method. Locates the card with the highest
     * volatility value in this player's hand. Sets this player's
     * "discard" card to that card.
     * 
     * @author Miles Edwards
     * @version 1.0
     * 
     */
    public void findMostVolatileCard() {
        int index = 0;
        for (int i = 1; i < 4; i++) {
            if (hand[i].getVolatility() > hand[index].getVolatility()) {
                index = i;
            }
        }
        discard = hand[index];
    }


    /**
     * updateHandVolatility method. Updates any non preferred card's
     * volatility in this player's hand.
     * 
     * @author Miles Edwards
     * @version 1.0
     * 
     */
    public void updateHandVolatility() {
        for (int i = 0; i < 4; i++) {
            if (hand[i].getDenomination() != preferredDenomination) {
                hand[i].incrementVolatility();
            }
        }
    }


    /**
     * run method. This method is called when this player's thread
     * begins. Plays the game continuously until this player wins.
     * Could be interrupted by other player threads before winning.
     * 
     * @author Miles Edwards
     * @version 1.0 
     * 
     */
    @Override
    public void run() {
        /**
         * Play the game according to spec.
         * Should output moves, etc.
         * Notify main thread when game is won.
         */
    }
}
