package kaptainwutax.featureutils.decorator.ore.nether;

import kaptainwutax.biomeutils.biome.Biome;
import kaptainwutax.biomeutils.biome.Biomes;
import kaptainwutax.featureutils.decorator.ore.HeightProvider;
import kaptainwutax.featureutils.decorator.ore.RegularOreDecorator;
import kaptainwutax.mcutils.block.Blocks;
import kaptainwutax.mcutils.state.Dimension;
import kaptainwutax.mcutils.version.MCVersion;
import kaptainwutax.mcutils.version.VersionMap;

public class BlackstoneOre extends RegularOreDecorator<RegularOreDecorator.Config, RegularOreDecorator.Data<BlackstoneOre>> {

	public static final VersionMap<Config> CONFIGS = new VersionMap<Config>()
		.add(MCVersion.v1_16, new Config(12, 7, 33, 2, HeightProvider.range(5, 10, 37), Blocks.BLACKSTONE, NETHERRACK)
			.add(9, 7, Biomes.CRIMSON_FOREST)
			.add(10, 7, Biomes.WARPED_FOREST))
		.add(MCVersion.v1_17, new Config(12, 7, 33, 2, HeightProvider.uniformRange(5, 31), Blocks.BLACKSTONE, NETHERRACK)
			.add(9, 7, Biomes.CRIMSON_FOREST)
			.add(10, 7, Biomes.WARPED_FOREST));

	public BlackstoneOre(MCVersion version) {
		super(CONFIGS.getAsOf(version), version);
	}

	@Override
	public String getName() {
		return name();
	}

	public static String name() {
		return "blackstone_ore";
	}

	@Override
	public Dimension getValidDimension() {
		return Dimension.NETHER;
	}

	@Override
	public boolean isValidBiome(Biome biome) {
		return biome == Biomes.NETHER_WASTES || biome == Biomes.SOUL_SAND_VALLEY || biome == Biomes.CRIMSON_FOREST || biome == Biomes.WARPED_FOREST;
	}

}
