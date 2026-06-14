package bullethell.gameobjects.ships;

import bullethell.GameContext;
import bullethell.gameobjects.Weapon;
import bullethell.gameobjects.scoredrops.Score100Drop;
import bullethell.gameobjects.scoredrops.Score500Drop;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class Enemy extends Ship {
    final float dropRate100 = 0.1f;
    final float dropRate500 = 0.02f;
    private final ArrayList<Vector2> path;
    private int targetIndex = 0;
    private final float shootDelay; // in ms
    private float shootTimer; // in ms
    private int nbShots; // -1 for "infinite"
    private final int scoreValue;

    public Enemy(GameContext context, Texture sprite, float speed, ArrayList<Vector2> path, float shootDelay, int nbShots, int scoreValue) {
        super(context, sprite, speed, sprite.getWidth() * 0.6f, sprite.getHeight() * 0.6f);
        this.path = path == null ? new ArrayList<>() : new ArrayList<>(path);
        this.shootDelay = shootDelay;
        this.nbShots = nbShots;
        this.scoreValue = scoreValue;
        this.shootingOffsetX = 0f;
        this.shootingOffsetY = -sprite.getHeight() / 2f;
        this.invulnerabilityTime = 0f;

        if (!this.path.isEmpty()) {
            Vector2 start = this.path.get(0);
            this.x = start.x;
            this.y = context.getPlayHeight() + start.y;
            this.targetIndex = 1;
        }
    }

    @Override
    public void update(float delta) {
        super.update(delta);
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
        // check for player
        Player player = context.getPlayer();
        if (this.collidesWith(player)){
            player.onCollision(this);
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(sprite, x - sprite.getWidth() / 2f, y - sprite.getHeight() / 2f);
        for (Weapon w : weapons) w.render(batch);
    }
    @Override
    public void die() {
        super.die();
        bullethell.gameobjects.CampaignSingleton.getInstance().addScore(this.scoreValue);
        if (Math.random() < dropRate500) {
            context.spawn( new Score500Drop(context, x,y));
        }else if (Math.random() < dropRate100) {
            context.spawn( new Score100Drop(context, x,y));
        }
    }
}
