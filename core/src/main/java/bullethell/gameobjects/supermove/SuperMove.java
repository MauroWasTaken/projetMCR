package bullethell.gameobjects.supermove;

import bullethell.gameobjects.ships.Ship;

public abstract class SuperMove {

    protected Ship owner;
    protected int maxCharges;

    public abstract void trigger();
    public void setOwner(Ship owner) {
        this.owner = owner;
    }

    protected boolean hasRunOutOfCharges() {
        return maxCharges <= 0;
    }

    public int getRemainingCharges() {
        return maxCharges;
    }

}
