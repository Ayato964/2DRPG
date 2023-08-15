package ayato.item;

import ayato.rpg.Main;
import org.ayato.system.RegistoryList;
import org.ayato.system.RegistoryObject;

import java.util.ArrayList;

public class ShopMenuTemplate {
    public static final RegistoryList<ArrayList<Item>> ITEM_TEMPLATE =
            RegistoryList.create(Main.scene, "item_template");

    public static final RegistoryObject<ArrayList<Item>> HEALER =
            ITEM_TEMPLATE.create(()-> {
                ArrayList<Item> items = new ArrayList<>();
                items.add(ItemFactory.POTION_SMALL.get());
                items.add(ItemFactory.POTION_MEDIUM.get());
                items.add(ItemFactory.POTION_LARGE.get());
                return items;
                }, "healer");

}