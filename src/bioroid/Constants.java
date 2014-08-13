package bioroid;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Constants {

    public static final String RESOURCE_FOLDER = "resources_dev";

    public static final int TILE_SIZE = 32;

    public static final int MAP_LAYER_FLOOR = 0;

    public static final int MAP_LAYER_IMPASSABLES = 1;

    public static final int MAP_LAYER_PASSABLES = 2;

    public static final int CHAR_PANEL_WIDTH = 300;

    // width of the popup help text in characters
    public static final int POPUP_CHARACTER_WIDTH = 44;

    // how often actions can be performed in milliseconds. This is set so
    // enemies don't move too fast.
    public static final int ACTION_DELAY = 100;

    public static final int PARTY_SIZE = 6;

    // default attribute starting value
    public static final int ATTRIBUTE_BASE = 3;

    // attribute points available for spending
    public static final int ATTRIBUTE_POINTS = 15;

    // default max value on a single type (racial bonuses can modify this)
    public static final int MAX_ATTRIBUTE = 10;

    public static final String LINE_BREAK = "/b";

    public static final String ADD_LINE_BREAK = " /b ";

    public static final Image ACTIVE_CHARACTER_BACKGROUND;
    static {
        try {
            ACTIVE_CHARACTER_BACKGROUND = new Image(RESOURCE_FOLDER + "/active.png");
        } catch (SlickException e) {
            throw new RuntimeException(e);
        }
    }

}
