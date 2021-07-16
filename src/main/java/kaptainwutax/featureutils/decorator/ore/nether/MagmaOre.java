package kaptainwutax.featureutils.decorator.ore.nether;

import kaptainwutax.biomeutils.biome.Biomes;
import kaptainwutax.featureutils.decorator.ore.HeightProvider;
import kaptainwutax.featureutils.decorator.ore.RegularOreDecorator;
import kaptainwutax.mcutils.block.Blocks;
import kaptainwutax.mcutils.state.Dimension;
import kaptainwutax.mcutils.version.MCVersion;
import kaptainwutax.mcutils.version.VersionMap;

public class MagmaOre extends RegularOreDecorator<RegularOreDecorator.Config, RegularOreDecorator.Data<MagmaOre>> {

	public static final VersionMap<Config> CONFIGS = new VersionMap<Config>()
		.add(MCVersion.v1_13, new Config(8, 5, 33, 4, HeightProvider.custom(r -> 32 - 5 + r.nextInt(10)), Blocks.MAGMA_BLOCK, NETHERRACK))
		.add(MCVersion.v1_16, new Config(9, 7, 33, 4, HeightProvider.custom(r -> 32 - 5 + r.nextInt(10)), Blocks.MAGMA_BLOCK, NETHERRACK)
			.add(8, 7, Biomes.SOUL_SAND_VALLEY)
			.add(6, 7, Biomes.CRIMSON_FOREST)
			.add(7, 7, Biomes.WARPED_FOREST)
			.add(11, 7, Biomes.BASALT_DELTAS))
		.add(MCVersion.v1_17, new Config(9, 7, 33, 4, HeightProvider.uniformRange(27, 36), Blocks.MAGMA_BLOCK, NETHERRACK)
			.add(8, 7, Biomes.SOUL_SAND_VALLEY)
			.add(6, 7, Biomes.CRIMSON_FOREST)
			.add(7, 7, Biomes.WARPED_FOREST)
			.add(11, 7, Biomes.BASALT_DELTAS));

	public MagmaOre(MCVersion version) {
		super(CONFIGS.getAsOf(version), version);
	}

	@Override
	public String getName() {
		return name();
	}

	public static String name() {
		return "magma_ore";
	}

	@Override
	public Dimension getValidDimension() {
		return Dimension.NETHER;
	}

}
