package bioroid.engine.entity.ui.raceselection;

import bioroid.GameHolder;
import bioroid.GameMode;
import bioroid.engine.entity.Entity;
import bioroid.engine.entity.EntityManager;
import bioroid.engine.entity.listener.EntityListener;

public class RaceSelectionNextButtonListener implements EntityListener {

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
        GameHolder.gameMode = GameMode.SKILL_SELECTION;
        EntityManager.getSkillSelectionPanel().reset();
        // CharacterUtils.resetAttributes(CharacterGenerationHolder.activeCharacter);
        // TODO: requirement: reset character skills
    }

}
