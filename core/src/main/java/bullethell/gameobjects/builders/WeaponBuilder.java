package bullethell.gameobjects.builders;

import bullethell.GameContext;
import bullethell.gameobjects.Projectile;
import bullethell.gameobjects.ships.Ship;
import bullethell.gameobjects.Weapon;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class WeaponBuilder {
    private final GameContext context;
    private final ArrayList<Projectile> templates = new ArrayList<>();

    public WeaponBuilder(GameContext context) {
        this.context = context;
    }

    public WeaponBuilder addProjectile(float xOffset, float yOffset, float velocityX, float velocityY, boolean isPlayerProjectile, Texture sprite) {
        templates.add(new Projectile(context, xOffset, yOffset, velocityX, velocityY, isPlayerProjectile, sprite));
        return this;
    }

    public GameContext getContext() {
        return context;
    }
    public Weapon build() {
        if (templates.isEmpty()) {
            throw new BuildingErrorException("Weapon requires at least one projectile template");
        }
        return new Weapon(templates.toArray(new Projectile[0]));
    }
}
