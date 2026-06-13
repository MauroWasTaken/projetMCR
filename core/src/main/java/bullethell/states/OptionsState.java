package bullethell.states;

import bullethell.GameContext;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static com.badlogic.gdx.Gdx.input;

public class OptionsState implements GameState {
    @Override
    public void update(GameContext context, float delta) {
        if (input.isKeyJustPressed(Input.Keys.NUM_1)) {
            if (context.getControlMode().equals(GameContext.ControlMode.KEYBOARD)) {
                context.setControlMode(GameContext.ControlMode.MOUSE);
                Gdx.input.setCursorCatched(true);
            } else if (context.getControlMode().equals(GameContext.ControlMode.MOUSE)) {
                context.setControlMode(GameContext.ControlMode.KEYBOARD);
                Gdx.input.setCursorCatched(false);
            }
        }

        if (input.isKeyJustPressed(Input.Keys.NUM_0)) {
            context.changeState(new HomeScreenState());
        }
    }

    @Override
    public void render(GameContext context, SpriteBatch batch, BitmapFont font) {
        final float playHeight = context.getPlayHeight();
        float lineOffset = playHeight - 50;
        font.getData().setScale(3f);
        lineOffset -= writeCenteredTextAtHeight("OPTIONS", lineOffset, font, context, batch);
        font.getData().setScale(1.5f);
        lineOffset -= writeCenteredTextAtHeight("Press leading digit to toggle given option", lineOffset - 20, font, context, batch);
        font.getData().setScale(2f);

        if (context.getControlMode().equals(GameContext.ControlMode.KEYBOARD)) {
            lineOffset -= writeCenteredTextAtHeight("1. Toggle mouse controls", lineOffset - 50, font, context, batch);
            font.getData().setScale(1f);
            final String[] text = new String[]{"Note: depending on your OS settings,", "you may not be able to hold space to fire", "and move at the same time"};
            writeTightMultilineAtHeight(text, lineOffset - 60, font, context, batch);
        } else if (context.getControlMode().equals(GameContext.ControlMode.MOUSE)) {
            writeCenteredTextAtHeight("1. Toggle keyboard controls", lineOffset - 50, font, context, batch);
        }

        font.getData().setScale(1f);
        writeCenteredTextAtHeight("Press 0 to go back", 50, font, context, batch);
    }
}
