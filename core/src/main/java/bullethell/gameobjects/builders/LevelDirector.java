package bullethell.gameobjects.builders;

import bullethell.GameContext;
import bullethell.Level;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class LevelDirector {

    public Level level1(LevelBuilder builder, GameContext context) {
        EnemyDirector enemyDirector = new EnemyDirector();
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
            builder.addSpawn(2 + i * 0.2f, enemyDirector.basicEnemy(context, path));
            builder.addSpawn(2 + i * 0.2f, enemyDirector.basicEnemy(context, path2));
        }

        return builder.build();
    }
}
