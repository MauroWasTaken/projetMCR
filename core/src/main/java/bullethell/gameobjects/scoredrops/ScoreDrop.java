package bullethell.gameobjects.scoredrops;

import bullethell.GameContext;
import bullethell.currencysystem.CurrencyBank;
import bullethell.gameobjects.GameObject;
import bullethell.gameobjects.ships.Player;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class ScoreDrop extends GameObject {
    protected final int scoreWin;
    protected final Texture sprite; // Flyweight pattern
    protected final float speed;

    protected ScoreDrop(GameContext context, int heldValue, float speed, float x, float y, Texture sprite)  {
        super(context, sprite.getWidth() * 0.33f, sprite.getHeight() * 0.33f); // the sprites are so big
        this.scoreWin = heldValue;
        this.sprite = sprite;
        this.speed = speed;
        this.x = x;
        this.y = y;
    }

    @Override
    public void update(float delta) {
        // Drop falls vertically, no need to wave it left to right
        y -= delta * speed;

        // screen limits only on X
        float halfWidth = width / 2f;
        float maxX = context.getPlayWidth() - halfWidth;
        x = Math.max(halfWidth, Math.min(x, maxX));

        // despawn when it goes off screen
        if (y < -height) {
            context.despawn(this);
            return;
        }

        Player player = context.getPlayer();
        if (player != null && this.collidesWith(player)) {
            CurrencyBank.getInstance().addFunds(scoreWin);
            context.despawn(this);
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(sprite, x - width / 2f, y - height / 2f, width, height);
    }
}
