package bioroid.model.event;

import bioroid.GameHolder;
import bioroid.model.location.Location;
import bioroid.utils.StringUtils;

public class TriggerPoint {

    private String mapCode;

    // TODO: allow range (box, or points)
    private Location location;

    public String getMapCode() {
        return mapCode;
    }

    public void setMapCode(String mapCode) {
        this.mapCode = mapCode;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public boolean isTriggered(Location location) {
        return StringUtils.equals(GameHolder.currentMap.getCode(), mapCode) && this.location.equals(location);
    }
}
