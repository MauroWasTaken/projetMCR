package bullethell.gameobjects.supermove;

/**
 * A kind of supermove. The shield becomes indestructible and kills enemies on collision
 */
public class SuperShield extends SuperMove {

    final float maxTime = 5f;

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
