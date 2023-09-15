package ayato.item;

import ayato.effect.EffectsFactory;
import ayato.entity.EntityStates;
import org.ayato.system.LunchScene;

public class AttackPotion extends Item{
    public AttackPotion(String name, int gold) {
        super(name, gold);
    }

    @Override
    public void use(LunchScene MASTER, EntityStates entity) {
        entity.effects.add(EffectsFactory.ATTACK_BOOST_I.get());

        animationPop(MASTER, entity.NAME, NAME, 0);
    }
}
