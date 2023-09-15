package ayato.effect;

import ayato.entity.AbstractEntity;
import ayato.system.ValueContainer;
import org.ayato.animation.image.ImageMaker;
import org.ayato.system.LunchScene;

public class AttackBoost extends Effect{
    private final double d;
    public AttackBoost(int interval, int et, double damage) {
        super(new ImageMaker("effect", "atk_up"), interval, et);
        d = damage;
    }

    @Override
    protected void addEffect(LunchScene master, AbstractEntity entity) {

    }

    @Override
    public void effects(AbstractEntity entity, ValueContainer valueContainer) {
        valueContainer.set(ValueContainer.ATK, (int) (valueContainer.get(ValueContainer.ATK) * d));
    }
}
