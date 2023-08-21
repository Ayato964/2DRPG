package ayato.effect;

import ayato.entity.AbstractEntity;
import ayato.system.PropertiesTemplate;
import org.ayato.animation.Animation;
import org.ayato.animation.AnimationComponent;
import org.ayato.animation.image.ImageMaker;
import org.ayato.system.Component;
import org.ayato.system.LunchScene;

public class HealSmall extends Effect {
    private int heal = 10;

    public HealSmall(int interval, int t) {
        super(new ImageMaker("effect", "heal"), interval, t);
    }

    @Override
    public void addEffect(LunchScene scene, AbstractEntity entity) {
        entity.getSTATES().giveHP(heal);
        Animation.create(scene, AnimationComponent.ofText(""), PropertiesTemplate.conv(
                null,
                ()-> Component.get(this, "mes", entity.getSTATES().NAME, String.valueOf(heal))
        ), false).drawThisScene();
    }
}
