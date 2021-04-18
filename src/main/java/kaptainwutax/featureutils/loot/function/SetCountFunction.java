package kaptainwutax.featureutils.loot.function;

import kaptainwutax.featureutils.loot.LootContext;
import kaptainwutax.featureutils.loot.item.ItemStack;
import kaptainwutax.featureutils.loot.roll.BinomialRoll;
import kaptainwutax.featureutils.loot.roll.ConstantRoll;
import kaptainwutax.featureutils.loot.roll.LootRoll;
import kaptainwutax.featureutils.loot.roll.UniformRoll;

public class SetCountFunction implements LootFunction {
	private final LootRoll roll;

	public SetCountFunction(LootRoll roll) {
		this.roll = roll;
	}

	public static SetCountFunction constant(int value) {
		return new SetCountFunction(new ConstantRoll(value));
	}

	public static SetCountFunction uniform(float min, float max) {
		return new SetCountFunction(new UniformRoll(min, max));
	}

	public static SetCountFunction binomial(int trials, float probability) {
		return new SetCountFunction(new BinomialRoll(trials, probability));
	}

	@Override
	public ItemStack process(ItemStack baseStack, LootContext context) {
		baseStack.setCount(this.roll.getCount(context));
		return baseStack;
	}

}
