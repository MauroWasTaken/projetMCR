package bullethell.gameobjects.builders;

import bullethell.GameContext;
import bullethell.gameobjects.Weapon;

/**
 * Creates different types of weapons
 */
public class WeaponDirector {

    /**
     * Create a main weapon for the player
     * @param builder to use to create the weapon
     * @return a new weapon
     */
    public Weapon playerMainWeapon(WeaponBuilder builder) {
        builder.reset();
        GameContext context = builder.getContext();
        builder.addProjectile(0, 0, 0, 400, true, context.getProjectileSprite());
        builder.addSprite(context.getBasicGunSprite());
        builder.addSoundFx(context.getMainWeaponFx(), 1f);
        builder.setFiringRate(0.4f);
        return builder.build();
    }

    public Weapon playerSideWeapons(WeaponBuilder builder) {
        builder.reset();
        GameContext context = builder.getContext();
        builder.addProjectile(0, 0, -200, 400, true, context.getProjectileSprite())
            .addProjectile(0, 0, 200, 400, true, context.getProjectileSprite())
            .addSprite(context.getBasicGunSprite())
            .addSoundFx(context.getSideWeaponFx(), 0.3f)
            .setFiringRate(1.2f);
        return builder.build();
    }

    public Weapon enemySpreadWeapon(WeaponBuilder builder) {
        builder.reset();
        GameContext context = builder.getContext();
        builder.addProjectile(0, 0, 0, -170, false, context.getEnemyProjectileSprite())
            .addProjectile(0, 0, -100, -100, false, context.getEnemyProjectileSprite())
            .addProjectile(0, 0, 100, -100, false, context.getEnemyProjectileSprite())
            .addSprite(context.getBasicGunSprite())
            .addSoundFx(context.getEnemyShootFx(), 0.5f)
            .setFiringRate(0.5f);
        return builder.build();
    }
}
