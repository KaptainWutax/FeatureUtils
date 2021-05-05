package kaptainwutax.featureutils.loot;

import kaptainwutax.featureutils.loot.item.ItemStack;
import kaptainwutax.featureutils.structure.generator.EndCityGenerator;
import kaptainwutax.featureutils.structure.generator.Generator;
import kaptainwutax.mcutils.rand.ChunkRand;
import kaptainwutax.mcutils.util.data.Pair;
import kaptainwutax.mcutils.util.pos.BPos;
import kaptainwutax.mcutils.util.pos.CPos;
import kaptainwutax.mcutils.version.MCVersion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public interface ILoot {

	default HashMap<Generator.ILootType, List<List<ItemStack>>> getLoot(long structureSeed, Generator generator, ChunkRand rand, boolean indexed) {
		if (!isCorrectGenerator(generator)) return null;
		List<Pair<Generator.ILootType, BPos>> lootPositions = generator.getChestsPos();

		HashMap<CPos, LinkedList<Pair<Generator.ILootType, BPos>>> posLinkedListHashMap = new HashMap<>();
		for (Pair<Generator.ILootType, BPos> lootPos : lootPositions) {
			if (lootPos.getFirst().getLootTable() != null) {
				BPos pos = lootPos.getSecond();
				CPos cPos = pos.toChunkPos();
				posLinkedListHashMap.computeIfAbsent(cPos, k -> new LinkedList<>()).add(lootPos);
			}
		}
		HashMap<EndCityGenerator.LootType, List<ChestData>> chestDataHashMap = new HashMap<>();
		for (CPos cPos : posLinkedListHashMap.keySet()) {
			LinkedList<Pair<Generator.ILootType, BPos>> lootTypes = posLinkedListHashMap.get(cPos);
			// FIXME index will be wrong I need to use the bpos this is for now a hacky fix
			int index = 0;
			for (Pair<Generator.ILootType, BPos> lootType : lootTypes) {
				chestDataHashMap.computeIfAbsent((EndCityGenerator.LootType) lootType.getFirst(), k -> new ArrayList<>()).add(new ChestData(index, cPos, lootType.getSecond(), lootTypes.size()));
				index += 1;
			}
		}
		HashMap<Generator.ILootType, List<List<ItemStack>>> result = new HashMap<>();
		for (EndCityGenerator.LootType lootType : chestDataHashMap.keySet()) {
			List<ChestData> chests = chestDataHashMap.get(lootType);
			for (ChestData chestData : chests) {
				CPos chunkChestPos = chestData.getcPos();
				long populationSeed = rand.setPopulationSeed(structureSeed, chunkChestPos.getX() * 16, chunkChestPos.getZ() * 16, this.getVersion());
				rand.setDecoratorSeed(populationSeed, this.getDecorationSalt(), this.getVersion());
				rand.advance(chestData.getNumberInChunk() * 2L);
				rand.advance(chestData.getIndex() * 2L);
				LootContext context = new LootContext(rand.nextLong(), this.getVersion());
				List<ItemStack> loot = indexed ? lootType.lootTable.generateIndexed(context) : lootType.lootTable.generate(context);
				result.computeIfAbsent(lootType, k -> new ArrayList<>()).add(loot);
			}
		}
		return result;
	}

	int getDecorationSalt();

	boolean isCorrectGenerator(Generator generator);

	// this actually abuse inheritance for features
	MCVersion getVersion();

	class ChestData {
		int index;
		CPos cPos;
		BPos bpos;
		int numberInChunk;

		public ChestData(int index, CPos cPos, BPos bPos, int numberInChunk) {
			this.index = index;
			this.cPos = cPos;
			this.bpos = bPos;
			this.numberInChunk = numberInChunk;
		}

		public BPos getBpos() {
			return bpos;
		}

		public CPos getcPos() {
			return cPos;
		}

		public int getIndex() {
			return index;
		}

		public int getNumberInChunk() {
			return numberInChunk;
		}

		@Override
		public String toString() {
			return "ChestData{" +
					"index=" + index +
					", cPos=" + cPos +
					", bpos=" + bpos +
					", numberInChunk=" + numberInChunk +
					'}';
		}
	}
}
