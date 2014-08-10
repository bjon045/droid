package bioroid.engine.entity.ui.raceselection;

import bioroid.GameHolder.CharacterGenerationHolder;
import bioroid.engine.entity.Entity;
import bioroid.engine.entity.EntityManager;
import bioroid.engine.entity.listener.PopupListener;
import bioroid.model.race.Race;
import bioroid.utils.CharacterUtils;

public class RaceSelectionListener extends PopupListener {

    private Race race;

    public RaceSelectionListener(String popupText, Race race) {
        super(popupText);
        this.race = race;
    }

    @Override
    public void mousePressed(Entity e, int mouseX, int mouseY) {
        CharacterGenerationHolder.activeCharacter.setRace(race.getCode());
        CharacterGenerationHolder.activeCharacter.setSubrace(null);
        CharacterGenerationHolder.activeCharacter.setCareer(null);
        CharacterUtils.resetAttributes(CharacterGenerationHolder.activeCharacter);
        EntityManager.getRaceSelectionPanel().reset();
    }

}
