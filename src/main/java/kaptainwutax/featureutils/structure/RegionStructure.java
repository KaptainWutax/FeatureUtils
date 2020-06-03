package kaptainwutax.featureutils.structure;

import kaptainwutax.biomeutils.Biome;
import kaptainwutax.biomeutils.BiomeSource;
import kaptainwutax.seedutils.mc.ChunkRand;
import kaptainwutax.seedutils.mc.MCVersion;
import kaptainwutax.seedutils.mc.pos.CPos;
import kaptainwutax.seedutils.mc.seed.ChunkSeeds;
import kaptainwutax.featureutils.Feature;

public abstract class RegionStructure<C extends RegionStructure.Config, D extends RegionStructure.Data<?>> extends Structure<C, D> {

	public RegionStructure(C config, MCVersion version) {
		super(config, version);
	}

	public int getSpacing() {
		return this.getConfig().spacing;
	}

	public int getSeparation() {
		return this.getConfig().separation;
	}

	public int getSalt() {
		return this.getConfig().salt;
	}

	public Data<?> at(int chunkX, int chunkZ) {
		return new Data<>(this, chunkX, chunkZ);
	}

	public abstract CPos getInRegion(long structureSeed, int regionX, int regionZ, ChunkRand rand);

	public static class Config extends Feature.Config {
		public final int spacing;
		public final int separation;
		public final int salt;

		public Config(int spacing, int separation, int salt) {
			this.spacing = spacing;
			this.separation = separation;
			this.salt = salt;
		}
	}

	public static class Data<T extends RegionStructure<?, ?>> extends Feature.Data<T> {
		public final int regionX;
		public final int regionZ;
		public final int offsetX;
		public final int offsetZ;
		public final long baseRegionSeed;

		public Data(T structure, int chunkX, int chunkZ) {
			super(structure, chunkX, chunkZ);

			int x = this.chunkX < 0 ? this.chunkX - this.feature.getSpacing() + 1 : this.chunkX;
			int z = this.chunkZ < 0 ? this.chunkZ - this.feature.getSpacing() + 1 : this.chunkZ;

			//Pick out in which region the chunk is.
			int regionX = (x / this.feature.getSpacing());
			int regionZ = (z / this.feature.getSpacing());

			this.regionX = regionX;
			this.regionZ = regionZ;

			regionX *= this.feature.getSpacing();
			regionZ *= this.feature.getSpacing();

			this.offsetX = this.chunkX - regionX;
			this.offsetZ = this.chunkZ - regionZ;

			this.baseRegionSeed = ChunkSeeds.getRegionSeed(0L, this.regionX, this.regionZ,
					this.feature.getSalt(), this.feature.getVersion());
		}
	}

}
