package ayato.entity;

import org.ayato.animation.image.ImageMaker;
import org.ayato.system.LunchScene;

import java.util.Random;

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

    public ImageMaker getAVATER() {
        return AVATER;
    }

    public AbstractEntity setSTATES(EntityStates STATES) {
        this.STATES = STATES;
        return this;
    }

    public EntityStates getSTATES() {
        return STATES;
    }

    protected abstract void reset();

    public String  getHP() {
        return STATES.HP + "/" + STATES.MHP + "HP";
    }
    public String getLV() {
        return "LV." + STATES.LV;
    }
    public String getMP(){
        return STATES.MP + "/" + STATES.MMP + "MP";
    }

    public int generateATK() {
        int r = new Random().nextInt(1, 1000);
        System.out.println("USERNAME:: " + STATES.NAME + "  Random::" + r);
        if(r < getSTATES().POW_CHANCE)
            if(r < getSTATES().POW_CHANCE / 2) {
                return STATES.ATK * 2;
            }
        else
                return (int) (STATES.ATK * 1.5);
        return STATES.ATK;
    }

    public int recivedATK(int generateATK) {
        int l = (int) ( generateATK * (1 - STATES.DF));
        STATES.HP -= l;
        return l;
    }
}
