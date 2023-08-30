package ayato.item.armors;

import ayato.entity.AbstractEntity;
import ayato.entity.EntityStates;
import ayato.item.Item;
import ayato.system.ValueContainer;
import org.ayato.system.LunchScene;

public class Sword extends Item implements Weapon {
    private final double ATK;
    public Sword(String name, int atk,  int gold) {
        super(name, gold);
        ATK = atk / 10d;
    }

    @Override
    public void use(LunchScene MASTER, EntityStates entity) {
        notEffect(MASTER);
    }

    @Override
    public void effects(AbstractEntity entity, ValueContainer valueContainer) {
        valueContainer.set(ValueContainer.ATK, (int) (valueContainer.get(ValueContainer.ATK) * ATK));
    }
}
