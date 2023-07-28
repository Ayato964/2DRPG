package ayato.scene;

import ayato.entity.Player;
import ayato.rpg.StagesFactory;
import org.ayato.animation.Animation;
import org.ayato.animation.AnimationKeyButtons;
import org.ayato.animation.AnimationList;
import org.ayato.animation.Properties;
import org.ayato.system.Component;
import org.ayato.system.LunchScene;
import org.ayato.util.IBaseScene;

import java.awt.*;

public class ChooseMap implements IBaseScene {
    Player player;
    public ChooseMap(Player player){
        this.player = player;
    }
    @Override
    public void display(Graphics graphics) {

    }

    @Override
    public void setup(LunchScene lunchScene) {
        Animation.create(lunchScene, Component.get(this, "title"),
                Properties.ofText(5, 10)
                        .font(new Font("", Font.PLAIN, 64)).center().color(Color.RED), true);

        AnimationList<String, Properties<String>> list =
                new AnimationList<>(lunchScene, "Normal Embust", Properties.ofText().font(new Font("", Font.PLAIN, 32)).center(),
                        ()-> lunchScene.changeScene(new Battle(player, StagesFactory.STAGE_0.get().getStates()))
                        );
        AnimationKeyButtons<String, AnimationList<String, Properties<String>>> alist =
                new AnimationKeyButtons<>(list, 15, 30, 100, 60, Color.RED, Color.WHITE, Color.BLACK);
        alist.setVisible(true);
    }
}
