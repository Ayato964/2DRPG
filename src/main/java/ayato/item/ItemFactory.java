package ayato.item;

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

    public static final RegistoryObject<Item> POTION_SMALL =
            ITEMS.create(()-> new HealPotion(Component.get(INSTANCE, "potion_small"), 50, 500), "potion_small");

    public static final RegistoryObject<Item> POTION_MEDIUM =
            ITEMS.create(()-> new HealPotion(Component.get(INSTANCE, "potion_medium"), 100, 980), "potion_medium");

    public static final RegistoryObject<Item> POTION_LARGE =
            ITEMS.create(()-> new HealPotion(Component.get(INSTANCE, "potion_large"), 150, 1400), "potion_large");

    /**
     * Armors
     */
    public static final RegistoryObject<Item> WOOD_SWORD =
            ITEMS.create(()->new Sword(Component.get(INSTANCE, "wood_sword"), 10,2500), "wood_sword");
    public static final RegistoryObject<Item> STONE_SWORD =
            ITEMS.create(()->new Sword(Component.get(INSTANCE, "stone_sword"), 16,5000), "stone_sword");
    public static final RegistoryObject<Item> IRON_SWORD =
            ITEMS.create(()->new Sword(Component.get(INSTANCE, "iron_sword"), 22,7500), "iron_sword");
    public static final RegistoryObject<Item> DIAMOND_SWORD =
            ITEMS.create(()->new Sword(Component.get(INSTANCE, "diamond_sword"), 28,10000), "diamond_sword");

}
