package ayato.effect;

import ayato.entity.AbstractEntity;
import ayato.system.ValueContainer;
import org.ayato.animation.image.ImageMaker;
import org.ayato.system.LunchScene;

public class AttackBoostI extends Effect{
    public AttackBoostI() {
        super(new ImageMaker("effect", "heal"), 1, 5);
    }

    @Override
    protected void addEffect(LunchScene master, AbstractEntity entity) {

    }

    @Override
    public void effects(AbstractEntity entity, ValueContainer valueContainer) {
        valueContainer.set(ValueContainer.ATK, (int) (valueContainer.get(ValueContainer.ATK) * 1.2d));
    }
}
