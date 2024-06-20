public abstract class Identifiable implements Comparable<Identifiable> {
    protected Integer identifier;

    protected Identifiable(String preHashIdentifier) {
        identifier = Sha1Hasher.hash(preHashIdentifier);
    }

    public void setUnhashedIdentifier(Integer unhashedIdentifier) {
        identifier = unhashedIdentifier;
    }

    @Override
    public int compareTo(Identifiable identifiable) {
        return identifier.compareTo(identifiable.getIdentifier());
    }

    public int compareTo(Integer ident) {
        return identifier.compareTo(ident);
    }

    public Integer getIdentifier() {
        return identifier;
    }

    /**
     * Check if this Identifiable lays between the range specified by lower and upper.
     *
     * @param lowerIncl Whether the lower bound is inclusive or exclusive.
     * @param lower     The lower bound of the range.
     * @param upper     The upper bound of the range.
     * @param upperIncl Whether the upper bound is inclusive or exclusive.
     * @return Whether this Identifiable lays between upper and lower bounds.
     */
    public boolean between(BoundIncl lowerIncl, Integer lower, Integer upper, BoundIncl upperIncl) {
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

    /**
     * Check if this Identifiable lays between the range specified by lower and upper.
     *
     * @param lowerIncl Whether the lower bound is inclusive or exclusive.
     * @param lower     The lower bound of the range.
     * @param upper     The upper bound of the range.
     * @param upperIncl Whether the upper bound is inclusive or exclusive.
     * @return Whether this Identifiable lays between upper and lower bounds.
     */
    public boolean between(BoundIncl lowerIncl, Identifiable lower, Identifiable upper, BoundIncl upperIncl) {
        return between(lowerIncl, lower.getIdentifier(), upper.getIdentifier(), upperIncl);
    }
}
