package kaptainwutax.featureutils.decorator.ore.nether;

import kaptainwutax.biomeutils.biome.Biomes;
import kaptainwutax.featureutils.decorator.ore.HeightProvider;
import kaptainwutax.featureutils.decorator.ore.RegularOreDecorator;
import kaptainwutax.mcutils.block.Blocks;
import kaptainwutax.mcutils.state.Dimension;
import kaptainwutax.mcutils.version.MCVersion;
import kaptainwutax.mcutils.version.VersionMap;

public class NetherGoldOre extends RegularOreDecorator<RegularOreDecorator.Config, RegularOreDecorator.Data<NetherGoldOre>> {

	public static final VersionMap<Config> CONFIGS = new VersionMap<Config>()
		.add(MCVersion.v1_16, new Config(13, 7, 10, 10, HeightProvider.range(10, 20, 128), Blocks.NETHER_GOLD_ORE, NETHERRACK)
			.add(10, 7, Biomes.CRIMSON_FOREST)
			.add(11, 7, Biomes.WARPED_FOREST)
			.add(13, 7, 10, 20, Biomes.BASALT_DELTAS))
		.add(MCVersion.v1_17, new Config(13, 7, 10, 10, HeightProvider.uniformRange(10, 117), Blocks.NETHER_GOLD_ORE, NETHERRACK)
			.add(10, 7, Biomes.CRIMSON_FOREST)
			.add(11, 7, Biomes.WARPED_FOREST)
			.add(13, 7, 10, 20, Biomes.BASALT_DELTAS));

	public NetherGoldOre(MCVersion version) {
		super(CONFIGS.getAsOf(version), version);
	}

	@Override
	public String getName() {
		return name();
	}

	public static String name() {
		return "nether_gold_ore";
	}

	@Override
	public Dimension getValidDimension() {
		return Dimension.NETHER;
	}

}
