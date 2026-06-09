package bullethell;

import bullethell.gameobjects.Enemy;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Level {
    private float x = 0;
    private final GameContext context;

    // enemy spawn management
    private final ArrayList<EnemySpawn> spawnQueue = new ArrayList<>();
    private int nextSpawnIndex = 0;

    // class that represents when and what kind of enemies will be spawned (might be used in builder)
    public static class EnemySpawn {
        float time;
        Enemy enemy;

        EnemySpawn(float time, Enemy enemy) {
            this.time = time;
            this.enemy = enemy;
        }
    }

    public Level(GameContext context) {
        this.context = context;

        // not needed just starting enemies to test
        ArrayList<Vector2> path = new ArrayList<>();
        path.add(new Vector2(context.getPlayWidth(), 0));
        path.add(new Vector2(20, -context.getPlayHeight() / 2));
        path.add(new Vector2(context.getPlayWidth(), -context.getPlayHeight()));
        ArrayList<Vector2> path2 = new ArrayList<>();
        for (Vector2 point : path) {
            float mirroredX = context.getPlayWidth() - point.x;
            path2.add(new Vector2(mirroredX, point.y));
        }
        for (int i = 0; i < 20; i++) {
            Enemy enemy = new Enemy(context, path, 1, -1);
            spawnQueue.add(new EnemySpawn(2 + i * 0.2f, enemy));
            Enemy enemy2 = new Enemy(context, path2,1 , -1);
            spawnQueue.add(new EnemySpawn(2 + i * 0.2f, enemy2));
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

}
