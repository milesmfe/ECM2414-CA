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

    @Test
    public void testConstructor() {
        CardGame gameTest = new CardGame(4);
        assertEquals(4, gameTest.getPlayers().size());
        assertEquals(4, gameTest.getDecks().size());
        assertEquals(32, gameTest.getPack().size());
    }
    
}