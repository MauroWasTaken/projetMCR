package bullethell.gameobjects.builders;

import bullethell.GameContext;
import bullethell.gameobjects.ships.Player;

import java.util.ArrayList;

public class PlayerBuilder {
    private final GameContext context;

    private final ArrayList<WeaponBuilder> weaponBuilders = new ArrayList<>();

    public PlayerBuilder(GameContext context) {
        this.context = context;
    }

    public PlayerBuilder addWeapon(WeaponBuilder weaponBuilder) {
        this.weaponBuilders.add(weaponBuilder);
        return this;
    }

    public Player build() {
        Player player = new Player(context);
        for (WeaponBuilder wb : weaponBuilders) {
            player.addWeapon(wb.build(player));
        }
        return player;
    }
}
