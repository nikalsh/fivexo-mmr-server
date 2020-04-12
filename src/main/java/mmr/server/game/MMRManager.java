package mmr.server.game;

import mmr.ExperienceCalculator;
import mmr.exception.LevelNotFoundException;
import mmr.model.PlayerRank;
import mmr.server.model.Player;
import mmr.server.service.PlayerService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class MMRManager {

    @Inject
    PlayerService playerService;

    public void updateExperience(Player winner, Player loser) {
        try {
            PlayerRank playerRankWinner = new PlayerRank(winner.experience, winner.id.toString());
            PlayerRank playerRankLoser = new PlayerRank(loser.experience, loser.id.toString());
            List<PlayerRank> playerRanks = ExperienceCalculator.updatePlayerRanks(playerRankWinner, playerRankLoser);

            playerRankWinner = find(playerRanks, winner);
            playerRankLoser = find(playerRanks, loser);

            winner.experience = playerRankWinner.getExperience();
            winner.level = playerRankWinner.getLevel();

            loser.experience = playerRankLoser.getExperience();
            loser.level = playerRankLoser.getLevel();

            playerService.update(winner);
            playerService.update(loser);

        } catch (LevelNotFoundException e) {
            e.printStackTrace();
        }
    }

    private PlayerRank find(List<PlayerRank> playerRanks, Player player) {
       return playerRanks.stream()
                .filter(e -> e.getPlayerId().equals(player.id.toString()))
                .findFirst()
                .get();
    }



}
