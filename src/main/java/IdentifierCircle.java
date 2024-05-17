import java.util.ArrayList;
import java.util.List;

public class IdentifierCircle {
    private final List<Node> CIRCLE = new ArrayList<>();

    public void addNode(Node node) {
        var index = 0;
        Node successor = !CIRCLE.isEmpty() ? CIRCLE.getFirst() : node;
        for (int i = 0; i < CIRCLE.size(); i++) {
            if (CIRCLE.get(i).compareTo(node) < 0) {
                index = i;
                successor = CIRCLE.get((i + 1) % CIRCLE.size());
                break;
            }
        }
        CIRCLE.add(index, node);
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
        if (CIRCLE.getLast().compareTo(ident) < 0) {
            return CIRCLE.getFirst();
        }
        return CIRCLE.getFirst().findSuccessor(ident);
    }
}
