package bullethell.states;

import bullethell.GameContext;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public interface GameState {
    void update(GameContext context, float delta);
    void render(GameContext context, SpriteBatch batch, BitmapFont font);
}
