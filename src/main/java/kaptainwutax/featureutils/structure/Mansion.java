package kaptainwutax.featureutils.structure;

import kaptainwutax.biomeutils.biome.Biome;
import kaptainwutax.biomeutils.biome.Biomes;
import kaptainwutax.biomeutils.source.BiomeSource;
import kaptainwutax.mcutils.state.Dimension;
import kaptainwutax.mcutils.version.MCVersion;
import kaptainwutax.mcutils.version.VersionMap;

public class Mansion extends TriangularStructure<Mansion> {

	public static final VersionMap<RegionStructure.Config> CONFIGS = new VersionMap<RegionStructure.Config>()
			.add(MCVersion.v1_11, new RegionStructure.Config(80, 20, 10387319));

	public Mansion(MCVersion version) {
		this(CONFIGS.getAsOf(version), version);
	}

	public Mansion(RegionStructure.Config config, MCVersion version) {
		super(config, version);
	}

	public static String name() {
		return "mansion";
	}

	@Override
	public boolean canSpawn(int chunkX, int chunkZ, BiomeSource source) {
		if (!super.canSpawn(chunkX, chunkZ, source)) return false;
		return source.iterateUniqueBiomes((chunkX << 4) + 9, (chunkZ << 4) + 9, 32, this::isValidBiome);
	}

	@Override
  public Dimension getValidDimension() {
		return  Dimension.OVERWORLD;
	}

	@Override
	public boolean isValidBiome(Biome biome) {
		return biome == Biomes.DARK_FOREST || biome == Biomes.DARK_FOREST_HILLS;
	}

}
