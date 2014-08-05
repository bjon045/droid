package bioroid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bioroid.control.action.Action;
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

    public static Location viewPoint;

    public static boolean combatMode;

    public static Action currentAction;

    public static class CharacterGenerationHolder {

        public static int attributePoints;

        public static GameCharacter activeCharacter;

        public static List<CodeAndValue> spentPoints;

        public static List<CodeAndValue> maximumPoints;

        public static String selectedSkillGroup;

    }

}
