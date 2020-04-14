package mmr.server.websocket;

public class MatchmakingObject {
    String id;
    boolean gameFound;

    public MatchmakingObject() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isGameFound() {
        return gameFound;
    }

    public void setGameFound(boolean gameFound) {
        this.gameFound = gameFound;
    }

    public MatchmakingObject(String id, boolean gameFound) {
        this.id = id;
        this.gameFound = gameFound;
    }
}
