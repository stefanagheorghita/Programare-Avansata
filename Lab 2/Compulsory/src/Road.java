import java.util.Objects;

enum RoadTypes {HIGHWAY, EXPRESS, COUNTRY}

public class Road {

    int speed;

    RoadTypes type;

    public Road() {
    }

    public Road(int speed, RoadTypes type, int length) {
        this.speed = speed;
        this.type = type;
        this.length = length;

    }

    int length;

    public RoadTypes getType() {
        return type;
    }

    public void setType(RoadTypes type) {
        this.type = type;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }


    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
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
        return speed == road.speed && length == road.length && type == road.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(speed, type, length);
    }
}
