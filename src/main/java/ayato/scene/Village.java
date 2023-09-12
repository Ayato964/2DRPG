package ayato.scene;

import ayato.entity.Player;
import ayato.item.ShopMenuTemplate;
import ayato.system.Share;
import org.ayato.animation.*;
import org.ayato.animation.image.ImageMaker;
import org.ayato.system.Component;
import org.ayato.system.LunchScene;
import org.ayato.util.IBaseScene;

import java.awt.*;

public class Village implements IBaseScene {
    private final Player player;
    public Village(Player player1){

        this.player = player1;
    }
    @Override
    public void display(Graphics graphics) {

    }

    @Override
    public void setup(LunchScene lunchScene) {
        lunchScene.BACKGROUND.mode.setImage(new ImageMaker("background", "village"));
        AnimationList<String, Properties> L =
                new AnimationList<>(lunchScene, PropertiesComponent.ofText()
                        .color(Color.WHITE)
                        .font(new Font("", Font.PLAIN, 28))
                );
        L.add(AnimationComponent.ofText(Component.get(this, "general_store")), abstractAnimations ->
                lunchScene.changeScene(new Shop(player, ShopMenuTemplate.HEALER.get(), "general_store")));

        L.add(AnimationComponent.ofText(Component.get(this, "armor_store")), abstractAnimations ->
                lunchScene.changeScene(new Shop(player, ShopMenuTemplate.ARMOR.get(), "armors"))
                );
        L.add(AnimationComponent.ofText(Component.get(this, "hotel")), abstractAnimations ->
                lunchScene.changeScene(new InnScene(player))
                );
        L.add(AnimationComponent.ofText(Component.get(Share.INSTANCE, "back")), abstractAnimations -> lunchScene.changeScene(new FrontWorld(player)));
        AnimationKeyButtons<String, AnimationList<String, Properties>> LIST =
                new AnimationKeyButtons<>(L, 10, 20, 40, 80, Color.RED, Color.WHITE, Color.BLACK);
        LIST.setVisible(true);    }
}
