package kaptainwutax.featureutils.structure;

import kaptainwutax.biomeutils.biome.Biome;
import kaptainwutax.biomeutils.biome.Biomes;
import kaptainwutax.biomeutils.source.BiomeSource;
import kaptainwutax.featureutils.structure.generator.piece.StructurePiece;
import kaptainwutax.featureutils.structure.generator.piece.stronghold.Start;
import kaptainwutax.featureutils.structure.generator.structure.StrongholdGenerator;
import kaptainwutax.mcutils.rand.ChunkRand;
import kaptainwutax.mcutils.state.Dimension;
import kaptainwutax.mcutils.util.block.BlockBox;
import kaptainwutax.mcutils.util.block.BlockDirection;
import kaptainwutax.mcutils.util.pos.BPos;
import kaptainwutax.mcutils.util.pos.CPos;
import kaptainwutax.mcutils.version.MCVersion;
import kaptainwutax.mcutils.version.VersionMap;
import kaptainwutax.seedutils.rand.JRand;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Stronghold extends Structure<Stronghold.Config, Stronghold.Data> {

	public static final VersionMap<Stronghold.Config> CONFIGS = new VersionMap<Stronghold.Config>()
		.add(MCVersion.v1_0, new Stronghold.Config(32, 3, 3))
		.add(MCVersion.v1_9, new Stronghold.Config(32, 3, 128));

	public static final Set<Biome> VALID_BIOMES_16 = new HashSet<>(Arrays.asList(
		Biomes.PLAINS, Biomes.DESERT, Biomes.MOUNTAINS, Biomes.FOREST, Biomes.TAIGA, Biomes.SNOWY_TUNDRA,
		Biomes.SNOWY_MOUNTAINS, Biomes.MUSHROOM_FIELDS, Biomes.MUSHROOM_FIELD_SHORE, Biomes.DESERT_HILLS,
		Biomes.WOODED_HILLS, Biomes.TAIGA_HILLS, Biomes.MOUNTAIN_EDGE, Biomes.JUNGLE, Biomes.JUNGLE_HILLS,
		Biomes.JUNGLE_EDGE, Biomes.STONE_SHORE, Biomes.BIRCH_FOREST, Biomes.BIRCH_FOREST_HILLS, Biomes.DARK_FOREST,
		Biomes.SNOWY_TAIGA, Biomes.SNOWY_TAIGA_HILLS, Biomes.GIANT_TREE_TAIGA, Biomes.GIANT_TREE_TAIGA_HILLS,
		Biomes.WOODED_MOUNTAINS, Biomes.SAVANNA, Biomes.SAVANNA_PLATEAU, Biomes.BADLANDS, Biomes.WOODED_BADLANDS_PLATEAU,
		Biomes.BADLANDS_PLATEAU, Biomes.SUNFLOWER_PLAINS, Biomes.DESERT_LAKES, Biomes.GRAVELLY_MOUNTAINS,
		Biomes.FLOWER_FOREST, Biomes.TAIGA_MOUNTAINS, Biomes.ICE_SPIKES, Biomes.MODIFIED_JUNGLE,
		Biomes.MODIFIED_JUNGLE_EDGE, Biomes.TALL_BIRCH_FOREST, Biomes.TALL_BIRCH_HILLS, Biomes.DARK_FOREST_HILLS,
		Biomes.SNOWY_TAIGA_MOUNTAINS, Biomes.GIANT_SPRUCE_TAIGA, Biomes.GIANT_SPRUCE_TAIGA_HILLS,
		Biomes.MODIFIED_GRAVELLY_MOUNTAINS, Biomes.SHATTERED_SAVANNA, Biomes.SHATTERED_SAVANNA_PLATEAU,
		Biomes.ERODED_BADLANDS, Biomes.MODIFIED_WOODED_BADLANDS_PLATEAU, Biomes.MODIFIED_BADLANDS_PLATEAU));
	public static final Set<Biome> VALID_BIOMES_15 = new HashSet<>(Arrays.asList(Biomes.BAMBOO_JUNGLE, Biomes.BAMBOO_JUNGLE_HILLS));
	public static final Set<Biome> INVALID_BIOMES = new HashSet<>(Arrays.asList(Biomes.OCEAN, Biomes.SWAMP, Biomes.RIVER, Biomes.FROZEN_OCEAN,
		Biomes.FROZEN_RIVER, Biomes.BEACH, Biomes.DEEP_OCEAN, Biomes.SNOWY_BEACH, Biomes.WARM_OCEAN, Biomes.LUKEWARM_OCEAN, Biomes.COLD_OCEAN,
		Biomes.DEEP_WARM_OCEAN, Biomes.DEEP_LUKEWARM_OCEAN, Biomes.DEEP_COLD_OCEAN, Biomes.DEEP_FROZEN_OCEAN, Biomes.SWAMP_HILLS));

	static {
		VALID_BIOMES_15.addAll(VALID_BIOMES_16);
	}

	public Stronghold(MCVersion version) {
		this(CONFIGS.getAsOf(version), version);
	}

	public Stronghold(Config config, MCVersion version) {
		super(config, version);
	}

	public static String name() {
		return "stronghold";
	}

	public int getDistance() {
		return this.getConfig().distance;
	}

	public int getSpread() {
		return this.getConfig().spread;
	}

	public int getCount() {
		return this.getConfig().count;
	}

	public StrongholdGenerator getGenerator() {
		return new StrongholdGenerator(this.getVersion());
	}

	public CPos[] getAllStarts(BiomeSource source, JRand rand) {
		return this.getStarts(source, this.getCount(), rand);
	}

	public CPos[] getStarts(BiomeSource source, int numberOfStronghold, JRand rand) {
		int distance = this.getDistance();
		int count = Math.min(numberOfStronghold, this.getCount());
		int numberPerRing = this.getSpread();

		CPos[] starts = new CPos[count];
		rand.setSeed(source.getWorldSeed());

		double angle = rand.nextDouble() * Math.PI * 2.0D;

		int numberInRing = getVersion().isOlderThan(MCVersion.v1_9) ? 3 : 0;
		int ringId = 0;

		for(int idx = 0; idx < count; ++idx) {
			double distanceRing;
			if(getVersion().isNewerOrEqualTo(MCVersion.v1_9)) {
				distanceRing = (4.0D * distance + distance * ringId * 6.0D) + (rand.nextDouble() - 0.5D) * (double)distance * 2.5D;
			} else {
				distanceRing = (1.25D * (double)(ringId + 1) + rand.nextDouble()) * distance * (double)(ringId + 1);
			}

			int chunkX = (int)Math.round(Math.cos(angle) * distanceRing);
			int chunkZ = (int)Math.round(Math.sin(angle) * distanceRing);
			BPos pos = source.locateBiome((chunkX << 4) + 8, 0, (chunkZ << 4) + 8, 112, getValidBiomes(), rand);

			if(pos != null) {
				chunkX = pos.getX() >> 4;
				chunkZ = pos.getZ() >> 4;
			}

			starts[idx] = new CPos(chunkX, chunkZ);
			if(getVersion().isNewerOrEqualTo(MCVersion.v1_9)) {
				angle += Math.PI * 2.0D / (double)numberPerRing;
				++numberInRing;
			} else {
				angle += (Math.PI * 2.0D) * (double)(ringId + 1) / (double)numberPerRing;
				numberInRing=idx;
			}
			if(numberInRing == numberPerRing) {
				if(getVersion().isNewerOrEqualTo(MCVersion.v1_9)) {
					++ringId;
					numberInRing = 0;
					numberPerRing += 2 * numberPerRing / (ringId + 1);
					numberPerRing = Math.min(numberPerRing, count - idx);
					angle += rand.nextDouble() * Math.PI * 2.0D;
				} else {
					ringId += 2 + rand.nextInt(5);
					numberPerRing += 1 + rand.nextInt(2);
				}
			}
		}

		return starts;
	}

	@Override
	public boolean canStart(Data data, long structureSeed, ChunkRand rand) {
		throw new UnsupportedOperationException("stronghold start depends on biomes");
	}

	@Override
	public boolean canSpawn(int chunkX, int chunkZ, BiomeSource source) {
		//TODO: Improve this (A LOT!)
		for(CPos start : this.getAllStarts(source, new JRand(0L))) {
			if(start.getX() == chunkX && start.getZ() == chunkZ) return true;
		}

		return false;
	}

	@Override
	public Dimension getValidDimension() {
		return Dimension.OVERWORLD;
	}

	private Set<Biome> getValidBiomes() {
		if(this.getVersion().isNewerOrEqualTo(MCVersion.v1_16)) {
			return VALID_BIOMES_16;
		} else {
			return VALID_BIOMES_15;
		}
	}

	@Override
	public boolean isValidBiome(Biome biome) {
		return getValidBiomes().contains(biome);
	}

	public Stronghold.Data at(int chunkX, int chunkZ) {
		return new Stronghold.Data(this, chunkX, chunkZ);
	}

	public static class Config extends Structure.Config {
		public final int distance;
		public final int spread;
		public final int count;

		public Config(int distance, int spread, int count) {
			this.distance = distance;
			this.spread = spread;
			this.count = count;
		}
	}

	public static class Data extends Structure.Data<Stronghold> {
		public Data(Stronghold stronghold, int chunkX, int chunkZ) {
			super(stronghold, chunkX, chunkZ);
		}
	}

	public static abstract class Piece extends StructurePiece<Stronghold.Piece> {
		public Piece(int pieceId) {
			super(pieceId);
		}

		protected static Piece getNextIntersectingPiece(List<Piece> pieces, BlockBox box) {
			Iterator<Piece> var2 = pieces.iterator();

			Piece piece;

			do {
				if(!var2.hasNext()) {
					return null;
				}

				piece = var2.next();
			} while(piece.getBoundingBox() == null || !piece.getBoundingBox().intersects(box));

			return piece;
		}

		protected static boolean isHighEnough(BlockBox box) {
			return box != null && box.minY > 10;
		}

		public void populatePieces(StrongholdGenerator gen, Start start, List<Piece> pieces, JRand rand) {
		}

		public boolean process(JRand rand, BPos pos) {
			return false;
		}

		public void skipWithRandomized(JRand rand, int minX, int minY, int minZ, int maxX, int maxY, int maxZ, boolean replaceAir) {
			for(int i = minY; i <= maxY; ++i) {
				for(int j = minX; j <= maxX; ++j) {
					for(int k = minZ; k <= maxZ; ++k) {
						// Warning we assume air check (that can not be checked decently)
						// this makes everything off by a lot FIXME
						if(!replaceAir || true) {
							if(i == minY || i == maxY || j == minX || j == maxX || k == minZ || k == maxZ) {
								rand.nextFloat();
							}
						}

					}
				}
			}
		}

		public void skipForChest(JRand rand, int x, int y, int z) {
			// not doing the getWorld crap to get the blockpos
			// not checking if inside a block not if chest
			// not checking if blockstate already exists
			// not checking is tileentity is chest
			rand.nextLong();
		}

		public void skipWithRandomizedChance(JRand rand, float chance, int minX, int minY, int minZ, int maxX, int maxY, int maxZ, boolean replaceAir, boolean replaceInterior) {
			for(int i = minY; i <= maxY; ++i) {
				for(int j = minX; j <= maxX; ++j) {
					for(int k = minZ; k <= maxZ; ++k) {
						if(rand.nextFloat() > chance) { // no need of more (short circuiting)
							continue;
						}
						// do nothing yet
					}
				}
			}
		}

		public void skipWithChance(JRand rand, float chance) {
			if(rand.nextFloat() < chance) {
				// do nothing yet
			}
		}

		protected Piece generateSmallDoorChildrenForward(StrongholdGenerator gen, Start start, List<Piece> pieces, JRand rand, int int_1, int int_2) {
			BlockDirection facing = this.getFacing();

			if(facing == null) {
				return null;
			} else if(facing == BlockDirection.NORTH) {
				return gen.generateAndAddPiece(start, pieces, rand, this.boundingBox.minX + int_1, this.boundingBox.minY + int_2, this.boundingBox.minZ - 1, facing, this.pieceId);
			} else if(facing == BlockDirection.SOUTH) {
				return gen.generateAndAddPiece(start, pieces, rand, this.boundingBox.minX + int_1, this.boundingBox.minY + int_2, this.boundingBox.maxZ + 1, facing, this.pieceId);
			} else if(facing == BlockDirection.WEST) {
				return gen.generateAndAddPiece(start, pieces, rand, this.boundingBox.minX - 1, this.boundingBox.minY + int_2, this.boundingBox.minZ + int_1, facing, this.pieceId);
			} else if(facing == BlockDirection.EAST) {
				return gen.generateAndAddPiece(start, pieces, rand, this.boundingBox.maxX + 1, this.boundingBox.minY + int_2, this.boundingBox.minZ + int_1, facing, this.pieceId);
			}

			return null;
		}

		protected Piece generateSmallDoorChildrenLeft(StrongholdGenerator gen, Start start, List<Piece> pieces, JRand rand, int int_1, int int_2) {
			BlockDirection facing = this.getFacing();

			if(facing == null) {
				return null;
			} else if(facing == BlockDirection.NORTH) {
				return gen.generateAndAddPiece(start, pieces, rand, this.boundingBox.minX - 1, this.boundingBox.minY + int_1, this.boundingBox.minZ + int_2, BlockDirection.WEST, this.pieceId);
			} else if(facing == BlockDirection.SOUTH) {
				return gen.generateAndAddPiece(start, pieces, rand, this.boundingBox.minX - 1, this.boundingBox.minY + int_1, this.boundingBox.minZ + int_2, BlockDirection.WEST, this.pieceId);
			} else if(facing == BlockDirection.WEST) {
				return gen.generateAndAddPiece(start, pieces, rand, this.boundingBox.minX + int_2, this.boundingBox.minY + int_1, this.boundingBox.minZ - 1, BlockDirection.NORTH, this.pieceId);
			} else if(facing == BlockDirection.EAST) {
				return gen.generateAndAddPiece(start, pieces, rand, this.boundingBox.minX + int_2, this.boundingBox.minY + int_1, this.boundingBox.minZ - 1, BlockDirection.NORTH, this.pieceId);
			}

			return null;
		}

		protected Piece generateSmallDoorChildRight(StrongholdGenerator gen, Start start, List<Piece> pieces, JRand rand, int int_1, int int_2) {
			BlockDirection facing = this.getFacing();

			if(facing == null) {
				return null;
			} else if(facing == BlockDirection.NORTH) {
				return gen.generateAndAddPiece(start, pieces, rand, this.boundingBox.maxX + 1, this.boundingBox.minY + int_1, this.boundingBox.minZ + int_2, BlockDirection.EAST, this.pieceId);
			} else if(facing == BlockDirection.SOUTH) {
				return gen.generateAndAddPiece(start, pieces, rand, this.boundingBox.maxX + 1, this.boundingBox.minY + int_1, this.boundingBox.minZ + int_2, BlockDirection.EAST, this.pieceId);
			} else if(facing == BlockDirection.WEST) {
				return gen.generateAndAddPiece(start, pieces, rand, this.boundingBox.minX + int_2, this.boundingBox.minY + int_1, this.boundingBox.maxZ + 1, BlockDirection.SOUTH, this.pieceId);
			} else if(facing == BlockDirection.EAST) {
				return gen.generateAndAddPiece(start, pieces, rand, this.boundingBox.minX + int_2, this.boundingBox.minY + int_1, this.boundingBox.maxZ + 1, BlockDirection.SOUTH, this.pieceId);
			}

			return null;
		}
	}

}
