package kaptainwutax.featureutils.structure;

import kaptainwutax.biomeutils.Biome;
import kaptainwutax.biomeutils.BiomeSource;
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
	public boolean canSpawn(Data<?> data, BiomeSource source) {
		if(!super.canSpawn(data, source))return false;
		//TODO: Add area check!
		return true;
	}

	@Override
	public boolean isValidBiome(Biome biome) {
		return biome == Biome.DEEP_COLD_OCEAN || biome == Biome.DEEP_FROZEN_OCEAN || biome == Biome.DEEP_LUKEWARM_OCEAN
				|| biome == Biome.DEEP_OCEAN || biome == Biome.DEEP_WARM_OCEAN;
	}

}
