package mmr.server.queue;

import java.util.ArrayList;
import java.util.List;

public class GameLobby {
    private List<String> clients = new ArrayList<>();

    public GameLobby(String id) {
        clients.add(id);
    }

    public GameLobby() {
    }

    public boolean addClient(String id) {
        if (clients.size() >= 2) {
            return false;
        }
        return clients.add(id);
    }

    public int clients() {
        return clients.size();
    }

    @Override
    public String toString() {
        return "{ROOM: players: " + clients.size() + " }";
    }

    public List<String> getClients() {
        return clients;
    }
}


