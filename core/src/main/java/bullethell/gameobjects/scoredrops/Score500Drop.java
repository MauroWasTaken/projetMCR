package bullethell.gameobjects.scoredrops;

import bullethell.GameContext;
import bullethell.gameobjects.GameObject;
import com.badlogic.gdx.graphics.Texture;

public class Score500Drop extends ScoreDrop {
    private static final int VALUE = 500;
    private static final int SPEED = 100;

    public Score500Drop(GameContext context, float x, float y) {
        super(context, VALUE, SPEED,x,y ,context.get500PointsSprite());
    }
    @Override
    public void OnCollision(GameObject other) {
        // does nothing
    }
}
