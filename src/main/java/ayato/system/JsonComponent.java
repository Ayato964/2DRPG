package ayato.system;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonComponent {
    public static final String STATES = "states";
    public static final String ENEMY = "enemy";
    public static final String ENEMY_LV = "lv";
    public static final String ENEMY_MAXLV = "maxLv";
    public static final String ENEMY_NUM = "num";
    public static final String ENEMY_ENTITIES = "entities";
    public static JsonNode get(String folder, String name){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readTree(JsonComponent.class.getClassLoader().getResource("assets/ayato/data/" + folder + "/" + name + ".json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
