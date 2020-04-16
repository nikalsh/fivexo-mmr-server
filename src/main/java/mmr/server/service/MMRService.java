package mmr.server.service;

import mmr.ExperienceCalculator;
import mmr.exception.LevelNotFoundException;
import mmr.model.PlayerRank;
import mmr.server.model.Game;
import mmr.server.model.Player;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class MMRService {

    @Inject
    PlayerService playerService;

    @Inject
    GameService gameService;

    public void postGame(String winnerId, String loserId, String gameId, Integer turns) {
        Player winningPlayer = playerService.findById(winnerId);
        Player losingPlayer = playerService.findById(loserId);
        winningPlayer.wins++;
        losingPlayer.losses++;
        Game game = gameService.findById(gameId);
        game.winnerId = winnerId;
        game.loserId = loserId;
        game.turns = turns;
        gameService.update(game);
        updateExperience(winningPlayer, losingPlayer);
    }

    public void tiePostGame(String playerId1, String playerId2, String gameId, Integer turns) {
        Player player1 = playerService.findById(playerId1);
        Player player2 = playerService.findById(playerId2);
        player1.ties++;
        player2.ties++;
        Game game = gameService.findById(gameId);
        game.turns = turns;
        gameService.update(game);
        playerService.update(player1);
        playerService.update(player2);
    }

    private void updateExperience(Player winner, Player loser) {
        try {
            PlayerRank playerRankWinner = new PlayerRank(winner.experience, winner.id.toString());
            PlayerRank playerRankLoser = new PlayerRank(loser.experience, loser.id.toString());
            List<PlayerRank> playerRanks = ExperienceCalculator.updatePlayerRanks(playerRankWinner, playerRankLoser);

            playerRankWinner = find(playerRanks, winner);
            playerRankLoser = find(playerRanks, loser);

            if (playerRankWinner != null && playerRankLoser != null) {
                winner.experience = playerRankWinner.getExperience();
                winner.level = playerRankWinner.getLevel();

                loser.experience = playerRankLoser.getExperience();
                loser.level = playerRankLoser.getLevel();

                playerService.update(winner);
                playerService.update(loser);
            }

        } catch (LevelNotFoundException e) {
            e.printStackTrace();
        }
    }

    private PlayerRank find(List<PlayerRank> playerRanks, Player player) {
        return playerRanks.stream()
                .filter(e -> e.getPlayerId().equals(player.id.toString()))
                .findFirst()
                .orElse(null);
    }
}
