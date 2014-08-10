package bioroid.engine.entity.ui.partygeneration;

import bioroid.GameHolder;
import bioroid.GameHolder.CharacterGenerationHolder;
import bioroid.GameMode;
import bioroid.engine.entity.Entity;
import bioroid.engine.entity.EntityManager;
import bioroid.engine.entity.listener.EntityListener;
import bioroid.utils.CharacterUtils;

public class CreateCharacterButtonListener implements EntityListener {

    @Override
    public void mouseEntered(Entity e) {
        // no action required
    }

    @Override
    public void mouseExited(Entity e) {
        // no action required
    }

    @Override
    public void mousePressed(Entity e, int mouseX, int mouseY) {
        GameHolder.gameMode = GameMode.RACE_SELECTION;
        CharacterGenerationHolder.activeCharacter = CharacterUtils.createNewCharacter();
        EntityManager.getRaceSelectionPanel().reset();
    }

}