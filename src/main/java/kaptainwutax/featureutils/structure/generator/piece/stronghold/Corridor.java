package kaptainwutax.featureutils.structure.generator.piece.stronghold;

import kaptainwutax.featureutils.structure.Stronghold;
import kaptainwutax.featureutils.structure.generator.StrongholdGenerator;
import kaptainwutax.seedutils.lcg.rand.JRand;
import kaptainwutax.seedutils.util.BlockBox;
import kaptainwutax.seedutils.util.Direction;

import java.util.List;

public class Corridor extends Stronghold.Piece {

	private final boolean leftExitExists;
	private final boolean rightExitExists;

	public Corridor(int pieceId, JRand rand, BlockBox boundingBox, Direction facing) {
		super(pieceId);
		this.setOrientation(facing);
		rand.nextInt(5); //Random entrance.
		this.boundingBox = boundingBox;
		this.leftExitExists = rand.nextInt(2) == 0;
		this.rightExitExists = rand.nextInt(2) == 0;
	}

	@Override
	public void populatePieces(StrongholdGenerator gen, Start start, List<Stronghold.Piece> pieces, JRand rand) {
		this.method_14874(gen, start, pieces, rand, 1, 1);

		if(this.leftExitExists) {
			this.method_14870(gen, start, pieces, rand, 1, 2);
		}

		if(this.rightExitExists) {
			this.method_14873(gen, start, pieces, rand, 1, 2);
		}
	}

	public static Corridor createPiece(List<Stronghold.Piece> pieces, JRand rand, int x, int y, int z, Direction facing, int pieceId) {
		BlockBox box = BlockBox.rotated(x, y, z, -1, -1, 0, 5, 5, 7, facing);
		return Stronghold.Piece.isHighEnough(box) && Stronghold.Piece.getNextIntersectingPiece(pieces, box) == null ? new Corridor(pieceId, rand, box, facing) : null;
	}

}
