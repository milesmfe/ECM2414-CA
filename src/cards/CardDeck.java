package cards;

import java.util.ArrayList;

/**
 * CardDeck class. Each CardDeck has an array of cards.
 * 
 * @author Miles Edwards
 * @version 1.0
 * 
 */
public class CardDeck {
    private static int deckCount = 0;
    private int deckNumber;
    private ArrayList<Card> cards;
    

    //Constructor
    public CardDeck() {
        deckCount++;
        deckNumber = deckCount;
        cards = new ArrayList<Card>();
    }


    //Get methods
    public static int getDeckCount() {
        return deckCount;
    }

    public int getDeckNumber() {
        return deckNumber;
    }


    /**
     * addCard method. Adds a card to the top of the deck.
     * 
     * @author Miles Edwards
     * @version 1.0
     * @param c card to be added to the deck.
     * 
     */
    public void addCard(Card c) {
        cards.add(c);
    }

    
    public Card popFromTop() {
        /**
         * Pops a card from the top of "cards"
         * Thus removing the top (i=n-1) card from "cards"
         * Returns said popped card.
         * cards.get(cards.size()-1) will return the card
         * at the top of deck.
         */
        return new Card();
    }
    

    public void appendToBottom(Card card) {
        /**
         * Appends "card" to the bottom (i=0) of "card"
         * 
         */
    }
}
