package ayato.map;

public class Plant extends MapChip{
    protected Plant(int x, int y) {
        super(MapChipDataBase.INSTANCE.get("grass").getChip(6,1),x, y);
    }
}
