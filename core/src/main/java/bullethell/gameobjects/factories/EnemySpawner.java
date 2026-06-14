package bullethell.gameobjects.factories;

import bullethell.GameContext;
import bullethell.gameobjects.builders.EnemyDirector;
import bullethell.gameobjects.builders.ShipBuilder.EnemyBuilder;
import bullethell.gameobjects.builders.WeaponBuilder;
import bullethell.gameobjects.ships.Enemy;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

/**
 * Factory for spawning enemies
 */
public class EnemySpawner {

    private final EnemyDirector director;
    private final EnemyBuilder enemyBuilder;
    private final WeaponBuilder weaponBuilder;
    private final GameContext context;

    public EnemySpawner(GameContext context) {
        this.context = context;
        this.director = new EnemyDirector();
        this.enemyBuilder = new EnemyBuilder(context);
        this.weaponBuilder = new WeaponBuilder(context);
    }

    /**
     * Makes a basic enemy
     * @param path the enemy will follow. Set in LevelDirector
     * @return a new basic enemy
     */
    public Enemy makeBasicEnemy(ArrayList<Vector2> path) {
        return director.basicEnemy(context, enemyBuilder, weaponBuilder, path);
    }

    /**
     * Makes a heavy enemy
     * @param path the enemy will follow. Set in LevelDirector
     * @return a new heavy enemy
     */
    public Enemy makeHeavyEnemy(ArrayList<Vector2> path) {
        return director.heavyEnemy(context, enemyBuilder, weaponBuilder, path);
    }
}
