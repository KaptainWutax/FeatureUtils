package kaptainwutax.featureutils.decorator.ore.overworld;

import kaptainwutax.featureutils.decorator.ore.HeightProvider;
import kaptainwutax.featureutils.decorator.ore.RegularOreDecorator;
import kaptainwutax.mcutils.block.Blocks;
import kaptainwutax.mcutils.version.MCVersion;
import kaptainwutax.mcutils.version.VersionMap;

public class RedstoneOre extends RegularOreDecorator<RegularOreDecorator.Config, RegularOreDecorator.Data<RedstoneOre>> {

	public static final VersionMap<Config> CONFIGS = new VersionMap<Config>()
		.add(MCVersion.v1_13, new Config(8, 4, 8, 8, HeightProvider.range(0, 0, 16), Blocks.REDSTONE_ORE, BASE_STONE_OVERWORLD))
		.add(MCVersion.v1_16, new Config(8, 6, 8, 8, HeightProvider.range(0, 0, 16), Blocks.REDSTONE_ORE, BASE_STONE_OVERWORLD))
		.add(MCVersion.v1_17, new Config(10, 6, 8, 8, HeightProvider.uniformRange(0, 15), Blocks.REDSTONE_ORE, BASE_STONE_OVERWORLD));

	public RedstoneOre(MCVersion version) {
		super(CONFIGS.getAsOf(version), version);
	}

	@Override
	public String getName() {
		return name();
	}

	public static String name() {
		return "redstone_ore";
	}

}
