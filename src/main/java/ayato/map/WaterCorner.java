package ayato.map;

public class WaterCorner extends MapChip{
    enum Direction{
        RIGHT_UP,
        LEFT_UP,
        RIGHT_DOWN,
        LEFT_DOWN
    }
    protected WaterCorner(Direction d, int x, int y) {
        super(switch (d){

            case RIGHT_UP ->MapChipDataBase.INSTANCE.get("water").getChip(5, 3);
            case LEFT_UP -> MapChipDataBase.INSTANCE.get("water").getChip(4, 3);
            case RIGHT_DOWN -> MapChipDataBase.INSTANCE.get("water").getChip(5, 4);
            case LEFT_DOWN -> MapChipDataBase.INSTANCE.get("water").getChip(4, 4);
        }, x, y);
    }
}
