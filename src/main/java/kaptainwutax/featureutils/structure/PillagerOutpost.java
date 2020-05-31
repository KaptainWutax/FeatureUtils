package kaptainwutax.featureutils.structure;

import kaptainwutax.seedutils.mc.ChunkRand;
import kaptainwutax.seedutils.mc.MCVersion;
import kaptainwutax.seedutils.mc.VersionMap;

public class PillagerOutpost extends OldStructure {

	public static final VersionMap<Config> CONFIGS = new VersionMap<OldStructure.Config>()
			.add(MCVersion.v1_14, new OldStructure.Config(165745296));

	public PillagerOutpost(MCVersion version) {
		super(CONFIGS.getAsOf(version), version);
	}

	public PillagerOutpost(RegionStructure.Config config) {
		super(config, null);
	}

	@Override
	public boolean test(Data<?> data, long structureSeed, ChunkRand rand) {
		if(!super.test(data, structureSeed, rand))return false;
		rand.setWeakSeed(structureSeed, data.chunkX, data.chunkZ, this.getVersion());
		rand.nextInt(); //Why? No one knows...
		if(rand.nextInt(5) != 0)return false;

		//TODO: Optimize this check (A LOT!).
		for(int z = data.chunkZ - 10; z <= data.chunkZ + 10; ++z) {
			for(int x = data.chunkX - 10; x <= data.chunkX + 10; ++x) {
				if(new Village(this.getVersion()).at(x, z).test(structureSeed, rand)) {
					return false;
				}
			}
		}

		return true;
	}

}
