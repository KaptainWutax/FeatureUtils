package kaptainwutax.featureutils.decorator.ore.overworld;

import kaptainwutax.featureutils.decorator.ore.HeightProvider;
import kaptainwutax.featureutils.decorator.ore.RegularOreDecorator;
import kaptainwutax.mcutils.block.Blocks;
import kaptainwutax.mcutils.version.MCVersion;
import kaptainwutax.mcutils.version.VersionMap;

public class IronOre extends RegularOreDecorator<RegularOreDecorator.Config, RegularOreDecorator.Data<IronOre>> {

	public static final VersionMap<Config> CONFIGS = new VersionMap<Config>()
		.add(MCVersion.v1_13, new Config(6, 4, 9, 20, HeightProvider.range(0, 0, 64), Blocks.IRON_ORE, BASE_STONE_OVERWORLD))
		.add(MCVersion.v1_16, new Config(6, 6, 9, 20, HeightProvider.range(0, 0, 64), Blocks.IRON_ORE, BASE_STONE_OVERWORLD))
		.add(MCVersion.v1_17, new Config(8, 6, 9, 20, HeightProvider.uniformRange(0, 63), Blocks.IRON_ORE, BASE_STONE_OVERWORLD));

	public IronOre(MCVersion version) {
		super(CONFIGS.getAsOf(version), version);
	}

	@Override
	public String getName() {
		return name();
	}

	public static String name() {
		return "iron_ore";
	}

}
