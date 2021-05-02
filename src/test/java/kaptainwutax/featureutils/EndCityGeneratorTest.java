package kaptainwutax.featureutils;

import kaptainwutax.biomeutils.source.BiomeSource;
import kaptainwutax.featureutils.structure.generator.EndCityGenerator;
import kaptainwutax.mcutils.rand.ChunkRand;
import kaptainwutax.mcutils.state.Dimension;
import kaptainwutax.mcutils.util.data.Pair;
import kaptainwutax.mcutils.util.pos.BPos;
import kaptainwutax.mcutils.util.pos.CPos;
import kaptainwutax.mcutils.version.MCVersion;
import kaptainwutax.terrainutils.ChunkGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

public class EndCityGeneratorTest {
	@Test
	public void testCorrectChest() {
		MCVersion version = MCVersion.v1_16_5;
		BiomeSource biomeSource = BiomeSource.of(Dimension.END, version, 1L);
		ChunkGenerator generator = ChunkGenerator.of(Dimension.END, biomeSource);
		CPos cPos = new CPos(-73, -15);
		EndCityGenerator endCityGenerator = new EndCityGenerator(version);
		ChunkRand rand = new ChunkRand().asChunkRandDebugger();
		endCityGenerator.generate(generator, cPos, rand);
		List<Pair<EndCityGenerator.LootType, BPos>> loots = endCityGenerator.getChestsPos();
		for (Pair<EndCityGenerator.LootType, BPos> loot : loots) {
			if (loot.getFirst() != EndCityGenerator.LootType.SENTRY) System.out.println(loot);
		}
	}
}
