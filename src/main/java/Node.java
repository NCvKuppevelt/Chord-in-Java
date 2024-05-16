public class Node extends Identifiable{
    private Key key;

    public Node(String ipAddress){
        //"A node’s identifier is chosen by hashing the node’s IP address", p3
        super(ipAddress);
    }

    public void setKey (Key key) {
        this.key = key;
    }
}
