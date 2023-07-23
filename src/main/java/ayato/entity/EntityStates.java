package ayato.entity;

public class EntityStates {
    public int HP, MHP, LV, EXP, MP, MMP, G;
    public double DF;
    public String NAME;
    public EntityStates(String name, int lv, int hp, int exp, int mp, int G, double df){
        NAME = name;
        LV = lv;
        HP = hp;
        MHP = HP;
        EXP = exp;
        MP = mp;
        MMP = mp;
        this.G = G;
        DF = Math.min(df, 1.0d);
    }
}
