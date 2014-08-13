package bioroid.engine.entity;

import static bioroid.Constants.RESOURCE_FOLDER;
import bioroid.Constants;
import bioroid.GameHolder;
import bioroid.model.character.GameCharacter;
import bioroid.model.location.Location;

public class PlayerCharacterEntity extends MapBasedEntity {

    private GameCharacter person;

    public PlayerCharacterEntity(GameCharacter person) {
        super(RESOURCE_FOLDER + "/" + person.getSpriteName(), 0, 0, 1);
        this.person = person;
    }

    @Override
    public Location getLocation() {
        return person.getLocation();
    }

    @Override
    public void update(int delta) {
        // TODO: optimisation this should only be done once, ideally when active character is set.
        if (GameHolder.activeCharacter == person) {
            setBackgroundImage(Constants.ACTIVE_CHARACTER_BACKGROUND);
        } else {
            setBackground(null);
        }
    }

}
