import kaptainwutax.biomeutils.source.OverworldBiomeSource;
import kaptainwutax.featureutils.structure.Monument;
import kaptainwutax.featureutils.structure.SwampHut;
import kaptainwutax.seedutils.mc.ChunkRand;
import kaptainwutax.seedutils.mc.MCVersion;
import kaptainwutax.seedutils.mc.pos.CPos;
import kaptainwutax.seedutils.util.math.DistanceMetric;

import java.util.ArrayList;
import java.util.List;

public class GodSeeds {

	private static Monument MONUMENT = new Monument(MCVersion.v1_15);
	private static SwampHut HUT = new SwampHut(MCVersion.v1_15);

	public static void main(String[] args) {
		ChunkRand rand = new ChunkRand();
		List<CPos> monuments = new ArrayList<>();

		CPos[][] huts = new CPos[4][4];
		CPos[] goodHuts = new CPos[2];

		long start = System.nanoTime();
		long count = 0;

		for(long structureSeed = 0; structureSeed < 1L << 26; structureSeed++) {
			for(int regionX = -2; regionX < 2; regionX++) {
				for(int regionZ = -2; regionZ < 2; regionZ++) {
					huts[regionX + 2][regionZ + 2] = HUT.getInRegion(structureSeed, regionX, regionZ, rand);

					if(regionX > -2) {
						if(huts[regionX + 2][regionZ + 2].distanceTo(huts[regionX + 1][regionZ + 2], DistanceMetric.MANHATTAN) < 15) {
							goodHuts[0] = huts[regionX + 2][regionZ + 2];
							goodHuts[1] = huts[regionX + 1][regionZ + 2];
							break;
						}
					}

					if(regionZ > -2) {
						if(huts[regionX + 2][regionZ + 2].distanceTo(huts[regionX + 2][regionZ + 1], DistanceMetric.MANHATTAN) < 15) {
							goodHuts[0] = huts[regionX + 2][regionZ + 2];
							goodHuts[1] = huts[regionX + 2][regionZ + 1];
							break;
						}
					}

					//monuments.add(MONUMENT.getInRegion(structureSeed, regionX, regionZ, rand));
				}

				if(goodHuts[0] != null)break;
			}

			if(goodHuts[0] == null)continue;

			for(int regionX = -2; regionX < 2; regionX++) {
				for(int regionZ = -2; regionZ < 2; regionZ++) {
					monuments.add(MONUMENT.getInRegion(structureSeed, regionX, regionZ, rand));
				}
			}

			/*for(long upperBits = 0; upperBits < 1L << 16; upperBits++) {
				OverworldBiomeSource source = new OverworldBiomeSource(MCVersion.v1_15, structureSeed | (upperBits << 48)).build();

				if(!HUT.canSpawn(goodHuts[0].getX(), goodHuts[0].getZ(), source))continue;
				if(!HUT.canSpawn(goodHuts[1].getX(), goodHuts[1].getZ(), source))continue;

				boolean canMonumentSpawn = false;

				for(CPos monument: monuments) {
					if(MONUMENT.canSpawn(monument.getX(), monument.getZ(), source)) {
						canMonumentSpawn = true;
						break;
					}
				}

				if(!canMonumentSpawn)continue;
				System.out.println(source.getWorldSeed());
			}*/

			count++;
			goodHuts[0] = null;
			monuments.clear();
		}

		System.out.println((System.nanoTime() - start) / 1000);
		System.out.println((double)count / (1L << 26));
	}

}
