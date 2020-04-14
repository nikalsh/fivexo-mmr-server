package mmr.server.websocket.encoders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import mmr.server.game.FiveInARowState;
import mmr.server.websocket.MatchmakingObject;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

public class ObjectToJson {

    public static String toJson(Object object) {
     ObjectMapper objectMapper = new ObjectMapper();

     String toReturn = null;
     try {
            toReturn = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return toReturn;
    }
}
