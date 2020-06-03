package kaptainwutax.featureutils.decorator;

import kaptainwutax.biomeutils.source.BiomeSource;
import kaptainwutax.featureutils.Feature;
import kaptainwutax.seedutils.mc.ChunkRand;
import kaptainwutax.seedutils.mc.MCVersion;
import kaptainwutax.seedutils.mc.VersionMap;

public class EndGateway extends Feature<EndGateway.Config, EndGateway.Data> {

	public static final VersionMap<EndGateway.Config> CONFIGS = new VersionMap<EndGateway.Config>()
			.add(MCVersion.v1_13, new EndGateway.Config(700, 0, 3));

	public EndGateway(MCVersion version) {
		super(CONFIGS.getAsOf(version), version);
	}

	public EndGateway(EndGateway.Config config) {
		super(config, null);
	}

	private int getIndex() {
		return this.getConfig().index;
	}

	private int getStep() {
		return this.getConfig().step;
	}

	@Override
	public boolean canStart(EndGateway.Data data, long structureSeed, ChunkRand rand) {
		rand.setDecoratorSeed(structureSeed, data.chunkX << 4, data.chunkZ << 4,
				this.getIndex(), this.getStep(), this.getVersion());

		if(rand.nextInt(700) != 0)return false;
		if(rand.nextInt(16) != data.offsetX)return false;
		if(rand.nextInt(16) != data.offsetZ)return false;
		return true;
	}

	@Override
	public boolean canSpawn(Data data, BiomeSource source) {
		return false;
	}

	public EndGateway.Data at(int blockX, int blockZ) {
		return new EndGateway.Data(this, blockX, blockZ);
	}

	public static class Config extends Feature.Config {
		public final int rarity;
		public final int index;
		public final int step;

		public Config(int rarity, int index, int step) {
			this.rarity = rarity;
			this.index = index;
			this.step = step;
		}
	}

	public static class Data extends Feature.Data<EndGateway> {
		public final int offsetX;
		public final int offsetZ;

		public Data(EndGateway feature, int blockX, int blockZ) {
			super(feature, blockX >> 4, blockZ >> 4);
			this.offsetX = blockX & 15;
			this.offsetZ = blockZ & 15;
		}
	}

 }
