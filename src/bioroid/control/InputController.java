package bioroid.control;

import java.util.List;

import bioroid.GameHolder;
import bioroid.control.action.Action;
import bioroid.control.action.ActionType;
import bioroid.engine.entity.Entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

public class InputController {

    public void checkForInput(GameContainer container) {
        // get mouse current location
        Input input = container.getInput();
        int mx = input.getMouseX();
        int my = input.getMouseY();
        boolean leftMouseClick = input.isMousePressed(Input.MOUSE_LEFT_BUTTON);

        // get entity mouse is over
        List<Entity> entities = EntityManager.getEntities(GameHolder.gameMode);
        Entity e = processEntities(mx, my, entities);

        // click on entity
        if ((e != null) && leftMouseClick) {
            e.mousePressed(mx, my);
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
        }
    }

    private Entity processEntities(int mx, int my, List<? extends Entity> entities) {
        Entity currentEntity = null;

        Entity e;
        for (int i = entities.size() - 1; i >= 0; i--) {
            e = entities.get(i);
            if (e.inside(mx, my)) {
                if (!e.isMouseInside()) {
                    e.mouseEntered();
                }
                if (e.getChildren() != null) {
                    Entity e2 = processEntities(mx, my, e.getChildren());
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
