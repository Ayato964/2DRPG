package ayato.system;

import ayato.entity.EntityStates;
import ayato.item.Consumers;
import ayato.item.Item;
import ayato.item.armors.Armor;
import ayato.item.armors.Necklace;
import ayato.item.armors.Ring;
import ayato.item.armors.Weapon;
import org.ayato.animation.*;
import org.ayato.system.Component;
import org.ayato.system.LunchScene;
import org.ayato.util.VoidSupplier;

import java.awt.*;
import java.util.ArrayList;

public class Inventory {
    private final ArrayList<Item> ITEMS;
    private Weapon WEAPON;
    private Armor ARMOR;
    private Ring RING;
    private Necklace NECKLACE;
    private AnimationKeyButtons<String, AnimationList<String, Properties>> LIST;
    private final EntityStates STATES;
    private final LunchScene MASTER;
    public Inventory(LunchScene scene, EntityStates entityStates){
        ITEMS = new ArrayList<>();
        MASTER = scene;
        STATES = entityStates;
    }

    public ArrayList<Item> getITEMS() {
        return ITEMS;
    }

    public void setARMOR(Armor ARMOR) {
        this.ARMOR = ARMOR;
        remove((Item) ARMOR);
    }

    public void setNECKLACE(Necklace NECKLACE) {
        this.NECKLACE = NECKLACE;
        remove((Item) NECKLACE);
    }

    public void setRING(Ring RING) {
        this.RING = RING;
        remove((Item) RING);
    }

    public void setWEAPON(Weapon WEAPON) {
        this.WEAPON = WEAPON;
        remove((Item) WEAPON);
    }

    public Armor getARMOR() {
        return ARMOR;
    }

    public Necklace getNECKLACE() {
        return NECKLACE;
    }

    public Weapon getWEAPON() {
        return WEAPON;
    }

    public Ring getRING() {
        return RING;
    }

    public void add(Item item){
        ITEMS.add(item);
    }
    public void remove(Item item){
        ITEMS.remove(item);
    }
    public void view(Consumers<Item,AbstractAnimations<?, ?>> action, VoidSupplier ac){
        if(ITEMS.isEmpty()){
            Animation.create(MASTER, AnimationComponent.ofText(""),
                            PropertiesTemplate.conv(iProperty -> ac.action(),
                                    ()-> Component.get(this, "not_item")), false)
                    .drawThisScene();
        }else {
            AnimationList<String, Properties> list =
                    new AnimationList<>(MASTER, PropertiesComponent.ofText().color(Color.WHITE));

            for (int i = 0; i < ITEMS.size(); i++) {
                int finalI = i;
                list.add(AnimationComponent.ofText(ITEMS.get(i).NAME), l -> {
                    action.accept(ITEMS.get(finalI), l);
                });
            }
            list.add(AnimationComponent.ofText(Component.get(this, "back")), l->{
                LIST.setVisible(false);
                ac.action();

            });
            LIST = new AnimationKeyButtons<>(list, 30, 10, 100, 100, Color.RED, Color.WHITE, Color.BLACK);
            LIST.setVisible(true);
        }
    }
    public void view(VoidSupplier ac){
        view((item, abstractAnimations) -> {
            item.use(MASTER, STATES);
            ITEMS.remove(item);
            LIST.setVisible(false);
            ac.action();
        },ac);
    }

}
