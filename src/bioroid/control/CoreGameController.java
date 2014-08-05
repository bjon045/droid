package bioroid.control;

import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.util.pathfinding.Path;
import org.newdawn.slick.util.pathfinding.Path.Step;

import bioroid.Constants;
import bioroid.GameHolder;
import bioroid.GameMode;
import bioroid.control.action.Action;
import bioroid.model.character.GameCharacter;
import bioroid.model.location.GameMap;
import bioroid.model.location.Location;
import bioroid.utils.CharacterUtils;

public class CoreGameController {

    private InputController inputController = new InputController();

    private EventController eventController = new EventController();

    private int delta;

    public void update(GameContainer container, int delta) {

        inputController.checkForInput(container);

        if (GameHolder.gameMode != GameMode.MAIN_GAME) {
            GameHolder.currentAction = null;
            return;
        }

        this.delta = this.delta + delta;

        if (this.delta < Constants.ACTION_DELAY) {
            return;
        }
        this.delta = 0;

        if (GameHolder.currentAction == null) {
            return;
        }

        GameMap gameMap = GameHolder.currentMap;

        if (GameHolder.combatMode) {
            // combat mode
            // in this mode only the current actor can move. if this is player
            // controlled check for keyboard event
            // if not player character then we need to check who's turn it is
            // and determine their move
            // a pause of 1 second (1000 delta) should be held between turns.
        } else {
            // if not in combat mode then only the active character (character
            // 0) moves
            // after a successful move then all remaining actors are updated

            GameCharacter activeCharacter = CharacterUtils.getMainCharacter();
            Location lastMoved = activeCharacter.getLocation();

            boolean moved = moveActiveCharacter(container, gameMap, activeCharacter);

            if (!moved) {
                return;
            }

            // update map view as we are not in combat and the main character
            // has moved.
            GameHolder.viewPoint.updateWith(activeCharacter.getLocation());

            // update the rest of the party. This is most likely to just make
            // them follow the main character.
            List<GameCharacter> pcs = GameHolder.currentGame.getPlayerCharacters();
            for (GameCharacter pc : pcs) {
                if (pc != activeCharacter) {
                    Location currentLocation = pc.getLocation();
                    gotoLocation(pc, lastMoved, gameMap);
                    lastMoved = currentLocation;
                }
            }

            eventController.update();
        }

        // TODO optimisation/design: decide if all maps should be updated or not. could be slow
        // depending on the number of maps/AI complexity

    }

    private void gotoLocation(GameCharacter person, Location targetLocation, GameMap gameMap) {
        Location followerLocation = person.getLocation();
        Path path = gameMap.getPathFinder().findPath(person, targetLocation.getX(), targetLocation.getY(),
                followerLocation.getX(), followerLocation.getY());
        // dont move if already next to target
        if ((path != null) && (path.getLength() > 1)) {
            Step step = path.getStep(path.getLength() - 2);
            if (!gameMap.isMoveBlocked(step.getX(), step.getY(), false)) {
                followerLocation.setX(step.getX());
                followerLocation.setY(step.getY());
            }
        }
    }

    private boolean moveActiveCharacter(GameContainer container, GameMap gameMap, GameCharacter activeCharacter) {

        Action action = GameHolder.currentAction;

        Location currentLocation = activeCharacter.getLocation();
        Location newLocation = currentLocation.copy();

        switch (action.getActionType()) {
        case PASS:
            break;
        case MOVE_WEST:
            newLocation.decrementX();
            break;
        case MOVE_EAST:
            newLocation.incrementX();
            break;
        case MOVE_NORTH:
            newLocation.derementY();
            break;
        case MOVE_SOUTH:
            newLocation.incrementY();
            break;
        case MOVE:
            gotoLocation(activeCharacter, action.getLocation(), gameMap);
            if (activeCharacter.getLocation().equals(action.getLocation())) {
                GameHolder.currentAction = null;
            }
            return true;
        }

        GameHolder.currentAction = null;

        if ((newLocation != null) && !gameMap.isMoveBlocked(newLocation.getX(), newLocation.getY(), true)) {
            // TODO: check if blocked because of another PC if so then switch
            // places
            activeCharacter.setLocation(newLocation);
            System.out.println("Moved to location x:" + newLocation.getX() + " y:" + newLocation.getY());
            return true;
        }
        return false;
    }

}
