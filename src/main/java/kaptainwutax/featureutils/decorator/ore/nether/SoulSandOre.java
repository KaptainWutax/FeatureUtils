package kaptainwutax.featureutils.decorator.ore.nether;

import kaptainwutax.biomeutils.biome.Biome;
import kaptainwutax.biomeutils.biome.Biomes;
import kaptainwutax.featureutils.decorator.ore.HeightProvider;
import kaptainwutax.featureutils.decorator.ore.RegularOreDecorator;
import kaptainwutax.mcutils.block.Blocks;
import kaptainwutax.mcutils.state.Dimension;
import kaptainwutax.mcutils.version.MCVersion;
import kaptainwutax.mcutils.version.VersionMap;

public class SoulSandOre extends RegularOreDecorator<RegularOreDecorator.Config, RegularOreDecorator.Data<SoulSandOre>> {

	public static final VersionMap<Config> CONFIGS = new VersionMap<Config>()
		.add(MCVersion.v1_16, new Config(10, 7, 12, 12, HeightProvider.range(0, 0, 32), Blocks.SOUL_SAND, NETHERRACK))
		.add(MCVersion.v1_17, new Config(10, 7, 12, 12, HeightProvider.uniformRange(0, 31), Blocks.SOUL_SAND, NETHERRACK));

	public SoulSandOre(MCVersion version) {
		super(CONFIGS.getAsOf(version), version);
	}

	@Override
	public String getName() {
		return name();
	}

	public static String name() {
		return "soulsand_ore";
	}

	@Override
	public Dimension getValidDimension() {
		return Dimension.NETHER;
	}

	@Override
	public boolean isValidBiome(Biome biome) {
		return biome == Biomes.SOUL_SAND_VALLEY;
	}

}
