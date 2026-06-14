package bullethell.gameobjects.super_move;

public class SuperShield extends SuperMove {

    float maxTime = 5f;

    int maxUses = 1;

    @Override
    public void trigger() {
        if (maxUses <= 0) return;
        --maxUses;
        if (owner.getShield() == null) return;
        owner.getShield().supercharge(maxTime);
    }
}
