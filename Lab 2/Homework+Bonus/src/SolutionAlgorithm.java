import java.util.ArrayList;
import java.util.List;

public class SolutionAlgorithm extends Algorithm {
    public BestRouteProblem pb;

    public SolutionAlgorithm(BestRouteProblem pb) {
        this.pb = pb;
    }

    public Solution solve() {
        if (!pb.connectedLocations(pb.getStartLocation(), pb.getEndLocation()))
            return null; //verificam ca exista o ruta intre cele doua locatii
        double[][] distances = new double[pb.getLocations().length][pb.getLocations().length]; //crearea tabloului de distante
        int[][] next = new int[pb.getLocations().length][pb.getLocations().length];
        //vom initializa distantele
        for (int i = 0; i < pb.getLocations().length; i++) {
            for (int j = 0; j < pb.getLocations().length; j++) {
                if (i == j) {
                    distances[i][j] = 0;
                    next[i][j] = j;
                } else {
                    distances[i][j] = Double.MAX_VALUE;

                }
            }

        }
        //vom folosi rutele pe care le stim deja
        for (Road road : pb.getRoads()) {
            int u = pb.indexOf(road.start);
            int v = pb.indexOf(road.end);
            distances[u][v] = road.getLength();
            distances[v][u] = road.getLength();
            next[u][v] = v;
            next[v][u] = u;
        }
        //calcularea distantelor
        for (int k = 0; k < pb.getLocations().length; k++) {
            for (int i = 0; i < pb.getLocations().length; i++) {
                for (int j = 0; j < pb.getLocations().length; j++)
                    if (distances[i][k] != Integer.MAX_VALUE && distances[k][j] != Integer.MAX_VALUE &&
                            distances[i][k] + distances[k][j] < distances[i][j]) {
                        distances[i][j] = distances[i][k] + distances[k][j];
                        next[i][j] = next[i][k];
                    }
            }
        }
        //calcularea rutei
        List<Location> route = new ArrayList<>();
        int x = pb.indexOf(pb.getStartLocation());
        int y = pb.indexOf(pb.getEndLocation());
        int now = x;
        while (now != y) {
            route.add(pb.getLocations()[now]);

            now = next[now][y];
        }
        route.add(pb.getLocations()[now]);

        Solution s = new Solution();
        s.setRoute(route);
        s.setLength(distances[x][y]);
        return s;

    }

}
