package ayato.scene;

import ayato.entity.Player;
import org.ayato.animation.Animation;
import org.ayato.animation.AnimationKeyButtons;
import org.ayato.animation.AnimationList;
import org.ayato.animation.Properties;
import org.ayato.system.Component;
import org.ayato.system.LunchScene;
import org.ayato.util.IBaseScene;

import java.awt.*;

public class Title implements IBaseScene {
    @Override
    public void display(Graphics graphics) {

    }

    @Override
    public void setup(LunchScene executeScene) {
        executeScene.BACKGROUND.mode.setColor(Color.BLACK);
        Animation.create(executeScene, Component.get(this, "title"),  Properties.ofText(20, 20)
                .font(new Font("", Font.PLAIN, 32)).color(Color.RED).center(), true
        );

        AnimationList<String, Properties<String>> list = new AnimationList<>(executeScene, Component.get(this, "begin"),
                Properties.ofText().font(new Font("", Font.PLAIN, 32)),
                () -> executeScene.changeScene(new Menu(new Player(executeScene, 0, 0, 0, 0))));

        list.add(Component.get(this, "exit"), () -> System.exit(-1));

        AnimationKeyButtons<String, AnimationList<String, Properties<String>>> keyList =new AnimationKeyButtons<>(list, 20, 60, 120, 50, Color.RED, Color.WHITE, Color.BLACK);

        keyList.setVisible(true);
    }
}
