public class Key {
    private String value;
    private String identifier;

    public Key (String value) {
        this.value = value;
        identifier = Sha1Hasher.encrypt(value);
    }
}
