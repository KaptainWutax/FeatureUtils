package kaptainwutax.featureutils.loot.entry;

import kaptainwutax.featureutils.loot.LootContext;
import kaptainwutax.featureutils.loot.LootGenerator;
import kaptainwutax.featureutils.loot.function.LootFunction;

import java.util.Arrays;

public abstract class LootEntry extends LootGenerator {

    public final int weight;

    public LootEntry() {
        this(1);
    }

    public LootEntry(int weight) {
        this.weight = weight;
    }

    public int getWeight(LootContext context) {
        return this.weight;
    }

    public LootEntry apply(LootFunction... lootFunctions) {
        this.apply(Arrays.asList(lootFunctions));
        return this;
    }
}
