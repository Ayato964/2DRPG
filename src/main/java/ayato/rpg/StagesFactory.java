package ayato.rpg;

import ayato.system.JsonComponent;
import org.ayato.system.RegistoryList;
import org.ayato.system.RegistoryObject;

public class StagesFactory {
    public static final RegistoryList<StagesObject> STAGES =
            RegistoryList.create(Main.scene, "stage");

    public static final RegistoryObject<StagesObject> TUTORIAL =
            STAGES.create(()-> new StagesObject(JsonComponent.get("stage", "tutorial")), "tutorial");

    public static final RegistoryObject<StagesObject> STAGE_0 =
            STAGES.create(()-> new StagesObject(JsonComponent.get("stage", "stage0")), "stage0");
    public static final RegistoryObject<StagesObject> SLIME_FOREST =
            STAGES.create(()-> new StagesObject(JsonComponent.get("stage", "slime_forest")), "slime_forest");
    public static final RegistoryObject<StagesObject> CAVE_ENTRANCE =
            STAGES.create(()->new StagesObject(JsonComponent.get("stage", "cave_entrance")), "cave_entrance");
}
