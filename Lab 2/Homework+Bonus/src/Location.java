import java.util.Objects;

/**
 * clasa ce reprezinta locatiile
 */
abstract public class Location {
    /**
     * constructor cu parametri
     *
     * @param name - nume
     * @param type - tip
     * @param x    - coordonata x
     * @param y    - coordonata y
     */
    public Location(String name, String type, int x, int y) {
        this.name = name;
        this.type = type;
        this.x = x;
        this.y = y;
    }

    public Location() {
    }

    protected String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    protected String type;

    public abstract void setType();


    public abstract String getType();


    public int x, y;

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
        return "Location{" + "name='" + name + '\'' + ", type=" + type + ", x=" + x + ", y=" + y + '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return x == location.x && y == location.y && Objects.equals(name, location.name) && Objects.equals(type, location.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, x, y);
    }
}
