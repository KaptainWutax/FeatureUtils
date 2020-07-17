package kaptainwutax.featureutils.loot.entry;

import kaptainwutax.featureutils.loot.LootContext;
import kaptainwutax.featureutils.loot.item.ItemStack;

import java.util.function.Consumer;

public class EmptyEntry extends LootEntry {

	public EmptyEntry(int weight) {
		super(weight);
	}

	@Override
	public void generate(LootContext context, Consumer<ItemStack> stackConsumer) {

	}

}
