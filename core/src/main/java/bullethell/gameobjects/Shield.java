package bullethell.gameobjects;

import bullethell.GameContext;
import bullethell.gameobjects.ships.Ship;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Shield extends GameObject {
    final private int maxHP;
    private int hp;
    final private float rechargeTime; // in ms  -1 for no recharge
    private float rechargeTimer;

    TextureRegion[][] frames2D;
    TextureRegion[] frames;
    Animation<TextureRegion> animation;
    private float stateTime = 0f;

    Ship owner;


    public Shield(GameContext context, Texture sprite, int maxHP, float rechargeTime) {
        super(context, sprite.getWidth() * 0.1f, sprite.getHeight() * 0.1f);
        this.maxHP = maxHP;
        this.hp = maxHP;
        this.rechargeTime = rechargeTime;
        frames2D = TextureRegion.split(sprite, 64, 64);
        frames = frames2D[0];
        animation = new Animation<>(0.1f, frames);
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

        stateTime += delta;
        this.x = owner.getX();
        this.y = owner.getY();
    }

    @Override
    public void render(SpriteBatch batch) {
        if (hp > 0) {
            TextureRegion currentFrame = animation.getKeyFrame(stateTime, true); // true = loop
            float naturalWidth = currentFrame.getRegionWidth();
            float naturalHeight = currentFrame.getRegionHeight();
            batch.draw(currentFrame,
                x - naturalWidth / 2f,
                y - naturalHeight / 2f,
                naturalWidth,
                naturalHeight);
        }
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
