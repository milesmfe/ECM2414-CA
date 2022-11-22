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
    }


    //Get methods
    public static int getDeckCount() {
        return deckCount;
    }

    public int getDeckNumber() {
        return deckNumber;
    }


    public ArrayList<Card> populateDeck(ArrayList<Card> pack) {
        /**
         * Pops 4 cards from top (i=n-1) of "pack".
         * Creates list of cards from these 4.
         * Sets "cards" to this list.
         * Returns the pack now with 4 fewer cards.
         * 
         */
        return pack;
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
