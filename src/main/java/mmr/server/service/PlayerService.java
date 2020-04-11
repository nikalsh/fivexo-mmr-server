package mmr.server.service;

import mmr.server.utils.PlayerExperienceComparator;
import mmr.server.model.Player;
import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class PlayerService {

    public Player createNewPlayer() {
        Player player = new Player();
        player.persist();
        return player;
    }

    public Player findById(String id) {
        return findById(new ObjectId(id));
    }

    private Player findById(ObjectId id) {
        return Player.findById(id);
    }

    public Player updateName(String id, String name) {
        Player player = findById(id);
        player.name = name;
        return updatePlayer(player);
    }

    private Player updatePlayer(Player player) {
        Player.update(player);
        return Player.findById(player.id);
    }

    public List<Player> findAllPlayers() {
        List<Player> list = Player.findAll().list();
        hideIds(list);
        sortByExperience(list);
        return list;
    }

    private List<Player> hideIds(List<Player> list) {
        list.stream().forEach(e -> e.id = null);
        return list;
    }

    private void sortByExperience(List<Player> list) {
        Collections.sort(list, new PlayerExperienceComparator());
        Collections.reverse(list);
    }


//    public List<Player> findPlayersByPageIndex() {
//        List<Player> list = Player.findAll().list();
//        anonymizeIds(list);
//        return list;
//    }




}
