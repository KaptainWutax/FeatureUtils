package kaptainwutax.featureutils.structure;

import kaptainwutax.biomeutils.Biome;
import kaptainwutax.seedutils.mc.Dimension;
import kaptainwutax.seedutils.mc.MCVersion;
import kaptainwutax.seedutils.mc.VersionMap;

public class Shipwreck extends UniformStructure<Shipwreck> {
	private boolean isBeached=false;
	public static final VersionMap<RegionStructure.Config> CONFIGS = new VersionMap<RegionStructure.Config>()
			.add(MCVersion.v1_13, new RegionStructure.Config(15, 8, 165745295))
			.add(MCVersion.v1_13_1, new RegionStructure.Config(16, 8, 165745295))
			.add(MCVersion.v1_16, new RegionStructure.Config(24, 4, 165745295));

	public Shipwreck(MCVersion version) {
		this(CONFIGS.getAsOf(version), version);
	}

	public Shipwreck(RegionStructure.Config config, MCVersion version) {
		super(config, version);
	}

	@Override
	public boolean isValidDimension(Dimension dimension) {
		return dimension == Dimension.OVERWORLD;
	}

	public boolean isBeached(){
		return isBeached;
	}

	@Override
	public boolean isValidBiome(Biome biome) {
		isBeached=biome == Biome.BEACH || biome == Biome.SNOWY_BEACH;
		return biome.getCategory() == Biome.Category.OCEAN || isBeached;
	}

}
