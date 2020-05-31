package kaptainwutax.featureutils.structure;

import kaptainwutax.seedutils.mc.MCVersion;
import kaptainwutax.seedutils.mc.VersionMap;

public class Village extends OldStructure {

	public static final VersionMap<OldStructure.Config> CONFIGS = new VersionMap<OldStructure.Config>()
			.add(MCVersion.v1_7, new OldStructure.Config(10387312));

	public Village(MCVersion version) {
		super(CONFIGS.getAsOf(version), version);
	}

	public Village(RegionStructure.Config config) {
		super(config, null);
	}

}
