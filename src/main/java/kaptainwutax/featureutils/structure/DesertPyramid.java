package kaptainwutax.featureutils.structure;

import kaptainwutax.biomeutils.biome.Biome;
import kaptainwutax.biomeutils.biome.Biomes;
import kaptainwutax.mcutils.state.Dimension;
import kaptainwutax.mcutils.version.MCVersion;
import kaptainwutax.mcutils.version.VersionMap;

public class DesertPyramid extends OldStructure<DesertPyramid> {

	public static final VersionMap<OldStructure.Config> CONFIGS = new VersionMap<OldStructure.Config>()
			.add(MCVersion.v1_8, new OldStructure.Config(14357617));

	public DesertPyramid(MCVersion version) {
		this(CONFIGS.getAsOf(version), version);
	}

	public DesertPyramid(RegionStructure.Config config, MCVersion version) {
		super(config, version);
	}

	public static String name() {
		return "desert_pyramid";
	}

	@Override
	public boolean isValidDimension(Dimension dimension) {
		return dimension == Dimension.OVERWORLD;
	}

	@Override
	public boolean isValidBiome(Biome biome) {
		return biome == Biomes.DESERT || biome == Biomes.DESERT_HILLS;
	}

}
