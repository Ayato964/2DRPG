package ayato.scene;

import ayato.entity.Player;
import ayato.system.Inn;
import org.ayato.system.LunchScene;
import org.ayato.util.IBaseScene;

import java.awt.*;

public class InnScene implements IBaseScene {
    private final Player player;
    public InnScene(Player p){
        player = p;
    }
    @Override
    public void display(Graphics graphics) {

    }

    @Override
    public void setup(LunchScene lunchScene) {
        Inn.stayInnWithConfirm(lunchScene, player, 500, iProperty -> {
            lunchScene.changeScene(new Menu(player));
        });
    }
}
