package bullethell.gameobjects.builders;

import bullethell.GameContext;
import bullethell.Level;
import bullethell.gameobjects.ships.Enemy;

import java.util.ArrayList;

public class LevelBuilder {

    private final GameContext context;
    private final ArrayList<Level.EnemySpawn> spawnQueue = new ArrayList<>();

    public LevelBuilder(GameContext context) {
        this.context = context;
    }

    public LevelBuilder addSpawn(float time, Enemy enemy) {
        spawnQueue.add(new Level.EnemySpawn(time, enemy));
        return this;
    }

    public Level build() {
        if (spawnQueue.isEmpty()) {
            throw new BuildingErrorException("Level requires at least one enemy spawn");
        }
        Level level = new Level(context, spawnQueue);
        spawnQueue.clear();
        return level;
    }
}
