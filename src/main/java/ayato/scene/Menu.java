package ayato.scene;

import ayato.entity.Player;
import ayato.item.ShopMenuTemplate;
import org.ayato.animation.*;
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
        Animation.create(lunchScene,AnimationComponent.ofText( Component.get(this, "selection")),
                PropertiesComponent.ofText(20, 20).font(new Font("", Font.PLAIN, 64)).color(Color.WHITE).center()
                ,true);
        AnimationList<String, Properties> l =
                new AnimationList<>(lunchScene, PropertiesComponent.ofText().color(Color.WHITE)
                                .font(new Font("", Font.PLAIN, 32)));

        l.add(AnimationComponent.ofText(Component.get(this, "battle")), list->lunchScene.changeScene(new ChooseMap(player)));
        l.add(AnimationComponent.ofText(Component.get(this, "hotel")), list-> lunchScene.changeScene(new InnScene(player)));
        l.add(AnimationComponent.ofText(Component.get(this, "shop")), list-> lunchScene.changeScene(new Shop(player, ShopMenuTemplate.ALL.get())));
        l.add(AnimationComponent.ofText(Component.get(this, "player_states")), list->lunchScene.changeScene(new PlayerStatesScene(player)));

        AnimationKeyButtons<String, AnimationList<String, Properties>> list =
                new AnimationKeyButtons<>(l, 20, 40, 100, 50, Color.RED, Color.WHITE, Color.BLACK);

        list.setVisible(true);
    }
}
