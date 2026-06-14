package bullethell.gameobjects.ships;

import bullethell.GameContext;
import bullethell.gameobjects.GameObject;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * An exploding ship, just before it dies
 */
public class ExplodingShip extends GameObject {
    private float stateTime = 0f;

    // Used to display exploding animation
    final TextureRegion[][] frames2D;
    final TextureRegion[] frames;
    final Animation<TextureRegion> animation;

    protected ExplodingShip(GameContext context, float width, float height, float xPos, float yPos) {
        super(context, width, height);
        frames2D = TextureRegion.split(context.getExplosionSprite(), 48, 48);
        frames = frames2D[0];
        animation = new Animation<>(0.1f, frames);
        x = xPos;
        y = yPos;
    }

    @Override
    public void render(SpriteBatch batch) {
        final TextureRegion currentFrame = animation.getKeyFrame(stateTime, false);
        final float naturalWidth = currentFrame.getRegionWidth();
        final float naturalHeight = currentFrame.getRegionHeight();
        batch.draw(currentFrame,
            x - naturalWidth / 2f,
            y - naturalHeight / 2f,
            naturalWidth,
            naturalHeight);
        if (animation.isAnimationFinished(stateTime)) {
            context.despawn(this);
        }
    }

    @Override
    public void onCollision(GameObject other) {
        // do nothing
    }

    @Override
    public void update(float delta) {
        // Simply fall down
        stateTime += delta;
    }
}
