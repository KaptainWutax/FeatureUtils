package kaptainwutax.featureutils.structure.generator.piece;

import kaptainwutax.mcutils.util.block.BlockBox;
import kaptainwutax.mcutils.util.block.BlockDirection;

import java.util.ArrayList;
import java.util.List;

public class StructurePiece<T extends StructurePiece<T>> {

	public List<T> children = new ArrayList<>();
	protected int pieceId;
	protected BlockBox boundingBox;
	protected BlockDirection facing;

	public StructurePiece(int pieceId) {
		this.pieceId = pieceId;
	}

	public BlockDirection getFacing() {
		return this.facing;
	}

	public BlockBox getBoundingBox() {
		return this.boundingBox;
	}

	public void setOrientation(BlockDirection facing) {
		this.facing = facing;
	}

}
