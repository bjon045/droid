package bioroid.control;

import java.util.List;

import org.newdawn.slick.GameContainer;

import bioroid.GameHolder;
import bioroid.model.ModelResources;
import bioroid.model.character.GameCharacter;
import bioroid.model.event.Event;
import bioroid.model.event.TriggerPoint;
import bioroid.model.location.GameMap;
import bioroid.utils.CharacterUtils;

/**
 * Event Controller should determine if any events have been triggered and update the appropriate entities/perform
 * actions and create UI components i.e. dialogs etc.
 */
public class EventController {

    public void update(GameContainer container) {

        GameCharacter mainCharacter = CharacterUtils.getMainCharacter();
        List<Event> events = ModelResources.events.getEvents();

        GameMap gameMap = GameHolder.currentMap;
        // TODO optimisation: events should be preloaded onto the map

        for (Event event : events) {
            for (TriggerPoint tp : event.getTriggerPoints()) {
                if (tp.isTriggered(mainCharacter.getLocation())) {
                    System.out.println("Event Triggered: " + event.getCode());
                }
            }
        }
    }

}
