package kaptainwutax.featureutils.structure;

import kaptainwutax.biomeutils.biome.Biome;
import kaptainwutax.mcutils.state.Dimension;
import kaptainwutax.mcutils.version.MCVersion;
import kaptainwutax.mcutils.version.VersionMap;

public class OceanRuin extends UniformStructure<OceanRuin> {

	public static final VersionMap<RegionStructure.Config> CONFIGS = new VersionMap<RegionStructure.Config>()
		.add(MCVersion.v1_13, new RegionStructure.Config(16, 8, 14357621))
		.add(MCVersion.v1_16, new RegionStructure.Config(20, 8, 14357621));

	public OceanRuin(MCVersion version) {
		this(CONFIGS.getAsOf(version), version);
	}

	public OceanRuin(RegionStructure.Config config, MCVersion version) {
		super(config, version);
	}

	public static String name() {
		return "ocean_ruin";
	}

	@Override
	public Dimension getValidDimension() {
		return Dimension.OVERWORLD;
	}

	@Override
	public boolean isValidBiome(Biome biome) {
		return biome.getCategory() == Biome.Category.OCEAN;
	}

}
