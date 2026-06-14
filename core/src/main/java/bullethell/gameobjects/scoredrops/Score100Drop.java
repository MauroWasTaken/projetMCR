package bullethell.gameobjects.scoredrops;

import bullethell.GameContext;
import bullethell.gameobjects.GameObject;
import com.badlogic.gdx.graphics.Texture;

public class Score100Drop extends ScoreDrop {
    private static final int VALUE = 100;
    private static final int SPEED = 100;

    public Score100Drop(GameContext context, float x, float y) {
        super(context, VALUE, SPEED, x, y, context.get100PointsSprite());
    }

    @Override
    public void onCollision(GameObject other) {
        // does nothing
    }
}
