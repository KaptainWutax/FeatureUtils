package kaptainwutax.featureutils.decorator.ore.overworld;

import kaptainwutax.featureutils.decorator.ore.HeightProvider;
import kaptainwutax.featureutils.decorator.ore.RegularOreDecorator;
import kaptainwutax.mcutils.block.Blocks;
import kaptainwutax.mcutils.version.MCVersion;
import kaptainwutax.mcutils.version.VersionMap;

public class DirtOre extends RegularOreDecorator<RegularOreDecorator.Config, RegularOreDecorator.Data<DirtOre>> {

	public static final VersionMap<Config> CONFIGS = new VersionMap<Config>()
		.add(MCVersion.v1_13, new Config(0, 4, 33, 10, HeightProvider.range(0, 0, 256), Blocks.DIRT, BASE_STONE_OVERWORLD))
		.add(MCVersion.v1_16, new Config(0, 6, 33, 10, HeightProvider.range(0, 0, 256), Blocks.DIRT, BASE_STONE_OVERWORLD))
		.add(MCVersion.v1_17, new Config(0, 6, 33, 10, HeightProvider.uniformRange(0, 255), Blocks.DIRT, BASE_STONE_OVERWORLD));

	public DirtOre(MCVersion version) {
		super(CONFIGS.getAsOf(version), version);
	}

	@Override
	public String getName() {
		return name();
	}

	public static String name() {
		return "dirt_ore";
	}

}
