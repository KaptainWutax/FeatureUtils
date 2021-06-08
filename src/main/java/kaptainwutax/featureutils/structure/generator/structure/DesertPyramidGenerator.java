package kaptainwutax.featureutils.structure.generator.structure;

import kaptainwutax.featureutils.loot.ChestContent;
import kaptainwutax.featureutils.loot.LootTable;
import kaptainwutax.featureutils.loot.MCLootTables;
import kaptainwutax.featureutils.structure.generator.Generator;
import kaptainwutax.mcutils.rand.ChunkRand;
import kaptainwutax.mcutils.util.block.BlockBox;
import kaptainwutax.mcutils.util.data.Pair;
import kaptainwutax.mcutils.util.pos.BPos;
import kaptainwutax.mcutils.util.pos.CPos;
import kaptainwutax.mcutils.version.MCVersion;
import kaptainwutax.terrainutils.TerrainGenerator;

import java.util.ArrayList;
import java.util.List;

public class DesertPyramidGenerator extends Generator {
	private BlockBox piece;
	private CPos temp;

	public DesertPyramidGenerator(MCVersion version) {
		super(version);
	}

	@Override
	public boolean generate(TerrainGenerator generator, int chunkX, int chunkZ, ChunkRand rand) {
		// fixme actually compute the BPos
		temp = new CPos(chunkX, chunkZ);
		piece = new BlockBox(temp, temp.add(1, 1));
		return true;
	}


	@Override
	public List<Pair<ILootType, BPos>> getLootPos() {
		return getChestsPos();
	}

	@Override
	public List<Pair<ILootType, BPos>> getChestsPos() {
		//piece.getInside(...,...);
		List<Pair<ILootType, BPos>> res = new ArrayList<>();
		if (temp == null) return res;
		res.add(new Pair<>(LootType.CHEST_1, temp.toBlockPos()));
		res.add(new Pair<>(LootType.CHEST_2, temp.toBlockPos()));
		res.add(new Pair<>(LootType.CHEST_3, temp.toBlockPos()));
		res.add(new Pair<>(LootType.CHEST_4, temp.toBlockPos()));
		return res;
	}

	@Override
	public ILootType[] getLootTypes() {
		return LootType.values();
	}


	public enum LootType implements ILootType {
		CHEST_1(MCLootTables.DESERT_PYRAMID_CHEST, ChestContent.ChestType.SINGLE_CHEST),
		CHEST_2(MCLootTables.DESERT_PYRAMID_CHEST, ChestContent.ChestType.SINGLE_CHEST),
		CHEST_3(MCLootTables.DESERT_PYRAMID_CHEST, ChestContent.ChestType.SINGLE_CHEST),
		CHEST_4(MCLootTables.DESERT_PYRAMID_CHEST, ChestContent.ChestType.SINGLE_CHEST),
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
