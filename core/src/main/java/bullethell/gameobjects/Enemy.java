package bullethell.gameobjects;

import bullethell.GameContext;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class Enemy extends GameObject {
    private final GameContext context;
    private final Texture sprite;
    private final ArrayList<Vector2> path;
    private int targetIndex = 0;
    final float speed = 150f;

    public Enemy(GameContext context, ArrayList<Vector2> points) {
        this.context = context;
        this.sprite = context.getPlayerSprite();
        this.path = points == null ? new ArrayList<>() : new ArrayList<>(points);

        if (!this.path.isEmpty()) {
            Vector2 start = this.path.get(0);
            x = start.x;
            y = context.getPlayHeight() + start.y;
            targetIndex = 1;
        }
    }

    @Override
    public void update(float delta) {
        if (path.isEmpty() || targetIndex >= path.size()) {
            context.despawn(this);
            return;
        }
        Vector2 target = path.get(targetIndex);
        float targetY = context.getPlayHeight() + target.y;
        float dx = target.x - x;
        float dy = targetY - y;
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        float step = speed * delta;

        if (distance <= step || distance == 0f) {
            x = target.x;
            y = targetY;
            targetIndex++;
            return;
        }

        x += dx / distance * step;
        y += dy / distance * step;
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(sprite, x - sprite.getWidth() / 2f, y - sprite.getHeight() / 2f);
    }

    @Override
    public void dispose() {
        // might remove this later idk yet
    }
}
