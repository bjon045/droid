package bioroid.engine.entity.ui.skillselection;

import bioroid.GameHolder.CharacterGenerationHolder;
import bioroid.engine.entity.Entity;
import bioroid.engine.entity.EntityManager;
import bioroid.engine.entity.listener.EntityListener;

public class SkillGroupSelectionListener implements EntityListener {

    private String skillGroupCode;

    public SkillGroupSelectionListener(String skillGroupCode) {
        this.skillGroupCode = skillGroupCode;
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
        CharacterGenerationHolder.selectedSkillGroup = skillGroupCode;
        EntityManager.getSkillSelectionPanel().reset();
    }

}
