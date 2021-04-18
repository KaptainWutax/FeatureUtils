package kaptainwutax.featureutils.loot;

import kaptainwutax.featureutils.loot.effect.Effects;
import kaptainwutax.featureutils.loot.entry.EmptyEntry;
import kaptainwutax.featureutils.loot.entry.ItemEntry;
import kaptainwutax.featureutils.loot.function.ApplyDamageFunction;
import kaptainwutax.featureutils.loot.function.EffectFunction;
import kaptainwutax.featureutils.loot.function.EnchantRandomlyFunction;
import kaptainwutax.featureutils.loot.function.EnchantWithLevelsFunction;
import kaptainwutax.featureutils.loot.item.Items;
import kaptainwutax.featureutils.loot.roll.ConstantRoll;
import kaptainwutax.featureutils.loot.roll.UniformRoll;

import static kaptainwutax.featureutils.loot.function.SetCountFunction.constant;
import static kaptainwutax.featureutils.loot.function.SetCountFunction.uniform;

@SuppressWarnings("unused")
public class MCLootTables {

	public static final LootTable ABANDONED_MINESHAFT_CHEST = new LootTable(
			new LootPool(new ConstantRoll(1),
					new ItemEntry(Items.GOLDEN_APPLE, 20),
					new ItemEntry(Items.ENCHANTED_GOLDEN_APPLE),
					new ItemEntry(Items.NAME_TAG, 30),
					new ItemEntry(Items.ENCHANTED_BOOK, 10) /* enchant_randomly */,
					new ItemEntry(Items.IRON_PICKAXE, 5),
					new EmptyEntry(5)),
			new LootPool(new UniformRoll(2.0F, 4.0F),
					new ItemEntry(Items.IRON_INGOT, 10).apply(uniform(1.0F, 5.0F)),
					new ItemEntry(Items.GOLD_INGOT, 5).apply(uniform(1.0F, 3.0F)),
					new ItemEntry(Items.REDSTONE, 5).apply(uniform(4.0F, 9.0F)),
					new ItemEntry(Items.LAPIS_LAZULI, 5).apply(uniform(4.0F, 9.0F)),
					new ItemEntry(Items.DIAMOND, 3).apply(uniform(1.0F, 2.0F)),
					new ItemEntry(Items.COAL, 10).apply(uniform(3.0F, 8.0F)),
					new ItemEntry(Items.BREAD, 15).apply(uniform(1.0F, 3.0F)),
					new ItemEntry(Items.MELON_SEEDS, 10).apply(uniform(2.0F, 4.0F)),
					new ItemEntry(Items.PUMPKIN_SEEDS, 10).apply(uniform(2.0F, 4.0F)),
					new ItemEntry(Items.BEETROOT_SEEDS, 10).apply(uniform(2.0F, 4.0F))),
			new LootPool(new ConstantRoll(3),
					new ItemEntry(Items.RAIL, 20).apply(uniform(4.0F, 8.0F)),
					new ItemEntry(Items.POWERED_RAIL, 5).apply(uniform(1.0F, 4.0F)),
					new ItemEntry(Items.DETECTOR_RAIL, 5).apply(uniform(1.0F, 4.0F)),
					new ItemEntry(Items.ACTIVATOR_RAIL, 5).apply(uniform(1.0F, 4.0F)),
					new ItemEntry(Items.TORCH, 15).apply(uniform(1.0F, 16.0F)))
	);

	public static final LootTable BASTION_BRIDGE_CHEST = new LootTable(
			new LootPool(new ConstantRoll(1),
					new ItemEntry(Items.LODESTONE).apply(constant(1))),
			new LootPool(new UniformRoll(1.0F, 2.0F),
					new ItemEntry(Items.CROSSBOW) /* set_damage */ /* enchant_randomly */,
					new ItemEntry(Items.SPECTRAL_ARROW).apply(uniform(2.0F, 12.0F)),
					new ItemEntry(Items.GILDED_BLACKSTONE).apply(uniform(5.0F, 8.0F)),
					new ItemEntry(Items.CRYING_OBSIDIAN).apply(uniform(3.0F, 8.0F)),
					new ItemEntry(Items.GOLD_BLOCK).apply(constant(1)),
					new ItemEntry(Items.GOLD_INGOT).apply(uniform(2.0F, 8.0F)),
					new ItemEntry(Items.IRON_INGOT).apply(uniform(2.0F, 8.0F)),
					new ItemEntry(Items.GOLDEN_SWORD).apply(constant(1)),
					new ItemEntry(Items.GOLDEN_CHESTPLATE).apply(constant(1)) /* enchant_randomly */,
					new ItemEntry(Items.GOLDEN_HELMET).apply(constant(1)) /* enchant_randomly */,
					new ItemEntry(Items.GOLDEN_LEGGINGS).apply(constant(1)) /* enchant_randomly */,
					new ItemEntry(Items.GOLDEN_BOOTS).apply(constant(1)) /* enchant_randomly */),
			new LootPool(new UniformRoll(2.0F, 4.0F),
					new ItemEntry(Items.STRING).apply(uniform(1.0F, 6.0F)),
					new ItemEntry(Items.LEATHER).apply(uniform(1.0F, 3.0F)),
					new ItemEntry(Items.ARROW).apply(uniform(5.0F, 17.0F)),
					new ItemEntry(Items.IRON_NUGGET).apply(uniform(2.0F, 6.0F)),
					new ItemEntry(Items.GOLD_NUGGET).apply(uniform(2.0F, 6.0F)))
	);

	public static final LootTable BASTION_HOGLIN_STABLE_CHEST = new LootTable(
			new LootPool(new ConstantRoll(1),
					new ItemEntry(Items.DIAMOND_SHOVEL, 5) /* set_damage */ /* enchant_randomly */,
					new ItemEntry(Items.NETHERITE_SCRAP, 2).apply(constant(1)),
					new ItemEntry(Items.ANCIENT_DEBRIS, 3).apply(constant(1)),
					new ItemEntry(Items.SADDLE, 10).apply(constant(1)),
					new ItemEntry(Items.GOLD_BLOCK, 25).apply(uniform(2.0F, 4.0F)),
					new ItemEntry(Items.GOLDEN_HOE, 15).apply(constant(1)) /* enchant_randomly */,
					new EmptyEntry(45)),
			new LootPool(new UniformRoll(3.0F, 4.0F),
					new ItemEntry(Items.GLOWSTONE).apply(uniform(1.0F, 5.0F)),
					new ItemEntry(Items.GILDED_BLACKSTONE).apply(uniform(1.0F, 5.0F)),
					new ItemEntry(Items.SOUL_SAND).apply(uniform(2.0F, 7.0F)),
					new ItemEntry(Items.CRIMSON_NYLIUM).apply(uniform(2.0F, 7.0F)),
					new ItemEntry(Items.GOLD_NUGGET).apply(uniform(2.0F, 8.0F)),
					new ItemEntry(Items.LEATHER).apply(uniform(1.0F, 3.0F)),
					new ItemEntry(Items.ARROW).apply(uniform(5.0F, 17.0F)),
					new ItemEntry(Items.STRING).apply(uniform(3.0F, 8.0F)),
					new ItemEntry(Items.PORKCHOP).apply(uniform(2.0F, 5.0F)),
					new ItemEntry(Items.COOKED_PORKCHOP).apply(uniform(2.0F, 5.0F)),
					new ItemEntry(Items.CRIMSON_FUNGUS).apply(uniform(2.0F, 7.0F)),
					new ItemEntry(Items.CRIMSON_ROOTS).apply(uniform(2.0F, 7.0F)))
	);

	public static final LootTable BASTION_OTHER_CHEST = new LootTable(
			new LootPool(new ConstantRoll(1),
					new ItemEntry(Items.CROSSBOW, 12) /* set_damage */ /* enchant_randomly */,
					new ItemEntry(Items.ANCIENT_DEBRIS, 2).apply(constant(1)),
					new ItemEntry(Items.NETHERITE_SCRAP, 2).apply(constant(1)),
					new ItemEntry(Items.SPECTRAL_ARROW, 16).apply(uniform(2.0F, 15.0F)),
					new ItemEntry(Items.PIGLIN_BANNER_PATTERN, 5).apply(constant(1)),
					new ItemEntry(Items.MUSIC_DISC_PIGSTEP, 3).apply(constant(1)),
					new ItemEntry(Items.ENCHANTED_BOOK, 10) /* enchant_randomly */,
					new EmptyEntry(50)),
			new LootPool(new ConstantRoll(2),
					new ItemEntry(Items.GOLDEN_BOOTS).apply(constant(1)) /* enchant_randomly */,
					new ItemEntry(Items.GOLD_BLOCK).apply(constant(1)),
					new ItemEntry(Items.CROSSBOW).apply(constant(1)),
					new ItemEntry(Items.GOLD_INGOT).apply(uniform(1.0F, 6.0F)),
					new ItemEntry(Items.IRON_INGOT).apply(uniform(1.0F, 6.0F)),
					new ItemEntry(Items.GOLDEN_SWORD).apply(constant(1)),
					new ItemEntry(Items.GOLDEN_CHESTPLATE).apply(constant(1)),
					new ItemEntry(Items.GOLDEN_HELMET).apply(constant(1)),
					new ItemEntry(Items.GOLDEN_LEGGINGS).apply(constant(1)),
					new ItemEntry(Items.GOLDEN_BOOTS).apply(constant(1)),
					new EmptyEntry(2)),
			new LootPool(new UniformRoll(3.0F, 5.0F),
					new ItemEntry(Items.CRYING_OBSIDIAN).apply(uniform(1.0F, 5.0F)),
					new ItemEntry(Items.GILDED_BLACKSTONE).apply(uniform(1.0F, 5.0F)),
					new ItemEntry(Items.CHAIN).apply(uniform(2.0F, 10.0F)),
					new ItemEntry(Items.MAGMA_CREAM).apply(uniform(2.0F, 6.0F)),
					new ItemEntry(Items.BONE_BLOCK).apply(uniform(3.0F, 6.0F)),
					new ItemEntry(Items.IRON_NUGGET).apply(uniform(2.0F, 8.0F)),
					new ItemEntry(Items.OBSIDIAN).apply(uniform(4.0F, 6.0F)),
					new ItemEntry(Items.GOLD_NUGGET).apply(uniform(2.0F, 8.0F)),
					new ItemEntry(Items.STRING).apply(uniform(4.0F, 6.0F)),
					new ItemEntry(Items.ARROW, 2).apply(uniform(5.0F, 17.0F)))
	);

	public static final LootTable BASTION_TREASURE_CHEST = new LootTable(
			new LootPool(new UniformRoll(1.0F, 2.0F),
					new ItemEntry(Items.NETHERITE_INGOT, 10).apply(constant(1)),
					new ItemEntry(Items.ANCIENT_DEBRIS, 14).apply(constant(1)),
					new ItemEntry(Items.NETHERITE_SCRAP, 10).apply(constant(1)),
					new ItemEntry(Items.ANCIENT_DEBRIS).apply(constant(2)),
					new ItemEntry(Items.DIAMOND_SWORD, 10).apply(new ApplyDamageFunction(), new EnchantRandomlyFunction()) /* set_damage */ /* enchant_randomly */,
					new ItemEntry(Items.DIAMOND_CHESTPLATE, 6).apply(new ApplyDamageFunction(), new EnchantRandomlyFunction()) /* set_damage */ /* enchant_randomly */,
					new ItemEntry(Items.DIAMOND_HELMET, 6).apply(new ApplyDamageFunction(), new EnchantRandomlyFunction()) /* set_damage */ /* enchant_randomly */,
					new ItemEntry(Items.DIAMOND_LEGGINGS, 6).apply(new ApplyDamageFunction(), new EnchantRandomlyFunction()) /* set_damage */ /* enchant_randomly */,
					new ItemEntry(Items.DIAMOND_BOOTS, 6).apply(new ApplyDamageFunction(), new EnchantRandomlyFunction()) /* set_damage */ /* enchant_randomly */,
					new ItemEntry(Items.DIAMOND_SWORD, 6).apply(new ApplyDamageFunction()) /* set_damage */,
					new ItemEntry(Items.DIAMOND_CHESTPLATE, 5).apply(new ApplyDamageFunction()) /* set_damage */,
					new ItemEntry(Items.DIAMOND_HELMET, 5).apply(new ApplyDamageFunction()) /* set_damage */,
					new ItemEntry(Items.DIAMOND_BOOTS, 5).apply(new ApplyDamageFunction()) /* set_damage */,
					new ItemEntry(Items.DIAMOND_LEGGINGS, 5).apply(new ApplyDamageFunction()) /* set_damage */,
					new ItemEntry(Items.DIAMOND, 5).apply(uniform(1.0F, 3.0F))),
			new LootPool(new UniformRoll(2.0F, 4.0F),
					new ItemEntry(Items.SPECTRAL_ARROW).apply(uniform(5.0F, 21.0F)),
					new ItemEntry(Items.GOLD_BLOCK).apply(uniform(2.0F, 5.0F)),
					new ItemEntry(Items.GOLD_INGOT).apply(uniform(3.0F, 9.0F)),
					new ItemEntry(Items.IRON_INGOT).apply(uniform(3.0F, 9.0F)),
					new ItemEntry(Items.CRYING_OBSIDIAN).apply(uniform(1.0F, 5.0F)),
					new ItemEntry(Items.QUARTZ).apply(uniform(8.0F, 23.0F)),
					new ItemEntry(Items.GILDED_BLACKSTONE).apply(uniform(1.0F, 5.0F)),
					new ItemEntry(Items.MAGMA_CREAM).apply(uniform(2.0F, 8.0F)),
					new ItemEntry(Items.IRON_NUGGET).apply(uniform(8.0F, 16.0F)))
	);

	public static final LootTable BURIED_TREASURE_CHEST = new LootTable(
			new LootPool(new ConstantRoll(1),
					new ItemEntry(Items.HEART_OF_THE_SEA)),
			new LootPool(new UniformRoll(5.0F, 8.0F),
					new ItemEntry(Items.IRON_INGOT, 20).apply(uniform(1.0F, 4.0F)),
					new ItemEntry(Items.GOLD_INGOT, 10).apply(uniform(1.0F, 4.0F)),
					new ItemEntry(Items.TNT, 5).apply(uniform(1.0F, 2.0F))),
			new LootPool(new UniformRoll(1.0F, 3.0F),
					new ItemEntry(Items.EMERALD, 5).apply(uniform(4.0F, 8.0F)),
					new ItemEntry(Items.DIAMOND, 5).apply(uniform(1.0F, 2.0F)),
					new ItemEntry(Items.PRISMARINE_CRYSTALS, 5).apply(uniform(1.0F, 5.0F))),
			new LootPool(new UniformRoll(0.0F, 1.0F),
					new ItemEntry(Items.LEATHER_CHESTPLATE),
					new ItemEntry(Items.IRON_SWORD)),
			new LootPool(new ConstantRoll(2),
					new ItemEntry(Items.COOKED_COD).apply(uniform(2.0F, 4.0F)),
					new ItemEntry(Items.COOKED_SALMON).apply(uniform(2.0F, 4.0F)))
	);

	public static final LootTable DESERT_PYRAMID_CHEST = new LootTable(
			new LootPool(new UniformRoll(2.0F, 4.0F),
					new ItemEntry(Items.DIAMOND, 5).apply(uniform(1.0F, 3.0F)),
					new ItemEntry(Items.IRON_INGOT, 15).apply(uniform(1.0F, 5.0F)),
					new ItemEntry(Items.GOLD_INGOT, 15).apply(uniform(2.0F, 7.0F)),
					new ItemEntry(Items.EMERALD, 15).apply(uniform(1.0F, 3.0F)),
					new ItemEntry(Items.BONE, 25).apply(uniform(4.0F, 6.0F)),
					new ItemEntry(Items.SPIDER_EYE, 25).apply(uniform(1.0F, 3.0F)),
					new ItemEntry(Items.ROTTEN_FLESH, 25).apply(uniform(3.0F, 7.0F)),
					new ItemEntry(Items.SADDLE, 20),
					new ItemEntry(Items.IRON_HORSE_ARMOR, 15),
					new ItemEntry(Items.GOLDEN_HORSE_ARMOR, 10),
					new ItemEntry(Items.DIAMOND_HORSE_ARMOR, 5),
					new ItemEntry(Items.ENCHANTED_BOOK, 20).apply(new EnchantRandomlyFunction()),
					new ItemEntry(Items.GOLDEN_APPLE, 20),
					new ItemEntry(Items.ENCHANTED_GOLDEN_APPLE, 2),
					new EmptyEntry(15)),
			new LootPool(new ConstantRoll(4),
					new ItemEntry(Items.BONE, 10).apply(uniform(1.0F, 8.0F)),
					new ItemEntry(Items.GUNPOWDER, 10).apply(uniform(1.0F, 8.0F)),
					new ItemEntry(Items.ROTTEN_FLESH, 10).apply(uniform(1.0F, 8.0F)),
					new ItemEntry(Items.STRING, 10).apply(uniform(1.0F, 8.0F)),
					new ItemEntry(Items.SAND, 10).apply(uniform(1.0F, 8.0F)))
	);

	public static final LootTable END_CITY_TREASURE_CHEST = new LootTable(
			new LootPool(new UniformRoll(2.0F, 6.0F),
					new ItemEntry(Items.DIAMOND, 5).apply(uniform(2.0F, 7.0F)),
					new ItemEntry(Items.IRON_INGOT, 10).apply(uniform(4.0F, 8.0F)),
					new ItemEntry(Items.GOLD_INGOT, 15).apply(uniform(2.0F, 7.0F)),
					new ItemEntry(Items.EMERALD, 2).apply(uniform(2.0F, 6.0F)),
					new ItemEntry(Items.BEETROOT_SEEDS, 5).apply(uniform(1.0F, 10.0F)),
					new ItemEntry(Items.SADDLE, 3),
					new ItemEntry(Items.IRON_HORSE_ARMOR),
					new ItemEntry(Items.GOLDEN_HORSE_ARMOR),
					new ItemEntry(Items.DIAMOND_HORSE_ARMOR),
					new ItemEntry(Items.DIAMOND_SWORD, 3) /* enchant_with_levels */,
					new ItemEntry(Items.DIAMOND_BOOTS, 3) /* enchant_with_levels */,
					new ItemEntry(Items.DIAMOND_CHESTPLATE, 3) /* enchant_with_levels */,
					new ItemEntry(Items.DIAMOND_LEGGINGS, 3) /* enchant_with_levels */,
					new ItemEntry(Items.DIAMOND_HELMET, 3) /* enchant_with_levels */,
					new ItemEntry(Items.DIAMOND_PICKAXE, 3) /* enchant_with_levels */,
					new ItemEntry(Items.DIAMOND_SHOVEL, 3) /* enchant_with_levels */,
					new ItemEntry(Items.IRON_SWORD, 3) /* enchant_with_levels */,
					new ItemEntry(Items.IRON_BOOTS, 3) /* enchant_with_levels */,
					new ItemEntry(Items.IRON_CHESTPLATE, 3) /* enchant_with_levels */,
					new ItemEntry(Items.IRON_LEGGINGS, 3) /* enchant_with_levels */,
					new ItemEntry(Items.IRON_HELMET, 3) /* enchant_with_levels */,
					new ItemEntry(Items.IRON_PICKAXE, 3) /* enchant_with_levels */,
					new ItemEntry(Items.IRON_SHOVEL, 3) /* enchant_with_levels */)
	);

	public static final LootTable IGLOO_CHEST_CHEST = new LootTable(
			new LootPool(new UniformRoll(2.0F, 8.0F),
					new ItemEntry(Items.APPLE, 15).apply(uniform(1.0F, 3.0F)),
					new ItemEntry(Items.COAL, 15).apply(uniform(1.0F, 4.0F)),
					new ItemEntry(Items.GOLD_NUGGET, 10).apply(uniform(1.0F, 3.0F)),
					new ItemEntry(Items.STONE_AXE, 2),
					new ItemEntry(Items.ROTTEN_FLESH, 10),
					new ItemEntry(Items.EMERALD),
					new ItemEntry(Items.WHEAT, 10).apply(uniform(2.0F, 3.0F))),
			new LootPool(new ConstantRoll(1),
					new ItemEntry(Items.GOLDEN_APPLE))
	);

	public static final LootTable JUNGLE_TEMPLE_CHEST = new LootTable(
			new LootPool(new UniformRoll(2.0F, 6.0F),
					new ItemEntry(Items.DIAMOND, 3).apply(uniform(1.0F, 3.0F)),
					new ItemEntry(Items.IRON_INGOT, 10).apply(uniform(1.0F, 5.0F)),
					new ItemEntry(Items.GOLD_INGOT, 15).apply(uniform(2.0F, 7.0F)),
					new ItemEntry(Items.BAMBOO, 15).apply(uniform(1.0F, 3.0F)),
					new ItemEntry(Items.EMERALD, 2).apply(uniform(1.0F, 3.0F)),
					new ItemEntry(Items.BONE, 20).apply(uniform(4.0F, 6.0F)),
					new ItemEntry(Items.ROTTEN_FLESH, 16).apply(uniform(3.0F, 7.0F)),
					new ItemEntry(Items.SADDLE, 3),
					new ItemEntry(Items.IRON_HORSE_ARMOR),
					new ItemEntry(Items.GOLDEN_HORSE_ARMOR),
					new ItemEntry(Items.DIAMOND_HORSE_ARMOR),
					new ItemEntry(Items.BOOK).apply(new EnchantWithLevelsFunction(30, 30, true)))
	);

	public static final LootTable JUNGLE_TEMPLE_DISPENSER_CHEST = new LootTable(
			new LootPool(new UniformRoll(1.0F, 2.0F),
					new ItemEntry(Items.ARROW, 30).apply(uniform(2.0F, 7.0F)))
	);

	public static final LootTable NETHER_BRIDGE_CHEST = new LootTable(
			new LootPool(new UniformRoll(2.0F, 4.0F),
					new ItemEntry(Items.DIAMOND, 5).apply(uniform(1.0F, 3.0F)),
					new ItemEntry(Items.IRON_INGOT, 5).apply(uniform(1.0F, 5.0F)),
					new ItemEntry(Items.GOLD_INGOT, 15).apply(uniform(1.0F, 3.0F)),
					new ItemEntry(Items.GOLDEN_SWORD, 5),
					new ItemEntry(Items.GOLDEN_CHESTPLATE, 5),
					new ItemEntry(Items.FLINT_AND_STEEL, 5),
					new ItemEntry(Items.NETHER_WART, 5).apply(uniform(3.0F, 7.0F)),
					new ItemEntry(Items.SADDLE, 10),
					new ItemEntry(Items.GOLDEN_HORSE_ARMOR, 8),
					new ItemEntry(Items.IRON_HORSE_ARMOR, 5),
					new ItemEntry(Items.DIAMOND_HORSE_ARMOR, 3),
					new ItemEntry(Items.OBSIDIAN, 2).apply(uniform(2.0F, 4.0F)))
	);

	public static final LootTable PILLAGER_OUTPOST_CHEST = new LootTable(
			new LootPool(new UniformRoll(0.0F, 1.0F),
					new ItemEntry(Items.CROSSBOW)),
			new LootPool(new UniformRoll(2.0F, 3.0F),
					new ItemEntry(Items.WHEAT, 7).apply(uniform(3.0F, 5.0F)),
					new ItemEntry(Items.POTATO, 5).apply(uniform(2.0F, 5.0F)),
					new ItemEntry(Items.CARROT, 5).apply(uniform(3.0F, 5.0F))),
			new LootPool(new UniformRoll(1.0F, 3.0F),
					new ItemEntry(Items.DARK_OAK_LOG).apply(uniform(2.0F, 3.0F))),
			new LootPool(new UniformRoll(2.0F, 3.0F),
					new ItemEntry(Items.EXPERIENCE_BOTTLE, 7),
					new ItemEntry(Items.STRING, 4).apply(uniform(1.0F, 6.0F)),
					new ItemEntry(Items.ARROW, 4).apply(uniform(2.0F, 7.0F)),
					new ItemEntry(Items.TRIPWIRE_HOOK, 3).apply(uniform(1.0F, 3.0F)),
					new ItemEntry(Items.IRON_INGOT, 3).apply(uniform(1.0F, 3.0F)),
					new ItemEntry(Items.ENCHANTED_BOOK) /* enchant_randomly */)
	);

	public static final LootTable RUINED_PORTAL_CHEST = new LootTable(
			new LootPool(new UniformRoll(4.0F, 8.0F),
					new ItemEntry(Items.OBSIDIAN, 40).apply(uniform(1.0F, 2.0F)),
					new ItemEntry(Items.FLINT, 40).apply(uniform(1.0F, 4.0F)),
					new ItemEntry(Items.IRON_NUGGET, 40).apply(uniform(9.0F, 18.0F)),
					new ItemEntry(Items.FLINT_AND_STEEL, 40),
					new ItemEntry(Items.FIRE_CHARGE, 40),
					new ItemEntry(Items.GOLDEN_APPLE, 15),
					new ItemEntry(Items.GOLD_NUGGET, 15).apply(uniform(4.0F, 24.0F)),
					new ItemEntry(Items.GOLDEN_SWORD, 15).apply(new EnchantRandomlyFunction(true)),
					new ItemEntry(Items.GOLDEN_AXE, 15).apply(new EnchantRandomlyFunction(true)),
					new ItemEntry(Items.GOLDEN_HOE, 15).apply(new EnchantRandomlyFunction(true)),
					new ItemEntry(Items.GOLDEN_SHOVEL, 15).apply(new EnchantRandomlyFunction(true)),
					new ItemEntry(Items.GOLDEN_PICKAXE, 15).apply(new EnchantRandomlyFunction(true)),
					new ItemEntry(Items.GOLDEN_BOOTS, 15).apply(new EnchantRandomlyFunction(true)),
					new ItemEntry(Items.GOLDEN_CHESTPLATE, 15).apply(new EnchantRandomlyFunction(true)),
					new ItemEntry(Items.GOLDEN_HELMET, 15).apply(new EnchantRandomlyFunction(true)),
					new ItemEntry(Items.GOLDEN_LEGGINGS, 15).apply(new EnchantRandomlyFunction(true)),
					new ItemEntry(Items.GLISTERING_MELON_SLICE, 5).apply(uniform(4.0F, 12.0F)),
					new ItemEntry(Items.GOLDEN_HORSE_ARMOR, 5),
					new ItemEntry(Items.LIGHT_WEIGHTED_PRESSURE_PLATE, 5),
					new ItemEntry(Items.GOLDEN_CARROT, 5).apply(uniform(4.0F, 12.0F)),
					new ItemEntry(Items.CLOCK, 5),
					new ItemEntry(Items.GOLD_INGOT, 5).apply(uniform(2.0F, 8.0F)),
					new ItemEntry(Items.BELL),
					new ItemEntry(Items.ENCHANTED_GOLDEN_APPLE),
					new ItemEntry(Items.GOLD_BLOCK).apply(uniform(1.0F, 2.0F)))
	);

	public static final LootTable SHIPWRECK_MAP_CHEST = new LootTable(
			new LootPool(new ConstantRoll(1),
					new ItemEntry(Items.MAP) /* exploration_map*/),
			new LootPool(new ConstantRoll(3),
					new ItemEntry(Items.COMPASS),
					new ItemEntry(Items.MAP),
					new ItemEntry(Items.CLOCK),
					new ItemEntry(Items.PAPER, 20).apply(uniform(1.0F, 10.0F)),
					new ItemEntry(Items.FEATHER, 10).apply(uniform(1.0F, 5.0F)),
					new ItemEntry(Items.BOOK, 5).apply(uniform(1.0F, 5.0F)))
	);

	public static final LootTable SHIPWRECK_SUPPLY_CHEST = new LootTable(
			new LootPool(new UniformRoll(3.0F, 10.0F),
					new ItemEntry(Items.PAPER, 8).apply(uniform(1.0F, 12.0F)),
					new ItemEntry(Items.POTATO, 7).apply(uniform(2.0F, 6.0F)),
					new ItemEntry(Items.POISONOUS_POTATO, 7).apply(uniform(2.0F, 6.0F)),
					new ItemEntry(Items.CARROT, 7).apply(uniform(4.0F, 8.0F)),
					new ItemEntry(Items.WHEAT, 7).apply(uniform(8.0F, 21.0F)),
					new ItemEntry(Items.SUSPICIOUS_STEW, 10).apply(
							EffectFunction.builder()
									.apply(Effects.NIGHT_VISION, 7.0F, 10.0F)
									.apply(Effects.JUMP, 7.0F, 10.0F)
									.apply(Effects.WEAKNESS, 6.0F, 8.0F)
									.apply(Effects.BLINDNESS, 5.0F, 7.0F)
									.apply(Effects.POISON, 10.0F, 20.0F)
									.apply(Effects.SATURATION, 7.0F, 10.0F)),
					new ItemEntry(Items.COAL, 6).apply(uniform(2.0F, 8.0F)),
					new ItemEntry(Items.ROTTEN_FLESH, 5).apply(uniform(5.0F, 24.0F)),
					new ItemEntry(Items.PUMPKIN, 2).apply(uniform(1.0F, 3.0F)),
					new ItemEntry(Items.BAMBOO, 2).apply(uniform(1.0F, 3.0F)),
					new ItemEntry(Items.GUNPOWDER, 3).apply(uniform(1.0F, 5.0F)),
					new ItemEntry(Items.TNT).apply(uniform(1.0F, 2.0F)),
					new ItemEntry(Items.LEATHER_HELMET, 3).apply(new EnchantRandomlyFunction()),
					new ItemEntry(Items.LEATHER_CHESTPLATE, 3).apply(new EnchantRandomlyFunction()),
					new ItemEntry(Items.LEATHER_LEGGINGS, 3).apply(new EnchantRandomlyFunction()),
					new ItemEntry(Items.LEATHER_BOOTS, 3).apply(new EnchantRandomlyFunction()))
	);

	public static final LootTable SHIPWRECK_TREASURE_CHEST = new LootTable(
			new LootPool(new UniformRoll(3.0F, 6.0F),
					new ItemEntry(Items.IRON_INGOT, 90).apply(uniform(1.0F, 5.0F)),
					new ItemEntry(Items.GOLD_INGOT, 10).apply(uniform(1.0F, 5.0F)),
					new ItemEntry(Items.EMERALD, 40).apply(uniform(1.0F, 5.0F)),
					new ItemEntry(Items.DIAMOND, 5),
					new ItemEntry(Items.EXPERIENCE_BOTTLE, 5)),
			new LootPool(new UniformRoll(2.0F, 5.0F),
					new ItemEntry(Items.IRON_NUGGET, 50).apply(uniform(1.0F, 10.0F)),
					new ItemEntry(Items.GOLD_NUGGET, 10).apply(uniform(1.0F, 10.0F)),
					new ItemEntry(Items.LAPIS_LAZULI, 20).apply(uniform(1.0F, 10.0F)))
	);

	public static final LootTable SIMPLE_DUNGEON_CHEST = new LootTable(
			new LootPool(new UniformRoll(1.0F, 3.0F),
					new ItemEntry(Items.SADDLE, 20),
					new ItemEntry(Items.GOLDEN_APPLE, 15),
					new ItemEntry(Items.ENCHANTED_GOLDEN_APPLE, 2),
					new ItemEntry(Items.MUSIC_DISC_13, 15),
					new ItemEntry(Items.MUSIC_DISC_CAT, 15),
					new ItemEntry(Items.NAME_TAG, 20),
					new ItemEntry(Items.GOLDEN_HORSE_ARMOR, 10),
					new ItemEntry(Items.IRON_HORSE_ARMOR, 15),
					new ItemEntry(Items.DIAMOND_HORSE_ARMOR, 5),
					new ItemEntry(Items.ENCHANTED_BOOK, 10) /* enchant_randomly */),
			new LootPool(new UniformRoll(1.0F, 4.0F),
					new ItemEntry(Items.IRON_INGOT, 10).apply(uniform(1.0F, 4.0F)),
					new ItemEntry(Items.GOLD_INGOT, 5).apply(uniform(1.0F, 4.0F)),
					new ItemEntry(Items.BREAD, 20),
					new ItemEntry(Items.WHEAT, 20).apply(uniform(1.0F, 4.0F)),
					new ItemEntry(Items.BUCKET, 10),
					new ItemEntry(Items.REDSTONE, 15).apply(uniform(1.0F, 4.0F)),
					new ItemEntry(Items.COAL, 15).apply(uniform(1.0F, 4.0F)),
					new ItemEntry(Items.MELON_SEEDS, 10).apply(uniform(2.0F, 4.0F)),
					new ItemEntry(Items.PUMPKIN_SEEDS, 10).apply(uniform(2.0F, 4.0F)),
					new ItemEntry(Items.BEETROOT_SEEDS, 10).apply(uniform(2.0F, 4.0F))),
			new LootPool(new ConstantRoll(3),
					new ItemEntry(Items.BONE, 10).apply(uniform(1.0F, 8.0F)),
					new ItemEntry(Items.GUNPOWDER, 10).apply(uniform(1.0F, 8.0F)),
					new ItemEntry(Items.ROTTEN_FLESH, 10).apply(uniform(1.0F, 8.0F)),
					new ItemEntry(Items.STRING, 10).apply(uniform(1.0F, 8.0F)))
	);

	public static final LootTable SPAWN_BONUS_CHEST_CHEST = new LootTable(
			new LootPool(new ConstantRoll(1),
					new ItemEntry(Items.STONE_AXE),
					new ItemEntry(Items.WOODEN_AXE, 3)),
			new LootPool(new ConstantRoll(1),
					new ItemEntry(Items.STONE_PICKAXE),
					new ItemEntry(Items.WOODEN_PICKAXE, 3)),
			new LootPool(new ConstantRoll(3),
					new ItemEntry(Items.APPLE, 5).apply(uniform(1.0F, 2.0F)),
					new ItemEntry(Items.BREAD, 3).apply(uniform(1.0F, 2.0F)),
					new ItemEntry(Items.SALMON, 3).apply(uniform(1.0F, 2.0F))),
			new LootPool(new ConstantRoll(4),
					new ItemEntry(Items.STICK, 10).apply(uniform(1.0F, 12.0F)),
					new ItemEntry(Items.OAK_PLANKS, 10).apply(uniform(1.0F, 12.0F)),
					new ItemEntry(Items.OAK_LOG, 3).apply(uniform(1.0F, 3.0F)),
					new ItemEntry(Items.SPRUCE_LOG, 3).apply(uniform(1.0F, 3.0F)),
					new ItemEntry(Items.BIRCH_LOG, 3).apply(uniform(1.0F, 3.0F)),
					new ItemEntry(Items.JUNGLE_LOG, 3).apply(uniform(1.0F, 3.0F)),
					new ItemEntry(Items.ACACIA_LOG, 3).apply(uniform(1.0F, 3.0F)),
					new ItemEntry(Items.DARK_OAK_LOG, 3).apply(uniform(1.0F, 3.0F)))
	);

	public static final LootTable STRONGHOLD_CORRIDOR_CHEST = new LootTable(
			new LootPool(new UniformRoll(2.0F, 3.0F),
					new ItemEntry(Items.ENDER_PEARL, 10),
					new ItemEntry(Items.DIAMOND, 3).apply(uniform(1.0F, 3.0F)),
					new ItemEntry(Items.IRON_INGOT, 10).apply(uniform(1.0F, 5.0F)),
					new ItemEntry(Items.GOLD_INGOT, 5).apply(uniform(1.0F, 3.0F)),
					new ItemEntry(Items.REDSTONE, 5).apply(uniform(4.0F, 9.0F)),
					new ItemEntry(Items.BREAD, 15).apply(uniform(1.0F, 3.0F)),
					new ItemEntry(Items.APPLE, 15).apply(uniform(1.0F, 3.0F)),
					new ItemEntry(Items.IRON_PICKAXE, 5),
					new ItemEntry(Items.IRON_SWORD, 5),
					new ItemEntry(Items.IRON_CHESTPLATE, 5),
					new ItemEntry(Items.IRON_HELMET, 5),
					new ItemEntry(Items.IRON_LEGGINGS, 5),
					new ItemEntry(Items.IRON_BOOTS, 5),
					new ItemEntry(Items.GOLDEN_APPLE),
					new ItemEntry(Items.SADDLE),
					new ItemEntry(Items.IRON_HORSE_ARMOR),
					new ItemEntry(Items.GOLDEN_HORSE_ARMOR),
					new ItemEntry(Items.DIAMOND_HORSE_ARMOR),
					new ItemEntry(Items.BOOK).apply(new EnchantWithLevelsFunction(30, 30, true)))
	);

	public static final LootTable STRONGHOLD_CROSSING_CHEST = new LootTable(
			new LootPool(new UniformRoll(1.0F, 4.0F),
					new ItemEntry(Items.IRON_INGOT, 10).apply(uniform(1.0F, 5.0F)),
					new ItemEntry(Items.GOLD_INGOT, 5).apply(uniform(1.0F, 3.0F)),
					new ItemEntry(Items.REDSTONE, 5).apply(uniform(4.0F, 9.0F)),
					new ItemEntry(Items.COAL, 10).apply(uniform(3.0F, 8.0F)),
					new ItemEntry(Items.BREAD, 15).apply(uniform(1.0F, 3.0F)),
					new ItemEntry(Items.APPLE, 15).apply(uniform(1.0F, 3.0F)),
					new ItemEntry(Items.IRON_PICKAXE),
					new ItemEntry(Items.BOOK) /* enchant_with_levels */)
	);

	public static final LootTable STRONGHOLD_LIBRARY_CHEST = new LootTable(
			new LootPool(new UniformRoll(2.0F, 10.0F),
					new ItemEntry(Items.BOOK, 20).apply(uniform(1.0F, 3.0F)),
					new ItemEntry(Items.PAPER, 20).apply(uniform(2.0F, 7.0F)),
					new ItemEntry(Items.MAP),
					new ItemEntry(Items.COMPASS),
					new ItemEntry(Items.BOOK, 10) /* enchant_with_levels */)
	);

	public static final LootTable UNDERWATER_RUIN_BIG_CHEST = new LootTable(
			new LootPool(new UniformRoll(2.0F, 8.0F),
					new ItemEntry(Items.COAL, 10).apply(uniform(1.0F, 4.0F)),
					new ItemEntry(Items.GOLD_NUGGET, 10).apply(uniform(1.0F, 3.0F)),
					new ItemEntry(Items.EMERALD),
					new ItemEntry(Items.WHEAT, 10).apply(uniform(2.0F, 3.0F))),
			new LootPool(new ConstantRoll(1),
					new ItemEntry(Items.GOLDEN_APPLE),
					new ItemEntry(Items.ENCHANTED_BOOK, 5) /* enchant_randomly */,
					new ItemEntry(Items.LEATHER_CHESTPLATE),
					new ItemEntry(Items.GOLDEN_HELMET),
					new ItemEntry(Items.FISHING_ROD, 5) /* enchant_randomly */,
					new ItemEntry(Items.MAP, 10) /* exploration_map*/)
	);

	public static final LootTable UNDERWATER_RUIN_SMALL_CHEST = new LootTable(
			new LootPool(new UniformRoll(2.0F, 8.0F),
					new ItemEntry(Items.COAL, 10).apply(uniform(1.0F, 4.0F)),
					new ItemEntry(Items.STONE_AXE, 2),
					new ItemEntry(Items.ROTTEN_FLESH, 5),
					new ItemEntry(Items.EMERALD),
					new ItemEntry(Items.WHEAT, 10).apply(uniform(2.0F, 3.0F))),
			new LootPool(new ConstantRoll(1),
					new ItemEntry(Items.LEATHER_CHESTPLATE),
					new ItemEntry(Items.GOLDEN_HELMET),
					new ItemEntry(Items.FISHING_ROD, 5) /* enchant_randomly */,
					new ItemEntry(Items.MAP, 5) /* exploration_map*/)
	);

	public static final LootTable VILLAGE_ARMORER_CHEST = new LootTable(
			new LootPool(new UniformRoll(1.0F, 5.0F),
					new ItemEntry(Items.IRON_INGOT, 2).apply(uniform(1.0F, 3.0F)),
					new ItemEntry(Items.BREAD, 4).apply(uniform(1.0F, 4.0F)),
					new ItemEntry(Items.IRON_HELMET),
					new ItemEntry(Items.EMERALD))
	);

	public static final LootTable VILLAGE_BUTCHER_CHEST = new LootTable(
			new LootPool(new UniformRoll(1.0F, 5.0F),
					new ItemEntry(Items.EMERALD),
					new ItemEntry(Items.PORKCHOP, 6).apply(uniform(1.0F, 3.0F)),
					new ItemEntry(Items.WHEAT, 6).apply(uniform(1.0F, 3.0F)),
					new ItemEntry(Items.BEEF, 6).apply(uniform(1.0F, 3.0F)),
					new ItemEntry(Items.MUTTON, 6).apply(uniform(1.0F, 3.0F)),
					new ItemEntry(Items.COAL, 3).apply(uniform(1.0F, 3.0F)))
	);

	public static final LootTable VILLAGE_CARTOGRAPHER_CHEST = new LootTable(
			new LootPool(new UniformRoll(1.0F, 5.0F),
					new ItemEntry(Items.MAP, 10).apply(uniform(1.0F, 3.0F)),
					new ItemEntry(Items.PAPER, 15).apply(uniform(1.0F, 5.0F)),
					new ItemEntry(Items.COMPASS, 5),
					new ItemEntry(Items.BREAD, 15).apply(uniform(1.0F, 4.0F)),
					new ItemEntry(Items.STICK, 5).apply(uniform(1.0F, 2.0F)))
	);

	public static final LootTable VILLAGE_DESERT_HOUSE_CHEST = new LootTable(
			new LootPool(new UniformRoll(3.0F, 8.0F),
					new ItemEntry(Items.CLAY_BALL),
					new ItemEntry(Items.GREEN_DYE),
					new ItemEntry(Items.CACTUS, 10).apply(uniform(1.0F, 4.0F)),
					new ItemEntry(Items.WHEAT, 10).apply(uniform(1.0F, 7.0F)),
					new ItemEntry(Items.BREAD, 10).apply(uniform(1.0F, 4.0F)),
					new ItemEntry(Items.BOOK),
					new ItemEntry(Items.DEAD_BUSH, 2).apply(uniform(1.0F, 3.0F)),
					new ItemEntry(Items.EMERALD).apply(uniform(1.0F, 3.0F)))
	);

	public static final LootTable VILLAGE_FISHER_CHEST = new LootTable(
			new LootPool(new UniformRoll(1.0F, 5.0F),
					new ItemEntry(Items.EMERALD),
					new ItemEntry(Items.COD, 2).apply(uniform(1.0F, 3.0F)),
					new ItemEntry(Items.SALMON).apply(uniform(1.0F, 3.0F)),
					new ItemEntry(Items.WATER_BUCKET).apply(uniform(1.0F, 3.0F)),
					new ItemEntry(Items.BARREL).apply(uniform(1.0F, 3.0F)),
					new ItemEntry(Items.WHEAT_SEEDS, 3).apply(uniform(1.0F, 3.0F)),
					new ItemEntry(Items.COAL, 2).apply(uniform(1.0F, 3.0F)))
	);

	public static final LootTable VILLAGE_FLETCHER_CHEST = new LootTable(
			new LootPool(new UniformRoll(1.0F, 5.0F),
					new ItemEntry(Items.EMERALD),
					new ItemEntry(Items.ARROW, 2).apply(uniform(1.0F, 3.0F)),
					new ItemEntry(Items.FEATHER, 6).apply(uniform(1.0F, 3.0F)),
					new ItemEntry(Items.EGG, 2).apply(uniform(1.0F, 3.0F)),
					new ItemEntry(Items.FLINT, 6).apply(uniform(1.0F, 3.0F)),
					new ItemEntry(Items.STICK, 6).apply(uniform(1.0F, 3.0F)))
	);

	public static final LootTable VILLAGE_MASON_CHEST = new LootTable(
			new LootPool(new UniformRoll(1.0F, 5.0F),
					new ItemEntry(Items.CLAY_BALL).apply(uniform(1.0F, 3.0F)),
					new ItemEntry(Items.FLOWER_POT),
					new ItemEntry(Items.STONE, 2),
					new ItemEntry(Items.STONE_BRICKS, 2),
					new ItemEntry(Items.BREAD, 4).apply(uniform(1.0F, 4.0F)),
					new ItemEntry(Items.YELLOW_DYE),
					new ItemEntry(Items.SMOOTH_STONE),
					new ItemEntry(Items.EMERALD))
	);

	public static final LootTable VILLAGE_PLAINS_HOUSE_CHEST = new LootTable(
			new LootPool(new UniformRoll(3.0F, 8.0F),
					new ItemEntry(Items.GOLD_NUGGET).apply(uniform(1.0F, 3.0F)),
					new ItemEntry(Items.DANDELION, 2),
					new ItemEntry(Items.POPPY),
					new ItemEntry(Items.POTATO, 10).apply(uniform(1.0F, 7.0F)),
					new ItemEntry(Items.BREAD, 10).apply(uniform(1.0F, 4.0F)),
					new ItemEntry(Items.APPLE, 10).apply(uniform(1.0F, 5.0F)),
					new ItemEntry(Items.BOOK),
					new ItemEntry(Items.FEATHER),
					new ItemEntry(Items.EMERALD, 2).apply(uniform(1.0F, 4.0F)),
					new ItemEntry(Items.OAK_SAPLING, 5).apply(uniform(1.0F, 2.0F)))
	);

	public static final LootTable VILLAGE_SAVANNA_HOUSE_CHEST = new LootTable(
			new LootPool(new UniformRoll(3.0F, 8.0F),
					new ItemEntry(Items.GOLD_NUGGET).apply(uniform(1.0F, 3.0F)),
					new ItemEntry(Items.GRASS, 5),
					new ItemEntry(Items.TALL_GRASS, 5),
					new ItemEntry(Items.BREAD, 10).apply(uniform(1.0F, 4.0F)),
					new ItemEntry(Items.WHEAT_SEEDS, 10).apply(uniform(1.0F, 5.0F)),
					new ItemEntry(Items.EMERALD, 2).apply(uniform(1.0F, 4.0F)),
					new ItemEntry(Items.ACACIA_SAPLING, 10).apply(uniform(1.0F, 2.0F)),
					new ItemEntry(Items.SADDLE),
					new ItemEntry(Items.TORCH).apply(uniform(1.0F, 2.0F)),
					new ItemEntry(Items.BUCKET))
	);

	public static final LootTable VILLAGE_SHEPHERD_CHEST = new LootTable(
			new LootPool(new UniformRoll(1.0F, 5.0F),
					new ItemEntry(Items.WHITE_WOOL, 6).apply(uniform(1.0F, 8.0F)),
					new ItemEntry(Items.BLACK_WOOL, 3).apply(uniform(1.0F, 3.0F)),
					new ItemEntry(Items.GRAY_WOOL, 2).apply(uniform(1.0F, 3.0F)),
					new ItemEntry(Items.BROWN_WOOL, 2).apply(uniform(1.0F, 3.0F)),
					new ItemEntry(Items.LIGHT_GRAY_WOOL, 2).apply(uniform(1.0F, 3.0F)),
					new ItemEntry(Items.EMERALD),
					new ItemEntry(Items.SHEARS),
					new ItemEntry(Items.WHEAT, 6).apply(uniform(1.0F, 6.0F)))
	);

	public static final LootTable VILLAGE_SNOWY_HOUSE_CHEST = new LootTable(
			new LootPool(new UniformRoll(3.0F, 8.0F),
					new ItemEntry(Items.BLUE_ICE),
					new ItemEntry(Items.SNOW_BLOCK, 4),
					new ItemEntry(Items.POTATO, 10).apply(uniform(1.0F, 7.0F)),
					new ItemEntry(Items.BREAD, 10).apply(uniform(1.0F, 4.0F)),
					new ItemEntry(Items.BEETROOT_SEEDS, 10).apply(uniform(1.0F, 5.0F)),
					new ItemEntry(Items.BEETROOT_SOUP),
					new ItemEntry(Items.FURNACE),
					new ItemEntry(Items.EMERALD).apply(uniform(1.0F, 4.0F)),
					new ItemEntry(Items.SNOWBALL, 10).apply(uniform(1.0F, 7.0F)),
					new ItemEntry(Items.COAL, 5).apply(uniform(1.0F, 4.0F)))
	);

	public static final LootTable VILLAGE_TAIGA_HOUSE_CHEST = new LootTable(
			new LootPool(new UniformRoll(3.0F, 8.0F),
					new ItemEntry(Items.IRON_NUGGET).apply(uniform(1.0F, 5.0F)),
					new ItemEntry(Items.FERN, 2),
					new ItemEntry(Items.LARGE_FERN, 2),
					new ItemEntry(Items.POTATO, 10).apply(uniform(1.0F, 7.0F)),
					new ItemEntry(Items.SWEET_BERRIES, 5).apply(uniform(1.0F, 7.0F)),
					new ItemEntry(Items.BREAD, 10).apply(uniform(1.0F, 4.0F)),
					new ItemEntry(Items.PUMPKIN_SEEDS, 5).apply(uniform(1.0F, 5.0F)),
					new ItemEntry(Items.PUMPKIN_PIE),
					new ItemEntry(Items.EMERALD, 2).apply(uniform(1.0F, 4.0F)),
					new ItemEntry(Items.SPRUCE_SAPLING, 5).apply(uniform(1.0F, 5.0F)),
					new ItemEntry(Items.SPRUCE_SIGN),
					new ItemEntry(Items.SPRUCE_LOG, 10).apply(uniform(1.0F, 5.0F)))
	);

	public static final LootTable VILLAGE_TANNERY_CHEST = new LootTable(
			new LootPool(new UniformRoll(1.0F, 5.0F),
					new ItemEntry(Items.LEATHER).apply(uniform(1.0F, 3.0F)),
					new ItemEntry(Items.LEATHER_CHESTPLATE, 2),
					new ItemEntry(Items.LEATHER_BOOTS, 2),
					new ItemEntry(Items.LEATHER_HELMET, 2),
					new ItemEntry(Items.BREAD, 5).apply(uniform(1.0F, 4.0F)),
					new ItemEntry(Items.LEATHER_LEGGINGS, 2),
					new ItemEntry(Items.SADDLE),
					new ItemEntry(Items.EMERALD).apply(uniform(1.0F, 4.0F)))
	);

	public static final LootTable VILLAGE_TEMPLE_CHEST = new LootTable(
			new LootPool(new UniformRoll(3.0F, 8.0F),
					new ItemEntry(Items.REDSTONE, 2).apply(uniform(1.0F, 4.0F)),
					new ItemEntry(Items.BREAD, 7).apply(uniform(1.0F, 4.0F)),
					new ItemEntry(Items.ROTTEN_FLESH, 7).apply(uniform(1.0F, 4.0F)),
					new ItemEntry(Items.LAPIS_LAZULI).apply(uniform(1.0F, 4.0F)),
					new ItemEntry(Items.GOLD_INGOT).apply(uniform(1.0F, 4.0F)),
					new ItemEntry(Items.EMERALD).apply(uniform(1.0F, 4.0F)))
	);

	public static final LootTable VILLAGE_TOOLSMITH_CHEST = new LootTable(
			new LootPool(new UniformRoll(3.0F, 8.0F),
					new ItemEntry(Items.DIAMOND).apply(uniform(1.0F, 3.0F)),
					new ItemEntry(Items.IRON_INGOT, 5).apply(uniform(1.0F, 5.0F)),
					new ItemEntry(Items.GOLD_INGOT).apply(uniform(1.0F, 3.0F)),
					new ItemEntry(Items.BREAD, 15).apply(uniform(1.0F, 3.0F)),
					new ItemEntry(Items.IRON_PICKAXE, 5),
					new ItemEntry(Items.COAL).apply(uniform(1.0F, 3.0F)),
					new ItemEntry(Items.STICK, 20).apply(uniform(1.0F, 3.0F)),
					new ItemEntry(Items.IRON_SHOVEL, 5))
	);

	public static final LootTable VILLAGE_WEAPONSMITH_CHEST = new LootTable(
			new LootPool(new UniformRoll(3.0F, 8.0F),
					new ItemEntry(Items.DIAMOND, 3).apply(uniform(1.0F, 3.0F)),
					new ItemEntry(Items.IRON_INGOT, 10).apply(uniform(1.0F, 5.0F)),
					new ItemEntry(Items.GOLD_INGOT, 5).apply(uniform(1.0F, 3.0F)),
					new ItemEntry(Items.BREAD, 15).apply(uniform(1.0F, 3.0F)),
					new ItemEntry(Items.APPLE, 15).apply(uniform(1.0F, 3.0F)),
					new ItemEntry(Items.IRON_PICKAXE, 5),
					new ItemEntry(Items.IRON_SWORD, 5),
					new ItemEntry(Items.IRON_CHESTPLATE, 5),
					new ItemEntry(Items.IRON_HELMET, 5),
					new ItemEntry(Items.IRON_LEGGINGS, 5),
					new ItemEntry(Items.IRON_BOOTS, 5),
					new ItemEntry(Items.OBSIDIAN, 5).apply(uniform(3.0F, 7.0F)),
					new ItemEntry(Items.OAK_SAPLING, 5).apply(uniform(3.0F, 7.0F)),
					new ItemEntry(Items.SADDLE, 3),
					new ItemEntry(Items.IRON_HORSE_ARMOR),
					new ItemEntry(Items.GOLDEN_HORSE_ARMOR),
					new ItemEntry(Items.DIAMOND_HORSE_ARMOR))
	);

	public static final LootTable WOODLAND_MANSION_CHEST = new LootTable(
			new LootPool(new UniformRoll(1.0F, 3.0F),
					new ItemEntry(Items.LEAD, 20),
					new ItemEntry(Items.GOLDEN_APPLE, 15),
					new ItemEntry(Items.ENCHANTED_GOLDEN_APPLE, 2),
					new ItemEntry(Items.MUSIC_DISC_13, 15),
					new ItemEntry(Items.MUSIC_DISC_CAT, 15),
					new ItemEntry(Items.NAME_TAG, 20),
					new ItemEntry(Items.CHAINMAIL_CHESTPLATE, 10),
					new ItemEntry(Items.DIAMOND_HOE, 15),
					new ItemEntry(Items.DIAMOND_CHESTPLATE, 5),
					new ItemEntry(Items.ENCHANTED_BOOK, 10).apply(new EnchantRandomlyFunction())),
			new LootPool(new UniformRoll(1.0F, 4.0F),
					new ItemEntry(Items.IRON_INGOT, 10).apply(uniform(1.0F, 4.0F)),
					new ItemEntry(Items.GOLD_INGOT, 5).apply(uniform(1.0F, 4.0F)),
					new ItemEntry(Items.BREAD, 20),
					new ItemEntry(Items.WHEAT, 20).apply(uniform(1.0F, 4.0F)),
					new ItemEntry(Items.BUCKET, 10),
					new ItemEntry(Items.REDSTONE, 15).apply(uniform(1.0F, 4.0F)),
					new ItemEntry(Items.COAL, 15).apply(uniform(1.0F, 4.0F)),
					new ItemEntry(Items.MELON_SEEDS, 10).apply(uniform(2.0F, 4.0F)),
					new ItemEntry(Items.PUMPKIN_SEEDS, 10).apply(uniform(2.0F, 4.0F)),
					new ItemEntry(Items.BEETROOT_SEEDS, 10).apply(uniform(2.0F, 4.0F))),
			new LootPool(new ConstantRoll(3),
					new ItemEntry(Items.BONE, 10).apply(uniform(1.0F, 8.0F)),
					new ItemEntry(Items.GUNPOWDER, 10).apply(uniform(1.0F, 8.0F)),
					new ItemEntry(Items.ROTTEN_FLESH, 10).apply(uniform(1.0F, 8.0F)),
					new ItemEntry(Items.STRING, 10).apply(uniform(1.0F, 8.0F)))
	);

}
