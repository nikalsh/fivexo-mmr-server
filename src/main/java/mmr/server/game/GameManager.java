package mmr.server.game;

public class GameManager {
    private static final String X = "X";
    private static final String O = "O";
    private FiveInARowPlayer[] players;
    private FiveInARow game;
    private String[] turnKeeper = new String[]{X, O};
    private int turn = 0;

    private GameManager() {

    }

    public void newGame(String playerOneId, String playerTwoId) {
        game = new FiveInARow();
        players = new FiveInARowPlayer[]{
                new FiveInARowPlayer(playerOneId, X),
                new FiveInARowPlayer(playerTwoId, O)};
    }

    public FiveInARowPlayer nextTurn() {
        turn = turn % players.length;
        return players[turn];
    }

    public FiveInARowState place(FiveInARowPlayer player, int x, int y) {
        if (!player.equals(players[turn])) {
            //add exception
            return null;
        }

        if (game.isGameOver()) {
            //add exception
            return null;
        }

        game.place(x, y, player.getCharacter());

        if (game.isGameOver()) {
            return FiveInARowState.of(game);
        }

        turn++;
        return FiveInARowState.of(game);
    }


}
