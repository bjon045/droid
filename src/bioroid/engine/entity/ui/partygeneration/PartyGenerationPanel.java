package bioroid.engine.entity.ui.partygeneration;

import java.util.ArrayList;
import java.util.List;

import bioroid.Constants;
import bioroid.GameHolder;
import bioroid.engine.entity.Entity;
import bioroid.engine.entity.StringEntity;
import bioroid.model.character.GameCharacter;

public class PartyGenerationPanel extends Entity {

    public static final int LEFT_OFFSET = 20;

    public static final int PARTY_PANEL_GAP = 50;

    private List<Entity> entities = new ArrayList<Entity>();

    public PartyGenerationPanel(int x, int y, int width, int height) {
        super(x, y, width, height, 0);
    }

    /**
     * Call this method when underlying party structure is changed
     */
    public void reset() {
        // refresh list of entities
        entities.clear();
        List<GameCharacter> pcs = GameHolder.currentGame.getPlayerCharacters();

        int counter = 1;
        for (GameCharacter pc : pcs) {
            PartySlotPanel psp = new PartySlotPanel(getX() + LEFT_OFFSET, getY() + (counter * PARTY_PANEL_GAP), 400,
                    20, pc);
            entities.add(psp);
            counter++;
        }

        if (counter < Constants.PARTY_SIZE) {
            // add create character button
            StringEntity createCharacterButton = new StringEntity(getX() + LEFT_OFFSET, getY()
                    + (counter * PARTY_PANEL_GAP), 25, "Create Character");
            createCharacterButton.setDrawBorder(true);
            createCharacterButton.setListener(new CreateCharacterButtonListener());
            entities.add(createCharacterButton);
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
