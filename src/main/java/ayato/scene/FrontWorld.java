package ayato.scene;

import ayato.entity.Player;
import ayato.rpg.Main;
import ayato.rpg.StagesFactory;
import org.ayato.animation.*;
import org.ayato.animation.image.ImageMaker;
import org.ayato.system.Background;
import org.ayato.system.Component;
import org.ayato.system.LunchScene;
import org.ayato.util.IBaseScene;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FrontWorld implements IBaseScene, KeyListener {
    private final Player player;
    public FrontWorld(Player player1){
        this.player = player1;
    }
    @Override
    public void display(Graphics graphics) {

    }
    @Override
    public void setup(@NotNull LunchScene lunchScene) {
        lunchScene.BACKGROUND.mode = Background.BackgroundMode.IMAGE;
        lunchScene.BACKGROUND.mode.setImage(new ImageMaker("background", "map"));
        lunchScene.FRAME.addKeyListener(this);

        Animation.create(lunchScene, AnimationComponent.ofText(Component.get(this, "selection")),
                PropertiesComponent.ofText(0, 5)
                        .color(Color.RED)
                        .font(new Font("", Font.PLAIN, 28))
                        .center()
                        .centerFrame(50, 10, ()->Color.WHITE, Color.BLACK)
                , true);
        chooseMap(lunchScene);


    }
    private void chooseMap(LunchScene scene){
        AnimationList<String, Properties> L =
                new AnimationList<>(scene, PropertiesComponent.ofText()
                        .color(Color.WHITE)
                        .font(new Font("", Font.PLAIN, 28))
                );
        L.add(AnimationComponent.ofText(Component.get(this, "village")), abstractAnimations ->
                scene.changeScene(new Village(player))
                );
        if(player.getSTATES().LV < 2) {
            L.add(AnimationComponent.ofText(Component.get(this, "tutorial")),
                    l -> scene.changeScene(new Battle(player, StagesFactory.TUTORIAL.get().getStates())));
        }
        L.add(AnimationComponent.ofText(Component.get(this, "blue_slime_forest")), abstractAnimations ->
                scene.changeScene(new Battle(player, StagesFactory.STAGE_0.get().getStates())));
        L.add(AnimationComponent.ofText(Component.get(this, "slime_forest")), abstractAnimations ->
                scene.changeScene(new Battle(player, StagesFactory.SLIME_FOREST.get().getStates())));
        L.add(AnimationComponent.ofText(Component.get(this, "cave_entrance")), abstractAnimations ->
                scene.changeScene(new Battle(player, StagesFactory.CAVE_ENTRANCE.get().getStates())));
        AnimationKeyButtons<String, AnimationList<String, Properties>> LIST =
                new AnimationKeyButtons<>(L, 10, 20, 40, 80, Color.RED, Color.WHITE, Color.BLACK);
        LIST.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        switch (e.getKeyChar()){
            case KeyEvent.VK_ESCAPE -> Main.scene.changeScene(new PlayerStatesScene(player));
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
