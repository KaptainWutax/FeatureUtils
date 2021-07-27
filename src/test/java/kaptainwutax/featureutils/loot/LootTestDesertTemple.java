package kaptainwutax.featureutils.loot;

import kaptainwutax.biomeutils.source.BiomeSource;
import kaptainwutax.featureutils.loot.item.ItemStack;
import kaptainwutax.featureutils.structure.DesertPyramid;
import kaptainwutax.featureutils.structure.generator.Generator;
import kaptainwutax.featureutils.structure.generator.structure.DesertPyramidGenerator;
import kaptainwutax.mcutils.rand.ChunkRand;
import kaptainwutax.mcutils.state.Dimension;
import kaptainwutax.mcutils.util.data.Pair;
import kaptainwutax.mcutils.util.pos.BPos;
import kaptainwutax.mcutils.util.pos.CPos;
import kaptainwutax.mcutils.version.MCVersion;
import kaptainwutax.terrainutils.TerrainGenerator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LootTestDesertTemple {
	private List<Pair<Generator.ILootType, BPos>> loots;
	private Generator structureGenerator;
	private BiomeSource biomeSource;
	private TerrainGenerator generator;

	public void setup(long worldseed, CPos cPos, MCVersion version) {
		biomeSource = BiomeSource.of(Dimension.OVERWORLD, version, worldseed);
		generator = TerrainGenerator.of(Dimension.OVERWORLD, biomeSource);
		structureGenerator = new DesertPyramidGenerator(version);
		ChunkRand rand = new ChunkRand().asChunkRandDebugger();
		structureGenerator.generate(generator, cPos, rand);
		loots = structureGenerator.getChestsPos();
	}

	@Test
	public void testCorrectChest1() {
		setup(123L, new BPos(2777, 60, -1159).toChunkPos(), MCVersion.v1_16_5);
		List<Pair<DesertPyramidGenerator.LootType, BPos>> checks = new ArrayList<Pair<DesertPyramidGenerator.LootType, BPos>>() {{
			add(new Pair<>(DesertPyramidGenerator.LootType.CHEST_1, new BPos(2768, 0, -1168)));
			add(new Pair<>(DesertPyramidGenerator.LootType.CHEST_2, new BPos(2768, 0, -1168)));
			add(new Pair<>(DesertPyramidGenerator.LootType.CHEST_3, new BPos(2768, 0, -1168)));
			add(new Pair<>(DesertPyramidGenerator.LootType.CHEST_4, new BPos(2768, 0, -1168)));
		}};
		for(Pair<DesertPyramidGenerator.LootType, BPos> check : checks) {
			assertTrue(loots.contains(check), String.format("Missing loot %s at pos %s", check.getFirst(), check.getSecond()));
		}
	}


	@Test
	public void testChestLoot() {
		setup(123L, new BPos(2777, 60, -1159).toChunkPos(), MCVersion.v1_16_5);
		DesertPyramid desertPyramid = new DesertPyramid(MCVersion.v1_16_5);
		List<ChestContent> chests = desertPyramid.getLoot(123L, structureGenerator, new ChunkRand(), false);
		long hash = 0;
		for(ChestContent chest : chests) {
			for(ItemStack stack : chest.getItems()) hash += stack.hashCode();
		}
		assertEquals(11777201006L, hash, "Items changed maybe?");
	}

	@Test
	public void testChestLoot2() {
		setup(3119024338951782547L, new BPos(761, 60, -743).toChunkPos(), MCVersion.v1_16_5);
		DesertPyramid desertPyramid = new DesertPyramid(MCVersion.v1_16_5);
		List<ChestContent> chests = desertPyramid.getLoot(3119024338951782547L, structureGenerator, new ChunkRand(), false);
		long hash = 0;
		for(ChestContent chest : chests) {
			for(ItemStack stack : chest.getItems()) hash += stack.hashCode();
		}
		assertEquals(8379442605L, hash, "Items changed maybe?");
	}
}
