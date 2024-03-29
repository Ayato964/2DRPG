package ayato.magic;

import ayato.rpg.Main;
import org.ayato.system.Component;
import org.ayato.system.RegistoryList;
import org.ayato.system.RegistoryObject;

public class MagicFactory {
    private static final MagicFactory INSTANCE = new MagicFactory();
    public static final RegistoryList<Magic> MAGICS =
            RegistoryList.create(Main.scene, "magic");

    public static final RegistoryObject<Magic> UNITY =
            MAGICS.create(()->new Unity(Component.get(INSTANCE, "name")), "unity");

    public static final RegistoryObject<Magic> UNITY_BREAKING =
            MAGICS.create(()->new UnityBreaking(Component.get(INSTANCE, "unity_breaking")), "unity_breaking");
    public static final RegistoryObject<Magic> ATTACK_BOOST =
            MAGICS.create(()-> new AttackBoost(Component.get(INSTANCE, "attack_boost")), "attack_boost");
    public static final RegistoryObject<Magic> DEFENCE_BOOST =
            MAGICS.create(()->new DefenceBoost(Component.get(INSTANCE, "defence_boost") ), "defence_boost");
}
