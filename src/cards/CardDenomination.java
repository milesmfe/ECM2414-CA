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

    private CardDenomination(int v) {
        value = v;
    }

    static {
        for (CardDenomination cardDenomination : CardDenomination.values()) {
            map.put(cardDenomination.value, cardDenomination);
        }
    }

    public static CardDenomination valueOf(int v) {
        return (CardDenomination) map.get(v);
    }

    public int getValue() {
        return value;
    }
}