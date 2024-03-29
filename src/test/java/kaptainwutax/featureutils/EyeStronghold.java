package kaptainwutax.featureutils;

import kaptainwutax.featureutils.structure.generator.structure.StrongholdGenerator;
import kaptainwutax.mcutils.rand.ChunkRand;
import kaptainwutax.mcutils.version.MCVersion;

import java.util.Arrays;

public class EyeStronghold {
	public static void main(String[] args) {
		StrongholdGenerator g = new StrongholdGenerator(MCVersion.v1_16);

		ChunkRand chunkRand = new ChunkRand().asChunkRandDebugger();
		g.populateStructure(42, -75, 61, chunkRand);
		System.out.println(Arrays.toString(g.getEyes()));
	}
}
