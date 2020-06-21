package kaptainwutax.featureutils.structure;

import kaptainwutax.biomeutils.Biome;
import kaptainwutax.biomeutils.source.BiomeSource;
import kaptainwutax.featureutils.structure.generator.StrongholdGenerator;
import kaptainwutax.featureutils.structure.generator.piece.StructurePiece;
import kaptainwutax.featureutils.structure.generator.piece.stronghold.Start;
import kaptainwutax.seedutils.lcg.rand.JRand;
import kaptainwutax.seedutils.mc.ChunkRand;
import kaptainwutax.seedutils.mc.MCVersion;
import kaptainwutax.seedutils.mc.VersionMap;
import kaptainwutax.seedutils.mc.pos.BPos;
import kaptainwutax.seedutils.mc.pos.CPos;
import kaptainwutax.seedutils.util.BlockBox;
import kaptainwutax.seedutils.util.Direction;

import java.util.*;

public class Stronghold extends Structure<Stronghold.Config, Stronghold.Data> {

	public static final VersionMap<Stronghold.Config> CONFIGS = new VersionMap<Stronghold.Config>()
			.add(MCVersion.v1_7, new Stronghold.Config(32, 3, 128));

	public static final Set<Biome> VALID_BIOMES = new HashSet<>(Arrays.asList(
			Biome.PLAINS, Biome.DESERT, Biome.MOUNTAINS, Biome.FOREST, Biome.TAIGA, Biome.SNOWY_TUNDRA,
			Biome.SNOWY_MOUNTAINS, Biome.MUSHROOM_FIELDS, Biome.MUSHROOM_FIELD_SHORE, Biome.DESERT_HILLS,
			Biome.WOODED_HILLS, Biome.TAIGA_HILLS, Biome.MOUNTAIN_EDGE, Biome.JUNGLE, Biome.JUNGLE_HILLS,
			Biome.JUNGLE_EDGE, Biome.STONE_SHORE, Biome.BIRCH_FOREST, Biome.BIRCH_FOREST_HILLS, Biome.DARK_FOREST,
			Biome.SNOWY_TAIGA, Biome.SNOWY_TAIGA_HILLS, Biome.GIANT_TREE_TAIGA, Biome.GIANT_TREE_TAIGA_HILLS,
			Biome.WOODED_MOUNTAINS, Biome.SAVANNA, Biome.SAVANNA_PLATEAU, Biome.BADLANDS, Biome.WOODED_BADLANDS_PLATEAU,
			Biome.BADLANDS_PLATEAU, Biome.SUNFLOWER_PLAINS, Biome.DESERT_LAKES, Biome.GRAVELLY_MOUNTAINS,
			Biome.FLOWER_FOREST, Biome.TAIGA_MOUNTAINS, Biome.ICE_SPIKES, Biome.MODIFIED_JUNGLE,
			Biome.MODIFIED_JUNGLE_EDGE, Biome.TALL_BIRCH_FOREST, Biome.TALL_BIRCH_HILLS, Biome.DARK_FOREST_HILLS,
			Biome.SNOWY_TAIGA_MOUNTAINS, Biome.GIANT_SPRUCE_TAIGA, Biome.GIANT_SPRUCE_TAIGA_HILLS,
			Biome.MODIFIED_GRAVELLY_MOUNTAINS, Biome.SHATTERED_SAVANNA, Biome.SHATTERED_SAVANNA_PLATEAU,
			Biome.ERODED_BADLANDS, Biome.MODIFIED_WOODED_BADLANDS_PLATEAU, Biome.MODIFIED_BADLANDS_PLATEAU,
			Biome.BAMBOO_JUNGLE, Biome.BAMBOO_JUNGLE_HILLS));

	public Stronghold(MCVersion version) {
		this(CONFIGS.get(version), version);
	}

	public Stronghold(Config config, MCVersion version) {
		super(config, version);
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

	public CPos[] getStarts(BiomeSource source, int count, JRand rand) {
		int i = this.getDistance();
		int j = Math.min(count, this.getCount());
		int k = this.getSpread();

		CPos[] starts = new CPos[j];
		rand.setSeed(source.getWorldSeed());

		double d = rand.nextDouble() * Math.PI * 2.0D;
		int l = 0;
		int m = 0;

		for(int n = 0; n < j; ++n) {
			double e = (double)(4 * i + i * m * 6) + (rand.nextDouble() - 0.5D) * (double)i * 2.5D;
			int o = (int)Math.round(Math.cos(d) * e);
			int p = (int)Math.round(Math.sin(d) * e);
			BPos pos = source.findBiomeInArea((o << 4) + 8, 0, (p << 4) + 8, 112, VALID_BIOMES, rand);

			if(pos != null) {
				o = pos.getX() >> 4;
				p = pos.getZ() >> 4;
			}

			starts[n] = new CPos(o, p);
			d += Math.PI * 2.0D / (double)k;
			++l;
			if (l == k) {
				++m;
				l = 0;
				k += 2 * k / (m + 1);
				k = Math.min(k, j - n);
				d += rand.nextDouble() * Math.PI * 2.0D;
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
		for(CPos start: this.getAllStarts(source, new JRand(0L))) {
			if(start.getX() == chunkX && start.getZ() == chunkZ)return true;
		}

		return false;
	}

	@Override
	public boolean isValidBiome(Biome biome) {
		return VALID_BIOMES.contains(biome);
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

		public void populatePieces(StrongholdGenerator gen, Start start, List<Piece> pieces, JRand rand) {
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

		protected Piece method_14874(StrongholdGenerator gen, Start start, List<Piece> pieces, JRand rand, int int_1, int int_2) {
			Direction facing = this.getFacing();

			if(facing == null) {
				return null;
			} else if(facing == Direction.NORTH) {
				return gen.generateAndAddPiece(start, pieces, rand, this.boundingBox.minX + int_1, this.boundingBox.minY + int_2, this.boundingBox.minZ - 1, facing, this.pieceId);
			} else if(facing == Direction.SOUTH) {
				return gen.generateAndAddPiece(start, pieces, rand, this.boundingBox.minX + int_1, this.boundingBox.minY + int_2, this.boundingBox.maxZ + 1, facing, this.pieceId);
			} else if(facing == Direction.WEST) {
				return gen.generateAndAddPiece(start, pieces, rand, this.boundingBox.minX - 1, this.boundingBox.minY + int_2, this.boundingBox.minZ + int_1, facing, this.pieceId);
			} else if(facing == Direction.EAST) {
				return gen.generateAndAddPiece(start, pieces, rand, this.boundingBox.maxX + 1, this.boundingBox.minY + int_2, this.boundingBox.minZ + int_1, facing, this.pieceId);
			}

			return null;
		}

		protected Piece method_14870(StrongholdGenerator gen, Start start, List<Piece> pieces, JRand rand, int int_1, int int_2) {
			Direction facing = this.getFacing();

			if(facing == null) {
				return null;
			} else if(facing == Direction.NORTH) {
				return gen.generateAndAddPiece(start, pieces, rand, this.boundingBox.minX - 1, this.boundingBox.minY + int_1, this.boundingBox.minZ + int_2, Direction.WEST, this.pieceId);
			} else if(facing == Direction.SOUTH) {
				return gen.generateAndAddPiece(start, pieces, rand, this.boundingBox.minX - 1, this.boundingBox.minY + int_1, this.boundingBox.minZ + int_2, Direction.WEST, this.pieceId);
			} else if(facing == Direction.WEST) {
				return gen.generateAndAddPiece(start, pieces, rand, this.boundingBox.minX + int_2, this.boundingBox.minY + int_1, this.boundingBox.minZ - 1, Direction.NORTH, this.pieceId);
			} else if(facing == Direction.EAST) {
				return gen.generateAndAddPiece(start, pieces, rand, this.boundingBox.minX + int_2, this.boundingBox.minY + int_1, this.boundingBox.minZ - 1, Direction.NORTH, this.pieceId);
			}

			return null;
		}

		protected Piece method_14873(StrongholdGenerator gen, Start start, List<Piece> pieces, JRand rand, int int_1, int int_2) {
			Direction facing = this.getFacing();

			if(facing == null) {
				return null;
			} else if(facing == Direction.NORTH) {
				return gen.generateAndAddPiece(start, pieces, rand, this.boundingBox.maxX + 1, this.boundingBox.minY + int_1, this.boundingBox.minZ + int_2, Direction.EAST, this.pieceId);
			} else if(facing == Direction.SOUTH) {
				return gen.generateAndAddPiece(start, pieces, rand, this.boundingBox.maxX + 1, this.boundingBox.minY + int_1, this.boundingBox.minZ + int_2, Direction.EAST, this.pieceId);
			} else if(facing == Direction.WEST) {
				return gen.generateAndAddPiece(start, pieces, rand, this.boundingBox.minX + int_2, this.boundingBox.minY + int_1, this.boundingBox.maxZ + 1, Direction.SOUTH, this.pieceId);
			} else if(facing == Direction.EAST) {
				return gen.generateAndAddPiece(start, pieces, rand, this.boundingBox.minX + int_2, this.boundingBox.minY + int_1, this.boundingBox.maxZ + 1, Direction.SOUTH, this.pieceId);
			}

			return null;
		}
	}

}
