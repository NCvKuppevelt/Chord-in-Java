public class Node {
    private Key key;
    private String identifier;

    public Node(String ipAddress){
        identifier = Sha1Hasher.encrypt(ipAddress);
    }
}
