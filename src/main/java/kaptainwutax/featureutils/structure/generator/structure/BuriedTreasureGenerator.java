package kaptainwutax.featureutils.structure.generator.structure;

import kaptainwutax.featureutils.loot.ChestContent;
import kaptainwutax.featureutils.loot.LootTable;
import kaptainwutax.featureutils.loot.MCLootTables;
import kaptainwutax.featureutils.structure.generator.Generator;
import kaptainwutax.mcutils.rand.ChunkRand;
import kaptainwutax.mcutils.util.data.Pair;
import kaptainwutax.mcutils.util.pos.BPos;
import kaptainwutax.mcutils.util.pos.CPos;
import kaptainwutax.mcutils.version.MCVersion;
import kaptainwutax.terrainutils.TerrainGenerator;

import java.util.Collections;
import java.util.List;

public class BuriedTreasureGenerator extends Generator {
	private CPos cPos;

	public BuriedTreasureGenerator(MCVersion version) {
		super(version);
	}

	@Override
	public boolean generate(TerrainGenerator generator, int chunkX, int chunkZ, ChunkRand rand) {
		cPos = new CPos(chunkX, chunkZ);
		return true;
	}

	@Override
	public List<Pair<ILootType, BPos>> getLootPos() {
		return getChestsPos();
	}

	@Override
	public List<Pair<ILootType, BPos>> getChestsPos() {
		return Collections.singletonList(new Pair<>(LootType.BURIED_CHEST, cPos.toBlockPos().add(9, 90, 9)));
	}

	@Override
	public ILootType[] getLootTypes() {
		return LootType.values();
	}

	public enum LootType implements ILootType {
		BURIED_CHEST(MCLootTables.BURIED_TREASURE_CHEST, ChestContent.ChestType.SINGLE_CHEST),
		;

		public final LootTable lootTable;
		public final ChestContent.ChestType chestType;

		LootType(LootTable lootTable, ChestContent.ChestType chestType) {
			this.lootTable = lootTable;
			this.chestType = chestType;
		}

		@Override
		public LootTable getLootTable(MCVersion version) {
			return lootTable.apply(version);
		}

		@Override
		public ChestContent.ChestType getChestType() {
			return chestType;
		}
	}

}
