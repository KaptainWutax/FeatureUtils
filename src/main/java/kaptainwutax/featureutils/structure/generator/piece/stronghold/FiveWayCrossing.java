package kaptainwutax.featureutils.structure.generator.piece.stronghold;

import kaptainwutax.featureutils.structure.Stronghold;
import kaptainwutax.featureutils.structure.generator.StrongholdGenerator;
import kaptainwutax.seedutils.lcg.rand.JRand;
import kaptainwutax.seedutils.util.BlockBox;
import kaptainwutax.seedutils.util.Direction;

import java.util.List;

public class FiveWayCrossing extends Stronghold.Piece {

	private final boolean lowerLeftExists;
	private final boolean upperLeftExists;
	private final boolean lowerRightExists;
	private final boolean upperRightExists;

	public FiveWayCrossing(int pieceId, JRand rand, BlockBox boundingBox, Direction facing) {
		super(pieceId);
		this.setOrientation(facing);
		rand.nextInt(5); //Random entrance.
		this.boundingBox = boundingBox;
		this.lowerLeftExists = rand.nextBoolean();
		this.upperLeftExists = rand.nextBoolean();
		this.lowerRightExists = rand.nextBoolean();
		this.upperRightExists = rand.nextInt(3) > 0;
	}

	@Override
	public void populatePieces(StrongholdGenerator gen, Start start, List<Stronghold.Piece> pieces, JRand rand) {
		int int_1 = 3;
		int int_2 = 5;
		Direction facing = this.getFacing();

		if (facing == Direction.WEST || facing == Direction.NORTH) {
			int_1 = 8 - int_1;
			int_2 = 8 - int_2;
		}

		this.method_14874(gen, start, pieces, rand, 5, 1);
		
		if(this.lowerLeftExists) {
			this.method_14870(gen, start, pieces, rand, int_1, 1);
		}

		if(this.upperLeftExists) {
			this.method_14870(gen, start, pieces, rand, int_2, 7);
		}

		if(this.lowerRightExists) {
			this.method_14873(gen, start, pieces, rand, int_1, 1);
		}

		if(this.upperRightExists) {
			this.method_14873(gen, start, pieces, rand, int_2, 7);
		}
	}

	public static FiveWayCrossing createPiece(List<Stronghold.Piece> pieces, JRand rand, int x, int y, int z, Direction facing, int pieceId) {
		BlockBox box = BlockBox.rotated(x, y, z, -4, -3, 0, 10, 9, 11, facing);
		return Stronghold.Piece.isHighEnough(box) && Stronghold.Piece.getNextIntersectingPiece(pieces, box) == null ? new FiveWayCrossing(pieceId, rand, box, facing) : null;
	}

}
