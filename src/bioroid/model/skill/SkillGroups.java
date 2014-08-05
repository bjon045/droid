package bioroid.model.skill;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SkillGroups {

    private List<SkillGroup> skillGroups;

    @XmlElement(name = "skillGroup")
    public List<SkillGroup> getSkillGroups() {
        return skillGroups;
    }

    public void setSkillGroups(List<SkillGroup> skillGroups) {
        this.skillGroups = skillGroups;
    }
}
