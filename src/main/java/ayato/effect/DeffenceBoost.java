package ayato.effect;

import ayato.entity.AbstractEntity;
import ayato.system.ValueContainer;
import org.ayato.animation.image.ImageMaker;
import org.ayato.system.LunchScene;

public class DeffenceBoost extends Effect {

    final double power;
    public DeffenceBoost( double power) {
        super(new ImageMaker("effect", "heal"), 1, 5);
        this.power = power;
    }

    @Override
    protected void addEffect(LunchScene master, AbstractEntity entity) {

    }

    @Override
    public void effects(AbstractEntity entity, ValueContainer valueContainer) {
        valueContainer.set(ValueContainer.DF, (int) (valueContainer.get(ValueContainer.DF) * power));
    }
}
