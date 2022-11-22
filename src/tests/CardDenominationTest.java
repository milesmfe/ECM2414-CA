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
        assertEquals(CardDenomination.ACE.getValue(), 1);
        assertEquals(CardDenomination.TWO.getValue(), 2);
        assertEquals(CardDenomination.THREE.getValue(), 3);
        assertEquals(CardDenomination.FOUR.getValue(), 4);
        assertEquals(CardDenomination.FIVE.getValue(), 5);
        assertEquals(CardDenomination.SIX.getValue(), 6);
        assertEquals(CardDenomination.SEVEN.getValue(), 7);
        assertEquals(CardDenomination.EIGHT.getValue(), 8);
        assertEquals(CardDenomination.NINE.getValue(), 9);
        assertEquals(CardDenomination.TEN.getValue(), 10);
        assertEquals(CardDenomination.JACK.getValue(), 11);
        assertEquals(CardDenomination.QUEEN.getValue(), 12);
        assertEquals(CardDenomination.KING.getValue(), 13);
    }


    @Test
    public void testValueOf() {
        assertEquals(CardDenomination.valueOf(1), CardDenomination.ACE);
        assertEquals(CardDenomination.valueOf(2), CardDenomination.TWO);
        assertEquals(CardDenomination.valueOf(3), CardDenomination.THREE);
        assertEquals(CardDenomination.valueOf(4), CardDenomination.FOUR);
        assertEquals(CardDenomination.valueOf(5), CardDenomination.FIVE);
        assertEquals(CardDenomination.valueOf(6), CardDenomination.SIX);
        assertEquals(CardDenomination.valueOf(7), CardDenomination.SEVEN);
        assertEquals(CardDenomination.valueOf(8), CardDenomination.EIGHT);
        assertEquals(CardDenomination.valueOf(9), CardDenomination.NINE);
        assertEquals(CardDenomination.valueOf(10), CardDenomination.TEN);
        assertEquals(CardDenomination.valueOf(11), CardDenomination.JACK);
        assertEquals(CardDenomination.valueOf(12), CardDenomination.QUEEN);
        assertEquals(CardDenomination.valueOf(13), CardDenomination.KING);
    }
    
}