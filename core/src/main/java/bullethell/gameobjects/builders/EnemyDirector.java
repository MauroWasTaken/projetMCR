package bullethell.gameobjects.builders;

import bullethell.GameContext;
import bullethell.gameobjects.builders.ShipBuilder.EnemyBuilder;
import bullethell.gameobjects.ships.Enemy;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class EnemyDirector {

    private WeaponDirector weaponDirector = new WeaponDirector();

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

    public Enemy heavyEnemy(GameContext context, EnemyBuilder builder, WeaponBuilder weaponBuilder, ArrayList<Vector2> path) {
        Texture sprite = context.getHeavyEnemySprite();

        builder.reset();
        builder.setSprite(sprite)
               .setSpeed(75f)
               .setPath(path)
               .setShootDelay(0.8f)
               .setNbShots(-1)
               .setScoreValue(150)
               .addShield(new ShieldDirector(context).weakShield())
               .addWeapon(weaponDirector.enemySpreadWeapon(weaponBuilder));

        return builder.build();
    }
}
