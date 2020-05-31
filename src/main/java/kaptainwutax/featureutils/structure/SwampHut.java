package kaptainwutax.featureutils.structure;

import kaptainwutax.seedutils.mc.MCVersion;
import kaptainwutax.seedutils.mc.VersionMap;

public class SwampHut extends OldStructure {

	public static final VersionMap<OldStructure.Config> CONFIGS = new VersionMap<OldStructure.Config>()
			.add(MCVersion.v1_7, new OldStructure.Config(14357617))
			.add(MCVersion.v1_13, new OldStructure.Config(14357620));

	public SwampHut(MCVersion version) {
		super(CONFIGS.getAsOf(version), version);
	}

	public SwampHut(RegionStructure.Config config) {
		super(config, null);
	}

}
