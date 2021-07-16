package kaptainwutax.featureutils.decorator.ore.overworld;

import kaptainwutax.featureutils.decorator.ore.HeightProvider;
import kaptainwutax.featureutils.decorator.ore.RegularOreDecorator;
import kaptainwutax.mcutils.block.Blocks;
import kaptainwutax.mcutils.version.MCVersion;
import kaptainwutax.mcutils.version.VersionMap;

public class CopperOre extends RegularOreDecorator<RegularOreDecorator.Config, RegularOreDecorator.Data<CopperOre>> {

	public static final VersionMap<Config> CONFIGS = new VersionMap<Config>()
		.add(MCVersion.v1_17, new Config(13, 6, 10, 6, HeightProvider.triangleRange(0, 96), Blocks.COPPER_ORE, BASE_STONE_OVERWORLD));

	public CopperOre(MCVersion version) {
		super(CONFIGS.getAsOf(version), version);
	}

	@Override
	public String getName() {
		return name();
	}

	public static String name() {
		return "copper_ore";
	}

}
