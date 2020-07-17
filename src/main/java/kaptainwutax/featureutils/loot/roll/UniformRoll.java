package kaptainwutax.featureutils.loot.roll;

import kaptainwutax.featureutils.loot.LootContext;
import kaptainwutax.seedutils.util.math.Mth;

public class UniformRoll extends LootRoll {

	public final float min;
	public final float max;

	public UniformRoll(float value) {
		this(value, value);
	}

	public UniformRoll(float min, float max) {
		this.min = min;
		this.max = max;
	}

	@Override
	public int nextInt(LootContext context) {
		int a = Mth.floor(this.min);
		int b = Mth.floor(this.max);
		return a >= b ? a : context.nextInt(b - a + 1) + a;
	}

}
