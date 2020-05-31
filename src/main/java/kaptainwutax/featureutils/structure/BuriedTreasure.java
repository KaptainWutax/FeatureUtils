package kaptainwutax.featureutils.structure;

import kaptainwutax.seedutils.mc.ChunkRand;
import kaptainwutax.seedutils.mc.MCVersion;
import kaptainwutax.seedutils.mc.VersionMap;
import kaptainwutax.seedutils.mc.seed.ChunkSeeds;
import kaptainwutax.featureutils.Feature;

public class BuriedTreasure extends Feature<BuriedTreasure.Config, BuriedTreasure.Data> {

	public static final VersionMap<BuriedTreasure.Config> CONFIGS = new VersionMap<BuriedTreasure.Config>()
			.add(MCVersion.v1_13, new BuriedTreasure.Config(0.01F, 10387320));

	public BuriedTreasure(MCVersion version) {
		super(CONFIGS.getAsOf(version), version);
	}

	public BuriedTreasure(BuriedTreasure.Config config) {
		super(config, null);
	}

	public int getSalt() {
		return this.getConfig().salt;
	}

	public float getChance() {
		return this.getConfig().chance;
	}

	@Override
	public boolean test(BuriedTreasure.Data data, long structureSeed, ChunkRand rand) {
		rand.setSeed(data.baseRegionSeed + structureSeed);
		return rand.nextFloat() < this.getChance();
	}

	public BuriedTreasure.Data at(int chunkX, int chunkZ) {
		return new BuriedTreasure.Data(this, chunkX, chunkZ);
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

	public static class Data extends Feature.Data<BuriedTreasure> {
		private final long baseRegionSeed;

		public Data(BuriedTreasure structure, int chunkX, int chunkZ) {
			super(structure, chunkX, chunkZ);
			this.baseRegionSeed = ChunkSeeds.getRegionSeed(0L, this.chunkX, this.chunkZ,
					structure.getSalt(), structure.getVersion());
		}
	}

}
