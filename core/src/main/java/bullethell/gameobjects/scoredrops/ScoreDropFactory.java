package bullethell.gameobjects.scoredrops;

import bullethell.GameContext;

public class ScoreDropFactory {
    private final GameContext context;

    private final float SPEED = 75f;

    public ScoreDropFactory(GameContext context) {
        this.context = context;
    }

    public ScoreDrop create100PointsDrop() {
        return new Score100Drop(context, SPEED, context.get100PointsSprite());
    }

    public ScoreDrop create500PointsDrop() {
        return new Score500Drop(context, SPEED, context.get500PointsSprite());
    }
}
