package ayato.entity;

import ayato.effect.Effect;
import ayato.item.Item;
import ayato.system.Debug;
import ayato.system.Inventory;
import ayato.system.ValueContainer;
import org.ayato.animation.image.ImageMaker;
import org.ayato.system.LunchScene;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class AbstractEntity {
    int X, Y, W, H;
    private ImageMaker AVATER;
    protected EntityStates STATES;
    protected LunchScene MASTER;
    protected ValueContainer container = new ValueContainer() {
        public int atk, pow_chance;
        public int df, avoid;

        @Override
        public void reset() {
            atk = STATES.ATK;
            pow_chance = STATES.POW_CHANCE;
            df = STATES.DF;
            avoid = STATES.AVOID;
        }

        @Override
        public void set(int c, int v) {
            switch (c){
                case ATK -> atk = v;
                case POW_CHANCE -> pow_chance = v;
                case DF -> df = v;
                case AVOID -> avoid = v;
            }
        }

        @Override
        public int count() {
            return 4;
        }

        @Override
        public int get(int c) {
            return switch (c){
                case ATK -> atk;
                case POW_CHANCE -> pow_chance;
                case DF -> df;
                case AVOID -> avoid;
                default -> throw new IllegalStateException("Unexpected value: " + c);
            };
        }
    };

    protected AbstractEntity(LunchScene m, ImageMaker maker,  int x, int y, int w, int h){
        X = x;
        Y = y;
        W = w;
        H = h;
        MASTER = m;
        AVATER = maker;
    }
    public CopyOnWriteArrayList<Effect> getEffects(){
        return STATES.effects;
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

    public void runEffect(){
        for(Effect e : STATES.effects){
            e.lunch(MASTER, this);
        }
    }
    public void runReinEffect(ValueContainer container){
        for(Effect e : STATES.effects){
            e.effects(this, container);
        }
    }

    public int generateATK() {
        container.reset();
        Inventory inv = STATES.inventory;

        if(inv.getWEAPON() != null)
            inv.getWEAPON().effects(this, container);
        if(inv.getRING() != null)
            inv.getRING().effects(this, container);
        runReinEffect(container);
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

        if(inv.getARMOR() != null)
            inv.getARMOR().effects(this, container);
        if(inv.getNECKLACE() != null)
            inv.getNECKLACE().effects(this, container);
        runReinEffect(container);

        double d = container.get(ValueContainer.DF) / 1000d;
        int r = new Random().nextInt(0, 1000);
        Debug.method(()-> System.out.println(STATES.NAME + "   avoid:" + r));
        int l = (int) ( generateATK * (1 - d));
        if(r > container.get(ValueContainer.AVOID)) {
            STATES.HP -= l;
            return l;
        }else 
            return 0;
    }

    public void addItemAll(ArrayList<Item> sumItem) {
        for(Item i : sumItem){
            STATES.inventory.add(i);
        }
    }

    public void useMana(int mana) {
        STATES.MP -= mana;
    }
}
