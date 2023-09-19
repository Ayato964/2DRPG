package ayato.map;

import java.util.HashMap;

public class MapChipDataBase {
    private final HashMap<String, ImageMakerPlus> MAP_CHIP = new HashMap<>();
    public static final MapChipDataBase INSTANCE = new MapChipDataBase();
    private MapChipDataBase(){
        MAP_CHIP.put("grass", new ImageMakerPlus("map", "grass"));
        MAP_CHIP.put("water", new ImageMakerPlus("map", "water"));
    }
    public ImageMakerPlus get(String key){
        return MAP_CHIP.get(key);
    }

}
