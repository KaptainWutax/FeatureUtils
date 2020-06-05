import kaptainwutax.biomeutils.Biome;
import kaptainwutax.biomeutils.source.OverworldBiomeSource;
import kaptainwutax.featureutils.structure.Mansion;
import kaptainwutax.seedutils.mc.ChunkRand;
import kaptainwutax.seedutils.mc.MCVersion;
import kaptainwutax.seedutils.mc.pos.CPos;

public class MM {

	public static Mansion MANSION = new Mansion(MCVersion.v1_15);

	public static void main(String[] args) {
		ChunkRand rand = new ChunkRand();

		for(long structureSeed = 4_000_000_000L; structureSeed < Long.MAX_VALUE; structureSeed++) {
			CPos mansion = MANSION.getInRegion(structureSeed, 0, 0, rand);

			for(long upperBits = 0; upperBits < 1L << 12; upperBits++) {
				long worldSeed = (upperBits << 48) | structureSeed;
				OverworldBiomeSource source = new OverworldBiomeSource(MCVersion.v1_15, worldSeed).build();
				if(!MANSION.canSpawn(mansion.getX(), mansion.getZ(), source))continue;

				source.iterateUniqueBiomes(mansion.getX() << 4, mansion.getZ() << 4, 50, biome -> {
					if(biome.getCategory() == Biome.Category.MUSHROOM) {
						System.out.println(worldSeed);
						System.out.println((mansion.getX() << 4) + ", " + (mansion.getZ() << 4));
						return false;
					}

					return true;
				});
			}

			/*
			StructureSeed.getWorldSeeds(structureSeed).forEachRemaining(worldSeed -> {
				OverworldBiomeSource source = new OverworldBiomeSource(MCVersion.v1_15, worldSeed).build();
				if(!MANSION.canSpawn(mansion.getX(), mansion.getZ(), source))return;
				if(source.getBiome((mansion.getX() << 4) + 100, 0, (mansion.getZ() << 4) + 100).getCategory() != Biome.Category.MUSHROOM) {
					return;
				}

				System.out.println(worldSeed);
				System.out.println((mansion.getX() << 4) + ", " + (mansion.getZ() << 4));
			});*/
		}
	}

}
