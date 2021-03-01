package kaptainwutax.featureutils.structure;

import kaptainwutax.biomeutils.Biome;
import kaptainwutax.biomeutils.source.BiomeSource;
import kaptainwutax.seedutils.mc.ChunkRand;
import kaptainwutax.seedutils.mc.Dimension;
import kaptainwutax.seedutils.mc.MCVersion;
import kaptainwutax.seedutils.mc.VersionMap;
import kaptainwutax.seedutils.mc.pos.CPos;
import kaptainwutax.seedutils.util.UnsupportedVersion;

public class Fortress extends UniformStructure<Fortress> {

	public static final VersionMap<RegionStructure.Config> CONFIGS = new VersionMap<RegionStructure.Config>()
			//This is there as reference, it doesn't actually use regions prior to 1.16.
			.add(MCVersion.v1_8, new RegionStructure.Config(16, 8, -1))
			.add(MCVersion.v1_16, new RegionStructure.Config(30, 4, 30084232))
			.add(MCVersion.v1_16_1, new RegionStructure.Config(27, 4, 30084232));

	public Fortress(MCVersion version) {
		super(CONFIGS.getAsOf(version), version);
	}

	public Fortress(RegionStructure.Config config, MCVersion version) {
		super(config, version);

		if(this.getVersion().isOlderThan(MCVersion.v1_16)) {
			throw new UnsupportedVersion(this.getVersion(), "fortress regions");
		}
	}

	@Override
	public boolean canStart(Data<Fortress> data, long structureSeed, ChunkRand rand) {
		if(this.getVersion().isOlderThan(MCVersion.v1_16)) {
			rand.setWeakSeed(structureSeed, data.chunkX, data.chunkZ, this.getVersion());
			rand.nextInt();
			if(rand.nextInt(3) != 0)return false;
			if(data.chunkX != (data.chunkX & ~15) + rand.nextInt(8) + 4)return false;
			if(data.chunkZ != (data.chunkZ & ~15) + rand.nextInt(8) + 4)return false;
			return true;
		}

		return super.canStart(data, structureSeed, rand) && rand.nextInt(5) < 2;
	}

	@Override
	public CPos getInRegion(long structureSeed, int regionX, int regionZ, ChunkRand rand) {
		if(this.getVersion().isOlderThan(MCVersion.v1_16)) {
			rand.setWeakSeed(structureSeed, regionX << 4, regionZ << 4, this.getVersion());
			rand.nextInt();
			if(rand.nextInt(3) != 0)return null;
			return new CPos((regionX << 4) + rand.nextInt(8) + 4, (regionZ << 4) + rand.nextInt(8) + 4);
		}

		CPos fortress = super.getInRegion(structureSeed, regionX, regionZ, rand);
		return rand.nextInt(5) < 2 ? fortress : null;
	}

	@Override
	public boolean canSpawn(int chunkX, int chunkZ, BiomeSource source) {
		int x = this.getVersion().isOlderThan(MCVersion.v1_16) ? (chunkX << 4) + 9 : (chunkX << 2) + 2;
		int z = this.getVersion().isOlderThan(MCVersion.v1_16) ? (chunkZ << 4) + 9 : (chunkZ << 2) + 2;
		return this.isValidBiome(this.getVersion().isOlderThan(MCVersion.v1_16)
				? source.getBiome(x, 0, z) : source.getBiomeForNoiseGen(x, 0, z));
	}

	@Override
	public boolean isValidDimension(Dimension dimension) {
		return dimension == Dimension.NETHER;
	}

	@Override
	public boolean isValidBiome(Biome biome) {
		return biome == Biome.BASALT_DELTAS || biome == Biome.CRIMSON_FOREST || biome == Biome.NETHER_WASTES
				|| biome == Biome.SOUL_SAND_VALLEY || biome == Biome.WARPED_FOREST;
	}

}
