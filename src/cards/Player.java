package cards;

/**
 * Player class. Each player instance has a prefferred
 * denomination and a hand of 4 cards.
 * 
 * @author Miles Edwards
 * @version 1.0
 * 
 */
public class Player {
    private static int playerCount = 0;
    private int playerNumber;
    private CardDenomination preferredDenomination;
    private Card[] hand = new Card[4];
    private Card discard;


    //Constructor
    public Player() {
        playerCount++;
        playerNumber = playerCount;
    }


    //Get methods
    public static int getPlayerCount() {
        return playerCount;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public CardDenomination getPreferredDenomination() {
        return preferredDenomination;
    }

    public Card getCardAt(int index) {
        return hand[index];
    }


    //Set methods
    public void setPreferredDenomination(CardDenomination d) {
        preferredDenomination = d;
    }

    public void setCardAt(int index, Card card) {
        hand[index] = card;
    }


    public boolean checkHand() {
        /**
         * Iterate through each card in the hand.
         * Check every card has an identical denomination.
         * Yes: return true;
         * No: return false;
         * 
         */
        return false;
    }

    public void pickCardFrom(CardDeck deck) {
        /**
         * Get a card from the top of "deck".
         * Use "findMostVolatileCard()" to find the card to 
         * copy from "hand" to "discard".
         * Add picked up card to "hand" at old index of "discard".
         * 
         */
    }

    public void discardCardTo(CardDeck deck) {
        /**
         * Add "discard" to "deck"
         * Reset "discard"
         * 
         */
    }

    public Card findMostVolatileCard() {
        /**
         * Iterate through "hand"
         * Determine which card is most volatile by checking
         * each card's "volatility" attr.
         * return the card with the highest volatility value.
         * 
         */
        return discard;
    }

    public void updateHandVolatility() {
        /**
         * Add 1 to the "volatility" value of each card in "hand".
         * Set "volatility" to 0 for any card of the prefferred 
         * denomination.
         * 
         */
    }
}
