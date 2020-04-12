package mmr.server.websocket.exception;

public class AlreadyInGameException extends RuntimeException {
    public AlreadyInGameException(String message) {
        super(message);
    }

    public AlreadyInGameException(String message, Throwable cause) {
        super(message, cause);
    }
}
