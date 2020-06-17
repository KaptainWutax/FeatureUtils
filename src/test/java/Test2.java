import kaptainwutax.biomeutils.source.OverworldBiomeSource;
import kaptainwutax.featureutils.structure.Monument;
import kaptainwutax.featureutils.structure.SwampHut;
<<<<<<< HEAD
=======
import kaptainwutax.featureutils.structure.Village;
>>>>>>> parent of f99c90c... Revert "added stronghold sim"
import kaptainwutax.seedutils.mc.ChunkRand;
import kaptainwutax.seedutils.mc.MCVersion;
import kaptainwutax.seedutils.mc.pos.CPos;
import kaptainwutax.seedutils.mc.seed.StructureSeed;
import kaptainwutax.seedutils.util.SeedIterator;
import kaptainwutax.seedutils.util.math.DistanceMetric;

<<<<<<< HEAD
=======
import javax.swing.text.Position;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

>>>>>>> parent of f99c90c... Revert "added stronghold sim"
public class Test2 {

	public static Monument MONUMENT = new Monument(MCVersion.v1_15);
	public static SwampHut SWAMP_HUT = new SwampHut(MCVersion.v1_15);

	public static void main(String[] args) {
		ChunkRand rand = new ChunkRand();

		new SeedIterator(1_000_000_000L, 1L << 48).forEachRemaining(structureSeed -> {
			CPos monument = MONUMENT.getInRegion(structureSeed, 3, 0, rand);
			CPos hut = SWAMP_HUT.getInRegion(structureSeed, 3, 0, rand);

			if(monument.distanceTo(hut, DistanceMetric.CHEBYSHEV) > 15)return;

			StructureSeed.getWorldSeeds(structureSeed).forEachRemaining(worldSeed -> {
				OverworldBiomeSource ow = new OverworldBiomeSource(MCVersion.v1_15, worldSeed).build();
				if(!MONUMENT.canSpawn(monument.getX(), monument.getZ(), ow))return;
				if(!SWAMP_HUT.canSpawn(hut.getX(), hut.getZ(), ow))return;
				System.out.println(worldSeed + ", " + structureSeed);
			});
		});
	}

<<<<<<< HEAD
=======
	public static class Player {

	}

>>>>>>> parent of f99c90c... Revert "added stronghold sim"
}
