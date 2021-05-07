package kaptainwutax.featureutils.structure.generator;

import kaptainwutax.featureutils.loot.LootTable;
import kaptainwutax.featureutils.loot.MCLootTables;
import kaptainwutax.mcutils.rand.ChunkRand;
import kaptainwutax.mcutils.util.data.Pair;
import kaptainwutax.mcutils.util.pos.BPos;
import kaptainwutax.mcutils.util.pos.CPos;
import kaptainwutax.mcutils.version.MCVersion;
import kaptainwutax.terrainutils.ChunkGenerator;

import java.util.Collections;
import java.util.List;

public class RuinedPortalGenerator extends Generator {
	private CPos cPos;

	public RuinedPortalGenerator(MCVersion version) {
		super(version);
	}

	@Override
	public boolean generate(ChunkGenerator generator, int chunkX, int chunkZ, ChunkRand rand) {
		cPos = new CPos(chunkX, chunkZ);
		return true;
	}

	@Override
	public List<Pair<ILootType, BPos>> getChestsPos() {
		return Collections.singletonList(new Pair<>(LootType.RUINED_TREASURE, cPos.toBlockPos().add(9, 90, 9)));
	}

	@Override
	public ILootType[] getLootTypes() {
		return LootType.values();
	}

	public enum LootType implements ILootType {
		RUINED_TREASURE(MCLootTables.RUINED_PORTAL_CHEST),
		;

		public final LootTable lootTable;

		LootType(LootTable lootTable) {
			this.lootTable = lootTable;
		}

		public LootTable getLootTable() {
			return lootTable;
		}
	}

}
