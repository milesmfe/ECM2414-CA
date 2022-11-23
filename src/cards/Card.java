package cards;

import java.util.Random;

/**
 * Card class. Uses "denomination" to denote each instance's
 * denominaiton. Each card has an integer volatility value
 * which should be used to identify how long the current player 
 * has held it for.
 * 
 * @author Miles Edwards
 * @version 1.0
 * 
 */
public class Card {
    private CardDenomination denomination;
    private int volatility = 0;

    /**
     * Card constructor. Assigns instance with random denomination.
     * 
     * @deprecated
     * @author Shuhui Chen
     * @version 1.0
     * 
     */
    @Deprecated
    public Card() {
        Random rand = new Random();
        int randomNum = rand.nextInt(13) + 1;
        denomination = CardDenomination.valueOf(randomNum);
    }


    /**
     * Card constructor. Assigns instance with specified denomination.
     * 
     * @author Miles Edwards
     * @version 1.0
     * @param d integer denomination value
     * 
     */
    public Card(int d) {
        denomination = CardDenomination.valueOf(d);
    }


    /**
     * getDenomination method.
     * 
     * @author Miles Edwards
     * @version 1.0
     * @return this card's denomination.
     * 
     */
    public CardDenomination getDenomination() {
        return denomination;
    }

    /**
     * getVolatility method.
     * 
     * @author Miles Edwards
     * @version 1.0
     * @return this card's volatility.
     * 
     */
    public int getVolatility() {
        return volatility;
    }


    /**
     * setVolatility method. Updates this card's volatility.
     * 
     * @author Miles Edwards
     * @version 1.0
     * @param v specify an integer volatility value.
     * 
     */
    public void setVolatility(int v) {
        volatility = v;
    }

    /**
     * incrementVolatility method. Adds 1 to this card's volatility.
     * 
     * @author Miles Edwards
     * @version 1.0
     * 
     */
    public void incrementVolatility() {
        volatility++;
    }
}