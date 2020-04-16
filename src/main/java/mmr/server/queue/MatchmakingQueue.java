package mmr.server.queue;

import mmr.server.model.Game;
import mmr.server.service.GameService;
import mmr.server.websocket.MatchmakingObject;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

//@Startup
@ApplicationScoped
public class MatchmakingQueue {

    private static final ExecutorService executors = Executors.newFixedThreadPool(50);

    @Inject
    GameService gameService;

    private BlockingDeque<String> queue = new LinkedBlockingDeque<>();
    private BlockingDeque<GameLobby> gameQueue = new LinkedBlockingDeque<>();

    private List<Game> gameList = Collections.synchronizedList(new ArrayList<>());

    public MatchmakingQueue() {

    }

    @PostConstruct
    public void init() {
        startQueue();
    }

    public void purge(String gameId) {
        synchronized (gameList) {
            Game game = gameList.stream().filter(e -> e.id.toString().equals(gameId)).findFirst().orElse(null);
            if (game != null) {
                gameList.remove(game);
            }
        }
    }

    public void startQueue() {
        executors.execute(() -> {
            while (true) {

                try {
                    Thread.sleep(1000);
//                    System.out.println(LocalDateTime.now() + " " + Thread.currentThread().getName());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                GameLobby gameLobby = nextGameOrNull();

                //is there anyone in queue?
                if (!queue.isEmpty()) {
                    String client1 = nextClientOrNull();
                    if (client1 != null) {
                        if (gameLobby == null) {
                            //no rooms available, create new game
                            gameLobby = new GameLobby();
                            gameLobby.addClient(client1);
                            gameQueue.add(gameLobby);

                        } else {
                            if (!gameLobby.addClient(client1)) {
                                //game is full and client could not be added, create a new game
                                GameLobby newGame = new GameLobby();
                                if (newGame.addClient(client1)) {
                                    //wait for next loop to add next client
                                    gameQueue.add(newGame);
                                }
                            }
                        }
                    }
                } else if (gameLobby != null && gameLobby.getClients().size() < 2) {
                    //re-add the gamelobby when no new client was added
                    gameQueue.add(gameLobby);
                }

                if (gameLobby != null) {
                    if (gameLobby.clients() == 2) {
                        //the gamelobby is full, no need to re-add it to the gameQueue
                        Game game = gameService.create(gameLobby.getClients().get(0), gameLobby.getClients().get(1));
                        synchronized (gameList) {
                            gameList.add(game);
                        }
                    }
                }
                System.out.format("ClientQueue %s%n", queue);
                System.out.format("GameQueue: %s%n", gameQueue);
                System.out.format("GameList: %s%n", gameList);
            }
        });
    }

    private String nextClientOrNull() {
        if (queue.peek() == null) {
            return null;
        }
        return queue.pollFirst();
    }

    public GameLobby nextGameOrNull() {
        if (gameQueue.peek() == null) {
            return null;
        }
        return gameQueue.pollFirst();
    }

    @Override
    public String toString() {
        return queue.toString();
    }

    public CompletableFuture<MatchmakingObject> addClient(String id) {
        queue.add(id);
        System.out.format("%s added to queue%n", id);

        CompletableFuture<MatchmakingObject> completableFuture
                = new CompletableFuture<>();

        executors.execute(() -> {
            Game game = null;
            while (true) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (gameList) {
                    game = gameList.stream()
                            .filter(e -> e.player1.equals(id) || e.player2.equals(id))
                            .findFirst()
                            .orElse(null);

                    if (game != null) {
                        break;
                    }
                }
            }
            completableFuture.complete(new MatchmakingObject(game.id.toString(), true));
        });
        return completableFuture;
    }
}
