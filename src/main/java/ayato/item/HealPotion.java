package ayato.item;

import ayato.entity.EntityStates;
import org.ayato.system.LunchScene;

public class HealPotion extends Item {
    private final int healSize;
    public HealPotion(String name, int healSize, int gold){
        super(name, gold);
        this.healSize = healSize;
    }
    @Override
    public void use(LunchScene MASTER, EntityStates entity) {
        entity.giveHP(healSize);
        animationPop(MASTER, entity.NAME, NAME, healSize);
    }
}
