package kaptainwutax.featureutils.structure.device;

@FunctionalInterface
public interface CoordChecker {
    
    boolean test(int x, int z, long mask, ParentInfo parent);

    @FunctionalInterface
    interface Head extends CoordChecker {
        boolean test(int x, int z, long mask);

        @Override
        default boolean test(int x, int z, long mask, ParentInfo parent) {
            return this.test(x, z, mask);
        }
    }

}
