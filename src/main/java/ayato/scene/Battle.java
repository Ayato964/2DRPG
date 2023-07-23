package ayato.scene;

import ayato.entity.Enemy;
import ayato.entity.Player;

public class Battle extends AbstractBattle{

    protected Battle(Player player_states) {
        super(player_states);
    }

    @Override
    protected Enemy[] getEnemy() {
        return new Enemy[0];
    }
}
