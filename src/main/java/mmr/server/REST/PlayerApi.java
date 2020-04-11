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
public class PlayerApi {

    @Inject
    PlayerService playerService;

    @POST
    @Path("/player")
    public Player createNewPlayer() {
        return playerService.createNewPlayer();
    }

    @GET
    @Path("/player/{id}")
    public Player findPlayerById(@PathParam(value = "id") String id) {
        return playerService.findById(id);
    }

    @PUT
    @Path("/player/{id}/{name}")
    public Player updateName(@PathParam(value = "id") String id, @PathParam(value = "name") String name) {
        return playerService.updateName(id, name);
    }

    @GET
    @Path("/player")
    public List<Player> findAllPlayers() {
        return playerService.findAllPlayers();
    }

//    @GET
//    @Path("/player/{index}")
//    public List<Player> findPlayersByIndex(@PathParam(value = "index") String index) {
//        return playerService.findPlayersByPageIndex();
//    }
}