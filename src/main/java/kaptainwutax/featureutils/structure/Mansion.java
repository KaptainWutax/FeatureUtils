package kaptainwutax.featureutils.structure;

import kaptainwutax.biomeutils.Biome;
import kaptainwutax.biomeutils.source.BiomeSource;
import kaptainwutax.seedutils.mc.MCVersion;
import kaptainwutax.seedutils.mc.VersionMap;

public class Mansion extends TriangularStructure<Mansion> {

	public static final VersionMap<RegionStructure.Config> CONFIGS = new VersionMap<RegionStructure.Config>()
			.add(MCVersion.v1_11, new RegionStructure.Config(80, 20, 10387319));

	public Mansion(MCVersion version) {
		super(CONFIGS.getAsOf(version), version);
	}

	public Mansion(RegionStructure.Config config) {
		super(config, null);
	}

	@Override
	public boolean canSpawn(int chunkX, int chunkZ, BiomeSource source) {
		if(!super.canSpawn(chunkX, chunkZ, source))return false;
		return source.iterateUniqueBiomes((chunkX << 4) + 9, (chunkZ << 4) + 9, 32, this::isValidBiome);
	}

	@Override
	public boolean isValidBiome(Biome biome) {
		return biome == Biome.DARK_FOREST || biome == Biome.DARK_FOREST_HILLS;
	}

}
