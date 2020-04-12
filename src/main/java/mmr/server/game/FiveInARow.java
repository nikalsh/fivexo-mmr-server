package mmr.server.game;

public class FiveInARow {
    private static final int X_DEFAULT = 15;
    private static final int Y_DEFAULT = 15;
    private String[][] grid;
    private boolean gameOver = false;
    private String winnerCharacter;
    private Delta[] Deltas = new Delta[]{
            new Delta(1, 1),
            new Delta(1, -1),
            new Delta(-1, 1),
            new Delta(-1, -1),
            new Delta(1, 0),
            new Delta(-1, 0),
            new Delta(0, 1),
            new Delta(0, -1),
    };

    public FiveInARow() {
        this.grid = new String[X_DEFAULT][Y_DEFAULT];
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public String getWinnerCharacter() {
        return winnerCharacter;
    }

    public boolean canPlace(int x, int y) {
        return isInBounds(x, y) && grid[x][y].isEmpty();
    }

    private boolean isInBounds(int x, int y) {
        return x >= 0 && x < X_DEFAULT && y >= 0 && y < Y_DEFAULT;
    }

    public boolean place(int x, int y, String XO) {
        if (canPlace(x, y)) {
            this.grid[x][y] = XO;

            for (Delta delta : Deltas) {
                gameOver = checkWin(XO, x, y, delta.x, delta.y);
                if (gameOver) {
                    break;
                }
            }

            if (gameOver) {
                winnerCharacter = XO;
            }
        }
        return gameOver;
    }


    private boolean checkWin(String XO, int x, int y, int deltaX, int deltaY) {
        boolean win = true;
        for (int count = 0; count < 5; count++) {
            if (isInBounds(x, y)) {
                String test = grid[x][y];
                if (test != XO) {
                    win = false;
                    break;
                }
            }
            x += deltaX;
            y += deltaY;
        }
        return win;
    }

    public String[][] getGrid() {
        return grid;
    }

    public class Delta {
        int x, y;

        public Delta(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
