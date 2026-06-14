package bullethell.gameobjects.builders.ShipBuilder;

import bullethell.GameContext;
import bullethell.gameobjects.builders.BuildingErrorException;
import bullethell.gameobjects.ships.Enemy;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import bullethell.gameobjects.Weapon;
import bullethell.gameobjects.Shield;

import java.util.ArrayList;

/**
 * Builds enemies
 */
public class EnemyBuilder extends ShipBuilder {

    public EnemyBuilder(GameContext context) {
        super(context);
    }

    @Override
    public EnemyBuilder setSprite(Texture sprite) {
        this.sprite = sprite;
        return this;
    }

    @Override
    public EnemyBuilder addWeapon(Weapon weapon) {
        this.weapons.add(weapon);
        return this;
    }

    @Override
    public EnemyBuilder addShield(Shield shield) {
        this.shield = shield;
        return this;
    }

    public EnemyBuilder setSpeed(float speed) {
        this.speed = speed;
        return this;
    }

    public EnemyBuilder setPath(ArrayList<Vector2> path) {
        this.path = path;
        return this;
    }

    public EnemyBuilder setShootDelay(float shootDelay) {
        this.shootDelay = shootDelay;
        return this;
    }

    public EnemyBuilder setNbShots(int nbShots) {
        this.nbShots = nbShots;
        return this;
    }

    public EnemyBuilder setScoreValue(int scoreValue) {
        this.scoreValue = scoreValue;
        return this;
    }

    public Enemy build() {

        if (path == null) throw new BuildingErrorException("Enemy requires a path"); //todo path needs to have 2 points
        if (sprite == null) throw new BuildingErrorException("Enemy requires a sprite");
        if (speed == -1f) throw new BuildingErrorException("Enemy requires a speed");
        if (shootDelay == -1f) throw new BuildingErrorException("Enemy requires a shoot delay");
        if (nbShots == -2) throw new BuildingErrorException("Enemy requires a number of shots");

        Enemy enemy = new Enemy(context, sprite, speed, path, shootDelay, nbShots, scoreValue);

        for (Weapon w : weapons) {
            enemy.addWeapon(w);
        }
        if (this.shield != null) {
            enemy.setShield(this.shield);
        }
        return enemy;
    }

    @Override
    public void reset() {
        super.reset();
        speed = -1f;
        path = null;
        shootDelay = -1f;
        nbShots = -2;

    }

    private float speed = -1f;
    private ArrayList<Vector2> path;
    private float shootDelay = -1f;
    private int nbShots = -2;
    private int scoreValue = 0;
}
