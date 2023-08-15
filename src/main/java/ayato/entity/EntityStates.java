package ayato.entity;

import ayato.rpg.Main;
import ayato.system.Inventory;

public class EntityStates {
    public int HP, MHP, LV, EXP, MP, MMP, G, ATK;
    public Inventory inventory;
    public int POW_CHANCE;
    public double DF;
    public String NAME;
    public EntityStates(String name, int lv, int hp, int exp, int mp, int G, int atk, int POW_CHANCE, double df){
        NAME = name;
        LV = lv;
        HP = hp;
        MHP = HP;
        EXP = exp;
        MP = mp;
        MMP = mp;
        this.G = G;
        ATK = atk;
        DF = Math.min(df, 1.0d);
        this.POW_CHANCE = Math.min(POW_CHANCE, 1000);
        inventory = new Inventory(Main.scene, this);
    }

    public void giveHP(int healSize) {
        HP += healSize;
        if(HP > MHP)
            HP = MHP;

    }
}
