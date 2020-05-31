package kaptainwutax.featureutils.structure;

import kaptainwutax.seedutils.mc.MCVersion;
import kaptainwutax.seedutils.mc.VersionMap;

public class RuinedPortal extends UniformStructure {

	public static final VersionMap<RegionStructure.Config> CONFIGS = new VersionMap<RegionStructure.Config>()
			.add(MCVersion.v1_16, new RegionStructure.Config(40, 15, 34222645));

	public RuinedPortal(MCVersion version) {
		super(CONFIGS.getAsOf(version), version);
	}

	public RuinedPortal(RegionStructure.Config config) {
		super(config, null);
	}

}
