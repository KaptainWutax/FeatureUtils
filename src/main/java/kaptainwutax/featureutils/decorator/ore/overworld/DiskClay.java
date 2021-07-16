package kaptainwutax.featureutils.decorator.ore.overworld;

import kaptainwutax.biomeutils.biome.Biomes;
import kaptainwutax.featureutils.decorator.ore.HeightProvider;
import kaptainwutax.featureutils.decorator.ore.SphereOreDecorator;
import kaptainwutax.mcutils.block.Blocks;
import kaptainwutax.mcutils.version.MCVersion;
import kaptainwutax.mcutils.version.VersionMap;

public class DiskClay extends SphereOreDecorator<SphereOreDecorator.Config, SphereOreDecorator.Data<DiskClay>> {
	public static final VersionMap<Config> CONFIGS = new VersionMap<Config>()
		.add(MCVersion.v1_16, new Config(12, 6, 2, 1, HeightProvider.spreadRange(2, 1), Blocks.CLAY, DIRT_CLAY)
			.add(11, 6, Biomes.SWAMP, Biomes.SWAMP_HILLS))
		.add(MCVersion.v1_17, new Config(15, 6, 2, 1, HeightProvider.spreadRange(2, 1), Blocks.CLAY, DIRT_CLAY)
			.add(14, 6, Biomes.SWAMP, Biomes.SWAMP_HILLS));

	public DiskClay(MCVersion version) {
		super(CONFIGS.getAsOf(version), version);
	}

	@Override
	public String getName() {
		return name();
	}

	public static String name() {
		return "disk_clay";
	}
}
