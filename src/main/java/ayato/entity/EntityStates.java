package ayato.entity;

public class EntityStates {
    public int HP, MHP, LV, EXP, MP, MMP, G, ATK;
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
    }
}
