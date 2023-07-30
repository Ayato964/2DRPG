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

public class Menu implements IBaseScene {
    Player player;
    public Menu(Player player){
        this.player = player;
    }

    @Override
    public void display(Graphics graphics) {

    }

    @Override
    public void setup(LunchScene lunchScene) {
        Animation.create(lunchScene, Component.get(this, "selection"),
                Properties.ofText(20, 20).font(new Font("", Font.PLAIN, 64)).color(Color.WHITE).center()
                ,true);
        AnimationList<String, Properties<String>> l =
                new AnimationList<>(lunchScene, Component.get(this, "battle"),
                        Properties.ofText().color(Color.WHITE)
                                .font(new Font("", Font.PLAIN, 32)),
                        ()-> lunchScene.changeScene(new ChooseMap(player))
                        );
        l.add(Component.get(this, "hotel"), ()-> lunchScene.changeScene(new InnScene(player)));
        l.add(Component.get(this, "shop"), ()-> System.out.println("Shop"));
        AnimationKeyButtons<String, AnimationList<String, Properties<String>>> list =
                new AnimationKeyButtons<>(l, 20, 40, 100, 50, Color.RED, Color.WHITE, Color.BLACK);
        list.setVisible(true);
    }
}
