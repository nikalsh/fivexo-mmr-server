package mmr.server.model;


import io.quarkus.mongodb.panache.MongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntityBase;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

@MongoEntity(collection = "Player")
public class Player extends PanacheMongoEntityBase {

    @BsonId
    public ObjectId id;
    public int wins = 0;
    public int losses = 0;
    public int ties = 0;
    public int experience = 1;
    public int level = 1;
    public String name = "Anonymous";

    public Player() {
    }

    @Override
    public String toString() {
        return "Player{" +
                "wins=" + wins +
                ", losses=" + losses +
                ", ties=" + ties +
                ", experience=" + experience +
                ", name= " + name +
                ", level= " + level +
                ", id= " + id.toString() +
                '}';
    }

}