package ayato.scene;

import ayato.entity.Player;
import org.ayato.animation.*;
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
        Animation.create(executeScene,AnimationComponent.ofText( Component.get(this, "title")),  PropertiesComponent.ofText(20, 20)
                .font(new Font("", Font.PLAIN, 32)).color(Color.RED).center(), true
        );

        AnimationList<String, Properties> list = new AnimationList<>(executeScene,
                PropertiesComponent.ofText().font(new Font("", Font.PLAIN, 32)));

        list.add(AnimationComponent.ofText(Component.get(this, "begin")), () -> executeScene.changeScene(new Menu(new Player(executeScene, 0, 0, 0, 0))));
        list.add(AnimationComponent.ofText(Component.get(this, "exit")), () -> System.exit(-1));

        AnimationKeyButtons<String, AnimationList<String, Properties>> keyList =new AnimationKeyButtons<>(list, 20, 60, 120, 50, Color.RED, Color.WHITE, Color.BLACK);

        keyList.setVisible(true);
    }
}
