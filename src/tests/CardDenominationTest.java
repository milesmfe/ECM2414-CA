package tests;

import org.junit.Test;
import cards.CardDenomination;
import static org.junit.Assert.*;

/**
 * CardDenominationTest class.
 * 
 * @author Miles Edwards
 * @version 1.0
 * 
 */
public class CardDenominationTest {

    @Test
    public void testGetValue() {
        assertEquals(1, CardDenomination.ACE.getValue());
        assertEquals(2, CardDenomination.TWO.getValue());
        assertEquals(3, CardDenomination.THREE.getValue());
        assertEquals(4, CardDenomination.FOUR.getValue());
        assertEquals(5, CardDenomination.FIVE.getValue());
        assertEquals(6, CardDenomination.SIX.getValue());
        assertEquals(7, CardDenomination.SEVEN.getValue());
        assertEquals(8, CardDenomination.EIGHT.getValue());
        assertEquals(9, CardDenomination.NINE.getValue());
        assertEquals(10, CardDenomination.TEN.getValue());
        assertEquals(11, CardDenomination.JACK.getValue());
        assertEquals(12, CardDenomination.QUEEN.getValue());
        assertEquals(13, CardDenomination.KING.getValue());
    }


    @Test
    public void testValueOf() {
        assertEquals(CardDenomination.ACE, CardDenomination.valueOf(1));
        assertEquals(CardDenomination.TWO, CardDenomination.valueOf(2));
        assertEquals(CardDenomination.THREE, CardDenomination.valueOf(3));
        assertEquals(CardDenomination.FOUR, CardDenomination.valueOf(4));
        assertEquals(CardDenomination.FIVE, CardDenomination.valueOf(5));
        assertEquals(CardDenomination.SIX, CardDenomination.valueOf(6));
        assertEquals(CardDenomination.SEVEN, CardDenomination.valueOf(7));
        assertEquals(CardDenomination.EIGHT, CardDenomination.valueOf(8));
        assertEquals(CardDenomination.NINE, CardDenomination.valueOf(9));
        assertEquals(CardDenomination.TEN, CardDenomination.valueOf(10));
        assertEquals(CardDenomination.JACK, CardDenomination.valueOf(11));
        assertEquals(CardDenomination.QUEEN, CardDenomination.valueOf(12));
        assertEquals(CardDenomination.KING, CardDenomination.valueOf(13));
    }
}