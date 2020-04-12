package mmr.server.websocket;

import mmr.server.game.FiveInARow;
import mmr.server.game.GameManager;
import mmr.server.model.Game;
import mmr.server.model.Player;
import mmr.server.service.GameService;
import mmr.server.service.PlayerService;
import mmr.server.websocket.exception.AlreadyInGameException;
import mmr.server.websocket.exception.AlreadyQueuedException;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@ServerEndpoint("/queue/{id}")
@ApplicationScoped
public class WebSocketMatchmakingQueue {

    @Inject
    PlayerService playerService;
    @Inject
    GameService gameService;

    ExecutorService executorService;
    Map<String, Session> sessions = new ConcurrentHashMap<>();
    Queue<Player> matchmakingQueue = new LinkedList<>();
    Map<String, GameManager> games = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        ExecutorService executor = Executors.newFixedThreadPool(100);
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("id") String id) {
        sessions.put(id, session);
    }

    @OnClose
    public void onClose(Session session, @PathParam("id") String id) throws IOException {
        sessions.get(id).close();
        sessions.remove(id);
    }

    @OnMessage
    public void message(String message, @PathParam("id") String id) {

        switch (message) {
            case "play":
                executorService.execute(() -> {
                    boolean waiting = true;
                    boolean alreadyQueued = matchmakingQueue.stream().filter(e -> e.id.toString().equals(id)).findFirst().isPresent();
                    boolean alreadyInGame = games.containsKey(id);
                    if (alreadyQueued) {
                        sendToSession(sessions.get(id), "Already queued for a game");
                        throw new AlreadyQueuedException("Already queued for a game");
                    }

                    if (alreadyInGame) {
                        sendToSession(sessions.get(id), "Already in a game");
                        throw new AlreadyInGameException("Already in a game");
                    }

                    matchmakingQueue.add(playerService.findById(id));
                    sendToSession(sessions.get(id), "Queued for a game");
                    //waiting for other player

                    while (waiting) {
                        if (matchmakingQueue.size() >= 2) {
                            Player player1 = matchmakingQueue.stream().filter(e -> e.id.toString().equals(id)).findFirst().get();
                            matchmakingQueue.remove(player1);
                            Player player2 = matchmakingQueue.poll();
//                            GameManager gameManager = new GameManager();
//                            games.put(player1.id.toString(), gameManager);
//                            games.put(player2.id.toString(), gameManager);
//                            gameManager.newGame(player1.id.toString(), player2.id.toString());
                            Game game = gameService.create(player1.id.toString(), player2.id.toString());
                            System.out.println(player1);
                            System.out.println(player2);
                            waiting = false;
                            sendToSession(sessions.get(player1.id.toString()), game.id.toString());
                            sendToSession(sessions.get(player2.id.toString()), game.id.toString());
                        }
                    }

                });

                break;
            case "turn":

        }

    }


    private void sendToSession(Session session, String message) {
        session.getAsyncRemote().sendObject(message, result -> {
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
