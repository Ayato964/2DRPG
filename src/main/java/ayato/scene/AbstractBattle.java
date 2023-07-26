package ayato.scene;

import ayato.animation.AnimationEntities;
import ayato.entity.Enemy;
import ayato.entity.PLayerStates;
import ayato.entity.Player;
import org.ayato.animation.Animation;
import org.ayato.animation.Properties;
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
        PLayerStates states = (PLayerStates) player.getSTATES();
        enemy = getEnemy(lunchScene);

        Animation.create(lunchScene, states.getString().get(),
                Properties.ofText(0, 10)
                        .color(Color.WHITE).font(new Font("", Font.PLAIN, 64))
                        .frame(0, 5, 211, 10, ()->Color.WHITE, Color.BLACK)
                        .center().changeMessage(states.getString()),
                true);
        AnimationEntities entities = new AnimationEntities(lunchScene, enemy, 0, 0, 211, 60);
        entities.draw();

    }
    protected abstract Enemy[] getEnemy(LunchScene MASTER);
}
