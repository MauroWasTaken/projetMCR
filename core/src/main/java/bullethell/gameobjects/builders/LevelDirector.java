package bullethell.gameobjects.builders;

import bullethell.GameContext;
import bullethell.Level;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class LevelDirector {
    // the following levels were very much ai assisted, we described the level flow and it implemented it

    public Level level1(LevelBuilder builder, GameContext context) {
        EnemyDirector enemyDirector = new EnemyDirector();
        float playWidth = context.getPlayWidth();
        float playHeight = context.getPlayHeight();
        // Paths
        // path entering from the right going left and leaving at the bottom
        ArrayList<Vector2> pathRight = new ArrayList<>();
        pathRight.add(new Vector2(playWidth - 50, 0));
        pathRight.add(new Vector2(100, -playHeight / 3));
        pathRight.add(new Vector2(playWidth - 50, -playHeight));

        // mirror of previous
        ArrayList<Vector2> pathLeft = new ArrayList<>();
        pathLeft.add(new Vector2(50, 0));
        pathLeft.add(new Vector2(playWidth - 100, -playHeight / 3));
        pathLeft.add(new Vector2(50, -playHeight));

        // middle path
        ArrayList<Vector2> pathCenter = new ArrayList<>();
        pathCenter.add(new Vector2(playWidth / 2, 0));
        pathCenter.add(new Vector2(playWidth / 2, -playHeight));

        // zigzag from the right
        ArrayList<Vector2> zigzagRight = new ArrayList<>();
        zigzagRight.add(new Vector2(playWidth - 80, 0));
        zigzagRight.add(new Vector2(150, -playHeight / 4));
        zigzagRight.add(new Vector2(playWidth - 80, -playHeight / 2));
        zigzagRight.add(new Vector2(150, -playHeight));

        // zigzag from the left
        ArrayList<Vector2> zigzagLeft = new ArrayList<>();
        zigzagLeft.add(new Vector2(80, 0));
        zigzagLeft.add(new Vector2(playWidth - 150, -playHeight / 4));
        zigzagLeft.add(new Vector2(80, -playHeight / 2));
        zigzagLeft.add(new Vector2(playWidth - 150, -playHeight));

        float t = 2f; // start time

        // Waves
        builder.addSpawn(t, enemyDirector.basicEnemy(context, pathCenter));
        t += 3f;
        builder.addSpawn(t, enemyDirector.basicEnemy(context, pathRight));
        t += 3f;
        builder.addSpawn(t, enemyDirector.basicEnemy(context, pathLeft));
        t += 3f;
        builder.addSpawn(t, enemyDirector.basicEnemy(context, pathRight));
        builder.addSpawn(t, enemyDirector.basicEnemy(context, pathLeft));
        t += 4f;
        builder.addSpawn(t, enemyDirector.basicEnemy(context, pathRight));
        builder.addSpawn(t + 1f, enemyDirector.basicEnemy(context, pathRight));
        t += 4f;
        builder.addSpawn(t, enemyDirector.basicEnemy(context, pathLeft));
        builder.addSpawn(t + 1f, enemyDirector.basicEnemy(context, pathLeft));
        t += 4f;
        builder.addSpawn(t, enemyDirector.basicEnemy(context, zigzagRight));
        t += 2f;
        builder.addSpawn(t, enemyDirector.basicEnemy(context, zigzagLeft));
        t += 3f;
        builder.addSpawn(t, enemyDirector.basicEnemy(context, pathRight));
        builder.addSpawn(t, enemyDirector.basicEnemy(context, pathLeft));
        builder.addSpawn(t + 1.5f, enemyDirector.basicEnemy(context, pathCenter));
        t += 4f;
        for (int i = 0; i < 3; i++) {
            builder.addSpawn(t + i * 2.5f, enemyDirector.basicEnemy(context, i % 2 == 0 ? zigzagRight : zigzagLeft));
        }
        t += 10f;
        for (int i = 0; i < 3; i++) {
            builder.addSpawn(t + i * 3f, enemyDirector.basicEnemy(context, pathRight));
            builder.addSpawn(t + i * 3f, enemyDirector.basicEnemy(context, pathLeft));
        }

        return builder.build();
    }


    public Level level2(LevelBuilder builder, GameContext context) {
        EnemyDirector enemyDirector = new EnemyDirector();
        float playWidth = context.getPlayWidth();
        float playHeight = context.getPlayHeight();

        // Paths

        // path entering from the right going left and leaving at the bottom and mirror
        ArrayList<Vector2> sweepRight = new ArrayList<>();
        sweepRight.add(new Vector2(playWidth - 50, 0));
        sweepRight.add(new Vector2(80, -playHeight / 3));
        sweepRight.add(new Vector2(playWidth - 50, -playHeight));

        ArrayList<Vector2> sweepLeft = new ArrayList<>();
        sweepLeft.add(new Vector2(50, 0));
        sweepLeft.add(new Vector2(playWidth - 80, -playHeight / 3));
        sweepLeft.add(new Vector2(50, -playHeight));

        // Straight down center
        ArrayList<Vector2> pathCenter = new ArrayList<>();
        pathCenter.add(new Vector2(playWidth / 2, 0));
        pathCenter.add(new Vector2(playWidth / 2, -playHeight));

        // slow descent on left side, lingers, then exits
        ArrayList<Vector2> heavyLeft = new ArrayList<>();
        heavyLeft.add(new Vector2(playWidth / 4, 0));
        heavyLeft.add(new Vector2(playWidth / 4, -playHeight / 3));
        heavyLeft.add(new Vector2(playWidth / 3, -playHeight / 2));
        heavyLeft.add(new Vector2(playWidth / 4, -playHeight));

        // slow descent on right side, lingers, then exits
        ArrayList<Vector2> heavyRight = new ArrayList<>();
        heavyRight.add(new Vector2(3 * playWidth / 4, 0));
        heavyRight.add(new Vector2(3 * playWidth / 4, -playHeight / 3));
        heavyRight.add(new Vector2(2 * playWidth / 3, -playHeight / 2));
        heavyRight.add(new Vector2(3 * playWidth / 4, -playHeight));

        // slow center descent
        ArrayList<Vector2> heavyCenter = new ArrayList<>();
        heavyCenter.add(new Vector2(playWidth / 2, 0));
        heavyCenter.add(new Vector2(playWidth / 2, -playHeight / 3));
        heavyCenter.add(new Vector2(playWidth / 2, -playHeight));

        // Wide zigzag (basic enemies)
        ArrayList<Vector2> wideZigzagRight = new ArrayList<>();
        wideZigzagRight.add(new Vector2(playWidth - 60, 0));
        wideZigzagRight.add(new Vector2(100, -playHeight / 4));
        wideZigzagRight.add(new Vector2(playWidth - 100, -playHeight / 2));
        wideZigzagRight.add(new Vector2(60, -playHeight));

        ArrayList<Vector2> wideZigzagLeft = new ArrayList<>();
        wideZigzagLeft.add(new Vector2(60, 0));
        wideZigzagLeft.add(new Vector2(playWidth - 100, -playHeight / 4));
        wideZigzagLeft.add(new Vector2(100, -playHeight / 2));
        wideZigzagLeft.add(new Vector2(playWidth - 60, -playHeight));

        float t = 2f;

        // Waves
        builder.addSpawn(t, enemyDirector.basicEnemy(context, sweepRight));
        builder.addSpawn(t + 1f, enemyDirector.basicEnemy(context, sweepLeft));
        t += 5f;
        builder.addSpawn(t, enemyDirector.basicEnemy(context, wideZigzagRight));
        builder.addSpawn(t + 1.5f, enemyDirector.basicEnemy(context, wideZigzagLeft));
        t += 6f;
        builder.addSpawn(t, enemyDirector.heavyEnemy(context, heavyCenter));
        t += 7f;
        builder.addSpawn(t, enemyDirector.heavyEnemy(context, heavyLeft));
        builder.addSpawn(t + 1f, enemyDirector.basicEnemy(context, sweepRight));
        builder.addSpawn(t + 2f, enemyDirector.basicEnemy(context, sweepRight));
        t += 6f;
        builder.addSpawn(t, enemyDirector.basicEnemy(context, sweepLeft));
        builder.addSpawn(t, enemyDirector.basicEnemy(context, sweepRight));
        builder.addSpawn(t + 1.5f, enemyDirector.basicEnemy(context, pathCenter));
        t += 6f;
        builder.addSpawn(t, enemyDirector.heavyEnemy(context, heavyRight));
        builder.addSpawn(t + 2f, enemyDirector.basicEnemy(context, wideZigzagLeft));
        t += 7f;
        for (int i = 0; i < 4; i++) {
            builder.addSpawn(t + i * 2f, enemyDirector.basicEnemy(context, i % 2 == 0 ? sweepRight : sweepLeft));
        }
        t += 10f;
        builder.addSpawn(t, enemyDirector.heavyEnemy(context, heavyLeft));
        builder.addSpawn(t + 1.5f, enemyDirector.heavyEnemy(context, heavyRight));
        t += 8f;
        builder.addSpawn(t, enemyDirector.basicEnemy(context, wideZigzagRight));
        t += 5f;
        builder.addSpawn(t, enemyDirector.heavyEnemy(context, heavyCenter));
        builder.addSpawn(t + 1f, enemyDirector.basicEnemy(context, sweepLeft));
        builder.addSpawn(t + 1f, enemyDirector.basicEnemy(context, sweepRight));
        builder.addSpawn(t + 3f, enemyDirector.basicEnemy(context, wideZigzagLeft));
        builder.addSpawn(t + 3f, enemyDirector.basicEnemy(context, wideZigzagRight));

        return builder.build();
    }

    public Level level3(LevelBuilder builder, GameContext context) {
        EnemyDirector enemyDirector = new EnemyDirector();
        float playWidth = context.getPlayWidth();
        float playHeight = context.getPlayHeight();

        // --- Paths ---

        // Faster sweeps
        ArrayList<Vector2> sweepRight = new ArrayList<>();
        sweepRight.add(new Vector2(playWidth - 40, 0));
        sweepRight.add(new Vector2(60, -playHeight / 3));
        sweepRight.add(new Vector2(playWidth - 40, -playHeight));

        ArrayList<Vector2> sweepLeft = new ArrayList<>();
        sweepLeft.add(new Vector2(40, 0));
        sweepLeft.add(new Vector2(playWidth - 60, -playHeight / 3));
        sweepLeft.add(new Vector2(40, -playHeight));

        // straight lanes
        ArrayList<Vector2> laneLeft = new ArrayList<>();
        laneLeft.add(new Vector2(playWidth / 4, 0));
        laneLeft.add(new Vector2(playWidth / 4, -playHeight));

        ArrayList<Vector2> laneRight = new ArrayList<>();
        laneRight.add(new Vector2(3 * playWidth / 4, 0));
        laneRight.add(new Vector2(3 * playWidth / 4, -playHeight));

        ArrayList<Vector2> laneCenter = new ArrayList<>();
        laneCenter.add(new Vector2(playWidth / 2, 0));
        laneCenter.add(new Vector2(playWidth / 2, -playHeight));

        // zigzags
        ArrayList<Vector2> zigzagRight = new ArrayList<>();
        zigzagRight.add(new Vector2(playWidth - 50, 0));
        zigzagRight.add(new Vector2(120, -playHeight / 5));
        zigzagRight.add(new Vector2(playWidth - 80, -2 * playHeight / 5));
        zigzagRight.add(new Vector2(80, -3 * playHeight / 5));
        zigzagRight.add(new Vector2(playWidth - 50, -playHeight));

        ArrayList<Vector2> zigzagLeft = new ArrayList<>();
        zigzagLeft.add(new Vector2(50, 0));
        zigzagLeft.add(new Vector2(playWidth - 120, -playHeight / 5));
        zigzagLeft.add(new Vector2(80, -2 * playHeight / 5));
        zigzagLeft.add(new Vector2(playWidth - 80, -3 * playHeight / 5));
        zigzagLeft.add(new Vector2(50, -playHeight));

        // Heavy paths
        ArrayList<Vector2> heavyLeft = new ArrayList<>();
        heavyLeft.add(new Vector2(playWidth / 4, 0));
        heavyLeft.add(new Vector2(playWidth / 4, -playHeight / 3));
        heavyLeft.add(new Vector2(playWidth / 3, -playHeight / 2));
        heavyLeft.add(new Vector2(playWidth / 5, -playHeight));

        ArrayList<Vector2> heavyRight = new ArrayList<>();
        heavyRight.add(new Vector2(3 * playWidth / 4, 0));
        heavyRight.add(new Vector2(3 * playWidth / 4, -playHeight / 3));
        heavyRight.add(new Vector2(2 * playWidth / 3, -playHeight / 2));
        heavyRight.add(new Vector2(4 * playWidth / 5, -playHeight));

        ArrayList<Vector2> heavyCenter = new ArrayList<>();
        heavyCenter.add(new Vector2(playWidth / 2, 0));
        heavyCenter.add(new Vector2(playWidth / 2, -playHeight / 4));
        heavyCenter.add(new Vector2(playWidth / 2, -playHeight));

        // Cross paths
        ArrayList<Vector2> crossLeftToRight = new ArrayList<>();
        crossLeftToRight.add(new Vector2(40, 0));
        crossLeftToRight.add(new Vector2(playWidth / 2, -playHeight / 3));
        crossLeftToRight.add(new Vector2(playWidth - 40, -playHeight));

        ArrayList<Vector2> crossRightToLeft = new ArrayList<>();
        crossRightToLeft.add(new Vector2(playWidth - 40, 0));
        crossRightToLeft.add(new Vector2(playWidth / 2, -playHeight / 3));
        crossRightToLeft.add(new Vector2(40, -playHeight));

        float t = 1f;


        // Waves
        builder.addSpawn(t, enemyDirector.basicEnemy(context, sweepRight));
        builder.addSpawn(t, enemyDirector.basicEnemy(context, sweepLeft));
        builder.addSpawn(t + 0.8f, enemyDirector.basicEnemy(context, laneCenter));
        t += 3f;
        builder.addSpawn(t, enemyDirector.basicEnemy(context, crossLeftToRight));
        builder.addSpawn(t, enemyDirector.basicEnemy(context, crossRightToLeft));
        t += 3f;
        for (int i = 0; i < 4; i++) {
            builder.addSpawn(t + i * 1.2f, enemyDirector.basicEnemy(context, i % 2 == 0 ? zigzagRight : zigzagLeft));
        }
        t += 6f;
        builder.addSpawn(t, enemyDirector.basicEnemy(context, laneLeft));
        builder.addSpawn(t, enemyDirector.basicEnemy(context, laneCenter));
        builder.addSpawn(t, enemyDirector.basicEnemy(context, laneRight));
        t += 3f;
        builder.addSpawn(t, enemyDirector.basicEnemy(context, sweepRight));
        builder.addSpawn(t + 0.5f, enemyDirector.basicEnemy(context, sweepRight));
        builder.addSpawn(t + 1f, enemyDirector.basicEnemy(context, sweepLeft));
        builder.addSpawn(t + 1.5f, enemyDirector.basicEnemy(context, sweepLeft));
        t += 4f;
        builder.addSpawn(t, enemyDirector.basicEnemy(context, crossLeftToRight));
        builder.addSpawn(t, enemyDirector.basicEnemy(context, crossRightToLeft));
        builder.addSpawn(t + 1f, enemyDirector.basicEnemy(context, laneCenter));

        // PHASE 2: Heavy introductions (t~25 to ~55s) ===
        t += 4f;
        builder.addSpawn(t, enemyDirector.heavyEnemy(context, heavyCenter));
        builder.addSpawn(t + 0.5f, enemyDirector.basicEnemy(context, sweepLeft));
        builder.addSpawn(t + 1f, enemyDirector.basicEnemy(context, sweepRight));
        t += 4f;
        builder.addSpawn(t, enemyDirector.heavyEnemy(context, heavyLeft));
        builder.addSpawn(t, enemyDirector.heavyEnemy(context, heavyRight));
        t += 2f;
        builder.addSpawn(t, enemyDirector.basicEnemy(context, zigzagRight));
        builder.addSpawn(t + 1f, enemyDirector.basicEnemy(context, zigzagLeft));
        t += 4f;
        builder.addSpawn(t, enemyDirector.heavyEnemy(context, heavyCenter));
        builder.addSpawn(t + 0.5f, enemyDirector.basicEnemy(context, crossLeftToRight));
        builder.addSpawn(t + 0.5f, enemyDirector.basicEnemy(context, crossRightToLeft));
        t += 4f;
        for (int i = 0; i < 6; i++) {
            ArrayList<Vector2> path = i % 3 == 0 ? sweepRight : i % 3 == 1 ? sweepLeft : laneCenter;
            builder.addSpawn(t + i * 0.8f, enemyDirector.basicEnemy(context, path));
        }
        t += 6f;
        builder.addSpawn(t, enemyDirector.heavyEnemy(context, heavyLeft));
        builder.addSpawn(t + 0.5f, enemyDirector.heavyEnemy(context, heavyRight));
        builder.addSpawn(t + 1f, enemyDirector.basicEnemy(context, zigzagLeft));
        builder.addSpawn(t + 1.5f, enemyDirector.basicEnemy(context, zigzagRight));

        // PHASE 3: Endurance gauntlet (t~55 to ~100s) ===
        t += 5f;
        builder.addSpawn(t, enemyDirector.heavyEnemy(context, heavyCenter));
        builder.addSpawn(t, enemyDirector.basicEnemy(context, laneLeft));
        builder.addSpawn(t, enemyDirector.basicEnemy(context, laneRight));
        builder.addSpawn(t + 2f, enemyDirector.basicEnemy(context, laneLeft));
        builder.addSpawn(t + 2f, enemyDirector.basicEnemy(context, laneRight));
        t += 4f;
        for (int i = 0; i < 4; i++) {
            builder.addSpawn(t + i * 1.5f, enemyDirector.basicEnemy(context, crossLeftToRight));
            builder.addSpawn(t + i * 1.5f, enemyDirector.basicEnemy(context, crossRightToLeft));
        }
        t += 7f;
        builder.addSpawn(t, enemyDirector.heavyEnemy(context, heavyLeft));
        builder.addSpawn(t + 2f, enemyDirector.heavyEnemy(context, heavyCenter));
        builder.addSpawn(t + 4f, enemyDirector.heavyEnemy(context, heavyRight));
        t += 3f;
        for (int i = 0; i < 5; i++) {
            builder.addSpawn(t + i * 1f, enemyDirector.basicEnemy(context, i % 2 == 0 ? zigzagRight : zigzagLeft));
        }
        t += 6f;
        builder.addSpawn(t, enemyDirector.basicEnemy(context, laneLeft));
        builder.addSpawn(t, enemyDirector.basicEnemy(context, laneCenter));
        builder.addSpawn(t, enemyDirector.basicEnemy(context, laneRight));
        builder.addSpawn(t + 1.5f, enemyDirector.basicEnemy(context, sweepRight));
        builder.addSpawn(t + 1.5f, enemyDirector.basicEnemy(context, sweepLeft));
        builder.addSpawn(t + 3f, enemyDirector.basicEnemy(context, crossLeftToRight));
        builder.addSpawn(t + 3f, enemyDirector.basicEnemy(context, crossRightToLeft));

        // PHASE 4: Final onslaught (t~100 to ~130s) ===
        t += 5f;
        builder.addSpawn(t, enemyDirector.heavyEnemy(context, heavyLeft));
        builder.addSpawn(t, enemyDirector.heavyEnemy(context, heavyRight));
        for (int i = 0; i < 4; i++) {
            builder.addSpawn(t + 1f + i * 1f, enemyDirector.basicEnemy(context, i % 2 == 0 ? sweepRight : sweepLeft));
        }
        t += 6f;
        builder.addSpawn(t, enemyDirector.heavyEnemy(context, heavyCenter));
        for (int i = 0; i < 3; i++) {
            builder.addSpawn(t + 0.5f + i * 1.2f, enemyDirector.basicEnemy(context, crossLeftToRight));
            builder.addSpawn(t + 0.5f + i * 1.2f, enemyDirector.basicEnemy(context, crossRightToLeft));
        }
        t += 6f;
        builder.addSpawn(t, enemyDirector.heavyEnemy(context, heavyLeft));
        builder.addSpawn(t, enemyDirector.heavyEnemy(context, heavyCenter));
        builder.addSpawn(t, enemyDirector.heavyEnemy(context, heavyRight));
        builder.addSpawn(t + 1f, enemyDirector.basicEnemy(context, zigzagRight));
        builder.addSpawn(t + 1f, enemyDirector.basicEnemy(context, zigzagLeft));
        builder.addSpawn(t + 2f, enemyDirector.basicEnemy(context, crossLeftToRight));
        builder.addSpawn(t + 2f, enemyDirector.basicEnemy(context, crossRightToLeft));
        builder.addSpawn(t + 3f, enemyDirector.basicEnemy(context, sweepRight));
        builder.addSpawn(t + 3f, enemyDirector.basicEnemy(context, sweepLeft));
        builder.addSpawn(t + 4f, enemyDirector.basicEnemy(context, laneLeft));
        builder.addSpawn(t + 4f, enemyDirector.basicEnemy(context, laneCenter));
        builder.addSpawn(t + 4f, enemyDirector.basicEnemy(context, laneRight));

        return builder.build();
    }

    public Level level4(LevelBuilder builder, GameContext context) {
        EnemyDirector enemyDirector = new EnemyDirector();
        float playWidth = context.getPlayWidth();
        float playHeight = context.getPlayHeight();

        // Paths

        // Wall paths (straight down)
        ArrayList<ArrayList<Vector2>> wallPaths = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ArrayList<Vector2> path = new ArrayList<>();
            path.add(new Vector2(playWidth * (i + 1) / 6f, 0));
            path.add(new Vector2(playWidth * (i + 1) / 6f, -playHeight));
            wallPaths.add(path);
        }

        // Fast crossing X-paths
        ArrayList<Vector2> crossX1 = new ArrayList<>();
        crossX1.add(new Vector2(0, 0));
        crossX1.add(new Vector2(playWidth, -playHeight));

        ArrayList<Vector2> crossX2 = new ArrayList<>();
        crossX2.add(new Vector2(playWidth, 0));
        crossX2.add(new Vector2(0, -playHeight));

        // Horizontal sweeps
        ArrayList<Vector2> horizSweepRight = new ArrayList<>();
        horizSweepRight.add(new Vector2(0, -playHeight / 5));
        horizSweepRight.add(new Vector2(playWidth, -playHeight / 4));
        horizSweepRight.add(new Vector2(0, -playHeight));

        ArrayList<Vector2> horizSweepLeft = new ArrayList<>();
        horizSweepLeft.add(new Vector2(playWidth, -playHeight / 5));
        horizSweepLeft.add(new Vector2(0, -playHeight / 4));
        horizSweepLeft.add(new Vector2(playWidth, -playHeight));

        // Pincer movement
        ArrayList<Vector2> pincerLeft = new ArrayList<>();
        pincerLeft.add(new Vector2(0, -playHeight / 2));
        pincerLeft.add(new Vector2(playWidth / 3, -playHeight / 2));
        pincerLeft.add(new Vector2(playWidth / 4, -playHeight));

        ArrayList<Vector2> pincerRight = new ArrayList<>();
        pincerRight.add(new Vector2(playWidth, -playHeight / 2));
        pincerRight.add(new Vector2(2 * playWidth / 3, -playHeight / 2));
        pincerRight.add(new Vector2(3 * playWidth / 4, -playHeight));

        // Heavy lingering center
        ArrayList<Vector2> heavyCenterLinger = new ArrayList<>();
        heavyCenterLinger.add(new Vector2(playWidth / 2, 0));
        heavyCenterLinger.add(new Vector2(playWidth / 2, -playHeight / 4));
        heavyCenterLinger.add(new Vector2(playWidth / 2, -playHeight / 4)); // linger
        heavyCenterLinger.add(new Vector2(playWidth / 2, -playHeight));

        // Heavy side crawls
        ArrayList<Vector2> heavyCrawlLeft = new ArrayList<>();
        heavyCrawlLeft.add(new Vector2(playWidth / 6, 0));
        heavyCrawlLeft.add(new Vector2(playWidth / 6, -playHeight));

        ArrayList<Vector2> heavyCrawlRight = new ArrayList<>();
        heavyCrawlRight.add(new Vector2(5 * playWidth / 6, 0));
        heavyCrawlRight.add(new Vector2(5 * playWidth / 6, -playHeight));

        float t = 1f;

        // === PHASE 1: Relentless Basics (t=1 to ~20s) ===

        // Wave 1-3: X-crosses rapid fire
        for (int i = 0; i < 4; i++) {
            builder.addSpawn(t, enemyDirector.basicEnemy(context, crossX1));
            builder.addSpawn(t, enemyDirector.basicEnemy(context, crossX2));
            t += 1.5f;
        }

        // Wave 4-6: Horizontal sweeps mixing with X-crosses
        t += 1f;
        for (int i = 0; i < 3; i++) {
            builder.addSpawn(t, enemyDirector.basicEnemy(context, horizSweepRight));
            builder.addSpawn(t + 1f, enemyDirector.basicEnemy(context, horizSweepLeft));
            t += 2f;
        }

        // Wave 7-8: Walls of basic enemies
        t += 2f;
        for (int i = 0; i < 5; i++) builder.addSpawn(t, enemyDirector.basicEnemy(context, wallPaths.get(i)));
        t += 3f;
        for (int i = 0; i < 5; i++) builder.addSpawn(t, enemyDirector.basicEnemy(context, wallPaths.get(i)));

        // === PHASE 2: Heavy Rain (t=20s to ~50s) ===

        t += 4f;
        // Introduce heavy center that lingers, surrounded by pincers
        builder.addSpawn(t, enemyDirector.heavyEnemy(context, heavyCenterLinger));
        for (int i = 0; i < 4; i++) {
            builder.addSpawn(t + 1f + i * 1.5f, enemyDirector.basicEnemy(context, pincerLeft));
            builder.addSpawn(t + 1f + i * 1.5f, enemyDirector.basicEnemy(context, pincerRight));
        }

        t += 8f;
        // Dual heavies with wall drops
        builder.addSpawn(t, enemyDirector.heavyEnemy(context, heavyCrawlLeft));
        builder.addSpawn(t, enemyDirector.heavyEnemy(context, heavyCrawlRight));
        t += 2f;
        for (int i = 1; i < 4; i++) builder.addSpawn(t, enemyDirector.basicEnemy(context, wallPaths.get(i))); // middle 3
        t += 3f;
        builder.addSpawn(t, enemyDirector.basicEnemy(context, wallPaths.get(0)));
        builder.addSpawn(t, enemyDirector.basicEnemy(context, wallPaths.get(4)));

        t += 4f;
        // Tri-Heavy threat
        builder.addSpawn(t, enemyDirector.heavyEnemy(context, heavyCrawlLeft));
        builder.addSpawn(t, enemyDirector.heavyEnemy(context, heavyCenterLinger));
        builder.addSpawn(t, enemyDirector.heavyEnemy(context, heavyCrawlRight));

        t += 2f;
        // Non-stop X-crosses during Tri-Heavy
        for (int i = 0; i < 6; i++) {
            builder.addSpawn(t + i * 1f, enemyDirector.basicEnemy(context, crossX1));
            builder.addSpawn(t + i * 1f, enemyDirector.basicEnemy(context, crossX2));
        }

        // === PHASE 3: Bullet Hell Chaos (t=50s to ~80s) ===

        t += 8f;
        // Alternating sweeps and pincers
        for (int i = 0; i < 6; i++) {
            builder.addSpawn(t, enemyDirector.basicEnemy(context, i % 2 == 0 ? horizSweepRight : horizSweepLeft));
            builder.addSpawn(t, enemyDirector.basicEnemy(context, i % 2 == 0 ? pincerLeft : pincerRight));
            if (i % 3 == 0) builder.addSpawn(t, enemyDirector.heavyEnemy(context, heavyCenterLinger));
            t += 1.5f;
        }

        t += 3f;
        // Five continuous walls
        for (int w = 0; w < 5; w++) {
            for (int i = 0; i < 5; i++) {
                if (i != w) builder.addSpawn(t, enemyDirector.basicEnemy(context, wallPaths.get(i))); // leaves a gap
            }
            t += 2f;
        }

        // === PHASE 4: The True Test (t=80s to ~100s) ===

        t += 3f;
        builder.addSpawn(t, enemyDirector.heavyEnemy(context, heavyCrawlLeft));
        builder.addSpawn(t, enemyDirector.heavyEnemy(context, heavyCrawlRight));
        builder.addSpawn(t + 1f, enemyDirector.heavyEnemy(context, heavyCenterLinger));
        builder.addSpawn(t + 2f, enemyDirector.heavyEnemy(context, heavyCrawlLeft));
        builder.addSpawn(t + 2f, enemyDirector.heavyEnemy(context, heavyCrawlRight));

        t += 3f;
        // Absolute stream of basic enemies
        for(int i = 0; i < 15; i++) {
            builder.addSpawn(t + i * 0.5f, enemyDirector.basicEnemy(context, crossX1));
            builder.addSpawn(t + i * 0.5f, enemyDirector.basicEnemy(context, horizSweepLeft));
            builder.addSpawn(t + i * 0.5f, enemyDirector.basicEnemy(context, pincerRight));
        }

        t += 8f;
        for (int i = 0; i < 5; i++) builder.addSpawn(t, enemyDirector.heavyEnemy(context, wallPaths.get(i))); // 5 heavies wall

        return builder.build();
    }
}
