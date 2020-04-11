package mmr.server.utils;

import mmr.server.model.Player;

import java.util.Comparator;

public class PlayerExperienceComparator implements Comparator<Player> {
    @Override
    public int compare(Player player, Player player1) {
        return Integer.valueOf(player.experience).compareTo(Integer.valueOf(player1.experience));
    }
}