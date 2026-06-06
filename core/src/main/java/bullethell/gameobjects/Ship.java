package bullethell.gameobjects;

import bullethell.GameContext;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Polygon;

import java.util.ArrayList;

public abstract class Ship extends GameObject{
    double shootingOffsetX,shootingOffsetY;
    final Texture sprite;
    final float speed;
    ArrayList<Weapon> weapons;

    protected Ship(GameContext context, Texture sprite, float speed, float width, float height) {
        super(context, width, height);
        this.sprite = sprite;
        this.speed = speed;
        this.weapons = new ArrayList<>();
    }

    @Override
    public void OnCollision(GameObject other) {
        //todo add explosion effect
        context.despawn(this);
    }

    @Override
    public Polygon getHitbox() {
        hitbox.setPosition(x - width / 2f, y - height / 2f);
        hitbox.setRotation(45f);
        return hitbox;
    }

    public double getShootingX(){
        return this.x + shootingOffsetX;
    }
    public double getShootingY(){
        return this.y + shootingOffsetY;
    }
}
