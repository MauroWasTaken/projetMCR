package bullethell.gameobjects.super_move;

import bullethell.gameobjects.ships.Ship;

public abstract class SuperMove {

    protected Ship owner;
    protected int maxCharges;

    public abstract void trigger();
    public void setOwner(Ship owner) {
        this.owner = owner;
    }

    protected boolean checkMaxCharges() {
        return maxCharges <= 0;
    }

    public int getRemainingCharges() {
        return maxCharges;
    }

}
