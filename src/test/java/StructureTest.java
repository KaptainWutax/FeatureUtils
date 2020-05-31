import kaptainwutax.featureutils.structure.BuriedTreasure;
import kaptainwutax.featureutils.structure.DesertPyramid;
import kaptainwutax.featureutils.structure.RegionStructure;
import kaptainwutax.featureutils.structure.Village;
import kaptainwutax.seedutils.mc.ChunkRand;
import kaptainwutax.seedutils.mc.MCVersion;

public class StructureTest {

	public static void main(String[] args) {
		ChunkRand rand = new ChunkRand();
		long worldSeed = 440219831988289667L;

		BuriedTreasure.Data treasure = new BuriedTreasure(MCVersion.v1_15).at(265 >> 4, 233 >> 4);
		RegionStructure.Data<?> desertPyramid = new DesertPyramid(MCVersion.v1_15).at(184 >> 4, 680 >> 4);
		RegionStructure.Data<?> village = new Village(MCVersion.v1_15).at(-268 >> 4, 564 >> 4);

		System.out.println(treasure.test(worldSeed, rand));
		System.out.println(desertPyramid.test(worldSeed, rand));
		System.out.println(village.test(worldSeed, rand));
	}

}
