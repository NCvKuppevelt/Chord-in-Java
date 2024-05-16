public class Key extends Identifiable {
    private String value;

    public Key (String value) {
        // "a key identifier is produced by hashing the key", p3
        super(value);
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
