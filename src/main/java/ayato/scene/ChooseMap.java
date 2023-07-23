package ayato.scene;

import ayato.entity.Player;
import org.ayato.animation.Animation;
import org.ayato.animation.AnimationList;
import org.ayato.animation.Properties;
import org.ayato.system.Component;
import org.ayato.system.LunchScene;
import org.ayato.util.IBaseScene;

import java.awt.*;

public class ChooseMap implements IBaseScene {
    @Override
    public void display(Graphics graphics) {

    }

    @Override
    public void setup(LunchScene lunchScene) {
        Animation.create(lunchScene, Component.get(this, "title"),
                Properties.ofText(5, 10)
                        .font(new Font("", Font.PLAIN, 64)).center().color(Color.RED), true);

        AnimationList<String, Properties<String>> list =
                new AnimationList<>(lunchScene, "", Properties.ofText().font(new Font("", Font.PLAIN, 32)).center(),
                        ()-> lunchScene.changeScene(new Battle(new Player(lunchScene, 0, 0, 0, 0)))
                        );
    }
}
