package bullethell.states;

import bullethell.GameContext;
import bullethell.gameobjects.Upgrade;
import bullethell.gameobjects.builders.PlayerBuilder;
import bullethell.gameobjects.builders.ShieldDirector;
import bullethell.gameobjects.builders.WeaponBuilder;
import bullethell.gameobjects.builders.WeaponDirector;
import bullethell.currencysystem.CurrencyBank;
import bullethell.currencysystem.InsufficientFundsException;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import java.util.ArrayList;
import java.util.Arrays;

import static com.badlogic.gdx.Gdx.input;

public class UpgradeMenuState extends AbstractGameState {

    private String shopMessage = "";
    private float shopMessageTimer = 0f;
    private final ArrayList<Upgrade> availableUpgrades; //available to purchase
    private final ArrayList<Upgrade> purchasedUpgrades = new ArrayList<>();

    public UpgradeMenuState(GameContext context, BitmapFont font, SpriteBatch batch) {
        super(context, font, batch);
        availableUpgrades = new ArrayList<>(Arrays.asList(//adds default items to shop
            new Upgrade("Main Weapon", "Main weapon shoots", 0, new WeaponDirector().playerMainWeapon(new WeaponBuilder(context))),
            new Upgrade("Side Weapons", "Shoots 2 extra projectiles", 500, new WeaponDirector().playerSideWeapons(new WeaponBuilder(context))),
            new Upgrade("Weak Shield", "1 HP, no recharge", 100, new ShieldDirector(context).weakShield()),
            new Upgrade("Quick Recharge Shield", "1 HP, recharges in 3.5s", 600, new ShieldDirector(context).quickRechargeShield()),
            new Upgrade("Strong Shield", "3 HP, no recharge", 1200, new ShieldDirector(context).strongShield())
        ));
    }

    private void buildPlayer(GameContext context) {
        PlayerBuilder playerBuilder = new PlayerBuilder(context); // start builder

        for (Upgrade upgrade : purchasedUpgrades) { //add purchased upgrades
            if (upgrade.isWeaponUpgrade()) {
                playerBuilder.addWeapon(upgrade.getWeapon());
            } else {
                playerBuilder.addShield(upgrade.getShield());
            }
        }

        context.getGameObjects().add(playerBuilder.build()); // build player and add to game objects
    }

    @Override
    public void update(float delta) {
        if (input.isKeyJustPressed(Input.Keys.ENTER)) { // if shopping is done, import player
            buildPlayer(context);                       // build new player
            context.setNextLevel();
            context.changeState(new PlayingState(context, writer)); // change state
            return;
        }

        if (shopMessageTimer > 0) {     //update image timer so that it doesn't show up forever
            shopMessageTimer -= delta;
        } else {
            shopMessage = "";
        }

        for (int i = 0; i < availableUpgrades.size(); i++) {
            if (input.isKeyJustPressed(Input.Keys.NUM_1 + i)) { // adds corresponding upgrade to purchased upgrades list
                Upgrade upgrade = availableUpgrades.get(i);
                try {
                    CurrencyBank.getInstance().purchase(upgrade.getPrice());

                    if (!upgrade.isWeaponUpgrade()) {
                        // if it's a shield, remove old shield if exists
                        Upgrade oldShield = null;
                        for (Upgrade purchasedUpgrade : purchasedUpgrades) { // look for a shield if there is one
                            if (!purchasedUpgrade.isWeaponUpgrade()) {
                                oldShield = purchasedUpgrade;
                                break;
                            }
                        }
                        if (oldShield != null) {                    // take it off and add the old shield back to the shop
                            purchasedUpgrades.remove(oldShield);
                            availableUpgrades.add(oldShield);
                        }
                    }

                    purchasedUpgrades.add(upgrade);
                    availableUpgrades.remove(upgrade);

                    shopMessage = "Purchased " + upgrade.getName() + "!";
                    shopMessageTimer = 2f;
                    break; // break out of the loop since we modified availableUpgrades
                } catch (InsufficientFundsException e) { // shows message if we dont have the money
                    shopMessage = "Not enough money!";
                    shopMessageTimer = 2f;
                }
            }
        }
    }

    @Override
    public void render() {
        final float playHeight = context.getPlayHeight();
        this.writer.writeCenteredTextAtHeight("UPGRADE MENU", playHeight - 50, 1.5f);
        this.writer.writeTightMultilineAtHeight(new String[]{"Use numbers 1-9 to pick your weapon", "Press ENTER to start level"}, playHeight - 80, 1f);

        float startY = playHeight - 120;

        final String TEMPLATE = "%d. %s %s - %d$";
        for (int i = 0; i < availableUpgrades.size(); i++) { //list the updates
            Upgrade u = availableUpgrades.get(i);
            final String type = u.isWeaponUpgrade() ? "[Weapon]" : "[Shield]";
            final String entry = TEMPLATE.formatted(i + 1, u.getName(), type, u.getPrice());
            this.writer.writeLeftBiasedTextAtHeight(entry, startY - (i * 40), 1f);
            this.writer.writeLeftBiasedTextAtHeight("   " + u.getDescription(), startY - (i * 40) - 20, 1f);
        }

        if (!shopMessage.isEmpty()) { // show shop message if there is one
            this.writer.writeCenteredTextAtHeight(shopMessage, 100, 1f, Color.YELLOW);
        }

        // draw current money
        this.writer.writeBottomRightText("Money: " + bullethell.currencysystem.CurrencyBank.getInstance().getValue(), 1f);
    }
}
