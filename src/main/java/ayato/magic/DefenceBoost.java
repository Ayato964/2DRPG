package ayato.magic;

import ayato.effect.EffectsFactory;
import ayato.entity.AbstractEntity;
import ayato.entity.Enemy;
import ayato.system.Share;
import org.ayato.animation.text.properties.PropertyAction;
import org.ayato.system.Component;
import org.ayato.system.LunchScene;

public class DefenceBoost extends Magic{

    public DefenceBoost(String name) {
        super(name, 5, 5, 10);
    }

    @Override
    protected void skillAction(LunchScene scene, PropertyAction after, AbstractEntity self, AbstractEntity enemy, Enemy[] enemies) {
        self.getSTATES().effects.add(EffectsFactory.DEFENCE_BOOST_I.get());
        backMessage(scene, after,
                ()-> Component.get(Share.INSTANCE, "magic_use", self.getSTATES().NAME, name)
        );
    }
}
