package mmr.server.model;

import io.quarkus.mongodb.panache.MongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntityBase;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

@MongoEntity(collection = "Game")
public class Game extends PanacheMongoEntityBase {

    @BsonId
    public ObjectId id;
    public String winnerId;
    public String loserId;
    public Integer turns;
    public String player1;
    public String player2;

    public Game() {
    }


}