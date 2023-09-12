package ayato.scene;

import ayato.entity.Player;
import ayato.item.Item;
import ayato.system.PropertiesTemplate;
import org.ayato.animation.*;
import org.ayato.animation.image.ImageMaker;
import org.ayato.system.Component;
import org.ayato.system.LunchScene;
import org.ayato.util.IBaseScene;

import java.awt.*;
import java.util.ArrayList;

public class Shop implements IBaseScene {
    private final Player player;
    private final ArrayList<Item> SEAL_ITEM;
    private AnimationKeyButtons<String, AnimationList<String, Properties>> ANIMATION;
    private final String imageName;
    public Shop(Player p, ArrayList<Item> items, String imageName){
        player = p;
        SEAL_ITEM = items;
        this.imageName = imageName;
    }

    @Override
    public void display(Graphics graphics) {

    }

    @Override
    public void setup(LunchScene lunchScene) {
        lunchScene.BACKGROUND.mode.setImage(new ImageMaker("background", imageName));
        Animation.create(lunchScene, AnimationComponent.ofText(Component.get(this, "select.mes", String.valueOf(player.getSTATES().G))),
                PropertiesComponent.ofText(10, 5).color(Color.WHITE).font(new Font("", Font.PLAIN, 32))
                        .changeMessage(()->Component.get(this, "select.mes", String.valueOf(player.getSTATES().G)))
                , true);

        AnimationList<String, Properties> LIST =
                new AnimationList<>(lunchScene, PropertiesComponent.ofText().color(Color.WHITE));

        for(int i = 0; i < SEAL_ITEM.size(); i ++){
            final int finalI = i;
           LIST.add(AnimationComponent.ofText(SEAL_ITEM.get(i).NAME + "   " + SEAL_ITEM.get(i).G + "G"), list-> buy(lunchScene, finalI));
        }
        LIST.add(AnimationComponent.ofText(Component.get(this, "back")), list->lunchScene.changeScene(new Village(player)));

        ANIMATION =
                new AnimationKeyButtons<>(LIST, 10, 30, 120, 90, Color.RED, Color.WHITE, Color.BLACK);
        ANIMATION.setVisible(true);

    }

    private void buy(LunchScene scene,int i) {
        Item item = SEAL_ITEM.get(i);
        if(item.G <= player.getSTATES().G){
            player.getSTATES().G -= item.G;
            player.getSTATES().inventory.add(item);
            Animation.create(scene, AnimationComponent.ofText(""), PropertiesTemplate.conv(iProperty ->{
                    ANIMATION.setVisible(false);ANIMATION.setVisible(true);}, ()->Component.get(this, "complete", item.NAME)), false).drawThisScene();
        }else{
            Animation.create(scene, AnimationComponent.ofText(""), PropertiesTemplate.conv(iProperty -> {
                ANIMATION.setVisible(false);
                ANIMATION.setVisible(true);
            },()->Component.get(this, "not_cash")), false).drawThisScene();
        }
    }
}
