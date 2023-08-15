package ayato.item;

import ayato.rpg.Main;
import org.ayato.system.Component;
import org.ayato.system.RegistoryList;
import org.ayato.system.RegistoryObject;

public class ItemFactory {
    public static final ItemFactory INSTANCE = new ItemFactory();
    public static final RegistoryList<Item> ITEMS =
            RegistoryList.create(Main.scene, "item");

    public static final RegistoryObject<Item> POTION_SMALL =
            ITEMS.create(()-> new HealPotion(Component.get(INSTANCE, "potion_small"), 50, 500), "potion_small");

    public static final RegistoryObject<Item> POTION_MEDIUM =
            ITEMS.create(()-> new HealPotion(Component.get(INSTANCE, "potion_medium"), 100, 980), "potion_medium");

    public static final RegistoryObject<Item> POTION_LARGE =
            ITEMS.create(()-> new HealPotion(Component.get(INSTANCE, "potion_large"), 150, 1400), "potion_large");
}
