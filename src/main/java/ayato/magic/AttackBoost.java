package ayato.magic;

import ayato.effect.EffectsFactory;
import ayato.entity.AbstractEntity;
import ayato.entity.Enemy;
import org.ayato.animation.text.properties.PropertyAction;
import org.ayato.system.Component;
import org.ayato.system.LunchScene;

public class AttackBoost extends Magic{

    public AttackBoost(String name) {
        super(name, 5, 5, 10);
    }

    @Override
    protected void skillAction(LunchScene scene, PropertyAction after, AbstractEntity self, AbstractEntity enemy, Enemy[] enemies) {
        self.getSTATES().effects.add(EffectsFactory.ATTACK_BOOST_I.get());
        backMessage(scene, after,
                ()-> Component.get(this, "use", self.getSTATES().NAME, name)
                );
    }
}
