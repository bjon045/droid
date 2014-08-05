package bioroid.control.action;

import bioroid.model.location.Location;

public class Action {

    private ActionType actionType;

    private Location location;

    public Action(ActionType actionType) {
        this.actionType = actionType;
    }

    public Action(ActionType actionType, Location location) {
        this.actionType = actionType;
        this.location = location;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

}
