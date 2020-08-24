package kaptainwutax.featureutils.structure.device.node;

import kaptainwutax.featureutils.Feature;
import kaptainwutax.featureutils.structure.Structure;
import kaptainwutax.featureutils.structure.device.CoordChecker;
import kaptainwutax.featureutils.structure.device.ParentInfo;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public abstract class Node<C extends Feature.Config> {

    protected Node<?> parent;
    protected Node<?> child;

    protected final C config;
    protected final int regionX;
    protected final int regionZ;
    protected CoordChecker checker;

    public Node(Structure<? extends C, ?> structure, int regionX, int regionZ, CoordChecker checker) {
        this(structure.getConfig(), regionX, regionZ, checker);
    }

    public Node(C config, int regionX, int regionZ, CoordChecker checker) {
        this.config = config;
        this.regionX = regionX;
        this.regionZ = regionZ;
        this.checker = checker;
    }

    public C getConfig() {
        return this.config;
    }

    public int getRegionX() {
        return this.regionX;
    }

    public int getRegionZ() {
        return this.regionZ;
    }

    public CoordChecker getChecker() {
        return this.checker;
    }

    public <C2 extends Feature.Config> Node<C2> add(Node<C2> child) {
        this.child = child;
        this.child.parent = this;
        return child;
    }

    public Set<Integer> getLiftingPoints() {
        return new HashSet<>(Collections.singleton(48));
    }

    public boolean test(long structureSeed, int bits, ParentInfo parent) {
        return this.child == null || this.child.test(structureSeed, bits, parent);
    }

    @Override
    public String toString() {
        return this.config.getClass().getCanonicalName();
    }

    public static abstract class Analyser<N extends Node<?>> {

        protected final N node;

        public Analyser(N node) {
            this.node = node;
        }

    }

}
