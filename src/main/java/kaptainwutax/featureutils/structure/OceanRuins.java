package kaptainwutax.featureutils.structure;

import kaptainwutax.biomeutils.Biome;
import kaptainwutax.seedutils.mc.MCVersion;
import kaptainwutax.seedutils.mc.VersionMap;

public class OceanRuins extends UniformStructure {

	public static final VersionMap<RegionStructure.Config> CONFIGS = new VersionMap<RegionStructure.Config>()
			.add(MCVersion.v1_13, new RegionStructure.Config(16, 8, 14357621))
			.add(MCVersion.v1_16, new RegionStructure.Config(20, 8, 14357621));

	public OceanRuins(MCVersion version) {
		super(CONFIGS.getAsOf(version), version);
	}

	public OceanRuins(RegionStructure.Config config) {
		super(config, null);
	}

	@Override
	public boolean isValidBiome(Biome biome) {
		return biome.getCategory() == Biome.Category.OCEAN;
	}

}
