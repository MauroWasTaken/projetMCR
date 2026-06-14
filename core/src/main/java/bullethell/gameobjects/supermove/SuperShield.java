package bullethell.gameobjects.supermove;

public class SuperShield extends SuperMove {

    float maxTime = 5f;

    public SuperShield() {
        maxCharges = 1;
    }

    @Override
    public void trigger() {
        if (hasRunOutOfCharges()) {
            return;
        }
        maxCharges--;
        if (owner.getShield() == null) {
            return;
        }
        owner.getShield().supercharge(maxTime);
    }
}
