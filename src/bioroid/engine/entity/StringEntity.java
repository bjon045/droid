package bioroid.engine.entity;

import java.util.Arrays;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import bioroid.font.Fonts;
import bioroid.utils.StringUtils;

public class StringEntity extends Entity {

    private static final int ROW_GAP = 2;

    private static int lineHeight = Fonts.BASIC_FONT.getHeight("A") + ROW_GAP;

    private static int lineWidth = Fonts.BASIC_FONT.getWidth("A") + 3;

    private Color colour = Color.green;

    private List<String> values;

    public StringEntity(int x, int y, String value) {
	super(x, y, 0, 0, 0);
	values = Arrays.asList(value);
	setHeight(values.size() * lineHeight);
	setWidth(value.length() * lineWidth);
    }

    public StringEntity(int x, int y, int maxWidth, String value) {
	super(x, y, 0, 0, 0);
	values = StringUtils.breakUpByWords(value, maxWidth);
	setHeight(values.size() * lineHeight);
	setWidth(maxWidth * lineWidth);
    }

    public StringEntity(int x, int y, int width, int height, String value) {
	super(x, y, width, height, 0);
	values = StringUtils.breakUpByWords(value, 100);
    }

    public void setColour(Color colour) {
	this.colour = colour;
    }

    @Override
    public void update(int delta) {
	// no update required
    }

    @Override
    protected void renderEntitySpecific(GameContainer container, Graphics g) {

	int rowNo = 0;
	for (String s : values) {
	    Fonts.BASIC_FONT.drawString(getX(), getY() + (rowNo * lineHeight), s, colour);
	    rowNo++;
	}
    }

}
