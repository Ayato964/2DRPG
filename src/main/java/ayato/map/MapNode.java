package ayato.map;

import ayato.entity.Player;
import com.fasterxml.jackson.databind.JsonNode;
import org.ayato.system.Background;
import org.ayato.system.LunchScene;
import org.ayato.util.IBaseScene;

import java.awt.*;

public class MapNode implements IBaseScene {
    private final Player player;
    private final MapGenerator GENERATOR;
    private final JsonNode[] LAYER;
    private MapChip[][] mapChips;
    public MapNode(Player player, MapGenerator generator, JsonNode mapData){
        this.player = player;
        GENERATOR = generator;
        LAYER = new JsonNode[3];
        for(int i = 0; i < 3; i ++)LAYER[i] = mapData.get("layer" + i);

        mapChips = new MapChip[3][MapGenerator.WEIGHT * MapGenerator.HEIGHT];

        for(int i = 0; i < 3; i ++)
            load(i);
    }
    private void load(int i){
        int c = 0;
        for(int y = 0; y < MapGenerator.HEIGHT; y ++){
            for(int x = 0; x < MapGenerator.WEIGHT; x ++){
                mapChips[i][c] = MapChip.create(LAYER[i].get(c).asInt(), x, y);
                c ++;
            }
        }
    }
    @Override
    public void display(Graphics graphics) {
        for(int i = 0; i < 3; i ++){
            drawChip(graphics, i);
        }
    }
    private void drawChip(Graphics g, int layer){
        for(MapChip c : mapChips[layer]){
            if(c != null)
                c.display(g);
        }
    }

    @Override
    public void setup(LunchScene lunchScene) {
        lunchScene.BACKGROUND.mode = Background.BackgroundMode.COLOR;
        lunchScene.BACKGROUND.mode.setColor(Color.BLACK);


    }
}
