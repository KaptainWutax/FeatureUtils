package kaptainwutax.featureutils;

import kaptainwutax.biomeutils.source.BiomeSource;
import kaptainwutax.featureutils.loot.item.ItemStack;
import kaptainwutax.featureutils.structure.EndCity;
import kaptainwutax.featureutils.structure.generator.EndCityGenerator;
import kaptainwutax.mcutils.rand.ChunkRand;
import kaptainwutax.mcutils.state.Dimension;
import kaptainwutax.mcutils.util.data.Pair;
import kaptainwutax.mcutils.util.pos.BPos;
import kaptainwutax.mcutils.util.pos.CPos;
import kaptainwutax.mcutils.version.MCVersion;
import kaptainwutax.terrainutils.ChunkGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static kaptainwutax.featureutils.structure.generator.EndCityGenerator.LootType.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EndCityGeneratorTest {
	private List<Pair<EndCityGenerator.LootType, BPos>> loots;
	private EndCityGenerator endCityGenerator;

	public void setup(long worldseed,CPos cPos,MCVersion version) {
		BiomeSource biomeSource = BiomeSource.of(Dimension.END, version, worldseed);
		ChunkGenerator generator = ChunkGenerator.of(Dimension.END, biomeSource);
		endCityGenerator = new EndCityGenerator(version);
		ChunkRand rand = new ChunkRand().asChunkRandDebugger();
		endCityGenerator.generate(generator, cPos, rand);
		loots = endCityGenerator.getChestsPos();
	}

	@Test
	public void testCorrectChest1() {
		setup(1L,new BPos(-1232, 0, -25280).toChunkPos(),MCVersion.v1_16_5);
		assertTrue(endCityGenerator.hasShip());
		List<Pair<EndCityGenerator.LootType, BPos>> checks=new ArrayList<Pair<EndCityGenerator.LootType, BPos>>() {{
			add(new Pair<>(SHIP_CHEST_1, new BPos(-1222, 100, -25202)));
			add(new Pair<>(SHIP_CHEST_2, new BPos(-1224, 100, -25202)));
			add(new Pair<>(SHIP_ELYTRA, new BPos(-1223, 100, -25202)));
		}};
		for (Pair<EndCityGenerator.LootType, BPos> check:checks){
			assertTrue(loots.contains(check),String.format("Missing loot %s at pos %s",check.getFirst(),check.getSecond()));
		}
	}

	@Test
	public void testCorrectChest2() {
		// TODO see why this one generated 5 too high
		setup(1L,new BPos(-12752 ,0,-30976).toChunkPos(),MCVersion.v1_16_5);
		List<Pair<EndCityGenerator.LootType, BPos>> checks=new ArrayList<Pair<EndCityGenerator.LootType, BPos>>() {{
			add(new Pair<>(THIRD_FLOOR_CHEST, new BPos(-12711, 103, -30973)));
		}};
		for (Pair<EndCityGenerator.LootType, BPos> check:checks){
			assertTrue(loots.contains(check),String.format("Missing loot %s at pos %s",check.getFirst(),check.getSecond()));
		}
	}

	@Test
	public void testCorrectChest3() {
		setup(1L,new BPos(-127280 ,0, -30944).toChunkPos(),MCVersion.v1_16_5);
		assertTrue(endCityGenerator.hasShip());
		List<Pair<EndCityGenerator.LootType, BPos>> checks=new ArrayList<Pair<EndCityGenerator.LootType, BPos>>() {{
			add(new Pair<>(FAT_TOWER_TOP_CHEST_1, new BPos(-127270, 123, -30943)));
			add(new Pair<>(FAT_TOWER_TOP_CHEST_2, new BPos(-127272, 123, -30945)));
			// those are also 5 too high
			add(new Pair<>(SHIP_CHEST_1, new BPos(-127272, 145, -30907)));
			add(new Pair<>(SHIP_ELYTRA, new BPos(-127273, 145, -30907)));
			add(new Pair<>(SHIP_CHEST_2, new BPos(-127274, 145, -30907)));
			add(new Pair<>(THIRD_FLOOR_CHEST, new BPos(-127276, 127, -30989)));
		}};
		System.out.println(loots);
		for (Pair<EndCityGenerator.LootType, BPos> check:checks){
			assertTrue(loots.contains(check),String.format("Missing loot %s at pos %s",check.getFirst(),check.getSecond()));
		}
	}

	@Test
	public void testChestLoot() {
		setup(1L,new BPos(-127280 ,0, -30944).toChunkPos(),MCVersion.v1_16_5);
		EndCity endCity=new EndCity(MCVersion.v1_16_5);
		HashMap<EndCityGenerator.LootType, List<ItemStack>> loots= endCity.getLoot(1L,endCityGenerator,new ChunkRand(),false);
		for (Map.Entry<EndCityGenerator.LootType, List<ItemStack>> loot:loots.entrySet()){
			System.out.println(loot);
		}
	}

}
