package bullethell.gameobjects.scoredrops;

import bullethell.GameContext;
import com.badlogic.gdx.graphics.Texture;

public class Score100Drop extends ScoreDrop {
    private static final int VALUE = 100;
    Score100Drop(GameContext context, float speed, Texture sprite) {
        super(context, VALUE, speed, sprite);
    }
}
