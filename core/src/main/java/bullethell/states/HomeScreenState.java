package bullethell.states;

import bullethell.GameContext;
import bullethell.states.statetextwriter.IStateTextWriter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.List;

import static com.badlogic.gdx.Gdx.input;

public class HomeScreenState extends AbstractGameState {
    public HomeScreenState(GameContext context, BitmapFont font, SpriteBatch batch) {
        super(context, font, batch);
    }

    HomeScreenState(GameContext context, IStateTextWriter writer) {
        super(context, writer);
    }

    @Override
    public void update(float delta) {
        if (input.isKeyJustPressed(Input.Keys.NUM_1)) {
            // Start game
            context.resetUpgradeMenuState();
            context.changeState(context.getUpgradeMenuState());
        } else if (input.isKeyJustPressed(Input.Keys.NUM_2)) {
            context.changeState(new OptionsState(context, writer));
        } else if (input.isKeyJustPressed(Input.Keys.NUM_3)) {
            // Credits
            context.changeState(new CreditsState(context, writer));
        } else if (input.isKeyJustPressed(Input.Keys.NUM_0)) {
            // Close the game
            Gdx.app.exit();
        }
    }

    @Override
    public void render() {
        final float playHeight = context.getPlayHeight();
        this.writer.writeCenteredTextAtHeight("Bullet Hell", playHeight - 50, 4f);

        final List<String> textOptions = List.of(new String[]{"1. PLAY", "2. OPTIONS", "3. CREDITS"});
        final float startY = playHeight - 150;
        for (int i = 0; i < textOptions.size(); ++i) {
            this.writer.writeCenteredTextAtHeight(textOptions.get(i), startY - (i * 80) - 40, 3f);
        }
        this.writer.writeCenteredTextAtHeight("Press 0 to close the game", 50, 1f);
    }
}
