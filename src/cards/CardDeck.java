package cards;

import java.util.ArrayList;

/**
 * CardDeck class. Each CardDeck has an array of cards and
 * a deck number determined by a static counter.
 * 
 * @author Miles Edwards
 * @author Shuhui Chen
 * @version 1.0
 * 
 */
public class CardDeck {
    private static int deckCount = 0;
    private int deckNumber;
    private ArrayList<Card> cards;
    

    /**
     * CardDeck constructor. Increments static deck count.
     * Assignes instance with a unique number.
     * Initiates cards list.
     * 
     * @author Miles Edwards
     * @version 1.0
     * 
     */
    public CardDeck() {
        deckCount++;
        deckNumber = deckCount;
        cards = new ArrayList<Card>();
    }


    /**
     * getDeckCount method.
     * 
     * @author Miles Edwards
     * @version 1.0
     * @return the global number of decks.
     * 
     */
    public static int getDeckCount() {
        return deckCount;
    }


    /**
     * getDeckNumber method.
     * 
     * @author Miles Edwards
     * @version 1.0
     * @return this instance's unique deck number.
     * 
     */
    public int getDeckNumber() {
        return deckNumber;
    }


    /**
     * addCard method. Adds a card to the top of the deck.
     * 
     * @author Shuhui Chen
     * @version 1.0
     * @param c card to be added to the deck.
     * 
     */
    public void addCard(Card c) {
        cards.add(c);
    }


    /**
     * popFromTop method. Popps the top card from the deck.
     * Also sets the popped card's volatility to 0.
     * 
     * @author Shuhui Chen
     * @author Miles Edwards
     * @version 1.0
     * @return a card popped from the top of the deck.
     * 
     */
    public Card popFromTop() {
        Card poppedCard = cards.get(cards.size() - 1);
        cards.remove(cards.size() - 1);
        poppedCard.setVolatility(0);
        return poppedCard;
    }
    

     /**
     * appendToBottom method. Appends a card to the bottom of the deck.
     * 
     * @author Shuhui Chen
     * @version 1.0
     * @param card the card to append to the bottom of the deck.
     * 
     */
    public void appendToBottom(Card card) {
        cards.add(0, card);
    }
}
