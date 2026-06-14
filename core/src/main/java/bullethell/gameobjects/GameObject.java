package bullethell.gameobjects;

import bullethell.GameContext;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;

/**
 * General game object.
 */
public abstract class GameObject {
    protected float x, y;
    protected final float width, height;
    protected final GameContext context;
    protected final Polygon hitbox;

    protected GameObject(GameContext context, float width, float height) {
        this.width = width;
        this.height = height;
        this.context = context;
        this.hitbox = new Polygon(new float[]{
            0, 0,
            width, 0,
            width, height,
            0, height
        });
        this.hitbox.setOrigin(width / 2f, height / 2f);
    }

    public abstract void update(float delta);

    public abstract void render(SpriteBatch batch);

    public abstract void onCollision(GameObject other);

    public Polygon getHitbox() {
        hitbox.setPosition(x - width / 2f, y - height / 2f);
        return hitbox;
    }

    public boolean collidesWith(GameObject other) {
        if (other == null) return false;
        return Intersector.overlapConvexPolygons(this.getHitbox(), other.getHitbox());
    }

}
