package kaptainwutax.featureutils.structure;

import kaptainwutax.biomeutils.Biome;
import kaptainwutax.seedutils.mc.ChunkRand;
import kaptainwutax.seedutils.mc.MCVersion;
import kaptainwutax.seedutils.mc.VersionMap;
import kaptainwutax.seedutils.mc.pos.CPos;

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
	public boolean canStart(Data<?> data, long structureSeed, ChunkRand rand) {
		if(!super.canStart(data, structureSeed, rand))return false;
		rand.setWeakSeed(structureSeed, data.chunkX, data.chunkZ, this.getVersion());
		rand.nextInt(); //Why? No one knows...
		if(rand.nextInt(5) != 0)return false;
		return !this.hasNearbyVillage(structureSeed, data.chunkX, data.chunkZ, rand);
	}

	@Override
	public CPos getInRegion(long structureSeed, int regionX, int regionZ, ChunkRand rand) {
		CPos chunkPos = super.getInRegion(structureSeed, regionX, regionZ, rand);
		rand.setWeakSeed(structureSeed, chunkPos.getX(), chunkPos.getZ(), this.getVersion());
		rand.nextInt();
		if(rand.nextInt(5) != 0)return null;
		return this.hasNearbyVillage(structureSeed, chunkPos.getX(), chunkPos.getZ(), rand) ? null : chunkPos;
	}

	//TODO: Optimize this check (A LOT!).
	public boolean hasNearbyVillage(long structureSeed, int chunkX, int chunkZ, ChunkRand rand) {
		for(int z = chunkZ - 10; z <= chunkZ + 10; ++z) {
			for(int x = chunkX - 10; x <= chunkX + 10; ++x) {
				if(new Village(this.getVersion()).at(x, z).testStart(structureSeed, rand)) {
					return true;
				}
			}
		}

		return false;
	}

	@Override
	public boolean isValidBiome(Biome biome) {
		return biome == Biome.PLAINS || biome == Biome.DESERT || biome == Biome.SAVANNA
				|| biome == Biome.TAIGA || biome == Biome.SNOWY_TAIGA;
	}

}
