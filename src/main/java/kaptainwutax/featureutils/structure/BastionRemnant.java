package kaptainwutax.featureutils.structure;

import kaptainwutax.biomeutils.Biome;
import kaptainwutax.seedutils.mc.ChunkRand;
import kaptainwutax.seedutils.mc.MCVersion;
import kaptainwutax.seedutils.mc.VersionMap;
import kaptainwutax.seedutils.mc.pos.CPos;
import kaptainwutax.seedutils.util.UnsupportedVersion;

public class BastionRemnant extends UniformStructure<BastionRemnant> {

	public static final VersionMap<RegionStructure.Config> CONFIGS = new VersionMap<RegionStructure.Config>()
			.add(MCVersion.v1_16, new RegionStructure.Config(30, 4, 30084232));

	public BastionRemnant(MCVersion version) {
		super(CONFIGS.getAsOf(version), version);
	}

	public BastionRemnant(RegionStructure.Config config) {
		super(config, null);
	}

	@Override
	public boolean canStart(Data<BastionRemnant> data, long structureSeed, ChunkRand rand) {
		if(!super.canStart(data, structureSeed, rand))return false;
		return rand.nextInt(5) >= 2;
	}

	@Override
	public CPos getInRegion(long structureSeed, int regionX, int regionZ, ChunkRand rand) {
		CPos bastion = super.getInRegion(structureSeed, regionX, regionZ, rand);
		return rand.nextInt(5) >= 2 ? bastion : null;
	}

	@Override
	public boolean isValidBiome(Biome biome) {
		return biome == Biome.NETHER_WASTES || biome == Biome.SOUL_SAND_VALLEY || biome == Biome.WARPED_FOREST
				|| biome == Biome.CRIMSON_FOREST;
	}

}
