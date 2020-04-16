package mmr.server.queue;

import mmr.server.model.Game;
import mmr.server.service.GameService;
import mmr.server.websocket.MatchmakingObject;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

@ApplicationScoped
public class MatchmakingQueue {

    @Inject
    GameService gameService;
    //keeps track of clients waiting to be added to a gamelobby, produces gamelobbies
    private BlockingDeque<String> queue = new LinkedBlockingDeque<>();
    //gamelobby acts as a waiting room for clients, consumes clients and produces games
    private BlockingDeque<GameLobby> gameQueue = new LinkedBlockingDeque<>();
    //gamelist are actual games, consumes a gamelobby when it's full
    private List<Game> gameList = Collections.synchronizedList(new ArrayList<>());
    private static final ExecutorService executors = Executors.newFixedThreadPool(50);

    public MatchmakingQueue() { }

    @PostConstruct
    public void init() {
        startQueue();
    }

    private void startQueue() {
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
                    String client = nextClientOrNull();
                    if (client != null) {

                        if (gameLobby == null) {
                            //no gamelobbies available, create new gamelobby
                            gameLobby = new GameLobby();
                            gameLobby.addClient(client);
                            gameQueue.add(gameLobby);
                        } else {
                            if (!gameLobby.addClient(client)) {
                                //gamelobby is full and client could not be added, create a new gamelobby and add client
                                GameLobby newGame = new GameLobby();
                                if (newGame.addClient(client)) {
                                    //wait for next loop to add next client
                                    gameQueue.add(newGame);
                                }
                            }
                        }
                    }
                } else if (gameLobby != null && gameLobby.getClients().size() < 2) {
                    //re-add the gamelobby to the queue when no new client was added
                    gameQueue.add(gameLobby);
                }
                if (gameLobby != null) {
                    if (gameLobby.clients() == 2) {
                        //the gamelobby is full, dont re-add it to the gameQueue
                        // create a game and add both clients from the lobby
                        Game game = gameService.create(gameLobby.getClients().get(0), gameLobby.getClients().get(1));
                        synchronized (gameList) {
                            gameList.add(game);
                        }
                    }
                }
//                System.out.format("ClientQueue %s%n", queue);
//                System.out.format("GameQueue: %s%n", gameQueue);
//                System.out.format("GameList: %s%n", gameList);
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
                        //only remove the game from list when its matchmakingobject has been initiated for both players
                        game.initiated++;
                        if (game.initiated == 2) {
                            removeGameFromGameList(game.id.toString());
                        }
                        break;
                    }
                }
            }
            completableFuture.complete(new MatchmakingObject(game.id.toString(), true));
        });
        return completableFuture;
    }

    public void removeGameFromGameList(String gameId) {
        synchronized (gameList) {
            Game game = gameList.stream().filter(e -> e.id.toString().equals(gameId)).findFirst().orElse(null);
            if (game != null) {
                gameList.remove(game);
            }
        }
    }

    @Override
    public String toString() {
        return queue.toString();
    }

}
