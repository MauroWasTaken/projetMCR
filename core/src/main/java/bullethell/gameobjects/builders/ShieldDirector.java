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
        return new Shield(context, context.getShieldSprite(), 1, -1f);
    }

    public Shield strongShield() {
        // 5 HP, 7 second recharge
        return new Shield(context, context.getShieldSprite(), 5, 7000f);
    }

    public Shield quickRechargeShield() {
        // 2 HP, 3.5 second recharge
        return new Shield(context, context.getShieldSprite(), 1, 3500f);
    }
}
