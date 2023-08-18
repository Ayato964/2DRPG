package ayato.scene;

import ayato.entity.PLayerStates;
import ayato.entity.Player;
import ayato.item.Item;
import ayato.item.armors.Armor;
import ayato.item.armors.Necklace;
import ayato.item.armors.Ring;
import ayato.item.armors.Weapon;
import ayato.system.Inventory;
import ayato.system.PropertiesTemplate;
import org.ayato.animation.*;
import org.ayato.system.Component;
import org.ayato.system.LunchScene;
import org.ayato.util.IBaseScene;

import java.awt.*;

public class PlayerStatesScene implements IBaseScene {
    private AnimationKeyButtons<String, AnimationList<String, Properties>> CHOOSE_LIST;
    public final Player PLAYER;
    public PlayerStatesScene(Player player) {
        PLAYER = player;
    }

    @Override
    public void display(Graphics graphics) {

    }

    @Override
    public void setup(LunchScene lunchScene) {
        AnimationList<String, Properties> CHOOSE = new AnimationList<>(lunchScene, PropertiesComponent.ofText().color(Color.WHITE)
                .font(new Font("", Font.PLAIN, 32)));

        CHOOSE.add(AnimationComponent.ofText(Component.get(this, "get_states")), i->playerStates(lunchScene));
        CHOOSE.add(AnimationComponent.ofText(Component.get(this, "get_armor")), i->armorSetting(lunchScene));
        CHOOSE.add(AnimationComponent.ofText(Component.get(this, "back")), i->lunchScene.changeScene(new Menu(PLAYER)));
        CHOOSE_LIST = new AnimationKeyButtons<>(CHOOSE, 10, 50, 30, 50, Color.RED, Color.WHITE, Color.BLACK);
        CHOOSE_LIST.setVisible(true);
    }

    private void armorSetting(LunchScene lunchScene) {
        Inventory inv = PLAYER.getSTATES().inventory;
        AnimationList<String, Properties> animationList =
                new AnimationList<>(lunchScene, PropertiesComponent.ofText()
                        .font(new Font("", Font.PLAIN, 32)).color(Color.WHITE));
        animationList.add(AnimationComponent.ofText(Component.get(this, "weapon",inv.getWEAPON() != null ? ((Item) inv.getWEAPON()).NAME : null)), LIST -> inv.view((item, abstractAnimations) -> {
            if(item instanceof Weapon) {
                inv.setWEAPON((Weapon) item);
                abstractAnimations.setVisible(false);
                LIST.repaint();
            } else {
                Animation.create(lunchScene, AnimationComponent.ofText(""), PropertiesTemplate.conv(iProperty -> abstractAnimations.repaint(),
                        ()->Component.get(this, "not_match")), false).drawThisScene();
            }
        }, LIST::repaint));
        animationList.add(AnimationComponent.ofText(Component.get(this, "armor",inv.getARMOR() != null ?((Item) inv.getARMOR()).NAME : null)), LIST -> inv.view((item, abstractAnimations) -> {
            if(item instanceof Armor) {
                inv.setARMOR((Armor) item);
                abstractAnimations.setVisible(false);
                LIST.repaint();
            } else {
                Animation.create(lunchScene, AnimationComponent.ofText(""), PropertiesTemplate.conv(iProperty -> abstractAnimations.repaint(),
                        ()->Component.get(this, "not_match")), false).drawThisScene();
            }
        }, LIST::repaint));
        animationList.add(AnimationComponent.ofText(Component.get(this, "necklace",inv.getNECKLACE() != null ?((Item) inv.getNECKLACE()).NAME : null)), LIST -> inv.view((item, abstractAnimations) -> {
            if(item instanceof Necklace) {;
                inv.setNECKLACE((Necklace) item);
                abstractAnimations.setVisible(false);
                LIST.repaint();
            } else {
                Animation.create(lunchScene, AnimationComponent.ofText(""), PropertiesTemplate.conv(iProperty -> abstractAnimations.repaint(),
                        ()->Component.get(this, "not_match")), false).drawThisScene();
            }
        }, LIST::repaint));
        animationList.add(AnimationComponent.ofText(Component.get(this, "ring",inv.getRING() != null ?((Item) inv.getRING()).NAME : null)), LIST -> inv.view((item, abstractAnimations) -> {
            if(item instanceof Ring) {
                inv.setRING((Ring) item);
                abstractAnimations.setVisible(false);
                LIST.repaint();
            } else {
                Animation.create(lunchScene, AnimationComponent.ofText(""), PropertiesTemplate.conv(iProperty -> abstractAnimations.repaint(),
                        ()->Component.get(this, "not_match")), false).drawThisScene();
            }
        }, LIST::repaint));
        animationList.add(AnimationComponent.ofText(Component.get(this, "back")), LIST -> {
            CHOOSE_LIST.repaint();
            LIST.setVisible(false);
        });

        AnimationKeyButtons<String, AnimationList<String, Properties>> LIST =
                new AnimationKeyButtons<>(animationList, 40, 50, 50, 50, Color.RED, Color.WHITE, Color.BLACK);
        LIST.setVisible(true);
    }

    private void playerStates(LunchScene lunchScene){
        AnimationKeyButtons<String, AnimationList<String, Properties>> LL;
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
        L.add(AnimationComponent.ofText(Component.get(this, "back")), list->{
            CHOOSE_LIST.setVisible(false);
            CHOOSE_LIST.setVisible(true);
            list.setVisible(false);
        });
        LL =
                new AnimationKeyButtons<>(L, 40, 50, 30, 20, Color.RED, Color.WHITE, Color.BLACK);
        LL.setVisible(true);
    }
}
