package bullethell.gameobjects.builders;

import bullethell.GameContext;
import bullethell.gameobjects.Projectile;
import bullethell.gameobjects.Weapon;
import bullethell.gameobjects.WeaponSoundFx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

/**
 * Builds weapons
 */
public class WeaponBuilder {

    private final GameContext context;

    private Texture sprite;
    private final ArrayList<Projectile> templates = new ArrayList<>();
    private float firingRate = -1f;
    private WeaponSoundFx soundFx;

    public WeaponBuilder(GameContext context) {
        this.context = context;
    }

    /**
     * Add a sprite to be rendered
     * @param sprite to render
     * @return this
     */
    public WeaponBuilder addSprite(Texture sprite) {
        this.sprite = sprite;
        return this;
    }

    /**
     * Sets this weapon's firing rate
     * @param rate at which the weapon fires
     * @return this
     */
    public WeaponBuilder setFiringRate(float rate) {
        this.firingRate = rate;
        return this;
    }

    /**
     * Add a new projectile to fire each time the gun is fired
     * @param xOffset x spawn position
     * @param yOffset y spawn position
     * @param velocityX x direction
     * @param velocityY y direction
     * @param isPlayerProjectile is the projectile coming from the player
     * @param sprite sprite to render
     * @return this
     */
    public WeaponBuilder addProjectile(float xOffset, float yOffset, float velocityX, float velocityY, boolean isPlayerProjectile, Texture sprite) {
        templates.add(new Projectile(context, xOffset, yOffset, velocityX, velocityY, isPlayerProjectile, sprite));
        return this;
    }

    /**
     * Add a sound to the weapon
     * @param sound to be played when firing
     * @param volume at which the sound is played
     * @return this
     */
    public WeaponBuilder addSoundFx(Sound sound, float volume) {
        this.soundFx = new WeaponSoundFx(sound, volume);
        return this;
    }

    public GameContext getContext() {
        return context;
    }

    /**
     * Build a new weapon
     * @return a new weapon
     */
    public Weapon build() {
        if (templates.isEmpty()) { throw new BuildingErrorException("Weapon requires at least one projectile template"); }
        return new Weapon(context, templates.toArray(new Projectile[0]), sprite, soundFx, firingRate);
    }

    public void reset() {
        sprite = null;
        templates.clear();
        firingRate = -1f;
        soundFx = null;
    }
}
