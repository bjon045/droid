package bioroid.engine.entity.ui.raceselection;

import bioroid.GameHolder.CharacterGenerationHolder;
import bioroid.engine.entity.Entity;
import bioroid.engine.entity.EntityManager;
import bioroid.engine.entity.listener.PopupListener;
import bioroid.model.career.Career;

public class CareerSelectionListener extends PopupListener {

    private Career career;

    public CareerSelectionListener(String popupText, Career career) {
        super(popupText);
        this.career = career;
    }

    @Override
    public void mousePressed(Entity e, int mouseX, int mouseY) {
        if (career.canTakeCareer(CharacterGenerationHolder.activeCharacter)) {
            CharacterGenerationHolder.activeCharacter.setCareer(career.getCode());
            EntityManager.getRaceSelectionPanel().reset();
        }
    }

}
