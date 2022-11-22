package tests;

import org.junit.Test;
import cards.CardGame;
import static org.junit.Assert.*;

/**
 * CardGameTest class.
 * 
 * @author Miles Edwards
 * @version 1.0
 * 
 */
public class CardGameTest {
    CardGame gameTest = new CardGame(4);


    @Test
    public void testConstructor() {
        assertEquals(4, gameTest.getPlayers().size());
        assertEquals(4, gameTest.getDecks().size());
        assertEquals(32, gameTest.getPack().size());
    }


    @Test
    public void testDealHands() {
        gameTest.dealHands();
        assertEquals(16, gameTest.getPack().size());
    }


    @Test
    public void testPopulateDecks() {
        gameTest.populateDecks();
        assertEquals(16, gameTest.getPack().size());
    }


    @Test
    public void testNextTurn() {
        gameTest.nextTurn();
        assertEquals(1, gameTest.getPlayerTurn());
        gameTest.nextTurn();
        assertEquals(2, gameTest.getPlayerTurn());
        gameTest.nextTurn();
        assertEquals(3, gameTest.getPlayerTurn());
        gameTest.nextTurn();
        assertEquals(0, gameTest.getPlayerTurn());
    }
}