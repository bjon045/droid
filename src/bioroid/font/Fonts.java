package bioroid.font;

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.SlickException;

public class Fonts {

    public static final Font BASIC_FONT;

    public static final Color DARK_GREEN = new Color(0, 80, 0);

    static {
	try {
	    BASIC_FONT = new AngelCodeFont("font/space.fnt", "font/space_00.png");

	} catch (SlickException e) {
	    throw new RuntimeException(e);
	}
    }
}
