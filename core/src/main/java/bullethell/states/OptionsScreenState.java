package bullethell.states;

import bullethell.GameContext;
import bullethell.states.statetextwriter.IStateTextWriter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import static com.badlogic.gdx.Gdx.input;

public class OptionsScreenState extends AbstractGameScreenState {

    OptionsScreenState(GameContext context, IStateTextWriter writer) {
        super(context, writer);
    }

    @Override
    public void update(float delta) {
        if (input.isKeyJustPressed(Input.Keys.NUM_1)) {
            if (context.getControlMode().equals(GameContext.ControlMode.KEYBOARD)) {
                context.setControlMode(GameContext.ControlMode.MOUSE);
                Gdx.input.setCursorCatched(true);
            } else if (context.getControlMode().equals(GameContext.ControlMode.MOUSE)) {
                context.setControlMode(GameContext.ControlMode.KEYBOARD);
                Gdx.input.setCursorCatched(false);
            }
        }

        if (input.isKeyJustPressed(Input.Keys.NUM_2)) {
            context.toggleSoundFx();
        }

        if (input.isKeyJustPressed(Input.Keys.NUM_3)) {
            context.toggleMusic();
        }

        if (input.isKeyJustPressed(Input.Keys.NUM_0)) {
            context.changeState(new HomeScreenState(context, writer));
        }
    }

    @Override
    public void render() {
        final float playHeight = context.getPlayHeight();
        float lineOffset = playHeight - 50;
        lineOffset -= this.writer.writeCenteredTextAtHeight("OPTIONS", lineOffset, 3f);
        lineOffset -= this.writer.writeCenteredTextAtHeight("Press leading digit to toggle given option", lineOffset - 20, 1.5f);

        if (context.getControlMode().equals(GameContext.ControlMode.KEYBOARD)) {
            lineOffset -= this.writer.writeCenteredTextAtHeight("1. Toggle mouse controls", lineOffset - 50, 2f);
            final String[] text = new String[]{"Note: depending on your OS settings,", "you may not be able to hold space to fire", "and move at the same time"};
            lineOffset = this.writer.writeTightMultilineAtHeight(text, lineOffset - 60, 1f);
            lineOffset += 50;
        } else if (context.getControlMode().equals(GameContext.ControlMode.MOUSE)) {
            lineOffset -= this.writer.writeCenteredTextAtHeight("1. Toggle keyboard controls", lineOffset - 50, 2f);
        }

        if (context.usesFx()) {
            lineOffset -= this.writer.writeCenteredTextAtHeight("2. Toggle sound effects off", lineOffset - 60, 2f);
        } else {
            lineOffset -= this.writer.writeCenteredTextAtHeight("2. Toggle sound effects on", lineOffset - 60, 2f);
        }

        if (context.usesMusic()) {
            this.writer.writeCenteredTextAtHeight("3. Toggle music off", lineOffset - 70, 2f);
        } else {
            this.writer.writeCenteredTextAtHeight("3. Toggle music on", lineOffset - 70, 2f);
        }

        this.writer.writeCenteredTextAtHeight("Press 0 to go back", 50, 1f);
    }
}
