import java.util.ArrayList;
import java.util.List;

public class Node extends Identifiable{
    private List<Key> keys = new ArrayList<>();
    private Node successor;

    public Node(String ipAddress){
        //"A node’s identifier is chosen by hashing the node’s IP address", p3
        super(ipAddress);
    }

    public void addKey(Key key) {
        keys.add(key);
    }

    public List<Key> getKeys() {
        return keys;
    }

    public void setSuccessor(Node successor) {
        this.successor = successor;
    }
}
