package kaptainwutax.featureutils.structure.generator.piece.stronghold;

import kaptainwutax.featureutils.structure.Stronghold;
import kaptainwutax.featureutils.structure.generator.StrongholdGenerator;
import kaptainwutax.mcutils.util.block.BlockBox;
import kaptainwutax.mcutils.util.block.BlockDirection;
import kaptainwutax.mcutils.util.pos.BPos;
import kaptainwutax.seedutils.rand.JRand;

import java.util.List;

public class PortalRoom extends Stronghold.Piece {
	private final boolean[] eyes = new boolean[12];

	public PortalRoom(int pieceId, BlockBox boundingBox, BlockDirection facing) {
		super(pieceId);
		this.setOrientation(facing);
		this.boundingBox = boundingBox;
	}

	public static PortalRoom createPiece(List<Stronghold.Piece> pieces, int x, int y, int z, BlockDirection facing, int pieceId) {
		BlockBox box = BlockBox.rotated(x, y, z, -4, -1, 0, 11, 8, 16, facing.getRotation());
		return Stronghold.Piece.isHighEnough(box) && Stronghold.Piece.getNextIntersectingPiece(pieces, box) == null ? new PortalRoom(pieceId, box, facing) : null;
	}

	@Override
	public void populatePieces(StrongholdGenerator gen, Start start, List<Stronghold.Piece> pieces, JRand rand) {
		if (start != null) {
			start.portalRoom = this;
		}
	}

	public boolean process(JRand rand, BPos pos) {
		skipWithRandomized(rand, 0, 0, 0, 10, 7, 15, false);
		// door not random
		int yy = 6;
		skipWithRandomized(rand, 1, yy, 1, 1, yy, 14, false);
		skipWithRandomized(rand, 9, yy, 1, 9, yy, 14, false);
		skipWithRandomized(rand, 2, yy, 1, 8, yy, 2, false);
		skipWithRandomized(rand, 2, yy, 14, 8, yy, 14, false);
		skipWithRandomized(rand, 1, 1, 1, 2, 1, 4, false);
		skipWithRandomized(rand, 8, 1, 1, 9, 1, 4, false);
		// 2 calls not random
		skipWithRandomized(rand, 3, 1, 8, 7, 1, 12, false);
		// 1 call not random
		// 14-3 calls not random
		// (9-2)/2 calls not random
		skipWithRandomized(rand, 4, 1, 5, 6, 1, 7, false);
		skipWithRandomized(rand, 4, 2, 6, 6, 2, 7, false);
		skipWithRandomized(rand, 4, 3, 7, 6, 3, 7, false);
		// 7-4 * 3 calls not random
		for (int i = 0; i < eyes.length; i++) {
			eyes[i] = rand.nextFloat() > 0.9f;
		}
		// no random after
		return true;
	}

	public boolean[] getEyes() {
		return eyes;
	}
}
