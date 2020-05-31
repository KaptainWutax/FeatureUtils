package kaptainwutax.featureutils.structure;

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
	public boolean test(Data<?> data, long structureSeed, ChunkRand rand) {
		if(!super.test(data, structureSeed, rand))return false;
		return rand.nextInt(6) >= 2;
	}

}
