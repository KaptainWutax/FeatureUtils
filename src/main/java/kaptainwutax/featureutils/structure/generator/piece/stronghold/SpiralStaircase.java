package kaptainwutax.featureutils.structure.generator.piece.stronghold;

import kaptainwutax.featureutils.structure.Stronghold;
import kaptainwutax.featureutils.structure.generator.StrongholdGenerator;
import kaptainwutax.seedutils.lcg.rand.JRand;
import kaptainwutax.seedutils.mc.pos.BPos;
import kaptainwutax.seedutils.mc.util.BlockBox;
import kaptainwutax.seedutils.mc.util.Direction;

import java.util.List;

public class SpiralStaircase extends Stronghold.Piece {

    private boolean isStructureStart;

    public SpiralStaircase(int pieceType, JRand rand, int x, int z) {
        super(pieceType);
        this.isStructureStart = true;
        this.setOrientation(Direction.randomHorizontal(rand));
        this.boundingBox = new BlockBox(x, 64, z, x + 5 - 1, 74, z + 5 - 1);
    }

    public SpiralStaircase(int pieceId, JRand rand, BlockBox boundingBox, Direction facing) {
        super(pieceId);
        this.isStructureStart = false;
        this.setOrientation(facing);
        rand.nextInt(5); //Random entrance.
        this.boundingBox = boundingBox;
    }

    public static SpiralStaircase createPiece(List<Stronghold.Piece> pieces, JRand rand, int x, int y, int z, Direction facing, int pieceId) {
        BlockBox box = BlockBox.rotated(x, y, z, -1, -7, 0, 5, 11, 5, facing.getRotation());
        return Stronghold.Piece.isHighEnough(box) && Stronghold.Piece.getNextIntersectingPiece(pieces, box) == null ? new SpiralStaircase(pieceId, rand, box, facing) : null;
    }

    @Override
    public void populatePieces(StrongholdGenerator gen, Start start, List<Stronghold.Piece> pieces, JRand rand) {
        if (this.isStructureStart) {
            gen.currentPiece = FiveWayCrossing.class;
        }

        this.generateSmallDoorChildrenForward(gen, start, pieces, rand, 1, 1);
    }

    public boolean process(JRand rand, BPos pos) {
        skipWithRandomized(rand, 0, 0, 0, 4, 10, 4, true);
        // 2 door not random
        // 17 not random
        return true;
    }
}
