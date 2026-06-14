package bullethell.gameobjects.builders.ShipBuilder;

import bullethell.GameContext;
import bullethell.gameobjects.Shield;
import bullethell.gameobjects.Weapon;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

/**
 * Builds generic ships
 */
public abstract class ShipBuilder {

    protected final GameContext context;

    protected Texture sprite;
    protected Shield shield;
    protected final ArrayList<Weapon> weapons = new ArrayList<>();

    public ShipBuilder(GameContext context) {
        this.context = context;
    }

    public ShipBuilder setSprite(Texture sprite) {
        this.sprite = sprite;
        return this;
    }

    public ShipBuilder addWeapon(Weapon weapon) {
        this.weapons.add(weapon);
        return this;
    }

    public ShipBuilder addShield(Shield shield) {
        this.shield = shield;
        return this;
    }

    public void reset() {
        sprite = null;
        shield = null;
        weapons.clear();
    }
}
