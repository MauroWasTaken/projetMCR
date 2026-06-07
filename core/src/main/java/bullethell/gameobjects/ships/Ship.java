package bullethell.gameobjects.ships;

import bullethell.GameContext;
import bullethell.gameobjects.GameObject;
import bullethell.gameobjects.Shield;
import bullethell.gameobjects.Weapon;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Polygon;

import java.util.ArrayList;

public abstract class Ship extends GameObject {
    double shootingOffsetX,shootingOffsetY;
    final Texture sprite;
    final float speed;
    ArrayList<Weapon> weapons;
    Shield shield;

    protected Ship(GameContext context, Texture sprite, float speed, float width, float height) {
        super(context, width, height);
        this.sprite = sprite;
        this.speed = speed;
        this.weapons = new ArrayList<>();
    }

    public void addWeapon(Weapon weapon) {
        this.weapons.add(weapon);
    }

    @Override
    public void update(float delta) {
        if (shield != null) {
            shield.update(delta);
        }
    }

    @Override
    public void OnCollision(GameObject other) {
        if (shield != null && shield.getHp() > 0) {
            shield.hit();
        } else {
            die();
        }
    }

    protected void die() {
        //todo add explosion effect
        context.despawn(this);
    }

    @Override
    public Polygon getHitbox() {
        if (shield != null && shield.getHp() > 0) {
            hitbox.setScale(sprite.getHeight() / width * 0.6f, sprite.getHeight() / height * 0.6f);
        } else {
            hitbox.setScale(1, 1);
        }
        hitbox.setPosition(x - width / 2f, y - height / 2f);
        hitbox.setRotation(45f);
        return hitbox;
    }
    public Shield getShield(){
        return shield;
    }
    public void setShield(Shield shield) {
        this.shield = shield;
    }

    public double getShootingX(){
        return this.x + shootingOffsetX;
    }
    public double getShootingY(){
        return this.y + shootingOffsetY;
    }
}
