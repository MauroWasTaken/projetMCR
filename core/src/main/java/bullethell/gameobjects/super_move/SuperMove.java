package bullethell.gameobjects.super_move;

import bullethell.gameobjects.ships.Ship;

public abstract class SuperMove {

    protected Ship owner;

    public abstract void trigger();
    public void setOwner(Ship owner) {
        this.owner = owner;
    }

}
