package mmr.server.game;

import javax.json.Json;
import java.io.StringWriter;
import java.util.Arrays;

public class FiveInARowState {
    private String[][] grid;
    private String turn;
    private boolean gameOver = false;
    private String winnerCharacter;

    private FiveInARowState(FiveInARow fiveInARow) {
        this.grid = fiveInARow.getGrid();
        this.gameOver = fiveInARow.isGameOver();
        this.winnerCharacter = fiveInARow.getWinnerCharacter();
    }

    public static FiveInARowState of(FiveInARow fiveInARow) {
        return new FiveInARowState(fiveInARow);
    }

    public String getTurn() {
        return turn;
    }

    public void setTurn(String turn) {
        this.turn = turn;
    }

    public String[][] getGrid() {
        return grid;
    }

    public boolean isGameOver() {
        return gameOver;
    }


    public String getWinnerCharacter() {
        return winnerCharacter;
    }

    @Override
    public String toString() {
        return Arrays.deepToString(grid);
    }
}
