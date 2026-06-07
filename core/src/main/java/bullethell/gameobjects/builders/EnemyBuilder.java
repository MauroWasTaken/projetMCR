package bullethell.gameobjects.builders;

import bullethell.GameContext;
import bullethell.gameobjects.ships.Enemy;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class EnemyBuilder {
    private GameContext context;
    private Texture sprite;
    private float speed = -1f;
    private ArrayList<Vector2> path;
    private float shootDelay = -1f;
    private int nbShots = -2;

    private final ArrayList<WeaponBuilder> weaponBuilders = new ArrayList<>();

    public EnemyBuilder(GameContext context) {
        this.context = context;
    }

    public EnemyBuilder setSprite(Texture sprite) {
        this.sprite = sprite;
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

    public EnemyBuilder addWeapon(WeaponBuilder weaponBuilder) {
        this.weaponBuilders.add(weaponBuilder);
        return this;
    }

    public Enemy build() {
        if (path == null) throw new BuildingErrorException("Enemy requires a path"); //todo path needs to have 2 points
        if (sprite == null) throw new BuildingErrorException("Enemy requires a sprite");
        if (speed == -1f) throw new BuildingErrorException("Enemy requires a speed");
        if (shootDelay == -1f) throw new BuildingErrorException("Enemy requires a shoot delay");
        if (nbShots == -2) throw new BuildingErrorException("Enemy requires a number of shots");

        Enemy enemy = new Enemy(context, sprite, speed, path, shootDelay, nbShots);
        for (WeaponBuilder wb : weaponBuilders) {
            enemy.addWeapon(wb.build(enemy));
        }
        return enemy;
    }
}
