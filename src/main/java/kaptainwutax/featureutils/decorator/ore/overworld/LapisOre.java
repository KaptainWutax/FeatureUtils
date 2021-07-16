package kaptainwutax.featureutils.decorator.ore.overworld;

import kaptainwutax.featureutils.decorator.ore.HeightProvider;
import kaptainwutax.featureutils.decorator.ore.RegularOreDecorator;
import kaptainwutax.mcutils.block.Blocks;
import kaptainwutax.mcutils.version.MCVersion;
import kaptainwutax.mcutils.version.VersionMap;

public class LapisOre extends RegularOreDecorator<RegularOreDecorator.Config, RegularOreDecorator.Data<LapisOre>> {

	public static final VersionMap<Config> CONFIGS = new VersionMap<Config>()
		.add(MCVersion.v1_13, new Config(10, 4, 7, 1, HeightProvider.depthAverage(16, 16), Blocks.LAPIS_ORE, BASE_STONE_OVERWORLD))
		.add(MCVersion.v1_16, new Config(10, 6, 7, 1, HeightProvider.depthAverage(16, 16), Blocks.LAPIS_ORE, BASE_STONE_OVERWORLD))
		.add(MCVersion.v1_17, new Config(12, 6, 7, 1, HeightProvider.triangleRange(0, 30), Blocks.LAPIS_ORE, BASE_STONE_OVERWORLD));

	public LapisOre(MCVersion version) {
		super(CONFIGS.getAsOf(version), version);
	}

	@Override
	public String getName() {
		return name();
	}

	public static String name() {
		return "lapis_ore";
	}

}
