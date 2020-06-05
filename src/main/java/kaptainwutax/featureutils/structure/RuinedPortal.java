package kaptainwutax.featureutils.structure;

import kaptainwutax.biomeutils.Biome;
import kaptainwutax.seedutils.mc.MCVersion;
import kaptainwutax.seedutils.mc.VersionMap;

public class RuinedPortal extends UniformStructure<RuinedPortal> {

	public static final VersionMap<RegionStructure.Config> CONFIGS = new VersionMap<RegionStructure.Config>()
			.add(MCVersion.v1_16, new RegionStructure.Config(40, 15, 34222645));

	public RuinedPortal(MCVersion version) {
		super(CONFIGS.getAsOf(version), version);
	}

	public RuinedPortal(RegionStructure.Config config) {
		super(config, null);
	}

	@Override
	public boolean isValidBiome(Biome biome) {
		return biome != Biome.THE_VOID && biome.getCategory() != Biome.Category.THE_END;
	}

}
