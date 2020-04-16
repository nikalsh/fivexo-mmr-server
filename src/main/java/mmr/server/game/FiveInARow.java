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
        this.grid = new String[Y_DEFAULT][X_DEFAULT];
        for (int i = 0; i < X_DEFAULT; i++) {
            for (int j = 0; j < Y_DEFAULT; j++) {
                grid[i][j] = "";
            }
        }
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public String getWinnerCharacter() {
        return winnerCharacter;
    }

    public boolean canPlace(int y, int x) {
        return isInBounds(x, y) && grid[y][x].isEmpty();
    }

    private boolean isInBounds(int y, int x) {
        return x >= 0 && x < X_DEFAULT && y >= 0 && y < Y_DEFAULT;
    }

    public boolean place(int y, int x, String XO) {
        if (canPlace(y, x)) {
            this.grid[y][x] = XO;

            for (Delta delta : Deltas) {
                gameOver = checkWin(XO, y, x, delta.y, delta.x);
                if (gameOver) {
                    winnerCharacter = XO;
                    break;
                }
            }
        }
        return gameOver;
    }

    private boolean checkWin(String XO, int y, int x, int deltaY, int deltaX) {
        int winCounter = 0;
        for (int count = 1; count <= 5; count++) {
            if (isInBounds(y, x)) {
                String test = grid[y][x];
                if (test == XO) {
                    winCounter++;
                } else {
                    break;
                }
            }
            x += deltaX;
            y += deltaY;
        }
        return winCounter == 5;
    }

    public String[][] getGrid() {
        return grid;
    }

    protected void setGrid(String[][] grid) {
        this.grid = grid;
    }

    public class Delta {
        int x, y;

        public Delta(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
