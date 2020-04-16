package mmr.server.websocket;

import mmr.server.game.FiveInARowState;
import mmr.server.game.GameManager;
import mmr.server.model.Game;
import mmr.server.service.GameService;
import mmr.server.websocket.encoders.ObjectToJson;

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
        System.out.println(gameId);
        System.out.println(playerId);
        System.out.println("game open");
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

        String turn = gameManagers.get(gameId).getTurn();

        String character = Arrays.stream(gameManagers.get(gameId).getPlayers())
                .filter(e -> e.getId().equals(playerId))
                .findFirst()
                .get()
                .getCharacter();

        CharacterTurnObject characterTurnObject = new CharacterTurnObject(character, turn);

        sendToSession(sessions.get(playerId), ObjectToJson.toJson(characterTurnObject));

//        sendToSession(session, "Connected to websocket successfully");
    }

    @OnMessage
    public void message(String coords, @PathParam("gameId") String gameId, @PathParam("playerId") String playerId) {
        Integer y = Integer.valueOf(coords.split(" ")[0]);
        Integer x = Integer.valueOf(coords.split(" ")[1]);

        GameManager gameManager = gameManagers.get(gameId);

        if (gameManager.gameIsOver()) {
            try {
                sessions.get(gameManager.getPlayers()[0].getId()).close();
                sessions.get(gameManager.getPlayers()[1].getId()).close();
                sessions.remove(gameManager.getPlayers()[0].getId());
                sessions.remove(gameManager.getPlayers()[1].getId());
                gameManagers.remove(gameId);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (gameManager.nextTurn().getId().equals(playerId) && gameManagers.get(gameId) != null) {
            FiveInARowState fiveInARowState = gameManager.place(Arrays.stream(gameManager.getPlayers()).filter(e -> e.getId().equals(playerId)).findFirst().get(), y, x);
//            System.out.println(fiveInARowState);
            fiveInARowState.setTurn(gameManagers.get(gameId).getTurn());
            sendToSession(sessions.get(gameManager.getPlayers()[0].getId()), ObjectToJson.toJson(fiveInARowState));
            sendToSession(sessions.get(gameManager.getPlayers()[1].getId()), ObjectToJson.toJson(fiveInARowState));
        }
    }

    @OnClose
    public void onClose(Session session, @PathParam("playerId") String id) throws IOException {
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
