package kaptainwutax.featureutils.structure.generator.piece.stronghold;

import kaptainwutax.featureutils.structure.Stronghold;
import kaptainwutax.featureutils.structure.generator.StrongholdGenerator;
import kaptainwutax.seedutils.rand.JRand;
import kaptainwutax.mcutils.util.pos.BPos;
import kaptainwutax.mcutils.util.block.BlockBox;
import kaptainwutax.mcutils.util.block.BlockDirection;

import java.util.List;

public class ChestCorridor extends Stronghold.Piece {

    public ChestCorridor(int pieceId, JRand rand, BlockBox boundingBox, BlockDirection facing) {
        super(pieceId);
        this.setOrientation(facing);
        rand.nextInt(5); //Random entrance.
        this.boundingBox = boundingBox;
    }

    public static ChestCorridor createPiece(List<Stronghold.Piece> pieces, JRand rand, int x, int y, int z, BlockDirection facing, int pieceId) {
        BlockBox box = BlockBox.rotated(x, y, z, -1, -1, 0, 5, 5, 7, facing.getRotation());
        return Stronghold.Piece.isHighEnough(box) && Stronghold.Piece.getNextIntersectingPiece(pieces, box) == null ? new ChestCorridor(pieceId, rand, box, facing) : null;
    }

    @Override
    public void populatePieces(StrongholdGenerator gen, Start start, List<Stronghold.Piece> pieces, JRand rand) {
        this.generateSmallDoorChildrenForward(gen, start, pieces, rand, 1, 1);
    }

    public boolean process(JRand rand, BPos pos) {
        skipWithRandomized(rand, 0, 0, 0, 4, 4, 6, true);
        // 2 door not random
        // 5 not random
        // 5-2 not random
        // not checking chest condition
        skipForChest(rand, 3, 2, 3);
        return true;
    }

}
