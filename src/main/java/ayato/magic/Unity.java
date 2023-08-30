package ayato.magic;

import ayato.entity.AbstractEntity;
import ayato.entity.Enemy;
import org.ayato.animation.text.properties.PropertyAction;
import org.ayato.system.Component;
import org.ayato.system.LunchScene;

public class Unity extends Magic{
    private double atk;
    private final double uatk;
    public Unity(String name) {
        super(name,  5, 5, 10);
        atk = 1.5d;
        uatk = 0.3d;
    }
    @Override
    public void skillAction(LunchScene scene, PropertyAction action,AbstractEntity self, AbstractEntity enemy, Enemy[] enemies) {
        int a = self.generateATK();
        int re = enemy.recivedATK((int) (a * atk));
        backMessage(scene, action,
                ()->Component.get(this, "use", self.getSTATES().NAME, name),
                ()-> Component.get(this, "damage", self.getSTATES().NAME, enemy.getSTATES().NAME, String.valueOf(re))
                );
    }

    @Override
    public void upgrade() {
        super.upgrade();
        atk += uatk;
    }
}
