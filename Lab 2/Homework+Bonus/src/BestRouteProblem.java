import java.util.Arrays;
import java.util.Objects;

public class BestRouteProblem {
    public Location[] getLocations() {
        return locations;
    }

    public Road[] getRoads() {
        return roads;
    }

    /**
     * @param locations
     * @param roads
     */
    private final Location[] locations;
    private final Road[] roads;
    private final int[] visited;

    private Location startLocation, endLocation;

    public Location getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(Location startLocation) {
        this.startLocation = startLocation;
    }

    public Location getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(Location endLocation) {
        this.endLocation = endLocation;
    }

    /**
     * @param locations
     * @param roads     constructorul instantei
     */
    public BestRouteProblem(Location[] locations, Road[] roads, Location a, Location b) {
        this.locations = locations;
        this.roads = roads;
        startLocation = a;
        endLocation = b;
        visited = new int[locations.length];
        for (int i = 0; i < locations.length; i++)
            visited[i] = 0;
        if (!Verify()) {
            System.out.println("Invalid instance.");
            System.exit(0);
        }
    }

    /**
     * @return valoare de adevar a corectitudinii instantei
     */
    public boolean Verify() {
        if (!verifyUniqueLocations()) {
            System.out.println("There are two locations with the same name.");
            return false;
        }
        if (!verifyUniqueRoads()) {
            System.out.println("There are two roads with the same name.");
            return false;
        }
        if (!existsRoadStartEnd()) {
            System.out.println("The start/end point for a road doesn't exists.");
            return false;
        }
        if (!searchLocation(startLocation) || !searchLocation(endLocation)) {
            System.out.println("The start/end location doesn't exist.");
            return false;
        }
        return true;
    }

    /**
     * @return verificarea sa nu fie adaugata aceeasi locatie de 2 ori
     */
    private boolean verifyUniqueLocations() {
        for (int i = 0; i < locations.length - 1; i++)
            for (int j = i + 1; j < locations.length; j++) {
                if (Objects.equals(locations[i], locations[j]))
                    return false;

            }

        return true;
    }

    private boolean verifyUniqueRoads() {
        for (int i = 0; i < roads.length - 1; i++)
            for (int j = i + 1; j < roads.length; j++) {
                if (Objects.equals(roads[i], roads[j]))
                    return false;

            }
        return true;
    }

    private boolean existsRoadStartEnd() {
        for (int i = 0; i < roads.length; i++)
            if (!searchLocation(roads[i].start) || !searchLocation(roads[i].end))
                return false;
        return true;
    }

    /**
     * cautarea unei locatii
     */
    private boolean searchLocation(Location x) {
        for (int i = 0; i < locations.length; i++)
            if (Objects.equals(locations[i], x))
                return true;
        return false;
    }

    /**
     * verificarea daca se poate ajunge de la o locatie la alta
     */
    public boolean connectedLocations(Location a, Location b) {
        for (int i = 0; i < roads.length; i++) //luam pe rand fiecare element
            if ((roads[i].start.equals(a) && roads[i].end.equals(b)) || (roads[i].start.equals(b) && roads[i].end.equals(a))) {
                //System.out.println("You can go from " + a.getName() + " to " + b.getName() + " using the given roads.");
                return true; //daca am gasit un drum care sa le conecteze
            } else if (roads[i].start.equals(a)) { // daca exista un drum care porneste din prima localitate vom continua verificarile
                if (visited[i] == 0) {
                    visited[i] = 1;
                    if (connectedLocations(roads[i].end, b)) {
                        //System.out.println("You can go from " + a.getName() + " to" + b.getName() + " using the given roads.");
                        return true;
                    }
                }
                visited[i] = 0;
            } else if (roads[i].end.equals(a)) { //daca exista un drum care se termina in prima localitate vom continua verificarile
                if (visited[i] == 0) {
                    visited[i] = 1;
                    if (connectedLocations(roads[i].start, b)) {
                        // System.out.println("You can go from " + a.getName() + " to " + b.getName() + " using the given roads.");
                        return true;
                    }
                }
                visited[i] = 0;
            }
        //System.out.println("You can't go from " + a.getName() + " to " + b.getName() + " using the given roads.");
        return false;
    }

    @Override
    public String toString() {
        return "BestRouteProblem{" +
                "locations=" + Arrays.toString(locations) +
                ", roads=" + Arrays.toString(roads) +
                '}';
    }

    public int indexOf(Location x) {
        for (int i = 0; i < locations.length; i++)
            if (Objects.equals(x, locations[i]))
                return i;
        return -1;
    }
}
