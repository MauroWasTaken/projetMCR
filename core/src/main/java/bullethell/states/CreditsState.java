package bullethell.states;

import bullethell.GameContext;
import bullethell.states.statetextwriter.StateTextWriter;
import com.badlogic.gdx.Input;

import static com.badlogic.gdx.Gdx.input;

public class CreditsState extends AbstractGameState {

    CreditsState(GameContext context, StateTextWriter writer) {
        super(context, writer);
    }

    @Override
    public void update(float delta) {
        if (input.isKeyJustPressed(Input.Keys.NUM_0)) {
            context.changeState(new HomeScreenState(context, writer));
        }
    }

    @Override
    public void render() {
        final float playHeight = context.getPlayHeight();
        this.writer.writeCenteredTextAtHeight("CREDITS", playHeight - 50, 3f);

        this.writer.writeCenteredTextAtHeight("Bullet Hell dev team", playHeight - 130, 2f);

        this.writer.writeCenteredTextAtHeight("Borgeaud Gaël", playHeight - 170, 1.5f);
        this.writer.writeCenteredTextAtHeight("Costa Dos Santos Mauro", playHeight - 200, 1.5f);
        this.writer.writeCenteredTextAtHeight("Ferreira Silva Sven", playHeight - 230, 1.5f);

        this.writer.writeCenteredTextAtHeight("Game engine", playHeight - 300, 2f);

        this.writer.writeCenteredTextAtHeight("Created by:", playHeight - 340, 1.5f);
        this.writer.writeCenteredTextAtHeight("Mario Zechner", playHeight - 370, 1.5f);
        this.writer.writeCenteredTextAtHeight("Nathan Sweet", playHeight - 400, 1.5f);

        this.writer.writeCenteredTextAtHeight("Press 0 to go back", 50, 1f);
    }
}
