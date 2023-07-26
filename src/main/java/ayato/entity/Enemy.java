package ayato.entity;

import ayato.rpg.EnemyFactory;
import com.fasterxml.jackson.databind.JsonNode;
import org.ayato.animation.image.ImageMaker;
import org.ayato.system.LunchScene;

import java.util.Random;

public class Enemy extends AbstractEntity{
    JsonNode eStates;
    private final int U_HP, U_MP, U_G, U_ATK, U_EXP;
    private final double U_DEFENCE;
    public Enemy(LunchScene m, ImageMaker maker, JsonNode entity_states) {
        super(m, maker, 0, 0, 0, 0);
        eStates = entity_states;
        U_HP = entity_states.get(EnemyFactory.U_HP).asInt();
        U_G = entity_states.get(EnemyFactory.U_G).asInt();
        U_ATK = entity_states.get(EnemyFactory.U_ATK).asInt();
        U_EXP = entity_states.get(EnemyFactory.U_EXP).asInt();
        U_MP = entity_states.get(EnemyFactory.U_MP).asInt();
        U_DEFENCE = entity_states.get(EnemyFactory.U_DF).asDouble();
        reset();
    }
    public void setEnemyLevel(int maxLv){
        STATES.LV = new Random().nextInt(STATES.LV, maxLv);
        STATES = new EntityStates(
                STATES.NAME,
                STATES.LV,
                STATES.HP + STATES.LV * U_HP - U_HP,
                STATES.EXP + STATES.LV * U_EXP - U_EXP,
                STATES.MP + STATES.LV * U_MP - U_MP,
                STATES.G + STATES.LV * U_G - U_G,
                STATES.ATK + STATES.LV * U_ATK - U_ATK,
                STATES.DF + STATES.LV * U_DEFENCE - U_DEFENCE
        );
    }

    @Override
    protected void reset() {
        STATES = new EntityStates(
                eStates.get(EnemyFactory.NAME).asText(),
                eStates.get(EnemyFactory.MIN_LV).asInt(),
                eStates.get(EnemyFactory.HP).asInt(),
                eStates.get(EnemyFactory.EXP).asInt(),
                eStates.get(EnemyFactory.MP).asInt(),
                eStates.get(EnemyFactory.G).asInt(),
                eStates.get(EnemyFactory.ATK).asInt(),
                eStates.get(EnemyFactory.DF).asDouble()
                );
    }
}
