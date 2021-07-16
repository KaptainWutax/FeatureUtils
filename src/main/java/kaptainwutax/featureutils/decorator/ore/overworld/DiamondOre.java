package kaptainwutax.featureutils.decorator.ore.overworld;

import kaptainwutax.featureutils.decorator.ore.HeightProvider;
import kaptainwutax.featureutils.decorator.ore.RegularOreDecorator;
import kaptainwutax.mcutils.block.Blocks;
import kaptainwutax.mcutils.version.MCVersion;
import kaptainwutax.mcutils.version.VersionMap;

public class DiamondOre extends RegularOreDecorator<RegularOreDecorator.Config, RegularOreDecorator.Data<DiamondOre>> {

	public static final VersionMap<Config> CONFIGS = new VersionMap<Config>()
		.add(MCVersion.v1_13, new Config(9, 4, 8, 1, HeightProvider.range(0, 0, 16), Blocks.DIAMOND_ORE, BASE_STONE_OVERWORLD))
		.add(MCVersion.v1_16, new Config(9, 6, 8, 1, HeightProvider.range(0, 0, 16), Blocks.DIAMOND_ORE, BASE_STONE_OVERWORLD))
		.add(MCVersion.v1_17, new Config(11, 6, 8, 1, HeightProvider.uniformRange(0, 16), Blocks.DIAMOND_ORE, BASE_STONE_OVERWORLD))
		.add(MCVersion.v1_17_1, new Config(11, 6, 8, 1, HeightProvider.uniformRange(0, 15), Blocks.DIAMOND_ORE, BASE_STONE_OVERWORLD));

	public DiamondOre(MCVersion version) {
		super(CONFIGS.getAsOf(version), version);
	}

	@Override
	public String getName() {
		return name();
	}

	public static String name() {
		return "diamond_ore";
	}

}
