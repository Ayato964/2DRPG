package ayato.entity;

import ayato.item.Item;
import ayato.item.ItemFactory;
import ayato.magic.MagicFactory;
import ayato.rpg.EnemyFactory;
import ayato.system.ValueContainer;
import com.fasterxml.jackson.databind.JsonNode;
import org.ayato.animation.image.ImageMaker;
import org.ayato.animation.text.properties.PropertyAction;
import org.ayato.system.LunchScene;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Random;

public class Enemy extends AbstractEntity{
    JsonNode eStates;
    private final int U_HP, U_MP, U_G, U_ATK, U_EXP;
    private final int U_DEFENCE, U_AVOID;
    private final int D_CHANCE;
    private final JsonNode ITEMS;
    public Enemy(LunchScene m, ImageMaker maker, @NotNull JsonNode entity_states) {
        super(m, maker, 0, 0, 0, 0);
        eStates = entity_states;
        U_HP = entity_states.get(EnemyFactory.U_HP).asInt();
        U_G = entity_states.get(EnemyFactory.DROP).get(EnemyFactory.D_U_G).asInt();
        U_ATK = entity_states.get(EnemyFactory.U_ATK).asInt();
        U_EXP = entity_states.get(EnemyFactory.U_EXP).asInt();
        U_MP = entity_states.get(EnemyFactory.U_MP).asInt();
        U_DEFENCE = entity_states.get(EnemyFactory.U_DF).asInt();
        U_AVOID = entity_states.get(EnemyFactory.U_AVOID).asInt();
        ITEMS = entity_states.get(EnemyFactory.DROP).get(EnemyFactory.D_ITEMS);
        D_CHANCE = entity_states.get(EnemyFactory.DROP).get(EnemyFactory.D_CHANCE).asInt();
        reset();
    }

    public void setEnemyLevel(int playerLv, int minLv,  int maxLv){
        STATES.LV = new Random().nextInt(minLv, playerLv + maxLv);
        STATES = new EntityStates(
                STATES.NAME,
                STATES.LV,
                STATES.HP + STATES.LV * U_HP - U_HP,
                STATES.EXP + STATES.LV * U_EXP - U_EXP,
                STATES.MP + STATES.LV * U_MP - U_MP,
                STATES.G + STATES.LV * U_G - U_G,
                STATES.ATK + STATES.LV * U_ATK - U_ATK,
                STATES.POW_CHANCE,
                STATES.DF + STATES.LV * U_DEFENCE - U_DEFENCE,
                STATES.AVOID + STATES.LV * U_AVOID - U_AVOID
        );
        for(int i = 0; i < ITEMS.size(); i ++){
            Item item = ItemFactory.ITEMS.get(ITEMS.get(i).asText()).get();
            if(item != null)
                STATES.inventory.add(item);
        }
        for(int i = 0; i < eStates.get("magic").size(); i ++){
            STATES.magic.add(MagicFactory.MAGICS.get(eStates.get("magic").get(i).asText()).get());
        }
    }

    @Override
    protected void reset() {
        STATES = new EntityStates(
                eStates.get(EnemyFactory.NAME).asText(),
                1,
                eStates.get(EnemyFactory.HP).asInt(),
                eStates.get(EnemyFactory.EXP).asInt(),
                eStates.get(EnemyFactory.MP).asInt(),
                eStates.get(EnemyFactory.DROP).get(EnemyFactory.D_G).asInt(),
                eStates.get(EnemyFactory.ATK).asInt(),
                eStates.get(EnemyFactory.POW).asInt(),
                eStates.get(EnemyFactory.DF).asInt(),
                eStates.get(EnemyFactory.AVOID).asInt()
                );
        for(int i = 0; i < eStates.get("magic").size(); i ++){
            STATES.magic.add(MagicFactory.MAGICS.get(eStates.get("magic").get(i).asText()).get());
        }
    }
    public void takeReward(Player p, ArrayList<Item> itemList, ValueContainer container) {
        int r = new Random().nextInt(0, 1000);
        if(r < D_CHANCE + p.STATES.POW_CHANCE && ITEMS.size() != 0){
            int i = new Random().nextInt(0, ITEMS.size());
            itemList.add(ItemFactory.ITEMS.get(ITEMS.get(i).asText()).get());
        }else
            container.set(ValueContainer.G, container.get(ValueContainer.G) + STATES.G);

        container.set(ValueContainer.EXP, container.get(ValueContainer.EXP) + STATES.EXP);
    }

    public void generateRandomMagic(LunchScene scene, PropertyAction after, AbstractEntity self, AbstractEntity enemy, Enemy[] enemies) {
        int size = STATES.magic.length();
        int r = new Random().nextInt(0, size);
        STATES.magic.run(scene, after, self, enemy, enemies, r);
    }
}
