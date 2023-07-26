package ayato.animation;

import ayato.entity.AbstractEntity;
import org.ayato.animation.Animation;
import org.ayato.animation.Properties;
import org.ayato.system.Component;
import org.ayato.system.LunchScene;

import java.awt.*;

public class AnimationEntities {
    private final LunchScene MASTER;
    private final AbstractEntity[] entities;
    private final int x, y, w, h;
    public AnimationEntities(LunchScene scene, AbstractEntity[] entity, int x, int y, int w, int h){
        MASTER = scene;
        entities = entity;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
    public void draw(){
        for(int i = 0; i < entities.length; i ++) {
            Animation.create(MASTER, entities[i].getAVATER(), Properties.ofImage(x + i * 55, y, 50, 50), true);
            Animation.create(MASTER, Component.get(this, entities[i].getSTATES().NAME),
                    Properties.ofText(x + i * 55, y + 55)
                            .color(Color.WHITE).font(new Font("", Font.PLAIN, 24))
                            .frame(50, 25, ()->Color.WHITE, Color.BLACK), true);

            Animation.create(MASTER, entities[i].getLV(),
                    Properties.ofText(x + i * 55, y + 60)
                            .font(new Font("", Font.PLAIN, 24))
                            .color(Color.WHITE),true);
            Animation.create(MASTER, entities[i].getHP(),
                    Properties.ofText(x + i * 55, y + 65)
                            .font(new Font("", Font.PLAIN, 24))
                            .color(Color.WHITE),true

                    );
            Animation.create(MASTER, entities[i].getMP(),
                    Properties.ofText(x + i * 55, y + 70)
                            .font(new Font("", Font.PLAIN, 24))
                            .color(Color.WHITE),true

            );


        }
    }
}
