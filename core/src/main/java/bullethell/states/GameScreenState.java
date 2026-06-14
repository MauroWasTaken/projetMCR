package bullethell.states;

/**
 * Screen interface. Is typically implemented by menus or scenes
 */
public interface GameScreenState {
    void update(float delta);
    void render();
}
