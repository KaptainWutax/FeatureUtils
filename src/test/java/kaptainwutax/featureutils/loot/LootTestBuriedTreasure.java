package kaptainwutax.featureutils.loot;

import kaptainwutax.biomeutils.source.BiomeSource;
import kaptainwutax.featureutils.loot.item.ItemStack;
import kaptainwutax.featureutils.structure.BuriedTreasure;
import kaptainwutax.featureutils.structure.generator.Generator;
import kaptainwutax.featureutils.structure.generator.structure.BuriedTreasureGenerator;
import kaptainwutax.mcutils.rand.ChunkRand;
import kaptainwutax.mcutils.state.Dimension;
import kaptainwutax.mcutils.util.data.Pair;
import kaptainwutax.mcutils.util.pos.BPos;
import kaptainwutax.mcutils.util.pos.CPos;
import kaptainwutax.mcutils.version.MCVersion;
import kaptainwutax.terrainutils.TerrainGenerator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LootTestBuriedTreasure {
	private List<Pair<Generator.ILootType, BPos>> loots;
	private Generator structureGenerator;
	private BiomeSource biomeSource;
	private TerrainGenerator generator;

	public void setup(long worldseed, CPos cPos, MCVersion version) {
		biomeSource = BiomeSource.of(Dimension.OVERWORLD, version, worldseed);
		generator = TerrainGenerator.of(Dimension.OVERWORLD, biomeSource);
		structureGenerator = new BuriedTreasureGenerator(version);
		ChunkRand rand = new ChunkRand().asChunkRandDebugger();
		structureGenerator.generate(generator, cPos, rand);
		loots = structureGenerator.getChestsPos();
	}

	@Test
	public void testCorrectChest1() {
		setup(123L, new BPos(905, 0, -1671).toChunkPos(), MCVersion.v1_16_5);
		List<Pair<BuriedTreasureGenerator.LootType, BPos>> checks = new ArrayList<Pair<BuriedTreasureGenerator.LootType, BPos>>() {{
			add(new Pair<>(BuriedTreasureGenerator.LootType.BURIED_CHEST, new BPos(905, 90, -1671)));
		}};
		for(Pair<BuriedTreasureGenerator.LootType, BPos> check : checks) {
			assertTrue(loots.contains(check), String.format("Missing loot %s at pos %s", check.getFirst(), check.getSecond()));
		}
	}


	@Test
	public void testChestLoot() {
		setup(123L, new BPos(905, 0, -1671).toChunkPos(), MCVersion.v1_16_5);
		BuriedTreasure buriedTreasure = new BuriedTreasure(MCVersion.v1_16_5);
		HashMap<Generator.ILootType, List<List<ItemStack>>> lootTypes = buriedTreasure.getLootEx(123L, structureGenerator, new ChunkRand(), false);
		long hash = 0;
		System.out.println(lootTypes);
		for(Map.Entry<Generator.ILootType, List<List<ItemStack>>> loots : lootTypes.entrySet()) {
			for(List<ItemStack> loot : loots.getValue()) {
				System.out.println(loots.getKey() + " " + loot);
				for(ItemStack stack : loot) hash += stack.hashCode();
			}
		}
		assertEquals(-1551810289L, hash, "Items changed maybe?");
	}

	@Test
	public void testChestLoot2() {
		setup(2000007L, new BPos(-279, 0, 121).toChunkPos(), MCVersion.v1_13_2);
		BuriedTreasure buriedTreasure = new BuriedTreasure(MCVersion.v1_13_2);
		HashMap<Generator.ILootType, List<List<ItemStack>>> lootTypes = buriedTreasure.getLootEx(2000007L, structureGenerator, new ChunkRand(), false);
		long hash = 0;
		System.out.println(lootTypes);
		for(Map.Entry<Generator.ILootType, List<List<ItemStack>>> loots : lootTypes.entrySet()) {
			for(List<ItemStack> loot : loots.getValue()) {
				System.out.println(loots.getKey() + " " + loot);
				for(ItemStack stack : loot) hash += stack.hashCode();
			}
		}
		assertEquals(2214247448L, hash, "Items changed maybe?");
	}
}
