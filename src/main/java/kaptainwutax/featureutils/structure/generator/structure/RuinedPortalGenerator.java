package kaptainwutax.featureutils.structure.generator.structure;

import kaptainwutax.biomeutils.biome.Biome;
import kaptainwutax.biomeutils.biome.Biomes;
import kaptainwutax.featureutils.loot.LootTable;
import kaptainwutax.featureutils.loot.MCLootTables;
import kaptainwutax.featureutils.structure.RuinedPortal;
import kaptainwutax.featureutils.structure.generator.Generator;
import kaptainwutax.mcutils.block.Block;
import kaptainwutax.mcutils.rand.ChunkRand;
import kaptainwutax.mcutils.util.block.BlockBox;
import kaptainwutax.mcutils.util.block.BlockMirror;
import kaptainwutax.mcutils.util.block.BlockRotation;
import kaptainwutax.mcutils.util.data.Pair;
import kaptainwutax.mcutils.util.math.Vec3i;
import kaptainwutax.mcutils.util.pos.BPos;
import kaptainwutax.mcutils.util.pos.CPos;
import kaptainwutax.mcutils.version.MCVersion;
import kaptainwutax.terrainutils.ChunkGenerator;

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
	}

	@Override
	public boolean generate(ChunkGenerator generator, int chunkX, int chunkZ, ChunkRand rand) {
		RuinedPortal ruinedPortal = new RuinedPortal(generator.getBiomeSource().getDimension(), this.getVersion());
		// instantiate the biome type
		ruinedPortal.canSpawn(chunkX, chunkZ, generator.getBiomeSource());
		Biome biome = ruinedPortal.getBiome();
		rand.setCarverSeed(generator.getWorldSeed(), chunkX, chunkZ, this.getVersion());
		// air pocket // nextFloat()<0.5f

		if (DESERT_BIOME.contains(biome)) {
			location = Location.PARTLY_BURIED;
			airpocket = false;
		} else if (JUNGLE_BIOME.contains(biome)) {
			location = Location.ON_LAND_SURFACE;
			airpocket = rand.nextFloat() < 0.5F;
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
		int y = isLand.test(location) ? generator.getHeightOnGround(center.getX(), center.getZ()) :
				generator.getFirstHeightInColumn(center.getX(), center.getZ(), b -> b.getId() == generator.getDefaultFluid().getId());
		y -= 1; //get the block inside the ground
		y = findSuitableY(generator, location, airpocket, y, piece, rand);
		pos = new BPos(anchor.getX(), y, anchor.getZ());
		// this line can be replaced by the two under
//		piece = BlockBox.getBoundingBox(pos, rotation, pivot, mirror, size);
		piece.minY = y;
		piece.maxY = size.getY() - 1;

		// this is useless
//		Vec3i vec3i=piece.getCenter();
//		BPos centerPiece=new BPos(vec3i.getX(),piece.minY,vec3i.getZ());

		// we have to save this bb due to StructureStart, it can be used later on for processors for instance
		chunkBB = new BlockBox(chunkX << 4, chunkZ << 4, (chunkX << 4) + 15, (chunkZ << 4) + 15);
		if (!piece.intersects(chunkBB)) {
			System.err.println("The Chunk bounding box did not intersect with the structure piece, please report this bug");
			return false;
		}
		chunkBB.encompass(piece);
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

	private static int findSuitableY(ChunkGenerator generator, Location location, boolean airPocket, int height, BlockBox blockBox, ChunkRand rand) {
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

		boolean useFluid = !isLand.test(location);


		int dig;
		for (dig = y; dig > 15; --dig) {
			int cornerMatch = 0;

			for (Block[] column : columns) {
				if (y > generator.getMaxWorldHeight() || y < generator.getMinWorldHeight())
					continue;
				Block block = column[dig];
				boolean match = useFluid ? block == generator.getDefaultFluid() : block == generator.getDefaultBlock();
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
			// warning not like shipwreck we have to use ORIENTATION=NORTH or blockrotation.NONE
			BPos chestPos = offset.add(getPos());
			res.add(new Pair<>(lootType, chestPos));
		}
		return res;
	}

	@Override
	public ILootType[] getLootTypes() {
		return LootType.values();
	}

	public enum LootType implements ILootType {
		RUINED_PORTAL(MCLootTables.RUINED_PORTAL_CHEST),
		;

		public final LootTable lootTable;

		LootType(LootTable lootTable) {
			this.lootTable = lootTable;
		}

		public LootTable getLootTable() {
			return lootTable;
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

	private static final String[] STRUCTURE_LOCATION_PORTALS = new String[] {"portal_1", "portal_2", "portal_3", "portal_4", "portal_5", "portal_6", "portal_7", "portal_8", "portal_9", "portal_10"};
	private static final String[] STRUCTURE_LOCATION_GIANT_PORTALS = new String[] {"giant_portal_1", "giant_portal_2", "giant_portal_3"};
	private static final HashMap<String, LinkedHashMap<LootType, BPos>> STRUCTURE_TO_LOOT = new HashMap<>();
	private static final HashMap<String, BPos> STRUCTURE_SIZE = new HashMap<>();

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
}
