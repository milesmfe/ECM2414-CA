package tests;

import org.junit.Test;

import cards.Card;
import cards.CardDeck;
import cards.CardGame;

import static org.junit.Assert.*;

/**
 * CardDeckTest class.
 * 
 * @author Miles Edwards
 * @version 1.0
 * 
 */
public class CardDeckTest {

    @Test
    public void testAddCard() {
        CardDeck testDeck = new CardDeck(new CardGame(4), 1);
        testDeck.addCard(new Card(1));
        testDeck.addCard(new Card(2));
        testDeck.addCard(new Card(3));
        testDeck.addCard(new Card(4));

        assertEquals(4, testDeck.getDeckSize());
    }
    
}
