package kaptainwutax.featureutils.loot.roll;

import kaptainwutax.featureutils.loot.LootContext;

public class UniformRoll extends LootRoll {

	public final int min;
	public final int max;

	public UniformRoll(float value) {
		this(value, value);
	}

	public UniformRoll(float min, float max) {
		this.min = min < (double) (int) min ? (int) min - 1 : (int) min;
		this.max = max < (double) (int) max ? (int) max - 1 : (int) max;
	}

	@Override
	public int getCount(LootContext context) {
		return this.min >= this.max ? this.min : context.nextInt(this.max - this.min + 1) + this.min;
	}

}
