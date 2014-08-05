package bioroid.utils;

import java.util.List;

import bioroid.Constants;
import bioroid.GameHolder;
import bioroid.model.character.GameCharacter;
import bioroid.model.location.GameMap;
import bioroid.model.location.Location;

public class MapUtils {

    public static boolean isMoveBlocked(GameMap gameMap, int x, int y, boolean playerControl) {
        // check for impassable objects
        int tileId = gameMap.getMap().getTileId(x, y, Constants.MAP_LAYER_IMPASSABLES);
        if (tileId > 0) {
            return true;
        }
        // check if another character exists in the location
        List<GameCharacter> pcs = GameHolder.currentGame.getPlayerCharacters();
        for (GameCharacter pc : pcs) {
            Location pcLoc = pc.getLocation();
            if ((pcLoc.getX() == x) && (pcLoc.getY() == y)) {
                // switch places with the moved character and return that the
                // path was not blocked
                if (playerControl) {
                    return false;
                }
                return true;
            }
        }

        // check if npc exist in area

        return false;
    }
}
