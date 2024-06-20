import java.util.ArrayList;
import java.util.List;

public class Node extends Identifiable {
    private final List<Key> KEYS = new ArrayList<>();
    private Node successor;
    private Node predecessor;
    private String ipAddress;
    private final int m = 32;
    private int next = 0;
    // "Each node n maintains a routing table, called the finger table.", p4
    private ArrayList<Node> fingerTable = new ArrayList<>();

    public Node(String ipAddress) {
        //"A node’s identifier is chosen by hashing the node’s IP address", p3
        super(ipAddress);
        this.ipAddress = ipAddress;
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
     *
     * @param identifier The identifier to find the successor of.
     * @return The node found as successor of the identifier.
     */
    public Node findSuccessorSimple(Integer identifier) {
        if (this.identifier.compareTo(identifier) >= 0) {
            return successor;
        } else {
            return successor.findSuccessorSimple(identifier);
        }
    }

    /**
     * Finds the successor if the given identifier by recursively hopping through nodes' successors.
     *
     * @param identifier The identifier to find the successor of.
     * @return The node found as successor of the identifier.
     */
    public Node findSuccessorWithFingerTable(Integer identifier) {
        if (this.identifier.compareTo(identifier) >= 0) {
            return successor;
        } else {
            return successor.findSuccessorWithFingerTable(identifier);
        }
    }

    private void fillFingerTable() {

    }

    /**
     * Ask this node to find the successor of id. From pseudocode on page 5:<br> // ask node n to find the successor of
     * id<br> n.find_successor(id)<br> if (id ∈ (n,successor])<br> return successor;<br> else<br> n' =
     * closest_preceding_node(id);<br> return n'.find_successor(id);
     *
     * @param id The id to find the successor of.
     * @return The successor of the given id.
     */
    public Node findSuccessor(Identifiable id) {
        if (id.between(BoundIncl.EXCLUSIVE, this, successor, BoundIncl.INCLUSIVE)) {
            return successor;
        } else {
            Node n2 = findClosestPrecedingNode(id);
            return n2.findSuccessor(id);
        }
    }

    /**
     * Finds the successor with and int id instead of Identifiable id.
     *
     * @param id the id to find the successor of.
     * @return The successor of the given id.
     */
    public Node findSuccessor(int id) {
        Node dummyNode = new Node("0.0.0.0");
        dummyNode.setUnhashedIdentifier(id);
        return findSuccessor(dummyNode);
    }

    /**
     * From pseudocode on page 5:<br> // search the local table for the highest predecessor of id<br> n.closest
     * preceding node(id)<br> for i = m downto 1<br> if (finger[i] ∈ (n,id))<br> return finger[i];<br> return n;
     *
     * @param id The id to find the closest preceding node of.
     * @return The closest preceding node of the given id.
     */
    private Node findClosestPrecedingNode(Identifiable id) {
        for (Node fingerNode : fingerTable.reversed())
            if (fingerNode.between(BoundIncl.EXCLUSIVE, this, id, BoundIncl.EXCLUSIVE))
                return fingerNode;
        return this;
    }

    /**
     * From pseudocode on page 6:<br>// create a new Chord ring.<br> n.create()<br> predecessor = nil;<br> successor =
     * n;<br>
     */
    public void create() {
        predecessor = null;
        successor = this;
    }

    /**
     * From pseudocode on page 6:<br> // join a Chord ring containing node n'.<br> n.join(n')<br> predecessor = nil;<br>
     * successor = n'.find successor(n);<br>
     *
     * @param otherNode A node of the ring to be joined.
     */
    public void join(Node otherNode) {
        predecessor = null;
        successor = otherNode.findSuccessor(this);
    }

    /**
     * From pseudocode on page 6:<br> // called periodically. verifies n’s immediate<br> // successor, and tells the
     * successor about n.<br> n.stabilize()<br> x = successor.predecessor;<br> if (x ∈ (n,successor))<br> successor =
     * x;<br> successor.notify(n);<br>
     */
    public void stabilize() {
        Node x = successor.predecessor;
        if (x.between(BoundIncl.EXCLUSIVE, this, successor, BoundIncl.EXCLUSIVE))
            successor = x;
        successor.notify(this);
    }

    /**
     * From pseudocode on page 6:<br> // n' thinks it might be our predecessor.<br> n.notify(n')<br> if (predecessor is
     * nil or n' ∈ (predecessor, n))<br> predecessor = n';<br>
     *
     * @param predCandidate The node that thinks it might be the predecessor.
     */
    public void notify(Node predCandidate) {
        if (predecessor == null || predCandidate.between(BoundIncl.EXCLUSIVE, predecessor, this, BoundIncl.EXCLUSIVE))
            predecessor = predCandidate;
    }

    /**
     * From pseudocode on page 6:<br> // called periodically. refreshes finger table entries.<br> // next stores the
     * index of the next finger to fix.<br> n.fix fingers()<br> next = next + 1 ;<br> if (next > m)<br> next = 1 ;<br>
     * finger[next] = find successor(n + 2^(next−1));
     */
    public void fixFingers() {
        next++;
        if (next > m)
            next = 0;
        fingerTable.set(next, findSuccessor(identifier + 2 ^ (next - 1)));
    }

    /**
     * From pseudocode on page 6:<br> // called periodically. checks whether predecessor has failed.<br> n.check
     * predecessor()<br> if (predecessor has failed)<br> predecessor = nil;<br>
     */
    public void checkPredecessor() {
        if (hasFailed(predecessor))
            predecessor = null;
    }

    /**
     * Checks if the node has failed. Not a real method as no pseudocode was given in the study.
     * @param node The node to check the status of.
     * @return Whether the given node has failed.
     */
    private boolean hasFailed(Node node) {
        return false;
    }
}
