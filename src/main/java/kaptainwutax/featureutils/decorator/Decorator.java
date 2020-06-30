package kaptainwutax.featureutils.decorator;

import kaptainwutax.biomeutils.Biome;
import kaptainwutax.biomeutils.source.BiomeSource;
import kaptainwutax.featureutils.Feature;
import kaptainwutax.seedutils.mc.ChunkRand;
import kaptainwutax.seedutils.mc.MCVersion;

public abstract class Decorator<C extends Decorator.Config, D extends Feature.Data<?>> extends Feature<C, D> {

	public Decorator(C config, MCVersion version) {
		super(config, version);
	}

	public int getStep() {
		return this.getConfig().step;
	}

	public int getIndex() {
		return this.getConfig().index;
	}

	@Override
	public boolean canStart(D data, long structureSeed, ChunkRand rand) {
		rand.setDecoratorSeed(structureSeed, data.chunkX << 4, data.chunkZ << 4,
				this.getIndex(), this.getStep(), this.getVersion());
		return true;
	}

	@Override
	public final boolean canSpawn(D data, BiomeSource source) {
		return this.canSpawn(data.chunkX, data.chunkZ, source);
	}

	public boolean canSpawn(int chunkX, int chunkZ, BiomeSource source) {
		if(this.getVersion().isOlderThan(MCVersion.v1_16)) {
			return this.isValidBiome(source.getBiome((chunkX << 4) + 8, 0, (chunkZ << 4) + 8));
		}

		return this.isValidBiome(source.getBiomeForNoiseGen((chunkX << 2) + 2, 0, (chunkZ << 2) + 2));
	}

	public abstract boolean isValidBiome(Biome biome);

	public static class Config extends Feature.Config {
		public final int step;
		public final int index;

		public Config(int step, int index) {
			this.step = step;
			this.index = index;
		}
	}

}
