package bullethell.gameobjects.ships;

import bullethell.GameContext;
import bullethell.gameobjects.Weapon;
import bullethell.gameobjects.super_move.SuperMove;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

    
import com.badlogic.gdx.math.Vector2;

public class Player extends Ship {

    private final GameContext.ControlMode controlMode;
    private SuperMove specialMove;

    public Player(GameContext context) {
        super(context, context.getPlayerSprite(), 200f, context.getPlayerSprite().getWidth() * 0.1f, context.getPlayerSprite().getHeight() * 0.1f);
        this.x = context.getPlayWidth() / 2f;
        this.y = 50f;
        this.shootingOffsetX = 0f;
        this.shootingOffsetY = sprite.getHeight() / 2f;
        this.controlMode = context.getControlMode();
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if (this.controlMode.equals(GameContext.ControlMode.KEYBOARD)) {
            this.handleKeyboardInput(delta);
        } else if (this.controlMode.equals(GameContext.ControlMode.MOUSE)) {
            this.handleMouseInput();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            for (Weapon w : weapons) {
                w.fire();
            }
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        if (isInvulnerable()) {
            if ((int)(invulnerabilityTimer * 10) % 2 == 0) return;
        }
        for (Weapon weapon : weapons) {
            weapon.render(batch);
        }
        if (shield != null) shield.render(batch);
        batch.draw(sprite, x - sprite.getWidth() / 2f, y - sprite.getHeight() / 2f);
    }

    private void handleKeyboardInput(float delta) {
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
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            for (Weapon w : weapons) {
                w.fire();
            }
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
            if (this.specialMove != null) {
                specialMove.trigger();
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
        if (isInvulnerable() && !shield.isSuperCharged()) {
            if ((int)(invulnerabilityTimer * 10) % 2 == 0) return;
        }
        for (Weapon weapon : weapons) {
            weapon.render(batch);
        }
        if (shield != null) shield.render(batch);
        batch.draw(sprite, x - sprite.getWidth() / 2f, y - sprite.getHeight() / 2f);
    }

    private void handleMouseInput() {
        // Check mouse pointer is within game area
        final float spriteHalfWidth = sprite.getWidth() / 2f;
        final float spriteHalfHeight = sprite.getHeight() / 2f;
        final float maxX = context.getPlayWidth() - spriteHalfWidth;
        final float maxY = context.getPlayHeight() - spriteHalfHeight;

        final Vector2 adjustedMouse = context.unprojectMouse(new Vector2(Gdx.input.getX(), Gdx.input.getY()));
        final float mouseX = adjustedMouse.x;
        final float mouseY = adjustedMouse.y;

        if (mouseX < spriteHalfWidth) {
            x = spriteHalfWidth;
        } else {
            x = Math.min(mouseX, maxX);
        }

        if (mouseY < spriteHalfHeight) {
            y = spriteHalfHeight;
        } else {
            y = Math.min(mouseY, maxY);
        }
    }

    public void setSpecial(SuperMove specialMove) {
        this.specialMove = specialMove;
    }

}
