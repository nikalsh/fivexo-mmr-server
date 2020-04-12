package mmr.server.game;

import mmr.server.game.exception.GameOverException;
import mmr.server.game.exception.PlayerTurnException;

public class GameManager {
    private static final String X = "X";
    private static final String O = "O";
    private FiveInARowPlayer[] players;
    private FiveInARow game;
    private String[] turnKeeper = new String[]{X, O};
    private int turn = 0;

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

    public FiveInARowState place(FiveInARowPlayer player, int x, int y) {
        if (!player.equals(players[turn])) {
            throw new PlayerTurnException("Player cannot take turn in another players' turn");
        }

        if (game.isGameOver()) {
            throw new GameOverException("Game is over, no more turns can be taken");
        }

        game.place(x, y, player.getCharacter());

        if (game.isGameOver()) {
            return FiveInARowState.of(this.game);
        }

        turn++;
        return FiveInARowState.of(this.game);
    }


}
