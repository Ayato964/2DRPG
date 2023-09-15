package ayato.item;

import ayato.rpg.Main;
import org.ayato.system.RegistoryList;
import org.ayato.system.RegistoryObject;

import java.util.ArrayList;

public class ShopMenuTemplate {
    public static final RegistoryList<ArrayList<Item>> ITEM_TEMPLATE =
            RegistoryList.create(Main.scene, "item_template");

    public static final RegistoryObject<ArrayList<Item>> ALL =
            ITEM_TEMPLATE.create(()->{
                ArrayList<Item> items = new ArrayList<>();
                items.add(ItemFactory.MEDICINAL_PLANTS.get());
                items.add(ItemFactory.POTION_SMALL.get());
                items.add(ItemFactory.POTION_MEDIUM.get());
                items.add(ItemFactory.POTION_LARGE.get());
                items.add(ItemFactory.EFFECT_HEAL_POTION_SMALL.get());
                items.add(ItemFactory.WOOD_SWORD.get());
                items.add(ItemFactory.STONE_SWORD.get());
                items.add(ItemFactory.IRON_SWORD.get());
                items.add(ItemFactory.DIAMOND_SWORD.get());
                items.add(ItemFactory.COMMON_CHEST.get());
                items.add(ItemFactory.UNCOMMON_CHEST.get());
                items.add(ItemFactory.REA_CHEST.get());
                items.add(ItemFactory.EPIC_CHEST.get());
                return items;
            }, "all");

    public static final RegistoryObject<ArrayList<Item>> HEALER =
            ITEM_TEMPLATE.create(()-> {
                ArrayList<Item> items = new ArrayList<>();
                items.add(ItemFactory.MEDICINAL_PLANTS.get());
                items.add(ItemFactory.POTION_SMALL.get());
                items.add(ItemFactory.POTION_MEDIUM.get());
                items.add(ItemFactory.POTION_LARGE.get());
                items.add(ItemFactory.EFFECT_HEAL_POTION_SMALL.get());
                items.add(ItemFactory.ATTACK_POTION_SMALL.get());

                return items;
                }, "healer");

    public static final RegistoryObject<ArrayList<Item>> ARMOR =
            ITEM_TEMPLATE.create(()->{
                ArrayList<Item> i = new ArrayList<>();
                i.add(ItemFactory.WOOD_SWORD.get());
                i.add(ItemFactory.STONE_SWORD.get());
                i.add(ItemFactory.IRON_SWORD.get());
                i.add(ItemFactory.DIAMOND_SWORD.get());
                i.add(ItemFactory.COMMON_CHEST.get());
                i.add(ItemFactory.UNCOMMON_CHEST.get());
                i.add(ItemFactory.REA_CHEST.get());
                i.add(ItemFactory.EPIC_CHEST.get());
                return i;
            }, "");

 }
