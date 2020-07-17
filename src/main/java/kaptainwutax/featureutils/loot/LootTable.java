package kaptainwutax.featureutils.loot;

import kaptainwutax.featureutils.loot.function.LootFunction;
import kaptainwutax.featureutils.loot.item.ItemStack;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Consumer;

public class LootTable extends LootGenerator {

	public final LootPool[] lootPools;

	public LootTable(LootPool... lootPools) {
		this(Arrays.asList(lootPools), null);
	}

	public LootTable(Collection<LootPool> lootPools, Collection<LootFunction> lootFunctions) {
		this.lootPools = lootPools.toArray(new LootPool[0]);
		this.apply(lootFunctions);
	}

	public LootTable apply(LootFunction... lootFunctions) {
		this.apply(Arrays.asList(lootFunctions));
		return this;
	}

	@Override
	public void generate(LootContext context, Consumer<ItemStack> stackConsumer) {
		stackConsumer = LootFunction.stack(stackConsumer, this.combinedLootFunction, context);

		for(LootPool lootPool: this.lootPools) {
			lootPool.generate(context, stackConsumer);
		}
	}

}
