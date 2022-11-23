package tests;

import org.junit.Test;

import cards.Card;
import cards.CardDenomination;
import cards.Player;

import static org.junit.Assert.*;

/**
 * PlayerTest class.
 * 
 * @author Miles Edwards
 * @version 1.0
 * 
 */
public class PlayerTest {

    @Test
    public void testSetCardAt() {
        Player testPlayer = new Player(null);
        Card testCardACE = new Card(1);
        Card testCardTWO = new Card(2);
        Card testCardTHREE = new Card(3);
        Card testCardFOUR = new Card(4);
        testPlayer.setCardAt(0, testCardACE);
        testPlayer.setCardAt(1, testCardTWO);
        testPlayer.setCardAt(2, testCardTHREE);
        testPlayer.setCardAt(3, testCardFOUR);

        assertEquals(CardDenomination.ACE, testPlayer.getCardAt(0).getDenomination());
        assertEquals(CardDenomination.TWO, testPlayer.getCardAt(1).getDenomination());
        assertEquals(CardDenomination.THREE, testPlayer.getCardAt(2).getDenomination());
        assertEquals(CardDenomination.FOUR, testPlayer.getCardAt(3).getDenomination());
    }
    
}