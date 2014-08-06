package bioroid.model.event;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import bioroid.model.ModelObject;

public class Event extends ModelObject {

    private List<TriggerPoint> triggerPoints = new ArrayList<TriggerPoint>();

    private EventType eventType;

    private boolean repeating;

    // TODO: must have prereqiuisites i.e. prior event or state. State of events will be stored
    // with game state information

    @XmlElement(name = "triggerPoint")
    public List<TriggerPoint> getTriggerPoints() {
        return triggerPoints;
    }

    public void setTriggerPoints(List<TriggerPoint> triggerPoints) {
        this.triggerPoints = triggerPoints;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public boolean isRepeating() {
        return repeating;
    }

    public void setRepeating(boolean repeating) {
        this.repeating = repeating;
    }

}
