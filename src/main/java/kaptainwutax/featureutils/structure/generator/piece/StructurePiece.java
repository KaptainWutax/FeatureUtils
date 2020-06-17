package kaptainwutax.featureutils.structure.generator.piece;

import kaptainwutax.seedutils.util.BlockBox;
import kaptainwutax.seedutils.util.Direction;

import java.util.ArrayList;
import java.util.List;

public class StructurePiece<T extends StructurePiece<T>> {

	protected int pieceId;
	public List<T> children = new ArrayList<>();

	protected BlockBox boundingBox;
	protected Direction facing;

	public StructurePiece(int pieceId) {
		this.pieceId = pieceId;
	}

	public Direction getFacing() {
		return this.facing;
	}

	public BlockBox getBoundingBox() {
		return this.boundingBox;
	}

	public void setOrientation(Direction facing) {
		this.facing = facing;
	}

}
