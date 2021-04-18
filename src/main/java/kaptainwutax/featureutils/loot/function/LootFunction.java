package kaptainwutax.featureutils.loot.function;

import kaptainwutax.featureutils.loot.LootContext;
import kaptainwutax.featureutils.loot.item.ItemStack;

import java.util.function.Consumer;

@FunctionalInterface
public interface LootFunction {
	static LootFunction combine(LootFunction[] lootFunctions) {
		return (baseStack, context) -> {
			for (LootFunction lootFunction : lootFunctions) {
				baseStack = lootFunction.process(baseStack, context);
			}

			return baseStack;
		};
	}

	static Consumer<ItemStack> stack(Consumer<ItemStack> stackConsumer, LootFunction lootFunction, LootContext context) {
		return stack -> stackConsumer.accept(lootFunction.process(stack, context));
	}

	ItemStack process(ItemStack baseStack, LootContext context);

}