package ayato.magic;

import ayato.entity.AbstractEntity;
import ayato.entity.Enemy;
import ayato.system.PropertiesTemplate;
import ayato.system.Share;
import org.ayato.animation.Animation;
import org.ayato.animation.AnimationComponent;
import org.ayato.animation.text.properties.PropertyAction;
import org.ayato.system.Component;
import org.ayato.system.LunchScene;

import java.util.function.Supplier;

public abstract class Magic {

    protected final String name;
    protected final int uMana;
    protected int mana, lv = 1, exp = 0, mExp;
    public Magic(String name, int mana, int uMana, int mExp){
        this.name = name;
        this.uMana = uMana;
        this.mana = mana;
        this.mExp = mExp;
    }

    public void sumMagic() {
        exp ++;
        if(exp > mExp)
            upgrade();

    }
    public void addExp(int i){
        exp += i;
        if(exp > mExp)
            upgrade();
    }

    public void upgrade() {
        exp -= mExp;
        lv ++;
        mana += uMana;
        mExp = mExp + mExp * (lv - 1);
    }
    @SafeVarargs
    protected final void backMessage(LunchScene scene, PropertyAction after, Supplier<String>... mes){
        Animation.create(scene, AnimationComponent.ofText(""), PropertiesTemplate.conv(after,mes), false).drawThisScene();
    }
    public void skill(LunchScene scene, PropertyAction after, AbstractEntity self, AbstractEntity enemy, Enemy[] enemies){
        int l = lv;
        addExp(1);
        if(lv != l)
            Animation.create(scene, AnimationComponent.ofText(""),
                    PropertiesTemplate.conv(null,
                            ()-> Component.get(Share.INSTANCE, "upgrade", name)
                            ), false).drawThisScene();
        skillAction(scene, after, self, enemy, enemies);

    }

    protected abstract void skillAction(LunchScene scene, PropertyAction after, AbstractEntity self, AbstractEntity enemy, Enemy[] enemies);

    public int getMana() {
        return mana;
    }
}
