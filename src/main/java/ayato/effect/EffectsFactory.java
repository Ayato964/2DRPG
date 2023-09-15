package ayato.effect;

import ayato.rpg.Main;
import org.ayato.system.RegistoryList;
import org.ayato.system.RegistoryObject;

public class EffectsFactory {
    public static final RegistoryList<Effect> EFFECTS =
            RegistoryList.create(Main.scene, "effects");

    public static final RegistoryObject<Effect> HEAL_SMALL =
            EFFECTS.create(()->new HealSmall( 3, 999999999), "heal_small");
    public static final RegistoryObject<Effect> ATTACK_BOOST_I =
            EFFECTS.create(()->new AttackBoost(1, 2, 1.2d), "attack_boost_i");
    public static final RegistoryObject<Effect> ATTACK_BOOST_II =
            EFFECTS.create(()->new AttackBoost(1, 2, 1.5d), "attack_boost_i");
    public static final RegistoryObject<Effect> ATTACK_BOOST_III =
            EFFECTS.create(()->new AttackBoost(1, 2, 2d), "attack_boost_i");
    public static final RegistoryObject<Effect> DEFENCE_BOOST_I =
            EFFECTS.create(()->new DeffenceBoost(1.2d), "defence_boost_i");
    public static final RegistoryObject<Effect> DEFENCE_BOOST_II =
            EFFECTS.create(()->new DeffenceBoost(1.5d), "defence_boost_ii");
    public static final RegistoryObject<Effect> DEFENCE_BOOST_III =
            EFFECTS.create(()->new DeffenceBoost(2.0d), "defence_boost_iii");
}
