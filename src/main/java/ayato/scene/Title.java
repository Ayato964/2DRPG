package ayato.scene;

import org.ayato.animation.text.AnimationText;
import org.ayato.animation.text.properties.TextProperties;
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
        AnimationText.create(executeScene, Component.get(this, "hello"), 20, 20, new TextProperties()
                .font(new Font("", 0, 32)).color(Color.RED).center()
        );
    }
}
