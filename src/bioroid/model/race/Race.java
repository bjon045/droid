package bioroid.model.race;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import bioroid.Constants;
import bioroid.model.CodeAndValue;
import bioroid.model.ModelObject;
import bioroid.model.ModelResources;
import bioroid.model.attribute.Attribute;
import bioroid.utils.CollectionUtils;

public class Race extends ModelObject {

    private List<CodeAndValue> attributeModifications = new ArrayList<CodeAndValue>();

    private List<String> subraces = new ArrayList<String>();

    @XmlElement(name = "attributeModification")
    public List<CodeAndValue> getAttributeModifications() {
        return attributeModifications;
    }

    public void setAttributeModifications(List<CodeAndValue> attributeModifications) {
        this.attributeModifications = attributeModifications;
    }

    @XmlElement(name = "subrace")
    public List<String> getSubraces() {
        return subraces;
    }

    public void setSubraces(List<String> subraces) {
        this.subraces = subraces;
    }

    @Override
    public String getDescription() {
        StringBuilder sb = new StringBuilder(super.getDescription());

        // attribute modifications
        sb.append(Constants.ADD_LINE_BREAK).append(Constants.ADD_LINE_BREAK);
        sb.append("Attribute Modifications:").append(Constants.ADD_LINE_BREAK);
        if (CollectionUtils.isNotEmpty(attributeModifications)) {
            for (CodeAndValue codeAndValue : attributeModifications) {
                Attribute attribute = (Attribute) ModelResources.modelObjects.get(codeAndValue.getCode());
                int value = codeAndValue.getValue();
                if (value > 0) {
                    sb.append("+").append(value).append(" ").append(attribute.getName())
                            .append(Constants.ADD_LINE_BREAK);
                } else if (value < 0) {
                    sb.append(value).append(" ").append(attribute.getName()).append(Constants.ADD_LINE_BREAK);
                }
            }
        } else {
            sb.append("None").append(Constants.ADD_LINE_BREAK);
        }

        // subraces
        sb.append(Constants.ADD_LINE_BREAK);
        sb.append("Allowable subraces:").append(Constants.ADD_LINE_BREAK);
        if (CollectionUtils.isNotEmpty(subraces)) {
            for (String raceCode : subraces) {
                Race race = (Race) ModelResources.modelObjects.get(raceCode);
                sb.append(race.getName()).append(Constants.ADD_LINE_BREAK);

            }
        } else {
            sb.append("None").append(Constants.ADD_LINE_BREAK);
        }

        return sb.toString();
    }

}
