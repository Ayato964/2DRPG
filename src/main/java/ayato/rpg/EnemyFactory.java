package ayato.rpg;

import ayato.entity.Enemy;
import ayato.system.JsonComponent;
import org.ayato.animation.image.ImageMaker;
import org.ayato.system.RegistoryList;
import org.ayato.system.RegistoryObject;

public class EnemyFactory {
    public static final String NAME = "name";
    public static final String HP = "hp";
    public static final String U_HP = "uhp";
    public static final String MP = "mp";
    public static final String U_MP = "ump";
    public static final String EXP = "exp";
    public static final String U_EXP = "uexp";

    public static final String G = "g";
    public static final String U_G = "ug";
    public static final String DF = "defence";
    public static final String U_DF = "udefence";
    public static final String ATK = "atk";
    public static final String U_ATK = "uatk";
    public static final String POW = "pow";
    public static final RegistoryList<Enemy> ENEMIES =
            RegistoryList.create(Main.scene, "enemy");

    public static final RegistoryObject<Enemy> SLIME_BLUE =
            ENEMIES.create(()->new Enemy(Main.scene, new ImageMaker("enemy", "slime_blue"),
                    JsonComponent.get("enemy", "slime_blue")), "slime_blue");
    public static final RegistoryObject<Enemy> SLIME_RED =
            ENEMIES.create(()-> new Enemy(Main.scene, new ImageMaker("enemy", "slime_red"),
                    JsonComponent.get("enemy", "slime_red")), "slime_red");
    public static final RegistoryObject<Enemy> SLIME_YELLOW =
            ENEMIES.create(()-> new Enemy(Main.scene, new ImageMaker("enemy", "slime_yellow"),
                    JsonComponent.get("enemy", "slime_yellow")), "slime_yellow");

    public static final RegistoryObject<Enemy> TUTORIAL =
            ENEMIES.create(()-> new Enemy(Main.scene, new ImageMaker("enemy", "tutorial"),
                    JsonComponent.get("enemy", "tutorial")), "tutorial");
}
