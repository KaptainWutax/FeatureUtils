package kaptainwutax.featureutils.structure.generator;

import kaptainwutax.biomeutils.biome.Biome;
import kaptainwutax.biomeutils.biome.Biomes;
import kaptainwutax.biomeutils.source.BiomeSource;
import kaptainwutax.featureutils.loot.LootTable;
import kaptainwutax.featureutils.loot.MCLootTables;
import kaptainwutax.mcutils.rand.ChunkRand;
import kaptainwutax.mcutils.util.block.BlockBox;
import kaptainwutax.mcutils.util.block.BlockMirror;
import kaptainwutax.mcutils.util.block.BlockRotation;
import kaptainwutax.mcutils.util.data.Pair;
import kaptainwutax.mcutils.util.pos.BPos;
import kaptainwutax.mcutils.util.pos.CPos;
import kaptainwutax.mcutils.version.MCVersion;
import kaptainwutax.terrainutils.ChunkGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class ShipwreckGenerator extends Generator {
	private ChunkRand random = null; // this is an internal one as it will be updated on a need to know basis
	private Boolean isBeached = null;
	private BlockRotation rotation = null;
	private String type = null;
	private BlockBox piece = null;

	public ShipwreckGenerator(MCVersion version) {
		super(version);
	}


	@Override
	public boolean generate(ChunkGenerator generator, int chunkX, int chunkZ, ChunkRand rand) {
		BiomeSource source = generator.getBiomeSource();
		Biome biome;
		if (this.getVersion().isOlderThan(MCVersion.v1_16)) {
			biome = source.getBiome((chunkX << 4) + 9, 0, (chunkZ << 4) + 9);
		} else {
			biome = source.getBiomeForNoiseGen((chunkX << 2) + 2, 0, (chunkZ << 2) + 2);
		}
		isBeached = biome == Biomes.BEACH || biome == Biomes.SNOWY_BEACH;
		random = new ChunkRand();
		random.setCarverSeed(generator.getWorldSeed(), chunkX, chunkZ, this.getVersion());
		rotation = BlockRotation.getRandom(random);
		String[] arr = isBeached ? STRUCTURE_LOCATION_BEACHED : STRUCTURE_LOCATION_OCEAN;
		type = arr[random.nextInt(arr.length)];
		if (!STRUCTURE_SIZE.containsKey(type) || !STRUCTURE_TO_LOOT.containsKey(type)) {
			System.err.println("We don't support this type yet " + type);
			return false;
		}
		BPos size = STRUCTURE_SIZE.get(type);
		BPos anchor = new CPos(chunkX, chunkZ).toBlockPos(90);
		BPos pivot = new BPos(4, 0, 15); // this is fixed for shipwreck
		BlockMirror mirror = BlockMirror.NONE; // this is fixed for shipwreck
		BlockBox blockBox = BlockBox.getBoundingBox(anchor, rotation, pivot, mirror, size);
		piece = blockBox.getRotated(rotation);
		return true;
	}


	@Override
	public List<Pair<ILootType, BPos>> getChestsPos() {
		HashMap<LootType, BPos> lootPos = STRUCTURE_TO_LOOT.get(type);
		List<Pair<ILootType, BPos>> res = new ArrayList<>();
		for (LootType lootType : lootPos.keySet()) {
			BPos offset = lootPos.get(lootType);
			BPos chestPos = piece.getInside(offset, rotation);
			res.add(new Pair<>(lootType, chestPos));
		}
		return res;
	}

	/**
	 * This will only work if you call canSpawn before
	 *
	 * @return
	 */
	public Boolean isBeached() {
		return isBeached;
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
	 * Reset the internal state, should be called if you reuse that same structure
	 */
	public void reset() {
		this.isBeached = null;
		this.rotation = null;
		this.random = null;
		this.type = null;
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
	 * @param structureSeed the 48 lower bits at least
	 * @param chunkPos      the chunk position of that shipwreck spawn
	 * @param version       the version to run on
	 * @return the rotation of the shipwreck
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

	public BlockRotation getRotation(long structureSeed, int chunkX, int chunkZ, MCVersion version) {
		// first call does the seeding the rest doesn't
		if (rotation == null) {
			random = new ChunkRand();
			random.setCarverSeed(structureSeed, chunkX, chunkZ, version);
			rotation = BlockRotation.getRandom(random);
		}
		return rotation;
	}

	@Override
	public ILootType[] getLootTypes() {
		return LootType.values();
	}

	public enum LootType implements ILootType {
		SUPPLY_CHEST(MCLootTables.SHIPWRECK_SUPPLY_CHEST),
		TREASURE_CHEST(MCLootTables.SHIPWRECK_TREASURE_CHEST),
		MAP_CHEST(MCLootTables.SHIPWRECK_MAP_CHEST);
		private final LootTable lootTable;

		LootType(LootTable lootTable) {
			this.lootTable = lootTable;
		}

		@Override
		public LootTable getLootTable() {
			return lootTable;
		}
	}


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
//import nbtlib
//from pathlib import *
//import sys
//
//p = Path(r'.').glob('**/*')
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
//            if "metadata" in nbt:
//                print(f'    put(LootType.{nbt["metadata"].upper()},new BPos({",".join(map(str,map(int,pos)))}));')
//    print('}});')
//    if "size" in root.keys():
//        print(f'STRUCTURE_SIZE.put("{file.name.rstrip(".nbt")}",new BPos({",".join(map(str,map(int,root["size"])))}));')
//    else:
//        print(f"Missing size key for {file.name.rstrip(".nbt")}")
//        sys.exit(1)
}
