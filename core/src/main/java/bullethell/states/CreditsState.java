package bullethell.states;

import bullethell.GameContext;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static com.badlogic.gdx.Gdx.input;

public class CreditsState implements GameState {
    @Override
    public void update(GameContext context, float delta) {
        if (input.isKeyJustPressed(Input.Keys.NUM_0)) {
            context.changeState(new HomeScreenState());
        }
    }

    @Override
    public void render(GameContext context, SpriteBatch batch, BitmapFont font) {
        final float playHeight = context.getPlayHeight();
        font.getData().setScale(3f);
        writeCenteredTextAtHeight("CREDITS", playHeight - 50, font, context, batch);
        font.getData().setScale(2f);

        writeCenteredTextAtHeight("Bullet Hell dev team", playHeight - 130, font, context, batch);
        font.getData().setScale(1.5f);
        writeCenteredTextAtHeight("Borgeaud Gaël", playHeight - 170, font, context, batch);
        writeCenteredTextAtHeight("Costa Dos Santos Mauro", playHeight - 200, font, context, batch);
        writeCenteredTextAtHeight("Ferreira Silva Sven", playHeight - 230, font, context, batch);

        font.getData().setScale(2f);
        writeCenteredTextAtHeight("Game engine", playHeight - 300, font, context, batch);
        font.getData().setScale(1.5f);
        writeCenteredTextAtHeight("Created by:", playHeight - 340, font, context, batch);
        writeCenteredTextAtHeight("Mario Zechner", playHeight - 370, font, context, batch);
        writeCenteredTextAtHeight("Nathan Sweet", playHeight - 400, font, context, batch);

        font.getData().setScale(1f);
        writeCenteredTextAtHeight("Press 0 to go back", playHeight - 450, font, context, batch);
    }
}
