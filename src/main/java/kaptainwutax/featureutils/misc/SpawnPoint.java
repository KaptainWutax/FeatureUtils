package kaptainwutax.featureutils.misc;

import kaptainwutax.biomeutils.biome.Biome;
import kaptainwutax.biomeutils.biome.Biomes;
import kaptainwutax.biomeutils.source.OverworldBiomeSource;
import kaptainwutax.mcutils.block.Block;
import kaptainwutax.mcutils.block.Blocks;
import kaptainwutax.mcutils.util.pos.BPos;
import kaptainwutax.mcutils.version.MCVersion;
import kaptainwutax.mcutils.version.UnsupportedVersion;
import kaptainwutax.seedutils.rand.JRand;
import kaptainwutax.terrainutils.terrain.OverworldTerrainGenerator;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class SpawnPoint {
	public static final List<Biome> SPAWN_BIOMES = Arrays.asList( Biomes.PLAINS,Biomes.SUNFLOWER_PLAINS, Biomes.TAIGA, Biomes.TAIGA_HILLS,Biomes.FOREST, Biomes.WOODED_HILLS, Biomes.JUNGLE, Biomes.JUNGLE_HILLS);
	public static final List<Block> SPAWN_BLOCKS = Arrays.asList(Blocks.GRASS_BLOCK,Blocks.PODZOL); // PODZOL doesn't matter cause pregen but whatever
	private final OverworldTerrainGenerator generator;
// TODO surface noise in NoiseChunkGenerator for GIANT TAIGA (boring)
	public SpawnPoint(OverworldTerrainGenerator generator){
		this.generator =generator;
	}

	public static BPos getSpawnPoint(OverworldTerrainGenerator generator){
		return new SpawnPoint(generator).getSpawnPoint();
	}

	public BPos getSpawnPoint() {
		return this.getSpawnPoint(SPAWN_BIOMES,true);
	}

	public BPos getApproximateSpawnPoint() {
		return this.getSpawnPoint(SPAWN_BIOMES,false);
	}

	public BPos getSpawnPoint(Collection<Biome> spawnBiomes,boolean trueSpawn) {
		if(this.getVersion().isOlderThan(MCVersion.v1_13)) {
			return getSpawnPoint12(spawnBiomes, false);
		}
		JRand rand = new JRand(this.getWorldSeed());
		BPos spawnPos = this.getSource().locateBiome(0, 0, 0, 256, spawnBiomes, rand);
		return spawnPos == null ? new BPos(8, 0, 8) : spawnPos.add(8, 0, 8);
	}

	public OverworldBiomeSource getSource(){
		return (OverworldBiomeSource) generator.getBiomeSource();
	}

	public double getGrassStats(Biome biome) {
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

	public boolean isValidPos(int x, int z, boolean trueSpawn) {
		// TODO tricky part, check biomes valid + gen terain == GRASS

		// void check not usable
		// for now lets just do the proba tables then we can move to full terrain for true spawn (see terrainUtils)
		if(!trueSpawn) {
			return getGrassStats(this.getSource().getBiome(x, 0, z)) >= 0.5;
		} else {
			return false;
			//return generator.getFirstHeightInColumn()
		}
	}

	public long getWorldSeed(){
		return this.getSource().getWorldSeed();
	}

	public MCVersion getVersion(){
		return this.getSource().getVersion();
	}

	public BPos getSpawnPoint12(Collection<Biome> spawnBiomes, boolean trueSpawn) {
		JRand rand = new JRand(this.getWorldSeed());
		BPos spawnPos = this.getSource().locateBiome12(0, 0, 256, spawnBiomes, rand);
		int x = 8;
		int z = 8;
		if(spawnPos != null) {
			x = spawnPos.getX();
			z = spawnPos.getZ();
		}
		int counter = 0;
		// wiggle
		while(!isValidPos(x, z, trueSpawn)) {
			x += rand.nextInt(64) - rand.nextInt(64);
			z += rand.nextInt(64) - rand.nextInt(64);
			++counter;
			if(counter == 1000) {
				break;
			}
		}
		return new BPos(x, 64, z);
	}
}
