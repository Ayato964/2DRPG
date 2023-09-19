package ayato.map;

import ayato.rpg.Main;
import org.ayato.util.Display;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class MapChip implements Display {
    public static final int CHIP_WIDTH = Main.scene.FRAME.DESCTOP_BOUNDS.width / MapGenerator.WEIGHT;
    public static final int CHIP_HEIGHT = Main.scene.FRAME.DESCTOP_BOUNDS.height / MapGenerator.HEIGHT;
    private final int x, y;
    private BufferedImage image;
    protected MapChip(BufferedImage plus, int x, int y){
        this.x = x;
        this.y = y;
        image = plus;
    }
    public static MapChip create(int id, int x, int y){
        return switch (id){
            case 0->null;
            case 50->new Wall(Wall.Walls.UP,x, y);
            case 52->new Wall(Wall.Walls.RIGHT,x, y);
            case 54->new Wall(Wall.Walls.DOWN,x, y);
            case 56->new Wall(Wall.Walls.LEFT,x, y);
            case 60->new WaterCorner(WaterCorner.Direction.RIGHT_UP,x, y);
            case 61->new WaterCorner(WaterCorner.Direction.RIGHT_DOWN,x, y);
            case 62->new WaterCorner(WaterCorner.Direction.LEFT_DOWN,x, y);
            case 63->new WaterCorner(WaterCorner.Direction.LEFT_UP,x, y);
            case 2->new Plant(x, y);
            default -> throw new IllegalStateException("Unexpected value: " + id);
        };
    }

    @Override
    public void display(@NotNull Graphics graphics) {
        graphics.drawImage(image, x * CHIP_WIDTH, y * CHIP_HEIGHT, CHIP_WIDTH ,CHIP_HEIGHT, null);
    }
}
