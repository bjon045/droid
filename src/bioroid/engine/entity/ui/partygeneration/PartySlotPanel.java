package bioroid.engine.entity.ui.partygeneration;

import java.util.ArrayList;
import java.util.List;

import bioroid.engine.entity.Entity;
import bioroid.engine.entity.StringEntity;
import bioroid.model.character.GameCharacter;

public class PartySlotPanel extends Entity {

    private List<Entity> entities = new ArrayList<Entity>();

    public PartySlotPanel(int x, int y, int width, int height, GameCharacter pc) {
        super(x, y, width, height, 0);

        StringEntity name = new StringEntity(x, y, 300, 20, pc.getName());
        entities.add(name);
    }

    @Override
    public void update(int delta) {

    }

    @Override
    public List<Entity> getChildren() {
        return entities;
    }

}
