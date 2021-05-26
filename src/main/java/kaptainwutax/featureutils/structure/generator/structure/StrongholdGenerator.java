package kaptainwutax.featureutils.structure.generator.structure;

import kaptainwutax.featureutils.structure.Stronghold;
import kaptainwutax.featureutils.structure.generator.Generator;
import kaptainwutax.featureutils.structure.generator.piece.stronghold.*;
import kaptainwutax.mcutils.rand.ChunkRand;
import kaptainwutax.mcutils.util.block.BlockBox;
import kaptainwutax.mcutils.util.block.BlockDirection;
import kaptainwutax.mcutils.util.data.Pair;
import kaptainwutax.mcutils.util.pos.BPos;
import kaptainwutax.mcutils.version.MCVersion;
import kaptainwutax.seedutils.rand.JRand;
import kaptainwutax.terrainutils.TerrainGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

public class StrongholdGenerator extends Generator {

	public final List<Stronghold.Piece> pieceList = new ArrayList<>();
	public Class<? extends Stronghold.Piece> currentPiece = null;
	public BlockBox strongholdBox = null;
	protected List<PieceWeight<Stronghold.Piece>> pieceWeights = null;
	protected int totalWeight;
	protected Predicate<Stronghold.Piece> loopPredicate;
	protected boolean halted;
	private boolean[] eyes = null;

	public StrongholdGenerator(MCVersion version) {
		super(version);
	}

	public static long getStrongholdSalt(MCVersion version) {
		return version.isOlderThan(MCVersion.v1_16) ? 20003L : 50000L;
	}

	private static Stronghold.Piece classToPiece(Class<? extends Stronghold.Piece> pieceClass,
												 List<Stronghold.Piece> pieceList, JRand rand,
												 int x, int y, int z, BlockDirection facing, int pieceId) {
		Stronghold.Piece piece = null;

		if (pieceClass == Corridor.class) {
			piece = Corridor.createPiece(pieceList, rand, x, y, z, facing, pieceId);
		} else if (pieceClass == PrisonHall.class) {
			piece = PrisonHall.createPiece(pieceList, rand, x, y, z, facing, pieceId);
		} else if (pieceClass == LeftTurn.class) {
			piece = LeftTurn.createPiece(pieceList, rand, x, y, z, facing, pieceId);
		} else if (pieceClass == RightTurn.class) {
			piece = RightTurn.createPiece(pieceList, rand, x, y, z, facing, pieceId);
		} else if (pieceClass == SquareRoom.class) {
			piece = SquareRoom.createPiece(pieceList, rand, x, y, z, facing, pieceId);
		} else if (pieceClass == Stairs.class) {
			piece = Stairs.createPiece(pieceList, rand, x, y, z, facing, pieceId);
		} else if (pieceClass == SpiralStaircase.class) {
			piece = SpiralStaircase.createPiece(pieceList, rand, x, y, z, facing, pieceId);
		} else if (pieceClass == FiveWayCrossing.class) {
			piece = FiveWayCrossing.createPiece(pieceList, rand, x, y, z, facing, pieceId);
		} else if (pieceClass == ChestCorridor.class) {
			piece = ChestCorridor.createPiece(pieceList, rand, x, y, z, facing, pieceId);
		} else if (pieceClass == Library.class) {
			piece = Library.createPiece(pieceList, rand, x, y, z, facing, pieceId);
		} else if (pieceClass == PortalRoom.class) {
			piece = PortalRoom.createPiece(pieceList, x, y, z, facing, pieceId);
		}

		return piece;
	}

	public boolean generate(TerrainGenerator generator, int chunkX, int chunkZ, ChunkRand rand) {
		if (generator==null) return false;
		return this.generateRecursively(generator.getWorldSeed(), chunkX, chunkZ, rand, piece -> true);
	}

	@Override
	public List<Pair<ILootType, BPos>> getLootPos() {
		return getChestsPos();
	}

	@Override
	public List<Pair<ILootType, BPos>> getChestsPos() {
		return null;
	}

	public boolean populateStructure(long worldSeed, int chunkX, int chunkZ, ChunkRand rand) {
		return this.populateStructure(worldSeed, chunkX, chunkZ, rand, piece -> true, true);
	}

	public boolean populateStructure(long worldSeed, int chunkX, int chunkZ, ChunkRand rand, Predicate<Stronghold.Piece> shouldContinue, boolean portalOnly) {
		boolean halted = this.generateRecursively(worldSeed, chunkX, chunkZ, rand, shouldContinue);
		if (halted) return true;
		int posX = chunkX << 4; // max 25bits
		int posZ = chunkZ << 4;

		long decoratorSeed = rand.setPopulationSeed(worldSeed, posX, posZ, this.getVersion());
		decoratorSeed = this.getVersion().isOlderThan(MCVersion.v1_13) ? decoratorSeed : decoratorSeed + getStrongholdSalt(this.getVersion());
		rand.setSeed(decoratorSeed);

		BlockBox mainBox = new BlockBox(posX, posZ, posX + 15, posZ + 15);
		synchronized (this.pieceList) {
			if (!this.pieceList.isEmpty()) {
				BlockBox box = this.pieceList.get(0).getBoundingBox();
				BPos pos = new BPos(box.getCenter());
				Iterator<Stronghold.Piece> iterator = this.pieceList.iterator();
				while (iterator.hasNext()) {
					Stronghold.Piece piece = iterator.next();
					if (piece.getBoundingBox().intersects(mainBox) && !piece.process(rand, pos)) {
						iterator.remove();
					}
					if (piece instanceof PortalRoom) {
						eyes = ((PortalRoom) piece).getEyes();
						if (portalOnly) {
							break;
						}
					}
				}
			}

		}
		return false;
	}

	public boolean[] getEyes() {
		return eyes;
	}


	private boolean generateRecursively(long worldSeed, int chunkX, int chunkZ, ChunkRand rand, Predicate<Stronghold.Piece> shouldContinue) {
		this.halted = false;
		this.loopPredicate = shouldContinue;

		Start startPiece;
		int attemptCount = 0;

		do {
			this.totalWeight = 0;
			this.currentPiece = null;

			this.pieceWeights = this.getPieceWeights();

			rand.setCarverSeed(worldSeed + (long) (attemptCount++), chunkX, chunkZ, this.getVersion());
			if (this.getVersion().isOlderThan(MCVersion.v1_14)) rand.nextInt();

			startPiece = new Start(rand, (chunkX << 4) + 2, (chunkZ << 4) + 2);
			if (!shouldContinue.test(startPiece)) return true;
			this.pieceList.add(startPiece);

			startPiece.populatePieces(this, startPiece, this.pieceList, rand);
			List<Stronghold.Piece> pieces = startPiece.children;

			while (!pieces.isEmpty() && !this.halted) {
				int i = rand.nextInt(pieces.size());
				Stronghold.Piece piece = pieces.remove(i);
				piece.populatePieces(this, startPiece, this.pieceList, rand);
			}
		} while ((this.pieceList.isEmpty() || startPiece.portalRoom == null) && !this.halted);

		if (!this.halted) {
			this.strongholdBox = BlockBox.empty();
			this.pieceList.forEach(piece -> this.strongholdBox.encompass(piece.getBoundingBox()));
		}

		return this.halted;
	}

	private List<PieceWeight<Stronghold.Piece>> getPieceWeights() {
		return new ArrayList<>(Arrays.asList(
				new PieceWeight<>(Corridor.class, 40, 0),
				new PieceWeight<>(PrisonHall.class, 5, 5),
				new PieceWeight<>(LeftTurn.class, 20, 0),
				new PieceWeight<>(RightTurn.class, 20, 0),
				new PieceWeight<>(SquareRoom.class, 10, 6),
				new PieceWeight<>(Stairs.class, 5, 5),
				new PieceWeight<>(SpiralStaircase.class, 5, 5),
				new PieceWeight<>(FiveWayCrossing.class, 5, 4),
				new PieceWeight<>(ChestCorridor.class, 5, 4),
				new PieceWeight<Stronghold.Piece>(Library.class, 10, 2) {
					@Override
					public boolean canSpawnMoreStructuresOfType(int placedPieces) {
						return super.canSpawnMoreStructuresOfType(placedPieces) && placedPieces > 4;
					}
				},
				new PieceWeight<Stronghold.Piece>(PortalRoom.class, 20, 1) {
					@Override
					public boolean canSpawnMoreStructuresOfType(int placedPieces) {
						return super.canSpawnMoreStructuresOfType(placedPieces) && placedPieces > 5;
					}
				}
		));
	}

	public Stronghold.Piece generateAndAddPiece(Start startPiece, List<Stronghold.Piece> pieces, JRand rand,
												int x, int y, int z, BlockDirection facing, int pieceId) {
		if (pieceId > 50) {
			return null;
		} else if (Math.abs(x - startPiece.getBoundingBox().minX) <= 112 && Math.abs(z - startPiece.getBoundingBox().minZ) <= 112) {
			Stronghold.Piece piece = this.getNextStructurePiece(startPiece, pieces, rand, x, y, z, facing, pieceId + 1);

			if (piece != null) {
				pieces.add(piece);

				if (!this.loopPredicate.test(piece)) {
					this.halted = true;
				}

				startPiece.children.add(piece);
			}

			return piece;
		} else {
			return null;
		}
	}

	private Stronghold.Piece getNextStructurePiece(Start startPiece, List<Stronghold.Piece> pieceList, JRand rand,
												   int x, int y, int z, BlockDirection facing, int pieceId) {
		if (!this.canAddStructurePieces()) {
			return null;
		} else {
			if (this.currentPiece != null) {
				Stronghold.Piece piece = classToPiece(this.currentPiece, pieceList, rand, x, y, z, facing, pieceId);
				this.currentPiece = null;

				if (piece != null) {
					return piece;
				}
			}

			int int_5 = 0;

			while (int_5 < 5) {
				++int_5;
				int int_6 = rand.nextInt(this.totalWeight);
				Iterator<PieceWeight<Stronghold.Piece>> pieceWeightsIterator = this.pieceWeights.iterator();

				while (pieceWeightsIterator.hasNext()) {
					PieceWeight<Stronghold.Piece> pieceWeight = pieceWeightsIterator.next();
					int_6 -= pieceWeight.pieceWeight;

					if (int_6 < 0) {
						if (!pieceWeight.canSpawnMoreStructuresOfType(pieceId) || pieceWeight == startPiece.pieceWeight) {
							break;
						}

						Stronghold.Piece piece = classToPiece(pieceWeight.pieceClass, pieceList, rand, x, y, z, facing, pieceId);

						if (piece != null) {
							++pieceWeight.instancesSpawned;
							startPiece.pieceWeight = pieceWeight;

							if (!pieceWeight.canSpawnMoreStructures()) {
								pieceWeightsIterator.remove();
							}

							return piece;
						}
					}
				}
			}

			BlockBox boundingBox = SmallCorridor.createBox(pieceList, rand, x, y, z, facing);

			if (boundingBox != null && boundingBox.minY > 1) {
				return new SmallCorridor(pieceId, boundingBox, facing);
			} else {
				return null;
			}
		}
	}

	private boolean canAddStructurePieces() {
		boolean flag = false;
		this.totalWeight = 0;

		for (PieceWeight<Stronghold.Piece> pieceWeight : this.pieceWeights) {
			if (pieceWeight.instancesLimit > 0 && pieceWeight.instancesSpawned < pieceWeight.instancesLimit) {
				flag = true;
			}

			totalWeight += pieceWeight.pieceWeight;
		}

		return flag;
	}

	@Override
	public ILootType[] getLootTypes() {
		return new ILootType[0];
	}
}
