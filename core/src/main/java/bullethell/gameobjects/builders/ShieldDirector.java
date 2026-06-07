package bullethell.gameobjects.builders;

import bullethell.gameobjects.Shield;

public class ShieldDirector {

    public Shield weakShield() {
        // 1 HP, no recharge
        return new Shield(1, -1f);
    }

    public Shield strongShield() {
        // 5 HP, 2 second recharge
        return new Shield(5, 2000f);
    }

    public Shield quickRechargeShield() {
        // 2 HP, 0.5 second recharge
        return new Shield(2, 500f);
    }
}
