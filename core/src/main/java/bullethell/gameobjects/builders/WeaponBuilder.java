package bullethell.gameobjects.builders;

import bullethell.GameContext;
import bullethell.gameobjects.Projectile;
import bullethell.gameobjects.Weapon;
import bullethell.gameobjects.WeaponSoundFx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class WeaponBuilder {
    private final GameContext context;
    private final ArrayList<Projectile> templates = new ArrayList<>();
    private Texture sprite;
    private float firingRate;
    private WeaponSoundFx soundFx;

    public WeaponBuilder(GameContext context) {
        this.context = context;
    }

    public WeaponBuilder addSprite(Texture sprite) {
        this.sprite = sprite;
        return this;
    }

    public WeaponBuilder setFiringRate(float rate) {
        this.firingRate = rate;
        return this;
    }

    public WeaponBuilder addProjectile(float xOffset, float yOffset, float velocityX, float velocityY, boolean isPlayerProjectile, Texture sprite) {
        templates.add(new Projectile(context, xOffset, yOffset, velocityX, velocityY, isPlayerProjectile, sprite));
        return this;
    }

    public WeaponBuilder addSoundFx(Sound sound, float volume) {
        this.soundFx = new WeaponSoundFx(sound, volume);
        return this;
    }


    public GameContext getContext() {
        return context;
    }

    public Weapon build() {
        if (templates.isEmpty()) {
            throw new BuildingErrorException("Weapon requires at least one projectile template");
        }
        return new Weapon(context, templates.toArray(new Projectile[0]), sprite, soundFx, firingRate);
    }
}
