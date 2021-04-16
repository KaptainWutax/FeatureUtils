package kaptainwutax.featureutils.structure.generator.piece.stronghold;

import kaptainwutax.seedutils.lcg.rand.JRand;

public class Start extends SpiralStaircase {

    public PieceWeight pieceWeight;
    public PortalRoom portalRoom;

    public Start(JRand rand, int x, int z) {
        super(0, rand, x, z);
    }

}
