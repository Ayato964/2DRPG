package ayato.item;

import ayato.entity.EntityStates;
import ayato.system.PropertiesTemplate;
import org.ayato.animation.Animation;
import org.ayato.animation.AnimationComponent;
import org.ayato.system.Component;
import org.ayato.system.LunchScene;

public abstract class Item {
    private static final Item INSTANCE = new Item(null, 0) {
        @Override
        public void use(LunchScene MASTER, EntityStates entity) {

        }
    };

    public final String NAME;
    public final int G;
    public Item(String name, int gold){
        NAME = name;
        G = gold;
    }
    public abstract void use(LunchScene MASTER, EntityStates entity);

    protected void animationPop(LunchScene MASTER, String playerName, String itemName, int value){
        Animation.create(MASTER, AnimationComponent.ofText(""), PropertiesTemplate.conv(null, ()-> Component.get(this, "use", playerName, itemName, String.valueOf(value))),
                false).drawThisScene();
    }
    protected void notEffect(LunchScene scene){
        Animation.create(scene, AnimationComponent.ofText(""), PropertiesTemplate.conv(null, ()->Component.get(INSTANCE, "not_effect")), false).drawThisScene();
    }

}
