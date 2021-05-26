package kaptainwutax.featureutils.structure.generator.structure;

import kaptainwutax.biomeutils.biome.Biome;
import kaptainwutax.biomeutils.biome.Biomes;
import kaptainwutax.featureutils.loot.ChestContent;
import kaptainwutax.featureutils.loot.LootTable;
import kaptainwutax.featureutils.loot.MCLootTables;
import kaptainwutax.featureutils.structure.RegionStructure;
import kaptainwutax.featureutils.structure.RuinedPortal;
import kaptainwutax.featureutils.structure.generator.Generator;
import kaptainwutax.mcutils.block.Block;
import kaptainwutax.mcutils.block.Blocks;
import kaptainwutax.mcutils.rand.ChunkRand;
import kaptainwutax.mcutils.util.block.BlockBox;
import kaptainwutax.mcutils.util.block.BlockMirror;
import kaptainwutax.mcutils.util.block.BlockRotation;
import kaptainwutax.mcutils.util.data.Pair;
import kaptainwutax.mcutils.util.math.Vec3i;
import kaptainwutax.mcutils.util.pos.BPos;
import kaptainwutax.mcutils.util.pos.CPos;
import kaptainwutax.mcutils.version.MCVersion;
import kaptainwutax.terrainutils.TerrainGenerator;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class RuinedPortalGenerator extends Generator {
	private BlockRotation rotation = null;
	private BlockMirror mirror = null;
	private BPos pivot = null;
	private BPos pos = null;
	private String type = null;
	private BlockBox piece = null;
	private Location location = null;
	private Boolean airpocket = null;
	private BlockBox chunkBB = null;
	private TerrainGenerator generator = null;
	private boolean overgrown = false;
	private boolean cold = false;
	private boolean buried = false;
	private int height;
	private CPos cPos;

	private static final Predicate<Location> isLand = l -> l != Location.ON_OCEAN_FLOOR;
	private final HashSet<Biome> DESERT_BIOME = new HashSet<Biome>() {{
		add(Biomes.DESERT);
		add(Biomes.DESERT_HILLS);
		add(Biomes.DESERT_LAKES);
	}};
	private final HashSet<Biome> JUNGLE_BIOME = new HashSet<Biome>() {{
		add(Biomes.JUNGLE);
		add(Biomes.JUNGLE_EDGE);
		add(Biomes.MODIFIED_JUNGLE_EDGE);
		add(Biomes.MODIFIED_JUNGLE);
		add(Biomes.JUNGLE_HILLS);
		add(Biomes.BAMBOO_JUNGLE);
		add(Biomes.BAMBOO_JUNGLE_HILLS);
	}};
	private final HashSet<Biome> SWAMP_BIOME = new HashSet<Biome>() {{
		add(Biomes.SWAMP);
		add(Biomes.SWAMP_HILLS);
	}};
	private final HashSet<Biome> MOUNTAINS_BIOME = new HashSet<Biome>() {{
		add(Biomes.MOUNTAINS);
		add(Biomes.MOUNTAIN_EDGE);
		add(Biomes.WOODED_MOUNTAINS);
		add(Biomes.GRAVELLY_MOUNTAINS);
		add(Biomes.MODIFIED_GRAVELLY_MOUNTAINS);

		add(Biomes.SAVANNA_PLATEAU); // isMountains
		add(Biomes.SHATTERED_SAVANNA); // isMountains
		add(Biomes.SHATTERED_SAVANNA_PLATEAU); // isMountains

		add(Biomes.BADLANDS_PLATEAU); // isMountains
		add(Biomes.MODIFIED_BADLANDS_PLATEAU); // isMountains
		add(Biomes.WOODED_BADLANDS_PLATEAU); // isMountains
		add(Biomes.MODIFIED_WOODED_BADLANDS_PLATEAU); // isMountains
		add(Biomes.ERODED_BADLANDS); // isMountains

		add(Biomes.SNOWY_TAIGA_MOUNTAINS); // isMountains
		add(Biomes.TAIGA_MOUNTAINS); // isMountains

		add(Biomes.STONE_SHORE); // isMountains
	}};
	private final HashSet<Biome> OCEAN_BIOME = new HashSet<Biome>() {{
		add(Biomes.OCEAN);
		add(Biomes.DEEP_OCEAN);

		add(Biomes.COLD_OCEAN);
		add(Biomes.DEEP_COLD_OCEAN);

		add(Biomes.LUKEWARM_OCEAN);
		add(Biomes.DEEP_LUKEWARM_OCEAN);

		add(Biomes.WARM_OCEAN);

		add(Biomes.DEEP_WARM_OCEAN);

		add(Biomes.FROZEN_OCEAN);
		add(Biomes.DEEP_FROZEN_OCEAN);
	}};
	private final HashSet<Biome> NETHER_BIOME = new HashSet<Biome>() {{
		add(Biomes.NETHER_WASTES);
		add(Biomes.SOUL_SAND_VALLEY);
		add(Biomes.CRIMSON_FOREST);
		add(Biomes.BASALT_DELTAS);
		add(Biomes.WARPED_FOREST);
	}};

	public RuinedPortalGenerator(MCVersion version) {
		super(version);
	}

	public void reset() {
		this.piece = null;
		this.rotation = null;
		this.mirror = null;
		this.pivot = null;
		this.pos = null;
		this.type = null;
		this.location = null;
		this.airpocket = null;
		this.chunkBB = null;
		this.generator = null;
		this.overgrown = false;
		this.cold = false;
		this.cPos = null;
	}

	@Override

	public boolean generate(TerrainGenerator generator, int chunkX, int chunkZ, ChunkRand rand) {
		return this.generateStructure(generator, chunkX, chunkZ, rand);
	}

	@SuppressWarnings("unchecked")
	public boolean generateStructure(TerrainGenerator generator, int chunkX, int chunkZ, ChunkRand rand) {
		RuinedPortal ruinedPortal = new RuinedPortal(generator.getBiomeSource().getDimension(), this.getVersion());
		if (!ruinedPortal.canStart((RegionStructure.Data<RuinedPortal>) ruinedPortal.at(chunkX, chunkZ), generator.getWorldSeed(), rand)) return false;
		// instantiate the biome type
		if (!ruinedPortal.canSpawn(chunkX, chunkZ, generator.getBiomeSource())) return false;
		Biome biome = ruinedPortal.getBiome();
		rand.setCarverSeed(generator.getWorldSeed(), chunkX, chunkZ, this.getVersion());
		// air pocket // nextFloat()<0.5f

		if (DESERT_BIOME.contains(biome)) {
			location = Location.PARTLY_BURIED;
			airpocket = false;
		} else if (JUNGLE_BIOME.contains(biome)) {
			location = Location.ON_LAND_SURFACE;
			airpocket = rand.nextFloat() < 0.5F;
			overgrown = true;
		} else if (SWAMP_BIOME.contains(biome)) {
			location = Location.ON_OCEAN_FLOOR;
			airpocket = false;
		} else if (MOUNTAINS_BIOME.contains(biome)) {
			boolean isInside = rand.nextFloat() < 0.5F;
			location = isInside ? Location.IN_MOUNTAIN : Location.ON_LAND_SURFACE;
			airpocket = isInside || rand.nextFloat() < 0.5F; // warning shortcutting
		} else if (OCEAN_BIOME.contains(biome)) {
			location = Location.ON_OCEAN_FLOOR;
			airpocket = false;
		} else if (NETHER_BIOME.contains(biome)) {
			location = Location.IN_NETHER;
			airpocket = rand.nextFloat() < 0.5F;
		} else {
			boolean isInside = rand.nextFloat() < 0.5F;
			location = isInside ? Location.UNDERGROUND : Location.ON_LAND_SURFACE;
			airpocket = isInside || rand.nextFloat() < 0.5F; // warning shortcutting
		}
		if (rand.nextFloat() < 0.05F) {
			type = rand.getRandom(STRUCTURE_LOCATION_GIANT_PORTALS);
		} else {
			type = rand.getRandom(STRUCTURE_LOCATION_PORTALS);
		}
		rotation = BlockRotation.getRandom(rand);
		mirror = rand.nextFloat() < 0.5F ? BlockMirror.NONE : BlockMirror.FRONT_BACK;
		BPos size = Objects.requireNonNull(STRUCTURE_SIZE.get(type)); // we could shr(1) but y value is important here
		BPos anchor = new CPos(chunkX, chunkZ).toBlockPos(0);
		pivot = new BPos(size.getX() / 2, 0, size.getZ() / 2);
		piece = BlockBox.getBoundingBox(anchor, rotation, pivot, mirror, size);

		Vec3i center = piece.getCenter();
		Predicate<Block> blockTest = isLand.test(location) ? TerrainGenerator.WORLD_SURFACE_WG : TerrainGenerator.OCEAN_FLOOR_WG;
		height = generator.getFirstHeightInColumn(center.getX(), center.getZ(), blockTest);
		height -= 1; //get the block inside the ground
		int y = findSuitableY(generator, location, blockTest, airpocket, height, piece, rand);
		if (y < height - 5) {
			if (location == Location.IN_NETHER) {
				Block[] blocks = generator.getColumnAt(center.getX(), center.getZ());
				buried = true;
				for (int i = 0; i < 5; i++) {
					if (blocks[y + i] == Blocks.AIR) {
						buried = false;
						break;
					}
				}
			} else {
				buried = true;
			}
		}
		pos = new BPos(anchor.getX(), y, anchor.getZ());
		// this is not done because we don't support the bug (also temperatures, hell no)
//		if (portalFeature.portalType == RuinedPortalStructure.Location.MOUNTAIN || portalFeature.portalType == RuinedPortalStructure.Location.OCEAN || portalFeature.portalType == RuinedPortalStructure.Location.STANDARD) {
//			cold = biome.getTemperature(pos) < 0.15F;
//		}
		// this line can be replaced by the two under
//		piece = BlockBox.getBoundingBox(pos, rotation, pivot, mirror, size);
		piece.minY = y;
		piece.maxY = y + size.getY() - 1;

		// this is useless
//		Vec3i vec3i=piece.getCenter();
//		BPos centerPiece=new BPos(vec3i.getX(),piece.minY,vec3i.getZ());

		// we have to save this bb due to StructureStart, it can be used later on for processors for instance
		chunkBB = new BlockBox(chunkX << 4, chunkZ << 4, (chunkX << 4) + 15, (chunkZ << 4) + 15);
		if (!piece.intersects(chunkBB)) {
			//System.err.println("The Chunk bounding box did not intersect with the structure piece, please report this bug to Mojang (the structure will not generate)");
			return false;
		}
		chunkBB.encompass(piece);
		this.generator = generator; // have to store it to check lava sadly
		this.cPos = new CPos(chunkX, chunkZ);
		return true;
	}

	public BlockBox getChunkBB() {
		return chunkBB;
	}

	public Location getLocation() {
		return location;
	}

	public Boolean getAirpocket() {
		return airpocket;
	}

	public int getHeight() {
		return height;
	}

	public boolean isBuried() {
		return buried;
	}

	private static int findSuitableY(TerrainGenerator generator, Location location, Predicate<Block> blockPredicate, boolean airPocket, int height, BlockBox blockBox, ChunkRand rand) {
		int y;
		if (location == Location.IN_NETHER) {
			if (airPocket) {
				y = rand.getInt(32, 100);
			} else if (rand.nextFloat() < 0.5F) {
				y = rand.getInt(27, 29);
			} else {
				y = rand.getInt(29, 100);
			}
		} else if (location == Location.IN_MOUNTAIN) {
			int j = height - blockBox.getYSpan();
			y = rand.getInt(70, j);
		} else if (location == Location.UNDERGROUND) {
			int i1 = height - blockBox.getYSpan();
			y = rand.getInt(15, i1);
		} else if (location == Location.PARTLY_BURIED) {
			y = height - blockBox.getYSpan() + rand.getInt(2, 8);
		} else {
			y = height;
		}

		List<BPos> corners = Arrays.asList(new BPos(blockBox.minX, 0, blockBox.minZ), new BPos(blockBox.maxX, 0, blockBox.minZ), new BPos(blockBox.minX, 0, blockBox.maxZ), new BPos(blockBox.maxX, 0, blockBox.maxZ));
		List<Block[]> columns = corners.stream().map(e -> generator.getColumnAt(e.getX(), e.getZ())).collect(Collectors.toList());

		int dig;
		for (dig = y; dig > 15; --dig) {
			int cornerMatch = 0;

			for (Block[] column : columns) {
				if (y > generator.getMaxWorldHeight() || y < generator.getMinWorldHeight())
					continue;
				Block block = column[dig];
				boolean match = blockPredicate.test(block);
				if (match) {
					++cornerMatch;
					if (cornerMatch == 3) {
						return dig;
					}
				}
			}
		}

		return dig;
	}

	public String getType() {
		return type;
	}

	public BlockBox getPiece() {
		return piece;
	}

	public BlockRotation getRotation() {
		return rotation;
	}

	public BlockMirror getMirror() {
		return mirror;
	}

	public BPos getPivot() {
		return pivot;
	}

	public BPos getPos() {
		return pos;
	}

	@Override
	public List<Pair<ILootType, BPos>> getChestsPos() {
		HashMap<LootType, BPos> lootPos = STRUCTURE_TO_LOOT.get(type);
		List<Pair<ILootType, BPos>> res = new ArrayList<>();
		for (LootType lootType : lootPos.keySet()) {
			BPos offset = lootPos.get(lootType);
			// don't forget to transform
			offset = offset.transform(this.getMirror(), this.getRotation(), this.getPivot());
			BPos chestPos = offset.add(getPos());
			if (generator != null) {
				Block block = generator.getBlockAt(chestPos.getX(), chestPos.getY(), chestPos.getZ());
				if (block.getId() != Blocks.LAVA.getId()) {
					res.add(new Pair<>(lootType, chestPos));
				}
			} else {
				res.add(new Pair<>(lootType, chestPos));
			}
			if (version.isOlderOrEqualTo(MCVersion.v1_16_1)) {
				//System.err.println("Warning the chest might not appear due to a bug not being blacklisted by netherrack replacement (we don't support it because it requires temperatures)");
			}
		}
		return res;
	}

	@Override
	public List<Pair<ILootType, BPos>> getLootPos() {
		HashMap<LootType, BPos> lootPos = STRUCTURE_TO_LOOT.get(type);
		List<Pair<ILootType, BPos>> res = new ArrayList<>();
		for (LootType lootType : lootPos.keySet()) {
			res.add(new Pair<>(lootType, this.cPos.toBlockPos()));
		}
		return res;
	}

	private List<Pair<Block, BPos>> processBlocks(List<BPos> obsidianPos) {
		List<Pair<Block, BPos>> res = new ArrayList<>();
		ChunkRand rand = new ChunkRand();
		for (BPos pos : obsidianPos) {
			pos = pos.transform(this.getMirror(), this.getRotation(), this.getPivot()).add(getPos());
			// TODO the mossy stuff
			rand.setPositionSeed(pos, this.getVersion());
			if (rand.nextFloat() < 0.15F) {
				res.add(new Pair<>(Blocks.CRYING_OBSIDIAN, pos));
			} else {
				res.add(new Pair<>(Blocks.OBSIDIAN, pos));
			}
		}
		return res;
	}

	/**
	 * @return all the obsidian blocks part of the structure as a list of pair with
	 * either crying or normal obsidian and their position
	 */
	public List<Pair<Block, BPos>> getObsidian() {
		HashMap<Block, List<BPos>> blocks = STRUCTURE_TO_BLOCKS.get(type);
		List<BPos> obsidianPos = blocks.entrySet().stream()
				.filter(e -> e.getKey().getId() == Blocks.OBSIDIAN.getId())
				.map(Map.Entry::getValue)
				.flatMap(Collection::stream)
				.collect(Collectors.toList());
		return processBlocks(obsidianPos);
	}

	/**
	 * @return all the obsidian blocks part only of the portal as a list of pair with
	 * either crying or normal obsidian and their position
	 */
	public List<Pair<Block, BPos>> getPortal() {
		HashMap<Block, List<BPos>> blocks = STRUCTURE_TO_BLOCKS.get(type);
		List<BPos> obsidianPos = blocks.entrySet().stream()
				.filter(e -> e.getKey().getName().contains("obsidian_frame"))
				.map(Map.Entry::getValue)
				.flatMap(Collection::stream)
				.collect(Collectors.toList());
		return processBlocks(obsidianPos);
	}

	/**
	 * @return all the obsidian blocks part of the minimal portal (maximum usable part to make a portal)
	 * as a list of pair with either crying or normal obsidian and their position
	 */
	public List<Pair<Block, BPos>> getMinimalPortal() {
		HashMap<Block, List<BPos>> blocks = STRUCTURE_TO_BLOCKS.get(type);
		List<BPos> obsidianPos = blocks.get(MINIMAL_OBSIDIAN_FRAME);
		if (obsidianPos == null) return new ArrayList<>();
		return processBlocks(obsidianPos);
	}

	@Override
	public ILootType[] getLootTypes() {
		return LootType.values();
	}

	public enum LootType implements ILootType {
		RUINED_PORTAL(MCLootTables.RUINED_PORTAL_CHEST, ChestContent.ChestType.SINGLE_CHEST),
		;

		public final LootTable lootTable;
		public final ChestContent.ChestType chestType;

		LootType(LootTable lootTable, ChestContent.ChestType chestType) {
			this.lootTable = lootTable;
			this.chestType = chestType;
		}

		@Override
		public LootTable getLootTable() {
			return lootTable;
		}

		@Override
		public ChestContent.ChestType getChestType() {
			return chestType;
		}
	}

	public enum Location {
		ON_LAND_SURFACE("on_land_surface"),
		PARTLY_BURIED("partly_buried"),
		ON_OCEAN_FLOOR("on_ocean_floor"),
		IN_MOUNTAIN("in_mountain"),
		UNDERGROUND("underground"),
		IN_NETHER("in_nether");

		private static final Map<String, Location> BY_NAME = Arrays.stream(values()).collect(Collectors.toMap(Location::getName, (location) -> location));
		private final String name;

		Location(String name) {
			this.name = name;
		}

		public String getName() {
			return this.name;
		}

		public static Location byName(String name) {
			return BY_NAME.get(name);
		}
	}

//	public static boolean shouldRemoveChest(TerrainGenerator generator, BPos chestPos, Location location, BlockBox piece, boolean cold, boolean overgrown, MCVersion version) {
//		if (version.isOlderOrEqualTo(MCVersion.v1_16_1)) {
//			// spread netherrack removed the chests
//			if (generator == null) return false;
//			ChunkRand rand = new ChunkRand();
//			CPos chunkChestPos = chestPos.toChunkPos();
//			RuinedPortal ruinedPortal = new RuinedPortal(generator.getBiomeSource().getDimension(), version);
//			rand.setDecoratorSeed(generator.getWorldSeed(), chunkChestPos.getX() * 16, chunkChestPos.getZ() * 16, ruinedPortal.getDecorationSalt(), version);
//			// we ignore specific calls
//			// we advance the loot seed from postprocess
//			rand.advance(2);
//			boolean isOnFloor = location == Location.ON_LAND_SURFACE || location == Location.ON_OCEAN_FLOOR;
//			Vec3i vector3i = piece.getCenter();
//			int centerX = vector3i.getX();
//			int centerZ = vector3i.getZ();
//			float[] afloat = new float[] {1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 0.9F, 0.9F, 0.8F, 0.7F, 0.6F, 0.4F, 0.2F};
//			int lenght = afloat.length;
//			int triangularSize = (piece.getXSpan() + piece.getZSpan()) / 2;
//			int offset = rand.nextInt(Math.max(1, 8 - triangularSize / 2));
//			Predicate<Block> blockPredicate = isLand.test(location) ? TerrainGenerator.WORLD_SURFACE_WG : TerrainGenerator.OCEAN_FLOOR_WG;
//			for (int x = centerX - lenght; x <= centerX + lenght; ++x) {
//				for (int z = centerZ - lenght; z <= centerZ + lenght; ++z) {
//					int distanceToCenter = Math.abs(x - centerX) + Math.abs(z - centerZ);
//					int idx = Math.max(0, distanceToCenter + offset);
//					if (idx < lenght) {
//						float probability = afloat[idx];
//						if (rand.nextDouble() < (double) probability) {
//							int height = generator.getFirstHeightInColumn(x, z, blockPredicate) - 1;
//							int y = isOnFloor ? height : Math.min(piece.minY, height);
//							BPos pos = new BPos(x, y, z);
//							if (Math.abs(y - piece.minY) <= 3 && canBlockBeReplacedByNetherrackOrMagma(pos)) {
////								this.placeNetherrackOrMagma(p_237019_1_, p_237019_2_, blockpos$mutable);
//								boolean p = !cold && rand.nextFloat() < 0.07F;
//								if (overgrown) {
//									boolean p2 = rand.nextFloat() < 0.5F;
////									this.maybeAddLeavesAbove(p_237019_1_, p_237019_2_, blockpos$mutable);
//								}
//
////								this.addNetherrackDripColumn(p_237019_1_, p_237019_2_, blockpos$mutable.below());
//								BPos posDown = pos.add(0, -1, 0);
////								this.placeNetherrackOrMagma(rand, p_237022_2_, blockpos$mutable);
//								boolean p6 = !cold && rand.nextFloat() < 0.07F;
//								int i = 8;
//
//								while (i > 0 && rand.nextFloat() < 0.5F) {
//									posDown = posDown.add(0, -1, 0);
//									--i;
////									this.placeNetherrackOrMagma(rand, p_237022_2_, blockpos$mutable);
//									boolean p7 = !cold && rand.nextFloat() < 0.07F;
//								}
//							}
//						}
//					}
//				}
//			}
//		}
//		return false;
//	}
//
//	private static boolean canBlockBeReplacedByNetherrackOrMagma(BPos pos) {
//		BlockState blockstate = p_237010_1_.getBlockState(p_237010_2_);
//		return !blockstate.is(Blocks.AIR) && !blockstate.is(Blocks.OBSIDIAN) && !blockstate.is(Blocks.CHEST) && (this.verticalPlacement == RuinedPortalPiece.Location.IN_NETHER || !blockstate.is(Blocks.LAVA));
//	}

	public static final String[] STRUCTURE_LOCATION_PORTALS = new String[] {"portal_1", "portal_2", "portal_3", "portal_4", "portal_5", "portal_6", "portal_7", "portal_8", "portal_9", "portal_10"};
	public static final String[] STRUCTURE_LOCATION_GIANT_PORTALS = new String[] {"giant_portal_1", "giant_portal_2", "giant_portal_3"};
	public static final HashMap<String, LinkedHashMap<LootType, BPos>> STRUCTURE_TO_LOOT = new HashMap<>();
	public static final HashMap<String, LinkedHashMap<Block, List<BPos>>> STRUCTURE_TO_BLOCKS = new HashMap<>();
	public static final HashMap<String, BPos> STRUCTURE_SIZE = new HashMap<>();

	public static final Block MINIMAL_OBSIDIAN_FRAME = new Block(Blocks.OBSIDIAN.getVersion(), Blocks.OBSIDIAN.getId(), "minimal_obsidian_frame");
	public static final Block OBSIDIAN_FRAME = new Block(Blocks.OBSIDIAN.getVersion(), Blocks.OBSIDIAN.getId(), "obsidian_frame");

	static {
		STRUCTURE_TO_LOOT.put("giant_portal_1", new LinkedHashMap<LootType, BPos>() {{
			put(LootType.RUINED_PORTAL, new BPos(4, 3, 3));
		}});
		STRUCTURE_SIZE.put("giant_portal_1", new BPos(11, 17, 16));
		STRUCTURE_TO_LOOT.put("giant_portal_2", new LinkedHashMap<LootType, BPos>() {{
			put(LootType.RUINED_PORTAL, new BPos(9, 1, 9));
		}});
		STRUCTURE_SIZE.put("giant_portal_2", new BPos(11, 16, 16));
		STRUCTURE_TO_LOOT.put("giant_portal_3", new LinkedHashMap<LootType, BPos>() {{
			put(LootType.RUINED_PORTAL, new BPos(9, 2, 3));
		}});
		STRUCTURE_SIZE.put("giant_portal_3", new BPos(16, 16, 16));
		STRUCTURE_TO_LOOT.put("portal_1", new LinkedHashMap<LootType, BPos>() {{
			put(LootType.RUINED_PORTAL, new BPos(2, 2, 0));
		}});
		STRUCTURE_SIZE.put("portal_1", new BPos(6, 10, 6));
		STRUCTURE_TO_LOOT.put("portal_10", new LinkedHashMap<LootType, BPos>() {{
			put(LootType.RUINED_PORTAL, new BPos(2, 1, 7));
		}});
		STRUCTURE_SIZE.put("portal_10", new BPos(12, 8, 10));
		STRUCTURE_TO_LOOT.put("portal_2", new LinkedHashMap<LootType, BPos>() {{
			put(LootType.RUINED_PORTAL, new BPos(8, 2, 6));
		}});
		STRUCTURE_SIZE.put("portal_2", new BPos(9, 12, 9));
		STRUCTURE_TO_LOOT.put("portal_3", new LinkedHashMap<LootType, BPos>() {{
			put(LootType.RUINED_PORTAL, new BPos(3, 3, 6));
		}});
		STRUCTURE_SIZE.put("portal_3", new BPos(8, 9, 9));
		STRUCTURE_TO_LOOT.put("portal_4", new LinkedHashMap<LootType, BPos>() {{
			put(LootType.RUINED_PORTAL, new BPos(3, 3, 2));
		}});
		STRUCTURE_SIZE.put("portal_4", new BPos(8, 9, 9));
		STRUCTURE_TO_LOOT.put("portal_5", new LinkedHashMap<LootType, BPos>() {{
			put(LootType.RUINED_PORTAL, new BPos(4, 3, 2));
		}});
		STRUCTURE_SIZE.put("portal_5", new BPos(10, 10, 7));
		STRUCTURE_TO_LOOT.put("portal_6", new LinkedHashMap<LootType, BPos>() {{
			put(LootType.RUINED_PORTAL, new BPos(1, 1, 4));
		}});
		STRUCTURE_SIZE.put("portal_6", new BPos(5, 7, 7));
		STRUCTURE_TO_LOOT.put("portal_7", new LinkedHashMap<LootType, BPos>() {{
			put(LootType.RUINED_PORTAL, new BPos(0, 1, 2));
		}});
		STRUCTURE_SIZE.put("portal_7", new BPos(9, 7, 9));
		STRUCTURE_TO_LOOT.put("portal_8", new LinkedHashMap<LootType, BPos>() {{
			put(LootType.RUINED_PORTAL, new BPos(4, 4, 2));
		}});
		STRUCTURE_SIZE.put("portal_8", new BPos(14, 9, 9));
		STRUCTURE_TO_LOOT.put("portal_9", new LinkedHashMap<LootType, BPos>() {{
			put(LootType.RUINED_PORTAL, new BPos(4, 1, 0));
		}});
		STRUCTURE_SIZE.put("portal_9", new BPos(10, 8, 9));

		STRUCTURE_TO_BLOCKS.put("giant_portal_1", new LinkedHashMap<Block, List<BPos>>() {{
			put(OBSIDIAN_FRAME, new ArrayList<BPos>() {{
				add(new BPos(5, 3, 4));
				add(new BPos(5, 3, 5));
				add(new BPos(5, 3, 6));
				add(new BPos(5, 3, 7));
				add(new BPos(5, 3, 8));
				add(new BPos(5, 3, 11));
				add(new BPos(5, 4, 4));
				add(new BPos(5, 7, 11));
				add(new BPos(5, 8, 4));
				add(new BPos(5, 8, 11));
				add(new BPos(5, 9, 4));
				add(new BPos(5, 9, 11));
				add(new BPos(5, 10, 4));
				add(new BPos(5, 11, 4));
				add(new BPos(5, 12, 4));
				add(new BPos(5, 12, 5));
				add(new BPos(5, 12, 6));
				add(new BPos(5, 12, 7));
			}});
			put(MINIMAL_OBSIDIAN_FRAME, new ArrayList<BPos>() {{
				add(new BPos(5, 3, 9));
				add(new BPos(5, 3, 10));
				add(new BPos(5, 4, 11));
				add(new BPos(5, 5, 11));
				add(new BPos(5, 6, 11));
			}});
			put(Blocks.OBSIDIAN, new ArrayList<BPos>() {{
				add(new BPos(8, 1, 12));
				add(new BPos(9, 1, 9));
				add(new BPos(9, 1, 12));
				add(new BPos(10, 1, 8));
				add(new BPos(7, 2, 1));
				add(new BPos(8, 2, 1));
				add(new BPos(9, 2, 9));
				add(new BPos(9, 2, 12));
			}});
		}});
		STRUCTURE_TO_BLOCKS.put("giant_portal_2", new LinkedHashMap<Block, List<BPos>>() {{
			put(OBSIDIAN_FRAME, new ArrayList<BPos>() {{
				add(new BPos(5, 3, 4));
				add(new BPos(5, 3, 5));
				add(new BPos(5, 3, 6));
				add(new BPos(5, 3, 7));
				add(new BPos(5, 3, 8));
				add(new BPos(5, 3, 11));
				add(new BPos(5, 4, 4));
				add(new BPos(5, 7, 11));
				add(new BPos(5, 8, 11));
				add(new BPos(5, 9, 11));
				add(new BPos(5, 10, 4));
				add(new BPos(5, 10, 11));
				add(new BPos(5, 11, 4));
				add(new BPos(5, 11, 11));
				add(new BPos(5, 12, 4));
				add(new BPos(5, 12, 7));
				add(new BPos(5, 12, 8));
				add(new BPos(5, 12, 11));
			}});
			put(MINIMAL_OBSIDIAN_FRAME, new ArrayList<BPos>() {{
				add(new BPos(5, 3, 9));
				add(new BPos(5, 3, 10));
				add(new BPos(5, 4, 11));
				add(new BPos(5, 5, 11));
				add(new BPos(5, 6, 11));
			}});
			put(Blocks.OBSIDIAN, new ArrayList<BPos>() {{
				add(new BPos(3, 2, 5));
				add(new BPos(4, 2, 2));
				add(new BPos(7, 2, 1));
				add(new BPos(8, 2, 1));
				add(new BPos(3, 3, 5));
				add(new BPos(3, 4, 5));
			}});
		}});
		STRUCTURE_TO_BLOCKS.put("giant_portal_3", new LinkedHashMap<Block, List<BPos>>() {{
			put(OBSIDIAN_FRAME, new ArrayList<BPos>() {{
				add(new BPos(5, 3, 4));
				add(new BPos(5, 3, 5));
				add(new BPos(5, 3, 6));
				add(new BPos(5, 3, 7));
				add(new BPos(5, 3, 8));
				add(new BPos(5, 3, 11));
				add(new BPos(5, 4, 4));
				add(new BPos(5, 7, 11));
				add(new BPos(5, 8, 11));
				add(new BPos(5, 9, 11));
				add(new BPos(5, 12, 9));
			}});
			put(MINIMAL_OBSIDIAN_FRAME, new ArrayList<BPos>() {{
				add(new BPos(5, 3, 9));
				add(new BPos(5, 3, 10));
				add(new BPos(5, 4, 11));
				add(new BPos(5, 5, 11));
				add(new BPos(5, 6, 11));
			}});
			put(Blocks.OBSIDIAN, new ArrayList<BPos>() {{
				add(new BPos(3, 1, 1));
				add(new BPos(9, 1, 9));
				add(new BPos(3, 2, 1));
				add(new BPos(9, 2, 9));
				add(new BPos(10, 2, 4));
				add(new BPos(10, 2, 5));
				add(new BPos(3, 3, 1));
				add(new BPos(9, 3, 9));
				add(new BPos(9, 4, 9));
			}});
		}});
		STRUCTURE_TO_BLOCKS.put("portal_1", new LinkedHashMap<Block, List<BPos>>() {{
			put(OBSIDIAN_FRAME, new ArrayList<BPos>() {{
				add(new BPos(3, 2, 1));
				add(new BPos(3, 2, 4));
				add(new BPos(3, 6, 1));
			}});
			put(MINIMAL_OBSIDIAN_FRAME, new ArrayList<BPos>() {{
				add(new BPos(3, 2, 2));
				add(new BPos(3, 2, 3));
				add(new BPos(3, 3, 1));
				add(new BPos(3, 3, 4));
				add(new BPos(3, 4, 1));
				add(new BPos(3, 5, 1));
				add(new BPos(3, 6, 2));
				add(new BPos(3, 6, 3));
			}});
		}});
		STRUCTURE_TO_BLOCKS.put("portal_10", new LinkedHashMap<Block, List<BPos>>() {{
			put(OBSIDIAN_FRAME, new ArrayList<BPos>() {{
				add(new BPos(3, 1, 3));
				add(new BPos(3, 1, 6));
			}});
			put(MINIMAL_OBSIDIAN_FRAME, new ArrayList<BPos>() {{
				add(new BPos(3, 1, 4));
				add(new BPos(3, 1, 5));
				add(new BPos(3, 2, 3));
			}});
			put(Blocks.OBSIDIAN, new ArrayList<BPos>() {{
				add(new BPos(5, 1, 6));
				add(new BPos(6, 1, 3));
				add(new BPos(7, 1, 3));
				add(new BPos(7, 1, 6));
				add(new BPos(8, 1, 3));
				add(new BPos(8, 1, 4));
				add(new BPos(8, 1, 5));
				add(new BPos(8, 1, 6));
			}});
		}});
		STRUCTURE_TO_BLOCKS.put("portal_2", new LinkedHashMap<Block, List<BPos>>() {{
			put(Blocks.OBSIDIAN, new ArrayList<BPos>() {{
				add(new BPos(3, 1, 6));
				add(new BPos(3, 2, 6));
			}});
			put(OBSIDIAN_FRAME, new ArrayList<BPos>() {{
				add(new BPos(5, 4, 2));
				add(new BPos(5, 8, 2));
				add(new BPos(5, 8, 5));
			}});
			put(MINIMAL_OBSIDIAN_FRAME, new ArrayList<BPos>() {{
				add(new BPos(5, 5, 2));
				add(new BPos(5, 6, 2));
				add(new BPos(5, 7, 2));
				add(new BPos(5, 7, 5));
				add(new BPos(5, 8, 3));
				add(new BPos(5, 8, 4));
			}});
		}});
		STRUCTURE_TO_BLOCKS.put("portal_3", new LinkedHashMap<Block, List<BPos>>() {{
			put(Blocks.OBSIDIAN, new ArrayList<BPos>() {{
				add(new BPos(6, 2, 2));
				add(new BPos(6, 3, 3));
			}});
			put(OBSIDIAN_FRAME, new ArrayList<BPos>() {{
				add(new BPos(4, 3, 2));
				add(new BPos(4, 3, 5));
				add(new BPos(4, 7, 5));
			}});

			put(MINIMAL_OBSIDIAN_FRAME, new ArrayList<BPos>() {{
				add(new BPos(4, 3, 3));
				add(new BPos(4, 3, 4));
				add(new BPos(4, 4, 5));
				add(new BPos(4, 5, 5));
				add(new BPos(4, 6, 5));
				add(new BPos(4, 7, 4));
			}});
		}});
		STRUCTURE_TO_BLOCKS.put("portal_4", new LinkedHashMap<Block, List<BPos>>() {{
			put(OBSIDIAN_FRAME, new ArrayList<BPos>() {{
				add(new BPos(4, 3, 2));
				add(new BPos(4, 3, 5));
			}});
			put(MINIMAL_OBSIDIAN_FRAME, new ArrayList<BPos>() {{
				add(new BPos(4, 3, 3));
				add(new BPos(4, 3, 4));
				add(new BPos(4, 4, 2));
				add(new BPos(4, 4, 5));
				add(new BPos(4, 5, 2));
				add(new BPos(4, 5, 5));
				add(new BPos(4, 6, 2));
			}});

			put(Blocks.OBSIDIAN, new ArrayList<BPos>() {{
				add(new BPos(7, 1, 6));
				add(new BPos(6, 2, 4));
			}});
		}});
		STRUCTURE_TO_BLOCKS.put("portal_5", new LinkedHashMap<Block, List<BPos>>() {{
			put(OBSIDIAN_FRAME, new ArrayList<BPos>() {{
				add(new BPos(2, 3, 1));
				add(new BPos(2, 3, 4)); ;
				add(new BPos(2, 7, 4));
				add(new BPos(2, 8, 4));
			}});
			put(MINIMAL_OBSIDIAN_FRAME, new ArrayList<BPos>() {{
				add(new BPos(2, 3, 2));
				add(new BPos(2, 3, 3));
				add(new BPos(2, 4, 4));
				add(new BPos(2, 5, 4));
				add(new BPos(2, 6, 4));
			}});

			put(Blocks.OBSIDIAN, new ArrayList<BPos>() {{
				add(new BPos(5, 3, 1));
				add(new BPos(6, 3, 1));
				add(new BPos(7, 3, 1));
				add(new BPos(8, 3, 1));
				add(new BPos(8, 3, 2));
				add(new BPos(8, 3, 3));
			}});
		}});
		STRUCTURE_TO_BLOCKS.put("portal_6", new LinkedHashMap<Block, List<BPos>>() {{
			put(OBSIDIAN_FRAME, new ArrayList<BPos>() {{
				add(new BPos(2, 1, 0));
				add(new BPos(2, 1, 4));
				add(new BPos(2, 5, 0));
				add(new BPos(2, 5, 4));
			}});
			put(MINIMAL_OBSIDIAN_FRAME, new ArrayList<BPos>() {{
				add(new BPos(2, 1, 1));
				add(new BPos(2, 1, 2));
				add(new BPos(2, 1, 3));
				add(new BPos(2, 2, 0));
				add(new BPos(2, 2, 4));
				add(new BPos(2, 3, 0));
				add(new BPos(2, 3, 4));
				add(new BPos(2, 4, 0));
				add(new BPos(2, 4, 4));
				add(new BPos(2, 5, 1));
				add(new BPos(2, 5, 3));
			}});
			put(Blocks.OBSIDIAN, new ArrayList<BPos>() {{
				add(new BPos(4, 1, 3));
			}});
		}});
		STRUCTURE_TO_BLOCKS.put("portal_7", new LinkedHashMap<Block, List<BPos>>() {{
			put(OBSIDIAN_FRAME, new ArrayList<BPos>() {{
				add(new BPos(3, 0, 2));
				add(new BPos(3, 4, 2));
			}});
			put(MINIMAL_OBSIDIAN_FRAME, new ArrayList<BPos>() {{
				add(new BPos(3, 0, 3));
				add(new BPos(3, 0, 4));
				add(new BPos(3, 1, 2));
				add(new BPos(3, 1, 5));
				add(new BPos(3, 2, 2));
				add(new BPos(3, 2, 5));
				add(new BPos(3, 3, 2));
				add(new BPos(3, 4, 3));
				add(new BPos(3, 4, 4));
			}});
			put(Blocks.OBSIDIAN, new ArrayList<BPos>() {{
				add(new BPos(5, 1, 6));
			}});
		}});
		STRUCTURE_TO_BLOCKS.put("portal_8", new LinkedHashMap<Block, List<BPos>>() {{
			put(OBSIDIAN_FRAME, new ArrayList<BPos>() {{
				add(new BPos(5, 3, 2));
				add(new BPos(5, 3, 6));
				add(new BPos(5, 7, 2));
				add(new BPos(5, 7, 6));
				add(new BPos(5, 8, 6));
			}});
			put(MINIMAL_OBSIDIAN_FRAME, new ArrayList<BPos>() {{
				add(new BPos(5, 3, 3));
				add(new BPos(5, 3, 4));
				add(new BPos(5, 3, 5));
				add(new BPos(5, 4, 2));
				add(new BPos(5, 4, 6));
				add(new BPos(5, 5, 2));
				add(new BPos(5, 5, 6));
				add(new BPos(5, 6, 2));
				add(new BPos(5, 6, 6));
			}});
			put(Blocks.OBSIDIAN, new ArrayList<BPos>() {{
				add(new BPos(9, 1, 3));
				add(new BPos(9, 1, 4));
				add(new BPos(9, 1, 5));

			}});
		}});
		STRUCTURE_TO_BLOCKS.put("portal_9", new LinkedHashMap<Block, List<BPos>>() {{
			put(OBSIDIAN_FRAME, new ArrayList<BPos>() {{
				add(new BPos(4, 1, 3));
				add(new BPos(4, 1, 6));
				add(new BPos(4, 5, 6));
			}});
			put(MINIMAL_OBSIDIAN_FRAME, new ArrayList<BPos>() {{
				add(new BPos(4, 1, 4));
				add(new BPos(4, 1, 5));
				add(new BPos(4, 2, 3));
				add(new BPos(4, 2, 6));
				add(new BPos(4, 3, 6));
				add(new BPos(4, 4, 6));
				add(new BPos(4, 5, 4));
				add(new BPos(4, 5, 5));
			}});
			put(Blocks.OBSIDIAN, new ArrayList<BPos>() {{
				add(new BPos(7, 1, 3));
			}});
		}});
	}
	//import nbtlib
	//from pathlib import *
	//import sys
	//
	//p = Path(r'.').glob('**/*.nbt')
	//files = [x for x in p if x.is_file()]
	//for file in files:
	//    print(f'STRUCTURE_TO_LOOT.put("{file.name.rstrip(".nbt")}", new LinkedHashMap<LootType, BPos>() {{{{')
	//    nbt_file=nbtlib.load(file)
	//    root=nbt_file.root
	//    if "blocks" not in root.keys():
	//        print(f"Missing blocks key for {file}")
	//        sys.exit(1)
	//    for block in root["blocks"]:
	//        if "nbt" in block.keys() and "pos" in block.keys():
	//            pos=block["pos"]
	//            nbt=block["nbt"]
	//            if "LootTable" in nbt:
	//                print(f'    put(LootType.{nbt["LootTable"].upper().removeprefix("MINECRAFT:CHESTS/")},new BPos({",".join(map(str,map(int,pos)))}));')
	//    print('}});')
	//    if "size" in root.keys():
	//        print(f'STRUCTURE_SIZE.put("{file.name.rstrip(".nbt")}",new BPos({",".join(map(str,map(int,root["size"])))}));')
	//    else:
	//        print(f'Missing size key for {file.name.rstrip(".nbt")}')
	//        sys.exit(1)


	// import nbtlib
	//from pathlib import *
	//import sys
	//from collections import defaultdict
	//p = Path(r'.').glob('**/*.nbt')
	//files = [x for x in p if x.is_file()]
	//filts=["obsidian"]
	//
	//for file in files:
	//    print(f'STRUCTURE_TO_BLOCKS.put("{file.name.rstrip(".nbt")}", new LinkedHashMap<Block, List<BPos>>() {{{{')
	//    nbt_file=nbtlib.load(file)
	//    root=nbt_file.root
	//    if "palette" not in root.keys():
	//        print(f"Missing blocks key for {file}")
	//        sys.exit(1)
	//    palettePos={}
	//    for i,p in enumerate(root["palette"]):
	//        if "Name" in p.keys():
	//            name=p["Name"]
	//            for j,f in enumerate(filts):
	//                if f in name:
	//                    palettePos[i]=(name,j)
	//
	//    if "blocks" not in root.keys():
	//        print(f"Missing blocks key for {file}")
	//        sys.exit(1)
	//
	//    posFilt=defaultdict(list)
	//    for block in root["blocks"]:
	//        if "state" in block.keys():
	//            state=block["state"]
	//            if state in palettePos:
	//                n,j=palettePos[state]
	//                pos=block["pos"]
	//                posFilt[j].append(pos)
	//    for j in posFilt:
	//        print(f'    put(Blocks.{filts[j].upper()}, new ArrayList<BPos>(){{{{')
	//        for p in posFilt[j]:
	//            print(f'		add(new BPos({",".join(map(str,map(int,p)))}));')
	//        print("	}});")
	//    print('}});')
}
