package ayato.system;

import ayato.entity.Player;
import ayato.scene.Menu;
import org.ayato.animation.Animation;
import org.ayato.animation.AnimationKeyButtons;
import org.ayato.animation.AnimationList;
import org.ayato.animation.Properties;
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
            Animation.create(scene, "", PropertiesTemplate.conv(action, ()-> Component.get(new Inn(), "heal_success")), false)
                    .drawThisScene();
        }else {
            Animation.create(scene, "", PropertiesTemplate.conv(action, ()-> Component.get(new Inn(), "empty_money")), false)
                    .drawThisScene();
        }
    }
    public static void stayInnWithConfirm(LunchScene scene, Player player, int howMuch, PropertyAction action){
        AnimationList<String, Properties<String>> l =
                new AnimationList<>(scene,Component.get(new Inn(), "yes"),
                        Properties.ofText()
                                .font(new Font("", Font.PLAIN, 32)),
                        ()->stayInn(scene, player, howMuch, action));
                l.add(Component.get(new Inn(), "no"), ()-> scene.changeScene(new Menu(player)));

        AnimationKeyButtons<String, AnimationList<String, Properties<String>>> list =
                new AnimationKeyButtons<>(l, 70, 20, 50, 50, Color.RED, Color.WHITE, Color.BLACK);

        Animation.create(scene, "", PropertiesTemplate.conv(iProperty -> list.setVisible(true),
                ()->Component.get(new Inn(), "mes1"),
                ()->Component.get(new Inn(), "mes2", String.valueOf(howMuch)),
                ()->Component.get(new Inn(), "mes3"),
                ()->Component.get(new Inn(), "mes4")
                ), false).drawThisScene();
    }
}
