package kaptainwutax.featureutils.structure;


import kaptainwutax.biomeutils.Biome;
import kaptainwutax.featureutils.loot.LootContext;
import kaptainwutax.featureutils.loot.LootTable;
import kaptainwutax.featureutils.loot.MCLootTables;
import kaptainwutax.featureutils.loot.item.ItemStack;
import kaptainwutax.mcutils.rand.ChunkRand;
import kaptainwutax.mcutils.state.Dimension;
import kaptainwutax.mcutils.util.block.BlockBox;
import kaptainwutax.mcutils.util.block.BlockMirror;
import kaptainwutax.mcutils.util.block.BlockRotation;
import kaptainwutax.mcutils.util.pos.BPos;
import kaptainwutax.mcutils.util.pos.CPos;
import kaptainwutax.mcutils.util.pos.RPos;
import kaptainwutax.mcutils.version.MCVersion;
import kaptainwutax.mcutils.version.VersionMap;

import java.util.*;

public class Shipwreck extends UniformStructure<Shipwreck> {
	public static final VersionMap<RegionStructure.Config> CONFIGS = new VersionMap<RegionStructure.Config>()
			.add(MCVersion.v1_13, new RegionStructure.Config(15, 8, 165745295))
			.add(MCVersion.v1_13_1, new RegionStructure.Config(16, 8, 165745295))
			.add(MCVersion.v1_16, new RegionStructure.Config(24, 4, 165745295));
	private static final String[] STRUCTURE_LOCATION_BEACHED = new String[] {
			"with_mast",
			"sideways_full",
			"sideways_fronthalf",
			"sideways_backhalf",
			"rightsideup_full",
			"rightsideup_fronthalf",
			"rightsideup_backhalf",
			"with_mast_degraded",
			"rightsideup_full_degraded",
			"rightsideup_fronthalf_degraded",
			"rightsideup_backhalf_degraded"
	};
	private static final String[] STRUCTURE_LOCATION_OCEAN = new String[] {
			"with_mast",
			"upsidedown_full",
			"upsidedown_fronthalf",
			"upsidedown_backhalf",
			"sideways_full",
			"sideways_fronthalf",
			"sideways_backhalf",
			"rightsideup_full",
			"rightsideup_fronthalf",
			"rightsideup_backhalf",
			"with_mast_degraded",
			"upsidedown_full_degraded",
			"upsidedown_fronthalf_degraded",
			"upsidedown_backhalf_degraded",
			"sideways_full_degraded",
			"sideways_fronthalf_degraded",
			"sideways_backhalf_degraded",
			"rightsideup_full_degraded",
			"rightsideup_fronthalf_degraded",
			"rightsideup_backhalf_degraded"
	};
	private static final HashMap<String, LinkedHashMap<LootType, BPos>> STRUCTURE_TO_LOOT = new HashMap<>();
	private static final HashMap<String, BPos> STRUCTURE_SIZE = new HashMap<>();

	static {
		// we are y+1
		STRUCTURE_TO_LOOT.put("rightsideup_backhalf", new LinkedHashMap<LootType, BPos>() {{
			put(LootType.MAP_CHEST, new BPos(5, 3, 6));
			put(LootType.TREASURE_CHEST, new BPos(6, 5, 12));
		}});
		STRUCTURE_SIZE.put("rightsideup_backhalf", new BPos(9, 9, 16));
		STRUCTURE_TO_LOOT.put("rightsideup_backhalf_degraded", new LinkedHashMap<LootType, BPos>() {{
			put(LootType.MAP_CHEST, new BPos(5, 3, 6));
			put(LootType.TREASURE_CHEST, new BPos(6, 5, 12));
		}});
		STRUCTURE_SIZE.put("rightsideup_backhalf_degraded", new BPos(9, 9, 16));
		STRUCTURE_TO_LOOT.put("rightsideup_fronthalf", new LinkedHashMap<LootType, BPos>() {{
			put(LootType.SUPPLY_CHEST, new BPos(4, 3, 8));
		}});
		STRUCTURE_SIZE.put("rightsideup_fronthalf", new BPos(9, 9, 24));
		STRUCTURE_TO_LOOT.put("rightsideup_fronthalf_degraded", new LinkedHashMap<LootType, BPos>() {{
			put(LootType.SUPPLY_CHEST, new BPos(4, 3, 8));
		}});
		STRUCTURE_SIZE.put("rightsideup_fronthalf_degraded", new BPos(9, 9, 24));
		STRUCTURE_TO_LOOT.put("rightsideup_full", new LinkedHashMap<LootType, BPos>() {{
			put(LootType.SUPPLY_CHEST, new BPos(4, 3, 8));
			put(LootType.MAP_CHEST, new BPos(5, 3, 18));
			put(LootType.TREASURE_CHEST, new BPos(6, 5, 24));
		}});
		STRUCTURE_SIZE.put("rightsideup_full", new BPos(9, 9, 28));
		STRUCTURE_TO_LOOT.put("rightsideup_full_degraded", new LinkedHashMap<LootType, BPos>() {{
			put(LootType.SUPPLY_CHEST, new BPos(4, 3, 8));
			put(LootType.MAP_CHEST, new BPos(5, 3, 18));
			put(LootType.TREASURE_CHEST, new BPos(6, 5, 24));
		}});
		STRUCTURE_SIZE.put("rightsideup_full_degraded", new BPos(9, 9, 28));
		STRUCTURE_TO_LOOT.put("sideways_backhalf", new LinkedHashMap<LootType, BPos>() {{
			put(LootType.TREASURE_CHEST, new BPos(3, 3, 13));
			put(LootType.MAP_CHEST, new BPos(6, 4, 8));
		}});
		STRUCTURE_SIZE.put("sideways_backhalf", new BPos(9, 9, 17));
		STRUCTURE_TO_LOOT.put("sideways_backhalf_degraded", new LinkedHashMap<LootType, BPos>() {{
			put(LootType.TREASURE_CHEST, new BPos(3, 3, 13));
			put(LootType.MAP_CHEST, new BPos(6, 4, 8));
		}});
		STRUCTURE_SIZE.put("sideways_backhalf_degraded", new BPos(9, 9, 17));
		STRUCTURE_TO_LOOT.put("sideways_fronthalf", new LinkedHashMap<LootType, BPos>() {{
			put(LootType.SUPPLY_CHEST, new BPos(5, 4, 8));
		}});
		STRUCTURE_SIZE.put("sideways_fronthalf", new BPos(9, 9, 24));
		STRUCTURE_TO_LOOT.put("sideways_fronthalf_degraded", new LinkedHashMap<LootType, BPos>() {{
			put(LootType.SUPPLY_CHEST, new BPos(5, 4, 8));
		}});
		STRUCTURE_SIZE.put("sideways_fronthalf_degraded", new BPos(9, 9, 24));
		STRUCTURE_TO_LOOT.put("sideways_full", new LinkedHashMap<LootType, BPos>() {{
			put(LootType.TREASURE_CHEST, new BPos(3, 3, 24));
			put(LootType.SUPPLY_CHEST, new BPos(5, 4, 8));
			put(LootType.MAP_CHEST, new BPos(6, 4, 19));
		}});
		STRUCTURE_SIZE.put("sideways_full", new BPos(9, 9, 28));
		STRUCTURE_TO_LOOT.put("sideways_full_degraded", new LinkedHashMap<LootType, BPos>() {{
			put(LootType.TREASURE_CHEST, new BPos(3, 3, 24));
			put(LootType.SUPPLY_CHEST, new BPos(5, 4, 8));
			put(LootType.MAP_CHEST, new BPos(6, 4, 19));
		}});
		STRUCTURE_SIZE.put("sideways_full_degraded", new BPos(9, 9, 28));
		STRUCTURE_TO_LOOT.put("upsidedown_backhalf", new LinkedHashMap<LootType, BPos>() {{
			put(LootType.TREASURE_CHEST, new BPos(2, 3, 12));
			put(LootType.MAP_CHEST, new BPos(3, 6, 5));
		}});
		STRUCTURE_SIZE.put("upsidedown_backhalf", new BPos(9, 9, 16));
		STRUCTURE_TO_LOOT.put("upsidedown_backhalf_degraded", new LinkedHashMap<LootType, BPos>() {{
			put(LootType.TREASURE_CHEST, new BPos(2, 3, 12));
			put(LootType.MAP_CHEST, new BPos(3, 6, 5));
		}});
		STRUCTURE_SIZE.put("upsidedown_backhalf_degraded", new BPos(9, 9, 16));
		STRUCTURE_TO_LOOT.put("upsidedown_fronthalf", new LinkedHashMap<LootType, BPos>() {{
			put(LootType.MAP_CHEST, new BPos(3, 6, 17));
			put(LootType.SUPPLY_CHEST, new BPos(4, 6, 8));
		}});
		STRUCTURE_SIZE.put("upsidedown_fronthalf", new BPos(9, 9, 22));
		STRUCTURE_TO_LOOT.put("upsidedown_fronthalf_degraded", new LinkedHashMap<LootType, BPos>() {{
			put(LootType.MAP_CHEST, new BPos(3, 6, 17));
			put(LootType.SUPPLY_CHEST, new BPos(4, 6, 8));
		}});
		STRUCTURE_SIZE.put("upsidedown_fronthalf_degraded", new BPos(9, 9, 22));
		STRUCTURE_TO_LOOT.put("upsidedown_full", new LinkedHashMap<LootType, BPos>() {{
			put(LootType.TREASURE_CHEST, new BPos(2, 3, 24));
			put(LootType.MAP_CHEST, new BPos(3, 6, 17));
			put(LootType.SUPPLY_CHEST, new BPos(4, 6, 8));
		}});
		STRUCTURE_SIZE.put("upsidedown_full", new BPos(9, 9, 28));
		STRUCTURE_TO_LOOT.put("upsidedown_full_degraded", new LinkedHashMap<LootType, BPos>() {{
			put(LootType.TREASURE_CHEST, new BPos(2, 3, 24));
			put(LootType.MAP_CHEST, new BPos(3, 6, 17));
			put(LootType.SUPPLY_CHEST, new BPos(4, 6, 8));
		}});
		STRUCTURE_SIZE.put("upsidedown_full_degraded", new BPos(9, 9, 28));
		STRUCTURE_TO_LOOT.put("with_mast", new LinkedHashMap<LootType, BPos>() {{
			put(LootType.SUPPLY_CHEST, new BPos(4, 3, 9));
			put(LootType.MAP_CHEST, new BPos(5, 3, 18));
			put(LootType.TREASURE_CHEST, new BPos(6, 5, 24));
		}});
		STRUCTURE_SIZE.put("with_mast", new BPos(9, 21, 28));
		STRUCTURE_TO_LOOT.put("with_mast_degraded", new LinkedHashMap<LootType, BPos>() {{
			put(LootType.SUPPLY_CHEST, new BPos(4, 3, 9));
			put(LootType.MAP_CHEST, new BPos(5, 3, 18));
			put(LootType.TREASURE_CHEST, new BPos(6, 5, 24));
		}});
		STRUCTURE_SIZE.put("with_mast_degraded", new BPos(9, 21, 28));
	}

	private ChunkRand random = null; // this is an internal one as it will be updated on a need to know basis
	private Boolean isBeached = null;
	private BlockRotation rotation = null;
	private String type = null;

	public Shipwreck(MCVersion version) {
		this(CONFIGS.getAsOf(version), version);
	}

	public Shipwreck(RegionStructure.Config config, MCVersion version) {
		super(config, version);
	}

	public static String name() {
		return "shipwreck";
	}

	/**
	 * This is a dangerous utility, we provide it on a need to know basis (don't use it)
	 *
	 * @return
	 */
	public ChunkRand getInternalRandom() {
		return random;
	}

	/**
	 * Should be called after canspawn and getRotation
	 *
	 * @return the type of shipwreck (useful to determine loot order)
	 */
	public String getType() {
		if (isBeached == null) return null;
		if (rotation == null) return null;
		if (type == null) {
			String[] arr = isBeached ? STRUCTURE_LOCATION_BEACHED : STRUCTURE_LOCATION_OCEAN;
			type = arr[random.nextInt(arr.length)];
		}
		return type;
	}

	/**
	 * This should be called before any operation related to nbt
	 *
	 * @param structureSeed
	 * @param chunkPos
	 * @param version
	 * @return
	 */
	public BlockRotation getRotation(long structureSeed, CPos chunkPos, MCVersion version) {
		// first call does the seeding the rest doesn't
		if (rotation == null) {
			random = new ChunkRand();
			random.setCarverSeed(structureSeed, chunkPos.getX(), chunkPos.getZ(), version);
			rotation = BlockRotation.getRandom(random);
		}
		return rotation;
	}

	@Override
	public boolean canStart(Data<Shipwreck> data, long structureSeed, ChunkRand rand) {
		return super.canStart(data, structureSeed, rand);
	}

	@Override
	public boolean isValidDimension(Dimension dimension) {
		return dimension == Dimension.OVERWORLD;
	}

	/**
	 * This will only work if you call canSpawn before
	 *
	 * @return
	 */
	public Boolean isBeached() {
		return isBeached;
	}

	@Override
	public boolean isValidBiome(Biome biome) {
		isBeached = biome == Biome.BEACH || biome == Biome.SNOWY_BEACH;
		return biome.getCategory() == Biome.Category.OCEAN || isBeached;
	}

	/**
	 * WARNING You need to call canSpawn before hand, else you will get a null
	 *
	 * @param start         the chunkposition of the shipwreck start (obtained with getInRegion
	 * @param structureSeed the structure seed (lower 48 bits of the world seed)
	 * @param rand          a chunkrand instance (for speed purpose)
	 * @param indexed       Should be indexed or not (default false)
	 * @return
	 */
	public HashMap<LootType, List<ItemStack>> getLoot(CPos start, long structureSeed, ChunkRand rand, boolean indexed) {
		if (isBeached == null) {
			System.err.println("Please call canspawn before");
			return null;
		}
		RPos rPos = start.toRegionPos(this.getSpacing());
		CPos validation = this.getInRegion(structureSeed, rPos.getX(), rPos.getZ(), rand);
		if (!start.equals(validation)) {
			System.err.println("Provided chunkpos " + start + " was wrong, correct was " + validation);
			return null;
		}
		BlockRotation rotation = this.getRotation(structureSeed, start, this.getVersion());
		String type = this.getType();
		if (!STRUCTURE_SIZE.containsKey(type) || !STRUCTURE_TO_LOOT.containsKey(type)) {
			System.err.println("We don't support this type yet " + type);
			return null;
		}
		int salt = 40006; //TODO make me version dependant
		BPos size = STRUCTURE_SIZE.get(type);
		HashMap<LootType, BPos> lootPos = STRUCTURE_TO_LOOT.get(type);
		BPos anchor = start.toBlockPos(90);
		BPos pivot = new BPos(4, 0, 15); // this is fixed for shipwreck
		BlockMirror mirror = BlockMirror.NONE; // this is fixed for shipwreck
		BlockBox blockBox = BlockBox.getBoundingBox(anchor, rotation, pivot, mirror, size);
		BlockBox rotated = blockBox.getRotated(rotation);
		HashMap<CPos, LinkedList<LootType>> cPosLootTypeHashMap = new HashMap<>();
		for (LootType lootType : lootPos.keySet()) {
			BPos offset = lootPos.get(lootType);
			BPos chestPos = rotated.getInside(offset, rotation);
			CPos chunkChestPos = chestPos.toChunkPos();
			if (cPosLootTypeHashMap.containsKey(chunkChestPos)) {
				cPosLootTypeHashMap.get(chunkChestPos).add(lootType);
			} else {
				cPosLootTypeHashMap.put(chunkChestPos, new LinkedList<>(Collections.singletonList(lootType)));
			}
		}
		HashMap<LootType, ChestData> chestDataHashMap = new HashMap<>();
		for (CPos cPos : cPosLootTypeHashMap.keySet()) {
			List<LootType> lootTypes = cPosLootTypeHashMap.get(cPos);
			int index = 0;
			for (LootType lootType : lootTypes) {
				chestDataHashMap.put(lootType, new ChestData(index, cPos, lootTypes.size()));
				index += 1;
			}
		}
		HashMap<LootType, List<ItemStack>> result = new HashMap<>();
		for (LootType lootType : chestDataHashMap.keySet()) {
			ChestData chestData = chestDataHashMap.get(lootType);
			CPos chunkChestPos = chestData.cPos;
			rand.setDecoratorSeed(structureSeed, chunkChestPos.getX() * 16, chunkChestPos.getZ() * 16, salt, this.getVersion());
			if (this.isBeached()) {
				rand.nextInt(3);
			}
			rand.advance(chestData.numberInChunk * 2L);
			rand.advance(chestData.index * 2L);
			System.out.println(rand.getSeed());
			LootContext context = new LootContext(rand.nextLong());
			result.put(lootType, indexed ? lootType.lootTable.generateIndexed(context) : lootType.lootTable.generate(context));
		}
		return result;
	}
	public enum LootType {
		SUPPLY_CHEST(MCLootTables.SHIPWRECK_SUPPLY_CHEST),
		TREASURE_CHEST(MCLootTables.SHIPWRECK_TREASURE_CHEST),
		MAP_CHEST(MCLootTables.SHIPWRECK_MAP_CHEST);
		private final LootTable lootTable;

		LootType(LootTable lootTable) {
			this.lootTable = lootTable;
		}
	}

	public static class ChestData {
		int index;
		CPos cPos;
		int numberInChunk;

		public ChestData(int index, CPos cPos, int numberInChunk) {
			this.index = index;
			this.cPos = cPos;
			this.numberInChunk = numberInChunk;
		}
	}
//import nbtlib
//from pathlib import *
//import sys
//
//p = Path(r'.').glob('**/*')
//files = [x for x in p if x.is_file()]
//for file in files:
//    print(f'STRUCTURE_TO_LOOT.put("{file.rstrip(".nbt")}", new LinkedHashMap<LootType, BPos>() {{{{')
//    nbt_file=nbtlib.load(file)
//    root=nbt_file.root
//    if "blocks" not in root.keys():
//        print(f"Missing blocks key for {file}")
//        sys.exit(1)
//    for block in root["blocks"]:
//        if "nbt" in block.keys() and "pos" in block.keys():
//            pos=block["pos"]
//            nbt=block["nbt"]
//            if "metadata" in nbt:
//                print(f'    put(LootType.{nbt["metadata"].upper()},new BPos({",".join(map(str,map(int,pos)))}));')
//    print('}});')
//    if "size" in root.keys():
//        print(f'STRUCTURE_SIZE.put("{file}",new BPos({",".join(map(str,map(int,root["size"])))}));')
//    else:
//        print(f"Missing size key for {file.rstrip(".nbt")}")
//        sys.exit(1)
}