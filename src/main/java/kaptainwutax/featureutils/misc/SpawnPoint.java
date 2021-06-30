package kaptainwutax.featureutils.misc;

import kaptainwutax.biomeutils.biome.Biome;
import kaptainwutax.biomeutils.biome.Biomes;
import kaptainwutax.biomeutils.source.BiomeSource;
import kaptainwutax.biomeutils.source.OverworldBiomeSource;
import kaptainwutax.featureutils.Feature;
import kaptainwutax.mcutils.block.Block;
import kaptainwutax.mcutils.block.Blocks;
import kaptainwutax.mcutils.rand.ChunkRand;
import kaptainwutax.mcutils.state.Dimension;
import kaptainwutax.mcutils.util.pos.BPos;
import kaptainwutax.mcutils.util.pos.CPos;
import kaptainwutax.mcutils.version.MCVersion;
import kaptainwutax.seedutils.rand.JRand;
import kaptainwutax.terrainutils.TerrainGenerator;
import kaptainwutax.terrainutils.terrain.OverworldTerrainGenerator;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class SpawnPoint extends Feature<Feature.Config, SpawnPoint.Data> {
	public static final List<Biome> SPAWN_BIOMES = Arrays.asList(
		Biomes.PLAINS, Biomes.SUNFLOWER_PLAINS,
		Biomes.TAIGA, Biomes.TAIGA_HILLS,
		Biomes.FOREST, Biomes.WOODED_HILLS,
		Biomes.JUNGLE, Biomes.JUNGLE_HILLS
	);
	public static final List<Block> SPAWN_BLOCKS = Arrays.asList(Blocks.GRASS_BLOCK, Blocks.PODZOL);
	// PODZOL doesn't matter cause pregen/pre builders but whatever

	public SpawnPoint() {
		super(new Config(), null);
	}

	public static String name() {
		return "spawn";
	}

	// static method
	public static BPos getSpawn(OverworldTerrainGenerator generator) {
		return new SpawnPoint().getSpawnPoint(generator);
	}

	// static method
	public static BPos getApproximateSpawn(OverworldBiomeSource source) {
		return new SpawnPoint().getApproximateSpawnPoint(source);
	}

	public BPos getSpawnPoint(OverworldTerrainGenerator generator) {
		return this.getSpawnPoint((OverworldBiomeSource)generator.getBiomeSource(), generator, SPAWN_BIOMES, true);
	}

	public BPos getApproximateSpawnPoint(OverworldBiomeSource source) {
		return this.getSpawnPoint(source, null, SPAWN_BIOMES, false);
	}

	// generator can be null if truespawn is false
	private BPos getSpawnPoint(OverworldBiomeSource source, OverworldTerrainGenerator generator, Collection<Biome> spawnBiomes, boolean trueSpawn) {
		if(source.getVersion().isOlderThan(MCVersion.v1_13)) {
			return getSpawnPoint12(source, generator, spawnBiomes, trueSpawn);
		}
		JRand rand = new JRand(source.getWorldSeed());
		BPos spawnPos = source.locateBiome(0, 0, 0, 256, spawnBiomes, rand);
		CPos spawnCPos = spawnPos == null ? new CPos(0, 0) : spawnPos.toChunkPos();
		// very important we set the spawnpos to something not null here
		spawnPos = spawnPos == null ? new BPos(8, 64, 8) : spawnPos.add(8, 64, 8);
		if(trueSpawn) {
			int cx = 0, cz = 0;
			int incX = 0;
			int incZ = -1;
			for(int l = 0; l < 1024; l++) {
				if(cx > -16 && cx <= 16 && cz > -16 && cz <= 16) {
					BPos newSpawnPos = getSpawnPosInChunk(generator, spawnCPos.add(cx, cz));
					if(newSpawnPos != null) {
						spawnPos = newSpawnPos;
						break;
					}
				}

				if(cx == cz || cx < 0 && cx == -cz || cx > 0 && cx == 1 - cz) {
					int swap = incX;
					incX = -incZ;
					incZ = swap;
				}

				cx += incX;
				cz += incZ;
			}
		}
		return spawnPos;
	}

	private static BPos getSpawnPosInChunk(OverworldTerrainGenerator terrainGenerator, CPos cPos) {
		BPos chunkStartPos = cPos.toBlockPos();
		for(int x = chunkStartPos.getX(); x <= chunkStartPos.getX() + 15; ++x) {
			for(int z = chunkStartPos.getZ(); z <= chunkStartPos.getZ() + 15; ++z) {
				BPos pos = getOverworldRespawnPos(terrainGenerator, x, z);
				if(pos != null) {
					return pos;
				}
			}
		}
		return null;
	}

	private static BPos getOverworldRespawnPos(OverworldTerrainGenerator terrainGenerator, int x, int z) {
		Biome biome = terrainGenerator.getBiomeSource().getBiome(x, 0, z);
		if(!SPAWN_BLOCKS.contains(biome.getSurfaceConfig().getTopBlock())) {
			return null;
		} else {
			// we ignore the || !fluidstate.isEmpty because we are doing pregen stuff, for
			// respawn its important but that part is truly random so who cares.
			int y = terrainGenerator.getFirstHeightInColumn(x, z, TerrainGenerator.OCEAN_FLOOR_WG) - 1;
			if(y < 0) {
				return null;
			} else {
				Block[] column = terrainGenerator.getColumnAt(x, z);
				// the weird check for y and terrainY is ignored because we are pregen
				for(int posY = y + 1; posY >= 0; --posY) {
					if(column[posY] == Blocks.STONE) {
						return new BPos(x, posY + 1, z);
					}
				}
				return null;
			}
		}
	}


	private static boolean isValidPos(OverworldBiomeSource source, OverworldTerrainGenerator generator, int x, int z, boolean trueSpawn) {
		if(!trueSpawn) {
			return getGrassStats(source.getBiome(x, 0, z)) >= 0.5;
		} else {
			return getBlockAboveSeaLevel(generator, x, z) == Blocks.GRASS_BLOCK;
		}
	}

	public static Block getBlockAboveSeaLevel(OverworldTerrainGenerator generator, int x, int z) {
		Block[] column = generator.getColumnAt(x, z);
		// could replace column length with generator.getMaxWorldHeight()
		int y;
		for(y = generator.getSeaLevel()+1; y < column.length; y++) {
			if(column[y] == Blocks.AIR) break;
		}
		return column[y - 1];
	}

	private static long getWorldSeed(OverworldBiomeSource source) {
		return source.getWorldSeed();
	}

	private static MCVersion getVersion(OverworldBiomeSource source) {
		return source.getVersion();
	}

	private static BPos getSpawnPoint12(OverworldBiomeSource source, OverworldTerrainGenerator generator, Collection<Biome> spawnBiomes, boolean trueSpawn) {
		JRand rand = new JRand(getWorldSeed(source));
		BPos spawnPos = source.locateBiome12(0, 0, 256, spawnBiomes, rand);
		int x = 8;
		int z = 8;
		if(spawnPos != null) {
			x = spawnPos.getX();
			z = spawnPos.getZ();
		}
		int counter = 0;
		// wiggle
		while(!isValidPos(source, generator, x, z, trueSpawn)) {
			x += rand.nextInt(64) - rand.nextInt(64);
			z += rand.nextInt(64) - rand.nextInt(64);
			++counter;
			if(counter == 1000) {
				break;
			}
		}
		return new BPos(x, 64, z);
	}


	private static double getGrassStats(Biome biome) {
		if(Biomes.PLAINS.equals(biome)) {
			return 1.0;
		} else if(Biomes.MOUNTAINS.equals(biome)) {
			return 0.8; // height dependent
		} else if(Biomes.FOREST.equals(biome)) {
			return 1.0;
		} else if(Biomes.TAIGA.equals(biome)) {
			return 1.0;
		} else if(Biomes.SWAMP.equals(biome)) {
			return 0.6; // height dependent
		} else if(Biomes.RIVER.equals(biome)) {
			return 0.2;
		} else if(Biomes.BEACH.equals(biome)) {
			return 0.1;
		} else if(Biomes.WOODED_HILLS.equals(biome)) {
			return 1.0;
		} else if(Biomes.TAIGA_HILLS.equals(biome)) {
			return 1.0;
		} else if(Biomes.MOUNTAIN_EDGE.equals(biome)) {
			return 1.0; // height dependent
		} else if(Biomes.JUNGLE.equals(biome)) {
			return 1.0;
		} else if(Biomes.JUNGLE_HILLS.equals(biome)) {
			return 1.0;
		} else if(Biomes.JUNGLE_EDGE.equals(biome)) {
			return 1.0;
		} else if(Biomes.BIRCH_FOREST.equals(biome)) {
			return 1.0;
		} else if(Biomes.BIRCH_FOREST_HILLS.equals(biome)) {
			return 1.0;
		} else if(Biomes.DARK_FOREST.equals(biome)) {
			return 0.9;
		} else if(Biomes.SNOWY_TAIGA.equals(biome)) {
			return 0.1; // below trees
		} else if(Biomes.SNOWY_TAIGA_HILLS.equals(biome)) {
			return 0.1; // below trees
		} else if(Biomes.GIANT_TREE_TAIGA.equals(biome)) {
			return 0.6;
		} else if(Biomes.GIANT_TREE_TAIGA_HILLS.equals(biome)) {
			return 0.6;
		} else if(Biomes.MODIFIED_GRAVELLY_MOUNTAINS.equals(biome)) {
			return 0.2; // height dependent
		} else if(Biomes.SAVANNA.equals(biome)) {
			return 1.0;
		} else if(Biomes.SAVANNA_PLATEAU.equals(biome)) {
			return 1.0;
		} else if(Biomes.BADLANDS.equals(biome)) {
			return 0.1; // height dependent
		} else if(Biomes.BADLANDS_PLATEAU.equals(biome)) {
			return 0.1; // height dependent
			// NOTE: in rare circumstances you can get also get grass islands that are
			// completely ocean variants...
		}
		return 0;
	}

	@Override
	public String getName() {
		return name();
	}

	@Override
	public boolean canStart(SpawnPoint.Data data, long structureSeed, ChunkRand rand) {
		throw new UnsupportedOperationException("Spawn depends on biomes!");
	}

	@Override
	public boolean canSpawn(SpawnPoint.Data data, BiomeSource source) {
		if(source instanceof OverworldBiomeSource) {
			Context context = this.getContext(source.getWorldSeed());
			if(context.getGenerator() != null) {
				BPos spawn = getSpawnPoint((OverworldTerrainGenerator)context.getGenerator());
				return data.blockX == spawn.getX() && data.blockZ == spawn.getZ();
			}
		}

		return false;
	}

	@Override
	public boolean canGenerate(Data data, TerrainGenerator generator) {
		return true;
	}

	@Override
	public Dimension getValidDimension() {
		return Dimension.OVERWORLD;
	}

	public static class Data extends Feature.Data<SpawnPoint> {
		public final int blockX;
		public final int blockZ;

		public Data(SpawnPoint feature, int blockX, int blockZ) {
			super(feature, blockX >> 4, blockZ >> 4);
			this.blockX = blockX;
			this.blockZ = blockZ;
		}
	}

}
