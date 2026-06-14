package bullethell.states;

import bullethell.GameContext;
import bullethell.states.statetextwriter.IStateTextWriter;
import bullethell.states.statetextwriter.StateTextWriter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Base for screen states
 */
public abstract class AbstractGameScreenState implements GameScreenState {
    protected final GameContext context;
    protected final IStateTextWriter writer;

    AbstractGameScreenState(GameContext context, IStateTextWriter writer) {
        this.context = context;
        this.writer = writer;
    }
}
