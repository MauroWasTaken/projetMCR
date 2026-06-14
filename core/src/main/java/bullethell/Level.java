package bullethell;

import bullethell.gameobjects.ships.Enemy;

import java.util.ArrayList;

/**
 * A game level
 */
public class Level {
    private float x = 0;
    private final GameContext context;

    // enemy spawn management
    private final ArrayList<EnemySpawn> spawnQueue = new ArrayList<>();
    private int nextSpawnIndex = 0;

    // class that represents when and what kind of enemies will be spawned (might be used in builder)
    public static class EnemySpawn {
        public final float time;
        public final Enemy enemy;

        public EnemySpawn(float time, Enemy enemy) {
            this.time = time;
            this.enemy = enemy;
        }
    }

    public Level(GameContext context, ArrayList<EnemySpawn> spawnQueue) {
        this.context = context;
        if (spawnQueue != null) {
            this.spawnQueue.addAll(spawnQueue);
        }
    }

    public void update(float delta) {
        x += delta;
        //System.out.println("Level time passed :" + x);
        // check if any enemies should be spawned
        while (nextSpawnIndex < spawnQueue.size()) {
            EnemySpawn event = spawnQueue.get(nextSpawnIndex);
            if (x >= event.time) {
                context.spawn(event.enemy);
                nextSpawnIndex++;
            } else {
                break;
            }
        }
    }
    public boolean isFinished(){
        return nextSpawnIndex == spawnQueue.size();
    }
}
