package kaptainwutax.featureutils.structure.generator;

import kaptainwutax.featureutils.loot.LootTable;
import kaptainwutax.featureutils.loot.MCLootTables;
import kaptainwutax.featureutils.loot.item.Item;
import kaptainwutax.featureutils.loot.item.Items;
import kaptainwutax.featureutils.structure.EndCity;
import kaptainwutax.mcutils.rand.ChunkRand;
import kaptainwutax.mcutils.util.block.BlockBox;
import kaptainwutax.mcutils.util.block.BlockMirror;
import kaptainwutax.mcutils.util.block.BlockRotation;
import kaptainwutax.mcutils.util.data.Pair;
import kaptainwutax.mcutils.util.pos.BPos;
import kaptainwutax.mcutils.util.pos.CPos;
import kaptainwutax.mcutils.version.MCVersion;
import kaptainwutax.terrainutils.ChunkGenerator;

import java.util.*;
import java.util.stream.Collectors;

public class EndCityGenerator {
	private final MCVersion version;
	private final List<Template> globalPieces = new ArrayList<>();

	public EndCityGenerator(MCVersion version) {
		this.version = version;
	}

	public MCVersion getVersion() {
		return version;
	}

	public boolean generate(ChunkGenerator generator, CPos cPos) {
		return this.generate(generator, cPos, new ChunkRand());
	}

	public boolean generate(ChunkGenerator generator, int chunkX, int chunkZ) {
		return this.generate(generator, chunkX, chunkZ, new ChunkRand());
	}

	public boolean generate(ChunkGenerator generator, CPos cPos, ChunkRand rand) {
		return this.generate(generator, cPos.getX(), cPos.getZ(), rand);
	}

	public boolean generate(ChunkGenerator generator, int chunkX, int chunkZ, ChunkRand rand) {
		rand.setCarverSeed(generator.getWorldSeed(), chunkX, chunkZ, this.getVersion());
		int y = EndCity.getAverageYPosition(generator, chunkX, chunkZ);
		if (y < 60) return false;
		BlockRotation rotation = BlockRotation.getRandom(rand);
		BPos start = new BPos(chunkX * 16 + 8, y, chunkZ * 16 + 8);
		this.start(start, rotation, this.globalPieces, rand);
		return true;
	}

	/**
	 * Get the chest block pos, should always be called after generate else will return an empty list
	 * @return list of lootype (warning some might not be loot, like Sentry) and bpos
	 */
	public List<Pair<LootType, BPos>> getChestsPos() {
		List<Pair<LootType, BPos>> res = new ArrayList<>();
		for (Template template:globalPieces){
			LinkedHashMap<LootType, List<BPos>> loot=STRUCTURE_TO_LOOT.get(template.getName());
			if (loot==null){
				System.err.println("Missing loot for "+template.getName());
				continue;
			}
			for (Map.Entry<LootType,List<BPos>> entry:loot.entrySet()){
				LootType lootType=entry.getKey();
				for (BPos offset:entry.getValue()){
					// we know for a fact that the pos is below the box size so we don't do the check
					BPos lootPos = template.box.getRotated(template.getRotation()).getInside(offset, template.getRotation());
					res.add(new Pair<>(lootType,lootPos));
				}
			}
		}
		return res;
	}

	public List<Pair<LootType, CPos>> getChestsChunkPos() {
		return this.getChestsPos().stream().map(e-> new Pair<>(e.getFirst(), e.getSecond().toChunkPos())).collect(Collectors.toList());
	}

	public boolean hasShip(){
		return globalPieces.stream().anyMatch(e-> e.getName().equals("ship"));
	}

	private static Template calculateTemplate(Template previous, BPos pos, String name, BlockRotation rotation, boolean overwrite) {
		Template template = new Template(name, previous.pos, rotation, overwrite);

		BPos transform1 = pos.transform(BlockMirror.NONE, previous.getRotation(), BPos.ORIGIN);
		BPos transform2 = BPos.ORIGIN.transform(BlockMirror.NONE, template.getRotation(), BPos.ORIGIN);
		BPos transform = transform1.subtract(transform2);
		template.move(transform);
		return template;
	}

	private static Template generateAndAdd(List<Template> pieces, Template previous, BPos pos, String name, BlockRotation rotation, boolean overwrite) {
		Template template = calculateTemplate(previous, pos, name, rotation, overwrite);
		pieces.add(template);
		return template;
	}

	public boolean start(BPos start, BlockRotation rotation, List<Template> pieces, ChunkRand rand) {
		TOWER_BRIDGE_GENERATOR.init();
		Template base = new Template("base_floor", start, rotation, true);
		pieces.add(base);
		base = generateAndAdd(pieces, base, new BPos(-1, 0, -1), "second_floor_1", rotation, false);
		base = generateAndAdd(pieces, base, new BPos(-1, 4, -1), "third_floor_1", rotation, false);
		base = generateAndAdd(pieces, base, new BPos(-1, 8, -1), "third_roof", rotation, true);
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
					Template collisions = piece.findCollisionPiece(pieces);
					if (collisions != null && collisions.genDepth != template.genDepth) {
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
		private BPos pos;
		private final BlockRotation rotation;
		private final boolean overwrite;
		private int genDepth = 0;
		private BlockBox box;

		public Template(String name, BPos pos, BlockRotation rotation, boolean overwrite) {
			this.name = name;
			this.pos = pos;
			this.rotation = rotation;
			this.overwrite = overwrite;
			this.box = BlockBox.getBoundingBox(pos, rotation, BPos.ORIGIN, BlockMirror.NONE, Objects.requireNonNull(STRUCTURE_SIZE.get(name)));
		}

		public void setGenDepth(int genDepth) {
			this.genDepth = genDepth;
		}

		public int getGenDepth() {
			return genDepth;
		}

		public Template findCollisionPiece(List<Template> templates) {
			for (Template template : templates) {
				if (template.getBox().intersects(this.box)) {
					return template;
				}
			}
			return null;
		}

		public void move(BPos by) {
			this.pos = this.pos.add(by);
			this.box.move(by.getX(), by.getY(), by.getZ());
		}

		public BlockBox getBox() {
			return box;
		}

		public void setBox(BlockBox box) {
			this.box = box;
		}

		public void setPos(BPos pos) {
			this.pos = pos;
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

	private static final List<Pair<BlockRotation, BPos>> FAT_TOWER_BRIDGES = Arrays.asList(
			new Pair<>(BlockRotation.NONE, new BPos(4, -1, 0)),
			new Pair<>(BlockRotation.CLOCKWISE_90, new BPos(12, -1, 4)),
			new Pair<>(BlockRotation.COUNTERCLOCKWISE_90, new BPos(0, -1, 8)),
			new Pair<>(BlockRotation.CLOCKWISE_180, new BPos(8, -1, 12))
	);

	private static final Generator FAT_TOWER_GENERATOR = new Generator() {
		@Override
		public void init() {

		}

		@Override
		public boolean generate(int depth, Template current, BPos pos, List<Template> pieces, ChunkRand rand) {
			BlockRotation rotation = current.getRotation();
			Template base = generateAndAdd(pieces, current, new BPos(-3, 4, -3), "fat_tower_base", rotation, true);
			base = generateAndAdd(pieces, base, new BPos(0, 4, 0), "fat_tower_middle", rotation, true);
			for (int floor = 0; floor < 2 && rand.nextInt(3) != 0; floor++) {
				base = generateAndAdd(pieces, base, new BPos(0, 8, 0), "fat_tower_middle", rotation, true);
				for (Pair<BlockRotation, BPos> towerBridge : FAT_TOWER_BRIDGES) {
					if (rand.nextBoolean()) {
						Template bridge = generateAndAdd(pieces, base, towerBridge.getSecond(), "bridge_end", rotation.getRotated(towerBridge.getFirst()), true);
						generateRecursively(TOWER_BRIDGE_GENERATOR, depth + 1, bridge, null, pieces, rand);
					}
				}
			}
			generateAndAdd(pieces, base, new BPos(-2, 8, -2), "fat_tower_top", rotation, true);
			return true;
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
				Template base = generateAndAdd(pieces, current, pos, "base_floor", rotation, true);
				int size = rand.nextInt(3);
				if (size == 0) {
					Template roof = generateAndAdd(pieces, base, new BPos(-1, 4, -1), "base_roof", rotation, true);
				} else if (size == 1) {
					Template secondFloor = generateAndAdd(pieces, base, new BPos(-1, 0, -1), "second_floor_2", rotation, false);
					Template secondRoof = generateAndAdd(pieces, secondFloor, new BPos(-1, 0, -1), "second_floor_2", rotation, false);
					generateRecursively(TOWER_GENERATOR, depth + 1, secondRoof, null, pieces, rand);
				} else {
					Template secondFloor = generateAndAdd(pieces, base, new BPos(-1, 0, -1), "second_floor_2", rotation, false);
					Template thirdFloor = generateAndAdd(pieces, secondFloor, new BPos(-1, 4, -1), "third_floor_2", rotation, false);
					Template thirdRoof = generateAndAdd(pieces, thirdFloor, new BPos(-1, 8, -1), "third_roof", rotation, true);
					generateRecursively(TOWER_GENERATOR, depth + 1, thirdRoof, null, pieces, rand);
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
			BlockRotation rotation = current.getRotation();
			int size = rand.nextInt(4) + 1;
			Template base = generateAndAdd(pieces, current, new BPos(0, 0, -4), "bridge_piece", rotation, true);
			base.setGenDepth(-1);
			int y = 0;
			for (int floor = 0; floor < size; floor++) {
				if (rand.nextBoolean()) {
					base = generateAndAdd(pieces, base, new BPos(0, y, -4), "bridge_piece", rotation, true);
					y = 0;
				} else {
					if (rand.nextBoolean()) {
						base = generateAndAdd(pieces, base, new BPos(0, y, -4), "bridge_steep_stairs", rotation, true);

					} else {
						base = generateAndAdd(pieces, base, new BPos(0, y, -8), "bridge_gentle_stairs", rotation, true);
					}
					y = 4;
				}
			}
			if (!this.shipCreated && rand.nextInt(10 - depth) == 0) {
				generateAndAdd(pieces, base, new BPos(-8 + rand.nextInt(8), y, -70 + rand.nextInt(10)), "ship", rotation, true);
				this.shipCreated = true;
			} else if (!generateRecursively(HOUSE_TOWER_GENERATOR, depth + 1, base, new BPos(-3, y + 1, -11), pieces, rand)) {
				return false;
			}
			base = generateAndAdd(pieces, base, new BPos(4, y, 0), "bridge_end", rotation.getRotated(BlockRotation.CLOCKWISE_180), true);
			base.setGenDepth(-1);
			return true;
		}
	};
	private static final List<Pair<BlockRotation, BPos>> TOWER_BRIDGES = Arrays.asList(
			new Pair<>(BlockRotation.NONE, new BPos(1, -1, 0)),
			new Pair<>(BlockRotation.CLOCKWISE_90, new BPos(6, -1, 1)),
			new Pair<>(BlockRotation.COUNTERCLOCKWISE_90, new BPos(0, -1, 5)),
			new Pair<>(BlockRotation.CLOCKWISE_180, new BPos(5, -1, 6))
	);

	private static final Generator TOWER_GENERATOR = new Generator() {

		@Override
		public void init() {

		}

		@Override
		public boolean generate(int depth, Template current, BPos pos, List<Template> pieces, ChunkRand rand) {
			BlockRotation rotation = current.getRotation();

			Template base = generateAndAdd(pieces, current, new BPos(3 + rand.nextInt(2), -3, 3 + rand.nextInt(2)), "tower_base", rotation, true);
			base = generateAndAdd(pieces, base, new BPos(0, 7, 0), "tower_piece", rotation, true);
			Template currentFloor = rand.nextInt(3) == 0 ? base : null;
			int size = rand.nextInt(3) + 1;
			for (int floor = 0; floor < size; floor++) {
				base = generateAndAdd(pieces, base, new BPos(0, 4, 0), "tower_piece", rotation, true);
				if (floor < size - 1 && rand.nextBoolean()) {
					currentFloor = base;
				}
			}
			if (currentFloor != null) {
				for (Pair<BlockRotation, BPos> towerBridge : TOWER_BRIDGES) {
					if (rand.nextBoolean()) {
						Template bridge = generateAndAdd(pieces, base, towerBridge.getSecond(), "bridge_end", rotation.getRotated(towerBridge.getFirst()), true);
						generateRecursively(TOWER_BRIDGE_GENERATOR, depth + 1, bridge, null, pieces, rand);
					}
				}
			} else if (depth != 7) {
				return generateRecursively(FAT_TOWER_GENERATOR, depth + 1, base, null, pieces, rand);
			}
			generateAndAdd(pieces, base, new BPos(-1, 4, -1), "tower_top", rotation, true);
			return true;
		}
	};

	interface Generator {
		void init();

		boolean generate(int depth, Template current, BPos pos, List<Template> pieces, ChunkRand rand);
	}


	public enum LootType {
		BASE_FLOOR_SENTRY_1(null, Items.SHULKER_SHELL),
		BASE_FLOOR_SENTRY_2(null, Items.SHULKER_SHELL),
		FAT_TOWER_MIDDLE_SENTRY_1(null, Items.SHULKER_SHELL),
		FAT_TOWER_MIDDLE_SENTRY_2(null, Items.SHULKER_SHELL),
		FAT_TOWER_MIDDLE_SENTRY_3(null, Items.SHULKER_SHELL),
		FAT_TOWER_MIDDLE_SENTRY_4(null, Items.SHULKER_SHELL),
		SECOND_FLOOR_SENTRY(null, Items.SHULKER_SHELL),
		SHIP_SENTRY_1(null, Items.SHULKER_SHELL),
		SHIP_SENTRY_2(null, Items.SHULKER_SHELL),
		SHIP_SENTRY_3(null, Items.SHULKER_SHELL),
		THIRD_FLOOR_SENTRY_1(null, Items.SHULKER_SHELL),
		THIRD_FLOOR_SENTRY_2(null, Items.SHULKER_SHELL),
		TOWER_TOP_SENTRY(null, Items.SHULKER_SHELL),

		FAT_TOWER_TOP_CHEST_1(MCLootTables.END_CITY_TREASURE_CHEST, Items.SHULKER_SHELL),
		FAT_TOWER_TOP_CHEST_2(MCLootTables.END_CITY_TREASURE_CHEST, Items.SHULKER_SHELL),
		THIRD_FLOOR_CHEST(MCLootTables.END_CITY_TREASURE_CHEST, Items.SHULKER_SHELL),
		SHIP_CHEST_1(MCLootTables.END_CITY_TREASURE_CHEST, Items.SHULKER_SHELL),
		SHIP_CHEST_2(MCLootTables.END_CITY_TREASURE_CHEST, Items.SHULKER_SHELL),

		SHIP_ELYTRA(null, Items.ELYTRA)
		;
		
		public final LootTable lootTable;
		public final Item item;

		LootType(LootTable lootTable, Item item) {
			this.lootTable = lootTable;
			this.item = item;
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
			computeIfAbsent(LootType.BASE_FLOOR_SENTRY_1, k -> new ArrayList<>()).add(new BPos(3, 2, 9));
			computeIfAbsent(LootType.BASE_FLOOR_SENTRY_2, k -> new ArrayList<>()).add(new BPos(6, 2, 9));
		}});
		STRUCTURE_SIZE.put("base_floor", new BPos(10, 4, 10));
		STRUCTURE_TO_LOOT.put("base_roof", new LinkedHashMap<LootType, List<BPos>>() {{
		}});
		STRUCTURE_SIZE.put("base_roof", new BPos(12, 2, 12));
		STRUCTURE_TO_LOOT.put("bridge_end", new LinkedHashMap<LootType, List<BPos>>() {{
		}});
		STRUCTURE_SIZE.put("bridge_end", new BPos(5, 6, 2));
		STRUCTURE_TO_LOOT.put("bridge_gentle_stairs", new LinkedHashMap<LootType, List<BPos>>() {{
		}});
		STRUCTURE_SIZE.put("bridge_gentle_stairs", new BPos(5, 7, 8));
		STRUCTURE_TO_LOOT.put("bridge_piece", new LinkedHashMap<LootType, List<BPos>>() {{
		}});
		STRUCTURE_SIZE.put("bridge_piece", new BPos(5, 6, 4));
		STRUCTURE_TO_LOOT.put("bridge_steep_stairs", new LinkedHashMap<LootType, List<BPos>>() {{
		}});
		STRUCTURE_SIZE.put("bridge_steep_stairs", new BPos(5, 7, 4));
		STRUCTURE_TO_LOOT.put("fat_tower_base", new LinkedHashMap<LootType, List<BPos>>() {{
		}});
		STRUCTURE_SIZE.put("fat_tower_base", new BPos(13, 4, 13));
		STRUCTURE_TO_LOOT.put("fat_tower_middle", new LinkedHashMap<LootType, List<BPos>>() {{
			computeIfAbsent(LootType.FAT_TOWER_MIDDLE_SENTRY_1, k -> new ArrayList<>()).add(new BPos(2, 2, 6));
			computeIfAbsent(LootType.FAT_TOWER_MIDDLE_SENTRY_2, k -> new ArrayList<>()).add(new BPos(10, 2, 6));
			computeIfAbsent(LootType.FAT_TOWER_MIDDLE_SENTRY_3, k -> new ArrayList<>()).add(new BPos(6, 6, 2));
			computeIfAbsent(LootType.FAT_TOWER_MIDDLE_SENTRY_4, k -> new ArrayList<>()).add(new BPos(6, 6, 10));
		}});
		STRUCTURE_SIZE.put("fat_tower_middle", new BPos(13, 8, 13));
		STRUCTURE_TO_LOOT.put("fat_tower_top", new LinkedHashMap<LootType, List<BPos>>() {{
			computeIfAbsent(LootType.FAT_TOWER_TOP_CHEST_1, k -> new ArrayList<>()).add(new BPos(3, 2, 11));
			computeIfAbsent(LootType.FAT_TOWER_TOP_CHEST_2, k -> new ArrayList<>()).add(new BPos(5, 2, 13));
		}});
		STRUCTURE_SIZE.put("fat_tower_top", new BPos(17, 6, 17));
		STRUCTURE_TO_LOOT.put("second_floor_1", new LinkedHashMap<LootType, List<BPos>>() {{
		}});
		STRUCTURE_SIZE.put("second_floor_1", new BPos(12, 8, 12));
		STRUCTURE_TO_LOOT.put("second_floor_2", new LinkedHashMap<LootType, List<BPos>>() {{
			computeIfAbsent(LootType.SECOND_FLOOR_SENTRY, k -> new ArrayList<>()).add(new BPos(8, 5, 6));
		}});
		STRUCTURE_SIZE.put("second_floor_2", new BPos(12, 8, 12));
		STRUCTURE_TO_LOOT.put("second_roof", new LinkedHashMap<LootType, List<BPos>>() {{
		}});
		STRUCTURE_SIZE.put("second_roof", new BPos(14, 2, 14));
		STRUCTURE_TO_LOOT.put("ship", new LinkedHashMap<LootType, List<BPos>>() {{
			computeIfAbsent(LootType.SHIP_SENTRY_1, k -> new ArrayList<>()).add(new BPos(6, 4, 8));
			computeIfAbsent(LootType.SHIP_SENTRY_2, k -> new ArrayList<>()).add(new BPos(8, 6, 27));
			computeIfAbsent(LootType.SHIP_SENTRY_3, k -> new ArrayList<>()).add(new BPos(4, 11, 27));
			computeIfAbsent(LootType.SHIP_CHEST_1, k -> new ArrayList<>()).add(new BPos(5, 5, 7));
			computeIfAbsent(LootType.SHIP_CHEST_2, k -> new ArrayList<>()).add(new BPos(7, 5, 7));
			computeIfAbsent(LootType.SHIP_ELYTRA, k -> new ArrayList<>()).add(new BPos(6, 5, 7));
		}});
		STRUCTURE_SIZE.put("ship", new BPos(13, 24, 29));
		STRUCTURE_TO_LOOT.put("third_floor_1", new LinkedHashMap<LootType, List<BPos>>() {{
		}});
		STRUCTURE_SIZE.put("third_floor_1", new BPos(14, 8, 14));
		STRUCTURE_TO_LOOT.put("third_floor_2", new LinkedHashMap<LootType, List<BPos>>() {{
			computeIfAbsent(LootType.THIRD_FLOOR_SENTRY_1, k -> new ArrayList<>()).add(new BPos(2, 5, 2));
			computeIfAbsent(LootType.THIRD_FLOOR_SENTRY_2, k -> new ArrayList<>()).add(new BPos(11, 5, 2));
			computeIfAbsent(LootType.THIRD_FLOOR_CHEST, k -> new ArrayList<>()).add(new BPos(6, 6, 2));
		}});
		STRUCTURE_SIZE.put("third_floor_2", new BPos(14, 8, 14));
		STRUCTURE_TO_LOOT.put("third_roof", new LinkedHashMap<LootType, List<BPos>>() {{
		}});
		STRUCTURE_SIZE.put("third_roof", new BPos(16, 2, 16));
		STRUCTURE_TO_LOOT.put("tower_base", new LinkedHashMap<LootType, List<BPos>>() {{
		}});
		STRUCTURE_SIZE.put("tower_base", new BPos(7, 7, 7));
		STRUCTURE_TO_LOOT.put("tower_floor", new LinkedHashMap<LootType, List<BPos>>() {{
		}});
		STRUCTURE_SIZE.put("tower_floor", new BPos(7, 4, 7));
		STRUCTURE_TO_LOOT.put("tower_piece", new LinkedHashMap<LootType, List<BPos>>() {{
		}});
		STRUCTURE_SIZE.put("tower_piece", new BPos(7, 4, 7));
		STRUCTURE_TO_LOOT.put("tower_top", new LinkedHashMap<LootType, List<BPos>>() {{
			computeIfAbsent(LootType.TOWER_TOP_SENTRY, k -> new ArrayList<>()).add(new BPos(4, 3, 4));
		}});
		STRUCTURE_SIZE.put("tower_top", new BPos(9, 5, 9));
	}

//import nbtlib
//from pathlib import *
//import sys
//from collections import defaultdict
//
//p = Path(r'.').glob('**/*')
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
