import java.util.ArrayList;
import java.util.List;

public class Node extends Identifiable{
    private final List<Key> KEYS = new ArrayList<>();
    private Node successor;

    public Node(String ipAddress){
        //"A node’s identifier is chosen by hashing the node’s IP address", p3
        super(ipAddress);
    }

    public void addKey(Key key) {
        KEYS.add(key);
    }

    public List<Key> getKeys() {
        return KEYS;
    }

    public void setSuccessor(Node successor) {
        this.successor = successor;
    }

    /**
     * Finds the successor if the given identifier by recursively hopping through nodes' successors.
     * @param identifier The identifier to find the successor of.
     * @return The node found as successor of the identifier.
     */
    public Node findSuccessor(String identifier) {
        if (this.identifier.compareTo(identifier) >= 0) {
            return successor;
        } else {
            return successor.findSuccessor(identifier);
        }
    }
}
