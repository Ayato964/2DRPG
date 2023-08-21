package ayato.effect;

import ayato.entity.AbstractEntity;
import ayato.item.armors.IAccessories;
import ayato.system.ValueContainer;
import org.ayato.animation.image.ImageMaker;
import org.ayato.system.Component;
import org.ayato.system.LunchScene;
import org.ayato.util.VoidSupplier;

public abstract class Effect implements IAccessories {
    public static final int INFINITIES = 999999999;
    private final ImageMaker image;
    private final String NAME = Component.get(this, "name");
    private int interval, effect_turn;
    private final int max_interval;


    public Effect(ImageMaker image, int interval, int et) {
        this.image = image;
        this.interval = interval;
        effect_turn = et;
        max_interval = interval;
    }
    public void view(LunchScene scene, int x, int y, int w, int h){

    }
    public void lunch(LunchScene master, AbstractEntity entity){
        effectCondition(entity, ()->addEffect(master, entity));
    }



    private void effectCondition(AbstractEntity entity, VoidSupplier supplier){
        interval--;
        if(interval <= 0){
            supplier.action();
            effect_turn --;
            if(effect_turn <= 0){
                entity.getSTATES().effects.remove(this);
            }
            interval = max_interval;
        }
    }
    protected abstract void addEffect(LunchScene master, AbstractEntity entity);
    @Override
    public void effects(AbstractEntity entity, ValueContainer valueContainer) {}

}
