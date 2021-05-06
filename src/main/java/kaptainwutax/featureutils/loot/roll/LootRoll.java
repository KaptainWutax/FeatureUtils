package kaptainwutax.featureutils.loot.roll;

import kaptainwutax.featureutils.loot.LootContext;

public abstract class LootRoll {

	public abstract int getCount(LootContext context);

	public float getFloat(LootContext context) {
		return 0.0F;
	}

}
