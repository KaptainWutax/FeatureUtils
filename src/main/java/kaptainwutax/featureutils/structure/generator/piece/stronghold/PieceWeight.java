package kaptainwutax.featureutils.structure.generator.piece.stronghold;

import kaptainwutax.featureutils.structure.generator.piece.StructurePiece;

public class PieceWeight<T extends StructurePiece<T>> {

	public Class<? extends T> pieceClass;
	public final int pieceWeight;
	public int instancesSpawned;
	public int instancesLimit;

	public PieceWeight(Class<? extends T> pieceClass, int pieceWeight, int instancesLimit) {
		this.pieceClass = pieceClass;
		this.pieceWeight = pieceWeight;
		this.instancesLimit = instancesLimit;
	}

	public boolean canSpawnMoreStructuresOfType(int placedPieces)  {
		return this.instancesLimit == 0 || this.instancesSpawned < this.instancesLimit;
	}

	public boolean canSpawnMoreStructures() {
		return this.instancesLimit == 0 || this.instancesSpawned < this.instancesLimit;
	}

}
