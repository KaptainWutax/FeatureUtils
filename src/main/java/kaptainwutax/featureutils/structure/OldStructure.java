package kaptainwutax.featureutils.structure;

import kaptainwutax.seedutils.mc.MCVersion;

public abstract class OldStructure extends UniformStructure {

	public OldStructure(RegionStructure.Config config, MCVersion version) {
		super(config, version);
	}

	public static class Config extends RegionStructure.Config {
		public static final int SPACING = 32;
		public static final int SEPARATION = 8;

		public Config(int salt) {
			super(SPACING, SEPARATION, salt);
		}
	}

}
