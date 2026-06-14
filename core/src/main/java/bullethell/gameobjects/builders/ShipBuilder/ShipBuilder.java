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

    /**
     * Sets the sprite used for rendering
     * @param sprite a Texture to display
     * @return this
     */
    public ShipBuilder setSprite(Texture sprite) {
        this.sprite = sprite;
        return this;
    }

    /**
     * Add a weapon to the list of current weapons. There is currently no limit on the number of weapons
     * @param weapon new weapon to add
     * @return this
     */
    public ShipBuilder addWeapon(Weapon weapon) {
        this.weapons.add(weapon);
        return this;
    }

    /**
     * Add a shield to protect the ship
     * @param shield to add
     * @return this
     */
    public ShipBuilder setShield(Shield shield) {
        this.shield = shield;
        return this;
    }

    /**
     * Resets values. Needed to not have multiple builder instances
     */
    public void reset() {
        sprite = null;
        shield = null;
        weapons.clear();
    }
}
