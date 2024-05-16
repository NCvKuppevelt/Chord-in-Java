public class Key {
    private String value;
    private String identifier;

    public Key (String value) {
        this.value = value;
        // "a key identifier is produced by hashing the key", p3
        identifier = Sha1Hasher.hash(value);
    }
}
