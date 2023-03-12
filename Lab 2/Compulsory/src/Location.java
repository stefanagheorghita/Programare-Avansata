import java.util.Objects;

enum LocationType {CITY, GAS_STATION, AIRPORT}

public class Location {

    public Location(String name, LocationType type, int x, int y) {
        this.name = name;
        this.type = type;
        this.x = x;
        this.y = y;
    }

    public Location() {
    }

    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    LocationType type;

    public void setType(LocationType type) {
        this.type = type;
    }


    public LocationType getType() {
        return type;
    }


    int x, y;

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

    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", x=" + x +
                ", y=" + y +
                '}';
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return x == location.x && y == location.y && name.equals(location.name) && type == location.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, x, y);
    }
}
