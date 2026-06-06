package bullethell.gameobjects;

import bullethell.GameContext;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.badlogic.gdx.math.Polygon;

public class Projectile extends GameObject{
    final float padding = 20f;
    final float velocityX, velocityY, rotation;
    final boolean isPlayerProjectile;
    final Texture sprite;
    public Projectile(GameContext context, float x, float y, float velocityX, float velocityY, boolean isPlayerProjectile, Texture sprite) {
        super(context,sprite.getWidth() * 0.9f, sprite.getHeight() * 0.9f);
        this.isPlayerProjectile = isPlayerProjectile;
        this.x = x;
        this.y = y;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.sprite = sprite;
        this.rotation = (float) (Math.atan2(velocityY, velocityX) * 180 / Math.PI + 90); //got this with the help of ai
    }

    @Override
    public void update(float delta) {
        x += velocityX * delta;
        y += velocityY * delta;

        if (x < -padding || x > context.getPlayWidth() + padding || y < -padding || y > context.getPlayHeight() + padding) {
            context.despawn(this);
        }
        ArrayList<GameObject> gameObjects = new ArrayList<>();
        if (isPlayerProjectile){
            gameObjects.addAll(List.of(context.getEnemies()));
        } else {
            Player player = context.getPlayer();
            if (player != null) {
                gameObjects.add(player);
            }
        }
        boolean hasCollided = false;
        for (GameObject gameObject : gameObjects.stream().filter(e -> e.collidesWith(this)).toList()) {
            gameObject.OnCollision(this);
            hasCollided = true;
        }
        if (hasCollided) context.despawn(this);
    }

    @Override
    public Polygon getHitbox() {
        hitbox.setPosition(x - width / 2f, y - height / 2f);
        hitbox.setRotation(rotation);
        return hitbox;
    }

    @Override
    public void render(SpriteBatch batch) {
        float width = sprite.getWidth();
        float height = sprite.getHeight();
        float originX = width / 2f;
        float originY = height / 2f;

        batch.draw(sprite,
                x - originX,
                y - originY,
                originX,
                originY,
                width,
                height,
                1f,
                1f,
                rotation,
                0,
                0,
                sprite.getWidth(),
                sprite.getHeight(),
                false,
                false);
    }

    @Override
    public void OnCollision(GameObject other) {
            //Do nothing
    }
}
