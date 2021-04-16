package kaptainwutax.featureutils.loot;

import kaptainwutax.featureutils.loot.effect.Effects;
import kaptainwutax.featureutils.loot.entry.EmptyEntry;
import kaptainwutax.featureutils.loot.entry.ItemEntry;
import kaptainwutax.featureutils.loot.function.ApplyDamageFunction;
import kaptainwutax.featureutils.loot.function.EffectFunction;
import kaptainwutax.featureutils.loot.function.EnchantRandomlyFunction;
import kaptainwutax.featureutils.loot.function.EnchantWithLevelsFunction;
import kaptainwutax.featureutils.loot.item.Item;
import kaptainwutax.featureutils.loot.roll.ConstantRoll;
import kaptainwutax.featureutils.loot.roll.UniformRoll;

import static kaptainwutax.featureutils.loot.function.SetCountFunction.constant;
import static kaptainwutax.featureutils.loot.function.SetCountFunction.uniform;

public class MCLootTables {

    public static final LootTable ABANDONED_MINESHAFT_CHEST = new LootTable(
            new LootPool(new ConstantRoll(1),
                    new ItemEntry(Item.GOLDEN_APPLE, 20),
                    new ItemEntry(Item.ENCHANTED_GOLDEN_APPLE),
                    new ItemEntry(Item.NAME_TAG, 30),
                    new ItemEntry(Item.ENCHANTED_BOOK, 10) /* enchant_randomly */,
                    new ItemEntry(Item.IRON_PICKAXE, 5),
                    new EmptyEntry(5)),
            new LootPool(new UniformRoll(2.0F, 4.0F),
                    new ItemEntry(Item.IRON_INGOT, 10).apply(uniform(1.0F, 5.0F)),
                    new ItemEntry(Item.GOLD_INGOT, 5).apply(uniform(1.0F, 3.0F)),
                    new ItemEntry(Item.REDSTONE, 5).apply(uniform(4.0F, 9.0F)),
                    new ItemEntry(Item.LAPIS_LAZULI, 5).apply(uniform(4.0F, 9.0F)),
                    new ItemEntry(Item.DIAMOND, 3).apply(uniform(1.0F, 2.0F)),
                    new ItemEntry(Item.COAL, 10).apply(uniform(3.0F, 8.0F)),
                    new ItemEntry(Item.BREAD, 15).apply(uniform(1.0F, 3.0F)),
                    new ItemEntry(Item.MELON_SEEDS, 10).apply(uniform(2.0F, 4.0F)),
                    new ItemEntry(Item.PUMPKIN_SEEDS, 10).apply(uniform(2.0F, 4.0F)),
                    new ItemEntry(Item.BEETROOT_SEEDS, 10).apply(uniform(2.0F, 4.0F))),
            new LootPool(new ConstantRoll(3),
                    new ItemEntry(Item.RAIL, 20).apply(uniform(4.0F, 8.0F)),
                    new ItemEntry(Item.POWERED_RAIL, 5).apply(uniform(1.0F, 4.0F)),
                    new ItemEntry(Item.DETECTOR_RAIL, 5).apply(uniform(1.0F, 4.0F)),
                    new ItemEntry(Item.ACTIVATOR_RAIL, 5).apply(uniform(1.0F, 4.0F)),
                    new ItemEntry(Item.TORCH, 15).apply(uniform(1.0F, 16.0F)))
    );

    public static final LootTable BASTION_BRIDGE_CHEST = new LootTable(
            new LootPool(new ConstantRoll(1),
                    new ItemEntry(Item.LODESTONE).apply(constant(1))),
            new LootPool(new UniformRoll(1.0F, 2.0F),
                    new ItemEntry(Item.CROSSBOW) /* set_damage */ /* enchant_randomly */,
                    new ItemEntry(Item.SPECTRAL_ARROW).apply(uniform(2.0F, 12.0F)),
                    new ItemEntry(Item.GILDED_BLACKSTONE).apply(uniform(5.0F, 8.0F)),
                    new ItemEntry(Item.CRYING_OBSIDIAN).apply(uniform(3.0F, 8.0F)),
                    new ItemEntry(Item.GOLD_BLOCK).apply(constant(1)),
                    new ItemEntry(Item.GOLD_INGOT).apply(uniform(2.0F, 8.0F)),
                    new ItemEntry(Item.IRON_INGOT).apply(uniform(2.0F, 8.0F)),
                    new ItemEntry(Item.GOLDEN_SWORD).apply(constant(1)),
                    new ItemEntry(Item.GOLDEN_CHESTPLATE).apply(constant(1)) /* enchant_randomly */,
                    new ItemEntry(Item.GOLDEN_HELMET).apply(constant(1)) /* enchant_randomly */,
                    new ItemEntry(Item.GOLDEN_LEGGINGS).apply(constant(1)) /* enchant_randomly */,
                    new ItemEntry(Item.GOLDEN_BOOTS).apply(constant(1)) /* enchant_randomly */),
            new LootPool(new UniformRoll(2.0F, 4.0F),
                    new ItemEntry(Item.STRING).apply(uniform(1.0F, 6.0F)),
                    new ItemEntry(Item.LEATHER).apply(uniform(1.0F, 3.0F)),
                    new ItemEntry(Item.ARROW).apply(uniform(5.0F, 17.0F)),
                    new ItemEntry(Item.IRON_NUGGET).apply(uniform(2.0F, 6.0F)),
                    new ItemEntry(Item.GOLD_NUGGET).apply(uniform(2.0F, 6.0F)))
    );

    public static final LootTable BASTION_HOGLIN_STABLE_CHEST = new LootTable(
            new LootPool(new ConstantRoll(1),
                    new ItemEntry(Item.DIAMOND_SHOVEL, 5) /* set_damage */ /* enchant_randomly */,
                    new ItemEntry(Item.NETHERITE_SCRAP, 2).apply(constant(1)),
                    new ItemEntry(Item.ANCIENT_DEBRIS, 3).apply(constant(1)),
                    new ItemEntry(Item.SADDLE, 10).apply(constant(1)),
                    new ItemEntry(Item.GOLD_BLOCK, 25).apply(uniform(2.0F, 4.0F)),
                    new ItemEntry(Item.GOLDEN_HOE, 15).apply(constant(1)) /* enchant_randomly */,
                    new EmptyEntry(45)),
            new LootPool(new UniformRoll(3.0F, 4.0F),
                    new ItemEntry(Item.GLOWSTONE).apply(uniform(1.0F, 5.0F)),
                    new ItemEntry(Item.GILDED_BLACKSTONE).apply(uniform(1.0F, 5.0F)),
                    new ItemEntry(Item.SOUL_SAND).apply(uniform(2.0F, 7.0F)),
                    new ItemEntry(Item.CRIMSON_NYLIUM).apply(uniform(2.0F, 7.0F)),
                    new ItemEntry(Item.GOLD_NUGGET).apply(uniform(2.0F, 8.0F)),
                    new ItemEntry(Item.LEATHER).apply(uniform(1.0F, 3.0F)),
                    new ItemEntry(Item.ARROW).apply(uniform(5.0F, 17.0F)),
                    new ItemEntry(Item.STRING).apply(uniform(3.0F, 8.0F)),
                    new ItemEntry(Item.PORKCHOP).apply(uniform(2.0F, 5.0F)),
                    new ItemEntry(Item.COOKED_PORKCHOP).apply(uniform(2.0F, 5.0F)),
                    new ItemEntry(Item.CRIMSON_FUNGUS).apply(uniform(2.0F, 7.0F)),
                    new ItemEntry(Item.CRIMSON_ROOTS).apply(uniform(2.0F, 7.0F)))
    );

    public static final LootTable BASTION_OTHER_CHEST = new LootTable(
            new LootPool(new ConstantRoll(1),
                    new ItemEntry(Item.CROSSBOW, 12) /* set_damage */ /* enchant_randomly */,
                    new ItemEntry(Item.ANCIENT_DEBRIS, 2).apply(constant(1)),
                    new ItemEntry(Item.NETHERITE_SCRAP, 2).apply(constant(1)),
                    new ItemEntry(Item.SPECTRAL_ARROW, 16).apply(uniform(2.0F, 15.0F)),
                    new ItemEntry(Item.PIGLIN_BANNER_PATTERN, 5).apply(constant(1)),
                    new ItemEntry(Item.MUSIC_DISC_PIGSTEP, 3).apply(constant(1)),
                    new ItemEntry(Item.ENCHANTED_BOOK, 10) /* enchant_randomly */,
                    new EmptyEntry(50)),
            new LootPool(new ConstantRoll(2),
                    new ItemEntry(Item.GOLDEN_BOOTS).apply(constant(1)) /* enchant_randomly */,
                    new ItemEntry(Item.GOLD_BLOCK).apply(constant(1)),
                    new ItemEntry(Item.CROSSBOW).apply(constant(1)),
                    new ItemEntry(Item.GOLD_INGOT).apply(uniform(1.0F, 6.0F)),
                    new ItemEntry(Item.IRON_INGOT).apply(uniform(1.0F, 6.0F)),
                    new ItemEntry(Item.GOLDEN_SWORD).apply(constant(1)),
                    new ItemEntry(Item.GOLDEN_CHESTPLATE).apply(constant(1)),
                    new ItemEntry(Item.GOLDEN_HELMET).apply(constant(1)),
                    new ItemEntry(Item.GOLDEN_LEGGINGS).apply(constant(1)),
                    new ItemEntry(Item.GOLDEN_BOOTS).apply(constant(1)),
                    new EmptyEntry(2)),
            new LootPool(new UniformRoll(3.0F, 5.0F),
                    new ItemEntry(Item.CRYING_OBSIDIAN).apply(uniform(1.0F, 5.0F)),
                    new ItemEntry(Item.GILDED_BLACKSTONE).apply(uniform(1.0F, 5.0F)),
                    new ItemEntry(Item.CHAIN).apply(uniform(2.0F, 10.0F)),
                    new ItemEntry(Item.MAGMA_CREAM).apply(uniform(2.0F, 6.0F)),
                    new ItemEntry(Item.BONE_BLOCK).apply(uniform(3.0F, 6.0F)),
                    new ItemEntry(Item.IRON_NUGGET).apply(uniform(2.0F, 8.0F)),
                    new ItemEntry(Item.OBSIDIAN).apply(uniform(4.0F, 6.0F)),
                    new ItemEntry(Item.GOLD_NUGGET).apply(uniform(2.0F, 8.0F)),
                    new ItemEntry(Item.STRING).apply(uniform(4.0F, 6.0F)),
                    new ItemEntry(Item.ARROW, 2).apply(uniform(5.0F, 17.0F)))
    );

    public static final LootTable BASTION_TREASURE_CHEST = new LootTable(
            new LootPool(new UniformRoll(1.0F, 2.0F),
                    new ItemEntry(Item.NETHERITE_INGOT, 10).apply(constant(1)),
                    new ItemEntry(Item.ANCIENT_DEBRIS, 14).apply(constant(1)),
                    new ItemEntry(Item.NETHERITE_SCRAP, 10).apply(constant(1)),
                    new ItemEntry(Item.ANCIENT_DEBRIS).apply(constant(2)),
                    new ItemEntry(Item.DIAMOND_SWORD, 10).apply(new ApplyDamageFunction(), new EnchantRandomlyFunction()) /* set_damage */ /* enchant_randomly */,
                    new ItemEntry(Item.DIAMOND_CHESTPLATE, 6).apply(new ApplyDamageFunction(), new EnchantRandomlyFunction()) /* set_damage */ /* enchant_randomly */,
                    new ItemEntry(Item.DIAMOND_HELMET, 6).apply(new ApplyDamageFunction(), new EnchantRandomlyFunction()) /* set_damage */ /* enchant_randomly */,
                    new ItemEntry(Item.DIAMOND_LEGGINGS, 6).apply(new ApplyDamageFunction(), new EnchantRandomlyFunction()) /* set_damage */ /* enchant_randomly */,
                    new ItemEntry(Item.DIAMOND_BOOTS, 6).apply(new ApplyDamageFunction(), new EnchantRandomlyFunction()) /* set_damage */ /* enchant_randomly */,
                    new ItemEntry(Item.DIAMOND_SWORD, 6).apply(new ApplyDamageFunction()) /* set_damage */,
                    new ItemEntry(Item.DIAMOND_CHESTPLATE, 5).apply(new ApplyDamageFunction()) /* set_damage */,
                    new ItemEntry(Item.DIAMOND_HELMET, 5).apply(new ApplyDamageFunction()) /* set_damage */,
                    new ItemEntry(Item.DIAMOND_BOOTS, 5).apply(new ApplyDamageFunction()) /* set_damage */,
                    new ItemEntry(Item.DIAMOND_LEGGINGS, 5).apply(new ApplyDamageFunction()) /* set_damage */,
                    new ItemEntry(Item.DIAMOND, 5).apply(uniform(1.0F, 3.0F))),
            new LootPool(new UniformRoll(2.0F, 4.0F),
                    new ItemEntry(Item.SPECTRAL_ARROW).apply(uniform(5.0F, 21.0F)),
                    new ItemEntry(Item.GOLD_BLOCK).apply(uniform(2.0F, 5.0F)),
                    new ItemEntry(Item.GOLD_INGOT).apply(uniform(3.0F, 9.0F)),
                    new ItemEntry(Item.IRON_INGOT).apply(uniform(3.0F, 9.0F)),
                    new ItemEntry(Item.CRYING_OBSIDIAN).apply(uniform(1.0F, 5.0F)),
                    new ItemEntry(Item.QUARTZ).apply(uniform(8.0F, 23.0F)),
                    new ItemEntry(Item.GILDED_BLACKSTONE).apply(uniform(1.0F, 5.0F)),
                    new ItemEntry(Item.MAGMA_CREAM).apply(uniform(2.0F, 8.0F)),
                    new ItemEntry(Item.IRON_NUGGET).apply(uniform(8.0F, 16.0F)))
    );

    public static final LootTable BURIED_TREASURE_CHEST = new LootTable(
            new LootPool(new ConstantRoll(1),
                    new ItemEntry(Item.HEART_OF_THE_SEA)),
            new LootPool(new UniformRoll(5.0F, 8.0F),
                    new ItemEntry(Item.IRON_INGOT, 20).apply(uniform(1.0F, 4.0F)),
                    new ItemEntry(Item.GOLD_INGOT, 10).apply(uniform(1.0F, 4.0F)),
                    new ItemEntry(Item.TNT, 5).apply(uniform(1.0F, 2.0F))),
            new LootPool(new UniformRoll(1.0F, 3.0F),
                    new ItemEntry(Item.EMERALD, 5).apply(uniform(4.0F, 8.0F)),
                    new ItemEntry(Item.DIAMOND, 5).apply(uniform(1.0F, 2.0F)),
                    new ItemEntry(Item.PRISMARINE_CRYSTALS, 5).apply(uniform(1.0F, 5.0F))),
            new LootPool(new UniformRoll(0.0F, 1.0F),
                    new ItemEntry(Item.LEATHER_CHESTPLATE),
                    new ItemEntry(Item.IRON_SWORD)),
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
                    new ItemEntry(Item.ENCHANTED_BOOK, 20).apply(new EnchantRandomlyFunction()),
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

    public static final LootTable END_CITY_TREASURE_CHEST = new LootTable(
            new LootPool(new UniformRoll(2.0F, 6.0F),
                    new ItemEntry(Item.DIAMOND, 5).apply(uniform(2.0F, 7.0F)),
                    new ItemEntry(Item.IRON_INGOT, 10).apply(uniform(4.0F, 8.0F)),
                    new ItemEntry(Item.GOLD_INGOT, 15).apply(uniform(2.0F, 7.0F)),
                    new ItemEntry(Item.EMERALD, 2).apply(uniform(2.0F, 6.0F)),
                    new ItemEntry(Item.BEETROOT_SEEDS, 5).apply(uniform(1.0F, 10.0F)),
                    new ItemEntry(Item.SADDLE, 3),
                    new ItemEntry(Item.IRON_HORSE_ARMOR),
                    new ItemEntry(Item.GOLDEN_HORSE_ARMOR),
                    new ItemEntry(Item.DIAMOND_HORSE_ARMOR),
                    new ItemEntry(Item.DIAMOND_SWORD, 3) /* enchant_with_levels */,
                    new ItemEntry(Item.DIAMOND_BOOTS, 3) /* enchant_with_levels */,
                    new ItemEntry(Item.DIAMOND_CHESTPLATE, 3) /* enchant_with_levels */,
                    new ItemEntry(Item.DIAMOND_LEGGINGS, 3) /* enchant_with_levels */,
                    new ItemEntry(Item.DIAMOND_HELMET, 3) /* enchant_with_levels */,
                    new ItemEntry(Item.DIAMOND_PICKAXE, 3) /* enchant_with_levels */,
                    new ItemEntry(Item.DIAMOND_SHOVEL, 3) /* enchant_with_levels */,
                    new ItemEntry(Item.IRON_SWORD, 3) /* enchant_with_levels */,
                    new ItemEntry(Item.IRON_BOOTS, 3) /* enchant_with_levels */,
                    new ItemEntry(Item.IRON_CHESTPLATE, 3) /* enchant_with_levels */,
                    new ItemEntry(Item.IRON_LEGGINGS, 3) /* enchant_with_levels */,
                    new ItemEntry(Item.IRON_HELMET, 3) /* enchant_with_levels */,
                    new ItemEntry(Item.IRON_PICKAXE, 3) /* enchant_with_levels */,
                    new ItemEntry(Item.IRON_SHOVEL, 3) /* enchant_with_levels */)
    );

    public static final LootTable IGLOO_CHEST_CHEST = new LootTable(
            new LootPool(new UniformRoll(2.0F, 8.0F),
                    new ItemEntry(Item.APPLE, 15).apply(uniform(1.0F, 3.0F)),
                    new ItemEntry(Item.COAL, 15).apply(uniform(1.0F, 4.0F)),
                    new ItemEntry(Item.GOLD_NUGGET, 10).apply(uniform(1.0F, 3.0F)),
                    new ItemEntry(Item.STONE_AXE, 2),
                    new ItemEntry(Item.ROTTEN_FLESH, 10),
                    new ItemEntry(Item.EMERALD),
                    new ItemEntry(Item.WHEAT, 10).apply(uniform(2.0F, 3.0F))),
            new LootPool(new ConstantRoll(1),
                    new ItemEntry(Item.GOLDEN_APPLE))
    );

    public static final LootTable JUNGLE_TEMPLE_CHEST = new LootTable(
            new LootPool(new UniformRoll(2.0F, 6.0F),
                    new ItemEntry(Item.DIAMOND, 3).apply(uniform(1.0F, 3.0F)),
                    new ItemEntry(Item.IRON_INGOT, 10).apply(uniform(1.0F, 5.0F)),
                    new ItemEntry(Item.GOLD_INGOT, 15).apply(uniform(2.0F, 7.0F)),
                    new ItemEntry(Item.BAMBOO, 15).apply(uniform(1.0F, 3.0F)),
                    new ItemEntry(Item.EMERALD, 2).apply(uniform(1.0F, 3.0F)),
                    new ItemEntry(Item.BONE, 20).apply(uniform(4.0F, 6.0F)),
                    new ItemEntry(Item.ROTTEN_FLESH, 16).apply(uniform(3.0F, 7.0F)),
                    new ItemEntry(Item.SADDLE, 3),
                    new ItemEntry(Item.IRON_HORSE_ARMOR),
                    new ItemEntry(Item.GOLDEN_HORSE_ARMOR),
                    new ItemEntry(Item.DIAMOND_HORSE_ARMOR),
                    new ItemEntry(Item.BOOK).apply(new EnchantWithLevelsFunction(30, 30, true)))
    );

    public static final LootTable JUNGLE_TEMPLE_DISPENSER_CHEST = new LootTable(
            new LootPool(new UniformRoll(1.0F, 2.0F),
                    new ItemEntry(Item.ARROW, 30).apply(uniform(2.0F, 7.0F)))
    );

    public static final LootTable NETHER_BRIDGE_CHEST = new LootTable(
            new LootPool(new UniformRoll(2.0F, 4.0F),
                    new ItemEntry(Item.DIAMOND, 5).apply(uniform(1.0F, 3.0F)),
                    new ItemEntry(Item.IRON_INGOT, 5).apply(uniform(1.0F, 5.0F)),
                    new ItemEntry(Item.GOLD_INGOT, 15).apply(uniform(1.0F, 3.0F)),
                    new ItemEntry(Item.GOLDEN_SWORD, 5),
                    new ItemEntry(Item.GOLDEN_CHESTPLATE, 5),
                    new ItemEntry(Item.FLINT_AND_STEEL, 5),
                    new ItemEntry(Item.NETHER_WART, 5).apply(uniform(3.0F, 7.0F)),
                    new ItemEntry(Item.SADDLE, 10),
                    new ItemEntry(Item.GOLDEN_HORSE_ARMOR, 8),
                    new ItemEntry(Item.IRON_HORSE_ARMOR, 5),
                    new ItemEntry(Item.DIAMOND_HORSE_ARMOR, 3),
                    new ItemEntry(Item.OBSIDIAN, 2).apply(uniform(2.0F, 4.0F)))
    );

    public static final LootTable PILLAGER_OUTPOST_CHEST = new LootTable(
            new LootPool(new UniformRoll(0.0F, 1.0F),
                    new ItemEntry(Item.CROSSBOW)),
            new LootPool(new UniformRoll(2.0F, 3.0F),
                    new ItemEntry(Item.WHEAT, 7).apply(uniform(3.0F, 5.0F)),
                    new ItemEntry(Item.POTATO, 5).apply(uniform(2.0F, 5.0F)),
                    new ItemEntry(Item.CARROT, 5).apply(uniform(3.0F, 5.0F))),
            new LootPool(new UniformRoll(1.0F, 3.0F),
                    new ItemEntry(Item.DARK_OAK_LOG).apply(uniform(2.0F, 3.0F))),
            new LootPool(new UniformRoll(2.0F, 3.0F),
                    new ItemEntry(Item.EXPERIENCE_BOTTLE, 7),
                    new ItemEntry(Item.STRING, 4).apply(uniform(1.0F, 6.0F)),
                    new ItemEntry(Item.ARROW, 4).apply(uniform(2.0F, 7.0F)),
                    new ItemEntry(Item.TRIPWIRE_HOOK, 3).apply(uniform(1.0F, 3.0F)),
                    new ItemEntry(Item.IRON_INGOT, 3).apply(uniform(1.0F, 3.0F)),
                    new ItemEntry(Item.ENCHANTED_BOOK) /* enchant_randomly */)
    );

    public static final LootTable RUINED_PORTAL_CHEST = new LootTable(
            new LootPool(new UniformRoll(4.0F, 8.0F),
                    new ItemEntry(Item.OBSIDIAN, 40).apply(uniform(1.0F, 2.0F)),
                    new ItemEntry(Item.FLINT, 40).apply(uniform(1.0F, 4.0F)),
                    new ItemEntry(Item.IRON_NUGGET, 40).apply(uniform(9.0F, 18.0F)),
                    new ItemEntry(Item.FLINT_AND_STEEL, 40),
                    new ItemEntry(Item.FIRE_CHARGE, 40),
                    new ItemEntry(Item.GOLDEN_APPLE, 15),
                    new ItemEntry(Item.GOLD_NUGGET, 15).apply(uniform(4.0F, 24.0F)),
                    new ItemEntry(Item.GOLDEN_SWORD, 15).apply(new EnchantRandomlyFunction(true)),
                    new ItemEntry(Item.GOLDEN_AXE, 15).apply(new EnchantRandomlyFunction(true)),
                    new ItemEntry(Item.GOLDEN_HOE, 15).apply(new EnchantRandomlyFunction(true)),
                    new ItemEntry(Item.GOLDEN_SHOVEL, 15).apply(new EnchantRandomlyFunction(true)),
                    new ItemEntry(Item.GOLDEN_PICKAXE, 15).apply(new EnchantRandomlyFunction(true)),
                    new ItemEntry(Item.GOLDEN_BOOTS, 15).apply(new EnchantRandomlyFunction(true)),
                    new ItemEntry(Item.GOLDEN_CHESTPLATE, 15).apply(new EnchantRandomlyFunction(true)),
                    new ItemEntry(Item.GOLDEN_HELMET, 15).apply(new EnchantRandomlyFunction(true)),
                    new ItemEntry(Item.GOLDEN_LEGGINGS, 15).apply(new EnchantRandomlyFunction(true)),
                    new ItemEntry(Item.GLISTERING_MELON_SLICE, 5).apply(uniform(4.0F, 12.0F)),
                    new ItemEntry(Item.GOLDEN_HORSE_ARMOR, 5),
                    new ItemEntry(Item.LIGHT_WEIGHTED_PRESSURE_PLATE, 5),
                    new ItemEntry(Item.GOLDEN_CARROT, 5).apply(uniform(4.0F, 12.0F)),
                    new ItemEntry(Item.CLOCK, 5),
                    new ItemEntry(Item.GOLD_INGOT, 5).apply(uniform(2.0F, 8.0F)),
                    new ItemEntry(Item.BELL),
                    new ItemEntry(Item.ENCHANTED_GOLDEN_APPLE),
                    new ItemEntry(Item.GOLD_BLOCK).apply(uniform(1.0F, 2.0F)))
    );

    public static final LootTable SHIPWRECK_MAP_CHEST = new LootTable(
            new LootPool(new ConstantRoll(1),
                    new ItemEntry(Item.MAP) /* exploration_map*/),
            new LootPool(new ConstantRoll(3),
                    new ItemEntry(Item.COMPASS),
                    new ItemEntry(Item.MAP),
                    new ItemEntry(Item.CLOCK),
                    new ItemEntry(Item.PAPER, 20).apply(uniform(1.0F, 10.0F)),
                    new ItemEntry(Item.FEATHER, 10).apply(uniform(1.0F, 5.0F)),
                    new ItemEntry(Item.BOOK, 5).apply(uniform(1.0F, 5.0F)))
    );

    public static final LootTable SHIPWRECK_SUPPLY_CHEST = new LootTable(
            new LootPool(new UniformRoll(3.0F, 10.0F),
                    new ItemEntry(Item.PAPER, 8).apply(uniform(1.0F, 12.0F)),
                    new ItemEntry(Item.POTATO, 7).apply(uniform(2.0F, 6.0F)),
                    new ItemEntry(Item.POISONOUS_POTATO, 7).apply(uniform(2.0F, 6.0F)),
                    new ItemEntry(Item.CARROT, 7).apply(uniform(4.0F, 8.0F)),
                    new ItemEntry(Item.WHEAT, 7).apply(uniform(8.0F, 21.0F)),
                    new ItemEntry(Item.SUSPICIOUS_STEW, 10).apply(
                            EffectFunction.builder()
                                    .apply(Effects.NIGHT_VISION, 7.0F, 10.0F)
                                    .apply(Effects.JUMP, 7.0F, 10.0F)
                                    .apply(Effects.WEAKNESS, 6.0F, 8.0F)
                                    .apply(Effects.BLINDNESS, 5.0F, 7.0F)
                                    .apply(Effects.POISON, 10.0F, 20.0F)
                                    .apply(Effects.SATURATION, 7.0F, 10.0F)),
                    new ItemEntry(Item.COAL, 6).apply(uniform(2.0F, 8.0F)),
                    new ItemEntry(Item.ROTTEN_FLESH, 5).apply(uniform(5.0F, 24.0F)),
                    new ItemEntry(Item.PUMPKIN, 2).apply(uniform(1.0F, 3.0F)),
                    new ItemEntry(Item.BAMBOO, 2).apply(uniform(1.0F, 3.0F)),
                    new ItemEntry(Item.GUNPOWDER, 3).apply(uniform(1.0F, 5.0F)),
                    new ItemEntry(Item.TNT).apply(uniform(1.0F, 2.0F)),
                    new ItemEntry(Item.LEATHER_HELMET, 3).apply(new EnchantRandomlyFunction()),
                    new ItemEntry(Item.LEATHER_CHESTPLATE, 3).apply(new EnchantRandomlyFunction()),
                    new ItemEntry(Item.LEATHER_LEGGINGS, 3).apply(new EnchantRandomlyFunction()),
                    new ItemEntry(Item.LEATHER_BOOTS, 3).apply(new EnchantRandomlyFunction()))
    );

    public static final LootTable SHIPWRECK_TREASURE_CHEST = new LootTable(
            new LootPool(new UniformRoll(3.0F, 6.0F),
                    new ItemEntry(Item.IRON_INGOT, 90).apply(uniform(1.0F, 5.0F)),
                    new ItemEntry(Item.GOLD_INGOT, 10).apply(uniform(1.0F, 5.0F)),
                    new ItemEntry(Item.EMERALD, 40).apply(uniform(1.0F, 5.0F)),
                    new ItemEntry(Item.DIAMOND, 5),
                    new ItemEntry(Item.EXPERIENCE_BOTTLE, 5)),
            new LootPool(new UniformRoll(2.0F, 5.0F),
                    new ItemEntry(Item.IRON_NUGGET, 50).apply(uniform(1.0F, 10.0F)),
                    new ItemEntry(Item.GOLD_NUGGET, 10).apply(uniform(1.0F, 10.0F)),
                    new ItemEntry(Item.LAPIS_LAZULI, 20).apply(uniform(1.0F, 10.0F)))
    );

    public static final LootTable SIMPLE_DUNGEON_CHEST = new LootTable(
            new LootPool(new UniformRoll(1.0F, 3.0F),
                    new ItemEntry(Item.SADDLE, 20),
                    new ItemEntry(Item.GOLDEN_APPLE, 15),
                    new ItemEntry(Item.ENCHANTED_GOLDEN_APPLE, 2),
                    new ItemEntry(Item.MUSIC_DISC_13, 15),
                    new ItemEntry(Item.MUSIC_DISC_CAT, 15),
                    new ItemEntry(Item.NAME_TAG, 20),
                    new ItemEntry(Item.GOLDEN_HORSE_ARMOR, 10),
                    new ItemEntry(Item.IRON_HORSE_ARMOR, 15),
                    new ItemEntry(Item.DIAMOND_HORSE_ARMOR, 5),
                    new ItemEntry(Item.ENCHANTED_BOOK, 10) /* enchant_randomly */),
            new LootPool(new UniformRoll(1.0F, 4.0F),
                    new ItemEntry(Item.IRON_INGOT, 10).apply(uniform(1.0F, 4.0F)),
                    new ItemEntry(Item.GOLD_INGOT, 5).apply(uniform(1.0F, 4.0F)),
                    new ItemEntry(Item.BREAD, 20),
                    new ItemEntry(Item.WHEAT, 20).apply(uniform(1.0F, 4.0F)),
                    new ItemEntry(Item.BUCKET, 10),
                    new ItemEntry(Item.REDSTONE, 15).apply(uniform(1.0F, 4.0F)),
                    new ItemEntry(Item.COAL, 15).apply(uniform(1.0F, 4.0F)),
                    new ItemEntry(Item.MELON_SEEDS, 10).apply(uniform(2.0F, 4.0F)),
                    new ItemEntry(Item.PUMPKIN_SEEDS, 10).apply(uniform(2.0F, 4.0F)),
                    new ItemEntry(Item.BEETROOT_SEEDS, 10).apply(uniform(2.0F, 4.0F))),
            new LootPool(new ConstantRoll(3),
                    new ItemEntry(Item.BONE, 10).apply(uniform(1.0F, 8.0F)),
                    new ItemEntry(Item.GUNPOWDER, 10).apply(uniform(1.0F, 8.0F)),
                    new ItemEntry(Item.ROTTEN_FLESH, 10).apply(uniform(1.0F, 8.0F)),
                    new ItemEntry(Item.STRING, 10).apply(uniform(1.0F, 8.0F)))
    );

    public static final LootTable SPAWN_BONUS_CHEST_CHEST = new LootTable(
            new LootPool(new ConstantRoll(1),
                    new ItemEntry(Item.STONE_AXE),
                    new ItemEntry(Item.WOODEN_AXE, 3)),
            new LootPool(new ConstantRoll(1),
                    new ItemEntry(Item.STONE_PICKAXE),
                    new ItemEntry(Item.WOODEN_PICKAXE, 3)),
            new LootPool(new ConstantRoll(3),
                    new ItemEntry(Item.APPLE, 5).apply(uniform(1.0F, 2.0F)),
                    new ItemEntry(Item.BREAD, 3).apply(uniform(1.0F, 2.0F)),
                    new ItemEntry(Item.SALMON, 3).apply(uniform(1.0F, 2.0F))),
            new LootPool(new ConstantRoll(4),
                    new ItemEntry(Item.STICK, 10).apply(uniform(1.0F, 12.0F)),
                    new ItemEntry(Item.OAK_PLANKS, 10).apply(uniform(1.0F, 12.0F)),
                    new ItemEntry(Item.OAK_LOG, 3).apply(uniform(1.0F, 3.0F)),
                    new ItemEntry(Item.SPRUCE_LOG, 3).apply(uniform(1.0F, 3.0F)),
                    new ItemEntry(Item.BIRCH_LOG, 3).apply(uniform(1.0F, 3.0F)),
                    new ItemEntry(Item.JUNGLE_LOG, 3).apply(uniform(1.0F, 3.0F)),
                    new ItemEntry(Item.ACACIA_LOG, 3).apply(uniform(1.0F, 3.0F)),
                    new ItemEntry(Item.DARK_OAK_LOG, 3).apply(uniform(1.0F, 3.0F)))
    );

    public static final LootTable STRONGHOLD_CORRIDOR_CHEST = new LootTable(
            new LootPool(new UniformRoll(2.0F, 3.0F),
                    new ItemEntry(Item.ENDER_PEARL, 10),
                    new ItemEntry(Item.DIAMOND, 3).apply(uniform(1.0F, 3.0F)),
                    new ItemEntry(Item.IRON_INGOT, 10).apply(uniform(1.0F, 5.0F)),
                    new ItemEntry(Item.GOLD_INGOT, 5).apply(uniform(1.0F, 3.0F)),
                    new ItemEntry(Item.REDSTONE, 5).apply(uniform(4.0F, 9.0F)),
                    new ItemEntry(Item.BREAD, 15).apply(uniform(1.0F, 3.0F)),
                    new ItemEntry(Item.APPLE, 15).apply(uniform(1.0F, 3.0F)),
                    new ItemEntry(Item.IRON_PICKAXE, 5),
                    new ItemEntry(Item.IRON_SWORD, 5),
                    new ItemEntry(Item.IRON_CHESTPLATE, 5),
                    new ItemEntry(Item.IRON_HELMET, 5),
                    new ItemEntry(Item.IRON_LEGGINGS, 5),
                    new ItemEntry(Item.IRON_BOOTS, 5),
                    new ItemEntry(Item.GOLDEN_APPLE),
                    new ItemEntry(Item.SADDLE),
                    new ItemEntry(Item.IRON_HORSE_ARMOR),
                    new ItemEntry(Item.GOLDEN_HORSE_ARMOR),
                    new ItemEntry(Item.DIAMOND_HORSE_ARMOR),
                    new ItemEntry(Item.BOOK).apply(new EnchantWithLevelsFunction(30, 30, true)))
    );

    public static final LootTable STRONGHOLD_CROSSING_CHEST = new LootTable(
            new LootPool(new UniformRoll(1.0F, 4.0F),
                    new ItemEntry(Item.IRON_INGOT, 10).apply(uniform(1.0F, 5.0F)),
                    new ItemEntry(Item.GOLD_INGOT, 5).apply(uniform(1.0F, 3.0F)),
                    new ItemEntry(Item.REDSTONE, 5).apply(uniform(4.0F, 9.0F)),
                    new ItemEntry(Item.COAL, 10).apply(uniform(3.0F, 8.0F)),
                    new ItemEntry(Item.BREAD, 15).apply(uniform(1.0F, 3.0F)),
                    new ItemEntry(Item.APPLE, 15).apply(uniform(1.0F, 3.0F)),
                    new ItemEntry(Item.IRON_PICKAXE),
                    new ItemEntry(Item.BOOK) /* enchant_with_levels */)
    );

    public static final LootTable STRONGHOLD_LIBRARY_CHEST = new LootTable(
            new LootPool(new UniformRoll(2.0F, 10.0F),
                    new ItemEntry(Item.BOOK, 20).apply(uniform(1.0F, 3.0F)),
                    new ItemEntry(Item.PAPER, 20).apply(uniform(2.0F, 7.0F)),
                    new ItemEntry(Item.MAP),
                    new ItemEntry(Item.COMPASS),
                    new ItemEntry(Item.BOOK, 10) /* enchant_with_levels */)
    );

    public static final LootTable UNDERWATER_RUIN_BIG_CHEST = new LootTable(
            new LootPool(new UniformRoll(2.0F, 8.0F),
                    new ItemEntry(Item.COAL, 10).apply(uniform(1.0F, 4.0F)),
                    new ItemEntry(Item.GOLD_NUGGET, 10).apply(uniform(1.0F, 3.0F)),
                    new ItemEntry(Item.EMERALD),
                    new ItemEntry(Item.WHEAT, 10).apply(uniform(2.0F, 3.0F))),
            new LootPool(new ConstantRoll(1),
                    new ItemEntry(Item.GOLDEN_APPLE),
                    new ItemEntry(Item.ENCHANTED_BOOK, 5) /* enchant_randomly */,
                    new ItemEntry(Item.LEATHER_CHESTPLATE),
                    new ItemEntry(Item.GOLDEN_HELMET),
                    new ItemEntry(Item.FISHING_ROD, 5) /* enchant_randomly */,
                    new ItemEntry(Item.MAP, 10) /* exploration_map*/)
    );

    public static final LootTable UNDERWATER_RUIN_SMALL_CHEST = new LootTable(
            new LootPool(new UniformRoll(2.0F, 8.0F),
                    new ItemEntry(Item.COAL, 10).apply(uniform(1.0F, 4.0F)),
                    new ItemEntry(Item.STONE_AXE, 2),
                    new ItemEntry(Item.ROTTEN_FLESH, 5),
                    new ItemEntry(Item.EMERALD),
                    new ItemEntry(Item.WHEAT, 10).apply(uniform(2.0F, 3.0F))),
            new LootPool(new ConstantRoll(1),
                    new ItemEntry(Item.LEATHER_CHESTPLATE),
                    new ItemEntry(Item.GOLDEN_HELMET),
                    new ItemEntry(Item.FISHING_ROD, 5) /* enchant_randomly */,
                    new ItemEntry(Item.MAP, 5) /* exploration_map*/)
    );

    public static final LootTable VILLAGE_ARMORER_CHEST = new LootTable(
            new LootPool(new UniformRoll(1.0F, 5.0F),
                    new ItemEntry(Item.IRON_INGOT, 2).apply(uniform(1.0F, 3.0F)),
                    new ItemEntry(Item.BREAD, 4).apply(uniform(1.0F, 4.0F)),
                    new ItemEntry(Item.IRON_HELMET),
                    new ItemEntry(Item.EMERALD))
    );

    public static final LootTable VILLAGE_BUTCHER_CHEST = new LootTable(
            new LootPool(new UniformRoll(1.0F, 5.0F),
                    new ItemEntry(Item.EMERALD),
                    new ItemEntry(Item.PORKCHOP, 6).apply(uniform(1.0F, 3.0F)),
                    new ItemEntry(Item.WHEAT, 6).apply(uniform(1.0F, 3.0F)),
                    new ItemEntry(Item.BEEF, 6).apply(uniform(1.0F, 3.0F)),
                    new ItemEntry(Item.MUTTON, 6).apply(uniform(1.0F, 3.0F)),
                    new ItemEntry(Item.COAL, 3).apply(uniform(1.0F, 3.0F)))
    );

    public static final LootTable VILLAGE_CARTOGRAPHER_CHEST = new LootTable(
            new LootPool(new UniformRoll(1.0F, 5.0F),
                    new ItemEntry(Item.MAP, 10).apply(uniform(1.0F, 3.0F)),
                    new ItemEntry(Item.PAPER, 15).apply(uniform(1.0F, 5.0F)),
                    new ItemEntry(Item.COMPASS, 5),
                    new ItemEntry(Item.BREAD, 15).apply(uniform(1.0F, 4.0F)),
                    new ItemEntry(Item.STICK, 5).apply(uniform(1.0F, 2.0F)))
    );

    public static final LootTable VILLAGE_DESERT_HOUSE_CHEST = new LootTable(
            new LootPool(new UniformRoll(3.0F, 8.0F),
                    new ItemEntry(Item.CLAY_BALL),
                    new ItemEntry(Item.GREEN_DYE),
                    new ItemEntry(Item.CACTUS, 10).apply(uniform(1.0F, 4.0F)),
                    new ItemEntry(Item.WHEAT, 10).apply(uniform(1.0F, 7.0F)),
                    new ItemEntry(Item.BREAD, 10).apply(uniform(1.0F, 4.0F)),
                    new ItemEntry(Item.BOOK),
                    new ItemEntry(Item.DEAD_BUSH, 2).apply(uniform(1.0F, 3.0F)),
                    new ItemEntry(Item.EMERALD).apply(uniform(1.0F, 3.0F)))
    );

    public static final LootTable VILLAGE_FISHER_CHEST = new LootTable(
            new LootPool(new UniformRoll(1.0F, 5.0F),
                    new ItemEntry(Item.EMERALD),
                    new ItemEntry(Item.COD, 2).apply(uniform(1.0F, 3.0F)),
                    new ItemEntry(Item.SALMON).apply(uniform(1.0F, 3.0F)),
                    new ItemEntry(Item.WATER_BUCKET).apply(uniform(1.0F, 3.0F)),
                    new ItemEntry(Item.BARREL).apply(uniform(1.0F, 3.0F)),
                    new ItemEntry(Item.WHEAT_SEEDS, 3).apply(uniform(1.0F, 3.0F)),
                    new ItemEntry(Item.COAL, 2).apply(uniform(1.0F, 3.0F)))
    );

    public static final LootTable VILLAGE_FLETCHER_CHEST = new LootTable(
            new LootPool(new UniformRoll(1.0F, 5.0F),
                    new ItemEntry(Item.EMERALD),
                    new ItemEntry(Item.ARROW, 2).apply(uniform(1.0F, 3.0F)),
                    new ItemEntry(Item.FEATHER, 6).apply(uniform(1.0F, 3.0F)),
                    new ItemEntry(Item.EGG, 2).apply(uniform(1.0F, 3.0F)),
                    new ItemEntry(Item.FLINT, 6).apply(uniform(1.0F, 3.0F)),
                    new ItemEntry(Item.STICK, 6).apply(uniform(1.0F, 3.0F)))
    );

    public static final LootTable VILLAGE_MASON_CHEST = new LootTable(
            new LootPool(new UniformRoll(1.0F, 5.0F),
                    new ItemEntry(Item.CLAY_BALL).apply(uniform(1.0F, 3.0F)),
                    new ItemEntry(Item.FLOWER_POT),
                    new ItemEntry(Item.STONE, 2),
                    new ItemEntry(Item.STONE_BRICKS, 2),
                    new ItemEntry(Item.BREAD, 4).apply(uniform(1.0F, 4.0F)),
                    new ItemEntry(Item.YELLOW_DYE),
                    new ItemEntry(Item.SMOOTH_STONE),
                    new ItemEntry(Item.EMERALD))
    );

    public static final LootTable VILLAGE_PLAINS_HOUSE_CHEST = new LootTable(
            new LootPool(new UniformRoll(3.0F, 8.0F),
                    new ItemEntry(Item.GOLD_NUGGET).apply(uniform(1.0F, 3.0F)),
                    new ItemEntry(Item.DANDELION, 2),
                    new ItemEntry(Item.POPPY),
                    new ItemEntry(Item.POTATO, 10).apply(uniform(1.0F, 7.0F)),
                    new ItemEntry(Item.BREAD, 10).apply(uniform(1.0F, 4.0F)),
                    new ItemEntry(Item.APPLE, 10).apply(uniform(1.0F, 5.0F)),
                    new ItemEntry(Item.BOOK),
                    new ItemEntry(Item.FEATHER),
                    new ItemEntry(Item.EMERALD, 2).apply(uniform(1.0F, 4.0F)),
                    new ItemEntry(Item.OAK_SAPLING, 5).apply(uniform(1.0F, 2.0F)))
    );

    public static final LootTable VILLAGE_SAVANNA_HOUSE_CHEST = new LootTable(
            new LootPool(new UniformRoll(3.0F, 8.0F),
                    new ItemEntry(Item.GOLD_NUGGET).apply(uniform(1.0F, 3.0F)),
                    new ItemEntry(Item.GRASS, 5),
                    new ItemEntry(Item.TALL_GRASS, 5),
                    new ItemEntry(Item.BREAD, 10).apply(uniform(1.0F, 4.0F)),
                    new ItemEntry(Item.WHEAT_SEEDS, 10).apply(uniform(1.0F, 5.0F)),
                    new ItemEntry(Item.EMERALD, 2).apply(uniform(1.0F, 4.0F)),
                    new ItemEntry(Item.ACACIA_SAPLING, 10).apply(uniform(1.0F, 2.0F)),
                    new ItemEntry(Item.SADDLE),
                    new ItemEntry(Item.TORCH).apply(uniform(1.0F, 2.0F)),
                    new ItemEntry(Item.BUCKET))
    );

    public static final LootTable VILLAGE_SHEPHERD_CHEST = new LootTable(
            new LootPool(new UniformRoll(1.0F, 5.0F),
                    new ItemEntry(Item.WHITE_WOOL, 6).apply(uniform(1.0F, 8.0F)),
                    new ItemEntry(Item.BLACK_WOOL, 3).apply(uniform(1.0F, 3.0F)),
                    new ItemEntry(Item.GRAY_WOOL, 2).apply(uniform(1.0F, 3.0F)),
                    new ItemEntry(Item.BROWN_WOOL, 2).apply(uniform(1.0F, 3.0F)),
                    new ItemEntry(Item.LIGHT_GRAY_WOOL, 2).apply(uniform(1.0F, 3.0F)),
                    new ItemEntry(Item.EMERALD),
                    new ItemEntry(Item.SHEARS),
                    new ItemEntry(Item.WHEAT, 6).apply(uniform(1.0F, 6.0F)))
    );

    public static final LootTable VILLAGE_SNOWY_HOUSE_CHEST = new LootTable(
            new LootPool(new UniformRoll(3.0F, 8.0F),
                    new ItemEntry(Item.BLUE_ICE),
                    new ItemEntry(Item.SNOW_BLOCK, 4),
                    new ItemEntry(Item.POTATO, 10).apply(uniform(1.0F, 7.0F)),
                    new ItemEntry(Item.BREAD, 10).apply(uniform(1.0F, 4.0F)),
                    new ItemEntry(Item.BEETROOT_SEEDS, 10).apply(uniform(1.0F, 5.0F)),
                    new ItemEntry(Item.BEETROOT_SOUP),
                    new ItemEntry(Item.FURNACE),
                    new ItemEntry(Item.EMERALD).apply(uniform(1.0F, 4.0F)),
                    new ItemEntry(Item.SNOWBALL, 10).apply(uniform(1.0F, 7.0F)),
                    new ItemEntry(Item.COAL, 5).apply(uniform(1.0F, 4.0F)))
    );

    public static final LootTable VILLAGE_TAIGA_HOUSE_CHEST = new LootTable(
            new LootPool(new UniformRoll(3.0F, 8.0F),
                    new ItemEntry(Item.IRON_NUGGET).apply(uniform(1.0F, 5.0F)),
                    new ItemEntry(Item.FERN, 2),
                    new ItemEntry(Item.LARGE_FERN, 2),
                    new ItemEntry(Item.POTATO, 10).apply(uniform(1.0F, 7.0F)),
                    new ItemEntry(Item.SWEET_BERRIES, 5).apply(uniform(1.0F, 7.0F)),
                    new ItemEntry(Item.BREAD, 10).apply(uniform(1.0F, 4.0F)),
                    new ItemEntry(Item.PUMPKIN_SEEDS, 5).apply(uniform(1.0F, 5.0F)),
                    new ItemEntry(Item.PUMPKIN_PIE),
                    new ItemEntry(Item.EMERALD, 2).apply(uniform(1.0F, 4.0F)),
                    new ItemEntry(Item.SPRUCE_SAPLING, 5).apply(uniform(1.0F, 5.0F)),
                    new ItemEntry(Item.SPRUCE_SIGN),
                    new ItemEntry(Item.SPRUCE_LOG, 10).apply(uniform(1.0F, 5.0F)))
    );

    public static final LootTable VILLAGE_TANNERY_CHEST = new LootTable(
            new LootPool(new UniformRoll(1.0F, 5.0F),
                    new ItemEntry(Item.LEATHER).apply(uniform(1.0F, 3.0F)),
                    new ItemEntry(Item.LEATHER_CHESTPLATE, 2),
                    new ItemEntry(Item.LEATHER_BOOTS, 2),
                    new ItemEntry(Item.LEATHER_HELMET, 2),
                    new ItemEntry(Item.BREAD, 5).apply(uniform(1.0F, 4.0F)),
                    new ItemEntry(Item.LEATHER_LEGGINGS, 2),
                    new ItemEntry(Item.SADDLE),
                    new ItemEntry(Item.EMERALD).apply(uniform(1.0F, 4.0F)))
    );

    public static final LootTable VILLAGE_TEMPLE_CHEST = new LootTable(
            new LootPool(new UniformRoll(3.0F, 8.0F),
                    new ItemEntry(Item.REDSTONE, 2).apply(uniform(1.0F, 4.0F)),
                    new ItemEntry(Item.BREAD, 7).apply(uniform(1.0F, 4.0F)),
                    new ItemEntry(Item.ROTTEN_FLESH, 7).apply(uniform(1.0F, 4.0F)),
                    new ItemEntry(Item.LAPIS_LAZULI).apply(uniform(1.0F, 4.0F)),
                    new ItemEntry(Item.GOLD_INGOT).apply(uniform(1.0F, 4.0F)),
                    new ItemEntry(Item.EMERALD).apply(uniform(1.0F, 4.0F)))
    );

    public static final LootTable VILLAGE_TOOLSMITH_CHEST = new LootTable(
            new LootPool(new UniformRoll(3.0F, 8.0F),
                    new ItemEntry(Item.DIAMOND).apply(uniform(1.0F, 3.0F)),
                    new ItemEntry(Item.IRON_INGOT, 5).apply(uniform(1.0F, 5.0F)),
                    new ItemEntry(Item.GOLD_INGOT).apply(uniform(1.0F, 3.0F)),
                    new ItemEntry(Item.BREAD, 15).apply(uniform(1.0F, 3.0F)),
                    new ItemEntry(Item.IRON_PICKAXE, 5),
                    new ItemEntry(Item.COAL).apply(uniform(1.0F, 3.0F)),
                    new ItemEntry(Item.STICK, 20).apply(uniform(1.0F, 3.0F)),
                    new ItemEntry(Item.IRON_SHOVEL, 5))
    );

    public static final LootTable VILLAGE_WEAPONSMITH_CHEST = new LootTable(
            new LootPool(new UniformRoll(3.0F, 8.0F),
                    new ItemEntry(Item.DIAMOND, 3).apply(uniform(1.0F, 3.0F)),
                    new ItemEntry(Item.IRON_INGOT, 10).apply(uniform(1.0F, 5.0F)),
                    new ItemEntry(Item.GOLD_INGOT, 5).apply(uniform(1.0F, 3.0F)),
                    new ItemEntry(Item.BREAD, 15).apply(uniform(1.0F, 3.0F)),
                    new ItemEntry(Item.APPLE, 15).apply(uniform(1.0F, 3.0F)),
                    new ItemEntry(Item.IRON_PICKAXE, 5),
                    new ItemEntry(Item.IRON_SWORD, 5),
                    new ItemEntry(Item.IRON_CHESTPLATE, 5),
                    new ItemEntry(Item.IRON_HELMET, 5),
                    new ItemEntry(Item.IRON_LEGGINGS, 5),
                    new ItemEntry(Item.IRON_BOOTS, 5),
                    new ItemEntry(Item.OBSIDIAN, 5).apply(uniform(3.0F, 7.0F)),
                    new ItemEntry(Item.OAK_SAPLING, 5).apply(uniform(3.0F, 7.0F)),
                    new ItemEntry(Item.SADDLE, 3),
                    new ItemEntry(Item.IRON_HORSE_ARMOR),
                    new ItemEntry(Item.GOLDEN_HORSE_ARMOR),
                    new ItemEntry(Item.DIAMOND_HORSE_ARMOR))
    );

    public static final LootTable WOODLAND_MANSION_CHEST = new LootTable(
            new LootPool(new UniformRoll(1.0F, 3.0F),
                    new ItemEntry(Item.LEAD, 20),
                    new ItemEntry(Item.GOLDEN_APPLE, 15),
                    new ItemEntry(Item.ENCHANTED_GOLDEN_APPLE, 2),
                    new ItemEntry(Item.MUSIC_DISC_13, 15),
                    new ItemEntry(Item.MUSIC_DISC_CAT, 15),
                    new ItemEntry(Item.NAME_TAG, 20),
                    new ItemEntry(Item.CHAINMAIL_CHESTPLATE, 10),
                    new ItemEntry(Item.DIAMOND_HOE, 15),
                    new ItemEntry(Item.DIAMOND_CHESTPLATE, 5),
                    new ItemEntry(Item.ENCHANTED_BOOK, 10).apply(new EnchantRandomlyFunction())),
            new LootPool(new UniformRoll(1.0F, 4.0F),
                    new ItemEntry(Item.IRON_INGOT, 10).apply(uniform(1.0F, 4.0F)),
                    new ItemEntry(Item.GOLD_INGOT, 5).apply(uniform(1.0F, 4.0F)),
                    new ItemEntry(Item.BREAD, 20),
                    new ItemEntry(Item.WHEAT, 20).apply(uniform(1.0F, 4.0F)),
                    new ItemEntry(Item.BUCKET, 10),
                    new ItemEntry(Item.REDSTONE, 15).apply(uniform(1.0F, 4.0F)),
                    new ItemEntry(Item.COAL, 15).apply(uniform(1.0F, 4.0F)),
                    new ItemEntry(Item.MELON_SEEDS, 10).apply(uniform(2.0F, 4.0F)),
                    new ItemEntry(Item.PUMPKIN_SEEDS, 10).apply(uniform(2.0F, 4.0F)),
                    new ItemEntry(Item.BEETROOT_SEEDS, 10).apply(uniform(2.0F, 4.0F))),
            new LootPool(new ConstantRoll(3),
                    new ItemEntry(Item.BONE, 10).apply(uniform(1.0F, 8.0F)),
                    new ItemEntry(Item.GUNPOWDER, 10).apply(uniform(1.0F, 8.0F)),
                    new ItemEntry(Item.ROTTEN_FLESH, 10).apply(uniform(1.0F, 8.0F)),
                    new ItemEntry(Item.STRING, 10).apply(uniform(1.0F, 8.0F)))
    );

}
