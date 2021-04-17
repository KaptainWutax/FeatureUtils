package kaptainwutax.featureutils.structure.device;

import kaptainwutax.featureutils.Feature;
import kaptainwutax.featureutils.structure.device.node.Node;
import kaptainwutax.mathutils.util.Mth;
import kaptainwutax.mcutils.util.data.ThreadPool;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.LongConsumer;

public class StructureDevice {

    public final List<Node<?>> heads = new ArrayList<>();
    private final ThreadPool pool;

    public StructureDevice() {
        this(1);
    }

    public StructureDevice(int threadCount) {
        this.pool = new ThreadPool(threadCount);
    }

    public <C extends Feature.Config> Node<C> addBranch(Node<C> node) {
        this.heads.add(node);
        return node;
    }

    public void findSeeds(LongConsumer onSeedFound, int bits) {
        List<BitGroup> groups = this.groupRestrictions();
        System.out.println(groups);

        BitGroup entry = groups.get(0);

        if (entry.bits < 48) {
            search(entry, 0, 0, onSeedFound, bits);
            this.pool.awaitCompletion();
            this.pool.shutdown();
        } else {
            throw new UnsupportedOperationException();
        }
    }

    public void search(BitGroup group, long baseSeed, int bits, LongConsumer onSeedFound, int totalBits) {
        System.out.println("[" + baseSeed + "] is good for the lowest " + bits + " bits! Lifting the next " + (group.bits - bits) + " bits...");

        long searchSpace = Mth.getPow2(group.bits - bits);

        for (long i = 0; i < searchSpace; i++) {
            long seed = baseSeed | (i << bits);

            if (!group.testSeed(seed)) continue;

            if (group.next == null || group.bits >= totalBits) {
                onSeedFound.accept(seed);
            } else {
                this.pool.run(() -> {
                    this.search(group.next, seed, group.bits, onSeedFound, totalBits);
                });

                this.pool.awaitFreeThread();
            }
        }
    }

    private List<BitGroup> groupRestrictions() {
        Map<Integer, BitGroup> raw = new TreeMap<>(Integer::compare);

        for (Node<?> head : this.heads) {
            for (int bitPoint : head.getLiftingPoints()) {
                raw.computeIfAbsent(bitPoint, i -> new BitGroup(i, new ArrayList<>())).heads.add(head);
            }
        }

        List<BitGroup> result = new ArrayList<>(raw.values());

        for (int i = 0; i < result.size() - 1; i++) {
            result.get(i).next = result.get(i + 1);
        }

        return result;
    }

    public static final class BitGroup {
        public final int bits;
        public final List<Node<?>> heads;
        public BitGroup next;

        public BitGroup(int bits, List<Node<?>> heads) {
            this.bits = bits;
            this.heads = heads;
        }

        public boolean testSeed(long seed) {
            for (Node<?> head : this.heads) {
                if (head.test(seed, this.bits, null)) return true;
            }

            return false;
        }

        @Override
        public String toString() {
            return "lift " + this.bits + " bits: " + this.heads;
        }
    }

}
