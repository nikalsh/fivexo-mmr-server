package mmr.server.service;

import mmr.server.model.Game;
import org.bson.types.ObjectId;

public class GameService {

    public Game create(String player1Id, String player2Id) {
        Game game = new Game();
        game.player1 = player1Id;
        game.player2 = player2Id;
        game.persist();
        return game;
    }

    public Game findById(String id) {
        return findById(new ObjectId(id));
    }

    private Game findById(Object id) {
        return Game.findById(id);
    }

    public Game update(Game game) {
        Game.update(game);
        return Game.findById(game.id);
    }



}
