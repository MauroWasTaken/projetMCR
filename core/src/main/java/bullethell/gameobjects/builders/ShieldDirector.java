package bullethell.gameobjects.builders;

import bullethell.GameContext;
import bullethell.gameobjects.Shield;

public class ShieldDirector {

    private final GameContext context;

    public ShieldDirector(GameContext context) {
        this.context = context;
    }

    public Shield weakShield() {
        // 1 HP, no recharge
        return new Shield(context, context.getShieldSprite(), context.getSuperShieldSprite(), 1, -1f);
    }

    public Shield strongShield() {
        // 5 HP, 0 second recharge
        return new Shield(context, context.getShieldSprite(), context.getSuperShieldSprite(),3, -1f);
    }

    public Shield quickRechargeShield() {
        // 1 HP, 20 second recharge
        return new Shield(context, context.getShieldSprite(), context.getSuperShieldSprite(),1, 20000f);
    }
}
