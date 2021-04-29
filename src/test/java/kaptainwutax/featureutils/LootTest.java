package kaptainwutax.featureutils;

import kaptainwutax.biomeutils.source.OverworldBiomeSource;
import kaptainwutax.featureutils.loot.LootContext;
import kaptainwutax.featureutils.loot.MCLootTables;
import kaptainwutax.featureutils.loot.item.ItemStack;
import kaptainwutax.featureutils.structure.BuriedTreasure;
import kaptainwutax.featureutils.structure.RegionStructure;
import kaptainwutax.mathutils.util.Mth;
import kaptainwutax.mcutils.rand.ChunkRand;
import kaptainwutax.mcutils.rand.seed.WorldSeed;
import kaptainwutax.mcutils.version.MCVersion;
import kaptainwutax.seedutils.lcg.LCG;
import kaptainwutax.terrainutils.utils.MathHelper;
import mjtb49.hashreversals.ChunkRandomReverser;

import java.util.Arrays;
import java.util.List;

public class LootTest {
	private static final MCVersion version = MCVersion.v1_13_2;
	public static final BuriedTreasure BURIED_TREASURE = new BuriedTreasure(version);
    public static void main(String[] args) {
		ChunkRand rand = new ChunkRand().asChunkRandDebugger();
		int chunkX=-1;
		int chunkZ=6;
		long worldSeed=1;
		OverworldBiomeSource source = new OverworldBiomeSource(version, worldSeed);
		RegionStructure.Data<BuriedTreasure> treasure = BURIED_TREASURE.at(chunkX, chunkZ);
		// check that the structure can generate in that chunk (it's luck based with a nextfloat)
		if (!treasure.testStart(WorldSeed.toStructureSeed(worldSeed), rand)) System.err.println("Incorrect RNG");
		// test if the biomes are correct at that place
		if (!treasure.testBiome(source)) System.err.println("Incorrect biome");
		// we get the decoration Seed (used to place all the decorators)
		long decoratorSeed = rand.setPopulationSeed(worldSeed, chunkX * 16, chunkZ * 16, version);
		System.out.println(decoratorSeed);
////                CPos cPos=new CPos(chunkX,chunkZ);
////                BPos bPos=cPos.toBlockPos().add(9,0,9);
////                Biome biome=source.getBiome(bPos.getX(),bPos.getY(),bPos.getZ()); // useful only to get the index and step
//		// set the feature seed based on the ordinals
		// this is 1.13 !!! offset
		rand.setDecoratorSeed(decoratorSeed, 2, 2, version); //specific ordinals and index for burried treasures

		// we get the loot table seed
		System.out.println(rand.getSeed()& Mth.MASK_48);
		long lootTableSeed = rand.nextLong();
		System.out.println(lootTableSeed);
		LootContext context = new LootContext(lootTableSeed,MCVersion.v1_13_2);
		System.out.println(context.getSeed()& Mth.MASK_48);
		List<ItemStack> loot = MCLootTables.BURIED_TREASURE_CHEST.generate(context);
		System.out.println(Arrays.toString(loot.toArray()));
		context = new LootContext(lootTableSeed,MCVersion.v1_16);
		System.out.println(context.getSeed()& Mth.MASK_48);
		loot = MCLootTables.BURIED_TREASURE_CHEST.generate(context);
		System.out.println(Arrays.toString(loot.toArray()));

    }

}
