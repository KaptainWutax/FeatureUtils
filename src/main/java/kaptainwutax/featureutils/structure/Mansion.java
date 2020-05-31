package kaptainwutax.featureutils.structure;

import kaptainwutax.seedutils.mc.MCVersion;
import kaptainwutax.seedutils.mc.VersionMap;

public class Mansion extends TriangularStructure {

	public static final VersionMap<RegionStructure.Config> CONFIGS = new VersionMap<RegionStructure.Config>()
			.add(MCVersion.v1_11, new RegionStructure.Config(80, 20, 10387319));

	public Mansion(MCVersion version) {
		super(CONFIGS.getAsOf(version), version);
	}

	public Mansion(RegionStructure.Config config) {
		super(config, null);
	}

}
