package kaptainwutax.featureutils.structure.generator.piece.stronghold;
import kaptainwutax.featureutils.structure.Stronghold;
import kaptainwutax.seedutils.lcg.rand.JRand;
import kaptainwutax.seedutils.util.BlockBox;
import kaptainwutax.seedutils.util.Direction;

import java.util.List;

public class Library extends Stronghold.Piece {

	public Library(int pieceId, JRand rand, BlockBox boundingBox, Direction facing) {
		super(pieceId);
		this.setOrientation(facing);
		rand.nextInt(5); //Random entrance.
		this.boundingBox = boundingBox;
	}

	public static Library createPiece(List<Stronghold.Piece> pieces, JRand rand, int x, int y, int z, Direction facing, int pieceId) {
		BlockBox box = BlockBox.rotated(x, y, z, -4, -1, 0, 14, 11, 15, facing);
		if (!Stronghold.Piece.isHighEnough(box) || Stronghold.Piece.getNextIntersectingPiece(pieces, box) != null) {
			box = BlockBox.rotated(x, y, z, -4, -1, 0, 14, 6, 15, facing);
			if (!Stronghold.Piece.isHighEnough(box) || Stronghold.Piece.getNextIntersectingPiece(pieces, box) != null) {
				return null;
			}
		}

		return new Library(pieceId, rand, box, facing);
	}

}
