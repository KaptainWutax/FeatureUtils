package kaptainwutax.featureutils.loot;

import kaptainwutax.biomeutils.source.BiomeSource;
import kaptainwutax.featureutils.loot.item.ItemStack;
import kaptainwutax.featureutils.structure.RuinedPortal;
import kaptainwutax.featureutils.structure.generator.Generator;
import kaptainwutax.featureutils.structure.generator.structure.RuinedPortalGenerator;
import kaptainwutax.mcutils.block.Block;
import kaptainwutax.mcutils.block.Blocks;
import kaptainwutax.mcutils.rand.ChunkRand;
import kaptainwutax.mcutils.state.Dimension;
import kaptainwutax.mcutils.util.data.Pair;
import kaptainwutax.mcutils.util.pos.BPos;
import kaptainwutax.mcutils.util.pos.CPos;
import kaptainwutax.mcutils.version.MCVersion;
import kaptainwutax.terrainutils.TerrainGenerator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LootTestRuinedPortal {
	private List<Pair<Generator.ILootType, BPos>> loots;
	private List<Pair<Block, BPos>> portal;
	private Generator structureGenerator;
	private BiomeSource biomeSource;
	private TerrainGenerator generator;

	public void setup(Dimension dimension, long worldseed, CPos cPos, MCVersion version) {
		biomeSource = BiomeSource.of(dimension, version, worldseed);
		generator = TerrainGenerator.of(dimension, biomeSource);
		structureGenerator = new RuinedPortalGenerator(version);
		ChunkRand rand = new ChunkRand().asChunkRandDebugger();
		structureGenerator.generate(generator, cPos, rand);
		loots = structureGenerator.getChestsPos();
		portal = ((RuinedPortalGenerator) structureGenerator).getPortal();
	}

	@Test
	public void testCorrectChest1() {
		setup(Dimension.OVERWORLD, 123L, new BPos(-311, 0, 121).toChunkPos(), MCVersion.v1_16_5);
		List<Pair<RuinedPortalGenerator.LootType, BPos>> checks = new ArrayList<Pair<RuinedPortalGenerator.LootType, BPos>>() {{
			add(new Pair<>(RuinedPortalGenerator.LootType.RUINED_PORTAL, new BPos(-315, 81, 127)));
		}};
		for (Pair<RuinedPortalGenerator.LootType, BPos> check : checks) {
			assertTrue(loots.contains(check), String.format("Missing loot %s at pos %s for loots: %s", check.getFirst(), check.getSecond(), Arrays.toString(loots.toArray())));
		}
	}

	@Test
	public void testCorrectChest2() {
		setup(Dimension.OVERWORLD, 1476413308176291228L, new BPos(153, 0, 121).toChunkPos(), MCVersion.v1_16_5);
		List<Pair<RuinedPortalGenerator.LootType, BPos>> checks = new ArrayList<Pair<RuinedPortalGenerator.LootType, BPos>>() {{
			add(new Pair<>(RuinedPortalGenerator.LootType.RUINED_PORTAL, new BPos(150, 67, 128)));
		}};
		for (Pair<RuinedPortalGenerator.LootType, BPos> check : checks) {
			assertTrue(loots.contains(check), String.format("Missing loot %s at pos %s for loots: %s", check.getFirst(), check.getSecond(), Arrays.toString(loots.toArray())));
		}
	}

	@Test
	public void testCorrectChest3() {
		setup(Dimension.OVERWORLD, 7948314503011477316L, new CPos(8, 10), MCVersion.v1_16_5);
		List<Pair<RuinedPortalGenerator.LootType, BPos>> checks = new ArrayList<Pair<RuinedPortalGenerator.LootType, BPos>>() {{
			add(new Pair<>(RuinedPortalGenerator.LootType.RUINED_PORTAL, new BPos(128, 78, 162)));
		}};
		for (Pair<RuinedPortalGenerator.LootType, BPos> check : checks) {
			assertTrue(loots.contains(check), String.format("Missing loot %s at pos %s for loots: %s", check.getFirst(), check.getSecond(), Arrays.toString(loots.toArray())));
		}
	}

	@Test
	public void testCorrectChest4() {
		setup(Dimension.OVERWORLD, 7948314503011477316L, new CPos(7, 101), MCVersion.v1_16_5);
		List<Pair<RuinedPortalGenerator.LootType, BPos>> checks = new ArrayList<Pair<RuinedPortalGenerator.LootType, BPos>>() {{
			add(new Pair<>(RuinedPortalGenerator.LootType.RUINED_PORTAL, new BPos(113, 38, 1629)));
		}};
		for (Pair<RuinedPortalGenerator.LootType, BPos> check : checks) {
			assertTrue(loots.contains(check), String.format("Missing loot %s at pos %s for loots: %s", check.getFirst(), check.getSecond(), Arrays.toString(loots.toArray())));
		}
	}

	@Test
	public void testCorrectChest5() {
		setup(Dimension.NETHER, 7948314503011477316L, new CPos(3, 5), MCVersion.v1_16_5);
		List<Pair<RuinedPortalGenerator.LootType, BPos>> checks = new ArrayList<Pair<RuinedPortalGenerator.LootType, BPos>>() {{
			// this blocks doesn't generate (removed by lava)
			//add(new Pair<>(RuinedPortalGenerator.LootType.RUINED_PORTAL, new BPos(53, 28, 82)));
		}};
		assertTrue(loots.isEmpty());
	}

	@Test
	public void testCorrectChest10() {
		setup(Dimension.OVERWORLD, -7387955057302025707L, new CPos(9, 7), MCVersion.v1_16_1);
		// 20w27a fixed the netherrack spread that remove chest (we don't support it)
		//assertTrue(loots.isEmpty());
		setup(Dimension.OVERWORLD, -7387955057302025707L, new CPos(9, 7), MCVersion.v1_16_2);
		List<Pair<RuinedPortalGenerator.LootType, BPos>> checks = new ArrayList<Pair<RuinedPortalGenerator.LootType, BPos>>() {{
			add(new Pair<>(RuinedPortalGenerator.LootType.RUINED_PORTAL, new BPos(145, 69, 116)));
		}};
		for (Pair<RuinedPortalGenerator.LootType, BPos> check : checks) {
			assertTrue(loots.contains(check), String.format("Missing loot %s at pos %s for loots: %s", check.getFirst(), check.getSecond(), Arrays.toString(loots.toArray())));
		}
	}

	@Test
	public void testCorrectChest6() {
		setup(Dimension.NETHER, 7948314503011477316L, new CPos(-11, -13), MCVersion.v1_16_5);
		List<Pair<RuinedPortalGenerator.LootType, BPos>> checks = new ArrayList<Pair<RuinedPortalGenerator.LootType, BPos>>() {{
			add(new Pair<>(RuinedPortalGenerator.LootType.RUINED_PORTAL, new BPos(-170, 50, -192)));
		}};
		for (Pair<RuinedPortalGenerator.LootType, BPos> check : checks) {
			assertTrue(loots.contains(check), String.format("Missing loot %s at pos %s for loots: %s", check.getFirst(), check.getSecond(), Arrays.toString(loots.toArray())));
		}
	}

	@Test
	public void testCorrectChest7() {
		setup(Dimension.NETHER, 7948314503011477316L, new CPos(25, 9), MCVersion.v1_16_5);
		List<Pair<RuinedPortalGenerator.LootType, BPos>> checks = new ArrayList<Pair<RuinedPortalGenerator.LootType, BPos>>() {{
			add(new Pair<>(RuinedPortalGenerator.LootType.RUINED_PORTAL, new BPos(405, 102, 146)));
		}};
		for (Pair<RuinedPortalGenerator.LootType, BPos> check : checks) {
			assertTrue(loots.contains(check), String.format("Missing loot %s at pos %s for loots: %s", check.getFirst(), check.getSecond(), Arrays.toString(loots.toArray())));
		}
	}

	@Test
	public void testCorrectChest8() {
		setup(Dimension.NETHER, 7948314503011477316L, new CPos(-23, 8), MCVersion.v1_16_5);
		List<Pair<RuinedPortalGenerator.LootType, BPos>> checks = new ArrayList<Pair<RuinedPortalGenerator.LootType, BPos>>() {{
			add(new Pair<>(RuinedPortalGenerator.LootType.RUINED_PORTAL, new BPos(-366, 34, 139)));
		}};
		for (Pair<RuinedPortalGenerator.LootType, BPos> check : checks) {
			assertTrue(loots.contains(check), String.format("Missing loot %s at pos %s for loots: %s", check.getFirst(), check.getSecond(), Arrays.toString(loots.toArray())));
		}
	}

	@Test
	public void testCorrectChest12() {
		setup(Dimension.OVERWORLD, 239648, new BPos(64, 0, 112).toChunkPos(), MCVersion.v1_16_5);
		List<Pair<RuinedPortalGenerator.LootType, BPos>> checks = new ArrayList<Pair<RuinedPortalGenerator.LootType, BPos>>() {{
			add(new Pair<>(RuinedPortalGenerator.LootType.RUINED_PORTAL, new BPos(-366, 34, 139)));
		}};
		RuinedPortal ruinedPortal = new RuinedPortal(this.biomeSource.getDimension(), this.biomeSource.getVersion());
		ChunkRand rand=new ChunkRand();
		ChunkRand rand1=rand.asChunkRandDebugger();
		HashMap<Generator.ILootType, List<List<ItemStack>>> lootTypeListHashMap = ruinedPortal.getLootEx(this.biomeSource.getWorldSeed(), this.structureGenerator, rand1, false);
		assertTrue(lootTypeListHashMap.containsKey(RuinedPortalGenerator.LootType.RUINED_PORTAL));
		List<List<ItemStack>> l = lootTypeListHashMap.get(RuinedPortalGenerator.LootType.RUINED_PORTAL);
		assertEquals(1, l.size());
		List<ItemStack> loot = l.get(0);
		long hashcode = 0;
		for (ItemStack stack : loot) hashcode += stack.hashCode();
		assertEquals(-1138008234, hashcode, "Something changed in loot");
	}

	@Test
	public void testCorrectChest11() {
		setup(Dimension.NETHER, -7002427602017045587L, new BPos(-400, 0, 1632).toChunkPos(), MCVersion.v1_16_5);
		List<Pair<RuinedPortalGenerator.LootType, BPos>> checks = new ArrayList<Pair<RuinedPortalGenerator.LootType, BPos>>() {{
			add(new Pair<>(RuinedPortalGenerator.LootType.RUINED_PORTAL, new BPos(-399, 28, 1634)));
		}};
		for (Pair<RuinedPortalGenerator.LootType, BPos> check : checks) {
			assertTrue(loots.contains(check), String.format("Missing loot %s at pos %s for loots: %s", check.getFirst(), check.getSecond(), Arrays.toString(loots.toArray())));
		}
		RuinedPortal ruinedPortal = new RuinedPortal(this.biomeSource.getDimension(), this.biomeSource.getVersion());
		HashMap<Generator.ILootType, List<List<ItemStack>>> lootTypeListHashMap = ruinedPortal.getLootEx(this.biomeSource.getWorldSeed(), this.structureGenerator, new ChunkRand(), false);
		assertTrue(lootTypeListHashMap.containsKey(RuinedPortalGenerator.LootType.RUINED_PORTAL));
		List<List<ItemStack>> l = lootTypeListHashMap.get(RuinedPortalGenerator.LootType.RUINED_PORTAL);
		assertEquals(1, l.size());
		List<ItemStack> loot = l.get(0);
		long hashcode = 0;
		for (ItemStack stack : loot) hashcode += stack.hashCode();
		assertEquals(-910440243, hashcode, "Something changed in loot");
	}

	@Test
	public void testPortal() {
		setup(Dimension.OVERWORLD, 7948314503011477316L, new CPos(64, 40), MCVersion.v1_16_5);
		List<Pair<RuinedPortalGenerator.LootType, BPos>> checks = new ArrayList<Pair<RuinedPortalGenerator.LootType, BPos>>() {{
			add(new Pair<>(RuinedPortalGenerator.LootType.RUINED_PORTAL, new BPos(1035, 65, 642)));
		}};
		for (Pair<RuinedPortalGenerator.LootType, BPos> check : checks) {
			assertTrue(loots.contains(check), String.format("Missing loot %s at pos %s for loots: %s", check.getFirst(), check.getSecond(), Arrays.toString(loots.toArray())));
		}
		List<Pair<Block, BPos>> blocks = new ArrayList<Pair<Block, BPos>>() {{
			// bottom left and clockwise (towards East)
			add(new Pair<>(Blocks.OBSIDIAN, new BPos(1036, 65, 643)));
			add(new Pair<>(Blocks.OBSIDIAN, new BPos(1036, 66, 643)));
			add(new Pair<>(Blocks.OBSIDIAN, new BPos(1036, 67, 643)));
			add(new Pair<>(Blocks.OBSIDIAN, new BPos(1036, 68, 643)));
			add(new Pair<>(Blocks.OBSIDIAN, new BPos(1036, 69, 643)));
			add(new Pair<>(Blocks.OBSIDIAN, new BPos(1036, 69, 644)));
			add(new Pair<>(Blocks.CRYING_OBSIDIAN, new BPos(1036, 65, 646)));
			add(new Pair<>(Blocks.CRYING_OBSIDIAN, new BPos(1036, 65, 645)));
			add(new Pair<>(Blocks.CRYING_OBSIDIAN, new BPos(1036, 65, 644)));
		}};
		RuinedPortalGenerator ruinedPortalGenerator = (RuinedPortalGenerator) structureGenerator;
		for (Pair<Block, BPos> block : blocks) {
			assertTrue(portal.contains(block), String.format("Missing loot %s at pos %s for loots: %s", block.getFirst(), block.getSecond(), Arrays.toString(portal.toArray())));
		}
		for (Pair<Block, BPos> block : blocks) {
			assertTrue(ruinedPortalGenerator.getObsidian().contains(block), String.format("Missing loot %s at pos %s for loots: %s", block.getFirst(), block.getSecond(), Arrays.toString(portal.toArray())));
		}
		// others
		assertTrue(ruinedPortalGenerator.getObsidian().contains(new Pair<>(Blocks.OBSIDIAN, new BPos(1038, 65, 645))));
		assertTrue(ruinedPortalGenerator.getObsidian().contains(new Pair<>(Blocks.OBSIDIAN, new BPos(1038, 64, 646))));
	}
}
