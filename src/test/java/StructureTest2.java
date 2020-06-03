import kaptainwutax.biomeutils.source.OverworldBiomeSource;
import kaptainwutax.featureutils.structure.Mansion;
import kaptainwutax.featureutils.structure.Monument;
import kaptainwutax.featureutils.structure.RegionStructure;
import kaptainwutax.seedutils.mc.ChunkRand;
import kaptainwutax.seedutils.mc.MCVersion;
import kaptainwutax.seedutils.mc.pos.CPos;

public class StructureTest2 {

	public static Mansion MANSION = new Mansion(MCVersion.v1_15);
	public static Monument MONUMENT = new Monument(MCVersion.v1_15);

	public static void main(String[] args) {
		ChunkRand rand = new ChunkRand();

		for(long structureSeed = 0; structureSeed < 1L << 48; structureSeed++) {
			CPos mansion = MANSION.getInRegion(structureSeed, 0, 0, rand);
			CPos monument = MONUMENT.getInRegion(structureSeed, 0, 0, rand);

			for(long upperBits = 0; upperBits < 1L << 16; upperBits++) {
				long worldSeed = (upperBits << 48) | structureSeed;

				OverworldBiomeSource ow = new OverworldBiomeSource(MCVersion.v1_15, worldSeed).build();
				if(!MANSION.canSpawn(mansion.getX(), mansion.getZ(), ow))continue;
				if(!MONUMENT.canSpawn(monument.getX(), monument.getZ(), ow))continue;

				System.out.println("Found world seed " + worldSeed + " with structure seed " + structureSeed);
				System.out.format("Mansion at (%d, %d)\n", mansion.getX() * 16, mansion.getZ() * 16);
				System.out.format("Monument at (%d, %d)\n", monument.getX() * 16, monument.getZ() * 16);
			}
		}
	}

}
