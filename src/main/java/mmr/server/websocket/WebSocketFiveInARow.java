package mmr.server.websocket;

import mmr.server.game.FiveInARowState;
import mmr.server.game.GameManager;
import mmr.server.model.Game;
import mmr.server.service.GameService;
import mmr.server.service.MMRService;
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

    @Inject
    MMRService mmrService;

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


        if (gameManager.nextTurn().getId().equals(playerId) && gameManager != null) {
            FiveInARowState fiveInARowState = gameManager.place(Arrays.stream(gameManager.getPlayers()).filter(e -> e.getId().equals(playerId)).findFirst().get(), y, x);
//            System.out.println(fiveInARowState);
            fiveInARowState.setTurn(gameManagers.get(gameId).getTurn());
            sendToSession(sessions.get(gameManager.getPlayers()[0].getId()), ObjectToJson.toJson(fiveInARowState));
            sendToSession(sessions.get(gameManager.getPlayers()[1].getId()), ObjectToJson.toJson(fiveInARowState));
        }


        if (gameManager.gameIsOver() && gameManager != null) {
            try {
                System.out.println("closing game");
                sessions.get(gameManager.getPlayers()[0].getId()).close();
                sessions.get(gameManager.getPlayers()[1].getId()).close();
                sessions.remove(gameManager.getPlayers()[0].getId());
                sessions.remove(gameManager.getPlayers()[1].getId());
                String winner = gameManager.getWinningPlayer().getId();
                String loser = gameManager.getLosingPlayer().getId();
                Integer turns = gameManager.getTotalTurns();
                postGame(winner, loser, gameId, turns);
                gameManagers.remove(gameId);
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (gameManager.getTotalTurns() == 15 * 15 && gameManager != null) {
            try {

                //tie
                System.out.println("tie, closing game");
                sessions.get(gameManager.getPlayers()[0].getId()).close();
                sessions.get(gameManager.getPlayers()[1].getId()).close();
                sessions.remove(gameManager.getPlayers()[0].getId());
                sessions.remove(gameManager.getPlayers()[1].getId());

                String playerId1 = gameManager.getPlayers()[0].getId();
                String playerId2 = gameManager.getPlayers()[1].getId();
                Integer turns = 15 * 15;
                tiePostGame(playerId1, playerId2, gameId, turns);
                gameManagers.remove(gameId);
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void tiePostGame(String playerId1, String playerId2, String gameId, Integer turns) {
        mmrService.tiePostGame(playerId1, playerId2, gameId, turns);
    }

    private void postGame(String winnerId, String loserId, String gameId, Integer turns) {
        mmrService.postGame(winnerId, loserId, gameId, turns);
    }

    @OnClose
    public void onClose(Session session, @PathParam("playerId") String id) throws IOException {
        System.out.format("recieved onClose from %s%n", id);
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
