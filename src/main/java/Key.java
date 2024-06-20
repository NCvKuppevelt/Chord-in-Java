public class Key extends Identifiable {
    private String value;
    private String name;

    public Key (String value, String name) {
        // "a key identifier is produced by hashing the key", p3
        super(value);
        this.value = value;
        this.name = name;
    }

    public String getValue() {
        return value;
    }
}
