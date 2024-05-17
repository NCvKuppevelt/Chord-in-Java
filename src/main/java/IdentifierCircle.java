import java.util.ArrayList;
import java.util.List;

public class IdentifierCircle {
    private final List<Node> circle = new ArrayList<>();

    public void addNode(Node node) {
        var index = 0;
        for (int i = 0; i < circle.size(); i++) {
            if (circle.get(i).compareTo(node) < 0) {
                index = i;
            }
        }
        circle.add(index, node);
    }

    /*
    Key k is assigned to the first node whose identifier is equal to or follows (the identifier of) k in the identifier
    space.
     */
    public void addKey(Key k) {
        var successor = getNodeForKey(k);
        successor.addKey(k);
    }

    /**
     * Finds which nodes a Key should be stored at by comparing its identifier to the nodes'.
     * @param k The Key that a node should be found for.
     * @return The node that the Key should be stored at.
     */
    private Node getNodeForKey(Key k) {
        for (Node node : circle) {
            if (node.compareTo(k) >= 0) {
                return node;
            }
        }
        return circle.getFirst();
    }
}
