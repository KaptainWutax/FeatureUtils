package kaptainwutax.featureutils.structure;

import kaptainwutax.biomeutils.Biome;
import kaptainwutax.seedutils.mc.MCVersion;
import kaptainwutax.seedutils.mc.VersionMap;

public class Igloo extends OldStructure {

	public static final VersionMap<OldStructure.Config> CONFIGS = new VersionMap<OldStructure.Config>()
			.add(MCVersion.v1_9, new OldStructure.Config(14357617))
			.add(MCVersion.v1_13, new OldStructure.Config(14357618));

	public Igloo(MCVersion version) {
		super(CONFIGS.getAsOf(version), version);
	}

	public Igloo(RegionStructure.Config config) {
		super(config, null);
	}

	@Override
	public boolean isValidBiome(Biome biome) {
		return biome == Biome.SNOWY_TAIGA || biome == Biome.SNOWY_TUNDRA;
	}

}
