package bullethell.gameobjects.super_move;

import bullethell.gameobjects.Weapon;

import java.util.ArrayList;

public class SuperLaser extends SuperMove {

    int maxUses = 3;

    @Override
    public void trigger() {
        if (maxUses <= 0) return;
        maxUses--;
        ArrayList<Weapon> weapons = owner.getWeapons();
        for (Weapon w : weapons) {
            w.supercharge();
        }
    }
}
