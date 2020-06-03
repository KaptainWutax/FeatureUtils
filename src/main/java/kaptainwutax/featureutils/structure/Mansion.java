package kaptainwutax.featureutils.structure;

import kaptainwutax.biomeutils.Biome;
import kaptainwutax.biomeutils.BiomeSource;
import kaptainwutax.seedutils.mc.MCVersion;
import kaptainwutax.seedutils.mc.VersionMap;

public class Mansion extends TriangularStructure {

	public static final VersionMap<RegionStructure.Config> CONFIGS = new VersionMap<RegionStructure.Config>()
			.add(MCVersion.v1_11, new RegionStructure.Config(80, 20, 10387319));

	public Mansion(MCVersion version) {
		super(CONFIGS.getAsOf(version), version);
	}

	public Mansion(RegionStructure.Config config) {
		super(config, null);
	}

	@Override
	public boolean canSpawn(Data<?> data, BiomeSource source) {
		if(!super.canSpawn(data, source))return false;
		//TODO: Add area check!
		return true;
	}

	@Override
	public boolean isValidBiome(Biome biome) {
		return biome == Biome.DARK_FOREST || biome == Biome.DARK_FOREST_HILLS;
	}

}
