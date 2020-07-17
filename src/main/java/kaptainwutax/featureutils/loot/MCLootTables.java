package kaptainwutax.featureutils.loot;

import kaptainwutax.featureutils.loot.function.SetCountFunction;
import kaptainwutax.featureutils.loot.item.Item;
import kaptainwutax.featureutils.loot.roll.ConstantRoll;
import kaptainwutax.featureutils.loot.roll.UniformRoll;

public class MCLootTables {

	public static final LootTable BURIED_TREASURE_CHEST = new LootTable(
			new LootPool(new ConstantRoll(1), new LootEntry(Item.HEART_OF_THE_SEA)),
			new LootPool(new UniformRoll(5.0F, 8.0F),
					new LootEntry(Item.IRON_INGOT, 20).apply(SetCountFunction.uniform(1.0F, 4.0F)),
					new LootEntry(Item.GOLD_INGOT, 10).apply(SetCountFunction.uniform(1.0F, 4.0F)),
					new LootEntry(Item.TNT, 5).apply(SetCountFunction.uniform(1.0F, 2.0F))),
			new LootPool(new UniformRoll(1.0F, 3.0F),
					new LootEntry(Item.EMERALD, 5).apply(SetCountFunction.uniform(4.0F, 8.0F)),
					new LootEntry(Item.DIAMOND, 5).apply(SetCountFunction.uniform(1.0F, 2.0F)),
					new LootEntry(Item.PRISMARINE_CRYSTALS, 5).apply(SetCountFunction.uniform(1.0F, 5.0F))),
			new LootPool(new UniformRoll(0.0F, 1.0F), new LootEntry(Item.LEATHER_CHESTPLATE), new LootEntry(Item.IRON_SWORD)),
			new LootPool(new ConstantRoll(2),
					new LootEntry(Item.COOKED_COD).apply(SetCountFunction.uniform(2.0F, 4.0F)),
					new LootEntry(Item.COOKED_SALMON).apply(SetCountFunction.uniform(2.0F, 4.0F)))
	);

}
