package bullethell.gameobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class GameObject {
    protected float x, y;

    public abstract void update(float delta);

    public abstract void render(SpriteBatch batch);

    public abstract void dispose();
}
