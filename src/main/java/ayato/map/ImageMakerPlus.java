package ayato.map;

import org.ayato.animation.image.ImageMaker;

import java.awt.image.BufferedImage;

public class ImageMakerPlus extends ImageMaker {
    public static final int CHIP_W = 32;
    public static final int CHIP_H = 32;
    public ImageMakerPlus(String directory, String filename) {
        super(directory, filename);
    }

    public ImageMakerPlus(String directory, String filename, int w, int h) {
        super(directory, filename, w, h);
    }
    public BufferedImage getChip(int x, int y){
        BufferedImage image = (BufferedImage) getEditImage();
        return image.getSubimage(x * CHIP_W, y * CHIP_H, CHIP_W, CHIP_H);
    }
}
