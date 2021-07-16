package kaptainwutax.featureutils.loot;

import kaptainwutax.featureutils.Feature;
import kaptainwutax.featureutils.GenerationContext;
import kaptainwutax.featureutils.loot.item.ItemStack;
import kaptainwutax.featureutils.structure.generator.Generator;
import kaptainwutax.featureutils.structure.generator.Generators;
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

	/**
	 * Utility to get the loot from all the chests in a structure
	 *
	 * @param structureSeed the structure seed (lower 48 bits)
	 * @param generator     the structure generator used to generate the structure
	 * @param rand          a chunkrand instance (for optimization, can be in any state)
	 * @param indexed       a boolean to indicate if the chest content should be spread or not,
	 *                      WARNING, if you put is a true then you will have null items, we
	 *                      recommend to have it to false for any checking
	 * @return list of chestContent that can be used to refine the search
	 */
	default List<ChestContent> getLoot(long structureSeed, Generator generator, ChunkRand rand, boolean indexed) {
		if(!isCorrectGenerator(generator)) return null;
		List<Pair<Generator.ILootType, BPos>> lootPositions = generator.getLootPos();

		HashMap<CPos, LinkedList<Pair<Generator.ILootType, BPos>>> posLinkedListHashMap = new HashMap<>();
		for(Pair<Generator.ILootType, BPos> lootPos : lootPositions) {
			if(lootPos.getFirst().getLootTable(this.getVersion()) != null) {
				BPos pos = lootPos.getSecond();
				CPos cPos = pos.toChunkPos();
				posLinkedListHashMap.computeIfAbsent(cPos, k -> new LinkedList<>()).add(lootPos);
			}
		}
		HashMap<Generator.ILootType, List<ChestData>> chestDataHashMap = new HashMap<>();
		for(CPos cPos : posLinkedListHashMap.keySet()) {
			LinkedList<Pair<Generator.ILootType, BPos>> lootTypes = posLinkedListHashMap.get(cPos);
			// FIXME index will be wrong I need to use the bpos this is for now a hacky fix
			int index = 0;
			for(Pair<Generator.ILootType, BPos> lootType : lootTypes) {
				chestDataHashMap.computeIfAbsent(lootType.getFirst(), k -> new ArrayList<>()).add(new ChestData(index, cPos, lootType.getSecond(), lootTypes.size()));
				index += 1;
			}
		}
		List<ChestContent> result = new ArrayList<>();
		for(Generator.ILootType lootType : chestDataHashMap.keySet()) {
			List<ChestData> chests = chestDataHashMap.get(lootType);
			for(ChestData chestData : chests) {
				CPos chunkChestPos = chestData.getChunkPos();
				rand.setDecoratorSeed(structureSeed, chunkChestPos.getX() * 16, chunkChestPos.getZ() * 16, this.getDecorationSalt(), this.getVersion());
				SpecificCalls calls = this.getSpecificCalls();
				if(calls != null) calls.run(generator, rand);
				if(shouldAdvanceInChunks()) rand.advance(chestData.getNumberInChunk() * 2L);
				rand.advance(chestData.getIndex() * 2L);
				LootContext context = new LootContext(rand.nextLong(), this.getVersion());
				List<ItemStack> loot = indexed ? lootType.getLootTable(this.getVersion()).generateIndexed(context) : lootType.getLootTable(this.getVersion()).generate(context);
				result.add(new ChestContent(lootType, loot, chestData.getPos(), indexed));
			}
		}
		return result;
	}

	/**
	 * This utility will provide the loot for a valid feature position (we don't check it tho)
	 * You don't have to provide anything else than
	 *
	 * @param worldSeed the world seed
	 * @param pos       the position of the structure (must be a valid one)
	 * @param rand      the rand object, used for being fast
	 * @param indexed   if loot should be indexed
	 * @return a list of chestcontent
	 */
	default List<ChestContent> getLootAtPos(long worldSeed, CPos pos, ChunkRand rand, boolean indexed) {
		if(!(this instanceof Feature<?, ?>)) return null;
		Feature<?, ?> feature = (Feature<?, ?>)this;
		Generator.GeneratorFactory<?> factory = Generators.get(feature.getClass());
		if(factory == null) return null;
		Generator generator = factory.create(this.getVersion());
		if(generator == null) return null;
		GenerationContext.Context context = feature.getContext(worldSeed);
		if(context == null) return null;
		if(!generator.generate(context.getGenerator(), pos)) return null;
		return this.getLoot(worldSeed, generator, rand, indexed);
	}

	/**
	 * Old method
	 *
	 * @param structureSeed
	 * @param generator
	 * @param rand
	 * @param indexed
	 * @return
	 */
	@Deprecated
	default HashMap<Generator.ILootType, List<List<ItemStack>>> getLootEx(long structureSeed, Generator generator, ChunkRand rand, boolean indexed) {
		HashMap<Generator.ILootType, List<List<ItemStack>>> res = new HashMap<>();
		for(ChestContent chestContent : this.getLoot(structureSeed, generator, rand, indexed)) {
			res.computeIfAbsent(chestContent.getLootType(), e -> new ArrayList<>())
				.add(chestContent.getItems());
		}
		return res;
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
		private final int index;
		private final CPos chunkPos;
		private final BPos pos;
		private final int numberInChunk;

		public ChestData(int index, CPos chunkPos, BPos pos, int numberInChunk) {
			this.index = index;
			this.chunkPos = chunkPos;
			this.pos = pos;
			this.numberInChunk = numberInChunk;
		}

		public BPos getPos() {
			return pos;
		}

		public CPos getChunkPos() {
			return chunkPos;
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
				", cPos=" + chunkPos +
				", bpos=" + pos +
				", numberInChunk=" + numberInChunk +
				'}';
		}
	}

	@FunctionalInterface
	interface SpecificCalls {
		void run(Generator generator, ChunkRand rand);
	}
}
