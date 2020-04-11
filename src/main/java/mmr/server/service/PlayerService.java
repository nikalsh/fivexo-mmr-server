package mmr.server.service;

import mmr.server.model.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerService {

    public Player findById(String id) {
        return new Player();
    }

    public Player updateName(Player player) {
        return new Player();
    }

    public List<Player> findAllPlayers() {
        return new ArrayList<>(List.of(new Player()));
    }

    public List<Player> findPlayersByIndex() {
        return new ArrayList<>(List.of(new Player()));
    }

    public Player newPlayer() {
        return new Player();
    }
}
