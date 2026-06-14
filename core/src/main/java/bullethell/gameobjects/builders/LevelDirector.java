package bullethell.gameobjects.builders;

import bullethell.GameContext;
import bullethell.Level;
import bullethell.gameobjects.spawners.EnemySpawner;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class LevelDirector {

    public Level level1(LevelBuilder builder, GameContext context) {
        EnemySpawner spawner = new EnemySpawner(context);
        float playWidth = context.getPlayWidth();
        float playHeight = context.getPlayHeight();

        ArrayList<Vector2> path = new ArrayList<>();
        path.add(new Vector2(playWidth, 0));
        path.add(new Vector2(20, -playHeight / 2));
        path.add(new Vector2(playWidth, -playHeight));

        ArrayList<Vector2> path2 = new ArrayList<>();
        for (Vector2 point : path) {
            float mirroredX = playWidth - point.x;
            path2.add(new Vector2(mirroredX, point.y));
        }

        for (int i = 0; i < 20; i++) {
            builder.addSpawn(2 + i * 0.2f, spawner.makeBasicEnemy(path));
            builder.addSpawn(2 + i * 0.2f, spawner.makeBasicEnemy(path2));
        }

        return builder.build();
    }


    public Level level2(LevelBuilder builder, GameContext context) {
        EnemySpawner spawner = new EnemySpawner(context);
        float playWidth = context.getPlayWidth();
        float playHeight = context.getPlayHeight();

        ArrayList<Vector2> path = new ArrayList<>();
        path.add(new Vector2(50, 0));
        path.add(new Vector2(50, -playHeight / 2));
        path.add(new Vector2(50, 0));

        ArrayList<Vector2> path2 = new ArrayList<>();
        for (Vector2 point : path) {
            float mirroredX = playWidth - point.x;
            path2.add(new Vector2(mirroredX, point.y));
        }

        ArrayList<Vector2> path3 = new ArrayList<>();
        path3.add(new Vector2(playWidth / 3, 0));
        path3.add(new Vector2(playWidth / 3, -playHeight / 3));
        path3.add(new Vector2(playWidth / 3, -playHeight / 3));
        path3.add(new Vector2(playWidth / 3, -playHeight / 3));
        path3.add(new Vector2(playWidth / 3, 0));

        ArrayList<Vector2> path4 = new ArrayList<>();
        path4.add(new Vector2(2 * playWidth / 3, 0));
        path4.add(new Vector2(2 * playWidth / 3, -playHeight / 3));
        path4.add(new Vector2(2 * playWidth / 3, -playHeight / 3));
        path4.add(new Vector2(2 * playWidth / 3, -playHeight / 3));
        path4.add(new Vector2(2 * playWidth / 3, 0));

        for (int i = 0; i < 10; ++i) {
            builder.addSpawn(4 + i * 0.5f, spawner.makeHeavyEnemy(path));
            builder.addSpawn(4 + i * 0.5f, spawner.makeHeavyEnemy(path2));
            if (i == 7) {
                builder.addSpawn(4, spawner.makeHeavyEnemy(path3));
                builder.addSpawn(4, spawner.makeHeavyEnemy(path4));
            }
        }

        return builder.build();
    }
}
