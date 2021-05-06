package kaptainwutax.featureutils.structure;

import kaptainwutax.biomeutils.biome.Biome;
import kaptainwutax.biomeutils.biome.Biomes;
import kaptainwutax.mcutils.state.Dimension;
import kaptainwutax.mcutils.version.MCVersion;
import kaptainwutax.mcutils.version.VersionMap;

public class Village extends OldStructure<Village> {

	public static final VersionMap<OldStructure.Config> CONFIGS = new VersionMap<OldStructure.Config>()
			.add(MCVersion.v1_8, new OldStructure.Config(10387312));

	public Village(MCVersion version) {
		this(CONFIGS.getAsOf(version), version);
	}

	public Village(RegionStructure.Config config, MCVersion version) {
		super(config, version);
	}

	public static String name() {
		return "village";
	}

	@Override
	public boolean isValidDimension(Dimension dimension) {
		return dimension == Dimension.OVERWORLD;
	}

	@Override
	public boolean isValidBiome(Biome biome) {
		if (biome == Biomes.PLAINS || biome == Biomes.DESERT || biome == Biomes.SAVANNA) return true;
		if (biome == Biomes.TAIGA && this.getVersion().isNewerOrEqualTo(MCVersion.v1_10)) return true;
		return biome == Biomes.SNOWY_TUNDRA && this.getVersion().isNewerOrEqualTo(MCVersion.v1_14);
	}

}
