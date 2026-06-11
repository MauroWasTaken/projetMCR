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

public class UpgradeMenuState implements GameState {

    private String shopMessage = "";
    private float shopMessageTimer = 0f;
    private final ArrayList<Upgrade> availableUpgrades; //available to purchase
    private final ArrayList<Upgrade> purchasedUpgrades = new ArrayList<>();

    public UpgradeMenuState(GameContext context) {
        availableUpgrades = new ArrayList<>(Arrays.asList(//adds default items to shop
            new Upgrade("Main Weapon", "Main weapon shoots", 0, new WeaponDirector().playerMainWeapon(new WeaponBuilder(context))),
            new Upgrade("Side Weapons", "Shoots 2 extra projectiles", 300, new WeaponDirector().playerSideWeapons(new WeaponBuilder(context))),
            new Upgrade("Quick Recharge Shield", "3 HP, recharges in 5s", 100, new ShieldDirector(context).quickRechargeShield()),
            new Upgrade("Strong Shield", "5 HP, 2s recharge", 200, new ShieldDirector(context).strongShield())
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
    public void update(GameContext context, float delta) {
        if (input.isKeyJustPressed(Input.Keys.ENTER)) { // if done shopping, import player
            buildPlayer(context);                           // build new player
            context.setNextLevel();
            context.changeState(new PlayingState());        // change state
            return;
        }

        if (shopMessageTimer > 0) {     //update image timer so that it doesnt show up forever
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
    public void render(GameContext context, SpriteBatch batch, BitmapFont font) {
        float playHeight = context.getPlayHeight();
        font.getData().setScale(1.5f);
        font.draw(batch, "UPGRADE MENU", 50, playHeight - 50);
        font.getData().setScale(1f);
        font.draw(batch, "Use numbers 1-9 to pick your weapon\nPress ENTER to start level", 50, playHeight - 80);

        float startY = playHeight - 120;

        for (int i = 0; i < availableUpgrades.size(); i++) { //list the updates
            Upgrade u = availableUpgrades.get(i);
            String type = u.isWeaponUpgrade() ? "[Weapon]" : "[Shield]";
            font.draw(batch, (i + 1) + ". " + u.getName() + " " + type + " - " + u.getPrice() + "$", 50, startY - (i * 40));
            font.draw(batch, "   " + u.getDescription(), 50, startY - (i * 40) - 20);
        }

        if (!shopMessage.isEmpty()) { // show shop message if there is one
            font.setColor(Color.YELLOW);
            font.draw(batch, shopMessage, 50, 100);
            font.setColor(Color.WHITE);
        }

        // draw current money
        font.draw(batch, "money: " + bullethell.currencysystem.CurrencyBank.getInstance().getValue(), context.getPlayWidth() - 100, 20);
    }
}
