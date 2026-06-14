package bullethell;

import bullethell.gameobjects.CampaignSingleton;
import bullethell.gameobjects.Upgrade;
import bullethell.gameobjects.builders.WeaponBuilder;
import bullethell.gameobjects.builders.WeaponDirector;
import bullethell.gameobjects.factories.ShieldFactory;
import bullethell.gameobjects.ships.Enemy;
import bullethell.gameobjects.ships.Player;
import bullethell.gameobjects.supermove.SuperLaser;
import bullethell.gameobjects.supermove.SuperShield;
import bullethell.states.GameScreenState;
import bullethell.states.HomeScreenState;
import bullethell.states.statetextwriter.StateTextWriter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import bullethell.gameobjects.GameObject;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */
public class GameContext extends Game {

    private final float playWidth = 450f;
    private final float playHeight = 600f;

    // Sprites
    SpriteBatch batch;
    private OrthographicCamera camera;
    private FitViewport viewport;
    private Texture background;
    private Texture playerSprite;
    private Texture enemySprite;
    private Texture heavyEnemySprite;
    private Texture shieldSprite;
    private Texture superChargedShield;
    private Texture projectileSprite;
    private Texture enemyProjectileSprite;
    private Texture score100Sprite;
    private Texture score500Sprite;
    private Texture explosionSprite;
    private Texture basicGun;

    // SoundFX
    private Sound mainWeaponFx;
    private Sound sideWeaponFx;
    private Sound enemyShootFx;
    private Sound superLaserFx;
    private Music backgroundMusic;
    private boolean useFx;
    private boolean useMusic;
    private final float MUSIC_VOLUME = 0.2f;

    private BitmapFont font;
    private ShapeRenderer shapeRenderer;
    private final ArrayList<GameObject> gameObjects = new ArrayList<>();
    private final ArrayList<GameObject> pendingAdd = new ArrayList<>();
    private final ArrayList<GameObject> pendingRemove = new ArrayList<>();

    // Campaign
    private final CampaignSingleton campaignInstance = CampaignSingleton.getInstance();

    private GameScreenState currentState;

    private ControlMode controlMode = ControlMode.KEYBOARD;

    private Level level;

    // Upgrades management
    private ArrayList<Upgrade> availableUpgrades; //available to purchase
    private final ArrayList<Upgrade> purchasedUpgrades = new ArrayList<>();

    // Builders
    WeaponBuilder weaponBuilder;
    WeaponDirector weaponDirector;
    ShieldFactory shieldFactory;

    @Override
    public void create() {
        batch = new SpriteBatch();
        // camera init
        camera = new OrthographicCamera();
        viewport = new FitViewport(playWidth, playHeight, camera);
        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
        batch.setProjectionMatrix(camera.combined);
        //setting up the camera's background
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(new Color(0.00f, 0.00f, 0.2f, 1f));
        pixmap.fill();
        background = new Texture(pixmap);
        pixmap.dispose();
        // player init
        // load shared sprites texture and font renderers
        playerSprite = new Texture("player.png");
        enemySprite = new Texture("enemy-ship.png");
        heavyEnemySprite = new Texture("enemy-heavy.png");
        projectileSprite = new Texture("projectile.png");
        enemyProjectileSprite = new Texture("enemy-projectile.png");
        score100Sprite = new Texture("Score100.png");
        score500Sprite = new Texture("Score500.png");
        shieldSprite = new Texture("shields.png");
        superChargedShield = new Texture("super-shields.png");
        explosionSprite = new Texture("explosion_48.png");
        basicGun = new Texture("big-gun.png");
        // Sounds
        mainWeaponFx = Gdx.audio.newSound(Gdx.files.internal("single_shot.wav"));
        sideWeaponFx = Gdx.audio.newSound(Gdx.files.internal("dual_shot.wav"));
        enemyShootFx = Gdx.audio.newSound(Gdx.files.internal("enemy_shoot.wav"));
        superLaserFx = Gdx.audio.newSound(Gdx.files.internal("special_weapon.mp3"));
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("background_music.mp3"));
        useFx = true;
        useMusic = true;
        font = new BitmapFont();
        shapeRenderer = new ShapeRenderer();

        currentState = new HomeScreenState(this, new StateTextWriter(this, font, batch));

        // Start music
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(this.MUSIC_VOLUME);
        backgroundMusic.play();

        // Builders
        weaponBuilder = new WeaponBuilder(this);
        weaponDirector = new WeaponDirector();
        shieldFactory = new ShieldFactory(this);

        // Upgrades management
        resetUpgrades();
    }

    @Override
    public void render() {
        ScreenUtils.clear(0f, 0f, 0f, 1f);
        float delta = Gdx.graphics.getDeltaTime();

        currentState.update(delta);

        //draw objects todo maybe move to playingState
        batch.begin();
        batch.draw(background, 0f, 0f, playWidth, playHeight);
        for (GameObject gameObject : gameObjects) {
            gameObject.render(batch);
        }

        currentState.render();

        batch.end();
        shapeRenderer.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        background.dispose();
        //disposal of sprites
        // TODO: dispose items
        if (playerSprite != null) playerSprite.dispose();
        if (font != null) font.dispose();
        if (shapeRenderer != null) shapeRenderer.dispose();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        batch.setProjectionMatrix(camera.combined);
    }

    public float getPlayWidth() {
        return playWidth;
    }

    public float getPlayHeight() {
        return playHeight;
    }

    public Vector2 unprojectMouse(Vector2 mouse) {
        // Method that takes a vector of mouse coordinates and adapts the value to the viewport.
        if (mouse == null) {
            throw new RuntimeException("Received null value for mouse control.");
        }
        return viewport.unproject(mouse);
    }

    public Texture getPlayerSprite() {
        return playerSprite;
    }

    public Texture getEnemySprite() {
        return enemySprite;
    }

    public Texture getHeavyEnemySprite() {
        return heavyEnemySprite;
    }

    public Texture getShieldSprite() {
        return shieldSprite;
    }

    public Texture getSuperShieldSprite() {
        return superChargedShield;
    }

    public Texture getProjectileSprite() {
        return projectileSprite;
    }

    public Texture getEnemyProjectileSprite() {
        return enemyProjectileSprite;
    }

    public Texture get100PointsSprite() {
        return score100Sprite;
    }

    public Texture get500PointsSprite() {
        return score500Sprite;
    }

    public Texture getExplosionSprite() {
        return explosionSprite;
    }

    public Texture getBasicGunSprite() {
        return basicGun;
    }

    // Sound getters
    public Sound getMainWeaponFx() {
        if (useFx) {
            return mainWeaponFx;
        } else {
            return null;
        }
    }

    public Sound getSideWeaponFx() {
        if (useFx) {
            return sideWeaponFx;
        } else {
            return null;
        }
    }

    public Sound getEnemyShootFx() {
        if (useFx) {
            return enemyShootFx;
        } else {
            return null;
        }
    }

    public Sound getSuperLaserFx() {
        if (useFx) {
            return superLaserFx;
        } else {
            return null;
        }
    }

    public void toggleSoundFx() {
        this.useFx = !this.useFx;
    }

    public boolean usesFx() {
        return this.useFx;
    }

    public void toggleMusic() {
        if (useMusic) {
            useMusic = false;
            backgroundMusic.stop();
        } else {
            useMusic = true;
            backgroundMusic.setLooping(true);
            backgroundMusic.setVolume(this.MUSIC_VOLUME);
            backgroundMusic.play();
        }
    }

    public boolean usesMusic() {
        return this.useMusic;
    }

    public void despawn(GameObject object) {
        // adds to deletion list to fix deleting while update is going
        if (!pendingRemove.contains(object) && gameObjects.contains(object)) {
            pendingRemove.add(object);
        }
    }

    public void spawn(GameObject object) {
        // delays addition to before the update starts
        if (!pendingAdd.contains(object) && !gameObjects.contains(object)) {
            pendingAdd.add(object);
        }
    }

    public Player getPlayer() {
        return gameObjects.stream().filter(o -> o instanceof Player)
            .map(o -> (Player) o) // converts gameobject to player
            .findFirst()
            .orElse(null); // returns null if no player was found
    }

    public Enemy[] getEnemies() {
        return gameObjects.stream().filter(o -> o instanceof Enemy)
            .map(o -> (Enemy) o) // converts gameobject to enemy
            .toArray(Enemy[]::new); // converts stream to array
    }

    public Level getLevel() {
        return level;
    }

    public void setNextLevel() {
        this.level = this.campaignInstance.getNextLevel(this);
    }

    public ArrayList<GameObject> getGameObjects() {
        return gameObjects;
    }

    public ArrayList<GameObject> getPendingAdd() {
        return pendingAdd;
    }

    public ArrayList<GameObject> getPendingRemove() {
        return pendingRemove;
    }

    public void changeState(GameScreenState newState) {
        this.currentState = newState;
    }

    public enum ControlMode {
        KEYBOARD,
        MOUSE
    }

    public void setControlMode(ControlMode controlMode) {
        this.controlMode = controlMode;
    }

    public ControlMode getControlMode() {
        return this.controlMode;
    }

    public void resetUpgrades() {
        availableUpgrades = new ArrayList<>(Arrays.asList(//adds default items to shop
            Upgrade.weapon("Main Weapon", "Main weapon shoots", 0, () -> weaponDirector.playerMainWeapon(weaponBuilder)),
            Upgrade.weapon("Side Weapons", "Shoots 2 extra projectiles", 500, () -> weaponDirector.playerSideWeapons(weaponBuilder)),
            Upgrade.shield("Weak Shield", "1 HP, no recharge", 100, () -> shieldFactory.weakShield()),
            Upgrade.shield("Quick Recharge Shield", "1 HP, recharges in 20s", 600, () -> shieldFactory.quickRechargeShield()),
            Upgrade.shield("Strong Shield", "3 HP, no recharge", 1200, () -> shieldFactory.strongShield()),
            Upgrade.superMove("Supercharge shields", "The best defense is a good offense", 1500, SuperShield::new),
            Upgrade.superMove("Supercharge weapons", "MURDER!!!!", 1500, () -> new SuperLaser(getSuperLaserFx()))
        ));
    }

    public ArrayList<Upgrade> getAvailableUpgrades() {
        return availableUpgrades;
    }

    public ArrayList<Upgrade> getPurchasedUpgrades() {
        return purchasedUpgrades;
    }
}
