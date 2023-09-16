package ayato.item;

import ayato.effect.AttackBoost;
import ayato.effect.EffectsFactory;
import ayato.item.armors.Chest;
import ayato.item.armors.Sword;
import ayato.rpg.Main;
import org.ayato.system.Component;
import org.ayato.system.RegistoryList;
import org.ayato.system.RegistoryObject;

public class ItemFactory {
    public static final ItemFactory INSTANCE = new ItemFactory();
    /**
     * Healers
     */
    public static final RegistoryList<Item> ITEMS =
            RegistoryList.create(Main.scene, "item");

    public static final RegistoryObject<Item> GREEN_PLANTS =
            ITEMS.create(()-> new HealPotion(Component.get(INSTANCE, "medicinal_plants"), 10, 140), "medicinal_plants");


    public static final RegistoryObject<Item> POTION_SMALL =
            ITEMS.create(()-> new HealPotion(Component.get(INSTANCE, "potion_small"), 50, 700), "potion_small");

    public static final RegistoryObject<Item> POTION_MEDIUM =
            ITEMS.create(()-> new HealPotion(Component.get(INSTANCE, "potion_medium"), 100, 1200), "potion_medium");

    public static final RegistoryObject<Item> POTION_LARGE =
            ITEMS.create(()-> new HealPotion(Component.get(INSTANCE, "potion_large"), 150, 2080), "potion_large");
    public static final RegistoryObject<Item> EFFECT_HEAL_POTION_SMALL =
            ITEMS.create(()-> new HealEffectPotion(Component.get(INSTANCE, "e_potion_small"), 300), "e_potion_small");

    /**
     * Effect Potions
     *
     *
    */
    public static final RegistoryObject<Item> RED_PLANTS =
            ITEMS.create(()->new AttackPotion(Component.get(INSTANCE, "attack_plants"), (AttackBoost) EffectsFactory.ATTACK_BOOST.get(), 300), "attack_plants");

    public static final RegistoryObject<Item> ATTACK_POTION_SMALL =
        ITEMS.create(()->new AttackPotion(Component.get(INSTANCE, "attack_potion_small"), (AttackBoost) EffectsFactory.ATTACK_BOOST_I.get(), 500), "attack_potion_small");

    /**
     * Armors
     */
    public static final RegistoryObject<Item> WOOD_SWORD =
            ITEMS.create(()->new Sword(Component.get(INSTANCE, "wood_sword"), 15,2000), "wood_sword");
    public static final RegistoryObject<Item> STONE_SWORD =
            ITEMS.create(()->new Sword(Component.get(INSTANCE, "stone_sword"), 20,7000), "stone_sword");
    public static final RegistoryObject<Item> IRON_SWORD =
            ITEMS.create(()->new Sword(Component.get(INSTANCE, "iron_sword"), 25,12000), "iron_sword");
    public static final RegistoryObject<Item> DIAMOND_SWORD =
            ITEMS.create(()->new Sword(Component.get(INSTANCE, "diamond_sword"), 30,19800), "diamond_sword");

    public static final RegistoryObject<Item > COMMON_CHEST =
            ITEMS.create(()->new Chest(Component.get(INSTANCE, "common_chest"), 100, 2500), "common_chest");
    public static final RegistoryObject<Item > UNCOMMON_CHEST =
            ITEMS.create(()->new Chest(Component.get(INSTANCE, "uncommon_chest"), 200, 5500), "uncommon_chest");
    public static final RegistoryObject<Item > REA_CHEST =
            ITEMS.create(()->new Chest(Component.get(INSTANCE, "rea_chest"), 300, 8000), "rea_chest");
    public static final RegistoryObject<Item > EPIC_CHEST =
            ITEMS.create(()->new Chest(Component.get(INSTANCE, "epic_chest"), 400, 15000), "epic_chest");

}
