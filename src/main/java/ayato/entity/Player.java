package ayato.entity;

import org.ayato.system.LunchScene;

public class Player extends AbstractEntity{
    public Player(LunchScene m, int x, int y, int w, int h) {
        super(m, null, x, y, w, h);
        reset();
    }


    @Override
    protected void reset() {
        setSTATES(new PLayerStates("Player", 1, 30, 0, 20, 500, 5, 50, 0.0d));
    }

    public void addGold(int sumG) {
        STATES.G += sumG;
    }

    public void addEXP(int sumEXP) {
        STATES.EXP += sumEXP;
        levelUp();
    }
    private void levelUp(){
        PLayerStates s = (PLayerStates) STATES;
        if(STATES.EXP >= s.MEXP){
            STATES.EXP -= s.MEXP;
            s.MEXP += 200 * STATES.LV;
            s.upgrade();
            levelUp();
        }
    }
}
