package bullethell.gameobjects.super_move;

import bullethell.gameobjects.Weapon;

import java.util.ArrayList;

public class SuperLaser extends SuperMove {

    public SuperLaser() {
        maxCharges = 3;
    }

    @Override
    public void trigger() {
        if (checkMaxCharges()) return;
        maxCharges--;
        ArrayList<Weapon> weapons = owner.getWeapons();
        for (Weapon w : weapons) {
            w.supercharge();
        }
    }
}
