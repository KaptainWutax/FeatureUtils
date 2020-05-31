package kaptainwutax.featureutils;

import kaptainwutax.seedutils.mc.ChunkRand;
import kaptainwutax.seedutils.mc.MCVersion;

public abstract class Feature<C extends Feature.Config, D extends Feature.Data<?>> {

	private final C config;
	private final MCVersion version;

	public Feature(C config, MCVersion version) {
		this.config = config;
		this.version = version;
	}

	public C getConfig() {
		return this.config;
	}

	public MCVersion getVersion() {
		return this.version;
	}

	public abstract boolean test(D data, long structureSeed, ChunkRand rand);

	public static class Config {

	}

	public static class Data<T extends Feature> {
		public final T feature;
		public final int chunkX;
		public final int chunkZ;

		public Data(T feature, int chunkX, int chunkZ) {
			this.feature = feature;
			this.chunkX = chunkX;
			this.chunkZ = chunkZ;
		}

		public boolean test(long structureSeed, ChunkRand rand) {
			return this.feature.test(this, structureSeed, rand);
		}
	}

}
