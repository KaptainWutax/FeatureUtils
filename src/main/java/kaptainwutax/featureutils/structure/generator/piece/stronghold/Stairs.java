package kaptainwutax.featureutils.structure.generator.piece.stronghold;

import kaptainwutax.featureutils.structure.Stronghold;
import kaptainwutax.featureutils.structure.generator.structure.StrongholdGenerator;
import kaptainwutax.mcutils.util.block.BlockBox;
import kaptainwutax.mcutils.util.block.BlockDirection;
import kaptainwutax.mcutils.util.pos.BPos;
import kaptainwutax.seedutils.rand.JRand;

import java.util.List;

public class Stairs extends Stronghold.Piece {

	public Stairs(int pieceId, JRand rand, BlockBox boundingBox, BlockDirection facing) {
		super(pieceId);
		this.setOrientation(facing);
		rand.nextInt(5); //Random entrance.
		this.boundingBox = boundingBox;
	}

	public static Stairs createPiece(List<Stronghold.Piece> pieces, JRand rand, int x, int y, int z, BlockDirection facing, int pieceId) {
		BlockBox box = BlockBox.rotated(x, y, z, -1, -7, 0, 5, 11, 8, facing.getRotation());
		return Stronghold.Piece.isHighEnough(box) && Stronghold.Piece.getNextIntersectingPiece(pieces, box) == null ? new Stairs(pieceId, rand, box, facing) : null;
	}

	@Override
	public void populatePieces(StrongholdGenerator gen, Start start, List<Stronghold.Piece> pieces, JRand rand) {
		this.generateSmallDoorChildrenForward(gen, start, pieces, rand, 1, 1);
	}

	public boolean process(JRand rand, BPos pos) {
		skipWithRandomized(rand, 0, 0, 0, 4, 10, 7, true);
		// 2 door not random
		// 6* 3 + 5*3 not random
		return true;
	}
}
