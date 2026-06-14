package bullethell.gameobjects.builders;

import bullethell.GameContext;
import bullethell.gameobjects.ships.Player;
import bullethell.gameobjects.Weapon;
import bullethell.gameobjects.Shield;
import bullethell.gameobjects.supermove.SuperMove;

import java.util.ArrayList;

public class PlayerBuilder {
    private final GameContext context;

    private final ArrayList<Weapon> weapons = new ArrayList<>();
    private Shield shield;
    private SuperMove specialMove;

    public PlayerBuilder(GameContext context) {
        this.context = context;
    }

    public PlayerBuilder addWeapon(Weapon weapon) {
        this.weapons.add(weapon);
        return this;
    }

    public PlayerBuilder addShield(Shield shield) {
        this.shield = shield;
        return this;
    }

    public PlayerBuilder addSpecial(SuperMove special) {
        this.specialMove = special;
        return this;
    }

    public Player build() {
        Player player = new Player(context);
        for (Weapon w : weapons) {
            player.addWeapon(w);
        }
        if (this.shield != null) {
            player.setShield(this.shield);
            this.shield.setOwner(player);
            context.getGameObjects().add(this.shield);
        }
        if (specialMove != null) {
            player.setSpecial(specialMove);
            this.specialMove.setOwner(player);
        }
        return player;
    }
}
