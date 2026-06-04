package bullethell.gameobjects;

import bullethell.GameContext;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class GameObject {
    protected float x, y;
    final GameContext context;

    protected GameObject(GameContext context) {
        this.context = context;
    }

    public abstract void update(float delta);

    public abstract void render(SpriteBatch batch);

    public abstract void dispose();
}
