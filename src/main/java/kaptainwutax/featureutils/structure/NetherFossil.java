package kaptainwutax.featureutils.structure;

import kaptainwutax.biomeutils.Biome;
import kaptainwutax.seedutils.mc.ChunkRand;
import kaptainwutax.seedutils.mc.MCVersion;
import kaptainwutax.seedutils.mc.VersionMap;

public class NetherFossil extends UniformStructure<NetherFossil> {

	public static final VersionMap<RegionStructure.Config> CONFIGS = new VersionMap<RegionStructure.Config>()
			.add(MCVersion.v1_16, new RegionStructure.Config(2, 1, 14357921));

	public NetherFossil(MCVersion version) {
		super(CONFIGS.getAsOf(version), version);
	}

	public NetherFossil(RegionStructure.Config config) {
		super(config, null);
	}

	@Override
	public boolean canStart(Data<NetherFossil> data, long structureSeed, ChunkRand rand) {
		//Quick shortcut for fossils.
		return (data.chunkX & 1) == 0 && (data.chunkZ & 1) == 0;
	}

	@Override
	public boolean isValidBiome(Biome biome) {
		return biome == Biome.SOUL_SAND_VALLEY;
	}

}
