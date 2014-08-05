package bioroid.utils;

import java.util.ArrayList;

import bioroid.Constants;
import bioroid.GameHolder;
import bioroid.GameHolder.CharacterGenerationHolder;
import bioroid.model.CodeAndValue;
import bioroid.model.ModelResources;
import bioroid.model.attribute.Attribute;
import bioroid.model.character.GameCharacter;
import bioroid.model.game.SavedGame;
import bioroid.model.race.Race;

public class CharacterUtils {

    public static GameCharacter getMainCharacter() {
        SavedGame currentGame = GameHolder.currentGame;
        return currentGame.getPlayerCharacters().get(0);
    }

    public static GameCharacter createNewCharacter() {
        return new GameCharacter();
    }

    public static void resetAttributes(GameCharacter pc) {
        int pointsSpent = 0;
        pc.getAttributes().clear();

        Race race = (Race) ModelResources.modelObjects.get(pc.getRace());
        Race subrace = null;
        if (StringUtils.isNotBlank(pc.getSubrace())) {
            subrace = (Race) ModelResources.modelObjects.get(pc.getSubrace());
        }

        CharacterGenerationHolder.spentPoints = new ArrayList<CodeAndValue>();
        CharacterGenerationHolder.maximumPoints = new ArrayList<CodeAndValue>();

        for (Attribute attributeType : ModelResources.attributes.getAttributes()) {
            CodeAndValue attribute = new CodeAndValue();
            attribute.setCode(attributeType.getCode());

            int value = Constants.ATTRIBUTE_BASE;
            int totalRacialAdjustment = 0;

            Integer racialModifier = CodeAndValueUtils.getValue(attribute.getCode(), race.getAttributeModifications());
            if (racialModifier != null) {
                value += racialModifier;
                totalRacialAdjustment = racialModifier;
            }

            if (subrace != null) {
                Integer subracialModifier = CodeAndValueUtils.getValue(attribute.getCode(),
                        subrace.getAttributeModifications());
                if (subracialModifier != null) {
                    value += subracialModifier;
                    totalRacialAdjustment += subracialModifier;
                }
            }
            attribute.setValue(value);

            pc.getAttributes().add(attribute);

            // holder for spent points (start at 0)
            CodeAndValue a2 = new CodeAndValue();
            a2.setCode(attributeType.getCode());
            a2.setValue(0);
            CharacterGenerationHolder.spentPoints.add(a2);

            // holder for maximum point spend
            CodeAndValue a3 = new CodeAndValue();
            a3.setCode(attributeType.getCode());
            a3.setValue(Constants.MAX_ATTRIBUTE + totalRacialAdjustment);
            CharacterGenerationHolder.maximumPoints.add(a3);
        }

        CharacterGenerationHolder.attributePoints = Constants.ATTRIBUTE_POINTS - pointsSpent;
    }

}
