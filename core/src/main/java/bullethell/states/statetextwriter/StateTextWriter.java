package bullethell.states.statetextwriter;

import bullethell.GameContext;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class StateTextWriter {
    private final GameContext context;
    private final BitmapFont font;
    private final SpriteBatch batch;

    public StateTextWriter(GameContext context, BitmapFont font, SpriteBatch batch) {
        this.context = context;
        this.font = font;
        this.batch = batch;
    }

    public float writeCenteredTextAtHeight(String text, float y, float fontSize) {
        font.getData().setScale(fontSize);
        final GlyphLayout layout = new GlyphLayout(font, text);
        font.draw(batch, text, (context.getPlayWidth() - layout.width) / 2, y);
        return layout.height;
    }

    public void writeCenteredTextAtHeight(String text, float y, float fontSize, Color color) {
        font.setColor(color);
        writeCenteredTextAtHeight(text, y, fontSize);
        font.setColor(Color.WHITE);
    }

    public void writeTightMultilineAtHeight(String[] text, float y, float fontSize) {
        float height = y;
        for (String line : text) {
            height -= writeCenteredTextAtHeight(line, height, fontSize);
        }
    }

    public void writeLeftBiasedTextAtHeight(String text, float y, float fontSize) {
        font.getData().setScale(fontSize);
        font.draw(batch, text, 50, y);
    }

    public void writeBottomRightText(String text, float fontSize) {
        font.getData().setScale(fontSize);
        final GlyphLayout layout = new GlyphLayout(font, text);
        font.draw(batch, text, context.getPlayWidth() - layout.width - 10, 20);
    }

    public void writeBottomLeftText(String text, float fontSize) {
        font.getData().setScale(fontSize);
        font.draw(batch, text, 10, 20);
    }
}
