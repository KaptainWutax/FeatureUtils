package kaptainwutax.featureutils.loot.entry;

import kaptainwutax.featureutils.loot.LootContext;
import kaptainwutax.featureutils.loot.LootGenerator;
import kaptainwutax.featureutils.loot.function.LootFunction;
import kaptainwutax.noiseutils.utils.MathHelper;

import java.util.Arrays;

public abstract class LootEntry extends LootGenerator {

	public final int weight;
	public final int quality;

	public LootEntry() {
		this(1);
	}

	public LootEntry(int weight) {
		this(weight, 0);
	}

	public LootEntry(int weight, int quality) {
		this.weight = weight;
		this.quality = quality;
	}

	public int getWeight(LootContext context) {
		return this.weight;
	}

	public int getEffectiveWeight(LootContext context) {
		return Math.max(MathHelper.floor((float) this.weight + (float) this.quality * context.getLuck()), 0);
	}

	public LootEntry apply(LootFunction... lootFunctions) {
		this.apply(Arrays.asList(lootFunctions));
		return this;
	}
}
