package bioroid.engine.entity.ui;

import static bioroid.Constants.RESOURCE_FOLDER;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import bioroid.Constants;
import bioroid.GameHolder;
import bioroid.GameMode;
import bioroid.engine.entity.Entity;
import bioroid.model.location.Location;

public class BackgroundPanel extends Entity {

    private Image horizontalBorder;

    private Image borderTopLeft;

    private Image borderTee;

    private int borderThickness;

    private int halfTeeThickness;

    private int charPanelXSize;

    private List<Entity> children = new ArrayList<Entity>();

    public BackgroundPanel(int x, int y, int width, int height) {
        super(x, y, width, height, 0);
        try {
            horizontalBorder = new Image(RESOURCE_FOLDER + "/border_horizontal.png", false, Image.FILTER_NEAREST);
            borderTopLeft = new Image(RESOURCE_FOLDER + "/border_top_left.png");
            borderTee = new Image(RESOURCE_FOLDER + "/border_tee.png");

            borderThickness = horizontalBorder.getHeight();
            halfTeeThickness = borderTee.getWidth() / 2;
            charPanelXSize = Constants.CHAR_PANEL_WIDTH + (borderThickness * 2);
        } catch (SlickException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void renderEntitySpecific(GameContainer container, Graphics g) {
        // top border
        horizontalBorder.draw(getX(), getY(), getWidth(), borderThickness);

        // bottom border
        horizontalBorder.draw(getX(), (getY() + getHeight()) - borderThickness, getWidth(), borderThickness);

        // left border
        g.rotate(getX(), getY(), 90);
        horizontalBorder.draw(getX(), getY() - borderThickness, getHeight(), borderThickness);
        g.rotate(getX(), getY(), -90);

        // right border
        g.rotate(getX(), getY(), 90);
        horizontalBorder.draw(getX(), (getY() - getWidth()), getHeight(), borderThickness);
        g.rotate(getX(), getY(), -90);

        // top left corner
        borderTopLeft.draw(getX(), getY());

        // top right corner
        g.rotate(getX(), getY(), 90);
        borderTopLeft.draw(getX(), getY() - getWidth());
        g.rotate(getX(), getY(), -90);

        // bottom left corner
        g.rotate(getX(), getY(), -90);
        borderTopLeft.draw(getX() - getHeight(), getY());
        g.rotate(getX(), getY(), 90);

        // bottom right corner
        g.rotate(getX(), getY(), 180);
        borderTopLeft.draw(getX() - getWidth(), getY() - getHeight());
        g.rotate(getX(), getY(), -180);

        if (GameHolder.gameMode == GameMode.MAIN_GAME) {
            // center border
            g.rotate(getX(), getY(), 90);
            horizontalBorder.draw(getX(), ((getY() - getWidth()) + charPanelXSize) - borderThickness, getHeight(),
                    borderThickness);
            g.rotate(getX(), getY(), -90);

            // top tee
            borderTee.draw((getX() + ((getWidth() - charPanelXSize) + (borderThickness / 2))) - halfTeeThickness,
                    getY());

            // bottom tee
            g.rotate(getX(), getY(), 180);
            borderTee.draw((((getX() - getWidth()) + charPanelXSize) - (borderThickness / 2)) - halfTeeThickness,
                    getY() - getHeight());
            g.rotate(getX(), getY(), -180);
        }
    }

    @Override
    public void update(int delta) {
        // not used
    }

    /**
     * Get the topleft pixel location of the main window that is usable i.e. topLeft pixel after the border ends. Used
     * for map, char screen etc
     * 
     * @return
     */

    public Location getMainStart() {
        return new Location(getX() + borderThickness, getY() + borderThickness);
    }

    public int getMainWidth() {
        return getWidth() - borderThickness;
    }

    public int getMainHeight() {
        return getHeight() - (borderThickness * 2);
    }

    public int getMapWidth() {
        return (getWidth() - charPanelXSize) - borderThickness;
    }

    public int getMapHeight() {
        return getMainHeight();
    }

    public int getBorderThickness() {
        return borderThickness;
    }

    @Override
    public boolean inside(int xp, int yp) {
        return false;
    }

    @Override
    public List<Entity> getChildren() {
        return children;
    }

}
