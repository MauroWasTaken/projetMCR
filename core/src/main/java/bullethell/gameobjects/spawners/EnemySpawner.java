package bullethell.gameobjects.spawners;

import bullethell.GameContext;
import bullethell.gameobjects.builders.EnemyDirector;
import bullethell.gameobjects.builders.ShipBuilder.EnemyBuilder;
import bullethell.gameobjects.builders.WeaponBuilder;
import bullethell.gameobjects.ships.Enemy;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

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

    public Enemy makeBasicEnemy(ArrayList<Vector2> path) {
        return director.basicEnemy(context, enemyBuilder, weaponBuilder, path);
    }

    public Enemy makeHeavyEnemy(ArrayList<Vector2> path) {
        return director.heavyEnemy(context, enemyBuilder, weaponBuilder, path);
    }
}
