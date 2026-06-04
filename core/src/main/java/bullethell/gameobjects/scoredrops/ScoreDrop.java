package bullethell.gameobjects.scoredrops;

import bullethell.GameContext;
import bullethell.gameobjects.GameObject;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class ScoreDrop extends GameObject {
    protected final int scoreWin;
    protected final Texture sprite; // Flyweight pattern

    protected ScoreDrop(GameContext context, int heldValue, Texture sprite) {
        super(context);
        this.scoreWin = heldValue;
        this.sprite = sprite;
    }

    @Override
    public void update(float delta) {
        // Drop falls vertically
        y -= delta;

        // screen limits
        float spriteHalfWidth = sprite.getWidth() / 2f;
        float spriteHalfHeight = sprite.getHeight() / 2f;
        float maxX = context.getPlayWidth() - spriteHalfWidth;
        float maxY = context.getPlayHeight() - spriteHalfHeight;
        x = Math.max(spriteHalfWidth, Math.min(x, maxX));
        y = Math.max(spriteHalfHeight, Math.min(y, maxY));
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(sprite, x - sprite.getWidth() / 2f, y - sprite.getHeight() / 2f);
    }

    @Override
    public void dispose() {
        // might remove
    }
}
