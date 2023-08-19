package ayato.item.armors;

import ayato.entity.AbstractEntity;
import ayato.entity.EntityStates;
import ayato.item.Item;
import ayato.system.ValueContainer;
import org.ayato.system.LunchScene;

public class Chest extends Item implements Armor {
    private final int df;
    public Chest(String name, int df,  int gold) {
        super(name, gold);
        this.df = df;
    }

    @Override
    public void use(LunchScene MASTER, EntityStates entity) {
        notEffect(MASTER);
    }
    @Override
    public void effects(AbstractEntity entity, ValueContainer valueContainer) {
        valueContainer.set(ValueContainer.DF, valueContainer.get(ValueContainer.DF) + df);
    }
}
