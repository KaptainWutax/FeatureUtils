package kaptainwutax.featureutils.structure;

import kaptainwutax.seedutils.mc.MCVersion;
import kaptainwutax.seedutils.mc.VersionMap;

public class DesertPyramid extends OldStructure {

	public static final VersionMap<OldStructure.Config> CONFIGS = new VersionMap<OldStructure.Config>()
			.add(MCVersion.v1_7, new OldStructure.Config(14357617));

	public DesertPyramid(MCVersion version) {
		super(CONFIGS.getAsOf(version), version);
	}

	public DesertPyramid(RegionStructure.Config config) {
		super(config, null);
	}

}
