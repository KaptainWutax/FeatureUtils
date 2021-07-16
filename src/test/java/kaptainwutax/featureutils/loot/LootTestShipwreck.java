package kaptainwutax.featureutils.loot;

import kaptainwutax.biomeutils.source.BiomeSource;
import kaptainwutax.featureutils.loot.item.ItemStack;
import kaptainwutax.featureutils.structure.Shipwreck;
import kaptainwutax.featureutils.structure.generator.Generator;
import kaptainwutax.featureutils.structure.generator.structure.ShipwreckGenerator;
import kaptainwutax.mcutils.rand.ChunkRand;
import kaptainwutax.mcutils.state.Dimension;
import kaptainwutax.mcutils.util.data.Pair;
import kaptainwutax.mcutils.util.pos.BPos;
import kaptainwutax.mcutils.util.pos.CPos;
import kaptainwutax.mcutils.util.pos.RPos;
import kaptainwutax.mcutils.version.MCVersion;
import kaptainwutax.terrainutils.TerrainGenerator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static kaptainwutax.featureutils.structure.generator.structure.ShipwreckGenerator.LootType.SUPPLY_CHEST;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LootTestShipwreck {

	private List<Pair<Generator.ILootType, BPos>> loots;
	private Generator structureGenerator;
	private BiomeSource biomeSource;
	private TerrainGenerator generator;

	public void setup(long worldseed, CPos cPos, MCVersion version) {
		biomeSource = BiomeSource.of(Dimension.OVERWORLD, version, worldseed);
		generator = TerrainGenerator.of(Dimension.OVERWORLD, biomeSource);
		structureGenerator = new ShipwreckGenerator(version);
		ChunkRand rand = new ChunkRand().asChunkRandDebugger();
		structureGenerator.generate(generator, cPos, rand);
		loots = structureGenerator.getChestsPos();
	}

	@Test
	public void testCorrectChest1() {
		setup(2276366175191987160L, new BPos(-2535, 10, -3015).toChunkPos(), MCVersion.v1_16_5);
		List<Pair<ShipwreckGenerator.LootType, BPos>> checks = new ArrayList<Pair<ShipwreckGenerator.LootType, BPos>>() {{
			add(new Pair<>(SUPPLY_CHEST, new BPos(-2533, 94, -3008)));
		}};
		for(Pair<ShipwreckGenerator.LootType, BPos> check : checks) {
			assertTrue(loots.contains(check), String.format("Missing loot %s at pos %s", check.getFirst(), check.getSecond()));
		}
	}

	@Test
	public void testCorrectChest2() {
		setup(2276366175191987160L, new BPos(-2535, 10, -3015).toChunkPos(), MCVersion.v1_16_5);
		List<Pair<ShipwreckGenerator.LootType, BPos>> checks = new ArrayList<Pair<ShipwreckGenerator.LootType, BPos>>() {{
			add(new Pair<>(SUPPLY_CHEST, new BPos(-2533, 94, -3008)));
		}};
		for(Pair<ShipwreckGenerator.LootType, BPos> check : checks) {
			assertTrue(loots.contains(check), String.format("Missing loot %s at pos %s", check.getFirst(), check.getSecond()));
		}
	}


	@Test
	public void testChestLoot() {
		setup(2276366175191987160L, new BPos(-2535, 10, -3015).toChunkPos(), MCVersion.v1_16_5);
		Shipwreck shipwreck = new Shipwreck(MCVersion.v1_16_5);
		HashMap<Generator.ILootType, List<List<ItemStack>>> lootTypes = shipwreck.getLootEx(2276366175191987160L, structureGenerator, new ChunkRand(), false);
		long hash = 0;
		System.out.println(lootTypes);
		for(Map.Entry<Generator.ILootType, List<List<ItemStack>>> loots : lootTypes.entrySet()) {
			for(List<ItemStack> loot : loots.getValue()) {
				for(ItemStack stack : loot) hash += stack.hashCode();
			}
		}
		assertEquals(716600595L, hash, "Items changed maybe?");
	}

	@Test
	public void testChestLoot2() {
		ChunkRand rand = new ChunkRand();
		long hash = 0;
		long worldSeed = 2276366175191987160L;
		MCVersion version = MCVersion.v1_16_5;
		Shipwreck shipwreck = new Shipwreck(version);
		RPos rPos = new RPos(1, 1, shipwreck.getSpacing());
		setup(worldSeed, rPos.toChunkPos(), MCVersion.v1_16_5);
		for(int i = 0; i < 100; i++) {
			RPos rPos1 = rPos.add(1, 1);
			CPos start = shipwreck.getInRegion(worldSeed, rPos1.getX(), rPos1.getZ(), rand);
			if(!shipwreck.canSpawn(start.getX(), start.getZ(), biomeSource)) continue;
			structureGenerator.generate(generator, start, rand);
			HashMap<Generator.ILootType, List<List<ItemStack>>> lootTypes = shipwreck.getLootEx(2276366175191987160L, structureGenerator, rand, false);

			for(Map.Entry<Generator.ILootType, List<List<ItemStack>>> loots : lootTypes.entrySet()) {
				for(List<ItemStack> loot : loots.getValue()) {
					for(ItemStack stack : loot) hash += stack.hashCode();
				}
			}
		}
		assertEquals(12552439300L, hash, "Items changed maybe?");
	}

}
