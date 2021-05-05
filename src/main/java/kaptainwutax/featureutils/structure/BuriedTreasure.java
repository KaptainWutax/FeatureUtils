package kaptainwutax.featureutils.structure;

import kaptainwutax.biomeutils.biome.Biome;
import kaptainwutax.biomeutils.biome.Biomes;
import kaptainwutax.featureutils.loot.ILoot;
import kaptainwutax.featureutils.structure.generator.BuriedTreasureGenerator;
import kaptainwutax.featureutils.structure.generator.Generator;
import kaptainwutax.mcutils.rand.ChunkRand;
import kaptainwutax.mcutils.state.Dimension;
import kaptainwutax.mcutils.util.pos.CPos;
import kaptainwutax.mcutils.version.MCVersion;
import kaptainwutax.mcutils.version.VersionMap;

public class BuriedTreasure extends RegionStructure<BuriedTreasure.Config, RegionStructure.Data<BuriedTreasure>> implements ILoot {

	public static final VersionMap<BuriedTreasure.Config> CONFIGS = new VersionMap<BuriedTreasure.Config>()
			.add(MCVersion.v1_13, new BuriedTreasure.Config(0.01F, 10387320));

	public BuriedTreasure(MCVersion version) {
		this(CONFIGS.getAsOf(version), version);
	}

	public BuriedTreasure(BuriedTreasure.Config config, MCVersion version) {
		super(config, version);
	}

	public static String name() {
		return "buried_treasure";
	}

	public float getChance() {
		return this.getConfig().chance;
	}

	@Override
	public boolean canStart(RegionStructure.Data<BuriedTreasure> data, long structureSeed, ChunkRand rand) {
		rand.setSeed(data.baseRegionSeed + structureSeed);
		return rand.nextFloat() < this.getChance();
	}

	@Override
	public CPos getInRegion(long structureSeed, int regionX, int regionZ, ChunkRand rand) {
		rand.setRegionSeed(structureSeed, regionX, regionZ, this.getSalt(), this.getVersion());
		return rand.nextFloat() < this.getChance() ? new CPos(regionX, regionZ) : null;
	}

	@Override
	public boolean isValidDimension(Dimension dimension) {
		return dimension == Dimension.OVERWORLD;
	}

	@Override
	public boolean isValidBiome(Biome biome) {
		return biome == Biomes.BEACH || biome == Biomes.SNOWY_BEACH;
	}

	@Override
	public RegionStructure.Data<BuriedTreasure> at(int chunkX, int chunkZ) {
		return new RegionStructure.Data<>(this, chunkX, chunkZ);
	}

	@Override
	public int getDecorationSalt() {
		return 30001;
	}

	@Override
	public boolean isCorrectGenerator(Generator generator) {
		return generator instanceof BuriedTreasureGenerator;
	}

	@Override
	public SpecificCalls getSpecificCalls() {
		return null;
	}

	@Override
	public boolean shouldAdvanceInChunks() {
		return false;
	}

	public static class Config extends RegionStructure.Config {
		public static final int SPACING = 1;
		public static final int SEPARATION = 0;

		private final float chance;

		public Config(float chance, int salt) {
			this(chance, SPACING, SEPARATION, salt);
		}

		public Config(float chance, int spacing, int separation, int salt) {
			super(spacing, separation, salt);
			this.chance = chance;
		}
	}

}
