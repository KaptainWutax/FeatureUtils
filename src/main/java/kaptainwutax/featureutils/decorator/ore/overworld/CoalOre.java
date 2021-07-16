package kaptainwutax.featureutils.decorator.ore.overworld;

import kaptainwutax.featureutils.decorator.ore.HeightProvider;
import kaptainwutax.featureutils.decorator.ore.RegularOreDecorator;
import kaptainwutax.mcutils.block.Blocks;
import kaptainwutax.mcutils.version.MCVersion;
import kaptainwutax.mcutils.version.VersionMap;

public class CoalOre extends RegularOreDecorator<RegularOreDecorator.Config, RegularOreDecorator.Data<CoalOre>> {

	public static final VersionMap<Config> CONFIGS = new VersionMap<Config>()
		.add(MCVersion.v1_13, new Config(5, 4, 17, 20, HeightProvider.range(0, 0, 128), Blocks.COAL_ORE, BASE_STONE_OVERWORLD))
		.add(MCVersion.v1_16, new Config(5, 6, 17, 20, HeightProvider.range(0, 0, 128), Blocks.COAL_ORE, BASE_STONE_OVERWORLD))
		.add(MCVersion.v1_17, new Config(7, 6, 17, 20, HeightProvider.uniformRange(0, 127), Blocks.COAL_ORE, BASE_STONE_OVERWORLD));

	public CoalOre(MCVersion version) {
		super(CONFIGS.getAsOf(version), version);
	}

	@Override
	public String getName() {
		return name();
	}

	public static String name() {
		return "coal_ore";
	}

}
