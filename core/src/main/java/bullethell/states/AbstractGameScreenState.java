package bullethell.states;

import bullethell.GameContext;
import bullethell.states.statetextwriter.IStateTextWriter;

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
