package bioroid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bioroid.control.action.Action;
import bioroid.engine.entity.Entity;
import bioroid.model.CodeAndValue;
import bioroid.model.character.GameCharacter;
import bioroid.model.game.SavedGame;
import bioroid.model.location.GameMap;
import bioroid.model.location.Location;

public class GameHolder {

    public static SavedGame currentGame;

    public static GameMap currentMap;

    public static GameMode gameMode;

    public static Map<String, GameMap> maps = new HashMap<String, GameMap>();

    public static GameCharacter activeCharacter;

    public static List<GameCharacter> allCharactersOnMap = new ArrayList<GameCharacter>();

    // map window x,y focus point
    public static Location viewPoint;

    // Not currently implement but will enable turn based movement
    public static boolean combatMode;

    // This is set by InputController or a listener i..e MapListener (see MapPanel)
    // the current action will be processed by the CoreGameController
    public static Action currentAction;

    // represents a screen entity which blocks all other input
    // i.e. a popup dialog that needs to be closed.
    public static Entity blockingEntity;

    public static class CharacterGenerationHolder {

        public static int attributePoints;

        public static GameCharacter activeCharacter;

        public static List<CodeAndValue> spentPoints;

        public static List<CodeAndValue> maximumPoints;

        public static String selectedSkillGroup;

    }

}
