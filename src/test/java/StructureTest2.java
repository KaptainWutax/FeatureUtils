import kaptainwutax.biomeutils.source.OverworldBiomeSource;
import kaptainwutax.featureutils.structure.Mansion;
import kaptainwutax.featureutils.structure.RegionStructure;
import kaptainwutax.seedutils.mc.ChunkRand;
import kaptainwutax.seedutils.mc.MCVersion;

public class StructureTest2 {

	public static Mansion MANSION = new Mansion(MCVersion.v1_15);

	public static void main(String[] args) {
		ChunkRand rand = new ChunkRand();
		RegionStructure.Data<?> data = MANSION.at(0, 0);

		for(long structureSeed = 0; structureSeed < 1L << 48; structureSeed++) {
			if(!data.testStart(structureSeed, rand))continue;
			System.out.println("Found structure seed " + structureSeed);

			for(long upperBits = 0; upperBits < 1L << 16; upperBits++) {
				long worldSeed = (upperBits << 48) | structureSeed;

				OverworldBiomeSource ow = new OverworldBiomeSource(MCVersion.v1_15, worldSeed).build();
				if(!data.testBiome(ow))continue;
				System.out.println("Found world seed " + worldSeed);
			}
		}
	}

}
