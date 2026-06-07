package bullethell.gameobjects.builders;

import bullethell.GameContext;
import bullethell.gameobjects.ships.Ship;
import bullethell.gameobjects.Weapon;

public class WeaponDirector {

    public Weapon playerMainWeapon(WeaponBuilder builder) {
        GameContext context = builder.getContext();
        builder.addProjectile(0, 0, 0, 400, true, context.getProjectileSprite());
        return builder.build();
    }

    public Weapon playerSideWeapons(WeaponBuilder builder) {
        GameContext context = builder.getContext();
        builder.addProjectile(0, 0, -200, 400, true, context.getProjectileSprite());
        builder.addProjectile(0, 0, 200, 400, true, context.getProjectileSprite());
        return builder.build();
    }

    public Weapon enemySpreadWeapon(WeaponBuilder builder) {
        GameContext context = builder.getContext();
        builder.addProjectile(0, 0, 0, -100, false, context.getProjectileSprite());
        builder.addProjectile(0, 0, -100, -100, false, context.getProjectileSprite());
        builder.addProjectile(0, 0, 100, -100, false, context.getProjectileSprite());
        return builder.build();
    }
}
