package ayato.scene;

import ayato.animation.AnimationEntities;
import ayato.entity.AbstractEntity;
import ayato.entity.Enemy;
import ayato.entity.PLayerStates;
import ayato.entity.Player;
import ayato.rpg.Main;
import org.ayato.animation.Animation;
import org.ayato.animation.AnimationKeyButtons;
import org.ayato.animation.AnimationList;
import org.ayato.animation.Properties;
import org.ayato.system.Component;
import org.ayato.system.LunchScene;
import org.ayato.util.Event;
import org.ayato.util.IBaseScene;

import java.awt.*;

public abstract class AbstractBattle implements IBaseScene {
    protected Player player;
    protected Enemy[] enemy;
    protected AnimationKeyButtons<String, AnimationList<String, Properties<String>>> CHOOSE;
    protected int sumG = 0, sumEXP = 0;

    protected AbstractBattle(Player player){
        this.player = player;

    }

    @Override
    public void display(Graphics graphics) {

    }

    @Override
    public void setup(LunchScene lunchScene) {
        Event.create(AbstractBattle.class, "battle_free", null).clear();
        Event.create(AbstractBattle.class, "battle_choose", null);

        PLayerStates states = (PLayerStates) player.getSTATES();
        enemy = getEnemy(lunchScene);

        Animation.create(lunchScene, states.getString().get(),
                Properties.ofText(0, 10)
                        .color(Color.WHITE).font(new Font("", Font.PLAIN, 64))
                        .frame(0, 5, 211, 10, ()->Color.WHITE, Color.BLACK)
                        .center().changeMessage(states.getString()),
                true);
        AnimationEntities entities = new AnimationEntities(lunchScene, enemy, this::returnEnemyAction, 0, 0, 211, 60);
        entities.draw();
        playerGUI(lunchScene, entities);
    }

    private void playerGUI(LunchScene scene, AnimationEntities entities) {
        Animation.create(scene, Component.get(this, "choose"),
                        Properties.ofText(50, 80)
                                .ifView(this::ifViewComment)
                                .font(new Font("", Font.PLAIN, 32))
                                .frame(50, 75, 120, 35, ()->Color.WHITE, Color.BLACK)
                                .color(Color.WHITE)
                                .changeMessage(this::changeCommentMessage), true);

        AnimationList<String, Properties<String>> PLAYER_CHOOSE =
                new AnimationList<>(scene, Component.get(this, "attack"),
                        Properties.ofText().font(new Font("", Font.PLAIN, 24)),
                        ()-> {
                            Event.get(AbstractBattle.class, "battle_choose").clear();
                            entities.begin();
                        });
        PLAYER_CHOOSE.add(Component.get(this, "magic"), ()-> System.out.println("Magic"));
        PLAYER_CHOOSE.add(Component.get(this, "item"), ()-> System.out.println("Item used!!"));
        PLAYER_CHOOSE.add(Component.get(this, "escape"), ()-> System.out.println("Escaped!!!"));

        CHOOSE = new AnimationKeyButtons<>(PLAYER_CHOOSE, 5, 75, 30, 40, Color.RED, Color.WHITE, Color.BLACK);
        CHOOSE.setVisible(true);
    }

    protected abstract Enemy[] getEnemy(LunchScene MASTER);

    public boolean ifViewComment(){
        return Event.get(AbstractBattle.class, "battle_free").getEvent() || Event.get(AbstractBattle.class, "battle_choose").getEvent();
    }
    public String changeCommentMessage() {
        if(Event.get(AbstractBattle.class, "battle_free").getEvent() &&  Event.get(AbstractBattle.class, "battle_choose").getEvent())
            return Component.get(this, "choose_enemy");
        else if(Event.get(AbstractBattle.class, "battle_free").getEvent() &&  !Event.get(AbstractBattle.class, "battle_choose").getEvent())
            return Component.get(this, "choose");
        else return "ERROR!!";
    }

    public void returnEnemyAction(AbstractEntity entity){
        Event.get(AbstractBattle.class, "battle_free").setEvent(false);
        Event.get(AbstractBattle.class, "battle_choose").setEvent(false);

        int E_RECIVED_DAMAGE = entity.recivedATK(player.generateATK());
        Animation.create(Main.scene, "",
                Properties.ofText(50, 80)
                        .font(new Font("", Font.PLAIN, 32))
                        .color(Color.WHITE)
                        .talk(this, true, iProperty ->{
                            if(entity.getSTATES().HP <= 0){
                                sumG += entity.getSTATES().G;
                                sumEXP += entity.getSTATES().EXP;
                            }
                            playerRecivedDamage(0);
                            },
                                ()->Component.get(this, "attack_enemy", player.getSTATES().NAME, entity.getSTATES().NAME, String.valueOf(E_RECIVED_DAMAGE))
                        )
                        .frame(50, 75, 120, 35, ()->Color.WHITE, Color.BLACK)
                ,false).drawThisScene();


    }

    private void playerRecivedDamage(int i) {
        if(i < enemy.length) {
            if(enemy[i].getSTATES().HP > 0) {
                int P_RECIVED_DAMAGE = player.recivedATK(enemy[i].generateATK());
                Animation.create(Main.scene, "",
                        Properties.ofText(50, 80)
                                .font(new Font("", Font.PLAIN, 32))
                                .color(Color.WHITE)
                                .talk(this, true, iProperty -> playerRecivedDamage(i + 1),
                                        () -> Component.get(this, "attack_enemy", enemy[i].getSTATES().NAME, player.getSTATES().NAME, String.valueOf(P_RECIVED_DAMAGE))
                                )
                                .frame(50, 75, 120, 35, () -> Color.WHITE, Color.BLACK), false).drawThisScene();
            } else {
                if(ifEnemiesHPAllZero()){
                    clearResult();
                }else {
                   playerRecivedDamage(i + 1);
                }
            }
        } else {
            if(player.getSTATES().HP <= 0){
                gameOver();
            }else {
                Event.get(AbstractBattle.class, "battle_free").clear();
                CHOOSE.setVisible(false);
                CHOOSE.setVisible(true);
            }

        }
    }

    private void gameOver() {
        Animation.create(Main.scene, "",
                Properties.ofText(50, 80)
                        .font(new Font("", Font.PLAIN, 32))
                        .color(Color.WHITE)
                        .talk(this, true, iProperty -> {
                                    Event.get(AbstractBattle.class, "battle_free").clear();
                                    Main.scene.changeScene(new Title());
                                },
                                () -> Component.get(this, "gameover")
                        )
                        .frame(50, 75, 120, 35, () -> Color.WHITE, Color.BLACK), false).drawThisScene();
    }

    private void clearResult() {
        final int base_lv = player.getSTATES().LV;
        player.addGold(sumG);
        player.addEXP(sumEXP);
        Animation.create(Main.scene, "", Properties.ofText(50, 80)
                        .font(new Font("", Font.PLAIN, 32))
                        .color(Color.WHITE)
                        .talk(this, true, iProperty -> {
                                if(base_lv < player.getSTATES().LV)
                                    levelUpMessage();
                                else {
                                    Event.get(AbstractBattle.class, "battle_free").setEvent(true);
                                    Event.get(AbstractBattle.class, "battle_choose").setEvent(false);
                                    Main.scene.changeScene(new Menu(player));
                                }
                                },
                                ()->Component.get(this, "clear.mes"),
                                ()->Component.get(this, "clear.result", player.getSTATES().NAME, String.valueOf(sumEXP), String.valueOf(sumG))
                                )
                .frame(50, 75, 120, 35, () -> Color.WHITE, Color.BLACK)
                , false).drawThisScene();
    }

    private void levelUpMessage() {
        Animation.create(Main.scene, "", Properties.ofText(50, 80)
                        .font(new Font("", Font.PLAIN, 32))
                        .color(Color.WHITE)
                        .frame(50, 75, 120, 35, () -> Color.WHITE, Color.BLACK)
                        .talk(this, true, iProperty ->{

                            Event.get(AbstractBattle.class, "battle_free").setEvent(true);
                            Event.get(AbstractBattle.class, "battle_choose").setEvent(false);
                            Main.scene.changeScene(new Menu(player));}, ()->Component.get(this, "levelup.mes"))
                , false).drawThisScene();

    }

    private boolean ifEnemiesHPAllZero() {
        for(Enemy e: enemy){
            if(e.getSTATES().HP > 0)
                return false;
        }
        return true;
    }
}
