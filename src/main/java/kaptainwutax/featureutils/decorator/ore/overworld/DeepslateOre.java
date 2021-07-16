package kaptainwutax.featureutils.decorator.ore.overworld;

import kaptainwutax.featureutils.decorator.ore.HeightProvider;
import kaptainwutax.featureutils.decorator.ore.RegularOreDecorator;
import kaptainwutax.mcutils.block.Blocks;
import kaptainwutax.mcutils.version.MCVersion;
import kaptainwutax.mcutils.version.VersionMap;

public class DeepslateOre extends RegularOreDecorator<RegularOreDecorator.Config, RegularOreDecorator.Data<DeepslateOre>> {

	public static final VersionMap<Config> CONFIGS = new VersionMap<Config>()
		.add(MCVersion.v1_17, new Config(6, 6, 64, 2, HeightProvider.uniformRange(0, 16), Blocks.DEEPSLATE, BASE_STONE_OVERWORLD));

	public DeepslateOre(MCVersion version) {
		super(CONFIGS.getAsOf(version), version);
	}

	@Override
	public String getName() {
		return name();
	}

	public static String name() {
		return "deepslate_ore";
	}

}
