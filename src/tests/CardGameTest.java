package tests;

import org.junit.Test;

import cards.Card;
import cards.CardDeck;
import cards.CardGame;
import static org.junit.Assert.*;

import java.util.ArrayList;

/**
 * CardGameTest class.
 * 
 * @author Miles Edwards
 * @version 1.0
 * 
 */
public class CardGameTest {


    @Test
    public void testSetupGame() {
        CardGame testGame = new CardGame(4);
        testGame.setupGame();

        assertEquals(new ArrayList<Card>(), testGame.getPack());
        assertNotEquals(0, testGame.getPlayer(1).getCardAt(0));
        assertNotEquals(0, testGame.getPlayer(1).getCardAt(1));
        assertNotEquals(0, testGame.getPlayer(1).getCardAt(2));
        assertNotEquals(0, testGame.getPlayer(1).getCardAt(3));
    }


    @Test
    public void testdeckLeftRightOf() {
        CardGame testGame = new CardGame(4);
        CardDeck left; CardDeck right;
        testGame.setupGame();

        left = testGame.deckLeftOf(1);
        right = testGame.deckRightOf(1);
        assertEquals(1, left.getDeckNumber());
        assertEquals(2, right.getDeckNumber());

        left = testGame.deckLeftOf(4);
        right = testGame.deckRightOf(4);
        assertEquals(4, left.getDeckNumber());
        assertEquals(1, right.getDeckNumber());
    }
}