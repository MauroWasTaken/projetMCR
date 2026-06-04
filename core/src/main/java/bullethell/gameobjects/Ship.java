package bullethell.gameobjects;

import bullethell.GameContext;
import com.badlogic.gdx.graphics.Texture;

public abstract class Ship extends GameObject{
    double shootingOffsetX,shootingOffsetY;
    final Texture sprite;
    final float speed;

    protected Ship(GameContext context, Texture sprite, float speed) {
        super(context);
        this.sprite = sprite;
        this.speed = speed;
    }

    public double getShootingX(){
        return this.x + shootingOffsetX;
    }
    public double getShootingY(){
        return this.y + shootingOffsetY;
    }

}
