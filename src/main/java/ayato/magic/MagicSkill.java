package ayato.magic;

import ayato.entity.AbstractEntity;
import ayato.entity.Enemy;
import ayato.scene.AbstractBattle;
import ayato.system.Share;
import org.ayato.animation.*;
import org.ayato.animation.text.properties.PropertyAction;
import org.ayato.system.Component;
import org.ayato.system.LunchScene;

import java.awt.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class MagicSkill {
    private final CopyOnWriteArrayList<Magic> magics;

    public MagicSkill() {
        this.magics = new CopyOnWriteArrayList<>();
    }
    public void add(Magic m){
        if(isHave(m) == -1)
            magics.add(m);
        else
            magics.get(isHave(m)).sumMagic();
    }
    public void remove(Magic m){
        magics.remove(m);
    }

    private int isHave(Magic m) {
        for(int i = 0; i < magics.size(); i ++){
            if(magics.get(i).name.equals(m.name)){
                return i;
            }
        }
        return -1;
    }
    public void viewList(LunchScene scene, AbstractBattle battle, int x, int y, int w, int h){
        AnimationList<String, Properties> l =
        new AnimationList<>(scene, PropertiesComponent.ofText()
                .font(new Font("", Font.PLAIN, 32))
                .color(Color.WHITE)
        );
        for (Magic magic : magics) {
            l.add(AnimationComponent.ofText(magic.name), abstractAnimations -> {
                abstractAnimations.setVisible(false);
                battle.useMagic(magic);
            });
        }
        l.add(AnimationComponent.ofText(Component.get(Share.INSTANCE, "back")), abstractAnimations -> {
            battle.reset();
            abstractAnimations.setVisible(false);
        });
        AnimationKeyButtons<String, AnimationList<String, Properties>> LIST =
                new AnimationKeyButtons<>(l, x, y, w, h, Color.RED, Color.WHITE, Color.BLACK);
        LIST.setVisible(true);
    }

    public int length() {
        return magics.size();
    }

    public void run(LunchScene scene, PropertyAction after, AbstractEntity self, AbstractEntity enemy, Enemy[] enemies, int r) {
        magics.get(r).skill(scene, after, self, enemy, enemies);
    }
}
