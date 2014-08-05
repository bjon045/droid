package bioroid.model.career;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import bioroid.Constants;
import bioroid.model.CodeAndValue;
import bioroid.model.ModelObject;
import bioroid.model.ModelResources;
import bioroid.model.attribute.Attribute;
import bioroid.model.character.GameCharacter;
import bioroid.utils.CodeAndValueUtils;
import bioroid.utils.CollectionUtils;

public class Career extends ModelObject {

    private List<CodeAndValue> attributeRequirements = new ArrayList<CodeAndValue>();

    private int hitsPerLevel;

    @XmlElement(name = "attributeRequirement")
    public List<CodeAndValue> getAttributeRequirements() {
        return attributeRequirements;
    }

    public void setAttributeRequirements(List<CodeAndValue> attributeRequirements) {
        this.attributeRequirements = attributeRequirements;
    }

    public int getHitsPerLevel() {
        return hitsPerLevel;
    }

    public void setHitsPerLevel(int hitsPerLevel) {
        this.hitsPerLevel = hitsPerLevel;
    }

    @Override
    public String getDescription() {
        StringBuilder sb = new StringBuilder(super.getDescription());

        // attribute requirements
        sb.append(Constants.ADD_LINE_BREAK).append(Constants.ADD_LINE_BREAK);
        sb.append("Attribute Requirements:").append(Constants.ADD_LINE_BREAK);
        if (CollectionUtils.isNotEmpty(attributeRequirements)) {
            for (CodeAndValue codeAndValue : attributeRequirements) {
                Attribute attribute = (Attribute) ModelResources.modelObjects.get(codeAndValue.getCode());
                int value = codeAndValue.getValue();
                if (value > 0) {
                    sb.append(attribute.getName()).append(": ").append(value).append(Constants.ADD_LINE_BREAK);
                }
            }
        } else {
            sb.append("None").append(Constants.ADD_LINE_BREAK);
        }

        // hits per level
        sb.append(Constants.ADD_LINE_BREAK);
        sb.append("Hitpoints per level: ").append(hitsPerLevel).append(Constants.ADD_LINE_BREAK);

        return sb.toString();
    }

    public boolean canTakeCareer(GameCharacter pc) {
        if (CollectionUtils.isNotEmpty(attributeRequirements)) {
            for (CodeAndValue codeAndValue : attributeRequirements) {
                int requiredValue = codeAndValue.getValue();
                int currentValue = CodeAndValueUtils.getValue(codeAndValue.getCode(), pc.getAttributes());
                if (requiredValue > currentValue) {
                    return false;
                }
            }
        }

        return true;
    }

}
