package mmr.server.websocket;

import mmr.server.model.Game;
import mmr.server.model.Player;
import mmr.server.queue.MatchmakingQueue;
import mmr.server.service.GameService;
import mmr.server.service.PlayerService;
import mmr.server.websocket.encoders.ObjectToJson;
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
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@ServerEndpoint("/queue/{id}")
@ApplicationScoped
public class WebSocketMatchmakingQueue {

    @Inject
    PlayerService playerService;
    @Inject
    GameService gameService;
    @Inject
    MatchmakingQueue matchmakingQueue;

    Map<String, Session> sessions = new ConcurrentHashMap<>();

    private static final ExecutorService executors = Executors.newFixedThreadPool(50);


    @OnOpen
    public void onOpen(Session session, @PathParam("id") String id) {
        session.setMaxIdleTimeout(300000L);
        sessions.put(id, session);
        sendToSession(sessions.get(id), "websocket opened");
    }

    @OnClose
    public void onClose(Session session, @PathParam("id") String id) throws IOException {
        sessions.remove(id);
    }

    @OnMessage
    public void asd(String message, @PathParam("id") String id) {
        System.out.println(message);

        sendToSession(sessions.get(id), "queued");

        switch (message) {
            case "queue":
                executors.execute(() -> {
                    //blocking
                    MatchmakingObject matchmakingObject = null;
                    try {
                        matchmakingObject = matchmakingQueue.addClient(id).get();
                        System.out.println(matchmakingObject);
                        sendToSession(sessions.get(id), ObjectToJson.toJson(matchmakingObject));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                });
            break;
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
