package mmr.server.game.exception;

public class PlayerTurnException extends RuntimeException {

    public PlayerTurnException(String message) {
        super(message);
    }

    public PlayerTurnException(String message, Throwable cause) {
        super(message, cause);
    }
}
