package mmr.server.websocket;

import mmr.server.game.FiveInARowState;
import mmr.server.game.GameManager;
import mmr.server.model.Game;
import mmr.server.service.GameService;
import mmr.server.websocket.encoders.FiveInARowStateEncoder;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/play/{gameId}/{playerId}")
@ApplicationScoped
public class WebSocketFiveInARow {

    @Inject
    GameService gameService;

    Map<String, Session> sessions = new ConcurrentHashMap<>();
    Map<String, GameManager> gameManagers = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("gameId") String gameId, @PathParam("playerId") String playerId) {
        Game game = gameService.findById(gameId);
        if (game == null) {
            return;
        }
        if (gameManagers.get(gameId) == null) {
            GameManager gameManager = new GameManager();
            gameManager.newGame(game.player1, game.player2);
            gameManagers.put(gameId, gameManager);
        }
        sessions.put(playerId, session);
        sendToSession(sessions.get(playerId),
                Arrays.stream(gameManagers.get(gameId).getPlayers())
                        .filter(e -> e.getId().equals(playerId))
                        .findFirst()
                        .get()
                        .getCharacter());
//        sendToSession(session, "Connected to websocket successfully");
    }

    @OnMessage
    public void message(String coords, @PathParam("gameId") String gameId, @PathParam("playerId") String playerId) {
        Integer x = Integer.valueOf(coords.split(" ")[0]);
        Integer y = Integer.valueOf(coords.split(" ")[1]);

        GameManager gameManager = gameManagers.get(gameId);

        if (gameManager.nextTurn().getId().equals(playerId)) {
            FiveInARowState fiveInARowState = gameManager.place(Arrays.stream(gameManager.getPlayers()).filter(e -> e.getId().equals(playerId)).findFirst().get(), x, y);
            sendToSession(sessions.get(gameManager.getPlayers()[0].getId()), FiveInARowStateEncoder.toJson(fiveInARowState));
            sendToSession(sessions.get(gameManager.getPlayers()[1].getId()), FiveInARowStateEncoder.toJson(fiveInARowState));
        }


    }

    @OnClose
    public void onClose(Session session, @PathParam("id") String id) throws IOException {
        sessions.get(id).close();
        sessions.remove(id);
    }

    private void sendToSession(Session session, String message) {
        session.getAsyncRemote().sendObject(message, result -> {
            if (result.getException() != null) {
                System.out.println("Unable to send message: " + result.getException());
            }
        });
    }

    private void sendGameStateToSession(Session session, FiveInARowState fiveInARowState) {
        session.getAsyncRemote().sendObject(fiveInARowState, result -> {
            if (result.getException() != null) {
                System.out.println("Unable to send message: " + result.getException());
            }
        });
    }

    private void broadcast(String message) {
        sessions.values().forEach(s -> {
            s.getAsyncRemote().sendObject(message, result -> {
                if (result.getException() != null) {
                    System.out.println("Unable to send message: " + result.getException());
                }
            });
        });
    }
}
