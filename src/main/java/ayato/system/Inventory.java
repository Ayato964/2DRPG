package ayato.system;

import ayato.entity.EntityStates;
import ayato.item.Item;
import org.ayato.animation.*;
import org.ayato.system.Component;
import org.ayato.system.LunchScene;
import org.ayato.util.VoidSupplier;

import java.awt.*;
import java.util.ArrayList;

public class Inventory {
    private final ArrayList<Item> ITEMS;
    private AnimationKeyButtons<String, AnimationList<String, Properties>> LIST;
    private final EntityStates STATES;
    private final LunchScene MASTER;
    public Inventory(LunchScene scene, EntityStates entityStates){
        ITEMS = new ArrayList<>();
        MASTER = scene;
        STATES = entityStates;
    }
    public void add(Item item){
        ITEMS.add(item);
    }
    public void remove(Item item){
        ITEMS.remove(item);
    }
    public void view(VoidSupplier ac){
        if(ITEMS.isEmpty()){
            Animation.create(MASTER, AnimationComponent.ofText(""),
                    PropertiesTemplate.conv(iProperty -> ac.action(),
                            ()->Component.get(this, "not_item")), false)
                    .drawThisScene();
        }else {
            AnimationList<String, Properties> list =
                    new AnimationList<>(MASTER, PropertiesComponent.ofText().color(Color.WHITE));

            for (int i = 0; i < ITEMS.size(); i++) {
                int finalI = i;
                list.add(AnimationComponent.ofText(ITEMS.get(i).NAME), () -> {
                    ITEMS.get(finalI).use(MASTER, STATES);
                    ITEMS.remove(ITEMS.get(finalI));
                    LIST.setVisible(false);
                    ac.action();
                });
            }
            list.add(AnimationComponent.ofText(Component.get(this, "back")), ()->{
                LIST.setVisible(false);
                ac.action();

            });
            LIST = new AnimationKeyButtons<>(list, 30, 10, 100, 100, Color.RED, Color.WHITE, Color.BLACK);
            LIST.setVisible(true);
        }
    }

}
