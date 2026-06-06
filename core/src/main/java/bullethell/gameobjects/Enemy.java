package bullethell.gameobjects;

import bullethell.GameContext;
import bullethell.gameobjects.scoredrops.Score100Drop;
import bullethell.gameobjects.scoredrops.Score500Drop;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class Enemy extends Ship {
    final float dropRate100 = 0.1f;
    final float dropRate500 = 0.02f;
    private final ArrayList<Vector2> path;
    private int targetIndex = 0;
    private final float shootDelay; // in ms
    private float shootTimer; // in ms
    private int nbShots; // -1 for "infinite"

    public Enemy(GameContext context, ArrayList<Vector2> points, float shootDelay, int nbShots) {
        super(context, context.getPlayerSprite(), 150f, context.getPlayerSprite().getWidth()*0.6f, context.getPlayerSprite().getHeight()*0.6f);
        this.path = points == null ? new ArrayList<>() : new ArrayList<>(points);
        this.shootDelay = shootDelay;
        this.nbShots = nbShots;

        shootingOffsetX = 0f;
        shootingOffsetY = -sprite.getHeight() / 2f;

        weapons.add(new Weapon(this, new Projectile[]{
            new Projectile(context, 0, 0, 0, -100, false, context.getProjectileSprite()),
            new Projectile(context, 0, 0, -100, -100, false, context.getProjectileSprite()),
            new Projectile(context, 0, 0, 100, -100, false, context.getProjectileSprite())
        }));

        if (!this.path.isEmpty()) {
            Vector2 start = this.path.get(0);
            x = start.x;
            y = context.getPlayHeight() + start.y;
            targetIndex = 1;
        }
    }

    @Override
    public void update(float delta) {
        // check if path is done
        if (path.isEmpty() || targetIndex >= path.size()) {
            context.despawn(this);
            return;
        }
        // go to closest path
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
        // update position
        x += dx / distance * step;
        y += dy / distance * step;

        // check for fire
        shootTimer += delta;
        if (shootTimer > shootDelay && nbShots != 0){
            for( Weapon w : weapons){
                w.fire();
            }
            shootTimer = 0;
            nbShots--;
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(sprite, x - sprite.getWidth() / 2f, y - sprite.getHeight() / 2f);
    }
    @Override
    public void OnCollision(GameObject other) {
        super.OnCollision(other);
        if (Math.random() < dropRate500) {
            context.spawn( new Score500Drop(context, x,y));
        }else if (Math.random() < dropRate100) {
            context.spawn( new Score100Drop(context, x,y));
        }
    }
}
