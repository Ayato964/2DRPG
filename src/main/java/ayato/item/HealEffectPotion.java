package ayato.item;

import ayato.effect.HealSmall;
import ayato.entity.EntityStates;
import ayato.system.PropertiesTemplate;
import org.ayato.animation.Animation;
import org.ayato.animation.AnimationComponent;
import org.ayato.system.Component;
import org.ayato.system.LunchScene;

public class HealEffectPotion extends Item{
    public HealEffectPotion(String name, int gold) {
        super(name, gold);
    }

    @Override
    public void use(LunchScene MASTER, EntityStates entity) {
        entity.effects.add(new HealSmall(2, 5));
        Animation.create(MASTER, AnimationComponent.ofText(""), PropertiesTemplate.conv(
                null,
                ()->Component.get(this, "mes")
        ), false).drawThisScene();
    }
}
