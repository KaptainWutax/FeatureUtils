package kaptainwutax.featureutils.structure.generator;

import kaptainwutax.featureutils.loot.LootTable;
import kaptainwutax.featureutils.loot.MCLootTables;
import kaptainwutax.featureutils.loot.item.Item;
import kaptainwutax.featureutils.loot.item.Items;
import kaptainwutax.featureutils.structure.EndCity;
import kaptainwutax.mcutils.rand.ChunkRand;
import kaptainwutax.mcutils.util.block.BlockMirror;
import kaptainwutax.mcutils.util.block.BlockRotation;
import kaptainwutax.mcutils.util.pos.BPos;
import kaptainwutax.mcutils.version.MCVersion;
import kaptainwutax.terrainutils.ChunkGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class EndCityGenerator {
	private final MCVersion version;
	private final List<Template> globalPieces = new ArrayList<>();

	public EndCityGenerator(MCVersion version) {
		this.version = version;
	}

	public MCVersion getVersion() {
		return version;
	}

	// maybe pass the generator here
	public boolean generate(ChunkGenerator generator, int chunkX, int chunkZ, ChunkRand rand) {
		rand.setCarverSeed(generator.getWorldSeed(), chunkX, chunkZ, this.getVersion());
		int y = EndCity.getAverageYPosition(generator, chunkX, chunkZ);
		if (y < 60) return false;
		BlockRotation rotation = BlockRotation.getRandom(rand);
		// we assume that the height is pass
		BPos start = new BPos(chunkX * 16 + 8, y, chunkZ * 16 + 8);
		this.start(start, rotation, this.globalPieces, rand);
		return true;
	}


	private static Template calculateTemplate(Template previous, BPos pos, String name, BlockRotation rotation, boolean overwrite) {
		Template template = new Template(name, previous.pos, rotation, overwrite);
		BPos transform = pos.transform(BlockMirror.NONE, rotation, BPos.ORIGIN);
		template.pos.add(transform);
		return template;
	}

	private static Template generateAndAdd(List<Template> pieces,Template previous, BPos pos, String name, BlockRotation rotation, boolean overwrite){
		Template template=calculateTemplate(previous,pos,name,rotation,overwrite);
		pieces.add(template);
		return template;
	}

	public boolean start(BPos start, BlockRotation rotation, List<Template> pieces, ChunkRand rand) {
		TOWER_BRIDGE_GENERATOR.init();
		Template base = new Template("base_floor", start, rotation, true);
		pieces.add(base);
		base=generateAndAdd(pieces,base, new BPos(-1, 0, -1), "second_floor_1", rotation, false);
		base = generateAndAdd(pieces,base, new BPos(-1, 4, -1), "third_floor_1", rotation, false);
		base = generateAndAdd(pieces,base, new BPos(-1, 8, -1), "third_roof", rotation, true);
		return generateRecursively(TOWER_GENERATOR, 1, base, null, pieces, rand);
	}

	private static boolean generateRecursively(Generator generator, int depth, Template template, BPos pos, List<Template> pieces, ChunkRand rand) {
		if (depth <= 8) {
			List<Template> localPieces = new ArrayList<>();
			if (generator.generate(depth, template, pos, localPieces, rand)) {
				boolean isBlocking = false;
				int genDepth = rand.nextInt();
				for (Template piece : localPieces) {
					piece.setGenDepth(genDepth);
					Template collisions = piece.findCollisionPiece(localPieces);
					if (collisions != null && collisions.genDepth == template.genDepth) {
						isBlocking = true;
						break;
					}
				}
				if (!isBlocking) {
					pieces.addAll(localPieces);
					return true;
				}
			}
		}
		return false;
	}

	static class Template {
		private final String name;
		private final BPos pos;
		private final BlockRotation rotation;
		private final boolean overwrite;
		private int genDepth = 0;

		public Template(String name, BPos pos, BlockRotation rotation, boolean overwrite) {
			this.name = name;
			this.pos = pos;
			this.rotation = rotation;
			this.overwrite = overwrite;
		}

		public void setGenDepth(int genDepth) {
			this.genDepth = genDepth;
		}

		public int getGenDepth() {
			return genDepth;
		}

		public Template findCollisionPiece(List<Template> templates) {
			return null;
		}

		public String getName() {
			return name;
		}

		public BlockRotation getRotation() {
			return rotation;
		}

		public BPos getPos() {
			return pos;
		}

		public boolean isOverwrite() {
			return overwrite;
		}
	}

	private static final Generator FAT_TOWER_GENERATOR = new Generator() {
		@Override
		public void init() {

		}

		@Override
		public boolean generate(int depth, Template current, BPos pos, List<Template> pieces, ChunkRand rand) {
			return false;
		}
	};

	private static final Generator HOUSE_TOWER_GENERATOR = new Generator() {
		@Override
		public void init() {

		}

		@Override
		public boolean generate(int depth, Template current, BPos pos, List<Template> pieces, ChunkRand rand) {
			if (depth <= 8) {
				BlockRotation rotation = current.getRotation();
				Template base = generateAndAdd(pieces,current, pos, "base_floor", rotation, true);
				int size = rand.nextInt(3);
				if (size == 0) {
					Template roof = generateAndAdd(pieces,current, new BPos(-1, 4, -1), "base_roof", rotation, true);
				}else if (size==1){
					Template secondFloor = generateAndAdd(pieces,current, new BPos(-1, 0, -1), "second_floor_2", rotation, false);
					Template secondRoof = generateAndAdd(pieces,secondFloor,new BPos(-1, 0, -1), "second_floor_2", rotation, false);
					generateRecursively(TOWER_GENERATOR,depth+1,secondRoof,null,pieces,rand);
				}else{
					Template secondFloor = generateAndAdd(pieces,current,new BPos(-1, 0, -1), "second_floor_2", rotation, false);
					Template thirdFloor = generateAndAdd(pieces,secondFloor,new BPos(-1, 4, -1), "third_floor_2", rotation, false);
					Template thirdRoof = generateAndAdd(pieces,thirdFloor,new BPos(-1, 8, -1), "third_roof", rotation, true);
					generateRecursively(TOWER_GENERATOR,depth+1,thirdRoof,null,pieces,rand);
				}
				return true;
			}
			return false;
		}
	};

	private static final Generator TOWER_BRIDGE_GENERATOR = new Generator() {
		public boolean shipCreated;

		@Override
		public void init() {
			shipCreated = false;
		}

		@Override
		public boolean generate(int depth, Template current, BPos pos, List<Template> pieces, ChunkRand rand) {
			return false;
		}
	};

	private static final Generator TOWER_GENERATOR = new Generator() {
		@Override
		public void init() {

		}

		@Override
		public boolean generate(int depth, Template current, BPos pos, List<Template> pieces, ChunkRand rand) {
			return false;
		}
	};

	interface Generator {
		void init();

		boolean generate(int depth, Template current, BPos pos, List<Template> pieces, ChunkRand rand);
	}


	public enum LootType {
		SENTRY(MCLootTables.NULL, Items.SHULKER_SHELL),
		ELYTRA(MCLootTables.NULL, Items.ELYTRA),
		CHEST(MCLootTables.END_CITY_TREASURE_CHEST, Items.CHEST);
		private final LootTable lootTable;
		private final Item item;

		LootType(LootTable lootTable, Item item) {
			this.lootTable = lootTable;
			this.item = item;
		}
	}

	private static class PieceInfo {
		private final int type;
		private final int depth;
		private final int xMin, yMin, zMin;
		private final int xMax, yMax, zMax;
		private final int facing;

		public PieceInfo(int type, int depth, int xMin, int yMin, int zMin, int xMax, int yMax, int zMax, int facing) {
			this.type = type;
			this.depth = depth;
			this.xMin = xMin;
			this.yMin = yMin;
			this.zMin = zMin;
			this.xMax = xMax;
			this.yMax = yMax;
			this.zMax = zMax;
			this.facing = facing;
		}
	}

	private static final String[] TYPES = new String[] {
			"base_floor",
			"base_roof",
			"bridge_end",
			"bridge_gentle_stairs",
			"bridge_piece",
			"bridge_steep_stairs",
			"fat_tower_base",
			"fat_tower_middle",
			"fat_tower_top",
			"second_floor_1",
			"second_floor_2",
			"second_roof",
			"ship",
			"third_floor_1",
			"third_floor_2",
			"third_roof",
			"tower_base",
			"tower_floor",
			"tower_piece",
			"tower_top"
	};

	private static final HashMap<String, LinkedHashMap<LootType, List<BPos>>> STRUCTURE_TO_LOOT = new HashMap<>();
	private static final HashMap<String, BPos> STRUCTURE_SIZE = new HashMap<>();

	static {
		STRUCTURE_TO_LOOT.put("base_floor", new LinkedHashMap<LootType, List<BPos>>() {{
			computeIfAbsent(LootType.SENTRY, k -> new ArrayList<>()).add(new BPos(3, 2, 9));
			computeIfAbsent(LootType.SENTRY, k -> new ArrayList<>()).add(new BPos(6, 2, 9));
		}});
		STRUCTURE_SIZE.put("base_floor.nbt", new BPos(10, 4, 10));
		STRUCTURE_TO_LOOT.put("base_roof", new LinkedHashMap<LootType, List<BPos>>() {{
		}});
		STRUCTURE_SIZE.put("base_roof.nbt", new BPos(12, 2, 12));
		STRUCTURE_TO_LOOT.put("bridge_end", new LinkedHashMap<LootType, List<BPos>>() {{
		}});
		STRUCTURE_SIZE.put("bridge_end.nbt", new BPos(5, 6, 2));
		STRUCTURE_TO_LOOT.put("bridge_gentle_stairs", new LinkedHashMap<LootType, List<BPos>>() {{
		}});
		STRUCTURE_SIZE.put("bridge_gentle_stairs.nbt", new BPos(5, 7, 8));
		STRUCTURE_TO_LOOT.put("bridge_piece", new LinkedHashMap<LootType, List<BPos>>() {{
		}});
		STRUCTURE_SIZE.put("bridge_piece.nbt", new BPos(5, 6, 4));
		STRUCTURE_TO_LOOT.put("bridge_steep_stairs", new LinkedHashMap<LootType, List<BPos>>() {{
		}});
		STRUCTURE_SIZE.put("bridge_steep_stairs.nbt", new BPos(5, 7, 4));
		STRUCTURE_TO_LOOT.put("fat_tower_base", new LinkedHashMap<LootType, List<BPos>>() {{
		}});
		STRUCTURE_SIZE.put("fat_tower_base.nbt", new BPos(13, 4, 13));
		STRUCTURE_TO_LOOT.put("fat_tower_middle", new LinkedHashMap<LootType, List<BPos>>() {{
			computeIfAbsent(LootType.SENTRY, k -> new ArrayList<>()).add(new BPos(2, 2, 6));
			computeIfAbsent(LootType.SENTRY, k -> new ArrayList<>()).add(new BPos(10, 2, 6));
			computeIfAbsent(LootType.SENTRY, k -> new ArrayList<>()).add(new BPos(6, 6, 2));
			computeIfAbsent(LootType.SENTRY, k -> new ArrayList<>()).add(new BPos(6, 6, 10));
		}});
		STRUCTURE_SIZE.put("fat_tower_middle.nbt", new BPos(13, 8, 13));
		STRUCTURE_TO_LOOT.put("fat_tower_top", new LinkedHashMap<LootType, List<BPos>>() {{
			computeIfAbsent(LootType.CHEST, k -> new ArrayList<>()).add(new BPos(3, 2, 11));
			computeIfAbsent(LootType.CHEST, k -> new ArrayList<>()).add(new BPos(5, 2, 13));
		}});
		STRUCTURE_SIZE.put("fat_tower_top.nbt", new BPos(17, 6, 17));
		STRUCTURE_TO_LOOT.put("second_floor_1", new LinkedHashMap<LootType, List<BPos>>() {{
		}});
		STRUCTURE_SIZE.put("second_floor_1.nbt", new BPos(12, 8, 12));
		STRUCTURE_TO_LOOT.put("second_floor_2", new LinkedHashMap<LootType, List<BPos>>() {{
			computeIfAbsent(LootType.SENTRY, k -> new ArrayList<>()).add(new BPos(8, 5, 6));
		}});
		STRUCTURE_SIZE.put("second_floor_2.nbt", new BPos(12, 8, 12));
		STRUCTURE_TO_LOOT.put("second_roof", new LinkedHashMap<LootType, List<BPos>>() {{
		}});
		STRUCTURE_SIZE.put("second_roof.nbt", new BPos(14, 2, 14));
		STRUCTURE_TO_LOOT.put("ship", new LinkedHashMap<LootType, List<BPos>>() {{
			computeIfAbsent(LootType.SENTRY, k -> new ArrayList<>()).add(new BPos(6, 4, 8));
			computeIfAbsent(LootType.SENTRY, k -> new ArrayList<>()).add(new BPos(8, 6, 27));
			computeIfAbsent(LootType.SENTRY, k -> new ArrayList<>()).add(new BPos(4, 11, 27));
			computeIfAbsent(LootType.CHEST, k -> new ArrayList<>()).add(new BPos(5, 5, 7));
			computeIfAbsent(LootType.CHEST, k -> new ArrayList<>()).add(new BPos(7, 5, 7));
			computeIfAbsent(LootType.ELYTRA, k -> new ArrayList<>()).add(new BPos(6, 5, 7));
		}});
		STRUCTURE_SIZE.put("ship.nbt", new BPos(13, 24, 29));
		STRUCTURE_TO_LOOT.put("third_floor_1", new LinkedHashMap<LootType, List<BPos>>() {{
		}});
		STRUCTURE_SIZE.put("third_floor_1.nbt", new BPos(14, 8, 14));
		STRUCTURE_TO_LOOT.put("third_floor_2", new LinkedHashMap<LootType, List<BPos>>() {{
			computeIfAbsent(LootType.SENTRY, k -> new ArrayList<>()).add(new BPos(2, 5, 2));
			computeIfAbsent(LootType.SENTRY, k -> new ArrayList<>()).add(new BPos(11, 5, 2));
			computeIfAbsent(LootType.CHEST, k -> new ArrayList<>()).add(new BPos(6, 6, 2));
		}});
		STRUCTURE_SIZE.put("third_floor_2.nbt", new BPos(14, 8, 14));
		STRUCTURE_TO_LOOT.put("third_roof", new LinkedHashMap<LootType, List<BPos>>() {{
		}});
		STRUCTURE_SIZE.put("third_roof.nbt", new BPos(16, 2, 16));
		STRUCTURE_TO_LOOT.put("tower_base", new LinkedHashMap<LootType, List<BPos>>() {{
		}});
		STRUCTURE_SIZE.put("tower_base.nbt", new BPos(7, 7, 7));
		STRUCTURE_TO_LOOT.put("tower_floor", new LinkedHashMap<LootType, List<BPos>>() {{
		}});
		STRUCTURE_SIZE.put("tower_floor.nbt", new BPos(7, 4, 7));
		STRUCTURE_TO_LOOT.put("tower_piece", new LinkedHashMap<LootType, List<BPos>>() {{
		}});
		STRUCTURE_SIZE.put("tower_piece.nbt", new BPos(7, 4, 7));
		STRUCTURE_TO_LOOT.put("tower_top", new LinkedHashMap<LootType, List<BPos>>() {{
			computeIfAbsent(LootType.SENTRY, k -> new ArrayList<>()).add(new BPos(4, 3, 4));
		}});
		STRUCTURE_SIZE.put("tower_top.nbt", new BPos(9, 5, 9));
	}

//import nbtlib
//from pathlib import *
//import sys
//from collections import defaultdict
//
//p = Path(r'.').glob('**/*.nbt')
//files = [x for x in p if x.is_file()]
//for file in files:
//    print(f'STRUCTURE_TO_LOOT.put("{file.name.rstrip(".nbt")}", new LinkedHashMap<LootType, List<BPos>>() {{{{')
//    nbt_file=nbtlib.load(file)
//    root=nbt_file.root
//    if "blocks" not in root.keys():
//        print(f"Missing blocks key for {file}")
//        sys.exit(1)
//    d=defaultdict(list)
//    for block in root["blocks"]:
//        if "nbt" in block.keys() and "pos" in block.keys():
//            pos=block["pos"]
//            nbt=block["nbt"]
//            if "metadata" in nbt:
//                d[nbt["metadata"].upper()].append(f'new BPos({",".join(map(str,map(int,pos)))})')
//
//    for k,v in d.items():
//       for pos in v:
//            print(f'	computeIfAbsent(LootType.{k}, k -> new ArrayList<>()).add({pos});')
//    print('}});')
//    if "size" in root.keys():
//        print(f'STRUCTURE_SIZE.put("{file}",new BPos({",".join(map(str,map(int,root["size"])))}));')
//    else:
//        print(f"Missing size key for {file.name.rstrip('.nbt')}")
//        sys.exit(1)
}
