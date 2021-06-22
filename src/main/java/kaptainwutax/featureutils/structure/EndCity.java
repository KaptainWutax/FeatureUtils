package kaptainwutax.featureutils.structure;

import kaptainwutax.biomeutils.biome.Biome;
import kaptainwutax.biomeutils.biome.Biomes;
import kaptainwutax.biomeutils.source.BiomeSource;
import kaptainwutax.featureutils.loot.ILoot;
import kaptainwutax.featureutils.structure.generator.Generator;
import kaptainwutax.featureutils.structure.generator.structure.EndCityGenerator;
import kaptainwutax.mcutils.rand.ChunkRand;
import kaptainwutax.mcutils.state.Dimension;
import kaptainwutax.mcutils.util.block.BlockRotation;
import kaptainwutax.mcutils.util.pos.CPos;
import kaptainwutax.mcutils.version.MCVersion;
import kaptainwutax.mcutils.version.VersionMap;
import kaptainwutax.terrainutils.TerrainGenerator;

public class EndCity extends TriangularStructure<EndCity> implements ILoot {

	public static final VersionMap<RegionStructure.Config> CONFIGS = new VersionMap<RegionStructure.Config>()
		.add(MCVersion.v1_9, new RegionStructure.Config(20, 11, 10387313));

	public EndCity(MCVersion version) {
		this(CONFIGS.getAsOf(version), version);
	}

	public EndCity(RegionStructure.Config config, MCVersion version) {
		super(config, version);
	}

	public static String name() {
		return "end_city";
	}

	@Override
	public boolean canStart(Data<EndCity> data, long structureSeed, ChunkRand rand) {
		return super.canStart(data, structureSeed, rand);
	}

	@Override
	public Dimension getValidDimension() {
		return Dimension.END;
	}

	@Override
	public boolean isValidBiome(Biome biome) {
		return biome == Biomes.END_MIDLANDS || biome == Biomes.END_HIGHLANDS;
	}

	@Override
	public boolean isValidTerrain(TerrainGenerator generator, int chunkX, int chunkZ) {
		return getAverageYPosition(generator, chunkX, chunkZ) >= 60;
	}

	@Override
	public boolean isCorrectGenerator(Generator generator) {
		return generator instanceof EndCityGenerator;
	}

	@Override
	public boolean canSpawn(CPos cPos, BiomeSource source) {
		return this.canSpawn(cPos.getX(), cPos.getZ(), source);
	}

	@Override
	public boolean canSpawn(int chunkX, int chunkZ, BiomeSource source) {
		if(this.getVersion().isOlderThan(MCVersion.v1_16)) {
			if(this.getVersion().isNewerOrEqualTo(MCVersion.v1_13)) {
				return this.isValidBiome(source.getBiome((chunkX << 4) + 9, 0, (chunkZ << 4) + 9));
			} else {
				// back then it was isIslandChunk(chunkX,chunkZ)
				// but since we do x>>=2 we need to counter that here
				return this.isValidBiome(source.getBiomeForNoiseGen((chunkX << 2), 0, (chunkZ << 2)));
			}
		}
		return this.isValidBiome(source.getBiomeForNoiseGen((chunkX << 2) + 2, 0, (chunkZ << 2) + 2));
	}

	@Override
	public SpecificCalls getSpecificCalls() {
		return null;
	}

	public static int getAverageYPosition(TerrainGenerator generator, int chunkX, int chunkZ) {
		@SuppressWarnings("IntegerMultiplicationImplicitCastToLong")
		ChunkRand random = new ChunkRand(chunkX + chunkZ * 10387313);
		BlockRotation rotation = BlockRotation.getRandom(random);
		int xOffset = 5;
		int zOffset = 5;
		if(rotation == BlockRotation.CLOCKWISE_90) {
			xOffset = -5;
		} else if(rotation == BlockRotation.CLOCKWISE_180) {
			xOffset = -5;
			zOffset = -5;
		} else if(rotation == BlockRotation.COUNTERCLOCKWISE_90) {
			zOffset = -5;
		}

		int posX = (chunkX << 4) + 7;
		int posZ = (chunkZ << 4) + 7;
		int center = generator.getHeightInGround(posX, posZ);
		int s = generator.getHeightInGround(posX, posZ + zOffset); // SOUTH
		int e = generator.getHeightInGround(posX + xOffset, posZ); //  EAST
		int se = generator.getHeightInGround(posX + xOffset, posZ + zOffset); // SOUTH EAST
		return Math.min(Math.min(center, s), Math.min(e, se));
	}

	@Override
	public int getDecorationSalt() {
		return 40010;
	}
}
