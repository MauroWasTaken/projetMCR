package bullethell.gameobjects.builders;

import bullethell.GameContext;
import bullethell.gameobjects.ships.Ship;
import bullethell.gameobjects.Weapon;

public class WeaponDirector {

    public Weapon playerMainWeapon(WeaponBuilder builder, Ship ship) {
        GameContext context = builder.getContext();
        builder.addProjectile(0, 0, 0, 400, true, context.getProjectileSprite());
        return builder.build(ship);
    }

    public Weapon playerSideWeapons(WeaponBuilder builder, Ship ship) {
        GameContext context = builder.getContext();
        builder.addProjectile(0, 0, -200, 400, true, context.getProjectileSprite());
        builder.addProjectile(0, 0, 200, 400, true, context.getProjectileSprite());
        return builder.build(ship);
    }

    public Weapon enemySpreadWeapon(WeaponBuilder builder, Ship ship) {
        GameContext context = builder.getContext();
        builder.addProjectile(0, 0, 0, -100, false, context.getProjectileSprite());
        builder.addProjectile(0, 0, -100, -100, false, context.getProjectileSprite());
        builder.addProjectile(0, 0, 100, -100, false, context.getProjectileSprite());
        return builder.build(ship);
    }
}
