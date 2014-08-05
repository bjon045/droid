package bioroid.engine.entity;

import static bioroid.Constants.RESOURCE_FOLDER;
import bioroid.model.character.GameCharacter;
import bioroid.model.location.Location;

public class PlayerCharacterEntity extends MapEntity {

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
	// not needed for player character
    }

}
