package bioroid.control;

import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

import bioroid.GameHolder;
import bioroid.control.action.Action;
import bioroid.control.action.ActionType;
import bioroid.engine.entity.Entity;
import bioroid.engine.entity.EntityManager;
import bioroid.engine.entity.ui.BackgroundPanel;
import bioroid.engine.entity.ui.main.CharacterPanel;
import bioroid.model.location.Location;

public class InputController {

    public void checkForInput(GameContainer container) {
        // get mouse current location
        Input input = container.getInput();
        int mx = input.getMouseX();
        int my = input.getMouseY();
        boolean leftMouseClick = input.isMousePressed(Input.MOUSE_LEFT_BUTTON);
        // get entity mouse is over
        List<Entity> entities = EntityManager.getEntities(GameHolder.gameMode);
        Entity e = processEntities(mx, my, entities, true);
        // click on entity
        if ((e != null) && leftMouseClick) {
            e.mousePressed(mx, my);
        }

        if (GameHolder.blockingEntity != null) {
            return;
        }

        // capture keyboard events
        if (container.getInput().isKeyPressed(Input.KEY_LEFT)) {
            GameHolder.currentAction = new Action(ActionType.MOVE_WEST);
        } else if (container.getInput().isKeyPressed(Input.KEY_RIGHT)) {
            GameHolder.currentAction = new Action(ActionType.MOVE_EAST);
        } else if (container.getInput().isKeyPressed(Input.KEY_UP)) {
            GameHolder.currentAction = new Action(ActionType.MOVE_NORTH);
        } else if (container.getInput().isKeyPressed(Input.KEY_DOWN)) {
            GameHolder.currentAction = new Action(ActionType.MOVE_SOUTH);
        } else if (container.getInput().isKeyPressed(Input.KEY_SPACE)) {
            GameHolder.currentAction = new Action(ActionType.PASS);
        } else if (container.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
            // GameLoader.saveGame("test");
            container.exit();
        } else if (container.getInput().isKeyPressed(Input.KEY_ENTER)) {
            GameHolder.combatMode = true;
        } else if (container.getInput().isKeyPressed(Input.KEY_C)) {
            CharacterPanel charPanel = EntityManager.getCharacterPanel();
            charPanel.setActive(!charPanel.isActive());
            BackgroundPanel backgroundPanel = EntityManager.getBackgroundPanel();
            Location mainPanelLocation = backgroundPanel.getMainStart();
            EntityManager.getMapPanel().updatePosition(mainPanelLocation.getX(), mainPanelLocation.getY(),
                    backgroundPanel.getMapWidth(), backgroundPanel.getMainHeight());
        }
    }

    private Entity processEntities(int mx, int my, List<? extends Entity> entities, boolean parentEntities) {
        Entity currentEntity = null;
        for (Entity e : entities) {
            if (parentEntities && GameHolder.blockingEntity != null && GameHolder.blockingEntity != e) {
                continue;
            }
            if (e.inside(mx, my)) {

                if (!e.isMouseInside()) {
                    e.mouseEntered();
                }
                if (e.getChildren() != null) {

                    Entity e2 = processEntities(mx, my, e.getChildren(), false);
                    if ((e2 != null) && (currentEntity == null)) {
                        currentEntity = e2;
                    }
                }
                if (currentEntity == null) {
                    currentEntity = e;
                }

            } else {

                updateMouseNotInside(e);
            }
            // mouse not over and was never inside

        }
        return currentEntity;
    }

    private void updateMouseNotInside(Entity e) {

        if (e.isMouseInside()) {
            // mouse is no longer inside
            e.mouseExited();
            if (e.getChildren() != null) {
                for (Entity ee : e.getChildren()) {
                    updateMouseNotInside(ee);
                }
            }
        }
    }

}
