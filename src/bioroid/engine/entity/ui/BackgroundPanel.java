package bioroid.engine.entity.ui;

import static bioroid.Constants.RESOURCE_FOLDER;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import bioroid.Constants;
import bioroid.GameHolder;
import bioroid.GameMode;
import bioroid.engine.entity.EntityManager;

public class BackgroundPanel extends BorderedPanel {

    private Image borderTee;

    private int halfTeeThickness;

    private int charPanelXSize;

    public BackgroundPanel(int x, int y, int width, int height) {
        super(x, y, width, height);
        try {
            horizontalBorder = new Image(RESOURCE_FOLDER + "/border_horizontal.png", false, Image.FILTER_NEAREST);
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
        super.renderEntitySpecific(container, g);
        if (GameHolder.gameMode == GameMode.MAIN_GAME && EntityManager.getCharacterPanel().isActive()) {
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

    public int getMapWidth() {
        return (getWidth() - (EntityManager.getCharacterPanel().isActive() ? charPanelXSize : 0)) - borderThickness;
    }

    public int getMapHeight() {
        return getMainHeight();
    }

    @Override
    public boolean inside(int xp, int yp) {
        return false;
    }

}
