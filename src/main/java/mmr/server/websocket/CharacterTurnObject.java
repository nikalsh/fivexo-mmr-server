package mmr.server.websocket;

public class CharacterTurnObject {
    String character;
    String turn;

    public CharacterTurnObject() {
    }

    public CharacterTurnObject(String character, String turn) {
        this.character = character;
        this.turn = turn;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getTurn() {
        return turn;
    }

    public void setTurn(String turn) {
        this.turn = turn;
    }
}
