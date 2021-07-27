package kaptainwutax.featureutils;

import kaptainwutax.biomeutils.source.BiomeSource;
import kaptainwutax.mcutils.state.Dimension;
import kaptainwutax.mcutils.version.MCVersion;
import kaptainwutax.mcutils.version.UnsupportedVersion;
import kaptainwutax.terrainutils.TerrainGenerator;

/**
 * Useful utility to bind a context to any class, gives a biome source and a terrain generator, mainly implemented for Features
 */
public interface GenerationContext {
	default Context getContext(long worldSeed) {
		if(!(this instanceof Feature<?, ?>)) return null;
		Feature<?, ?> feature = (Feature<?, ?>)this;
		return getContext(worldSeed,feature.getValidDimension(),feature.getVersion());
	}

	static Context getContext(long worldSeed, Dimension dimension, MCVersion version) {
		BiomeSource biomeSource = BiomeSource.of(dimension, version, worldSeed);
		TerrainGenerator generator = null;
		if(biomeSource != null) {
			try {
				generator = TerrainGenerator.of(biomeSource);
			} catch(UnsupportedVersion ignored) {}
		}

		return new Context(biomeSource, generator);
	}

	class Context {
		private final BiomeSource biomeSource;
		private final TerrainGenerator generator;
		private final Long worldSeed;
		private final Dimension dimension;

		public Context(BiomeSource biomeSource, TerrainGenerator generator) {
			this.biomeSource = biomeSource;
			this.generator = generator;
			if(biomeSource != null) {
				this.worldSeed = biomeSource.getWorldSeed();
				this.dimension = biomeSource.getDimension();
			} else {
				this.worldSeed = null;
				this.dimension = null;
			}
		}

		public Dimension getDimension() {
			return dimension;
		}

		public BiomeSource getBiomeSource() {
			return biomeSource;
		}

		public TerrainGenerator getGenerator() {
			return generator;
		}

		public Long getWorldSeed() {
			return worldSeed;
		}
	}
}
