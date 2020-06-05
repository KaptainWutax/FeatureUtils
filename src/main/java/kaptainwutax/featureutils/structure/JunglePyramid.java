package kaptainwutax.featureutils.structure;

import kaptainwutax.biomeutils.Biome;
import kaptainwutax.seedutils.mc.MCVersion;
import kaptainwutax.seedutils.mc.VersionMap;

public class JunglePyramid extends OldStructure<JunglePyramid> {

	public static final VersionMap<OldStructure.Config> CONFIGS = new VersionMap<OldStructure.Config>()
			.add(MCVersion.v1_7, new OldStructure.Config(14357617))
			.add(MCVersion.v1_13, new OldStructure.Config(14357619));

	public JunglePyramid(MCVersion version) {
		super(CONFIGS.getAsOf(version), version);
	}

	public JunglePyramid(RegionStructure.Config config) {
		super(config, null);
	}

	@Override
	public boolean isValidBiome(Biome biome) {
		return biome == Biome.JUNGLE || biome == Biome.JUNGLE_HILLS || biome == Biome.BAMBOO_JUNGLE
				|| biome == Biome.BAMBOO_JUNGLE_HILLS;
	}

}
