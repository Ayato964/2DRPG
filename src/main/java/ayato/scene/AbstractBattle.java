package ayato.scene;

import ayato.entity.Enemy;
import ayato.entity.Player;
import org.ayato.system.LunchScene;
import org.ayato.util.IBaseScene;

import java.awt.*;

public abstract class AbstractBattle implements IBaseScene {
    protected Player player;
    protected Enemy[] enemy;

    protected AbstractBattle(Player player){
        this.player = player;
    }

    @Override
    public void display(Graphics graphics) {

    }

    @Override
    public void setup(LunchScene lunchScene) {

    }
    protected abstract Enemy[] getEnemy();
}
