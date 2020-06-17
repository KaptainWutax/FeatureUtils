import kaptainwutax.biomeutils.Biome;
import kaptainwutax.biomeutils.source.OverworldBiomeSource;
import kaptainwutax.featureutils.structure.RegionStructure;
import kaptainwutax.featureutils.structure.Village;
import kaptainwutax.seedutils.mc.ChunkRand;
import kaptainwutax.seedutils.mc.MCVersion;
import kaptainwutax.seedutils.mc.pos.CPos;
import kaptainwutax.seedutils.mc.seed.StructureSeed;

public class Test {

	public static Village VILLAGE = new Village(MCVersion.v1_15);

	public static void main(String[] args) {
		ChunkRand rand = new ChunkRand();

		RegionStructure.Data<?> v = VILLAGE.at(0, 0);

		StructureSeed.iterator().forEachRemaining(structureSeed -> {
			if(!v.testStart(structureSeed, rand))return;

			StructureSeed.getWorldSeeds(structureSeed).forEachRemaining(worldSeed -> {
				OverworldBiomeSource ow = new OverworldBiomeSource(MCVersion.v1_15, worldSeed).build();
				if(!v.testBiome(ow))return;
				System.out.format("%d at (%d, %d) \n", worldSeed, v.chunkX << 4, v.chunkZ << 4);
			});
		});
	}

}
