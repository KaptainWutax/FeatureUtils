package kaptainwutax.featureutils.structure;

import kaptainwutax.biomeutils.Biome;
import kaptainwutax.mcutils.rand.ChunkRand;
import kaptainwutax.mcutils.state.Dimension;
import kaptainwutax.mcutils.version.MCVersion;
import kaptainwutax.mcutils.version.VersionMap;

public class EndCity extends TriangularStructure<EndCity> {

	public static final VersionMap<RegionStructure.Config> CONFIGS = new VersionMap<RegionStructure.Config>()
			.add(MCVersion.v1_9, new RegionStructure.Config(20, 11, 10387313));

	public EndCity(MCVersion version) {
		this(CONFIGS.getAsOf(version), version);
	}

	public EndCity(RegionStructure.Config config, MCVersion version) {
		super(config, version);
	}

	public static String name() {
		return "end_city";
	}

	@Override
	public boolean canStart(Data<EndCity> data, long structureSeed, ChunkRand rand) {
		if (!super.canStart(data, structureSeed, rand)) return false;
		//TODO: add terrain check!
		return true;
	}

	@Override
	public boolean isValidDimension(Dimension dimension) {
		return dimension == Dimension.END;
	}

	@Override
	public boolean isValidBiome(Biome biome) {
		return biome == Biome.END_MIDLANDS || biome == Biome.END_HIGHLANDS;
	}

}
