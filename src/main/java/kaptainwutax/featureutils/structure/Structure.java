package kaptainwutax.featureutils.structure;

import kaptainwutax.biomeutils.Biome;
import kaptainwutax.biomeutils.source.BiomeSource;
import kaptainwutax.featureutils.Feature;
import kaptainwutax.seedutils.mc.MCVersion;

public abstract class Structure<C extends Feature.Config, D extends Feature.Data<?>> extends Feature<C, D> {

	public Structure(C config, MCVersion version) {
		super(config, version);
	}

	@Override
	public boolean canSpawn(D data, BiomeSource source) {
		return this.canSpawn(data.chunkX, data.chunkZ, source);
	}

	public boolean canSpawn(int chunkX, int chunkZ, BiomeSource source) {
		if(this.getVersion().isOlderThan(MCVersion.v1_16)) {
			return this.isValidBiome(source.getBiome((chunkX << 4) + 9, 0, (chunkZ << 4) + 9));
		}

		return this.isValidBiome(source.getBiomeForNoiseGen((chunkX << 2) + 2, 0, (chunkZ << 2) + 2));
	}

	public abstract boolean isValidBiome(Biome biome);

}
