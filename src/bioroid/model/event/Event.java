package bioroid.model.event;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import bioroid.model.ModelObject;

public class Event extends ModelObject {

    private List<TriggerPoint> triggerPoints = new ArrayList<TriggerPoint>();

    private EventType eventType;

    private Boolean repeating;

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

    public Boolean getRepeating() {
        return repeating;
    }

    public void setRepeating(Boolean repeating) {
        this.repeating = repeating;
    }

}
