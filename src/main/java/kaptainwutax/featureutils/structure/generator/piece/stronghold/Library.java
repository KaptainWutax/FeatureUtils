package kaptainwutax.featureutils.structure.generator.piece.stronghold;

import kaptainwutax.featureutils.structure.Stronghold;
import kaptainwutax.seedutils.lcg.rand.JRand;
import kaptainwutax.seedutils.mc.pos.BPos;
import kaptainwutax.seedutils.mc.util.BlockBox;
import kaptainwutax.seedutils.mc.util.Direction;

import java.util.List;

public class Library extends Stronghold.Piece {
    boolean isTall;

    public Library(int pieceId, JRand rand, BlockBox boundingBox, Direction facing) {
        super(pieceId);
        this.setOrientation(facing);
        rand.nextInt(5); //Random entrance.
        this.boundingBox = boundingBox;
        this.isTall = boundingBox.getYSpan() > 6;
    }

    public static Library createPiece(List<Stronghold.Piece> pieces, JRand rand, int x, int y, int z, Direction facing, int pieceId) {
        BlockBox box = BlockBox.rotated(x, y, z, -4, -1, 0, 14, 11, 15, facing.getRotation());
        if (!Stronghold.Piece.isHighEnough(box) || Stronghold.Piece.getNextIntersectingPiece(pieces, box) != null) {
            box = BlockBox.rotated(x, y, z, -4, -1, 0, 14, 6, 15, facing.getRotation());
            if (!Stronghold.Piece.isHighEnough(box) || Stronghold.Piece.getNextIntersectingPiece(pieces, box) != null) {
                return null;
            }
        }

        return new Library(pieceId, rand, box, facing);
    }

    public boolean process(JRand rand, BPos pos) {
        int yy = isTall ? 11 : 6;
        skipWithRandomized(rand, 0, 0, 0, 13, yy - 1, 14, true);
        // door not random
        skipWithRandomizedChance(rand, 0.07f, 2, 1, 1, 11, 4, 13, false, false);
        // tons of calls (not counting them)
        skipForChest(rand, 3, 3, 5);
        if (isTall) {
            skipForChest(rand, 12, 8, 1);
        }
        return true;
    }
}
