import java.util.*;

public class Network {
    private List<Node> nodes = new ArrayList<>();
    private boolean[] visited;
    private int[] tin, low;
    private int timer;

    private Map<Integer, List<Node>> components;
    private List<Node> cutVertices;

    public Network() {
    }

    public Network(List<Node> nodes) {
        this.nodes = nodes;
        //verificare nod unic
        if (!verifyUnique()) {
            System.out.println("There are two different nodes with the same name in the network.");
            System.exit(2);
        }


    }

    private boolean verifyUnique() {
        for (int i = 0; i < nodes.size() - 1; i++)
            for (int j = i + 1; j < nodes.size(); j++)
                if (nodes.get(i).getName().compareTo(nodes.get(j).getName()) == 0)
                    return false;
        return true;

    }

    public void addNode(Node node) {
        for (int i = 0; i < nodes.size(); i++)
            if (node.getName().compareTo(nodes.get(i).getName()) == 0) {
                System.out.println("The node " + node.getName() + " wasn't added to the network. There already exists a node with this name.");
            } else
                nodes.add(node);

    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    @Override
    public String toString() {
        return "Network{" +
                "nodes=" + nodes +
                '}';
    }

    public int importance(Node n) {
        return n.getNoRelationships();
    }

    private void dfs(Node n, int p, int componentNumber) {
        int v = nodes.indexOf(n);
        visited[v] = true;
        tin[v] = low[v] = timer++;
        int children = 0;
        for (Node td : n.getAdj()) {
            int to = nodes.indexOf(td);
            if (to == p) continue;
            if (visited[to]) {
                low[v] = Math.min(low[v], tin[to]);
            } else {
                dfs(td, v, componentNumber);
                low[v] = Math.min(low[v], low[to]);
                if (low[to] >= tin[v] && p != -1)
                    cutVertices.add(nodes.get(v));
                ++children;
            }
            components.putIfAbsent(componentNumber, new ArrayList<>());
            components.get(componentNumber).add(n);
            if (to != v) {
                components.get(componentNumber).add(td);
            }
            while (components.get(componentNumber).size() > 2 && components.get(componentNumber).get(1) != n) {
                components.get(componentNumber).remove(0);
            }
            componentNumber++;
        }
        if (p == -1 && children > 1)
            cutVertices.add(nodes.get(v));

    }


    public List<Node> find_cutpoints() {
        timer = 0;
        visited = new boolean[nodes.size()];
        tin = new int[nodes.size()];
        low = new int[nodes.size()];

        Arrays.fill(visited, false);
        Arrays.fill(tin, -1);
        Arrays.fill(low, -1);
        cutVertices = new ArrayList<>();
        components = new HashMap<>();
        for (int i = 0; i < nodes.size(); ++i) {
            if (!visited[i])
                dfs(nodes.get(i), -1, 0);
        }
        return cutVertices;
    }


    public Map<Integer, List<Node>> findMaximally2ConnectedComponents() {
        timer = 0;
        visited = new boolean[nodes.size()];
        tin = new int[nodes.size()];
        low = new int[nodes.size()];

        Arrays.fill(visited, false);
        Arrays.fill(tin, -1);
        Arrays.fill(low, -1);
        cutVertices = new ArrayList<>();
        components = new HashMap<>();
        for (int i = 0; i < nodes.size(); i++) {
            if (!visited[i]) {
                dfs(nodes.get(i), -1, 0);
            }
        }
        return components;
    }


}


