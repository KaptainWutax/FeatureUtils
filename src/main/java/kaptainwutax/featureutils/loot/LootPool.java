package kaptainwutax.featureutils.loot;

import kaptainwutax.featureutils.loot.entry.LootEntry;
import kaptainwutax.featureutils.loot.function.LootFunction;
import kaptainwutax.featureutils.loot.item.ItemStack;
import kaptainwutax.featureutils.loot.roll.LootRoll;

import java.util.Arrays;
import java.util.function.Consumer;

public class LootPool extends LootGenerator {

	public final LootRoll rolls;
	public final LootEntry[] lootEntries;

	public LootPool(LootRoll rolls, LootEntry... lootEntries) {
		this.rolls = rolls;
		this.lootEntries = lootEntries;
	}

	public LootPool apply(LootFunction... lootFunctions) {
		this.apply(Arrays.asList(lootFunctions));
		return this;
	}

	@Override
	public void generate(LootContext context, Consumer<ItemStack> stackConsumer) {
		stackConsumer = LootFunction.stack(stackConsumer, this.combinedLootFunction, context);

		int rolls = this.rolls.nextInt(context);

		for(int i = 0; i < rolls; i++) {
			this.generatePool(context, stackConsumer);
		}
	}

	private void generatePool(LootContext context, Consumer<ItemStack> stackConsumer) {
		int totalWeight = 0;

		for(LootEntry lootEntry: this.lootEntries) {
			totalWeight += lootEntry.getWeight(context);
		}

		if(this.lootEntries.length == 1) {
			this.lootEntries[0].generate(context, stackConsumer);
			return;
		}

		int i = context.nextInt(totalWeight);
		LootEntry pickedEntry = null;

		for(LootEntry lootEntry: this.lootEntries) {
			pickedEntry = lootEntry;
			i -= lootEntry.getWeight(context);
			if(i < 0)break;
		}

		if(pickedEntry != null) {
			pickedEntry.generate(context, stackConsumer);
		}
	}

}
