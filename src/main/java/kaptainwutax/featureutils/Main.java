package kaptainwutax.featureutils;

import kaptainwutax.featureutils.structure.generator.StrongholdGenerator;
import kaptainwutax.seedutils.mc.ChunkRand;
import kaptainwutax.seedutils.mc.MCVersion;

public class Main {
    public static void main(String[] args) {
        StrongholdGenerator g=new StrongholdGenerator(MCVersion.v1_16);

        ChunkRand chunkRand=new ChunkRand();
        System.out.println(g.populateStructure(42,-75,61,chunkRand));
    }
}
