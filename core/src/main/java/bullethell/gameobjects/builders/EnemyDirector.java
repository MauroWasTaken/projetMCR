package bullethell.gameobjects.builders;

import bullethell.GameContext;
import bullethell.gameobjects.builders.ShipBuilder.EnemyBuilder;
import bullethell.gameobjects.factories.ShieldFactory;
import bullethell.gameobjects.ships.Enemy;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

/**
 * Creates different types of enemies
 */
public class EnemyDirector {

    private WeaponDirector weaponDirector = new WeaponDirector();

    /**
     * Creates a basic enemy
     * @param context game context
     * @param builder builder to use to create the enemy
     * @param weaponBuilder builder to use to create the enemy's weapons
     * @param path the enemy will follow
     * @return an enemy
     */
    public Enemy basicEnemy(GameContext context, EnemyBuilder builder, WeaponBuilder weaponBuilder, ArrayList<Vector2> path) {
        Texture sprite = context.getEnemySprite();

        builder.reset();
        builder.setSprite(sprite)
               .setSpeed(150f)
               .setPath(path)
               .setShootDelay(1f)
               .setNbShots(-1)
               .setScoreValue(50)
               .addWeapon(weaponDirector.enemySpreadWeapon(weaponBuilder));

        return builder.build();
    }

    /**
     * Creates a basic enemy
     * @param context game context
     * @param builder builder to use to create the enemy
     * @param weaponBuilder builder to use to create the enemy's weapons
     * @param path the enemy will follow
     * @return an enemy
     */
    public Enemy heavyEnemy(GameContext context, EnemyBuilder builder, WeaponBuilder weaponBuilder, ArrayList<Vector2> path) {
        Texture sprite = context.getHeavyEnemySprite();

        builder.reset();
        builder.setSprite(sprite)
               .setSpeed(75f)
               .setPath(path)
               .setShootDelay(0.8f)
               .setNbShots(-1)
               .setScoreValue(150)
               .setShield(new ShieldFactory(context).weakShield())
               .addWeapon(weaponDirector.enemySpreadWeapon(weaponBuilder));

        return builder.build();
    }
}
