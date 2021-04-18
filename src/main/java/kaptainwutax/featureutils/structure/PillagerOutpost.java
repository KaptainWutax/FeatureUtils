package kaptainwutax.featureutils.structure;

import kaptainwutax.biomeutils.Biome;
import kaptainwutax.biomeutils.source.BiomeSource;
import kaptainwutax.mcutils.rand.ChunkRand;
import kaptainwutax.mcutils.state.Dimension;
import kaptainwutax.mcutils.util.math.DistanceMetric;
import kaptainwutax.mcutils.util.pos.CPos;
import kaptainwutax.mcutils.version.MCVersion;
import kaptainwutax.mcutils.version.VersionMap;

import java.util.ArrayList;
import java.util.List;

public class PillagerOutpost extends OldStructure<PillagerOutpost> {

	public static final VersionMap<OldStructure.Config> CONFIGS = new VersionMap<OldStructure.Config>()
			.add(MCVersion.v1_14, new OldStructure.Config(165745296));

	private final Village village;

	public PillagerOutpost(MCVersion version) {
		this(version, new Village(version));
	}

	public PillagerOutpost(MCVersion version, Village village) {
		this(CONFIGS.getAsOf(version), version, village);
	}

	public PillagerOutpost(RegionStructure.Config config, MCVersion version, Village village) {
		super(config, version);
		this.village = village;
	}

	public static String name() {
		return "pillager_outpost";
	}

	public Village getVillage() {
		return this.village;
	}

	@Override
	public boolean canStart(Data<PillagerOutpost> data, long structureSeed, ChunkRand rand) {
		if (!super.canStart(data, structureSeed, rand)) return false;
		rand.setWeakSeed(structureSeed, data.chunkX, data.chunkZ, this.getVersion());
		rand.nextInt(); //Why? No one knows...
		if (rand.nextInt(5) != 0) return false;

		//In older versions, the nearby village checks also
		// ran biome check so we have to ignore it here.
		if (this.getVersion().isOlderThan(MCVersion.v1_16)) {
			return true;
		}

		return !this.hasNearbyVillage(structureSeed, data.chunkX, data.chunkZ, rand);
	}

	@Override
	public CPos getInRegion(long structureSeed, int regionX, int regionZ, ChunkRand rand) {
		CPos chunkPos = super.getInRegion(structureSeed, regionX, regionZ, rand);
		rand.setWeakSeed(structureSeed, chunkPos.getX(), chunkPos.getZ(), this.getVersion());
		rand.nextInt();
		if (rand.nextInt(5) != 0) return null;

		//In older versions, the nearby village checks also
		// ran biome check so we have to ignore it here.
		if (this.getVersion().isOlderThan(MCVersion.v1_16)) {
			return chunkPos;
		}

		return this.hasNearbyVillage(structureSeed, chunkPos.getX(), chunkPos.getZ(), rand) ? null : chunkPos;
	}

	@Override
	public boolean canSpawn(int chunkX, int chunkZ, BiomeSource source) {
		if (!super.canSpawn(chunkX, chunkZ, source)) return false;

		//Let's do the biome checks here.
		if (this.getVersion().isOlderThan(MCVersion.v1_16)) {
			for (CPos nearbyVillage : this.getNearbyVillages(source.getWorldSeed(), chunkX, chunkZ, new ChunkRand())) {
				if (this.village.canSpawn(nearbyVillage.getX(), nearbyVillage.getZ(), source)) return false;
			}
		}

		return true;
	}

	@Override
	public boolean isValidDimension(Dimension dimension) {
		return dimension == Dimension.OVERWORLD;
	}

	@Override
	public boolean isValidBiome(Biome biome) {
		return biome == Biome.PLAINS || biome == Biome.DESERT || biome == Biome.SAVANNA
				|| biome == Biome.TAIGA || biome == Biome.SNOWY_TUNDRA;
	}

	public boolean hasNearbyVillage(long structureSeed, int chunkX, int chunkZ, ChunkRand rand) {
		CPos outpostPos = new CPos(chunkX, chunkZ);

		Village.Data<?> nw = this.village.at(chunkX - 10, chunkZ - 10);
		Village.Data<?> se = this.village.at(chunkX + 10, chunkZ + 10);

		CPos villagePos = this.village.getInRegion(structureSeed, nw.regionX, nw.regionZ, rand);

		if (villagePos != null && villagePos.distanceTo(outpostPos, DistanceMetric.CHEBYSHEV) <= 10) {
			return true;
		}

		//The area is contained within one region.
		if (nw.regionX == se.regionX && nw.regionZ == se.regionZ) {
			return false;
		}

		//The area intersects 4 regions.
		if (nw.regionX != se.regionX && nw.regionZ != se.regionZ) {
			villagePos = this.village.getInRegion(structureSeed, se.regionX, se.regionZ, rand);

			if (villagePos != null && villagePos.distanceTo(outpostPos, DistanceMetric.CHEBYSHEV) <= 10) {
				return true;
			}

			Data<?> sw = this.village.at(chunkX - 10, chunkZ + 10);
			villagePos = this.village.getInRegion(structureSeed, sw.regionX, sw.regionZ, rand);

			if (villagePos != null && villagePos.distanceTo(outpostPos, DistanceMetric.CHEBYSHEV) <= 10) {
				return true;
			}

			Data<?> ne = this.village.at(chunkX + 10, chunkZ - 10);
			villagePos = this.village.getInRegion(structureSeed, ne.regionX, ne.regionZ, rand);
			return villagePos != null && villagePos.distanceTo(outpostPos, DistanceMetric.CHEBYSHEV) <= 10;
		}

		//The area intersects 2 regions.
		villagePos = this.village.getInRegion(structureSeed, se.regionX, se.regionZ, rand);
		return villagePos != null && villagePos.distanceTo(outpostPos, DistanceMetric.CHEBYSHEV) <= 10;
	}

	public List<CPos> getNearbyVillages(long structureSeed, int chunkX, int chunkZ, ChunkRand rand) {
		List<CPos> villages = new ArrayList<>();
		CPos outpostPos = new CPos(chunkX, chunkZ);

		Village.Data<?> nw = this.village.at(chunkX - 10, chunkZ - 10);
		Village.Data<?> se = this.village.at(chunkX + 10, chunkZ + 10);

		CPos villagePos = this.village.getInRegion(structureSeed, nw.regionX, nw.regionZ, rand);

		if (villagePos != null && villagePos.distanceTo(outpostPos, DistanceMetric.CHEBYSHEV) <= 10) {
			villages.add(villagePos);
		}

		//The area is contained within one region.
		if (nw.regionX == se.regionX && nw.regionZ == se.regionZ) {
			return villages;
		}

		//The area intersects 4 regions.
		if (nw.regionX != se.regionX && nw.regionZ != se.regionZ) {
			villagePos = this.village.getInRegion(structureSeed, se.regionX, se.regionZ, rand);

			if (villagePos != null && villagePos.distanceTo(outpostPos, DistanceMetric.CHEBYSHEV) <= 10) {
				villages.add(villagePos);
			}

			Data<?> sw = this.village.at(chunkX - 10, chunkZ + 10);
			villagePos = this.village.getInRegion(structureSeed, sw.regionX, sw.regionZ, rand);

			if (villagePos != null && villagePos.distanceTo(outpostPos, DistanceMetric.CHEBYSHEV) <= 10) {
				villages.add(villagePos);
			}

			Data<?> ne = this.village.at(chunkX + 10, chunkZ - 10);
			villagePos = this.village.getInRegion(structureSeed, ne.regionX, ne.regionZ, rand);

			if (villagePos != null && villagePos.distanceTo(outpostPos, DistanceMetric.CHEBYSHEV) <= 10) {
				villages.add(villagePos);
			}

			return villages;
		}

		//The area intersects 2 regions.
		villagePos = this.village.getInRegion(structureSeed, se.regionX, se.regionZ, rand);

		if (villagePos != null && villagePos.distanceTo(outpostPos, DistanceMetric.CHEBYSHEV) <= 10) {
			villages.add(villagePos);
		}

		return villages;
	}

	//More user friendly code (also about 150 times slower than the optimized version above)
	public boolean hasNearbyVillageNaive(long structureSeed, int chunkX, int chunkZ, ChunkRand rand) {
		for (int z = chunkZ - 10; z <= chunkZ + 10; ++z) {
			for (int x = chunkX - 10; x <= chunkX + 10; ++x) {
				if (this.village.at(x, z).testStart(structureSeed, rand)) {
					return true;
				}
			}
		}

		return false;
	}

}
