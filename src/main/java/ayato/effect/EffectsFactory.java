package ayato.effect;

import ayato.rpg.Main;
import org.ayato.system.RegistoryList;
import org.ayato.system.RegistoryObject;

public class EffectsFactory {
    public static final RegistoryList<Effect> EFFECTS =
            RegistoryList.create(Main.scene, "effects");

    public static final RegistoryObject<Effect> HEAL_SMALL =
            EFFECTS.create(()->new HealSmall( 3, 999999999), "heal_small");
}
