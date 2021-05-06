package kaptainwutax.featureutils.loot;

import kaptainwutax.featureutils.loot.entry.LootEntry;
import kaptainwutax.featureutils.loot.function.LootFunction;
import kaptainwutax.featureutils.loot.item.ItemStack;
import kaptainwutax.featureutils.loot.roll.LootRoll;
import kaptainwutax.featureutils.loot.roll.UniformRoll;
import kaptainwutax.mcutils.version.MCVersion;
import kaptainwutax.noiseutils.utils.MathHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class LootPool extends LootGenerator {

	public final LootRoll rolls;
	public final LootEntry[] lootEntries;
	public final LootRoll bonusRolls = new UniformRoll(0.0F, 0.0F);

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

		int rolls = this.rolls.getCount(context) + MathHelper.floor(this.bonusRolls.getFloat(context) * context.getLuck());

		for (int i = 0; i < rolls; i++) {
			this.generatePool(context, stackConsumer);
		}
	}

	private void generatePool(LootContext context, Consumer<ItemStack> stackConsumer) {
		if (context.getVersion().isNewerOrEqualTo(MCVersion.v1_14)) {
			// unchecked for 1.14 and 1.15 (1.16 seems right)
			generatePool14(context, stackConsumer);
		} else {
			generatePool13(context, stackConsumer);
		}
	}

	private void generatePool13(LootContext context, Consumer<ItemStack> stackConsumer) {
		int totalWeight = 0;
		List<LootEntry> entries = new ArrayList<>();
		for (LootEntry lootEntry : this.lootEntries) {
			// we assume no conditions here
			int weight = lootEntry.getEffectiveWeight(context);
			if (weight > 0) {
				entries.add(lootEntry);
				totalWeight += weight;
			}
		}

		if (totalWeight != 0 && !entries.isEmpty()) {
			int count = context.nextInt(totalWeight);
			for (LootEntry entry : entries) {
				count -= entry.getEffectiveWeight(context);
				if (count < 0) {
					entry.generate(context, stackConsumer);
					return;
				}
			}
		}
	}

	private void generatePool14(LootContext context, Consumer<ItemStack> stackConsumer) {
		int totalWeight = 0;
		for (LootEntry lootEntry : this.lootEntries) {
			totalWeight += lootEntry.getEffectiveWeight(context);
		}

		if (this.lootEntries.length == 1) {
			this.lootEntries[0].generate(context, stackConsumer);
			return;
		}

		int i = context.nextInt(totalWeight);
		LootEntry pickedEntry = null;

		for (LootEntry lootEntry : this.lootEntries) {
			pickedEntry = lootEntry;
			i -= lootEntry.getWeight(context);
			if (i < 0) break;
		}

		if (pickedEntry != null) {
			pickedEntry.generate(context, stackConsumer);
		}
	}

}
