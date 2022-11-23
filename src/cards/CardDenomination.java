package cards;

import java.util.HashMap;

/**
 * CardDenominations enum. Defines 13 card denominations.
 * Use in conjuncture with the Card class.
 * 
 * @author Miles Edwards
 * @version 1.0
 * 
 */
public enum CardDenomination {
    ACE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(11),
    QUEEN(12),
    KING(13);

    private int value;
    private static HashMap<Integer, CardDenomination> map = new HashMap<Integer, CardDenomination>();

    
    /**
     * CardDenomination constructor. Private constructor that
     * sets up enum automatically with name:value pairs.
     * 
     * @author Miles Edwards
     * @version 1.0
     * @param v the denomination value.
     * 
     */
    private CardDenomination(int v) {
        value = v;
    }


    /**
     * A static block that, during the enum setup, maps each name
     * to its corresponding value.
     * 
     * @author Miles Edwards
     * @version 1.0
     * 
     */
    static {
        for (CardDenomination cardDenomination : CardDenomination.values()) {
            map.put(cardDenomination.value, cardDenomination);
        }
    }


    /**
     * valueOf method. Returns the correponding name of a specified
     * card denomination value.
     * 
     * @author Miles Edwards
     * @version 1.0
     * @param v specify an integer value, between 1 and 13 (inclusive), of
     * any card denomination.
     * @return the corresponding card denomination name.
     * 
     */
    public static CardDenomination valueOf(int v) {
        return (CardDenomination) map.get(v);
    }
    

    /**
     * getValue method.
     * 
     * @author Miles Edwards
     * @version 1.0
     * @return the integer version of this card denomination name.
     * 
     */
    public int getValue() {
        return value;
    }
}