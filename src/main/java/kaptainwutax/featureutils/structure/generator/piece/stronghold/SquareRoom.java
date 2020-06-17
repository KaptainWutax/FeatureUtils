package kaptainwutax.featureutils.structure.generator.piece.stronghold;

import kaptainwutax.featureutils.structure.Stronghold;
import kaptainwutax.featureutils.structure.generator.StrongholdGenerator;
import kaptainwutax.seedutils.lcg.rand.JRand;
import kaptainwutax.seedutils.util.BlockBox;
import kaptainwutax.seedutils.util.Direction;

import java.util.List;

public class SquareRoom extends Stronghold.Piece {

	public SquareRoom(int pieceId, JRand rand, BlockBox boundingBox, Direction facing) {
		super(pieceId);
		this.setOrientation(facing);
		rand.nextInt(5); //Random entrance.
		this.boundingBox = boundingBox;
		rand.nextInt(5); //Random room type.
	}

	@Override
	public void populatePieces(StrongholdGenerator gen, Start start, List<Stronghold.Piece> pieces, JRand rand) {
		this.method_14874(gen, start, pieces, rand, 4, 1);
		this.method_14870(gen, start, pieces, rand, 1, 4);
		this.method_14873(gen, start, pieces, rand, 1, 4);
	}

	public static SquareRoom createPiece(List<Stronghold.Piece> pieces, JRand rand, int x, int y, int z, Direction facing, int pieceId) {
		BlockBox blockBox_1 = BlockBox.rotated(x, y, z, -4, -1, 0, 11, 7, 11, facing);
		return Stronghold.Piece.isHighEnough(blockBox_1) && Stronghold.Piece.getNextIntersectingPiece(pieces, blockBox_1) == null ? new SquareRoom(pieceId, rand, blockBox_1, facing) : null;
	}

}
