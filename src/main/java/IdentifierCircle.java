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
        node.setSuccessor(findSuccessor(node));
    }

    /*
    Key k is assigned to the first node whose identifier is equal to or follows (the identifier of) k in the identifier
    space.
     */
    public void addKey(Key k) {
        var successor = findSuccessor(k);
        successor.addKey(k);
    }

    /**
     * Finds the successor of an Identifiable. The successor is the first node whose identifier is equal to or follows
     * the Identifiable.
     *
     * @param ident The Identifiable that a successor should be found for.
     * @return The successor of the identifiable.
     */
    private Node findSuccessor(Identifiable ident) {
        for (Node node : circle) {
            if (node.compareTo(ident) >= 0) {
                return node;
            }
        }
        return circle.getFirst();
    }
}
