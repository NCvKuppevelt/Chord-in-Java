public abstract class Identifiable implements Comparable<Identifiable> {
    protected Comparable<String> identifier;

    protected Identifiable(String preHashIdentifier) {
        identifier = Sha1Hasher.hash(preHashIdentifier);
    }

    @Override
    public int compareTo(Identifiable identifiable) {
        return identifier.compareTo(identifiable.getIdentifier());
    }

    public int compareTo(String ident) {
        return identifier.compareTo(ident);
    }

    public String getIdentifier() {
        return identifier.toString();
    }
}
