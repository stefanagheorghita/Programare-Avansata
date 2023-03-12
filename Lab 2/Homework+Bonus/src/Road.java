import java.util.Objects;


import static java.lang.Math.sqrt;

/**
 * clasa abstracta Road
 */
abstract public class Road {

    protected int speed;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected String name;

    protected String type;

    /**
     * constructor default
     */
    public Road() {
    }

    /**
     * constructor cu parametri
     *
     * @param name  - nume sosea
     * @param speed - viteza
     * @param type  -tip
     * @param a     - locatia de inceput
     * @param b     - locatia de final
     */
    public Road(String name, int speed, String type, Location a, Location b) {
        this.name = name;
        this.speed = speed;
        this.type = type;
        this.start = a;
        this.end = b;
        this.length = sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y));

    }

    Location start, end;
    protected double length;

    public abstract String getType();

    public abstract void setType();

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }


    public double getLength() {
        return length;
    }

    public void setLength() {
        this.length = sqrt((start.x - end.x) * (start.x - end.x) + (start.y - end.y) * (start.y - end.y));

    }

    @Override
    public String toString() {
        return "Road{" +
                "speed=" + speed +
                ", type=" + type +
                ", length=" + length +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Road road = (Road) o;
        return speed == road.speed && Double.compare(road.length, length) == 0 && Objects.equals(name, road.name) && Objects.equals(type, road.type) && Objects.equals(start, road.start) && Objects.equals(end, road.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(speed, name, type, start, end, length);
    }
}
