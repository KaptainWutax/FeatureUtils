package kaptainwutax.featureutils.loot;

import kaptainwutax.featureutils.loot.function.LootFunction;
import kaptainwutax.featureutils.loot.item.ItemStack;

import java.util.Collection;
import java.util.function.Consumer;

public abstract class LootGenerator {

    public LootFunction[] lootFunctions;
    public LootFunction combinedLootFunction;

    public LootGenerator() {
        this.apply(null);
    }

    public LootGenerator apply(Collection<LootFunction> lootFunctions) {
        if (lootFunctions != null) {
            this.lootFunctions = lootFunctions.toArray(new LootFunction[0]);
            this.combinedLootFunction = LootFunction.combine(this.lootFunctions);
        } else {
            this.lootFunctions = new LootFunction[0];
            this.combinedLootFunction = (baseStack, context) -> baseStack;
        }

        return this;
    }

    public abstract void generate(LootContext context, Consumer<ItemStack> stackConsumer);

}
