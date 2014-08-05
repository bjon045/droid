package bioroid.engine.entity.ui.skillselection;

import java.util.ArrayList;
import java.util.List;

import bioroid.GameHolder.CharacterGenerationHolder;
import bioroid.engine.entity.Entity;
import bioroid.engine.entity.StringEntity;
import bioroid.model.ModelResources;
import bioroid.model.character.GameCharacter;
import bioroid.model.skill.SkillGroup;
import bioroid.utils.StringUtils;

public class SkillSelectionPanel extends Entity {

    public static final int FIRST_LEFT_OFFSET = 20;

    public static final int SECOND_LEFT_OFFSET = 80;

    public static final int THIRD_LEFT_OFFSET = 320;

    public static final int FORTH_LEFT_OFFSET = 380;

    public static final int FIFTH_LEFT_OFFSET = 450;

    public static final int SIXTH_LEFT_OFFSET = 620;

    public static final int SEVENTH_LEFT_OFFSET = 680;

    public static final int PANEL_GAP = 25;

    private List<Entity> entities = new ArrayList<Entity>();

    public SkillSelectionPanel(int x, int y, int width, int height) {
	super(x, y, width, height, 0);
    }

    /**
     * Call this method when underlying party structure is changed
     */
    public void reset() {
	// refresh list of entities
	entities.clear();
	GameCharacter pc = CharacterGenerationHolder.activeCharacter;
	int counter1 = 0;

	// Skill groups
	List<SkillGroup> skillGroups = ModelResources.skillGroups.getSkillGroups();
	for (SkillGroup skillGroup : skillGroups) {
	    StringEntity skillGroupLabel = new StringEntity(getX() + FIRST_LEFT_OFFSET + (counter1 * 150), getY() + 10, skillGroup.getName());
	    skillGroupLabel.setListener(new SkillGroupSelectionListener(skillGroup.getCode()));

	    if (StringUtils.equals(CharacterGenerationHolder.selectedSkillGroup, skillGroup.getCode())) {
		skillGroupLabel.setDrawBorder(true);
	    }

	    entities.add(skillGroupLabel);
	    counter1++;
	}

	// skills
	if (StringUtils.isNotBlank(CharacterGenerationHolder.selectedSkillGroup)) {

	}

    }

    @Override
    public void update(int delta) {

    }

    @Override
    public List<Entity> getChildren() {
	return entities;
    }

}
