package ayato.map;

public class Wall extends MapChip{
    public enum Walls{
        UP,
        DOWN,
        LEFT,
        RIGHT
    }
    protected Wall(Walls d, int x, int y) {
        super(switch (d){

            case UP ->MapChipDataBase.INSTANCE.get("water").getChip(6, 2);
            case DOWN -> MapChipDataBase.INSTANCE.get("water").getChip(6, 0);
            case LEFT -> MapChipDataBase.INSTANCE.get("water").getChip(7, 1);
            case RIGHT -> MapChipDataBase.INSTANCE.get("water").getChip(5, 1);
        }, x, y);
    }
}
