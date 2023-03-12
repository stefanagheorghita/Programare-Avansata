import java.util.Arrays;
import java.util.List;

public class Solution {
    private List<Location> route;

    public void setRoute(List<Location> route) {
        this.route = route;
    }


    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    private double length;

    @Override
    public String toString() {
        return "Solution for " + route.get(0) + " - " + route.get(route.size() - 1) + ":\n" +
                "route=" + route + "\n" +
                "length=" + length +
                '}';
    }
}
