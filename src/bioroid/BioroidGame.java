package bioroid;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import bioroid.control.CoreGameController;
import bioroid.control.EntityManager;
import bioroid.engine.entity.Entity;
import bioroid.model.ModelResources;

public class BioroidGame implements Game {

    private CoreGameController coreGameController;

    @Override
    public boolean closeRequested() {
        return true;
    }

    @Override
    public String getTitle() {
        return "Bioroid";
    }

    public static void main(String[] args) throws SlickException {
        // System.setProperty("org.lwjgl.opengl.Window.undecorated", "false");
        BioroidGame bioroidGame = new BioroidGame();
        DisplayMode ddm = Display.getDesktopDisplayMode();
        AppGameContainer agc = new AppGameContainer(bioroidGame, 800, 600, false);
        // AppGameContainer agc = new AppGameContainer(bioroidGame,
        // ddm.getWidth(), ddm.getHeight(), true);
        agc.setTargetFrameRate(70);
        agc.start();
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        System.out.println("Model count:" + ModelResources.modelObjects.size());

        coreGameController = new CoreGameController();
        GameHolder.gameMode = GameMode.START_MENU;
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        // logic branch
        coreGameController.update(container, delta);

        // update ui entities
        for (Entity entity : EntityManager.getEntities(GameHolder.gameMode)) {
            entity.update(delta);
        }

        if (container.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
            // GameLoader.saveGame("test");
            container.exit();
        }
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        for (Entity entity : EntityManager.getEntities(GameHolder.gameMode)) {
            entity.render(container, g);
        }
    }
}
