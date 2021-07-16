package kaptainwutax.featureutils.decorator.ore.overworld;

import kaptainwutax.featureutils.decorator.ore.HeightProvider;
import kaptainwutax.featureutils.decorator.ore.RegularOreDecorator;
import kaptainwutax.mcutils.block.Blocks;
import kaptainwutax.mcutils.version.MCVersion;
import kaptainwutax.mcutils.version.VersionMap;

public class GravelOre extends RegularOreDecorator<RegularOreDecorator.Config, RegularOreDecorator.Data<GravelOre>> {

	public static final VersionMap<Config> CONFIGS = new VersionMap<Config>()
		.add(MCVersion.v1_13, new Config(1, 4, 33, 8, HeightProvider.range(0, 0, 256), Blocks.GRAVEL, BASE_STONE_OVERWORLD))
		.add(MCVersion.v1_16, new Config(1, 6, 33, 8, HeightProvider.range(0, 0, 256), Blocks.GRAVEL, BASE_STONE_OVERWORLD))
		.add(MCVersion.v1_17, new Config(1, 6, 33, 8, HeightProvider.uniformRange(0, 255), Blocks.GRAVEL, BASE_STONE_OVERWORLD));

	public GravelOre(MCVersion version) {
		super(CONFIGS.getAsOf(version), version);
	}

	@Override
	public String getName() {
		return name();
	}

	public static String name() {
		return "gravel_ore";
	}

}
