package kaptainwutax.featureutils.loot;

import kaptainwutax.featureutils.loot.entry.EmptyEntry;
import kaptainwutax.featureutils.loot.entry.ItemEntry;
import kaptainwutax.featureutils.loot.item.Item;
import kaptainwutax.featureutils.loot.roll.ConstantRoll;
import kaptainwutax.featureutils.loot.roll.UniformRoll;

import static kaptainwutax.featureutils.loot.function.SetCountFunction.uniform;

public class MCLootTables {

	public static final LootTable BURIED_TREASURE_CHEST = new LootTable(
			new LootPool(new ConstantRoll(1), new ItemEntry(Item.HEART_OF_THE_SEA)),
			new LootPool(new UniformRoll(5.0F, 8.0F),
					new ItemEntry(Item.IRON_INGOT, 20).apply(uniform(1.0F, 4.0F)),
					new ItemEntry(Item.GOLD_INGOT, 10).apply(uniform(1.0F, 4.0F)),
					new ItemEntry(Item.TNT, 5).apply(uniform(1.0F, 2.0F))),
			new LootPool(new UniformRoll(1.0F, 3.0F),
					new ItemEntry(Item.EMERALD, 5).apply(uniform(4.0F, 8.0F)),
					new ItemEntry(Item.DIAMOND, 5).apply(uniform(1.0F, 2.0F)),
					new ItemEntry(Item.PRISMARINE_CRYSTALS, 5).apply(uniform(1.0F, 5.0F))),
			new LootPool(new UniformRoll(0.0F, 1.0F), new ItemEntry(Item.LEATHER_CHESTPLATE), new ItemEntry(Item.IRON_SWORD)),
			new LootPool(new ConstantRoll(2),
					new ItemEntry(Item.COOKED_COD).apply(uniform(2.0F, 4.0F)),
					new ItemEntry(Item.COOKED_SALMON).apply(uniform(2.0F, 4.0F)))
	);

	public static final LootTable DESERT_PYRAMID_CHEST = new LootTable(
			new LootPool(new UniformRoll(2.0F, 4.0F),
					new ItemEntry(Item.DIAMOND, 5).apply(uniform(1.0F, 3.0F)),
					new ItemEntry(Item.IRON_INGOT, 15).apply(uniform(1.0F, 5.0F)),
					new ItemEntry(Item.GOLD_INGOT, 15).apply(uniform(2.0F, 7.0F)),
					new ItemEntry(Item.EMERALD, 15).apply(uniform(1.0F, 3.0F)),
					new ItemEntry(Item.BONE, 25).apply(uniform(4.0F, 6.0F)),
					new ItemEntry(Item.SPIDER_EYE, 25).apply(uniform(1.0F, 3.0F)),
					new ItemEntry(Item.ROTTEN_FLESH, 25).apply(uniform(3.0F, 7.0F)),
					new ItemEntry(Item.SADDLE, 20),
					new ItemEntry(Item.IRON_HORSE_ARMOR, 15),
					new ItemEntry(Item.GOLDEN_HORSE_ARMOR, 10),
					new ItemEntry(Item.DIAMOND_HORSE_ARMOR, 5),
					new ItemEntry(Item.ENCHANTED_BOOK, 20), //TODO: enchantment loot function
					new ItemEntry(Item.GOLDEN_APPLE, 20),
					new ItemEntry(Item.ENCHANTED_GOLDEN_APPLE, 2),
					new EmptyEntry(15)),
			new LootPool(new ConstantRoll(4),
					new ItemEntry(Item.BONE, 10).apply(uniform(1.0F, 8.0F)),
					new ItemEntry(Item.GUNPOWDER, 10).apply(uniform(1.0F, 8.0F)),
					new ItemEntry(Item.ROTTEN_FLESH, 10).apply(uniform(1.0F, 8.0F)),
					new ItemEntry(Item.STRING, 10).apply(uniform(1.0F, 8.0F)),
					new ItemEntry(Item.SAND, 10).apply(uniform(1.0F, 8.0F)))
	);

}
