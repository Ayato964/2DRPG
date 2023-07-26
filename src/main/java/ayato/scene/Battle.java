package ayato.scene;

import ayato.entity.Enemy;
import ayato.entity.Player;
import ayato.rpg.EnemyFactory;
import ayato.system.JsonComponent;
import com.fasterxml.jackson.databind.JsonNode;
import org.ayato.system.LunchScene;

import java.util.Random;

public class Battle extends AbstractBattle{
    JsonNode stage_states;

    protected Battle(Player player_states, JsonNode stage_info) {
        super(player_states);
        stage_states = stage_info;
    }

    @Override
    protected Enemy[] getEnemy(LunchScene MASTER) {
        JsonNode enemy_states = stage_states.get(JsonComponent.ENEMY);
        Enemy[] enemy =new Enemy[new Random().nextInt(1, enemy_states.get(JsonComponent.ENEMY_NUM).asInt() + 1)];
        JsonNode enemies = enemy_states.get(JsonComponent.ENEMY_ENTITIES);

        for(int i = 0; i < enemy.length; i ++){
            enemy[i] = EnemyFactory.ENEMIES.get(enemies.get(new Random().nextInt(enemies.size())).asText()).get();
            enemy[i].setEnemyLevel(enemy_states.get(JsonComponent.ENEMY_MAXLV).asInt());
        }
        return enemy;
    }
}
