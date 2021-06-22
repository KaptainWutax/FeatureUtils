package kaptainwutax.featureutils.misc;

import kaptainwutax.biomeutils.source.BiomeSource;
import kaptainwutax.featureutils.Feature;
import kaptainwutax.mcutils.rand.ChunkRand;
import kaptainwutax.mcutils.state.Dimension;
import kaptainwutax.mcutils.version.MCVersion;
import kaptainwutax.mcutils.version.VersionMap;
import kaptainwutax.terrainutils.TerrainGenerator;

public class SlimeChunk extends Feature<SlimeChunk.Config, SlimeChunk.Data> {

	public static final VersionMap<SlimeChunk.Config> CONFIGS = new VersionMap<SlimeChunk.Config>()
		.add(MCVersion.v1_8, new SlimeChunk.Config(10));

	public SlimeChunk(MCVersion version) {
		this(CONFIGS.getAsOf(version), version);
	}

	public SlimeChunk(Config config, MCVersion version) {
		super(config, version);
	}

	@Override
	public String getName() {
		return name();
	}

	public static String name() {
		return "slime_chunk";
	}

	public int getRarity() {
		return this.getConfig().rarity;
	}

	@Override
	public boolean canStart(SlimeChunk.Data data, long structureSeed, ChunkRand rand) {
		rand.setSlimeSeed(structureSeed, data.chunkX, data.chunkZ, this.getVersion());
		return (rand.nextInt(this.getRarity()) == 0) == data.isSlime;
	}

	@Override
	public boolean canSpawn(SlimeChunk.Data data, BiomeSource source) {
		return true;
	}

	@Override
	public boolean canGenerate(Data data, TerrainGenerator generator) {
		return true;
	}

	@Override
	public Dimension getValidDimension() {
		return Dimension.OVERWORLD;
	}

	public SlimeChunk.Data at(int chunkX, int chunkZ, boolean isSlime) {
		return new SlimeChunk.Data(this, chunkX, chunkZ, isSlime);
	}

	public static class Config extends Feature.Config {
		public final int rarity;

		public Config(int rarity) {
			this.rarity = rarity;
		}
	}

	public static class Data extends Feature.Data<SlimeChunk> {
		public final boolean isSlime;

		public Data(SlimeChunk feature, int chunkX, int chunkZ, boolean isSlime) {
			super(feature, chunkX, chunkZ);
			this.isSlime = isSlime;
		}
	}

}
