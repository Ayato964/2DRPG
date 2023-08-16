package ayato.scene;

import ayato.entity.PLayerStates;
import ayato.entity.Player;
import org.ayato.animation.*;
import org.ayato.system.Component;
import org.ayato.system.LunchScene;
import org.ayato.util.IBaseScene;

import java.awt.*;

public class PlayerStatesScene implements IBaseScene {
    public final Player PLAYER;
    public PlayerStatesScene(Player player) {
        PLAYER = player;
    }

    @Override
    public void display(Graphics graphics) {

    }

    @Override
    public void setup(LunchScene lunchScene) {
        Animation.create(lunchScene, AnimationComponent.ofText(((PLayerStates) PLAYER.getSTATES()).getString().get()),
                PropertiesComponent.ofText(10, 50)
                        .font(new Font("", Font.PLAIN, 32))
                        .color(Color.WHITE)
                        .center()
                , true);
        Animation.create(lunchScene, AnimationComponent.ofText(Component.get(this, "df_and_luck",
                        String.valueOf(PLAYER.getSTATES().DF),
                        String.valueOf(PLAYER.getSTATES().POW_CHANCE))),
                PropertiesComponent.ofText(10, 60)
                        .font(new Font("", Font.PLAIN, 32))
                        .color(Color.WHITE)
                        .center()
                , true);
        AnimationList<String, Properties> L = new AnimationList<>(lunchScene, PropertiesComponent.ofText()
                .font(new Font("", Font.PLAIN, 32))
                .color(Color.WHITE));
        L.add(AnimationComponent.ofText(Component.get(this, "back")), ()->lunchScene.changeScene(new Menu(PLAYER)));
        AnimationKeyButtons<String, AnimationList<String, Properties>> LL =
                new AnimationKeyButtons<>(L, 10, 60, 30, 20, Color.RED, Color.WHITE, Color.BLACK);
        LL.setVisible(true);

    }
}
