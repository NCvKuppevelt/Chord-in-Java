import java.util.ArrayList;
import java.util.List;

public class IdentifierCircle {
    private final List<Node> circle = new ArrayList<>();

    public void addNode(Node node) {
        var index = 0;
        Node successor = circle.getFirst();
        for (int i = 0; i < circle.size(); i++) {
            if (circle.get(i).compareTo(node) < 0) {
                index = i;
                successor = circle.get((i + 1) % circle.size());
                break;
            }
        }
        circle.add(index, node);
        node.setSuccessor(successor);
    }

    /*
    Key k is assigned to the first node whose identifier is equal to or follows (the identifier of) k in the identifier
    space.
     */
    public void addKey(Key k) {
        Node successor = findSuccessor(k.getIdentifier());
        successor.addKey(k);
    }

    /**
     * Finds the successor of an identifier bij recursively hopping over the nodes in the circle.
     *
     * @param ident the identifier to find the successor of.
     * @return The found successor.
     */
    private Node findSuccessor(String ident) {
        // Catches the edge case where ident is bigger than the last node in the circle, causing infinite recursion.
        if (circle.getLast().compareTo(ident) < 0) {
            return circle.getFirst();
        }
        return circle.getFirst().findSuccessor(ident);
    }
}
