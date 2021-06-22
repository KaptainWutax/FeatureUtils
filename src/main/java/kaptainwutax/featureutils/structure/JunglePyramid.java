package kaptainwutax.featureutils.structure;

import kaptainwutax.biomeutils.biome.Biome;
import kaptainwutax.biomeutils.biome.Biomes;
import kaptainwutax.mcutils.state.Dimension;
import kaptainwutax.mcutils.version.MCVersion;
import kaptainwutax.mcutils.version.VersionMap;

public class JunglePyramid extends OldStructure<JunglePyramid> {

	public static final VersionMap<OldStructure.Config> CONFIGS = new VersionMap<OldStructure.Config>()
		.add(MCVersion.v1_8, new OldStructure.Config(14357617))
		.add(MCVersion.v1_13, new OldStructure.Config(14357619));

	public JunglePyramid(MCVersion version) {
		this(CONFIGS.getAsOf(version), version);
	}

	public JunglePyramid(RegionStructure.Config config, MCVersion version) {
		super(config, version);
	}

	public static String name() {
		return "jungle_pyramid";
	}

	@Override
	public Dimension getValidDimension() {
		return Dimension.OVERWORLD;
	}

	@Override
	public boolean isValidBiome(Biome biome) {
		return biome == Biomes.JUNGLE || biome == Biomes.JUNGLE_HILLS || biome == Biomes.BAMBOO_JUNGLE
			|| biome == Biomes.BAMBOO_JUNGLE_HILLS;
	}

}
