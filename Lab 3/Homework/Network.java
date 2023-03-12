import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Network {
    private List<Node> nodes = new ArrayList<>();
    public Network() {
    }

    public Network(List<Node> nodes) {
        this.nodes = nodes;
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

}

