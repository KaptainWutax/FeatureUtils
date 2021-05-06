package kaptainwutax.featureutils.loot.roll;

import kaptainwutax.featureutils.loot.LootContext;
import kaptainwutax.noiseutils.utils.MathHelper;

public class UniformRoll extends LootRoll {

	public final int min;
	public final int max;

	public UniformRoll(float value) {
		this(value, value);
	}

	public UniformRoll(float min, float max) {
		this.min = MathHelper.floor(min);
		this.max = MathHelper.floor(max);
	}

	@Override
	public int getCount(LootContext context) {
		return this.min >= this.max ? this.min : context.nextInt(this.max - this.min + 1) + this.min;
	}

	@Override
	public float getFloat(LootContext context) {
		return this.min >= this.max ? this.min : context.nextFloat() * (this.max - this.min) + this.min;
	}
}
