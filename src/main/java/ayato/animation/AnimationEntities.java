package ayato.animation;

import ayato.entity.AbstractEntity;
import org.ayato.animation.*;
import org.ayato.system.Component;
import org.ayato.system.LunchScene;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.function.Consumer;

public class AnimationEntities implements KeyListener {
    private final Consumer<AbstractEntity> ACTION;
    private final LunchScene MASTER;
    private final AbstractEntity[] entities;
    private final ArrayList<AnimationList<?, Properties>> animationList;
    private final int x, y, w, h;
    private int count = -1, saveCount = 0;
    public AnimationEntities(LunchScene scene, AbstractEntity[] entity, Consumer<AbstractEntity> action,  int x, int y, int w, int h){
        ACTION = action;
        MASTER = scene;
        entities = entity;
        animationList = new ArrayList<>();
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
    public void draw(){
        for(int i = 0; i < entities.length; i ++) {
            final int finalI = i;

            Animation.create(MASTER, AnimationComponent.ofImage(entities[i].getAVATER()),
                    PropertiesComponent.ofImage(x + i * 55, y, 50, 50).ifView(()->entities[finalI].getSTATES().HP > 0), true);

            Animation.create(MASTER, AnimationComponent.ofText(Component.get(this, entities[i].getSTATES().NAME)),
                    PropertiesComponent.ofText(x + i * 55, y + 50)
                            .ifView(()->entities[finalI].getSTATES().HP > 0)
                            .color(Color.WHITE).font(new Font("", Font.PLAIN, 24))
                            .frame(50, 25, ()->finalI == count ?Color.RED :Color.WHITE, Color.BLACK), true);

            Animation.create(MASTER,AnimationComponent.ofText( entities[i].getLV()),
                    PropertiesComponent.ofText(x + i * 55, y + 55)
                            .ifView(()->entities[finalI].getSTATES().HP > 0)
                            .font(new Font("", Font.PLAIN, 24))
                            .color(Color.WHITE)
                            .changeMessage(() ->entities[finalI].getLV()),true);

            Animation.create(MASTER, AnimationComponent.ofText( entities[i].getHP()),
                    PropertiesComponent.ofText(x + i * 55, y + 60)
                            .ifView(()->entities[finalI].getSTATES().HP > 0)
                            .font(new Font("", Font.PLAIN, 24))
                            .color(Color.WHITE).changeMessage(()->entities[finalI].getHP()),true

                    );
            Animation.create(MASTER, AnimationComponent.ofText(entities[i].getMP()),
                    PropertiesComponent.ofText(x + i * 55, y + 65)
                            .ifView(()->entities[finalI].getSTATES().HP > 0)
                            .font(new Font("", Font.PLAIN, 24))
                            .color(Color.WHITE)
                            .changeMessage(()->entities[finalI].getMP()),true

            );
        }
    }
    public void begin(){
        MASTER.FRAME.addKeyListener(this);
        if(entities[saveCount].getSTATES().HP > 0)
            count = saveCount;
        else
            count = aliveEntity();
    }

    private int aliveEntity() {
        for(int i = 0; i < entities.length; i ++){
            if(entities[i].getSTATES().HP > 0)
                return i;
        }
        MASTER.FRAME.removeKeyListener(this);
        return 0;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int i = 0;
        switch (e.getKeyCode()){
            case KeyEvent.VK_LEFT :

                r1 : do {
                    i ++;
                    if(count > 0)
                        count --;
                    else
                        count = entities.length - 1;
                    if(i >= 100) {
                        MASTER.FRAME.removeKeyListener(this);
                        break r1;
                    }
                }while (entities[count].getSTATES().HP <= 0);
                break;
            case KeyEvent.VK_RIGHT:
                r2 :do {
                    i ++;
                    if (count < entities.length - 1)
                        count++;
                    else
                        count = 0;
                    if(i >= 100) {
                        MASTER.FRAME.removeKeyListener(this);
                        break r2;
                    }
                }while (entities[count].getSTATES().HP <= 0);
                break;
            case KeyEvent.VK_ENTER:ACTION.accept(entities[count]);saveCount = count; count = -1; MASTER.FRAME.removeKeyListener(this);break;
            case KeyEvent.VK_ESCAPE:ACTION.accept(null);saveCount = count; count = -1; MASTER.FRAME.removeKeyListener(this);break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
