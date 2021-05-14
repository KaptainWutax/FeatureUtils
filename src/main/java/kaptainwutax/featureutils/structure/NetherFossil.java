package kaptainwutax.featureutils.structure;

import kaptainwutax.biomeutils.biome.Biome;
import kaptainwutax.biomeutils.biome.Biomes;
import kaptainwutax.mcutils.block.Block;
import kaptainwutax.mcutils.block.Blocks;
import kaptainwutax.mcutils.rand.ChunkRand;
import kaptainwutax.mcutils.state.Dimension;
import kaptainwutax.mcutils.version.MCVersion;
import kaptainwutax.mcutils.version.VersionMap;
import kaptainwutax.terrainutils.ChunkGenerator;

public class NetherFossil extends UniformStructure<NetherFossil> {

	public static final VersionMap<RegionStructure.Config> CONFIGS = new VersionMap<RegionStructure.Config>()
			.add(MCVersion.v1_16, new RegionStructure.Config(2, 1, 14357921));

	public NetherFossil(MCVersion version) {
		this(CONFIGS.getAsOf(version), version);
	}

	public NetherFossil(RegionStructure.Config config, MCVersion version) {
		super(config, version);
	}

	public static String name() {
		return "nether_fossil";
	}

	@Override
	public boolean isValidDimension(Dimension dimension) {
		return dimension == Dimension.NETHER;
	}

	@Override
	public boolean isValidBiome(Biome biome) {
		return biome == Biomes.SOUL_SAND_VALLEY;
	}

	@Override
	public boolean isValidTerrain(ChunkGenerator generator, int chunkX, int chunkZ) {
		if (generator==null) return true;
		ChunkRand rand=new ChunkRand();
		rand.setCarverSeed(generator.getWorldSeed(),chunkX,chunkZ,this.getVersion());
		int x=(chunkX<<4)+rand.nextInt(16);
		int z=(chunkZ<<4)+rand.nextInt(16);
		int seaLevel=generator.getSeaLevel();
		int y=seaLevel+rand.nextInt(generator.getWorldHeight()-2-seaLevel);
		Block[] column=generator.getColumnAt(x,z);
		for (;y>seaLevel;--y) {
			Block block=column[y];
			Block blockDown=column[y-1];
			if (block== Blocks.AIR && blockDown==Blocks.NETHERRACK){
				break;
			}
		}
		return y > seaLevel;
	}
}
