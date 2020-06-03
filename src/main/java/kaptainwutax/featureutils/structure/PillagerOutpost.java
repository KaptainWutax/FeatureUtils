package kaptainwutax.featureutils.structure;

import kaptainwutax.biomeutils.Biome;
import kaptainwutax.seedutils.mc.ChunkRand;
import kaptainwutax.seedutils.mc.MCVersion;
import kaptainwutax.seedutils.mc.VersionMap;
import kaptainwutax.seedutils.mc.pos.CPos;
import kaptainwutax.seedutils.util.math.DistanceMetric;

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

	@Override
	public boolean isValidBiome(Biome biome) {
		return biome == Biome.PLAINS || biome == Biome.DESERT || biome == Biome.SAVANNA
				|| biome == Biome.TAIGA || biome == Biome.SNOWY_TAIGA;
	}

	public boolean hasNearbyVillage(long structureSeed, int chunkX, int chunkZ, ChunkRand rand) {
		Village village = new Village(this.getVersion());
		CPos chunkPos = new CPos(chunkX, chunkZ);

		Data<?> nn = village.at(chunkX - 10, chunkZ - 10);
		Data<?> pp = village.at(chunkX + 10, chunkZ + 10);

		if(village.getInRegion(structureSeed, nn.regionX, nn.regionZ, rand).distanceTo(chunkPos, DistanceMetric.CHEBYSHEV) <= 10) {
			return true;
		}

		if(nn.regionX == pp.regionX && nn.regionZ == pp.regionZ) {
			//The area is contained within one region.
			return false;
		} else if(nn.regionX != pp.regionX && nn.regionZ != pp.regionZ) {
			//The area intersects 4 regions.
			if(village.getInRegion(structureSeed, pp.regionX, pp.regionZ, rand).distanceTo(chunkPos, DistanceMetric.CHEBYSHEV) <= 10) {
				return true;
			}

			Data<?> np = village.at(chunkX - 10, chunkZ + 10);

			if(village.getInRegion(structureSeed, np.regionX, np.regionZ, rand).distanceTo(chunkPos, DistanceMetric.CHEBYSHEV) <= 10) {
				return true;
			}

			Data<?> pn = village.at(chunkX + 10, chunkZ - 10);
			return village.getInRegion(structureSeed, pn.regionX, pn.regionZ, rand).distanceTo(chunkPos, DistanceMetric.CHEBYSHEV) <= 10;
		}

		//The area intersects 2 regions.
		return village.getInRegion(structureSeed, pp.regionX, pp.regionZ, rand).distanceTo(chunkPos, DistanceMetric.CHEBYSHEV) <= 10;
	}

	/* More user friendly code (also about 150 times slower than the optimized version above)
	public boolean hasNearbyVillage2(long structureSeed, int chunkX, int chunkZ, ChunkRand rand) {
		for(int z = chunkZ - 10; z <= chunkZ + 10; ++z) {
			for(int x = chunkX - 10; x <= chunkX + 10; ++x) {
				if(new Village(this.getVersion()).at(x, z).testStart(structureSeed, rand)) {
					return true;
				}
			}
		}

		return false;
	}*/

}
