package ayato.entity;

import org.ayato.animation.image.ImageMaker;
import org.ayato.system.LunchScene;

public class Enemy extends AbstractEntity{
    protected Enemy(LunchScene m, ImageMaker maker, int x, int y, int w, int h) {
        super(m, maker, x, y, w, h);
    }

    @Override
    protected void reset() {

    }
}
