package bullethell.states;

import bullethell.GameContext;
import bullethell.currencysystem.CurrencyBank;
import bullethell.gameobjects.GameObject;
import bullethell.gameobjects.builders.LevelBuilder;
import bullethell.gameobjects.builders.LevelDirector;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class PlayingState implements GameState {

    private boolean levelCompleted = false;
    private float levelCompletionTimer = 0f;

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

        // check level completion
        if (context.getLevel().isFinished() && context.getEnemies().length == 0) {
            if (!levelCompleted) { // so that money is only added once
                CurrencyBank.getInstance().addFunds(100);
            }
            levelCompleted = true;
        }

        if (levelCompleted) {
            levelCompletionTimer += delta;
            if (levelCompletionTimer >= 2f) {
                // clear all game objects
                context.getGameObjects().clear();
                context.getPendingAdd().clear();
                context.getPendingRemove().clear();

                // Change to upgrade menu and prep next level
                context.changeState(context.getUpgradeMenuState());

                // For now, reload level 1 since there is no level 2
                context.setLevel(new LevelDirector().level1(new LevelBuilder(context), context));
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
        //todo add defeat screen
    }
}
