package kaptainwutax.featureutils.structure.generator.piece.stronghold;

import kaptainwutax.featureutils.structure.Stronghold;
import kaptainwutax.featureutils.structure.generator.StrongholdGenerator;
import kaptainwutax.seedutils.rand.JRand;
import kaptainwutax.mcutils.util.pos.BPos;
import kaptainwutax.mcutils.util.block.BlockBox;
import kaptainwutax.mcutils.util.block.BlockDirection;

import java.util.List;

public class PrisonHall extends Stronghold.Piece {

    public PrisonHall(int pieceId, JRand rand, BlockBox boundingBox, BlockDirection facing) {
        super(pieceId);
        this.setOrientation(facing);
        rand.nextInt(5); //Random entrance.
        this.boundingBox = boundingBox;
    }

    public static PrisonHall createPiece(List<Stronghold.Piece> pieces, JRand rand, int x, int y, int z, BlockDirection facing, int pieceId) {
        BlockBox box = BlockBox.rotated(x, y, z, -1, -1, 0, 9, 5, 11, facing.getRotation());
        return Stronghold.Piece.isHighEnough(box) && Stronghold.Piece.getNextIntersectingPiece(pieces, box) == null ? new PrisonHall(pieceId, rand, box, facing) : null;
    }

    @Override
    public void populatePieces(StrongholdGenerator gen, Start start, List<Stronghold.Piece> pieces, JRand rand) {
        this.generateSmallDoorChildrenForward(gen, start, pieces, rand, 1, 1);
    }

    public boolean process(JRand rand, BPos pos) {
        skipWithRandomized(rand, 0, 0, 0, 8, 4, 10, true);
        // door not random
        // 1 not random
        skipWithRandomized(rand, 4, 1, 1, 4, 3, 1, false);
        skipWithRandomized(rand, 4, 1, 3, 4, 3, 3, false);
        skipWithRandomized(rand, 4, 1, 7, 4, 3, 7, false);
        skipWithRandomized(rand, 4, 1, 9, 4, 3, 9, false);
        // 4-1 *6 not random
        // 2 not random
        // 4 not random
        return true;
    }
}
