package bioroid.model.location;

public class Location {

    private int x;

    private int y;

    // required for JAXB
    public Location() {

    }

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void incrementX() {
        x++;
    }

    public void decrementX() {
        x--;
    }

    public void incrementY() {
        y++;
    }

    public void derementY() {
        y--;
    }

    public void updateWith(Location location) {
        x = location.x;
        y = location.y;
    }

    public Location copy() {
        Location newLocation = new Location();
        newLocation.setX(x);
        newLocation.setY(y);
        return newLocation;
    }

    public boolean isNextTo(Location location) {
        return (Math.abs(this.x - location.x) <= 1 && Math.abs(this.y - location.y) <= 1);
    }

    @Override
    public boolean equals(Object o) {
        if ((o == null) || !(o instanceof Location)) {
            return false;
        }
        Location l = (Location) o;
        if ((l.x == x) && (l.y == y)) {
            return true;
        }
        return false;
    }

    public boolean equals(int x, int y) {
        if ((this.x == x) && (this.y == y)) {
            return true;
        }
        return false;
    }

}
