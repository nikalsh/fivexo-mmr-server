package mmr.server.model;

import io.quarkus.mongodb.panache.MongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntityBase;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

import java.util.Objects;

@MongoEntity(collection = "Game")
public class Game extends PanacheMongoEntityBase {

    @BsonId
    public ObjectId id;
    public String winnerId = "";
    public String loserId = "";
    public Integer turns = 0;
    public String player1 = "";
    public String player2 = "";
    public Integer initiated = 0;

    public Game() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(id, game.id) &&
                Objects.equals(winnerId, game.winnerId) &&
                Objects.equals(loserId, game.loserId) &&
                Objects.equals(turns, game.turns) &&
                Objects.equals(player1, game.player1) &&
                Objects.equals(player2, game.player2) &&
                Objects.equals(initiated, game.initiated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, winnerId, loserId, turns, player1, player2, initiated);
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", winnerId='" + winnerId + '\'' +
                ", loserId='" + loserId + '\'' +
                ", turns=" + turns +
                ", player1='" + player1 + '\'' +
                ", player2='" + player2 + '\'' +
                '}';
    }
}