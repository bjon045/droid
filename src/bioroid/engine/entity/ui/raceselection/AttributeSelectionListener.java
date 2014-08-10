package bioroid.engine.entity.ui.raceselection;

import bioroid.GameHolder.CharacterGenerationHolder;
import bioroid.engine.entity.Entity;
import bioroid.engine.entity.EntityManager;
import bioroid.engine.entity.listener.EntityListener;
import bioroid.model.ModelResources;
import bioroid.model.career.Career;
import bioroid.utils.CodeAndValueUtils;
import bioroid.utils.StringUtils;

public class AttributeSelectionListener implements EntityListener {

    private String attributeCode;

    private int value;

    public AttributeSelectionListener(String attributeCode, int value) {
        this.attributeCode = attributeCode;
        this.value = value;
    }

    @Override
    public void mouseEntered(Entity e) {
        //
    }

    @Override
    public void mouseExited(Entity e) {
        //
    }

    @Override
    public void mousePressed(Entity e, int mouseX, int mouseY) {
        CodeAndValueUtils.adjustValue(attributeCode, value, CharacterGenerationHolder.spentPoints);
        CodeAndValueUtils.adjustValue(attributeCode, value, CharacterGenerationHolder.activeCharacter.getAttributes());
        CharacterGenerationHolder.attributePoints -= value;

        // is selected career still valid?
        String careerCode = CharacterGenerationHolder.activeCharacter.getCareer();
        if (StringUtils.isNotBlank(careerCode)) {
            Career career = (Career) ModelResources.modelObjects.get(careerCode);
            if (!career.canTakeCareer(CharacterGenerationHolder.activeCharacter)) {
                CharacterGenerationHolder.activeCharacter.setCareer(null);
            }
        }

        EntityManager.getRaceSelectionPanel().reset();
    }

}
