package kaptainwutax.featureutils.examples.loot.simple;

import kaptainwutax.biomeutils.source.BiomeSource;
import kaptainwutax.featureutils.loot.item.ItemStack;
import kaptainwutax.featureutils.structure.BuriedTreasure;
import kaptainwutax.featureutils.structure.DesertPyramid;
import kaptainwutax.featureutils.structure.RegionStructure;
import kaptainwutax.featureutils.structure.Shipwreck;
import kaptainwutax.featureutils.structure.generator.*;
import kaptainwutax.mcutils.rand.ChunkRand;
import kaptainwutax.mcutils.state.Dimension;
import kaptainwutax.mcutils.util.pos.CPos;
import kaptainwutax.mcutils.util.pos.RPos;
import kaptainwutax.mcutils.version.MCVersion;
import kaptainwutax.terrainutils.ChunkGenerator;

import java.util.HashMap;
import java.util.List;

public class GetLoot {
	// this is to remove dynamic dispatch
	private static void assertTrue(boolean condition) {
		assert condition;
	}

	public static void main(String[] args) {
		// For now stick to 1.14+ and especially 1.16.5
		MCVersion version = MCVersion.v1_16;
		long worldSeed = 1L;
		desertPyramid(version, worldSeed);
		buriedTreasure(version, worldSeed);
		shipwreck(version, worldSeed);
	}


	public static void desertPyramid(MCVersion version, long worldSeed) {
		// For optimization we ask you to create the chunkRand, this should be done per thread
		ChunkRand rand = new ChunkRand();
		// Create my structure
		DesertPyramid desertPyramid = new DesertPyramid(version);
		// Create my factory
		Generator.GeneratorFactory<?> generatorFactory = Generators.get(desertPyramid.getClass());
		assert generatorFactory != null;
		// Create my generator
		Generator structureGenerator = generatorFactory.create(version);
		assert structureGenerator instanceof DesertPyramidGenerator;
		// Generate my biome source
		BiomeSource source = BiomeSource.of(Dimension.OVERWORLD, version, worldSeed);
		// Generate my ChunkGenerator
		ChunkGenerator chunkGenerator = ChunkGenerator.of(Dimension.OVERWORLD, source);

		// Choose a valid chunk position for my structure
		// here we chose 24 and 49 as the region coordinates, remember region are structure dependant
		// so use structure#getSpacing() to change base
		// getInRegion guarantee that in that region that structure canStart
		CPos pos = desertPyramid.getInRegion(worldSeed, 24, 49, rand);
		assertTrue(pos.toRegionPos(desertPyramid.getSpacing()).equals(new RPos(24, 49, desertPyramid.getSpacing())));
		// Alternatively you can get the data at a specific chunk position, however you will need to check canStart then
		// We don't recommend this as you usually never know the chunk position beforehand...
		@SuppressWarnings("unchecked")
		RegionStructure.Data<DesertPyramid> data = (RegionStructure.Data<DesertPyramid>) desertPyramid.at(782, 1584);
		assertTrue(desertPyramid.canStart(data, worldSeed, rand));

		// Verify that this chunk position is a valid spot to spawn
		assertTrue(desertPyramid.canSpawn(pos, source));
		// Alternatively if you had a data
		assertTrue(desertPyramid.canSpawn(data, source));

		// Verify that this chunk position is a valid spot to generate terrain wise
		// (not all structure have this check, desert pyramid doesn't need it for instance)
		assertTrue(desertPyramid.canGenerate(pos, chunkGenerator));
		// Alternatively if you had a data
		assertTrue(desertPyramid.canGenerate(data, chunkGenerator));

		// Generate the chest position for that structure at that valid chunk position (rand is optional but encouraged)
		assertTrue(structureGenerator.generate(chunkGenerator, pos, rand));
		// Alternatively if you had a data
		assertTrue(structureGenerator.generate(chunkGenerator, data.chunkX, data.chunkZ, rand));

		// You can chest the chest positions here
		System.out.println(structureGenerator.getChestsPos());
		assertTrue(structureGenerator.getChestsPos().size() == 4);

		// Get the loot that generated in those chest positions (indexed allow to create a chest as in Minecraft with randomized slots and EMPTY_ITEM)
		HashMap<Generator.ILootType, List<List<ItemStack>>> lootTypes = desertPyramid.getLoot(worldSeed, structureGenerator, rand, false);
		// the result is a hashmap with each possible type of chest (there could be multiple instance of that type of chest)
		// with a list of chest content attached to each.

		// Here desert pyramid have 4 chest with a single chest per list
		List<List<ItemStack>> chest1 = lootTypes.get(DesertPyramidGenerator.LootType.CHEST_1);
		assertTrue(chest1.size() == 1);
		System.out.println(chest1);
		List<List<ItemStack>> chest2 = lootTypes.get(DesertPyramidGenerator.LootType.CHEST_2);
		assertTrue(chest2.size() == 1);
		System.out.println(chest2);
		List<List<ItemStack>> chest3 = lootTypes.get(DesertPyramidGenerator.LootType.CHEST_3);
		assertTrue(chest3.size() == 1);
		System.out.println(chest3);
		List<List<ItemStack>> chest4 = lootTypes.get(DesertPyramidGenerator.LootType.CHEST_4);
		assertTrue(chest4.size() == 1);
		System.out.println(chest4);

	}

	public static void buriedTreasure(MCVersion version, long worldSeed) {
		// For optimization we ask you to create the chunkRand, this should be done per thread
		ChunkRand rand = new ChunkRand();
		// Create my structure
		BuriedTreasure buriedTreasure = new BuriedTreasure(version);
		// Create my factory
		Generator.GeneratorFactory<?> generatorFactory = Generators.get(buriedTreasure.getClass());
		assert generatorFactory != null;
		// Create my generator
		Generator structureGenerator = generatorFactory.create(version);
		assert structureGenerator instanceof BuriedTreasureGenerator;
		// Generate my biome source
		BiomeSource source = BiomeSource.of(Dimension.OVERWORLD, version, worldSeed);
		// Generate my ChunkGenerator
		ChunkGenerator chunkGenerator = ChunkGenerator.of(Dimension.OVERWORLD, source);

		// Choose a valid chunk position for my structure
		// here we chose -25, -20 as the region coordinates, remember region are structure dependant
		// so use structure#getSpacing() to change base
		// getInRegion guarantee that in that region that structure canStart
		CPos pos = buriedTreasure.getInRegion(worldSeed, -25, -20, rand);
		assertTrue(pos.toRegionPos(buriedTreasure.getSpacing()).equals(new RPos(-25, -20, buriedTreasure.getSpacing())));
		// Alternatively you can get the data at a specific chunk position, however you will need to check canStart then
		// We don't recommend this as you usually never know the chunk position beforehand...
		RegionStructure.Data<BuriedTreasure> data = buriedTreasure.at(-25, -20);
		assertTrue(buriedTreasure.canStart(data, worldSeed, rand));

		// Verify that this chunk position is a valid spot to spawn
		assertTrue(buriedTreasure.canSpawn(pos, source));
		// Alternatively if you had a data
		assertTrue(buriedTreasure.canSpawn(data, source));

		// Verify that this chunk position is a valid spot to generate terrain wise
		// (not all structure have this check, buried treasure doesn't need it for instance)
		assertTrue(buriedTreasure.canGenerate(pos, chunkGenerator));
		// Alternatively if you had a data
		assertTrue(buriedTreasure.canGenerate(data, chunkGenerator));

		// Generate the chest position for that structure at that valid chunk position (rand is optional but encouraged)
		assertTrue(structureGenerator.generate(chunkGenerator, pos, rand));
		// Alternatively if you had a data
		assertTrue(structureGenerator.generate(chunkGenerator, data.chunkX, data.chunkZ, rand));

		// You can chest the chest positions here
		System.out.println(structureGenerator.getChestsPos());
		assertTrue(structureGenerator.getChestsPos().size() == 1);

		// Get the loot that generated in those chest positions (indexed allow to create a chest as in Minecraft with randomized slots and EMPTY_ITEM)
		HashMap<Generator.ILootType, List<List<ItemStack>>> lootTypes = buriedTreasure.getLoot(worldSeed, structureGenerator, rand, false);
		// the result is a hashmap with each possible type of chest (there could be multiple instance of that type of chest)
		// with a list of chest content attached to each.

		// Here desert pyramid have 4 chest with a single chest per list
		List<List<ItemStack>> chest1 = lootTypes.get(BuriedTreasureGenerator.LootType.BURIED_CHEST);
		assertTrue(chest1.size() == 1);
		System.out.println(chest1);
	}


	public static void shipwreck(MCVersion version, long worldSeed) {
		// For optimization we ask you to create the chunkRand, this should be done per thread
		ChunkRand rand = new ChunkRand();
		// Create my structure
		Shipwreck shipwreck = new Shipwreck(version);
		// Create my factory
		Generator.GeneratorFactory<?> generatorFactory = Generators.get(shipwreck.getClass());
		assert generatorFactory != null;
		// Create my generator
		Generator structureGenerator = generatorFactory.create(version);
		assert structureGenerator instanceof ShipwreckGenerator;
		// Generate my biome source
		BiomeSource source = BiomeSource.of(Dimension.OVERWORLD, version, worldSeed);
		// Generate my ChunkGenerator
		ChunkGenerator chunkGenerator = ChunkGenerator.of(Dimension.OVERWORLD, source);

		// Choose a valid chunk position for my structure
		// here we chose the blockpos -615,9 that we convert to region
		// as the region coordinates, remember region are structure dependant
		// so use structure#getSpacing() to change base
		// getInRegion guarantee that in that region that structure canStart
		CPos pos = shipwreck.getInRegion(worldSeed, -615/16/shipwreck.getSpacing(), 9/16/shipwreck.getSpacing(), rand);
		assertTrue(pos.toRegionPos(shipwreck.getSpacing()).equals(new RPos(-1, 0, shipwreck.getSpacing())));
		// Alternatively you can get the data at a specific chunk position, however you will need to check canStart then
		// We don't recommend this as you usually never know the chunk position beforehand...
		@SuppressWarnings("unchecked")
		RegionStructure.Data<Shipwreck> data = (RegionStructure.Data<Shipwreck>) shipwreck.at(-39, 0);
		assertTrue(shipwreck.canStart(data, worldSeed, rand));

		// Verify that this chunk position is a valid spot to spawn
		assertTrue(shipwreck.canSpawn(pos, source));
		// Alternatively if you had a data
		assertTrue(shipwreck.canSpawn(data, source));

		// Verify that this chunk position is a valid spot to generate terrain wise
		// (not all structure have this check, shipwreck doesn't need it for instance)
		assertTrue(shipwreck.canGenerate(pos, chunkGenerator));
		// Alternatively if you had a data
		assertTrue(shipwreck.canGenerate(data, chunkGenerator));

		// Generate the chest position for that structure at that valid chunk position (rand is optional but encouraged)
		assertTrue(structureGenerator.generate(chunkGenerator, pos, rand));
		// Alternatively if you had a data
		assertTrue(structureGenerator.generate(chunkGenerator, data.chunkX, data.chunkZ, rand));

		// You can chest the chest positions here
		System.out.println(structureGenerator.getChestsPos());
		assertTrue(structureGenerator.getChestsPos().size() == 1);

		// Get the loot that generated in those chest positions (indexed allow to create a chest as in Minecraft with randomized slots and EMPTY_ITEM)
		HashMap<Generator.ILootType, List<List<ItemStack>>> lootTypes = shipwreck.getLoot(worldSeed, structureGenerator, rand, false);
		// the result is a hashmap with each possible type of chest (there could be multiple instance of that type of chest)
		// with a list of chest content attached to each.

		// Here shipwreck have 3 chest with a single chest per list
		List<List<ItemStack>> treasureChest = lootTypes.get(ShipwreckGenerator.LootType.TREASURE_CHEST);
		assertTrue(treasureChest.size() == 1);
		System.out.println(treasureChest);
		List<List<ItemStack>> mapChest = lootTypes.get(ShipwreckGenerator.LootType.MAP_CHEST);
		assertTrue(mapChest.size() == 1);
		System.out.println(mapChest);
		List<List<ItemStack>> supplyChest = lootTypes.get(ShipwreckGenerator.LootType.SUPPLY_CHEST);
		assertTrue(supplyChest.size() == 1);
		System.out.println(supplyChest);
	}

	// etc for End cities, ruined portal...

}
