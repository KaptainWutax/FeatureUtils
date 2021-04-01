package kaptainwutax.featureutils.loot.entry;

import kaptainwutax.featureutils.loot.LootContext;
import kaptainwutax.featureutils.loot.function.LootFunction;
import kaptainwutax.featureutils.loot.item.Item;
import kaptainwutax.featureutils.loot.item.ItemStack;

import java.util.function.Consumer;

public class ItemEntry extends LootEntry {

    public final Item item;

    public ItemEntry(Item item) {
        this(item, 1);
    }

    public ItemEntry(Item item, int weight) {
        super(weight);
        this.item = item;
    }

    public void generate(LootContext context, Consumer<ItemStack> stackConsumer) {
        stackConsumer = LootFunction.stack(stackConsumer, this.combinedLootFunction, context);
        stackConsumer.accept(new ItemStack(new Item(this.item.getName())));
    }
}
