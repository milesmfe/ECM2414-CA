package cards;

/**
 * GameStatus enum. Defines each status that CardGame
 * can have.
 * 
 * @author Miles Edwards
 * @version 1.0
 * 
 */
public enum GameStatus {
    NOT_SETUP_NO_PACK,
    NOT_SETUP_PACK_READY,
    SETUP_IDLE,
    SETUP_ACTIVE,
    GAME_WON;
}
