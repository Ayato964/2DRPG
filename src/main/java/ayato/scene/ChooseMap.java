package ayato.scene;

import ayato.entity.Player;
import ayato.rpg.StagesFactory;
import org.ayato.animation.*;
import org.ayato.animation.image.ImageMaker;
import org.ayato.system.Background;
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
        lunchScene.BACKGROUND.mode = Background.BackgroundMode.IMAGE;
        lunchScene.BACKGROUND.mode.setImage(new ImageMaker("background", "map"));
        Animation.create(lunchScene, AnimationComponent.ofText(Component.get(this, "title")),
                PropertiesComponent.ofText(5, 10)
                        .font(new Font("", Font.PLAIN, 64)).center().color(Color.RED), true);

        AnimationList<String, Properties> list =
                new AnimationList<>(lunchScene, PropertiesComponent.ofText().font(new Font("", Font.PLAIN, 32)).center());
        if(player.getSTATES().LV < 2) {
            list.add(AnimationComponent.ofText(Component.get(this, "tutorial")),
                    l -> lunchScene.changeScene(new Battle(player, StagesFactory.TUTORIAL.get().getStates())));
        }

        list.add(AnimationComponent.ofText(Component.get(this, "slime")),
                l-> lunchScene.changeScene(new Battle(player, StagesFactory.STAGE_0.get().getStates())));
        list.add(AnimationComponent.ofText(Component.get(this, "slime_forest")), l->lunchScene.changeScene(new Battle(player, StagesFactory.SLIME_FOREST.get().getStates())));
        list.add(AnimationComponent.ofText(Component.get(this, "cave_entrance")), l->lunchScene.changeScene(new Battle(player, StagesFactory.CAVE_ENTRANCE.get().getStates())));
        list.add(AnimationComponent.ofText(Component.get(this, "back")), l->lunchScene.changeScene(new Menu(player)));
        AnimationKeyButtons<String, AnimationList<String, Properties>> alist =
                new AnimationKeyButtons<>(list, 0, 30, 100, 60, Color.RED, Color.WHITE, Color.BLACK);
        alist.setVisible(true);
    }
}
