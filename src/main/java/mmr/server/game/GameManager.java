package mmr.server.game;

import mmr.server.game.exception.GameOverException;
import mmr.server.game.exception.PlayerTurnException;

import java.util.Arrays;

public class GameManager {
    private static final String X = "X";
    private static final String O = "O";
    private FiveInARowPlayer[] players;
    private FiveInARowPlayer winningPlayer;
    private FiveInARowPlayer losingPlayer;
    private FiveInARow game;
    private String[] turnKeeper = new String[]{X, O};
    private int turn = 0;
    private int totalTurns = 0;

    public GameManager() {
    }

    public FiveInARowPlayer[] getPlayers() {
        return players;
    }

    public FiveInARowState newGame(String playerOneId, String playerTwoId) {
        game = new FiveInARow();
        players = new FiveInARowPlayer[]{
                new FiveInARowPlayer(playerOneId, X),
                new FiveInARowPlayer(playerTwoId, O)};
        return FiveInARowState.of(this.game);
    }

    public FiveInARowPlayer nextTurn() {
        turn = turn % players.length;
        return players[turn];
    }

    public FiveInARowState init() {
        return FiveInARowState.of(this.game);
    }

    public FiveInARowState place(FiveInARowPlayer player, int y, int x) {
        if (!player.equals(players[turn])) {
            throw new PlayerTurnException("Player cannot take turn in another players' turn");
        }

        if (game.isGameOver()) {
            throw new GameOverException("Game is over, no more turns can be taken");
        }

        game.place(y, x, player.getCharacter());

        if (game.isGameOver()) {
            setWinningPlayer(player);
            setLosingPlayer(Arrays.stream(players).filter(e -> !e.equals(player)).findFirst().get());
            return FiveInARowState.of(this.game);
        }

        turn++;
        totalTurns++;
        return FiveInARowState.of(this.game);
    }

    public boolean gameIsOver() {
        return this.game.isGameOver();
    }

    public String getTurn() {
        return players[turn % 2].getCharacter();
    }

    @Override
    public String toString() {
        return "GameManager{" +
                "players=" + Arrays.toString(players) +
                ", game=" + game +
                ", turnKeeper=" + Arrays.toString(turnKeeper) +
                ", turn=" + turn +
                ", totalTurns=" + totalTurns +
                '}';
    }

    public FiveInARowPlayer getWinningPlayer() {
        return winningPlayer;
    }

    private void setWinningPlayer(FiveInARowPlayer winningPlayer) {
        this.winningPlayer = winningPlayer;
    }

    public FiveInARowPlayer getLosingPlayer() {
        return losingPlayer;
    }

    private void setLosingPlayer(FiveInARowPlayer losingPlayer) {
        this.losingPlayer = losingPlayer;
    }

    public int getTotalTurns() {
        return totalTurns;
    }
}
