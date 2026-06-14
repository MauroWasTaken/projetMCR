package bullethell.gameobjects.builders.ShipBuilder;

import bullethell.GameContext;
import bullethell.gameobjects.builders.BuildingErrorException;
import bullethell.gameobjects.ships.Player;
import bullethell.gameobjects.Weapon;
import bullethell.gameobjects.Shield;
import bullethell.gameobjects.supermove.SuperMove;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class PlayerBuilder extends ShipBuilder {

    private SuperMove specialMove;

    public PlayerBuilder(GameContext context) {
        super(context);
    }

    @Override
    public PlayerBuilder addWeapon(Weapon weapon) {
        this.weapons.add(weapon);
        return this;
    }

    @Override
    public PlayerBuilder addShield(Shield shield) {
        this.shield = shield;
        return this;
    }

    @Override
    public PlayerBuilder setSprite(Texture sprite) {
        this.sprite = sprite;
        return this;
    }

    public PlayerBuilder addSpecial(SuperMove special) {
        this.specialMove = special;
        return this;
    }

    public Player build() {

        if (sprite == null) throw new BuildingErrorException("Player requires a sprite");

        Player player = new Player(context, sprite);
        for (Weapon w : weapons) {
            player.addWeapon(w);
        }
        if (this.shield != null) {
            player.setShield(this.shield);
        }
        if (specialMove != null) {
            player.setSpecial(specialMove);
        }
        return player;
    }

    @Override
    public void reset() {
        super.reset();
        specialMove = null;
    }
}
