package ayato.scene;

import ayato.animation.AnimationEntities;
import ayato.effect.Effect;
import ayato.entity.AbstractEntity;
import ayato.entity.Enemy;
import ayato.entity.PLayerStates;
import ayato.entity.Player;
import ayato.item.Item;
import ayato.magic.Magic;
import ayato.rpg.Main;
import ayato.system.PropertiesTemplate;
import ayato.system.ValueContainer;
import org.ayato.animation.*;
import org.ayato.animation.image.ImageMaker;
import org.ayato.system.Background;
import org.ayato.system.Component;
import org.ayato.system.LunchScene;
import org.ayato.util.Event;
import org.ayato.util.IBaseScene;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public abstract class AbstractBattle implements IBaseScene {
    public static final Event.EventCard<?> BATTLE_FREE = Event.create(AbstractBattle.class, "battle_free", null);
    public static final Event.EventCard<?> BATTLE_CHOOSE =   Event.create(AbstractBattle.class, "battle_choose", null);
    protected Player player;
    protected Enemy[] enemy;
    protected AnimationKeyButtons<String, AnimationList<String, Properties>> CHOOSE;
    protected int sumG = 0, sumEXP = 0;
    protected ArrayList<Item> sumItem = new ArrayList<>();
    public AnimationEntities ENTITIES;
    private String bgName;

    private final ValueContainer container = new ValueContainer() {
        @Override
        public void set(int c, int v) {
            switch (c){
                case G -> sumG = v;
                case EXP -> sumEXP = v;
            }
        }

        @Override
        public int count() {
            return 2;
        }

        @Override
        public int get(int c) {
            return switch (c){
                case G -> sumG;
                case EXP -> sumEXP;
                default -> throw new IllegalStateException("Unexpected value: " + c);
            };
        }

        @Override
        public void reset() {
            sumG = 0;
            sumEXP = 0;
        }
    };
    protected AbstractBattle(Player player, String bgName){
        this.player = player;
        this.bgName = bgName;
    }

    @Override
    public void display(Graphics graphics) {

    }

    @Override
    public void setup(LunchScene lunchScene) {
        lunchScene.BACKGROUND.mode = Background.BackgroundMode.IMAGE;
        lunchScene.BACKGROUND.mode.setImage(new ImageMaker("background", bgName));
        BATTLE_FREE.clear();
        BATTLE_CHOOSE.setEvent(false);
        PLayerStates states = (PLayerStates) player.getSTATES();
        enemy = getEnemy(lunchScene);

        Animation.create(lunchScene,AnimationComponent.ofText( states.getString().get()),
                PropertiesComponent.ofText(0, 10)
                        .color(Color.WHITE).font(new Font("", Font.PLAIN, 64))
                        .frame(0, 5, 211, 10, ()->Color.WHITE, Color.BLACK)
                        .center().changeMessage(states.getString()),
                true);

        ENTITIES = new AnimationEntities(lunchScene, enemy, 0, 0, 211, 60);
        ENTITIES.draw(this::returnEnemyAction);
        playerGUI(lunchScene, ENTITIES);
    }
    public void reset(){
        Event.get(AbstractBattle.class, "battle_free").setEvent(true);
        BATTLE_CHOOSE.setEvent(false);
        CHOOSE.setVisible(false);
        CHOOSE.setVisible(true);
    }

    private void playerGUI(LunchScene scene, AnimationEntities entities) {
        Animation.create(scene, AnimationComponent.ofText(Component.get(this, "choose")),
                        PropertiesComponent.ofText(50, 80)
                                .ifView(this::ifViewComment)
                                .font(new Font("", Font.PLAIN, 32))
                                .frame(50, 75, 120, 35, ()->Color.WHITE, Color.BLACK)
                                .color(Color.WHITE)
                                .changeMessage(this::changeCommentMessage), true);

        AnimationList<String, Properties> PLAYER_CHOOSE =
                new AnimationList<>(scene,
                        PropertiesComponent.ofText().font(new Font("", Font.PLAIN, 24)));

        PLAYER_CHOOSE.add(AnimationComponent.ofText( Component.get(this, "attack")),  list-> {
            Event.get(AbstractBattle.class, "battle_choose").clear();
            entities.begin(this::returnEnemyAction);
        });

        PLAYER_CHOOSE.add(AnimationComponent.ofText(Component.get(this, "magic")), list->{
                Event.get(AbstractBattle.class, "battle_choose").clear();
                player.getSTATES().magic.viewList(scene, this, 30, 20, 100, 100);
        });
        PLAYER_CHOOSE.add(AnimationComponent.ofText(Component.get(this, "item")), list-> {
                    Event.get(AbstractBattle.class, "battle_free").setEvent(false);
                    player.getSTATES().inventory.view(()->{
                        reset();
                        viewEffect(scene);
                    });
        });
        PLAYER_CHOOSE.add(AnimationComponent.ofText(Component.get(this, "escape")), this::escape);

        CHOOSE = new AnimationKeyButtons<>(PLAYER_CHOOSE, 5, 75, 30, 40, Color.RED, Color.WHITE, Color.BLACK);
        CHOOSE.setVisible(true);

        viewEffect(scene);
    }

    private void viewEffect(LunchScene scene) {
        int x = scene.FRAME.DESCTOP_BOUNDS.width / scene.FRAME.DW, y = 50, w = 10, h = 10;
        x -= w;
        for(int i= 0; i < player.getEffects().size(); i ++){
            if(!player.getEffects().get(i).isView)
                player.getEffects().get(i).view(scene, x - i * (w + 5), y, w, h);
        }
    }

    private void escape(AbstractAnimations<?, ?> list) {
        int r = new Random().nextInt(0, 1000);
        if(r < escapeChance()){
            Animation.create(Main.scene, AnimationComponent.ofText(""), PropertiesTemplate
                    .conv(iProperty -> Main.scene.changeScene(new Menu(player)),
                            ()->Component.get(this, "escaped", player.getSTATES().NAME)),
                    false).drawThisScene();
        }else {
            Animation.create(Main.scene, AnimationComponent.ofText(""), PropertiesTemplate.conv(
                    iProperty -> playerRecivedDamage(0),
                    ()->Component.get(this, "escape_failed", player.getSTATES().NAME)
            ), false).drawThisScene();
        }
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
        Enemy enemy = (Enemy) entity;
        if(entity == null) {
            Event.get(AbstractBattle.class, "battle_free").clear();
            CHOOSE.setVisible(false);
            CHOOSE.setVisible(true);
        } else {
            Event.get(AbstractBattle.class, "battle_free").setEvent(false);
            Event.get(AbstractBattle.class, "battle_choose").setEvent(false);

            int E_RECIVED_DAMAGE = entity.recivedATK(player.generateATK());
            Animation.create(Main.scene, AnimationComponent.ofText(""), PropertiesTemplate.conv(iProperty -> {
                                if (entity.getSTATES().HP <= 0) {
                                    enemy.takeReward(player, sumItem, container);
                                }
                                playerRecivedDamage(0);
                            },
                            () -> Component.get(this, E_RECIVED_DAMAGE == 0 ? "attack_field_enemy" : "attack_enemy", player.getSTATES().NAME, entity.getSTATES().NAME, String.valueOf(E_RECIVED_DAMAGE)))
                    , false).drawThisScene();
        }

    }

    private void playerRecivedDamage(int i) {
        if(i < enemy.length) {
            if(enemy[i].getSTATES().HP > 0) {
                int r = new Random().nextInt(0, 1000);
                if(enemy[i].getSTATES().magic.length() == 0) {
                    int P_RECIVED_DAMAGE = player.recivedATK(enemy[i].generateATK());
                    Animation.create(Main.scene, AnimationComponent.ofText(""), PropertiesTemplate.conv(iProperty -> playerRecivedDamage(i + 1),
                            () -> Component.get(this, P_RECIVED_DAMAGE == 0 ? "attack_field_enemy" : "attack_enemy", enemy[i].getSTATES().NAME, player.getSTATES().NAME, String.valueOf(P_RECIVED_DAMAGE))), false).drawThisScene();
                }else{
                    if(r < 300)
                        enemy[i].generateRandomMagic(Main.scene, iProperty -> playerRecivedDamage(i + 1), enemy[i], player, enemy);
                    else{
                        int P_RECIVED_DAMAGE = player.recivedATK(enemy[i].generateATK());
                        Animation.create(Main.scene, AnimationComponent.ofText(""), PropertiesTemplate.conv(iProperty -> playerRecivedDamage(i + 1),
                                () -> Component.get(this, P_RECIVED_DAMAGE == 0 ? "attack_field_enemy" : "attack_enemy", enemy[i].getSTATES().NAME, player.getSTATES().NAME, String.valueOf(P_RECIVED_DAMAGE))), false).drawThisScene();
                    }
                }
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
                player.runEffect();
                Event.get(AbstractBattle.class, "battle_free").clear();
                CHOOSE.setVisible(false);
                CHOOSE.setVisible(true);
            }

        }
    }

    private void gameOver() {
        Animation.create(Main.scene, AnimationComponent.ofText(""),
                PropertiesTemplate.conv( iProperty -> {
                            Event.get(AbstractBattle.class, "battle_free").clear();
                            Main.scene.changeScene(new Title());
                        },
                        () -> Component.get(this, "gameover")), false).drawThisScene();
    }

    private void clearResult() {
        final int base_lv = player.getSTATES().LV;
        player.addGold(sumG);
        player.addEXP(sumEXP);
        player.addItemAll(sumItem);
        Animation.create(Main.scene, AnimationComponent.ofText(""),
                PropertiesTemplate.conv(iProperty -> {
                            goMenu(base_lv < player.getSTATES().LV);
                            getItemMessage(0);
                        },
                        ()->Component.get(this, "clear.mes"),
                        ()->Component.get(this, "clear.result", player.getSTATES().NAME, String.valueOf(sumEXP), String.valueOf(sumG)))
                , false).drawThisScene();
    }

    private void getItemMessage(int i) {
        if(i < sumItem.size()) {
            Animation.create(Main.scene, AnimationComponent.ofText(""),
                    PropertiesTemplate.conv(iProperty -> getItemMessage(i + 1), ()->Component.get(this, "get_item", player.getSTATES().NAME,sumItem.get(i).NAME)), false).drawThisScene();
        }
    }

    private void goMenu(boolean isLevelUp){
        if(isLevelUp)
            levelUpMessage();
        else {
            Event.get(AbstractBattle.class, "battle_free").setEvent(true);
            Event.get(AbstractBattle.class, "battle_choose").setEvent(false);
            Main.scene.changeScene(new Menu(player));
        }
        for(Effect e : player.getEffects())
            e.isView = false;
    }
    private void levelUpMessage() {
        Animation.create(Main.scene, AnimationComponent.ofText(""),
                PropertiesTemplate.conv(iProperty ->{
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
    public abstract int escapeChance();

    public void useMagic(Magic magic) {
        if(player.getSTATES().MP >= magic.getMana()) {
            player.useMana(magic.getMana());
            ENTITIES.begin(entity -> returnEnemyActionForMagic(magic, entity));
        }else{
            Event.get(AbstractBattle.class, "battle_free").clear();
            CHOOSE.setVisible(false);
            CHOOSE.setVisible(true);
        }
    }

    private void returnEnemyActionForMagic(Magic m, AbstractEntity entity) {
        if(entity == null) {
            Event.get(AbstractBattle.class, "battle_free").clear();
            CHOOSE.setVisible(false);
            CHOOSE.setVisible(true);
        }else{
            Event.get(AbstractBattle.class, "battle_free").setEvent(false);
            Event.get(AbstractBattle.class, "battle_choose").setEvent(false);
            m.skill(Main.scene, iProperty -> {
                for(Enemy e : enemy){
                    if(e.getSTATES().HP <= 0){
                        e.takeReward(player, sumItem, container);
                    }
                }
                playerRecivedDamage(0);
            },  player, entity, enemy);
        }
    }
}
