public abstract class Identifiable {
    protected Comparable<String> identifier;

    protected Identifiable(String preHashIdentifier) {
        identifier = Sha1Hasher.hash(preHashIdentifier);
    }
}
