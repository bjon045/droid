package bioroid.control;

import java.util.List;

import bioroid.GameHolder;
import bioroid.engine.entity.ui.dialog.DialogGenerator;
import bioroid.model.ModelResources;
import bioroid.model.character.GameCharacter;
import bioroid.model.event.Event;
import bioroid.model.event.EventType;
import bioroid.model.event.TriggerPoint;
import bioroid.model.location.GameMap;
import bioroid.utils.CharacterUtils;

/**
 * Event Controller should determine if any events have been triggered and update the appropriate entities/perform
 * actions and create UI components i.e. dialogs etc.
 */
public class EventController {

    public void update() {

        GameCharacter mainCharacter = CharacterUtils.getMainCharacter();
        List<Event> events = ModelResources.events.getEvents();

        GameMap gameMap = GameHolder.currentMap;
        // TODO optimisation: events should be preloaded onto the map

        for (Event event : events) {
            if (!event.isRepeating()) {
                // if an event is not repeating it needs to be checked if it has already triggered
                // TODO requirement: check game state and get event status/progression
                continue;
            }
            for (TriggerPoint tp : event.getTriggerPoints()) {

                if (tp.isTriggered(mainCharacter.getLocation())) {
                    handleEvent(event);
                }
            }
        }
    }

    private void handleEvent(Event event) {
        // TODO: requirement: implement event actions
        System.out.println("Event Triggered: " + event.getCode());
        if (event.getEventType() == EventType.MESSAGE) {
            DialogGenerator.createDialog(event.getDescription());
        }
    }

}
