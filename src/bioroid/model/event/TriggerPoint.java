////////////////////////////////////////////////////////////////////////////////
//
// Copyright (c) 2014, Suncorp Metway Limited. All rights reserved.
//
// This is unpublished proprietary source code of Suncorp Metway Limited.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
////////////////////////////////////////////////////////////////////////////////
// $Id$
// $Revision$
// $Date$
// $Author$
////////////////////////////////////////////////////////////////////////////////
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
