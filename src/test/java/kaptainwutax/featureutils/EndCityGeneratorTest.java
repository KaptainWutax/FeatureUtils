package kaptainwutax.featureutils;

import kaptainwutax.biomeutils.source.BiomeSource;
import kaptainwutax.featureutils.loot.item.ItemStack;
import kaptainwutax.featureutils.structure.EndCity;
import kaptainwutax.featureutils.structure.RegionStructure;
import kaptainwutax.featureutils.structure.generator.EndCityGenerator;
import kaptainwutax.mcutils.rand.ChunkRand;
import kaptainwutax.mcutils.state.Dimension;
import kaptainwutax.mcutils.util.data.Pair;
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
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EndCityGeneratorTest {
	private List<Pair<EndCityGenerator.LootType, BPos>> loots;
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
		System.out.println(loots);
		for (Pair<EndCityGenerator.LootType, BPos> check : checks) {
			assertTrue(loots.contains(check), String.format("Missing loot %s at pos %s", check.getFirst(), check.getSecond()));
		}
	}

	@Test
	public void testChestLoot() {
		setup(1L, new BPos(-127280, 0, -30944).toChunkPos(), MCVersion.v1_16_5);
		EndCity endCity = new EndCity(MCVersion.v1_16_5);
		HashMap<EndCityGenerator.LootType, List<List<ItemStack>>> lootTypes = endCity.getLoot(1L, endCityGenerator, new ChunkRand(), false);
		for (Map.Entry<EndCityGenerator.LootType, List<List<ItemStack>>> loots : lootTypes.entrySet()) {
			for (List<ItemStack> loot : loots.getValue()) {
				System.out.println(loot);
			}
		}
	}

	@Test
	public void testWrong() {
		// /tp @p 1952 150 -1840
		setup(12132233L, new BPos(1952, 0, -1840).toChunkPos() , MCVersion.v1_16_5);
		for (Pair<EndCityGenerator.LootType, BPos> check : endCityGenerator.getChestsPos()) {
			if (check.getFirst().lootTable != null) System.out.println(check);
		}
		EndCity endCity = new EndCity(MCVersion.v1_16_5);
		HashMap<EndCityGenerator.LootType, List<List<ItemStack>>> lootTypes = endCity.getLoot(12132233L, endCityGenerator, new ChunkRand(), false);
	}

	public static void main(String[] args) {
		MCVersion version = MCVersion.v1_16_5;
		EndCity endCity = new EndCity(version);
		ChunkRand rand = new ChunkRand();

		EndCityGenerator endCityGenerator = new EndCityGenerator(version);

		for (int i = 0; i < 1000000000; i++) {
			long worldseed = rand.nextLong();
			BiomeSource biomeSource = BiomeSource.of(Dimension.END, version, worldseed);
			ChunkGenerator generator = ChunkGenerator.of(Dimension.END, biomeSource);
			for (int x = -3000 / 16 / endCity.getSpacing(); x < 3000 / 16 / endCity.getSpacing(); x++) {
				for (int z = -3000 / 16 / endCity.getSpacing(); z < 3000 / 16 / endCity.getSpacing(); z++) {
					Feature.Data<?> pos = endCity.at(x * endCity.getSpacing(), z * endCity.getSpacing());
					if (endCity.canStart((RegionStructure.Data<EndCity>) pos, worldseed, rand)) {
						if (endCity.canSpawn((RegionStructure.Data<EndCity>) pos, biomeSource)) {
							if (endCity.canGenerate((RegionStructure.Data<EndCity>) pos, generator)) {
								endCityGenerator.generate(generator, pos.chunkX, pos.chunkZ, rand);
								HashMap<EndCityGenerator.LootType, List<List<ItemStack>>> loots = endCity.getLoot(worldseed, endCityGenerator, rand, false);
								int diamond = 0;
								for (Map.Entry<EndCityGenerator.LootType, List<List<ItemStack>>> loot : loots.entrySet()) {
									for (List<ItemStack> l : loot.getValue()) {
										diamond += l.stream().filter(e -> e.getItem().getName().contains("diamond")).mapToInt(ItemStack::getCount).sum();
									}
								}
								if (diamond > 50) {
									System.out.printf("Diamond: %d, seed: %d, tp: /tp @p %d ~ %d%n", diamond, worldseed, pos.chunkX * 16, pos.chunkZ * 16);
								}
								endCityGenerator.reset();
							}
						}
					}
				}
			}
		}

	}

}
