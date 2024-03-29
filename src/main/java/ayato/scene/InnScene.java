package ayato.scene;

import ayato.entity.Player;
import ayato.system.Inn;
import org.ayato.animation.Animation;
import org.ayato.animation.AnimationComponent;
import org.ayato.animation.PropertiesComponent;
import org.ayato.animation.image.ImageMaker;
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
        lunchScene.BACKGROUND.mode.setImage(new ImageMaker("background", "hotel"));
        Animation.create(lunchScene, AnimationComponent.ofText(player.getSTATES().NAME + ": " + player.getSTATES().HP + "/" + player.getSTATES().MHP + "HP"),
                PropertiesComponent.ofText(120, 60)
                        .font(new Font("", Font.PLAIN, 32))
                        .color(Color.WHITE)
                , true);
        Inn.stayInnWithConfirm(lunchScene, player, 600, iProperty -> {
            lunchScene.changeScene(new Village(player));
        });
    }
}
