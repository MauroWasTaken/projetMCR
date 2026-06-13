package bullethell.states;

import bullethell.GameContext;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public interface GameState {
    void update(GameContext context, float delta);
    void render(GameContext context, SpriteBatch batch, BitmapFont font);

    // TODO: implement an intermediate (abstract) class to hold instances of font, context, batch
    default float writeCenteredTextAtHeight(String text, float y, BitmapFont font, GameContext context, SpriteBatch batch) {
        final GlyphLayout layout = new GlyphLayout(font, text);
        font.draw(batch, text, (context.getPlayWidth() - layout.width) / 2, y);
        return layout.height;
    }

    default void writeTightMultilineAtHeight(String[] text, float y, BitmapFont font, GameContext context, SpriteBatch batch) {
        float height = y;
        for (String line : text) {
            height -= writeCenteredTextAtHeight(line, height, font, context, batch);
        }
    }
}
