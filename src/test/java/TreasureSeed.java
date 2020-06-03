import kaptainwutax.biomeutils.source.OverworldBiomeSource;
import kaptainwutax.featureutils.structure.BuriedTreasure;
import kaptainwutax.featureutils.structure.RegionStructure;
import kaptainwutax.featureutils.structure.Village;
import kaptainwutax.seedutils.mc.ChunkRand;
import kaptainwutax.seedutils.mc.MCVersion;
import kaptainwutax.seedutils.mc.seed.StructureSeed;

public class TreasureSeed {

	public static final Village VILLAGE = new Village(MCVersion.v1_15);
	public static final BuriedTreasure BURIED_TREASURE = new BuriedTreasure(MCVersion.v1_15);

	public static void main(String[] args) {
		ChunkRand rand = new ChunkRand();

		RegionStructure.Data<?>[] data = new RegionStructure.Data<?>[] {
				BURIED_TREASURE.at(0, 0),
				BURIED_TREASURE.at(1, 0),
				VILLAGE.at(0, 0)
		};

		StructureSeed.iterator().forEachRemaining(structureSeed -> {
			for(RegionStructure.Data<?> datum: data) {
				if(!datum.testStart(structureSeed, rand))return;
			}

			System.out.println("Found structure seed " + structureSeed);

			StructureSeed.getWorldSeeds(structureSeed).forEachRemaining(worldSeed -> {
				OverworldBiomeSource source = new OverworldBiomeSource(MCVersion.v1_15, worldSeed).build();

				for(RegionStructure.Data<?> datum: data) {
					if(!datum.testBiome(source))return;
				}

				System.out.println("Found world seed " + worldSeed);
			});
		});
	}

}
