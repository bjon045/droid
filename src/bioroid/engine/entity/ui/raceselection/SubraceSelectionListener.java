package bioroid.engine.entity.ui.raceselection;

import bioroid.GameHolder.CharacterGenerationHolder;
import bioroid.engine.entity.Entity;
import bioroid.engine.entity.EntityManager;
import bioroid.engine.entity.listener.PopupListener;
import bioroid.model.race.Race;
import bioroid.utils.CharacterUtils;

public class SubraceSelectionListener extends PopupListener {

    private Race race;

    public SubraceSelectionListener(String popupText, Race race) {
        super(popupText);
        this.race = race;
    }

    @Override
    public void mousePressed(Entity e, int mouseX, int mouseY) {
        CharacterGenerationHolder.activeCharacter.setSubrace(race.getCode());
        CharacterGenerationHolder.activeCharacter.setCareer(null);
        CharacterUtils.resetAttributes(CharacterGenerationHolder.activeCharacter);
        EntityManager.getRaceSelectionPanel().reset();
    }

}
