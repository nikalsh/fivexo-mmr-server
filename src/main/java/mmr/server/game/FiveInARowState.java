package mmr.server.game;

public class FiveInARowState {
    private String[][] grid;
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


    public String[][] getGrid() {
        return grid;
    }

    public boolean isGameOver() {
        return gameOver;
    }


    public String getWinnerCharacter() {
        return winnerCharacter;
    }
}
