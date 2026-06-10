package bullethell.gameobjects.ships;

import bullethell.GameContext;
import bullethell.gameobjects.GameObject;
import bullethell.gameobjects.Shield;
import bullethell.gameobjects.Weapon;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Polygon;

public class Player extends Ship {

    public Player(GameContext context) {
        super(context, context.getPlayerSprite(), 200f, context.getPlayerSprite().getWidth() * 0.1f, context.getPlayerSprite().getHeight() * 0.1f);
        this.x = context.getPlayWidth() / 2f;
        this.y = 50f;
        this.shootingOffsetX = 0f;
        this.shootingOffsetY = sprite.getHeight() / 2f;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        float dirX = 0f;
        float dirY = 0f;
        //input handling
        if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            dirX -= 1f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            dirX += 1f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) {
            dirY += 1f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            dirY -= 1f;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            for (Weapon w : weapons) {
                w.fire(this);
            }
        }

        // normalize movement (in an if statement so that we dont divide by 0)
        if (dirX != 0f || dirY != 0f) {
            float len = (float) Math.sqrt(dirX * dirX + dirY * dirY);
            dirX = dirX / len;
            dirY = dirY / len;
            x += dirX * speed * delta;
            y += dirY * speed * delta;
        }

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
        if (isInvulnerable()) {
            if ((int)(invulnerabilityTimer * 10) % 2 == 0) return;
        }
        batch.draw(sprite, x - sprite.getWidth() / 2f, y - sprite.getHeight() / 2f);
        shield.render(batch);
    }

}
