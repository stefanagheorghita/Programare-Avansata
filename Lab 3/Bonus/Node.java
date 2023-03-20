import java.util.List;

public interface Node {
    String getName();

    int getNoRelationships();

    List<Node> getAdj();
}

