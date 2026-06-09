package bullethell;

import bullethell.gameobjects.Enemy;
import bullethell.gameobjects.Player;
import bullethell.gameobjects.scoredrops.ScoreDropFactory;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import bullethell.gameobjects.GameObject;

import java.util.ArrayList;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */
public class GameContext extends ApplicationAdapter {
    private final float playWidth = 450f;
    private final float playHeight = 600f;

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private FitViewport viewport;
    private Texture background;
    private Texture playerSprite;
    private Texture projectileSprite;
    private Texture score100Sprite;
    private Texture score500Sprite;
    private BitmapFont font;
    private ShapeRenderer shapeRenderer;
    private final ArrayList<GameObject> gameObjects = new ArrayList<>();
    private final ArrayList<GameObject> pendingAdd = new ArrayList<>();
    private final ArrayList<GameObject> pendingRemove = new ArrayList<>();

    Level level;

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
        // load shared sprite texture and create player
        playerSprite = new Texture("player.png");
        projectileSprite = new Texture("projectile.png");
        score100Sprite = new Texture("Score100.png");
        score500Sprite = new Texture("Score500.png");
        font = new BitmapFont();
        shapeRenderer = new ShapeRenderer();
        gameObjects.add(new Player(this));
        level = new Level(this);
    }

    @Override
    public void render() {
        ScreenUtils.clear(0f, 0f, 0f, 1f);
        float delta = Gdx.graphics.getDeltaTime();

        //System.out.println("FPS: " + Gdx.graphics.getFramesPerSecond());
        // execute level logic
        level.update(delta);

        // update game objects
        for (GameObject gameObject : gameObjects) {
            gameObject.update(delta);
        }

        // delete pending objects
        if (!pendingRemove.isEmpty()) {
            for (GameObject o : pendingRemove) {
                gameObjects.remove(o);
            }
            pendingRemove.clear();
        }
        // add pending objects
        if (!pendingAdd.isEmpty()) {
            gameObjects.addAll(pendingAdd);
            pendingAdd.clear();
        }
        //draw objects
        batch.begin();
        batch.draw(background, 0f, 0f, playWidth, playHeight);
        for (GameObject gameObject : gameObjects) {
            gameObject.render(batch);
        }
        font.draw(batch, "money: " + bullethell.currencysystem.CurrencyBank.getInstance().getValue(), playWidth - 100, 20);
        batch.end();
        // hitbox viewer (might only keep it for the player)
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.RED);
        for (GameObject gameObject : gameObjects) {
            shapeRenderer.polygon(gameObject.getHitbox().getTransformedVertices());
        }
        shapeRenderer.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        background.dispose();
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

    public Texture getPlayerSprite() {
        return playerSprite;
    }
    public Texture getProjectileSprite() { return projectileSprite; }

    public Texture get100PointsSprite() {
        return score100Sprite;
    }

    public Texture get500PointsSprite() {
        return score500Sprite;
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
    public Player getPlayer(){
        return gameObjects.stream().filter(o -> o instanceof Player)
            .map(o -> (Player) o) // converts gameobject to player
            .findFirst()
            .orElse(null); // returns null if no player was found
    }
    public Enemy[] getEnemies(){
        return gameObjects.stream().filter(o -> o instanceof Enemy)
            .map(o -> (Enemy) o) // converts gameobject to enemy
            .toArray(Enemy[]::new); // converts stream to array
    }
}
