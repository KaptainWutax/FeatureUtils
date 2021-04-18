package kaptainwutax.featureutils.structure.generator.piece.stronghold;

import kaptainwutax.featureutils.structure.Stronghold;
import kaptainwutax.featureutils.structure.generator.StrongholdGenerator;
import kaptainwutax.mcutils.util.block.BlockBox;
import kaptainwutax.mcutils.util.block.BlockDirection;
import kaptainwutax.mcutils.util.pos.BPos;
import kaptainwutax.seedutils.rand.JRand;

import java.util.List;

public class FiveWayCrossing extends Stronghold.Piece {

	private final boolean lowerLeftExists;
	private final boolean upperLeftExists;
	private final boolean lowerRightExists;
	private final boolean upperRightExists;

	public FiveWayCrossing(int pieceId, JRand rand, BlockBox boundingBox, BlockDirection facing) {
		super(pieceId);
		this.setOrientation(facing);
		rand.nextInt(5); //Random entrance.
		this.boundingBox = boundingBox;
		this.lowerLeftExists = rand.nextBoolean();
		this.upperLeftExists = rand.nextBoolean();
		this.lowerRightExists = rand.nextBoolean();
		this.upperRightExists = rand.nextInt(3) > 0;
	}

	public static FiveWayCrossing createPiece(List<Stronghold.Piece> pieces, JRand rand, int x, int y, int z, BlockDirection facing, int pieceId) {
		BlockBox box = BlockBox.rotated(x, y, z, -4, -3, 0, 10, 9, 11, facing.getRotation());
		return Stronghold.Piece.isHighEnough(box) && Stronghold.Piece.getNextIntersectingPiece(pieces, box) == null ? new FiveWayCrossing(pieceId, rand, box, facing) : null;
	}

	@Override
	public void populatePieces(StrongholdGenerator gen, Start start, List<Stronghold.Piece> pieces, JRand rand) {
		int int_1 = 3;
		int int_2 = 5;
		BlockDirection facing = this.getFacing();

		if (facing == BlockDirection.WEST || facing == BlockDirection.NORTH) {
			int_1 = 8 - int_1;
			int_2 = 8 - int_2;
		}

		this.generateSmallDoorChildrenForward(gen, start, pieces, rand, 5, 1);

		if (this.lowerLeftExists) {
			this.generateSmallDoorChildrenLeft(gen, start, pieces, rand, int_1, 1);
		}

		if (this.upperLeftExists) {
			this.generateSmallDoorChildrenLeft(gen, start, pieces, rand, int_2, 7);
		}

		if (this.lowerRightExists) {
			this.generateSmallDoorChildRight(gen, start, pieces, rand, int_1, 1);
		}

		if (this.upperRightExists) {
			this.generateSmallDoorChildRight(gen, start, pieces, rand, int_2, 7);
		}
	}

	public boolean process(JRand rand, BPos pos) {
		skipWithRandomized(rand, 0, 0, 0, 9, 8, 10, true);
		// door not random
		// 4 conditionned not random
		// 1 not random
		skipWithRandomized(rand, 1, 2, 1, 8, 2, 6, false);
		skipWithRandomized(rand, 4, 1, 5, 4, 4, 9, false);
		skipWithRandomized(rand, 8, 1, 5, 8, 4, 9, false);
		skipWithRandomized(rand, 1, 4, 7, 3, 4, 9, false);
		skipWithRandomized(rand, 1, 3, 5, 3, 3, 6, false);
		// 2 not random
		skipWithRandomized(rand, 5, 1, 7, 7, 1, 8, false);
		// 5 not random
		// 1 not random

		return true;
	}
}
