package kaptainwutax.featureutils.structure;

import kaptainwutax.biomeutils.Biome;
import kaptainwutax.seedutils.mc.MCVersion;
import kaptainwutax.seedutils.mc.VersionMap;

public class Shipwreck extends UniformStructure {

	public static final VersionMap<RegionStructure.Config> CONFIGS = new VersionMap<RegionStructure.Config>()
			.add(MCVersion.v1_13, new RegionStructure.Config(16, 8, 165745295))
			.add(MCVersion.v1_16, new RegionStructure.Config(24, 4, 165745295));

	public Shipwreck(MCVersion version) {
		super(CONFIGS.getAsOf(version), version);
	}

	public Shipwreck(RegionStructure.Config config) {
		super(config, null);
	}

	@Override
	public boolean isValidBiome(Biome biome) {
		return biome.getCategory() == Biome.Category.OCEAN || biome == Biome.BEACH || biome == Biome.SNOWY_BEACH;
	}

}
