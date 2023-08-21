package ayato.entity;

import ayato.system.Inventory;
import ayato.system.ValueContainer;
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
        Inventory inv = STATES.inventory;
        ValueContainer container = new ValueContainer() {
            public int atk = STATES.ATK, pow_chance = STATES.POW_CHANCE;
            @Override
            public void set(int c, int v) {
                switch (c){
                    case ATK -> atk = v;
                    case POW_CHANCE -> pow_chance = v;
                }
            }

            @Override
            public int count() {
                return 2;
            }

            @Override
            public int get(int c) {
                return switch (c){
                    case ATK -> atk;
                    case POW_CHANCE -> pow_chance;
                    default -> throw new IllegalStateException("Unexpected value: " + c);
                };
            }
        };
        if(inv.getWEAPON() != null)
            inv.getWEAPON().effects(this, container);
        if(inv.getRING() != null)
            inv.getRING().effects(this, container);
        int r = new Random().nextInt(1, 1000);
        if(r < container.get(ValueContainer.POW_CHANCE))
            if (r < container.get(ValueContainer.POW_CHANCE) / 2)
                return container.get(ValueContainer.ATK) * 2;
            else
                return (int) (container.get(ValueContainer.ATK) * 1.5);
        return container.get(ValueContainer.ATK);
    }

    public int recivedATK(int generateATK) {
        Inventory inv = STATES.inventory;
        ValueContainer container = new ValueContainer() {
            int df = STATES.DF;
            @Override
            public void set(int c, int v) {
                if(c == DF)
                    df = v;
            }

            @Override
            public int count() {
                return 1;
            }

            @Override
            public int get(int c) {
                return c == DF ? df : -1;
            }
        };

        if(inv.getARMOR() != null)
            inv.getARMOR().effects(this, container);
        if(inv.getNECKLACE() != null)
            inv.getNECKLACE().effects(this, container);

        double d = container.get(ValueContainer.DF) / 1000d;
        int l = (int) ( generateATK * (1 - d));
        STATES.HP -= l;
        return l;
    }
}
