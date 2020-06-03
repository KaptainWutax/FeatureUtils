package kaptainwutax.featureutils.structure;

import kaptainwutax.biomeutils.Biome;
import kaptainwutax.seedutils.mc.ChunkRand;
import kaptainwutax.seedutils.mc.MCVersion;
import kaptainwutax.seedutils.mc.VersionMap;

public class BastionRemnant extends UniformStructure {

	public static final VersionMap<RegionStructure.Config> CONFIGS = new VersionMap<RegionStructure.Config>()
			.add(MCVersion.v1_16, new RegionStructure.Config(30, 4, 30084232));

	public BastionRemnant(MCVersion version) {
		super(CONFIGS.getAsOf(version), version);
	}

	public BastionRemnant(RegionStructure.Config config) {
		super(config, null);
	}

	@Override
	public boolean canStart(Data<?> data, long structureSeed, ChunkRand rand) {
		if(!super.canStart(data, structureSeed, rand))return false;
		return rand.nextInt(6) >= 2;
	}

	@Override
	public boolean isValidBiome(Biome biome) {
		return biome == Biome.NETHER_WASTES || biome == Biome.SOUL_SAND_VALLEY || biome == Biome.WARPED_FOREST
				|| biome == Biome.CRIMSON_FOREST;
	}

}
