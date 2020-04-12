package mmr.server.game;

import java.util.Objects;

public class FiveInARowPlayer {
    private String id;
    private String character;
    private boolean won = false;

    public boolean hasWon() {
        return won;
    }

    public void setWon(boolean won) {
        this.won = won;
    }

    public FiveInARowPlayer(String id, String character) {
        this.id = id;
        this.character = character;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FiveInARowPlayer that = (FiveInARowPlayer) o;
        return won == that.won &&
                Objects.equals(id, that.id) &&
                Objects.equals(character, that.character);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, character, won);
    }
}
