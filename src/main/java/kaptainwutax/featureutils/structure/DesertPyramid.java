package kaptainwutax.featureutils.structure;

import kaptainwutax.biomeutils.Biome;
import kaptainwutax.seedutils.mc.MCVersion;
import kaptainwutax.seedutils.mc.VersionMap;

public class DesertPyramid extends OldStructure<DesertPyramid> {

	public static final VersionMap<OldStructure.Config> CONFIGS = new VersionMap<OldStructure.Config>()
			.add(MCVersion.v1_8, new OldStructure.Config(14357617));

	public DesertPyramid(MCVersion version) {
		this(CONFIGS.getAsOf(version), version);
	}

	public DesertPyramid(RegionStructure.Config config, MCVersion version) {
		super(config, version);
	}

	@Override
	public boolean isValidBiome(Biome biome) {
		return biome == Biome.DESERT || biome == Biome.DESERT_HILLS;
	}

}
