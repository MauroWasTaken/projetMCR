package bullethell.states;

import bullethell.GameContext;
import bullethell.gameobjects.GameObject;
import bullethell.gameobjects.builders.LevelBuilder;
import bullethell.gameobjects.builders.LevelDirector;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class PlayingState implements GameState {

    private boolean levelCompleted = false;
    private boolean gameOver = false;
    private float levelTransitionTimer = 0f;

    @Override
    public void update(GameContext context, float delta) {
        // execute level logic
        context.getLevel().update(delta);

        // update game objects
        for (GameObject gameObject : context.getGameObjects()) {
            gameObject.update(delta);
        }

        // delete pending objects
        if (!context.getPendingRemove().isEmpty()) {
            for (GameObject o : context.getPendingRemove()) {
                context.getGameObjects().remove(o);
            }
            context.getPendingRemove().clear();
        }

        // add pending objects
        if (!context.getPendingAdd().isEmpty()) {
            context.getGameObjects().addAll(context.getPendingAdd());
            context.getPendingAdd().clear();
        }
        // check if player is still alive
        if (context.getPlayer() == null){
            gameOver = true;
        }

        // check level completion
        if (context.getLevel().isFinished() && context.getEnemies().length == 0 && !gameOver) {
            levelCompleted = true;
        }

        if (levelCompleted || gameOver) {
            levelTransitionTimer += delta;
            if (levelTransitionTimer >= 2f) {
                // clear all game objects
                context.getGameObjects().clear();
                context.getPendingAdd().clear();
                context.getPendingRemove().clear();
                if (levelCompleted){
                    // Change to upgrade menu and prep next level
                    context.changeState(context.getUpgradeMenuState());
                    // For now, reload level 1 since there is no level 2
                    context.setLevel(new LevelDirector().level1(new LevelBuilder(context), context));
                    return;
                }
                context.changeState(new HomeScreenState());
            }
        }
    }

    @Override
    public void render(GameContext context, SpriteBatch batch, BitmapFont font) {
        if (levelCompleted) { //draws victory screen
            font.getData().setScale(1.5f);
            font.draw(batch, "Level Completed!", context.getPlayWidth() / 2 - 70, context.getPlayHeight() / 2);
            font.getData().setScale(1f); // reset scale
        }
        // draw current money
        font.draw(batch, "money: " + bullethell.currencysystem.CurrencyBank.getInstance().getValue(), context.getPlayWidth() - 100, 20);

        if (gameOver) { //draws game over screen
            font.getData().setScale(1.5f);
            font.draw(batch, "GameOver!", context.getPlayWidth() / 2 - 50, context.getPlayHeight() / 2);
            font.getData().setScale(1f); // reset scale
        }
    }
}
