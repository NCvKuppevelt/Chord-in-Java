public class Node {
    private Key key;
    private String identifier;

    public Node(String ipAddress){
        //"A node’s identifier is chosen by hashing the node’s IP address", p3
        identifier = Sha1Hasher.hash(ipAddress);
    }
}
