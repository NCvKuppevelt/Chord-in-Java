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
        var node = getNodeForKey(k);
        node.setKey(k);
    }

    private Node getNodeForKey(Key k) {
        for (Node node : circle) {
            if (node.compareTo(k) >= 0) {
                return node;
            }
        }
        return circle.getFirst();
    }
}
