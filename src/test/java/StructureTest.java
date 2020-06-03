import kaptainwutax.biomeutils.source.BiomeSource;
import kaptainwutax.biomeutils.source.OverworldBiomeSource;
import kaptainwutax.featureutils.structure.*;
import kaptainwutax.seedutils.mc.ChunkRand;
import kaptainwutax.seedutils.mc.MCVersion;
import kaptainwutax.seedutils.mc.pos.CPos;

import java.util.ArrayList;
import java.util.List;

public class StructureTest {

	public static final Village VILLAGE = new Village(MCVersion.v1_15);
	public static final SwampHut SWAMP_HUT = new SwampHut(MCVersion.v1_15);
	public static final DesertPyramid DESERT_PYRAMID = new DesertPyramid(MCVersion.v1_15);
	public static final JunglePyramid JUNGLE_PYRAMID = new JunglePyramid(MCVersion.v1_15);
	public static final PillagerOutpost OUTPOST = new PillagerOutpost(MCVersion.v1_15);
	public static final Igloo IGLOO = new Igloo(MCVersion.v1_15);

	public static void main(String[] args) {
		ChunkRand rand = new ChunkRand();

		List<CPos> villages = new ArrayList<>();
		List<CPos> huts = new ArrayList<>();
		List<CPos> desertPyramids = new ArrayList<>();
		List<CPos> junglePyramids = new ArrayList<>();
		List<CPos> outposts = new ArrayList<>();
		List<CPos> igloos = new ArrayList<>();

		for(long structureSeed = 0; structureSeed < 1L << 48; structureSeed++) {
			//Most structure regions are 32x32 chunks big.
			//3x3 of regions represents a 1536x1536 block area.
			for(int regionX = -1; regionX <= 1; regionX++) {
				for(int regionZ = -1; regionZ < 1; regionZ++) {
					villages.add(VILLAGE.getInRegion(structureSeed, regionX, regionZ, rand));
					huts.add(SWAMP_HUT.getInRegion(structureSeed, regionX, regionZ, rand));
					desertPyramids.add(DESERT_PYRAMID.getInRegion(structureSeed, regionX, regionZ, rand));
					junglePyramids.add(JUNGLE_PYRAMID.getInRegion(structureSeed, regionX, regionZ, rand));
					outposts.add(OUTPOST.getInRegion(structureSeed, regionX, regionZ, rand));
					igloos.add(IGLOO.getInRegion(structureSeed, regionX, regionZ, rand));
				}
			}

			//Bruteforce across 2^12 upper bits instead of 2^16, there's no need to get all the possible seeds...
			for(long upperBits = 0; upperBits < 1L << 12; upperBits++) {
				long worldSeed = (upperBits << 48) | structureSeed;

				OverworldBiomeSource source = new OverworldBiomeSource(MCVersion.v1_15, worldSeed).build();

				CPos village, hut, desert, jungle, outpost, igloo;

				if((village = getValidStart(VILLAGE, villages, source)) == null)continue;
				if((hut = getValidStart(SWAMP_HUT, huts, source)) == null)continue;
				if((desert = getValidStart(DESERT_PYRAMID, desertPyramids, source)) == null)continue;
				if((jungle = getValidStart(JUNGLE_PYRAMID, junglePyramids, source)) == null)continue;
				if((outpost = getValidStart(OUTPOST, outposts, source)) == null)continue;
				if((igloo = getValidStart(IGLOO, igloos, source)) == null)continue;

				System.out.println("Found world seed " + worldSeed + " with structure seed " + structureSeed + " ==========");
				System.out.println("Village is at (" + village.getX() * 16 + ", " + village.getZ() * 16 + ")");
				System.out.println("Swamp Hut is at (" + hut.getX() * 16 + ", " + hut.getZ() * 16 + ")");
				System.out.println("Desert Pyramid is at (" + desert.getX() * 16 + ", " + desert.getZ() * 16 + ")");
				System.out.println("Jungle Pyramid is at (" + jungle.getX() * 16 + ", " + jungle.getZ() * 16 + ")");
				System.out.println("Outpost is at (" + outpost.getX() * 16 + ", " + outpost.getZ() * 16 + ")");
				System.out.println("Igloo is at (" + igloo.getX() * 16 + ", " + igloo.getZ() * 16 + ")");
			}

			villages.clear();
			huts.clear();
			desertPyramids.clear();
			junglePyramids.clear();
			outposts.clear();
			igloos.clear();
		}
	}

	public static CPos getValidStart(Structure<?, ?> structure, List<CPos> positions, BiomeSource source) {
		for(CPos position: positions) {
			if(position == null)continue;

			if(structure.canSpawn(position.getX(), position.getZ(), source)) {
				return position;
			}
		}

		return null;
	}

}
