package bullethell.gameobjects.scoredrops;

import bullethell.GameContext;
import com.badlogic.gdx.graphics.Texture;

public class Score500Drop extends ScoreDrop {
    private static final int VALUE = 500;

    Score500Drop(GameContext context, float speed, Texture sprite) {
        super(context, VALUE, speed, sprite);
    }
}
