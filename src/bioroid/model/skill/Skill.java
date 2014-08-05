package bioroid.model.skill;

import java.util.List;

import bioroid.model.ModelObject;
import bioroid.model.ModelResources;
import bioroid.utils.CollectionUtils;

public class Skill extends ModelObject {

    private List<PrerequisiteSkill> prerequisiteSkill;

    public List<PrerequisiteSkill> getPrerequisiteSkill() {
        return prerequisiteSkill;
    }

    public void setPrerequisiteSkill(List<PrerequisiteSkill> prerequisiteSkill) {
        this.prerequisiteSkill = prerequisiteSkill;
    }

    public static class PrerequisiteSkill {

        private String code;

        private int value;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

    }

    @Override
    public boolean validate() {
        if (CollectionUtils.isNotEmpty(prerequisiteSkill)) {
            for (PrerequisiteSkill preSkill : prerequisiteSkill) {
                if (preSkill.value == 0) {
                    return false;
                }
                // check skill actually exists
                ModelObject modelObject = ModelResources.modelObjects.get(preSkill.code);
                if ((modelObject == null) || !(modelObject instanceof Skill)) {
                    return false;
                }

            }
        }
        return super.validate();
    }

}
