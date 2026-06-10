package bullethell.gameobjects;

import bullethell.GameContext;
import bullethell.gameobjects.ships.Ship;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Shield extends GameObject {
    final private int maxHP;
    private int hp;
    final private float rechargeTime; // in ms  -1 for no recharge
    private float rechargeTimer;
    private final Texture sprite;

    Ship owner;


    public Shield(GameContext context, Texture sprite, int maxHP, float rechargeTime) {
        super(context, context.getShieldSprite().getWidth() * 0.1f, context.getShieldSprite().getHeight() * 0.1f);
        this.maxHP = maxHP;
        this.hp = maxHP;
        this.rechargeTime = rechargeTime;
        this.sprite = sprite;
    }

    public void setOwner(Ship owner) {
        this.owner = owner;
        this.x = owner.getX();
        this.y = owner.getY();
    }

    public void update(float delta) {
        if (hp < maxHP) {
            rechargeTimer += delta * 1000f;
            if ( rechargeTime != -1 && rechargeTimer >= rechargeTime ) {
                hp++;
                rechargeTimer = 0f;
            }
        }

        this.x = owner.getX();
        this.y = owner.getY();
    }

    @Override
    public void render(SpriteBatch batch) {
        if (hp > 0) batch.draw(sprite, x - sprite.getWidth() / 2f, y - sprite.getHeight() / 2f);
    }

    @Override
    public void OnCollision(GameObject other) {

    }

    public void hit(){
        if (hp == 0) return;
        this.hp--;
        rechargeTimer = 0f;


    }
    public int getHp(){
        return hp;
    }

}
