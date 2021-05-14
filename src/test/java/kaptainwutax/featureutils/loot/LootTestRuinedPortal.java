package kaptainwutax.featureutils.loot;

import kaptainwutax.biomeutils.source.BiomeSource;
import kaptainwutax.featureutils.structure.generator.Generator;
import kaptainwutax.featureutils.structure.generator.structure.RuinedPortalGenerator;
import kaptainwutax.mcutils.rand.ChunkRand;
import kaptainwutax.mcutils.state.Dimension;
import kaptainwutax.mcutils.util.data.Pair;
import kaptainwutax.mcutils.util.pos.BPos;
import kaptainwutax.mcutils.util.pos.CPos;
import kaptainwutax.mcutils.version.MCVersion;
import kaptainwutax.terrainutils.ChunkGenerator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LootTestRuinedPortal {
	private List<Pair<Generator.ILootType, BPos>> loots;
	private Generator structureGenerator;
	private BiomeSource biomeSource;
	private ChunkGenerator generator;

	public void setup(Dimension dimension,long worldseed, CPos cPos, MCVersion version) {
		biomeSource = BiomeSource.of(dimension, version, worldseed);
		generator = ChunkGenerator.of(dimension, biomeSource);
		structureGenerator = new RuinedPortalGenerator(version);
		ChunkRand rand = new ChunkRand().asChunkRandDebugger();
		structureGenerator.generate(generator, cPos, rand);
		loots = structureGenerator.getChestsPos();
	}

	@Test
	public void testCorrectChest1() {
		setup(Dimension.OVERWORLD,123L, new BPos(-311, 0, 121).toChunkPos(), MCVersion.v1_16_5);
		List<Pair<RuinedPortalGenerator.LootType, BPos>> checks = new ArrayList<Pair<RuinedPortalGenerator.LootType, BPos>>() {{
			add(new Pair<>(RuinedPortalGenerator.LootType.RUINED_PORTAL, new BPos(-315, 81, 127)));
		}};
		for (Pair<RuinedPortalGenerator.LootType, BPos> check : checks) {
			assertTrue(loots.contains(check), String.format("Missing loot %s at pos %s for loots: %s", check.getFirst(), check.getSecond(), Arrays.toString(loots.toArray())));
		}
	}

	@Test
	public void testCorrectChest2() {
		setup(Dimension.OVERWORLD,1476413308176291228L, new BPos(153, 0, 121).toChunkPos(), MCVersion.v1_16_5);
		List<Pair<RuinedPortalGenerator.LootType, BPos>> checks = new ArrayList<Pair<RuinedPortalGenerator.LootType, BPos>>() {{
			add(new Pair<>(RuinedPortalGenerator.LootType.RUINED_PORTAL, new BPos(150, 67, 128)));
		}};
		for (Pair<RuinedPortalGenerator.LootType, BPos> check : checks) {
			assertTrue(loots.contains(check), String.format("Missing loot %s at pos %s for loots: %s", check.getFirst(), check.getSecond(), Arrays.toString(loots.toArray())));
		}
	}

	@Test
	public void testCorrectChest3() {
		setup(Dimension.OVERWORLD,7948314503011477316L, new CPos(8, 10), MCVersion.v1_16_5);
		List<Pair<RuinedPortalGenerator.LootType, BPos>> checks = new ArrayList<Pair<RuinedPortalGenerator.LootType, BPos>>() {{
			add(new Pair<>(RuinedPortalGenerator.LootType.RUINED_PORTAL, new BPos(128, 78, 162)));
		}};
		for (Pair<RuinedPortalGenerator.LootType, BPos> check : checks) {
			assertTrue(loots.contains(check), String.format("Missing loot %s at pos %s for loots: %s", check.getFirst(), check.getSecond(), Arrays.toString(loots.toArray())));
		}
	}

	@Test
	public void testCorrectChest4() {
		setup(Dimension.OVERWORLD,7948314503011477316L,new BPos(112 ,0, 1616).toChunkPos(), MCVersion.v1_16_5);
		List<Pair<RuinedPortalGenerator.LootType, BPos>> checks = new ArrayList<Pair<RuinedPortalGenerator.LootType, BPos>>() {{
			add(new Pair<>(RuinedPortalGenerator.LootType.RUINED_PORTAL, new BPos(53, 28, 82)));
		}};
		for (Pair<RuinedPortalGenerator.LootType, BPos> check : checks) {
			assertTrue(loots.contains(check), String.format("Missing loot %s at pos %s for loots: %s", check.getFirst(), check.getSecond(), Arrays.toString(loots.toArray())));
		}
	}

	@Test
	public void testCorrectChest5() {
		setup(Dimension.NETHER,7948314503011477316L,new BPos(48 ,0, 80).toChunkPos(), MCVersion.v1_16_5);
		List<Pair<RuinedPortalGenerator.LootType, BPos>> checks = new ArrayList<Pair<RuinedPortalGenerator.LootType, BPos>>() {{
			add(new Pair<>(RuinedPortalGenerator.LootType.RUINED_PORTAL, new BPos(113, 38, 1629)));
		}};
		for (Pair<RuinedPortalGenerator.LootType, BPos> check : checks) {
			assertTrue(loots.contains(check), String.format("Missing loot %s at pos %s for loots: %s", check.getFirst(), check.getSecond(), Arrays.toString(loots.toArray())));
		}
	}

	@Test
	public void testCorrectChest6() {
		setup(Dimension.NETHER,7948314503011477316L,new CPos(-11,-13), MCVersion.v1_16_5);
		List<Pair<RuinedPortalGenerator.LootType, BPos>> checks = new ArrayList<Pair<RuinedPortalGenerator.LootType, BPos>>() {{
			add(new Pair<>(RuinedPortalGenerator.LootType.RUINED_PORTAL, new BPos(-170, 50, -192)));
		}};
		for (Pair<RuinedPortalGenerator.LootType, BPos> check : checks) {
			assertTrue(loots.contains(check), String.format("Missing loot %s at pos %s for loots: %s", check.getFirst(), check.getSecond(), Arrays.toString(loots.toArray())));
		}
	}

	@Test
	public void testCorrectChest7() {
		setup(Dimension.NETHER,7948314503011477316L,new CPos(25,9), MCVersion.v1_16_5);
		List<Pair<RuinedPortalGenerator.LootType, BPos>> checks = new ArrayList<Pair<RuinedPortalGenerator.LootType, BPos>>() {{
			add(new Pair<>(RuinedPortalGenerator.LootType.RUINED_PORTAL, new BPos(-170, 50, -192)));
		}};
		for (Pair<RuinedPortalGenerator.LootType, BPos> check : checks) {
			assertTrue(loots.contains(check), String.format("Missing loot %s at pos %s for loots: %s", check.getFirst(), check.getSecond(), Arrays.toString(loots.toArray())));
		}
	}

	@Test
	public void testCorrectChest8() {
		setup(Dimension.NETHER,7948314503011477316L,new CPos(-23,8), MCVersion.v1_16_5);
		List<Pair<RuinedPortalGenerator.LootType, BPos>> checks = new ArrayList<Pair<RuinedPortalGenerator.LootType, BPos>>() {{
			add(new Pair<>(RuinedPortalGenerator.LootType.RUINED_PORTAL, new BPos(-366, 34, 139)));
		}};
		for (Pair<RuinedPortalGenerator.LootType, BPos> check : checks) {
			assertTrue(loots.contains(check), String.format("Missing loot %s at pos %s for loots: %s", check.getFirst(), check.getSecond(), Arrays.toString(loots.toArray())));
		}
	}

	@Test
	public void testCorrectChest9() {
		setup(Dimension.NETHER,7948314503011477316L,new CPos(3,5), MCVersion.v1_16_5);
		List<Pair<RuinedPortalGenerator.LootType, BPos>> checks = new ArrayList<Pair<RuinedPortalGenerator.LootType, BPos>>() {{
			add(new Pair<>(RuinedPortalGenerator.LootType.RUINED_PORTAL, new BPos(-366, 34, 139)));
		}};
		for (Pair<RuinedPortalGenerator.LootType, BPos> check : checks) {
			assertTrue(loots.contains(check), String.format("Missing loot %s at pos %s for loots: %s", check.getFirst(), check.getSecond(), Arrays.toString(loots.toArray())));
		}
	}
}
