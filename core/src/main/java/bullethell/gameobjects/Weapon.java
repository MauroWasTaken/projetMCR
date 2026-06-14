package bullethell.gameobjects;

import bullethell.GameContext;
import bullethell.gameobjects.ships.Enemy;
import bullethell.gameobjects.ships.Ship;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Weapon extends GameObject {
    Ship owner;
    final Projectile[] projectiles;
    final Sound soundFx;
    final float volume;
    final TextureRegion[][] frames2D;
    final TextureRegion[] frames;
    final Animation<TextureRegion> animation;
    private final float firingRate;

    private float shootTimer;

    private float offsetX, offsetY;

    public Weapon (GameContext context, Projectile[] projectiles, Texture sprite, WeaponSoundFx soundFx, float firingRate){
        super(context, sprite.getWidth() * 0.1f, sprite.getHeight() * 0.1f);
        this.projectiles = projectiles;
        frames2D = TextureRegion.split(sprite, 48, 48);
        frames = frames2D[0];

        float frameDuration = firingRate / (float) frames.length;
        animation = new Animation<>(frameDuration, frames);
        this.soundFx = soundFx.soundFx();
        this.volume = soundFx.volume();
        this.firingRate = firingRate;
        shootTimer = firingRate;
    }

    public void fire(){
        if (shootTimer < firingRate) {
            return;
        }
        shootTimer = 0;

        for (Projectile p : projectiles) {
            Projectile copy = new Projectile(
                context,
                x - p.x,
                y - p.y,
                p.velocityX,
                p.velocityY,
                p.isPlayerProjectile,
                p.sprite
            );
            context.spawn(copy);
        }
        if (soundFx != null) {
            soundFx.play(volume);
        }
    }

    public void supercharge() {

        int nbShots = 12;

        for (int i = 0; i < nbShots; ++i) {
            double angle = (2 * Math.PI / nbShots) * i;
            double cos = Math.cos(angle);
            double sin = Math.sin(angle);
            for (Projectile p : projectiles) {
                Projectile copy = new Projectile(
                    context,
                    x - p.x,
                    y - p.y,
                    (float) (p.velocityX * cos - p.velocityY * sin),
                    (float) (p.velocityX * sin - p.velocityY * cos),
                    p.isPlayerProjectile,
                    p.sprite
                );
                context.spawn(copy);
            }
        }
    }

    public void setOwner(Ship owner) {
        this.owner = owner;
        this.x = owner.getX();
        this.y = owner.getY();
    }

    public void setOffset(float offsetX, float offsetY) {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    @Override
    public void update(float delta) {
        shootTimer += delta;

        this.x = owner.getX() + offsetX;
        this.y = owner.getY() + offsetY;
    }

    @Override
    public void render(SpriteBatch batch) {
        if (owner instanceof Enemy) return;

        TextureRegion currentFrame;

        if (shootTimer >= firingRate) {
            currentFrame = frames[0];
        } else {
            currentFrame = animation.getKeyFrame(shootTimer, false);
        }

        float naturalWidth = currentFrame.getRegionWidth();
        float naturalHeight = currentFrame.getRegionHeight();
        batch.draw(currentFrame,
            x - naturalWidth / 2f,
            y - naturalHeight / 2f,
            naturalWidth,
            naturalHeight);
    }

    @Override
    public void onCollision(GameObject other) {

    }
}

