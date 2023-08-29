package ayato.system;

import ayato.entity.Player;
import ayato.scene.Menu;
import org.ayato.animation.*;
import org.ayato.animation.text.properties.PropertyAction;
import org.ayato.system.Component;
import org.ayato.system.LunchScene;

import java.awt.*;

public class Inn {
    private Inn(){}
    public static void stayInn(LunchScene scene, Player player, int howMuchG, PropertyAction action){
        if(player.getSTATES().G >= howMuchG){
            player.getSTATES().G -= howMuchG;
            player.getSTATES().HP = player.getSTATES().MHP;
            player.getSTATES().MP = player.getSTATES().MMP;
            Animation.create(scene, AnimationComponent.ofText(""), PropertiesTemplate.conv(action, ()-> Component.get(new Inn(), "heal_success")), false)
                    .drawThisScene();
        }else {
            Animation.create(scene, AnimationComponent.ofText(""), PropertiesTemplate.conv(action, ()-> Component.get(new Inn(), "empty_money")), false)
                    .drawThisScene();
        }
    }
    public static void stayInnWithConfirm(LunchScene scene, Player player, int howMuch, PropertyAction action){
        AnimationList<String, Properties> l =
                new AnimationList<>(scene, PropertiesComponent.ofText()
                                .font(new Font("", Font.PLAIN, 32)));

                l.add(AnimationComponent.ofText(Component.get(new Inn(), "yes")), list->stayInn(scene, player, howMuch, action));
                l.add(AnimationComponent.ofText(Component.get(new Inn(), "no")), list-> scene.changeScene(new Menu(player)));

        AnimationKeyButtons<String, AnimationList<String, Properties>> list =
                new AnimationKeyButtons<>(l, 70, 20, 50, 50, Color.RED, Color.WHITE, Color.BLACK);

        Animation.create(scene, AnimationComponent.ofText(""), PropertiesTemplate.conv(iProperty -> list.setVisible(true),
                ()->Component.get(new Inn(), "mes1"),
                ()->Component.get(new Inn(), "mes2", String.valueOf(howMuch)),
                ()->Component.get(new Inn(), "mes3"),
                ()->Component.get(new Inn(), "mes4")
                ), false).drawThisScene();
    }
}
