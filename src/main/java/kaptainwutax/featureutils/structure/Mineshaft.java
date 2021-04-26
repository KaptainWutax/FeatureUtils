package kaptainwutax.featureutils.structure;

import kaptainwutax.biomeutils.biome.Biome;
import kaptainwutax.biomeutils.biome.Biomes;
import kaptainwutax.featureutils.Feature;
import kaptainwutax.mcutils.rand.ChunkRand;
import kaptainwutax.mcutils.state.Dimension;
import kaptainwutax.mcutils.version.MCVersion;
import kaptainwutax.mcutils.version.VersionMap;

public class Mineshaft extends Structure<Mineshaft.Config, Feature.Data<?>> {

	public static final VersionMap<Mineshaft.Config> CONFIGS = new VersionMap<Mineshaft.Config>()
			.add(MCVersion.v1_8, new Mineshaft.Config(0.004D));

	public Mineshaft(MCVersion version) {
		this(CONFIGS.getAsOf(version), version);
	}

	public Mineshaft(Mineshaft.Config config, MCVersion version) {
		super(config, version);
	}

	public static String name() {
		return "mineshaft";
	}

	private double getChance() {
		return this.getConfig().chance;
	}

	@Override
	public boolean canStart(Data<?> data, long structureSeed, ChunkRand rand) {
		rand.setCarverSeed(structureSeed, data.chunkX, data.chunkZ, this.getVersion());
		return rand.nextDouble() < this.getChance();
	}

	@Override
	public boolean isValidDimension(Dimension dimension) {
		return dimension == Dimension.OVERWORLD;
	}

	@Override
	public boolean isValidBiome(Biome biome) {
		return biome.getCategory() == Biome.Category.OCEAN
				|| biome == Biomes.BAMBOO_JUNGLE || biome == Biomes.BAMBOO_JUNGLE_HILLS || biome == Biomes.BIRCH_FOREST
				|| biome == Biomes.BIRCH_FOREST_HILLS || biome == Biomes.DARK_FOREST || biome == Biomes.DARK_FOREST_HILLS
				|| biome == Biomes.DESERT || biome == Biomes.DESERT_HILLS || biome == Biomes.DESERT_LAKES
				|| biome == Biomes.FLOWER_FOREST || biome == Biomes.FOREST || biome == Biomes.GIANT_SPRUCE_TAIGA
				|| biome == Biomes.GIANT_SPRUCE_TAIGA_HILLS || biome == Biomes.GIANT_TREE_TAIGA || biome == Biomes.GIANT_TREE_TAIGA_HILLS
				|| biome == Biomes.GRAVELLY_MOUNTAINS || biome == Biomes.ICE_SPIKES || biome == Biomes.JUNGLE
				|| biome == Biomes.JUNGLE_EDGE || biome == Biomes.JUNGLE_HILLS || biome == Biomes.MODIFIED_GRAVELLY_MOUNTAINS
				|| biome == Biomes.MODIFIED_JUNGLE || biome == Biomes.MODIFIED_JUNGLE_EDGE || biome == Biomes.MOUNTAIN_EDGE
				|| biome == Biomes.MOUNTAINS || biome == Biomes.MUSHROOM_FIELDS || biome == Biomes.MUSHROOM_FIELD_SHORE
				|| biome == Biomes.PLAINS || biome == Biomes.SAVANNA || biome == Biomes.SAVANNA_PLATEAU
				|| biome == Biomes.SHATTERED_SAVANNA || biome == Biomes.SHATTERED_SAVANNA_PLATEAU || biome == Biomes.SNOWY_MOUNTAINS
				|| biome == Biomes.SNOWY_TAIGA || biome == Biomes.SNOWY_TAIGA_HILLS || biome == Biomes.SNOWY_TAIGA_MOUNTAINS
				|| biome == Biomes.SNOWY_TUNDRA || biome == Biomes.STONE_SHORE || biome == Biomes.SUNFLOWER_PLAINS
				|| biome == Biomes.TAIGA || biome == Biomes.TAIGA_HILLS || biome == Biomes.TAIGA_MOUNTAINS
				|| biome == Biomes.TALL_BIRCH_FOREST || biome == Biomes.TALL_BIRCH_HILLS || biome == Biomes.WOODED_HILLS
				|| biome == Biomes.WOODED_MOUNTAINS || biome.getCategory() == Biome.Category.MESA
				//hardcoded checks
				|| biome == Biomes.BEACH || biome == Biomes.FROZEN_RIVER || biome == Biomes.RIVER
				|| biome == Biomes.SNOWY_BEACH || biome == Biomes.SWAMP || biome == Biomes.SWAMP_HILLS;
	}

	public Feature.Data<Mineshaft> at(int chunkX, int chunkZ) {
		return new Feature.Data<>(this, chunkX, chunkZ);
	}

	public static class Config extends Feature.Config {
		public final double chance;

		public Config(double chance) {
			this.chance = chance;
		}
	}

}
