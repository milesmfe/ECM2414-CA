package cards;


/**
 * Player class. Each player instance has a prefferred
 * denomination and a hand of 4 cards.
 * 
 * @author Miles Edwards
 * @version 1.1
 * 
 */
public class Player extends Thread {
    private int playerNumber;
    private CardGame game;
    private CardDenomination preferredDenomination;
    private Card[] hand = new Card[4];
    private Card discard;
    private CardDeck leftDeck;
    private CardDeck rightDeck;
    private int turns = 0;


    /**
     * Player constructor. Increments static player counter.
     * Assigns self a unique player number.
     * Sets preferred Denomination to player number.
     * 
     * @author Miles Edwards
     * @version 1.0
     * @param g indicates the game this player is in.
     * @param n indicates the player number.
     * 
     */
    public Player(CardGame g, int n) {
        playerNumber = n;
        preferredDenomination = CardDenomination.valueOf(playerNumber);
        game = g;
    }


    /**
     * getPlayerNumber method.
     * 
     * @author Miles Edwards
     * @version 1.0
     * @return this instance's unique player number.
     * 
     */
    public int getPlayerNumber() {
        return playerNumber;
    }


    /**
     * getPreferredDenomination method.
     * 
     * @author Miles Edwards
     * @version 1.0
     * @return this instance's preferred denomination.
     * 
     */
    public CardDenomination getPreferredDenomination() {
        return preferredDenomination;
    }

    
    /**
     * getCardAt method. Note: this method does not modify
     * the player's hand, only reads it.
     * 
     * @author Miles Edwards
     * @version 1.0
     * @param index specify an index in the hand.
     * @return the card at the specified index.
     * 
     */
    public Card getCardAt(int index) {
        return hand[index];
    }


    public CardDeck getLeftDeck() {
        return leftDeck;
    }


    public CardDeck getRightDeck() {
        return rightDeck;
    }


    /**
     * setCardAt method. Use this method to modify the player's
     * hand.
     * 
     * @author Miles Edwards
     * @version 1.0
     * @param index specify an index in the hand
     * @param card the card that will be inserted at the 
     * specified index.
     * 
     */
    public void setCardAt(int index, Card card) {
        hand[index] = card;
    }


    /**
     * locateDecks method. Finds the positions of decks either side of 
     * this player.
     * 
     * @author Miles Edwards
     * @version 1.0
     * 
     */
    public void locateDecks() {
        rightDeck = game.deckRightOf(playerNumber);
        leftDeck = game.deckLeftOf(playerNumber);
    }


    /**
     * checkHand method. Checks if hand contains four cards of the
     * same value.
     * 
     * @author Miles Edwards
     * @version 1.0
     * @return flag indicating whether or not this player has won.
     * 
     */
    public boolean checkHand() {
        CardDenomination denomination = hand[0].getDenomination();
        for (int i = 1; i < 4; i++) {
            if (hand[i].getDenomination() != denomination) {
                return false;
            }
        }
        return true;
    }


    /**
     * pickCardFrom method. Pick up a card from a specified deck.
     * Calls findMostVolatileCard to choose the card to replace.
     * Checks if picked up card is preferred and sets its volatility
     * to -1.
     * 
     * @author Miles Edwards
     * @version 1.0
     * @param deck specify the deck to pick a card from.
     * @throws Exception
     * 
     */
    private void pickCardFrom(CardDeck deck) {
        findMostVolatileCard();
        int index = 0;
        for (int i = 0; i < 4; i++) { if (hand[i] == discard) { index = i; } }
        Card card = deck.popFromTop();
        if (card.getDenomination() == preferredDenomination) { card.setVolatility(-1); }
        hand[index] = card;
        // -- Print card draw action -- //
        System.out.println(String.format("player %d draws a %d from deck %d",
            getPlayerNumber(), card.getDenomination().getValue(), deck.getDeckNumber()));
    }

    
    /**
     * discardCardTo method. Discards a card to a specified deck.
     * Resets this player's "discard" card.
     * 
     * @author Miles Edwards
     * @version 1.0
     * @param deck specify the deck to discard a card to.
     * 
     */
    private void discardCardTo(CardDeck deck) {
        deck.appendToBottom(discard);
        // -- Print card discard action -- //
        System.out.println(String.format("player %d discards a %d to deck %d",
            getPlayerNumber(), discard.getDenomination().getValue(), deck.getDeckNumber()));
        discard = null;
        updateHandVolatility();
    }


    /**
     * atomicPlayAction method. Synchronized atomic action that
     * performs pickCardFrom and discardCardTo in one method that
     * each thread can only access one at a time.
     * 
     * @author Miles Edwards
     * @version 1.0
     * @param left deck to the left of the player.
     * @param right deck to the right of the player.
     * 
     */
    private synchronized void atomicPlayAction(CardDeck left, CardDeck right) {
        pickCardFrom(left);
        discardCardTo(right);
    }


    /**
     * findMostVolatileCard method. Locates the card with the highest
     * volatility value in this player's hand. Sets this player's
     * "discard" card to that card.
     * 
     * @author Miles Edwards
     * @version 1.0
     * 
     */
    public void findMostVolatileCard() {
        int index = 0;
        for (int i = 1; i < 4; i++) {
            if (hand[i].getVolatility() > hand[index].getVolatility()) {
                index = i;
            }
        }
        discard = hand[index];
    }


    /**
     * updateHandVolatility method. Updates any non preferred card's
     * volatility in this player's hand.
     * 
     * @author Miles Edwards
     * @version 1.0
     * 
     */
    public void updateHandVolatility() {
        for (int i = 0; i < 4; i++) {
            if (hand[i].getDenomination() != preferredDenomination) {
                hand[i].incrementVolatility();
            }
        }
    }


    /**
     * run method. This method is called when this player's thread
     * begins. Plays the game continuously until this player wins.
     * Could be interrupted by other player threads before winning.
     * 
     * @author Miles Edwards
     * @version 1.0 
     * 
     */
    @Override
    public void run() {
        // -- Print hand dealt -- //
        System.out.println(String.format("player %d initial hand %s %s %s %s",
        playerNumber,
        hand[0].getDenomination().name(), hand[1].getDenomination().name(),
        hand[2].getDenomination().name(), hand[3].getDenomination().name()));
        // -- Immediately check whether player has won, if so declare this -- //
        if (checkHand()) { 
            if (game.declareWinnerAs(this)) {
                // -- If player excusively wins -- //
                System.out.println(String.format("player %d wins", playerNumber));
                System.out.println(String.format("player %d final hand %s %s %s %s",
                playerNumber,
                hand[0].getDenomination().name(), hand[1].getDenomination().name(),
                hand[2].getDenomination().name(),hand[3].getDenomination().name()));
            } else {
                // -- If another player declared win first -- //
            }
        }
        // -- Loop game logic until game status changes -- //
        while (game.getStatus() != GameStatus.GAME_WON) {
            // -- Check that deck to the left is not empty (so a card may be drawn) -- //
            if (leftDeck.getDeckSize() > 0) {
                // -- Game logic -- //
                atomicPlayAction(leftDeck, rightDeck);
                System.out.println(String.format("player %d current hand is %s %s %s %s",
                        playerNumber,
                        hand[0].getDenomination().name(), hand[1].getDenomination().name(),
                        hand[2].getDenomination().name(),hand[3].getDenomination().name()));
                turns++;
                if (checkHand()) {
                    if (game.declareWinnerAs(this)) {
                        // -- If player excusively wins -- //
                        System.out.println(String.format("player %d wins", playerNumber));
                        System.out.println(String.format("player %d exits after %d turns",
                        playerNumber, turns));
                        System.out.println(String.format("player %d final hand %s %s %s %s",
                        playerNumber,
                        hand[0].getDenomination().name(), hand[1].getDenomination().name(),
                        hand[2].getDenomination().name(),hand[3].getDenomination().name()));
                    } else {
                        // -- If another player declared win first -- //
                    }
                }
            } 
        }
        int winningPlayer = game.getWinningPlayer().getPlayerNumber();
        if (winningPlayer != getPlayerNumber()) {
            // -- If player looses -- //
            System.out.println(String.format("player %d has informed player %d that player %d has won",
            winningPlayer, getPlayerNumber(), winningPlayer));
            System.out.println(String.format("player %d exits after %d turns",
            playerNumber, turns));
            System.out.println(String.format("player %d hand %s %s %s %s",
            playerNumber,
            hand[0].getDenomination().name(), hand[1].getDenomination().name(),
            hand[2].getDenomination().name(),hand[3].getDenomination().name()));
        }
    }
}
