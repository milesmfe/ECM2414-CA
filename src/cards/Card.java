package cards;

/**
 * Card class. Uses "denomination" to denote each instance's
 * denominaiton.
 * 
 * @author Miles Edwards
 * @version 1.0
 * 
 */
public class Card {
    private CardDenomination denomination;
    private int volatility = 0;

    // Constructor
    public Card() {
        /**
         * Assign self a random denomination by:
         * Picking a random number between 1 and 13.
         * Using CardDenomination method: "valueOf" to assign
         * "denomination" with a CardDenomination.
         * denomination = CardDenomination.valueOf(new Random().nextInt(13) + 1);
         * 
         * Will eventually become depreciated once file system
         * is implemented. Replaced by public Card(CardDenomination d) {}
         * 
         */
    }


    // Get methods
    public CardDenomination getDenomination() {
        return denomination;
    }

    public int getVolatility() {
        return volatility;
    }


    // Set methods
    public void setVolatility(int v) {
        volatility = v;
    }
}