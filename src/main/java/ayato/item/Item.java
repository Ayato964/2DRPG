package ayato.item;

import ayato.entity.EntityStates;
import ayato.system.PropertiesTemplate;
import org.ayato.animation.Animation;
import org.ayato.animation.AnimationComponent;
import org.ayato.system.Component;
import org.ayato.system.LunchScene;

public abstract class Item {

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

}
