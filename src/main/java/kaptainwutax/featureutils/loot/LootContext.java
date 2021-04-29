package kaptainwutax.featureutils.loot;


import kaptainwutax.mcutils.rand.ChunkRand;
import kaptainwutax.mcutils.version.MCVersion;

public class LootContext extends ChunkRand {
	private final MCVersion version;
	private int luck = 1;

	public LootContext(long lootTableSeed) {
		super(lootTableSeed);
		this.version = MCVersion.v1_16;
	}

	public LootContext(long lootTableSeed, MCVersion version) {
		super(lootTableSeed);
		this.version = version;
	}

	public int getLuck() {
		return luck;
	}

	public LootContext withLuck(int luck) {
		this.luck = luck;
		return this;
	}

	public MCVersion getVersion() {
		return version;
	}
}
