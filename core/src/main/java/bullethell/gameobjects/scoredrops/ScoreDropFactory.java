package bullethell.gameobjects.scoredrops;

import bullethell.GameContext;

public class ScoreDropFactory {
    private final GameContext context;

    public ScoreDropFactory(GameContext context) {
        this.context = context;
    }

    public ScoreDrop create100PointsDrop() {
        return new Score100Drop(context, context.get100PointsSprite());
    }

    public ScoreDrop create500PointsDrop() {
        return new Score500Drop(context, context.get500PointsSprite());
    }
}
