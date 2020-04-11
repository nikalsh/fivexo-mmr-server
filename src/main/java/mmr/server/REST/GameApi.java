package mmr.server.REST;


import mmr.server.model.Player;
import mmr.server.service.PlayerService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GameApi {

    @Inject
    PlayerService playerService;

    @POST
    @Path("/player")
    public Player createNewPlayer() {

    }

    @GET
    @Path("/player/{id}")
    public Player getPlayerById(@PathParam(value="id") String id) {
        return playerService.findById(id);
    }

    @PUT
    @Path("/player")
    public Player updateName(Player player) {
        return playerService.updateName(player);
    }

    @GET
    @Path("/player")
    public List<Player> getAllPlayers() {
        return playerService.findAllPlayers();
    }

    @GET
    @Path("/player/{index}")
    public List<Player> findPlayersByIndex(@PathParam(value = "index") String index) {
        return playerService.findPlayersByIndex();
    }
}