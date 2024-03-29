package kaptainwutax.featureutils.loot;

import kaptainwutax.featureutils.loot.entry.LootEntry;
import kaptainwutax.featureutils.loot.function.LootFunction;
import kaptainwutax.featureutils.loot.item.ItemStack;
import kaptainwutax.featureutils.loot.roll.LootRoll;
import kaptainwutax.featureutils.loot.roll.UniformRoll;
import kaptainwutax.mcutils.version.MCVersion;
import kaptainwutax.noiseutils.utils.MathHelper;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class LootPool extends LootGenerator {

	public final LootRoll rolls;
	public LootEntry[] lootEntries;
	public int totalWeight;
	public LootEntry[] precomputedWeights;
	public final LootRoll bonusRolls = new UniformRoll(0.0F, 0.0F);

	public LootPool(LootRoll rolls, LootEntry... lootEntries) {
		this.rolls = rolls;
		this.lootEntries = lootEntries;
	}

	public LootPool apply(MCVersion version) {
		this.lootEntries = Arrays.stream(lootEntries).filter(lootEntry -> {
			// remove the entry if it was not yet introduced yet (so older and not equal to the introduced version)
			if(lootEntry.introducedVersion != null) {
				if(version.isOlderThan(lootEntry.introducedVersion)) {
					return false;
				}
			}
			// remove all newer version (or equal) to the deprecation
			if(lootEntry.deprecatedVersion != null) {
				return !version.isNewerOrEqualTo(lootEntry.deprecatedVersion);
			}
			return true;
		}).toArray(LootEntry[]::new);
		for (LootEntry lootEntry:this.lootEntries){
			lootEntry.apply(version);
		}
		return this;
	}

	public LootPool processWeights(int luck){
		this.totalWeight = 0;

		for (LootEntry entry : this.lootEntries) {
			this.totalWeight += entry.getEffectiveWeight(luck);
		}

		this.precomputedWeights = new LootEntry[totalWeight];

		int k = 0;
		for (LootEntry entry : this.lootEntries) {
			int weight =  entry.getEffectiveWeight(luck);
			for (int i = 0; i < weight; i++) {
				this.precomputedWeights[k + i] = entry;
			}
			k += weight;
		}
		return this;
	}

	@Override
	public void generate(LootContext context, Consumer<ItemStack> stackConsumer) {
		stackConsumer = LootFunction.stack(stackConsumer, this.combinedLootFunction, context);

		int rolls = this.rolls.getCount(context) + MathHelper.floor(this.bonusRolls.getFloat(context) * context.getLuck());

		for(int i = 0; i < rolls; i++) {
			this.generatePool(context, stackConsumer);
		}
	}

	private void generatePool(LootContext context, Consumer<ItemStack> stackConsumer) {
		if(context.getVersion().isNewerOrEqualTo(MCVersion.v1_14)) {
			// unchecked for 1.14 and 1.15 (1.16 seems right)
			generatePool14(context, stackConsumer);
		} else {
			generatePool13(context, stackConsumer);
		}
	}

	private void generatePool13(LootContext context, Consumer<ItemStack> stackConsumer) {
		this.precomputedWeights[context.nextInt(totalWeight)].generate(context, stackConsumer);
	}

	private void generatePool14(LootContext context, Consumer<ItemStack> stackConsumer) {
		if (this.lootEntries.length == 1) {
			this.lootEntries[0].generate(context, stackConsumer);
		} else {
			this.precomputedWeights[context.nextInt(this.totalWeight)].generate(context, stackConsumer);
		}
	}

}
