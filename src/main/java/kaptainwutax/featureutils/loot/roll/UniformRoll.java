package kaptainwutax.featureutils.loot.roll;

import kaptainwutax.featureutils.loot.LootContext;
import kaptainwutax.seedutils.util.math.Mth;

public class UniformRoll extends LootRoll {

	public final int min;
	public final int max;

	public UniformRoll(float value) {
		this(value, value);
	}

	public UniformRoll(float min, float max) {
		this.min = Mth.floor(min);
		this.max = Mth.floor(max);
	}

	@Override
	public int getCount(LootContext context) {
		return this.min >= this.max ? this.min : context.nextInt(this.max - this.min + 1) + this.min;
	}

}
