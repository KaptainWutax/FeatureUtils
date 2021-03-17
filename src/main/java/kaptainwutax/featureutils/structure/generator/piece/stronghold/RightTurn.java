package kaptainwutax.featureutils.structure.generator.piece.stronghold;

import kaptainwutax.featureutils.structure.Stronghold;
import kaptainwutax.featureutils.structure.generator.StrongholdGenerator;
import kaptainwutax.seedutils.lcg.rand.JRand;
import kaptainwutax.seedutils.mc.pos.BPos;
import kaptainwutax.seedutils.util.BlockBox;
import kaptainwutax.seedutils.util.Direction;

import java.util.List;

public class RightTurn extends Stronghold.Piece {

	public RightTurn(int pieceId, JRand rand, BlockBox boundingBox, Direction facing) {
		super(pieceId);
		this.setOrientation(facing);
		rand.nextInt(5); //Random entrance.
		this.boundingBox = boundingBox;
	}

	@Override
	public void populatePieces(StrongholdGenerator gen, Start start, List<Stronghold.Piece> pieces, JRand rand) {
		Direction facing = this.getFacing();

		if(facing != Direction.NORTH && facing != Direction.EAST) {
			this.generateSmallDoorChildrenLeft(gen, start, pieces, rand, 1, 1);
		} else {
			this.generateSmallDoorChildRight(gen, start, pieces, rand, 1, 1);
		}
	}

	public static RightTurn createPiece(List<Stronghold.Piece> pieces, JRand rand, int x, int y, int z, Direction facing, int pieceId) {
		BlockBox box = BlockBox.rotated(x, y, z, -1, -1, 0, 5, 5, 5, facing);
		return Stronghold.Piece.isHighEnough(box) && Stronghold.Piece.getNextIntersectingPiece(pieces, box) == null ? new RightTurn(pieceId, rand, box, facing) : null;
	}

	public boolean process(JRand rand, BPos pos) {
		skipWithRandomized(rand,0, 0, 0, 4, 4, 4, true);
		// door not random
		// 1 call not random (direction dependant)
		return true;
	}

}
