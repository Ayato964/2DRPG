package ayato.scene;

import ayato.entity.Player;
import org.ayato.system.LunchScene;
import org.ayato.util.IBaseScene;

import java.awt.*;

public class Village implements IBaseScene {
    private final Player player;
    public Village(Player player1){

        this.player = player1;
    }
    @Override
    public void display(Graphics graphics) {

    }

    @Override
    public void setup(LunchScene lunchScene) {

    }
}
