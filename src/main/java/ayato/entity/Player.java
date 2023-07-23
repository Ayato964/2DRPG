package ayato.entity;

import org.ayato.system.LunchScene;

public class Player extends AbstractEntity{
    private int MEXP;
    public Player(LunchScene m, int x, int y, int w, int h) {
        super(m, null, x, y, w, h);
        reset();
    }


    @Override
    protected void reset() {
        setSTATES(new EntityStates("Player", 1, 20, 0, 20, 0, 0.0d));
        MEXP = 20;
    }
}
