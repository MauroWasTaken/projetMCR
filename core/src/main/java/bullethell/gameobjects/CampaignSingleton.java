package bullethell.gameobjects;

import bullethell.GameContext;
import bullethell.Level;
import bullethell.gameobjects.builders.LevelBuilder;
import bullethell.gameobjects.builders.LevelDirector;
import bullethell.gameobjects.factories.EnemySpawner;

/**
 * Represents a campaign, or one run through multiple levels until death
 */
public class CampaignSingleton {
    private static CampaignSingleton instance;
    private int nextLevel;
    private final int MAX_LEVEL = 4;
    private int score;

    private final LevelDirector director;
    private EnemySpawner enemySpawner;
    private LevelBuilder levelBuilder;

    private CampaignSingleton() {
        reset();
        nextLevel = 1;
        director = new LevelDirector();
    }

    public static CampaignSingleton getInstance() {
        if (CampaignSingleton.instance == null) {
            CampaignSingleton.instance = new CampaignSingleton();
        }
        return CampaignSingleton.instance;
    }

    /**
     * Checks if the campaign is over
     * @return true if it is over
     */
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

    /**
     * Creates the next level to play
     * @param context game context
     * @return a new level
     */
    public Level getNextLevel(GameContext context) {

        if (this.enemySpawner == null) enemySpawner = new EnemySpawner(context);
        if (this.levelBuilder == null) levelBuilder = new LevelBuilder(context);

        return switch (this.nextLevel) {
            case 1 -> director.level1(levelBuilder, context, enemySpawner);
            case 2 -> director.level2(levelBuilder, context, enemySpawner);
            case 3 -> director.level3(levelBuilder, context, enemySpawner);
            case 4 -> director.level4(levelBuilder, context, enemySpawner);
            default -> director.level1(levelBuilder, context, enemySpawner); // TODO: clean up that shi, emergency fallback for now, but shouldn't happen in normal playthrough
        };
    }
}
