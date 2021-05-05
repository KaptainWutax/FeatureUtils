package kaptainwutax.featureutils;

import kaptainwutax.biomeutils.source.BiomeSource;
import kaptainwutax.featureutils.loot.item.ItemStack;
import kaptainwutax.featureutils.structure.EndCity;
import kaptainwutax.featureutils.structure.generator.EndCityGenerator;
import kaptainwutax.featureutils.structure.generator.Generator;
import kaptainwutax.mcutils.rand.ChunkRand;
import kaptainwutax.mcutils.state.Dimension;
import kaptainwutax.mcutils.util.data.Pair;
import kaptainwutax.mcutils.util.data.ThreadPool;
import kaptainwutax.mcutils.util.pos.BPos;
import kaptainwutax.mcutils.util.pos.CPos;
import kaptainwutax.mcutils.version.MCVersion;
import kaptainwutax.terrainutils.ChunkGenerator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static kaptainwutax.featureutils.structure.generator.EndCityGenerator.LootType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EndCityGeneratorTest {
	private List<Pair<Generator.ILootType, BPos>> loots;
	private EndCityGenerator endCityGenerator;

	public void setup(long worldseed, CPos cPos, MCVersion version) {
		BiomeSource biomeSource = BiomeSource.of(Dimension.END, version, worldseed);
		ChunkGenerator generator = ChunkGenerator.of(Dimension.END, biomeSource);
		endCityGenerator = new EndCityGenerator(version);
		ChunkRand rand = new ChunkRand().asChunkRandDebugger();
		endCityGenerator.generate(generator, cPos, rand);
		loots = endCityGenerator.getChestsPos();
	}

	@Test
	public void testCorrectChest1() {
		setup(1L, new BPos(-1232, 0, -25280).toChunkPos(), MCVersion.v1_16_5);
		assertTrue(endCityGenerator.hasShip());
		List<Pair<EndCityGenerator.LootType, BPos>> checks = new ArrayList<Pair<EndCityGenerator.LootType, BPos>>() {{
			add(new Pair<>(SHIP_CHEST_1, new BPos(-1222, 100, -25202)));
			add(new Pair<>(SHIP_CHEST_2, new BPos(-1224, 100, -25202)));
			add(new Pair<>(SHIP_ELYTRA, new BPos(-1223, 100, -25202)));
		}};
		for (Pair<EndCityGenerator.LootType, BPos> check : checks) {
			assertTrue(loots.contains(check), String.format("Missing loot %s at pos %s", check.getFirst(), check.getSecond()));
		}
	}

	@Test
	public void testCorrectChest2() {
		// TODO see why this one generated 5 too high
		setup(1L, new BPos(-12752, 0, -30976).toChunkPos(), MCVersion.v1_16_5);
		List<Pair<EndCityGenerator.LootType, BPos>> checks = new ArrayList<Pair<EndCityGenerator.LootType, BPos>>() {{
			add(new Pair<>(THIRD_FLOOR_CHEST, new BPos(-12711, 103, -30973)));
		}};
		for (Pair<EndCityGenerator.LootType, BPos> check : checks) {
			assertTrue(loots.contains(check), String.format("Missing loot %s at pos %s", check.getFirst(), check.getSecond()));
		}
	}

	@Test
	public void testCorrectChest3() {
		setup(1L, new BPos(-127280, 0, -30944).toChunkPos(), MCVersion.v1_16_5);
		assertTrue(endCityGenerator.hasShip());
		List<Pair<EndCityGenerator.LootType, BPos>> checks = new ArrayList<Pair<EndCityGenerator.LootType, BPos>>() {{
			add(new Pair<>(FAT_TOWER_TOP_CHEST_1, new BPos(-127270, 123, -30943)));
			add(new Pair<>(FAT_TOWER_TOP_CHEST_2, new BPos(-127272, 123, -30945)));
			// those are also 5 too high
			add(new Pair<>(SHIP_CHEST_1, new BPos(-127272, 145, -30907)));
			add(new Pair<>(SHIP_ELYTRA, new BPos(-127273, 145, -30907)));
			add(new Pair<>(SHIP_CHEST_2, new BPos(-127274, 145, -30907)));
			add(new Pair<>(THIRD_FLOOR_CHEST, new BPos(-127276, 127, -30989)));
		}};
		for (Pair<EndCityGenerator.LootType, BPos> check : checks) {
			assertTrue(loots.contains(check), String.format("Missing loot %s at pos %s", check.getFirst(), check.getSecond()));
		}
	}

	@Test
	public void testChestLoot() {
		setup(1L, new BPos(-127280, 0, -30944).toChunkPos(), MCVersion.v1_16_5);
		EndCity endCity = new EndCity(MCVersion.v1_16_5);
		HashMap<Generator.ILootType, List<List<ItemStack>>> lootTypes = endCity.getLoot(1L, endCityGenerator, new ChunkRand(), false);
		long hash = 0;
		for (Map.Entry<Generator.ILootType, List<List<ItemStack>>> loots : lootTypes.entrySet()) {
			for (List<ItemStack> loot : loots.getValue()) {
				for (ItemStack stack : loot) hash += stack.hashCode();
			}
		}
		assertEquals(-2289062442L, hash, "Items changed maybe?");
	}

	@Test
	public void testLargeEndcity() {
		// /tp @p 1952 150 -1840
		long worldSeed = -4425006226675986357L;
		setup(worldSeed, new BPos(-2880, 0, -320).toChunkPos(), MCVersion.v1_16_5);
		assertEquals(155, endCityGenerator.getGlobalPieces().size(), "The end city doesn't have the proper size");
		assertTrue(endCityGenerator.hasShip());
		EndCity endCity = new EndCity(MCVersion.v1_16_5);
		HashMap<Generator.ILootType, List<List<ItemStack>>> lootTypes = endCity.getLoot(worldSeed, endCityGenerator, new ChunkRand(), false);
		long diamondCount = 0;
		for (Map.Entry<Generator.ILootType, List<List<ItemStack>>> loots : lootTypes.entrySet()) {
			diamondCount += loots.getValue().stream().mapToLong(e -> e.stream().filter(f -> f.getItem().getName().contains("diamond")).mapToLong(ItemStack::getCount).sum()).sum();
		}
		assertEquals(68, diamondCount, "Diamond count doesn't match");
	}

	public static void main(String[] args) {
		new ThreadPool(Runtime.getRuntime().availableProcessors()).run(EndCityGeneratorTest::tryFindDiamond);
	}

	public static void tryFindDiamond() {
		MCVersion version = MCVersion.v1_16_5;
		EndCity endCity = new EndCity(version);
		ChunkRand rand = new ChunkRand();

		EndCityGenerator endCityGenerator = new EndCityGenerator(version);

		for (int i = 0; i < 1000000000; i++) {
			long worldseed = rand.nextLong();
			BiomeSource biomeSource = BiomeSource.of(Dimension.END, version, worldseed);
			ChunkGenerator generator = ChunkGenerator.of(Dimension.END, biomeSource);
			for (int regionX = -3000 / 16 / endCity.getSpacing(); regionX < 3000 / 16 / endCity.getSpacing(); regionX++) {
				for (int regionZ = -3000 / 16 / endCity.getSpacing(); regionZ < 3000 / 16 / endCity.getSpacing(); regionZ++) {
					CPos pos = endCity.getInRegion(worldseed, regionX, regionZ, rand);
					if (endCity.canSpawn(pos, biomeSource)) {
						if (endCity.canGenerate(pos, generator)) {
							endCityGenerator.generate(generator, pos.getX(), pos.getZ(), rand);
							HashMap<Generator.ILootType, List<List<ItemStack>>> loots = endCity.getLoot(worldseed, endCityGenerator, rand, false);
							int diamond = 0;
							for (Map.Entry<Generator.ILootType, List<List<ItemStack>>> loot : loots.entrySet()) {
								for (List<ItemStack> l : loot.getValue()) {
									diamond += l.stream().filter(e -> e.getItem().getName().contains("diamond")).mapToInt(ItemStack::getCount).sum();
								}
							}
							if (diamond > 50) {
								System.out.printf("Diamond: %d, seed: %d, tp: /tp @p %d ~ %d%n", diamond, worldseed, pos.getX() * 16, pos.getZ() * 16);
							}
							endCityGenerator.reset();
						}
					}
				}
			}
		}
	}
}


