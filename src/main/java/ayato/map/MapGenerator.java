package ayato.map;

import ayato.entity.Player;
import ayato.system.JsonComponent;
import com.fasterxml.jackson.databind.JsonNode;
import org.ayato.system.LunchScene;

public class MapGenerator{
    public static final int WEIGHT = 15;
    public static final int HEIGHT = 10;
    private final Player player;
    private final JsonNode STAGE_DATA;
    private final int CHUNK_WEIGHT, CHUNK_HEIGHT;
    private MapNode[][] CHUNK;
    private MapNode HOST, GOAL;
    public MapGenerator(LunchScene scene, Player player, JsonNode stageData){
        this.player = player;
        STAGE_DATA = stageData;
        CHUNK_HEIGHT = STAGE_DATA.get(JsonComponent.STATES).get(JsonComponent.STAGE).get("size").get("height").asInt();
        CHUNK_WEIGHT = STAGE_DATA.get(JsonComponent.STATES).get(JsonComponent.STAGE).get("size").get("weight").asInt();
        CHUNK = new MapNode[CHUNK_WEIGHT][CHUNK_HEIGHT];
        generateHostChunk();
        scene.changeScene(HOST);

    }
    private void generateHostChunk() {
        CHUNK[0][0] = new MapNode(player, this, JsonComponent.get("map", "test"));
        HOST = CHUNK[0][0];
    }
}
