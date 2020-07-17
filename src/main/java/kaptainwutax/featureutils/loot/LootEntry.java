package kaptainwutax.featureutils.loot;

import kaptainwutax.featureutils.loot.function.LootFunction;
import kaptainwutax.featureutils.loot.item.Item;
import kaptainwutax.featureutils.loot.item.ItemStack;

import java.util.Arrays;
import java.util.function.Consumer;

public class LootEntry extends LootGenerator {

	public final Item item;
	public final int weight;

	public LootEntry(Item item) {
		this(item, 1);
	}

	public LootEntry(Item item, int weight) {
		this.item = item;
		this.weight = weight;
	}

	public int getWeight(LootContext context) {
		return this.weight;
	}

	public LootEntry apply(LootFunction... lootFunctions) {
		this.apply(Arrays.asList(lootFunctions));
		return this;
	}

	public void generate(LootContext context, Consumer<ItemStack> stackConsumer) {
		stackConsumer = LootFunction.stack(stackConsumer, this.combinedLootFunction, context);
		stackConsumer.accept(new ItemStack(this.item));
	}

}
