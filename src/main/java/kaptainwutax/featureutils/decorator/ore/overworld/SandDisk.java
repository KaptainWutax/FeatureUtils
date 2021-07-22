package kaptainwutax.featureutils.decorator.ore.overworld;

import kaptainwutax.biomeutils.biome.Biome;
import kaptainwutax.featureutils.decorator.ore.HeightProvider;
import kaptainwutax.featureutils.decorator.ore.SphereOreDecorator;
import kaptainwutax.mcutils.block.Blocks;
import kaptainwutax.mcutils.version.MCVersion;
import kaptainwutax.mcutils.version.VersionMap;

public class SandDisk extends SphereOreDecorator<SphereOreDecorator.Config, SphereOreDecorator.Data<SandDisk>> {
	public static final VersionMap<Config> CONFIGS = new VersionMap<Config>()
		.add(MCVersion.v1_13, new Config(11, 4, 2, 3, HeightProvider.spreadRange(2, 4), Blocks.SAND, DIRT_GRASS))
		.add(MCVersion.v1_16, new Config(11, 6, 2, 3, HeightProvider.spreadRange(2, 4), Blocks.SAND, DIRT_GRASS))
		.add(MCVersion.v1_17, new Config(14, 6, 2, 3, HeightProvider.spreadRange(2, 4), Blocks.SAND, DIRT_GRASS));

	public SandDisk(MCVersion version) {
		super(CONFIGS.getAsOf(version), version);
	}

	@Override
	public boolean isValidBiome(Biome biome) {
		return super.isValidBiome(biome) && biome.getCategory() != Biome.Category.SWAMP;
	}

	@Override
	public String getName() {
		return name();
	}

	public static String name() {
		return "sand_disk";
	}
}
