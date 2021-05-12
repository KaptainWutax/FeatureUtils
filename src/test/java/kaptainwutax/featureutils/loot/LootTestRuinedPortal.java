package kaptainwutax.featureutils.loot;

import kaptainwutax.biomeutils.source.BiomeSource;
import kaptainwutax.featureutils.structure.generator.Generator;
import kaptainwutax.featureutils.structure.generator.structure.DesertPyramidGenerator;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LootTestRuinedPortal {
	private List<Pair<Generator.ILootType, BPos>> loots;
	private Generator structureGenerator;
	private BiomeSource biomeSource;
	private ChunkGenerator generator;

	public void setup(long worldseed, CPos cPos, MCVersion version) {
		biomeSource = BiomeSource.of(Dimension.OVERWORLD, version, worldseed);
		generator = ChunkGenerator.of(Dimension.OVERWORLD, biomeSource);
		structureGenerator = new RuinedPortalGenerator(version);
		ChunkRand rand = new ChunkRand().asChunkRandDebugger();
		structureGenerator.generate(generator, cPos, rand);
		loots = structureGenerator.getChestsPos();
	}

	@Test
	public void testCorrectChest1() {
		// oh wow this one is trippy /tp @s -320 ~ 112
		setup(123L, new BPos(-391,0,313).toChunkPos(), MCVersion.v1_16_5);
		List<Pair<RuinedPortalGenerator.LootType, BPos>> checks = new ArrayList<Pair<RuinedPortalGenerator.LootType, BPos>>() {{
			add(new Pair<>(RuinedPortalGenerator.LootType.RUINED_PORTAL, new BPos(-395,3,310)));
		}};
		for (Pair<RuinedPortalGenerator.LootType, BPos> check : checks) {
			assertTrue(loots.contains(check), String.format("Missing loot %s at pos %s", check.getFirst(), check.getSecond()));
		}
	}

	@Test
	public void testCorrectChest2() {
		setup(1476413308176291228L, new BPos( 153,0, 121).toChunkPos(), MCVersion.v1_16_5);
		List<Pair<RuinedPortalGenerator.LootType, BPos>> checks = new ArrayList<Pair<RuinedPortalGenerator.LootType, BPos>>() {{
			add(new Pair<>(RuinedPortalGenerator.LootType.RUINED_PORTAL, new BPos(150,2,128)));
		}};
		for (Pair<RuinedPortalGenerator.LootType, BPos> check : checks) {
			assertTrue(loots.contains(check), String.format("Missing loot %s at pos %s", check.getFirst(), check.getSecond()));
		}
	}
}
