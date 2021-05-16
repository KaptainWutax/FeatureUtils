package kaptainwutax.featureutils.loot;

import kaptainwutax.featureutils.loot.item.ItemStack;
import kaptainwutax.featureutils.structure.generator.Generator;
import kaptainwutax.mcutils.rand.ChunkRand;
import kaptainwutax.mcutils.util.data.Pair;
import kaptainwutax.mcutils.util.pos.BPos;
import kaptainwutax.mcutils.util.pos.CPos;
import kaptainwutax.mcutils.version.MCVersion;
import kaptainwutax.seedutils.lcg.LCG;
import kaptainwutax.seedutils.rand.JRand;

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
		HashMap<Generator.ILootType, List<ChestData>> chestDataHashMap = new HashMap<>();
		for (CPos cPos : posLinkedListHashMap.keySet()) {
			LinkedList<Pair<Generator.ILootType, BPos>> lootTypes = posLinkedListHashMap.get(cPos);
			// FIXME index will be wrong I need to use the bpos this is for now a hacky fix
			int index = 0;
			for (Pair<Generator.ILootType, BPos> lootType : lootTypes) {
				chestDataHashMap.computeIfAbsent(lootType.getFirst(), k -> new ArrayList<>()).add(new ChestData(index, cPos, lootType.getSecond(), lootTypes.size()));
				index += 1;
			}
		}
		HashMap<Generator.ILootType, List<List<ItemStack>>> result = new HashMap<>();
		for (Generator.ILootType lootType : chestDataHashMap.keySet()) {
			List<ChestData> chests = chestDataHashMap.get(lootType);
			for (ChestData chestData : chests) {
				CPos chunkChestPos = chestData.getcPos();
				rand.setDecoratorSeed(structureSeed, chunkChestPos.getX() * 16, chunkChestPos.getZ() * 16, this.getDecorationSalt(), this.getVersion());
				SpecificCalls calls = this.getSpecificCalls();
				if (calls != null) calls.run(generator, rand);
				if (shouldAdvanceInChunks()) rand.advance(chestData.getNumberInChunk() * 2L);
				rand.advance(chestData.getIndex() * 2L);
				LootContext context = new LootContext(rand.nextLong(), this.getVersion());
				List<ItemStack> loot = indexed ? lootType.getLootTable().generateIndexed(context) : lootType.getLootTable().generate(context);
				result.computeIfAbsent(lootType, k -> new ArrayList<>()).add(loot);
			}
		}
		return result;
	}

	/**
	 * Sets the mode to advance for the number of feature in that chunk, this account for template doing twice the loot table seed,
	 * normal structure don't, be sure to override
	 *
	 * @return if should advance in that chunk twice
	 */
	default boolean shouldAdvanceInChunks() {
		return true;
	}

	int getDecorationSalt();

	boolean isCorrectGenerator(Generator generator);

	// this actually abuse inheritance for features
	MCVersion getVersion();

	SpecificCalls getSpecificCalls();

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

	@FunctionalInterface
	interface SpecificCalls {
		void run(Generator generator, ChunkRand rand);
	}
}
