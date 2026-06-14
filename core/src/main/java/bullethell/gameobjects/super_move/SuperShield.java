package bullethell.gameobjects.super_move;

public class SuperShield extends SuperMove {

    float maxTime = 5f;

    public SuperShield() {
        maxCharges = 1;
    }

    @Override
    public void trigger() {
        if (checkMaxCharges()) return;
        if (owner.getShield() == null) return;
        owner.getShield().supercharge(maxTime);
    }
}
