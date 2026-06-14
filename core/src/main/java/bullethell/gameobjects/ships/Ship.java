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

    //TODO Make it so weapon places are computed dynamically
    float[] xOffsets = {-20, 20, 2, 4};
    float[] yOffsets = {-1, -1, 0.5f, 1};
    int nbWeapons = 0;

    protected float invulnerabilityTime = 1.5f;
    protected float invulnerabilityTimer = 0f;

    protected Ship(GameContext context, Texture sprite, float speed, float width, float height) {
        super(context, width, height);
        this.sprite = sprite;
        this.speed = speed;
        this.weapons = new ArrayList<>();
    }

    public void addWeapon(Weapon weapon) {

        this.weapons.add(weapon);
        weapon.setOwner(this);
        weapon.setOffset(xOffsets[nbWeapons], yOffsets[nbWeapons]);
        ++nbWeapons;
    }

    public boolean isInvulnerable() {
        return invulnerabilityTimer > 0f;
    }

    @Override
    public void update(float delta) {
        for (Weapon w : weapons) w.update(delta);
        if (invulnerabilityTimer > 0f) {
            invulnerabilityTimer -= delta;
        }
        if (shield != null) {
            shield.update(delta);
        }
    }

    @Override
    public void onCollision(GameObject other) {
        if (isInvulnerable()) return;

        if (shield != null && shield.getHp() > 0) {
            shield.onCollision(other);
            if (!shield.isSuperCharged()) {
                setInvulnerabilityTimer(invulnerabilityTime);
            }
        } else {
            die();
        }
    }

    protected void die() {
        if (shield != null) context.despawn(shield);
        context.despawn(this);
        context.spawn(new ExplodingShip(context, this.width, this.height, this.x, this.y));
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

    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    public void setInvulnerabilityTimer(float time) {
        if (invulnerabilityTimer <= 0f)
            invulnerabilityTimer = time;
    }

    public double getShootingX(){
        return this.x + shootingOffsetX;
    }
    public double getShootingY(){
        return this.y + shootingOffsetY;
    }

    public float getX() { return this.x; }
    public float getY() { return this.y; }
}
