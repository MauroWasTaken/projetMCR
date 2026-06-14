package bullethell.states.statetextwriter;

import com.badlogic.gdx.graphics.Color;

public interface IStateTextWriter {
    float writeCenteredTextAtHeight(String text, float y, float fontSize);
    void writeCenteredTextAtHeight(String text, float y, float fontSize, Color color);
    float writeTightMultilineAtHeight(String[] text, float y, float fontSize);
    void writeLeftBiasedTextAtHeight(String text, float y, float fontSize);
    void writeBottomRightText(String text, float fontSize);
    void writeBottomLeftText(String text, float fontSize);
}
