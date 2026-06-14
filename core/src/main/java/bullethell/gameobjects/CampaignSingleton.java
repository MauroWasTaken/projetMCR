package bullethell.gameobjects;

import bullethell.GameContext;
import bullethell.Level;
import bullethell.gameobjects.builders.LevelBuilder;
import bullethell.gameobjects.builders.LevelDirector;

public class CampaignSingleton {
    private static CampaignSingleton instance;
    private int nextLevel;
    private final int MAX_LEVEL = 4;
    private int score;

    private CampaignSingleton() {
        reset();
    }

    public static CampaignSingleton getInstance() {
        if (CampaignSingleton.instance == null) {
            CampaignSingleton.instance = new CampaignSingleton();
        }
        return CampaignSingleton.instance;
    }

    public boolean isCampaignCleared() {
        return nextLevel > MAX_LEVEL;
    }

    public void levelSucceeded() {
        this.nextLevel += 1;
    }

    public void reset() {
        this.nextLevel = 1;
        this.score = 0;
    }

    public void addScore(int amount) {
        this.score += amount;
    }

    public int getScore() {
        return this.score;
    }

    public int getCurrentLevel() {
        return this.nextLevel;
    }

    public Level getNextLevel(GameContext context) {
        final LevelDirector director = new LevelDirector();
        return switch (this.nextLevel) {
            case 1 -> director.level1(new LevelBuilder(context), context);
            case 2 -> director.level2(new LevelBuilder(context), context);
            case 3 -> director.level3(new LevelBuilder(context), context);
            case 4 -> director.level4(new LevelBuilder(context), context);
            default -> director.level1(new LevelBuilder(context), context); // TODO: clean up that shi, emergency fallback for now, but shouldn't happen in normal playthrough
        };
    }
}
