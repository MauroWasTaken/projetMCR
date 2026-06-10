package bullethell.gameobjects;

import bullethell.GameContext;
import bullethell.gameobjects.ships.Enemy;
import bullethell.gameobjects.ships.Ship;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Weapon extends GameObject{
    Ship owner;
    Projectile[] projectiles;
    Texture sprite;
    TextureRegion[][] frames2D;
    TextureRegion[] frames;
    Animation<TextureRegion> animation;
    private float firingRate;
    private float shootTimer = 0;

    private float offsetX, offsetY;

    public Weapon (GameContext context, Projectile[] projectiles, Texture sprite, float firingRate){
        super(context, sprite.getWidth() * 0.1f, sprite.getHeight() * 0.1f);
        this.projectiles = projectiles;
        frames2D = TextureRegion.split(sprite, 48, 48);
        frames = frames2D[0];
        animation = new Animation<>(0.1f, frames);
        this.firingRate = firingRate;
    }

    public void fire(){
        if (shootTimer < firingRate) {
            return;
        }
        shootTimer = 0;
        TextureRegion currentFrame = animation.getKeyFrame(shootTimer, true);
        float naturalWidth = currentFrame.getRegionWidth() * 0.7f;
        float naturalHeight = currentFrame.getRegionHeight() * 0.7f;

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
        TextureRegion currentFrame = animation.getKeyFrame(shootTimer, true); // true = loop
        float naturalWidth = currentFrame.getRegionWidth();
        float naturalHeight = currentFrame.getRegionHeight();
        batch.draw(currentFrame,
            x - naturalWidth / 2f,
            y - naturalHeight / 2f,
            naturalWidth,
            naturalHeight);
    }

    @Override
    public void OnCollision(GameObject other) {

    }
}

