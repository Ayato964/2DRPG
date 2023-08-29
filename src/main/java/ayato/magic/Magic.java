package ayato.magic;

import ayato.entity.AbstractEntity;
import ayato.entity.Enemy;
import ayato.system.PropertiesTemplate;
import org.ayato.animation.Animation;
import org.ayato.animation.AnimationComponent;
import org.ayato.animation.text.properties.PropertyAction;
import org.ayato.system.LunchScene;

import java.util.function.Supplier;

public abstract class Magic {
    protected final String name;
    protected final int uAtk, uMana;
    protected int atk, mana, lv = 1, exp = 0, mExp;
    public Magic(String name, int atk, int uAtk, int mana, int uMana, int mExp){
        this.name = name;
        this.uMana = uMana;
        this.uAtk = uAtk;
        this.atk = atk;
        this.mana = mana;
        this.mExp = mExp;
    }

    public void sumMagic() {
        exp ++;
        if(exp > mExp)
            upgrade();

    }

    public void upgrade() {
        exp -= mExp;
        lv ++;
        atk += uAtk;
        mana += uMana;
        mExp = mExp + mExp * (lv - 1);
    }
    protected void backMessage(LunchScene scene, PropertyAction after, Supplier<String>... mes){
        Animation.create(scene, AnimationComponent.ofText(""), PropertiesTemplate.conv(after,mes), false).drawThisScene();
    }

    public abstract void skillAction(LunchScene scene, PropertyAction after, AbstractEntity self, AbstractEntity enemy, Enemy[] enemies);

    public int getMana() {
        return mana;
    }
}
