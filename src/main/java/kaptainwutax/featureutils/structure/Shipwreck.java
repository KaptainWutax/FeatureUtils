package kaptainwutax.featureutils.structure;


import kaptainwutax.biomeutils.biome.Biome;
import kaptainwutax.biomeutils.biome.Biomes;
import kaptainwutax.featureutils.loot.ILoot;
import kaptainwutax.featureutils.loot.LootContext;
import kaptainwutax.featureutils.loot.LootTable;
import kaptainwutax.featureutils.loot.MCLootTables;
import kaptainwutax.featureutils.loot.item.ItemStack;
import kaptainwutax.featureutils.structure.generator.EndCityGenerator;
import kaptainwutax.featureutils.structure.generator.Generator;
import kaptainwutax.featureutils.structure.generator.ShipwreckGenerator;
import kaptainwutax.mcutils.rand.ChunkRand;
import kaptainwutax.mcutils.state.Dimension;
import kaptainwutax.mcutils.util.block.BlockBox;
import kaptainwutax.mcutils.util.block.BlockMirror;
import kaptainwutax.mcutils.util.block.BlockRotation;
import kaptainwutax.mcutils.util.data.Pair;
import kaptainwutax.mcutils.util.pos.BPos;
import kaptainwutax.mcutils.util.pos.CPos;
import kaptainwutax.mcutils.util.pos.RPos;
import kaptainwutax.mcutils.version.MCVersion;
import kaptainwutax.mcutils.version.VersionMap;

import java.util.*;
import java.util.concurrent.Callable;

public class Shipwreck extends UniformStructure<Shipwreck> implements ILoot {
	public static final VersionMap<RegionStructure.Config> CONFIGS = new VersionMap<RegionStructure.Config>()
			.add(MCVersion.v1_13, new RegionStructure.Config(15, 8, 165745295))
			.add(MCVersion.v1_13_1, new RegionStructure.Config(16, 8, 165745295))
			.add(MCVersion.v1_16, new RegionStructure.Config(24, 4, 165745295));



	public Shipwreck(MCVersion version) {
		this(CONFIGS.getAsOf(version), version);
	}

	public Shipwreck(RegionStructure.Config config, MCVersion version) {
		super(config, version);
	}

	public static String name() {
		return "shipwreck";
	}

	@Override
	public boolean canStart(Data<Shipwreck> data, long structureSeed, ChunkRand rand) {
		return super.canStart(data, structureSeed, rand);
	}

	@Override
	public boolean isValidDimension(Dimension dimension) {
		return dimension == Dimension.OVERWORLD;
	}

	@Override
	public boolean isValidBiome(Biome biome) {
		return biome.getCategory() == Biome.Category.OCEAN ||  biome == Biomes.BEACH || biome == Biomes.SNOWY_BEACH;
	}

	@Override
	public int getDecorationSalt() {
		return 40006;
	}

	@Override
	public boolean isCorrectGenerator(Generator generator) {
		return generator instanceof ShipwreckGenerator;
	}

	@Override
	public SpecificCalls getSpecificCalls() {
		return (generator, rand) -> {
			if (isCorrectGenerator(generator)){
				if (((ShipwreckGenerator) generator).isBeached()){
					rand.nextInt(3);
				}
			}
		};
	}
}
