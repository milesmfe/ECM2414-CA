package tests;

import cards.*;

public class SequentialCardGameTest {
    
    public static void main(String[] args) {
        CardGame sequentialTestGame = new CardGame(4);
        sequentialTestGame.setupGame();

        int currentPlayer = 1;
        while (sequentialTestGame.getStatus() == GameStatus.SETUP_ACTIVE) {
            for (int i = 0; i < sequentialTestGame.getNumPlayers(); i++) {
                Player player = sequentialTestGame.getPlayer(currentPlayer);
                player.pickCardFrom(player.getLeftDeck());
                player.discardCardTo(player.getRightDeck());
                if(player.checkHand()) { sequentialTestGame.declareWinnerAs(currentPlayer); }
                System.out.println(String.format("Player %o says: This is my hand:", currentPlayer));
                for (int j = 0; j < 4; j++) {
                    System.out.println(player.getCardAt(j).getDenomination().name());
                }
                if (currentPlayer == sequentialTestGame.getNumPlayers()) {
                    currentPlayer = 1;
                } else {
                    currentPlayer++;
                }
            }
        }
        System.out.println(String.format("Player %o wins!", sequentialTestGame.getWinningPlayer().getPlayerNumber()));
        System.out.println(String.format("Player %o says: This is my winning hand:", currentPlayer));
        for (int j = 0; j < 4; j++) {
            System.out.println(sequentialTestGame.getWinningPlayer().getCardAt(j).getDenomination().name());
        }
    }
}
