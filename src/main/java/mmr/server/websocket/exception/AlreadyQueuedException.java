package mmr.server.websocket.exception;

public class AlreadyQueuedException extends RuntimeException{
    public AlreadyQueuedException(String message) {
        super(message);
    }

    public AlreadyQueuedException(String message, Throwable cause) {
        super(message, cause);
    }
}
