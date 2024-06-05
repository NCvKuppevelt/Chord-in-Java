public abstract class Identifiable implements Comparable<Identifiable> {
    protected Comparable<String> identifier;

    protected Identifiable(String preHashIdentifier) {
        identifier = Sha1Hasher.hash(preHashIdentifier);
    }

    public void setUnhashedIdentifier(String unhashedIdentifier) {
        identifier = unhashedIdentifier;
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

    /**
     * Check if this Identifiable lays between the range specified by lower and upper
     *
     * @param lowerIncl Whether the lower bound is inclusive or exclusive.
     * @param lower     The lower bound of the range.
     * @param upper     The upper bound of the range.
     * @param upperIncl Whether the upper bound is inclusive or exclusive.
     * @return Whether this Identifiable lays between upper and lower bounds.
     */
    public boolean between(BoundIncl lowerIncl, Identifiable lower, Identifiable upper, BoundIncl upperIncl) {
        boolean leftBool;
        switch (lowerIncl) {
            case BoundIncl.INCLUSIVE -> leftBool = compareTo(lower) >= 0;
            case BoundIncl.EXCLUSIVE -> leftBool = compareTo(lower) > 0;
            default -> {
                return false;
            }
        }

        if (leftBool) {
            boolean rightBool;
            switch (upperIncl) {
                case BoundIncl.INCLUSIVE -> rightBool = compareTo(upper) <= 0;
                case BoundIncl.EXCLUSIVE -> rightBool = compareTo(upper) < 0;
                default -> {
                    return false;
                }
            }
            return rightBool;
        }
        return false;
    }
}
