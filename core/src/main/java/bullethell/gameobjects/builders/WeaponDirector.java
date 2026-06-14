package bullethell.gameobjects.builders;

import bullethell.GameContext;
import bullethell.gameobjects.Weapon;
import com.badlogic.gdx.graphics.Texture;

public class WeaponDirector {

    public Weapon playerMainWeapon(WeaponBuilder builder) {
        GameContext context = builder.getContext();
        builder.addProjectile(0, 0, 0, 400, true, context.getProjectileSprite());
        builder.addSprite(new Texture("big-gun.png"));
        builder.addSoundFx(context.getMainWeaponFx(), 1f);
        builder.setFiringRate(0.4f);
        return builder.build();
    }

    public Weapon playerSideWeapons(WeaponBuilder builder) {
        GameContext context = builder.getContext();
        builder.addProjectile(0, 0, -200, 400, true, context.getProjectileSprite());
        builder.addProjectile(0, 0, 200, 400, true, context.getProjectileSprite());
        builder.addSprite(new Texture("big-gun.png"));
        builder.addSoundFx(context.getSideWeaponFx(), 0.3f);
        builder.setFiringRate(1.2f);
        return builder.build();
    }

    public Weapon enemySpreadWeapon(WeaponBuilder builder) {
        GameContext context = builder.getContext();
        builder.addProjectile(0, 0, 0, -100, false, context.getEnemyProjectileSprite());
        builder.addProjectile(0, 0, -100, -100, false, context.getEnemyProjectileSprite());
        builder.addProjectile(0, 0, 100, -100, false, context.getEnemyProjectileSprite());
        builder.addSprite(new Texture("big-gun.png"));
        builder.addSoundFx(context.getEnemyShootFx(), 0.5f);
        builder.setFiringRate(0.5f);
        return builder.build();
    }
}
