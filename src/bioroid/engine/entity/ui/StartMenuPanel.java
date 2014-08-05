package bioroid.engine.entity.ui;

import static bioroid.Constants.RESOURCE_FOLDER;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import bioroid.GameHolder;
import bioroid.GameMode;
import bioroid.control.EntityManager;
import bioroid.engine.entity.Entity;
import bioroid.engine.entity.StaticImageEntity;
import bioroid.engine.entity.StringEntity;
import bioroid.engine.entity.listener.EntityListener;
import bioroid.utils.GameLoaderUtils;

public class StartMenuPanel extends Entity {

    private List<Entity> entities = new ArrayList<Entity>();

    public StartMenuPanel(int x, int y, int width, int height) {
        super(x, y, width, height, 0);
        Image startImage;
        try {
            startImage = new Image(RESOURCE_FOLDER + "/logo.png");
        } catch (SlickException e) {
            throw new RuntimeException(e);
        }
        // TODO: should offset x and y by the entities x and y on these
        // images/button
        StaticImageEntity logo = new StaticImageEntity((width / 2) - (startImage.getWidth() / 2), height / 5,
                startImage);
        entities.add(logo);

        StringEntity startButton = new StringEntity((width / 2) - 75, height / 2, 12, "Start Game");
        startButton.setDrawBorder(true);
        startButton.setListener(new StartButtonListener());
        entities.add(startButton);

        StringEntity loadButton = new StringEntity((width / 2) - 75, (int) (height / 1.75), 12, "Load Game");
        loadButton.setDrawBorder(true);
        loadButton.setListener(new LoadButtonListener());
        entities.add(loadButton);
    }

    @Override
    public void update(int delta) {

    }

    @Override
    public List<Entity> getChildren() {
        return entities;
    }

    public class StartButtonListener implements EntityListener {

        @Override
        public void mouseEntered(Entity e) {
            // no action required
        }

        @Override
        public void mouseExited(Entity e) {
            // no action required
        }

        @Override
        public void mousePressed(Entity e, int mouseX, int mouseY) {
            GameLoaderUtils.newGame();
            EntityManager.getPartyGenerationPanel().reset();
            GameHolder.gameMode = GameMode.PARTY_GENERATION;
        }

    }

    public class LoadButtonListener implements EntityListener {

        @Override
        public void mouseEntered(Entity e) {
            // no action required
        }

        @Override
        public void mouseExited(Entity e) {
            // no action required
        }

        @Override
        public void mousePressed(Entity e, int mouseX, int mouseY) {
            GameLoaderUtils.loadGame("test");
            GameHolder.gameMode = GameMode.MAIN_GAME;

            // TODO implement menu UI's game system. current default to straight
            // into map
            EntityManager.getMapPanel().reset();
        }

    }

}
