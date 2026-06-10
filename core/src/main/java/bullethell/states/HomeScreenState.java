package bullethell.states;

import bullethell.GameContext;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.List;

import static com.badlogic.gdx.Gdx.input;

public class HomeScreenState implements GameState {


    @Override
    public void update(GameContext context, float delta) {
        if (input.isKeyJustPressed(Input.Keys.NUM_1)) {
            // Start game
            context.changeState(new UpgradeMenuState(context));
        } else if (input.isKeyJustPressed(Input.Keys.NUM_2)) {
            // TODO: create options, maybe FX sounds/music toggle...or get rid of this
        } else if (input.isKeyJustPressed(Input.Keys.NUM_3)) {
            // Credits
            context.changeState(new CreditsState());
        }
    }

    @Override
    public void render(GameContext context, SpriteBatch batch, BitmapFont font) {
        final float playHeight = context.getPlayHeight();
        font.getData().setScale(4f);
        writeCenteredTextAtHeight("Bullet Hell", playHeight - 50, font, context, batch);
        font.getData().setScale(3f);

        final List<String> textOptions = List.of(new String[]{"1. PLAY", "2. OPTIONS", "3. CREDITS"});
        final float startY = playHeight - 150;
        for (int i = 0; i < textOptions.size(); ++i) {
            writeCenteredTextAtHeight(textOptions.get(i), startY - (i * 80) - 40, font, context, batch);
        }
    }
}
