package kaptainwutax.featureutils.decorator.ore.overworld;

import kaptainwutax.featureutils.decorator.ore.HeightProvider;
import kaptainwutax.featureutils.decorator.ore.RegularOreDecorator;
import kaptainwutax.mcutils.block.Blocks;
import kaptainwutax.mcutils.version.MCVersion;
import kaptainwutax.mcutils.version.VersionMap;

public class GoldOre extends RegularOreDecorator<RegularOreDecorator.Config, RegularOreDecorator.Data<GoldOre>> {

	public static final VersionMap<Config> CONFIGS = new VersionMap<Config>()
		.add(MCVersion.v1_13, new Config(7, 4, 9, 2, HeightProvider.range(0, 0, 32), Blocks.GOLD_ORE, BASE_STONE_OVERWORLD))
		.add(MCVersion.v1_16, new Config(7, 6, 9, 2, HeightProvider.range(0, 0, 32), Blocks.GOLD_ORE, BASE_STONE_OVERWORLD))
		.add(MCVersion.v1_17, new Config(9, 6, 9, 2, HeightProvider.uniformRange(0, 31), Blocks.GOLD_ORE, BASE_STONE_OVERWORLD));

	public GoldOre(MCVersion version) {
		super(CONFIGS.getAsOf(version), version);
	}

	@Override
	public String getName() {
		return name();
	}

	public static String name() {
		return "gold_ore";
	}

}
