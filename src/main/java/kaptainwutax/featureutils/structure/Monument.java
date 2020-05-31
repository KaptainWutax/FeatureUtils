package kaptainwutax.featureutils.structure;

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

}
