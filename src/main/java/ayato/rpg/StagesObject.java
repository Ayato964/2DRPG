package ayato.rpg;

import ayato.system.JsonComponent;
import com.fasterxml.jackson.databind.JsonNode;

public class StagesObject {
    private JsonNode node;
    private JsonNode states;
    public StagesObject(JsonNode node){
        this.node = node;
        states = node.get(JsonComponent.STATES);
    }

    public JsonNode getStates() {
        return states;
    }
}
