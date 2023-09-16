package ayato.item;

import ayato.effect.AttackBoost;
import ayato.entity.EntityStates;
import org.ayato.system.LunchScene;

public class AttackPotion extends Item{
    private AttackBoost effect;
    public AttackPotion(String name, AttackBoost atk, int gold) {
        super(name, gold);
        effect = atk;
    }

    @Override
    public void use(LunchScene MASTER, EntityStates entity) {
        entity.effects.add(effect);
        animationPop(MASTER, entity.NAME, NAME, 0);
    }
}
