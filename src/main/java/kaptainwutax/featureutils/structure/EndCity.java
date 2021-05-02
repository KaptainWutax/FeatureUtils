package kaptainwutax.featureutils.structure;

import kaptainwutax.biomeutils.biome.Biome;
import kaptainwutax.biomeutils.biome.Biomes;
import kaptainwutax.featureutils.loot.LootContext;
import kaptainwutax.featureutils.loot.item.ItemStack;
import kaptainwutax.featureutils.structure.generator.EndCityGenerator;
import kaptainwutax.mcutils.rand.ChunkRand;
import kaptainwutax.mcutils.state.Dimension;
import kaptainwutax.mcutils.util.block.BlockRotation;
import kaptainwutax.mcutils.util.data.Pair;
import kaptainwutax.mcutils.util.pos.BPos;
import kaptainwutax.mcutils.util.pos.CPos;
import kaptainwutax.mcutils.version.MCVersion;
import kaptainwutax.mcutils.version.VersionMap;
import kaptainwutax.terrainutils.ChunkGenerator;

import java.util.*;

public class EndCity extends TriangularStructure<EndCity> {

	public static final VersionMap<RegionStructure.Config> CONFIGS = new VersionMap<RegionStructure.Config>()
			.add(MCVersion.v1_9, new RegionStructure.Config(20, 11, 10387313));

	public EndCity(MCVersion version) {
		this(CONFIGS.getAsOf(version), version);
	}

	public EndCity(RegionStructure.Config config, MCVersion version) {
		super(config, version);
	}

	public static String name() {
		return "end_city";
	}

	@Override
	public boolean canStart(Data<EndCity> data, long structureSeed, ChunkRand rand) {
		return super.canStart(data, structureSeed, rand);
	}

	@Override
	public boolean isValidDimension(Dimension dimension) {
		return dimension == Dimension.END;
	}

	@Override
	public boolean isValidBiome(Biome biome) {
		return biome == Biomes.END_MIDLANDS || biome == Biomes.END_HIGHLANDS;
	}

	@Override
	public boolean isValidTerrain(ChunkGenerator generator, int chunkX, int chunkZ) {
		return getAverageYPosition(generator, chunkX, chunkZ) >= 60;
	}

	public static int getAverageYPosition(ChunkGenerator generator, int chunkX, int chunkZ) {
		@SuppressWarnings("IntegerMultiplicationImplicitCastToLong")
		ChunkRand random = new ChunkRand(chunkX + chunkZ * 10387313);
		BlockRotation rotation = BlockRotation.getRandom(random);
		int xOffset = 5;
		int zOffset = 5;
		if (rotation == BlockRotation.CLOCKWISE_90) {
			xOffset = -5;
		} else if (rotation == BlockRotation.CLOCKWISE_180) {
			xOffset = -5;
			zOffset = -5;
		} else if (rotation == BlockRotation.COUNTERCLOCKWISE_90) {
			zOffset = -5;
		}

		int posX = (chunkX << 4) + 7;
		int posZ = (chunkZ << 4) + 7;
		int center = generator.getHeightInGround(posX, posZ);
		int s = generator.getHeightInGround(posX, posZ + zOffset); // SOUTH
		int e = generator.getHeightInGround(posX + xOffset, posZ); //  EAST
		int se = generator.getHeightInGround(posX + xOffset, posZ + zOffset); // SOUTH EAST
		return Math.min(Math.min(center, s), Math.min(e, se));
	}

	/**
	 * This will only return the chest loot, you would have to check with hasShip
	 *
	 * @param structureSeed
	 * @param endCityGenerator
	 * @param rand
	 * @param indexed
	 * @return
	 */
	public HashMap<EndCityGenerator.LootType, List<List<ItemStack>>> getLoot(long structureSeed, EndCityGenerator endCityGenerator, ChunkRand rand, boolean indexed) {
		int salt = 40010; // TODO make me version dependant
		List<Pair<EndCityGenerator.LootType, BPos>> lootPositions = endCityGenerator.getChestsPos();

		HashMap<CPos, LinkedList<Pair<EndCityGenerator.LootType, BPos>>> posLinkedListHashMap = new HashMap<>();
		for (Pair<EndCityGenerator.LootType, BPos> lootPos : lootPositions) {
			if (lootPos.getFirst().lootTable!=null) {
				BPos pos = lootPos.getSecond();
				CPos cPos = pos.toChunkPos();
				if (posLinkedListHashMap.containsKey(cPos)) {
					posLinkedListHashMap.get(cPos).add(lootPos);
				} else {
					posLinkedListHashMap.put(cPos, new LinkedList<>(Collections.singletonList(lootPos)));
				}
			}
		}
		HashMap<EndCityGenerator.LootType, List<ChestData>> chestDataHashMap = new HashMap<>();
		for (CPos cPos : posLinkedListHashMap.keySet()) {
			LinkedList<Pair<EndCityGenerator.LootType, BPos>> lootTypes = posLinkedListHashMap.get(cPos);
			// FIXME index will be wrong I need to use the bpos this is for now a hacky fix
			int index = 0;
			for (Pair<EndCityGenerator.LootType, BPos> lootType : lootTypes) {
				chestDataHashMap.computeIfAbsent(lootType.getFirst(),k->new ArrayList<>()).add(new ChestData(index, cPos, lootTypes.size()));
				index += 1;
			}
		}
		HashMap<EndCityGenerator.LootType, List<List<ItemStack>>> result = new HashMap<>();
		for (EndCityGenerator.LootType lootType : chestDataHashMap.keySet()) {
			List<ChestData> chests = chestDataHashMap.get(lootType);
			for (ChestData chestData:chests){
				CPos chunkChestPos = chestData.cPos;
				rand.setDecoratorSeed(structureSeed, chunkChestPos.getX() * 16, chunkChestPos.getZ() * 16, salt, this.getVersion());
				rand.advance(chestData.numberInChunk * 2L);
				rand.advance(chestData.index * 2L);
				LootContext context = new LootContext(rand.nextLong(), this.getVersion());
				result.computeIfAbsent(lootType,k->new ArrayList<>()).add(indexed ? lootType.lootTable.generateIndexed(context) : lootType.lootTable.generate(context));
			}
		}
		return result;
	}

	public static class ChestData {
		int index;
		CPos cPos;
		int numberInChunk;

		public ChestData(int index, CPos cPos, int numberInChunk) {
			this.index = index;
			this.cPos = cPos;
			this.numberInChunk = numberInChunk;
		}
	}

}
