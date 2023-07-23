package ayato.entity;

import org.ayato.animation.image.ImageMaker;
import org.ayato.system.LunchScene;

public abstract class AbstractEntity {
    int X, Y, W, H;
    private ImageMaker AVATER;
    protected EntityStates STATES;
    protected LunchScene MASTER;
    protected AbstractEntity(LunchScene m, ImageMaker maker,  int x, int y, int w, int h){
        X = x;
        Y = y;
        W = w;
        H = h;
        MASTER = m;
        AVATER = maker;
    }

    public AbstractEntity setSTATES(EntityStates STATES) {
        this.STATES = STATES;
        return this;
    }

    public EntityStates getSTATES() {
        return STATES;
    }

    protected abstract void reset();
}
