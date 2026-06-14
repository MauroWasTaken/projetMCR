package bullethell.gameobjects.supermove;

import bullethell.gameobjects.Weapon;
import com.badlogic.gdx.audio.Sound;

import java.util.ArrayList;

/**
 * A kind of supermove. Fires each weapon once is a 360 pattern
 */
public class SuperLaser extends SuperMove {

    private final Sound soundFx;

    public SuperLaser(Sound soundFx) {
        this.maxCharges = 3;
        this.soundFx = soundFx;
    }

    @Override
    public void trigger() {
        if (hasRunOutOfCharges()) return;
        maxCharges--;
        ArrayList<Weapon> weapons = owner.getWeapons();
        for (Weapon w : weapons) {
            w.supercharge();
        }
        if (soundFx != null) {
            soundFx.play();
        }
    }
}
