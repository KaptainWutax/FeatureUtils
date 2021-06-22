package kaptainwutax.featureutils.structure;

import kaptainwutax.featureutils.Feature;
import kaptainwutax.mcutils.rand.ChunkRand;
import kaptainwutax.mcutils.util.pos.CPos;
import kaptainwutax.mcutils.version.MCVersion;

public abstract class RegionStructure<C extends RegionStructure.Config, D extends RegionStructure.Data<?>> extends Structure<C, D> {

	public RegionStructure(C config, MCVersion version) {
		super(config, version);
	}

	public static String name() {
		return "region_structure";
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

			this.regionX = x / this.feature.getSpacing();
			this.regionZ = z / this.feature.getSpacing();

			this.offsetX = this.chunkX - this.regionX * this.feature.getSpacing();
			this.offsetZ = this.chunkZ - this.regionZ * this.feature.getSpacing();

			this.baseRegionSeed = new ChunkRand().setRegionSeed(0L, this.regionX, this.regionZ,
				this.feature.getSalt(), this.feature.getVersion());
		}

		@Override
		public String toString() {
			return "Data{" +
				"regionX=" + regionX +
				", regionZ=" + regionZ +
				", offsetX=" + offsetX +
				", offsetZ=" + offsetZ +
				", baseRegionSeed=" + baseRegionSeed +
				'}';
		}
	}

}
