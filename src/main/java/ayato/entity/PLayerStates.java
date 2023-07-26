package ayato.entity;

import org.ayato.system.Component;

import java.util.function.Supplier;

public class PLayerStates extends EntityStates {
    public int MEXP;
    public PLayerStates(String name, int lv, int hp, int exp, int mp, int G, int atk, double df) {
        super(name, lv, hp, exp, mp, G, atk, df);
        MEXP = 200;
    }

    public Supplier<String> getString() {
        return ()->Component.get(this, "player_states",
                NAME, String.valueOf(HP),
                String.valueOf(MHP),
                String.valueOf(MP),
                String.valueOf(MMP),
                String.valueOf(EXP),
                String.valueOf(MEXP),
                String.valueOf(G));
    }
}
