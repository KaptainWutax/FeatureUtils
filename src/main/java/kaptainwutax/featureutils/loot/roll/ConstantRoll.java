package kaptainwutax.featureutils.loot.roll;

import kaptainwutax.featureutils.loot.LootContext;

public class ConstantRoll extends LootRoll {

	public final int value;

	public ConstantRoll(int value) {
		this.value = value;
	}

	@Override
	public int nextInt(LootContext context) {
		return this.value;
	}

}
