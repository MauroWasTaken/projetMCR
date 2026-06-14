package bullethell.gameobjects.builders;

import bullethell.GameContext;
import bullethell.gameobjects.ships.Enemy;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class EnemyDirector {

    public Enemy basicEnemy(GameContext context, ArrayList<Vector2> path) {
        Texture sprite = context.getEnemySprite();
        EnemyBuilder builder = new EnemyBuilder(context);
        builder.setSprite(sprite)
               .setSpeed(150f)
               .setPath(path)
               .setShootDelay(1f)
               .setNbShots(-1)
               .setScoreValue(50)
               .addWeapon(new WeaponDirector().enemySpreadWeapon(new WeaponBuilder(context)));

        return builder.build();
    }

    public Enemy heavyEnemy(GameContext context, ArrayList<Vector2> path) {
        Texture sprite = context.getHeavyEnemySprite();
        EnemyBuilder builder = new EnemyBuilder(context);
        builder.setSprite(sprite)
               .setSpeed(75f)
               .setPath(path)
               .setShootDelay(0.8f)
               .setNbShots(-1)
               .setScoreValue(150)
               .addShield(new ShieldDirector(context).weakShield())
               .addWeapon(new WeaponDirector().enemySpreadWeapon(new WeaponBuilder(context)));

        return builder.build();
    }
}
