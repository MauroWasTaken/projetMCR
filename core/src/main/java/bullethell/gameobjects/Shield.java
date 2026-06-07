package bullethell.gameobjects;

import bullethell.gameobjects.ships.Ship;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Shield  {
    final private int maxHP;
    private int hp;
    final private float rechargeTime; // in ms  -1 for no recharge
    private float rechargeTimer;

    public Shield(int maxHP, float rechargeTime) {
        this.maxHP = maxHP;
        this.hp = maxHP;
        this.rechargeTime = rechargeTime;
    }

    public void update(float delta) {
        if (hp < maxHP) {
            rechargeTimer += delta * 1000f;
            if ( rechargeTime != -1 && rechargeTimer >= rechargeTime ) {
                hp++;
                rechargeTimer = 0f;
            }
        }
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
