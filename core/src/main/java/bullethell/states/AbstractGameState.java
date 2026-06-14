package bullethell.states;

import bullethell.GameContext;
import bullethell.states.statetextwriter.StateTextWriter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class AbstractGameState implements GameState {
    protected final GameContext context;
    protected final StateTextWriter writer;

    AbstractGameState(GameContext context, BitmapFont font, SpriteBatch batch) {
        this.context = context;
        this.writer = new StateTextWriter(context, font, batch);
    }

    AbstractGameState(GameContext context, StateTextWriter writer) {
        this.context = context;
        this.writer = writer;
    }
}
