package ayato.system;

public interface ValueContainer {
    static final int ATK = 0;
    static final int DF = 1;
    static final int POW_CHANCE = 2;
    static final int AVOID = 3;
    static final int G = 4;
    static final int EXP = 5;

    public void set(int c, int v);
    public int count();
    public int get(int c);
    public void reset();
}
