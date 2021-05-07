package kaptainwutax.featureutils.structure;

import kaptainwutax.biomeutils.biome.Biome;
import kaptainwutax.biomeutils.biome.Biomes;
import kaptainwutax.featureutils.loot.ILoot;
import kaptainwutax.featureutils.structure.generator.structure.DesertPyramidGenerator;
import kaptainwutax.featureutils.structure.generator.Generator;
import kaptainwutax.mcutils.state.Dimension;
import kaptainwutax.mcutils.version.MCVersion;
import kaptainwutax.mcutils.version.VersionMap;

public class DesertPyramid extends OldStructure<DesertPyramid> implements ILoot {

	public static final VersionMap<OldStructure.Config> CONFIGS = new VersionMap<OldStructure.Config>()
			.add(MCVersion.v1_8, new OldStructure.Config(14357617));

	public DesertPyramid(MCVersion version) {
		this(CONFIGS.getAsOf(version), version);
	}

	public DesertPyramid(RegionStructure.Config config, MCVersion version) {
		super(config, version);
	}

	public static String name() {
		return "desert_pyramid";
	}

	@Override
	public boolean isValidDimension(Dimension dimension) {
		return dimension == Dimension.OVERWORLD;
	}

	@Override
	public boolean isValidBiome(Biome biome) {
		return biome == Biomes.DESERT || biome == Biomes.DESERT_HILLS;
	}

	@Override
	public int getDecorationSalt() {
		return 40003;
	}

	@Override
	public boolean isCorrectGenerator(Generator generator) {
		return generator instanceof DesertPyramidGenerator;
	}

	@Override
	public SpecificCalls getSpecificCalls() {
		return null;
	}

	@Override
	public boolean shouldAdvanceInChunks() {
		return false;
	}
}
