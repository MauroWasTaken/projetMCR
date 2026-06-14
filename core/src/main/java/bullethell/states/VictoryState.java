package bullethell.states;

import bullethell.GameContext;
import bullethell.gameobjects.CampaignSingleton;
import bullethell.states.statetextwriter.IStateTextWriter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

/**
 * Victory screen
 */
public class VictoryState extends AbstractGameScreenState {

    public VictoryState(GameContext context, IStateTextWriter writer) {
        super(context, writer);
    }

    @Override
    public void update(float delta) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            bullethell.currencysystem.CurrencyBank.getInstance().reset();
            CampaignSingleton.getInstance().reset();
            context.changeState(new HomeScreenState(context, writer));
        }
    }

    @Override
    public void render() {
        final float playHeight = context.getPlayHeight();
        this.writer.writeCenteredTextAtHeight("VICTORY!", playHeight / 2 + 50, 2f);
        this.writer.writeCenteredTextAtHeight("Final Score: " + CampaignSingleton.getInstance().getScore(), playHeight / 2, 1.5f);
        this.writer.writeCenteredTextAtHeight("Press SPACE or ENTER to return to Main Menu", playHeight / 2 - 50, 1f);
    }
}
