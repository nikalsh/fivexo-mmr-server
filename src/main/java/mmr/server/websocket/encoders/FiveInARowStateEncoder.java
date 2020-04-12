package mmr.server.websocket.encoders;

import mmr.server.game.FiveInARowState;

import javax.json.Json;
import javax.json.JsonWriter;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class FiveInARowStateEncoder {

    public static String toJson(FiveInARowState fiveInARowState) {
        Jsonb jsonb = JsonbBuilder.create();
        String jsonString = jsonb.toJson(fiveInARowState);
        return jsonString;
    }
}
