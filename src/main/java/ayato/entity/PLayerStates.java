package ayato.entity;

import org.ayato.system.Component;

import java.util.Random;
import java.util.function.Supplier;

public class PLayerStates extends EntityStates {
    public int MEXP;
    public PLayerStates(String name, int lv, int hp, int exp, int mp, int G, int atk, int pow, double df) {
        super(name, lv, hp, exp, mp, G, atk, pow,  df);
        MEXP = 80;
    }

    public Supplier<String> getString() {
        return ()->Component.get(this, "player_states",
                String.valueOf(LV),
                NAME, String.valueOf(HP),
                String.valueOf(MHP),
                String.valueOf(MP),
                String.valueOf(MMP),
                String.valueOf(EXP),
                String.valueOf(MEXP),
                String.valueOf(G));
    }

    public void upgrade() {
        LV ++;
        MHP += 10 + bounus();
        MMP += 2 + new Random().nextInt(0, LV);
        ATK += 2 + new Random().nextInt(0, LV);
        DF += 0.004;
        POW_CHANCE += 4;

        HP += MHP;
        MP += MMP;
    }

    private int bounus() {
        double r = new Random().nextInt(0,1000);
        if(r < POW_CHANCE) {
            return new Random().nextInt(0, LV);
        }
            return 0;
    }
}
