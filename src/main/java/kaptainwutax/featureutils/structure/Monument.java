package kaptainwutax.featureutils.structure;

import kaptainwutax.biomeutils.Biome;
import kaptainwutax.biomeutils.source.BiomeSource;
import kaptainwutax.seedutils.mc.MCVersion;
import kaptainwutax.seedutils.mc.VersionMap;

public class Monument extends TriangularStructure {

	public static final VersionMap<RegionStructure.Config> CONFIGS = new VersionMap<RegionStructure.Config>()
			.add(MCVersion.v1_8, new RegionStructure.Config(32, 5, 10387313));

	public Monument(MCVersion version) {
		super(CONFIGS.getAsOf(version), version);
	}

	public Monument(RegionStructure.Config config) {
		super(config, null);
	}

	@Override
	public boolean canSpawn(int chunkX, int chunkZ, BiomeSource source) {
		if(!super.canSpawn(chunkX, chunkZ, source))return false;

		if(!source.iterateUniqueBiomes((chunkX << 4) + 9, (chunkZ << 4) + 9, 16, this::isValidBiome)) {
			return false;
		}

		return source.iterateUniqueBiomes((chunkX << 4) + 9, (chunkZ << 4) + 9, 29, this::isOceanOrRiver);
	}

	public boolean isOceanOrRiver(Biome biome) {
		return biome.getCategory() == Biome.Category.OCEAN || biome.getCategory() == Biome.Category.RIVER;
	}

	@Override
	public boolean isValidBiome(Biome biome) {
		return biome == Biome.DEEP_COLD_OCEAN || biome == Biome.DEEP_FROZEN_OCEAN || biome == Biome.DEEP_LUKEWARM_OCEAN
				|| biome == Biome.DEEP_OCEAN || biome == Biome.DEEP_WARM_OCEAN;
	}

}
