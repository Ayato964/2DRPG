package ayato.magic;

import ayato.entity.AbstractEntity;
import ayato.entity.Enemy;
import org.ayato.animation.text.properties.PropertyAction;
import org.ayato.system.Component;
import org.ayato.system.LunchScene;

import java.util.function.Supplier;

public class UnityBreaking extends Magic{

    public UnityBreaking(String name) {
        super(name, 10, 5, 10, 5, 5);
    }

    @Override
    public void skillAction(LunchScene scene, PropertyAction after, AbstractEntity self, AbstractEntity enemy, Enemy[] enemies) {
        Supplier<String>[] m = new Supplier[enemies.length + 1];
        m[0] = ()->Component.get(this, "use", self.getSTATES().NAME, name);
       for(int i = 0; i < enemies.length; i ++){
           int a = self.generateATK() + atk;
           int re = enemies[i].recivedATK(a);
           int finalI = i;
           m[i + 1] = ()-> Component.get(this, "damage", self.getSTATES().NAME, enemies[finalI].getSTATES().NAME, String.valueOf(re));
       }
       backMessage(scene, after, m);
    }
}
