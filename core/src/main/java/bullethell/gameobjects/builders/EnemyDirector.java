package bullethell.gameobjects.builders;

import bullethell.GameContext;
import bullethell.gameobjects.ships.Enemy;
import bullethell.gameobjects.Weapon;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class EnemyDirector {

    public Enemy basicEnemy(GameContext context, ArrayList<Vector2> path) {
        Texture sprite = context.getPlayerSprite();
        EnemyBuilder builder = new EnemyBuilder(context);
        builder.setSprite(sprite)
               .setSpeed(150f)
               .setPath(path)
               .setShootDelay(1f)
               .setNbShots(-1);

        Enemy enemy = builder.build();
        Weapon weapon = new WeaponDirector().enemySpreadWeapon(new WeaponBuilder(context), enemy);
        enemy.addWeapon(weapon);

        return enemy;
    }

    public Enemy heavyEnemy(GameContext context, ArrayList<Vector2> path) {
        Texture sprite = context.getPlayerSprite();
        EnemyBuilder builder = new EnemyBuilder(context);
        builder.setSprite(sprite)
               .setSpeed(75f)
               .setPath(path)
               .setShootDelay(0.5f)
               .setNbShots(-1);

        Enemy enemy = builder.build();
        Weapon weapon = new WeaponDirector().enemySpreadWeapon(new WeaponBuilder(context), enemy);
        enemy.addWeapon(weapon);

        return enemy;
    }
}
