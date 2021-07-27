package kaptainwutax.featureutils.loot;

import kaptainwutax.featureutils.loot.entry.LootEntry;
import kaptainwutax.featureutils.loot.function.LootFunction;
import kaptainwutax.featureutils.loot.item.ItemStack;
import kaptainwutax.mcutils.version.MCVersion;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class LootGenerator {

	public Function<MCVersion,LootFunction>[] supplierLootFunctions;
	public LootFunction[] lootFunctions;
	public LootFunction combinedLootFunction;

	public LootGenerator() {
		this.apply((Collection<Function<MCVersion,LootFunction>>)null);
	}

	@SuppressWarnings("unchecked")
	public LootGenerator apply(Collection<Function<MCVersion,LootFunction>> lootFunctions) {
		if(lootFunctions != null) {
			this.supplierLootFunctions = lootFunctions.toArray(new Function[0]);
		}else{
			this.supplierLootFunctions=null;
		}
		return this;
	}


	public LootGenerator apply(MCVersion version){
		if(supplierLootFunctions != null) {
			this.lootFunctions = new LootFunction[supplierLootFunctions.length];
			int i=0;
			for (Function<MCVersion,LootFunction> function:supplierLootFunctions){
				this.lootFunctions[i++]=function.apply(version);
			}
			this.combinedLootFunction = LootFunction.combine(this.lootFunctions);
		} else {
			this.lootFunctions = new LootFunction[0];
			this.combinedLootFunction = (baseStack, context) -> baseStack;
		}
		return this;
	}

	public abstract void generate(LootContext context, Consumer<ItemStack> stackConsumer);

}
