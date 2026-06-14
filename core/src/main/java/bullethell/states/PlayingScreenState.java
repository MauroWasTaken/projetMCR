package bullethell.states;

import bullethell.GameContext;
import bullethell.currencysystem.CurrencyBank;
import bullethell.gameobjects.CampaignSingleton;
import bullethell.gameobjects.GameObject;
import bullethell.gameobjects.ships.Player;
import bullethell.states.statetextwriter.IStateTextWriter;

/**
 * Main game
 */
public class PlayingScreenState extends AbstractGameScreenState {

    PlayingScreenState(GameContext context, IStateTextWriter writer) {
        super(context, writer);
    }

    private boolean levelCompleted = false;
    private boolean gameOver = false;
    private float levelTransitionTimer = 0f;
    private final CampaignSingleton instance = CampaignSingleton.getInstance();
    int nbRemainingSpecials = 0;

    @Override
    public void update(float delta) {
        // execute level logic
        context.getLevel().update(delta);

        // update game objects
        for (GameObject gameObject : context.getGameObjects()) {
            gameObject.update(delta);
        }

        // delete pending objects
        if (!context.getPendingRemove().isEmpty()) {
            for (GameObject o : context.getPendingRemove()) {
                context.getGameObjects().remove(o);
            }
            context.getPendingRemove().clear();
        }

        // add pending objects
        if (!context.getPendingAdd().isEmpty()) {
            context.getGameObjects().addAll(context.getPendingAdd());
            context.getPendingAdd().clear();
        }
        // check if player is still alive
        if (context.getPlayer() == null) {
            gameOver = true;
        } else {
            // If player is not dead, update remaining specials
            nbRemainingSpecials = context.getPlayer().getRemainingSpecialCharges();
        }

        // check level completion
        if (context.getLevel().isFinished() && context.getEnemies().length == 0 && !gameOver) {
            levelCompleted = true;
        }

        if (levelCompleted || gameOver) {
            levelTransitionTimer += delta;
            if (levelTransitionTimer >= 2f) {
                // clear all game objects
                context.getGameObjects().clear();
                context.getPendingAdd().clear();
                context.getPendingRemove().clear();
                if (levelCompleted) {
                    // Change to upgrade menu and prep next level
                    CurrencyBank.getInstance().addFunds(200);
                    instance.levelSucceeded();
                    if (instance.isCampaignCleared()) {
                        // Go to Victory Screen
                        context.changeState(new VictoryState(context, writer));
                    } else {
                        context.changeState(new UpgradeMenuScreenState(context, writer));
                    }
                }
                if (gameOver) {
                    // Go to Game Over Screen
                    context.changeState(new GameOverState(context, writer));

                }
            }
        }
    }

    @Override
    public void render() {
        if (levelCompleted && !instance.isCampaignCleared()) { //draws level completed transition
            this.writer.writeCenteredTextAtHeight("Level Completed!", context.getPlayHeight() / 2, 1.5f);
        }

        // draw shield hp, if any
        final Player player = context.getPlayer();
        if (player != null && player.getShield() != null) {
            this.writer.writeBottomLeftText("Shield HP: " + player.getShield().getHp(), 1f);
        }

        // draw current money
        this.writer.writeBottomRightText("Money: " + bullethell.currencysystem.CurrencyBank.getInstance().getValue(), 1f);

        // draw HUD
        this.writer.writeLeftBiasedTextAtHeight("Level: " + instance.getCurrentLevel(), context.getPlayHeight() - 20, 1f);
        this.writer.writeCenteredTextAtHeight("Score: " + instance.getScore(), context.getPlayHeight() - 20, 1f);

        if (player != null && player.hasSpecial()) {
            this.writer.writeCenteredTextAtHeight("Super charges: " + nbRemainingSpecials, 20, 1f);
        }
    }
}
