package kaptainwutax.featureutils.structure.generator.structure;

import kaptainwutax.biomeutils.biome.Biome;
import kaptainwutax.biomeutils.biome.Biomes;
import kaptainwutax.featureutils.structure.RegionStructure;
import kaptainwutax.featureutils.structure.Village;
import kaptainwutax.featureutils.structure.generator.Generator;
import kaptainwutax.mcutils.rand.ChunkRand;
import kaptainwutax.mcutils.util.block.BlockRotation;
import kaptainwutax.mcutils.util.data.Pair;
import kaptainwutax.mcutils.util.pos.BPos;
import kaptainwutax.mcutils.util.pos.CPos;
import kaptainwutax.mcutils.version.MCVersion;
import kaptainwutax.terrainutils.TerrainGenerator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VillageGenerator extends Generator {

	public VillageGenerator(MCVersion version) {
		super(version);
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean generate(TerrainGenerator generator, int chunkX, int chunkZ, ChunkRand rand) {
		BPos bPos = new CPos(chunkX, chunkZ).toBlockPos(0);
		rand.setCarverSeed(generator.getWorldSeed(), chunkX, chunkZ, generator.getVersion());
		BlockRotation rotation = BlockRotation.getRandom(rand);

		Village village = new Village(this.getVersion());
		if(!village.canStart((RegionStructure.Data<Village>)village.at(chunkX, chunkZ), generator.getWorldSeed(), rand)) return false;
		// instantiate the biome type
		if(!village.canSpawn(chunkX, chunkZ, generator.getBiomeSource())) return false;
		Biome biome = village.getBiome();
		VillageType villageType = VillageType.getType(biome, generator.getVersion());
		String start = STARTS.get(villageType);
		List<Pair<String, Integer>> templates = VILLAGE_POOLS.get(start);
		assert templates != null;
		Pair<String,Integer> template=rand.getRandom(templates);


		return true;
	}

	@Override
	public List<Pair<ILootType, BPos>> getLootPos() {
		return getChestsPos();
	}

	@Override
	public List<Pair<ILootType, BPos>> getChestsPos() {
		return null;
	}

	@Override
	public ILootType[] getLootTypes() {
		return new ILootType[0];
	}

	public static final Map<VillageType, String> STARTS = new HashMap<VillageType, String>() {{
		put(VillageType.DESERT, "desert/town_centers");
		put(VillageType.LEGACY, "");
		put(VillageType.PLAINS, "");
		put(VillageType.SAVANNA, "");
		put(VillageType.SNOWY, "");
	}};

	public static final Map<String, List<Pair<String, Integer>>> VILLAGE_POOLS = new HashMap<String, List<Pair<String, Integer>>>() {{
		put("desert/town_centers", Arrays.asList(
			new Pair<>("desert/town_centers/desert_meeting_point_1", 98),
			new Pair<>("desert/town_centers/desert_meeting_point_2", 98),
			new Pair<>("desert/town_centers/desert_meeting_point_3", 49),
			new Pair<>("desert/zombie/town_centers/desert_meeting_point_1", 2),
			new Pair<>("desert/zombie/town_centers/desert_meeting_point_2", 2),
			new Pair<>("desert/zombie/town_centers/desert_meeting_point_3", 1)
		));
		put("desert/streets", Arrays.asList(
			new Pair<>("village/desert/streets/corner_01", 3),
			new Pair<>("village/desert/streets/corner_02", 3),
			new Pair<>("village/desert/streets/straight_01", 4),
			new Pair<>("village/desert/streets/straight_02", 4),
			new Pair<>("village/desert/streets/straight_03", 3),
			new Pair<>("village/desert/streets/crossroad_01", 3),
			new Pair<>("village/desert/streets/crossroad_02", 3),
			new Pair<>("village/desert/streets/crossroad_03", 3),
			new Pair<>("village/desert/streets/square_01", 3),
			new Pair<>("village/desert/streets/square_02", 3),
			new Pair<>("village/desert/streets/turn_01", 3)
		));
		put("desert/zombie/streets", Arrays.asList(
			new Pair<>("village/desert/zombie/streets/corner_01", 3),
			new Pair<>("village/desert/zombie/streets/corner_02", 3),
			new Pair<>("village/desert/zombie/streets/straight_01", 4),
			new Pair<>("village/desert/zombie/streets/straight_02", 4),
			new Pair<>("village/desert/zombie/streets/straight_03", 3),
			new Pair<>("village/desert/zombie/streets/crossroad_01", 3),
			new Pair<>("village/desert/zombie/streets/crossroad_02", 3),
			new Pair<>("village/desert/zombie/streets/crossroad_03", 3),
			new Pair<>("village/desert/zombie/streets/square_01", 3),
			new Pair<>("village/desert/zombie/streets/square_02", 3),
			new Pair<>("village/desert/zombie/streets/turn_01", 3)
		));
		put("desert/houses", Arrays.asList(
			new Pair<>("village/desert/houses/desert_small_house_1", 2),
			new Pair<>("village/desert/houses/desert_small_house_2", 2),
			new Pair<>("village/desert/houses/desert_small_house_3", 2),
			new Pair<>("village/desert/houses/desert_small_house_4", 2),
			new Pair<>("village/desert/houses/desert_small_house_5", 2),
			new Pair<>("village/desert/houses/desert_small_house_6", 1),
			new Pair<>("village/desert/houses/desert_small_house_7", 2),
			new Pair<>("village/desert/houses/desert_small_house_8", 2),
			new Pair<>("village/desert/houses/desert_medium_house_1", 2),
			new Pair<>("village/desert/houses/desert_medium_house_2", 2),
			new Pair<>("village/desert/houses/desert_butcher_shop_1", 2),
			new Pair<>("village/desert/houses/desert_tool_smith_1", 2),
			new Pair<>("village/desert/houses/desert_fletcher_house_1", 2),
			new Pair<>("village/desert/houses/desert_shepherd_house_1", 2),
			new Pair<>("village/desert/houses/desert_armorer_1", 1),
			new Pair<>("village/desert/houses/desert_fisher_1", 2),
			new Pair<>("village/desert/houses/desert_tannery_1", 2),
			new Pair<>("village/desert/houses/desert_cartographer_house_1", 2),
			new Pair<>("village/desert/houses/desert_library_1", 2),
			new Pair<>("village/desert/houses/desert_mason_1", 2),
			new Pair<>("village/desert/houses/desert_weaponsmith_1", 2),
			new Pair<>("village/desert/houses/desert_temple_1", 2),
			new Pair<>("village/desert/houses/desert_temple_2", 2),
			new Pair<>("village/desert/houses/desert_large_farm_1", 11),
			new Pair<>("village/desert/houses/desert_farm_1", 4),
			new Pair<>("village/desert/houses/desert_farm_2", 4),
			new Pair<>("village/desert/houses/desert_animal_pen_1", 2),
			new Pair<>("village/desert/houses/desert_animal_pen_2", 2),
			new Pair<>("empty", 5)
		));
		put("desert/zombie/houses", Arrays.asList(
			new Pair<>("village/desert/zombie/houses/desert_small_house_1", 2),
			new Pair<>("village/desert/zombie/houses/desert_small_house_2", 2),
			new Pair<>("village/desert/zombie/houses/desert_small_house_3", 2),
			new Pair<>("village/desert/zombie/houses/desert_small_house_4", 2),
			new Pair<>("village/desert/zombie/houses/desert_small_house_5", 2),
			new Pair<>("village/desert/zombie/houses/desert_small_house_6", 1),
			new Pair<>("village/desert/zombie/houses/desert_small_house_7", 2),
			new Pair<>("village/desert/zombie/houses/desert_small_house_8", 2),
			new Pair<>("village/desert/zombie/houses/desert_medium_house_1", 2),
			new Pair<>("village/desert/zombie/houses/desert_medium_house_2", 2),
			new Pair<>("village/desert/houses/desert_butcher_shop_1", 2),
			new Pair<>("village/desert/houses/desert_tool_smith_1", 2),
			new Pair<>("village/desert/houses/desert_fletcher_house_1", 2),
			new Pair<>("village/desert/houses/desert_shepherd_house_1", 2),
			new Pair<>("village/desert/houses/desert_armorer_1", 1),
			new Pair<>("village/desert/houses/desert_fisher_1", 2),
			new Pair<>("village/desert/houses/desert_tannery_1", 2),
			new Pair<>("village/desert/houses/desert_cartographer_house_1", 2),
			new Pair<>("village/desert/houses/desert_library_1", 2),
			new Pair<>("village/desert/houses/desert_mason_1", 2),
			new Pair<>("village/desert/houses/desert_weaponsmith_1", 2),
			new Pair<>("village/desert/houses/desert_temple_1", 2),
			new Pair<>("village/desert/houses/desert_temple_2", 2),
			new Pair<>("village/desert/houses/desert_large_farm_1", 7),
			new Pair<>("village/desert/houses/desert_farm_1", 4),
			new Pair<>("village/desert/houses/desert_farm_2", 4),
			new Pair<>("village/desert/houses/desert_animal_pen_1", 2),
			new Pair<>("village/desert/houses/desert_animal_pen_2", 2),
			new Pair<>("empty", 5)
		));
		put("desert/terminators", Arrays.asList(
			new Pair<>("village/desert/terminators/terminator_01", 1),
			new Pair<>("village/desert/terminators/terminator_02", 1)
		));
		put("desert/zombie/terminators", Arrays.asList(
			new Pair<>("village/desert/terminators/terminator_01", 1),
			new Pair<>("village/desert/zombie/terminators/terminator_02", 1)
		));
		put("desert/decor", Arrays.asList(
			new Pair<>("village/desert/desert_lamp_1", 10),
			new Pair<>("patch_cactus", 4),
			new Pair<>("pile_hay",4),
			new Pair<>("empty",10)
		));
		put("desert/zombie/decor", Arrays.asList(
			new Pair<>("village/desert/desert_lamp_1", 10),
			new Pair<>("patch_cactus", 4),
			new Pair<>("pile_hay",4),
			new Pair<>("empty",10)
		));
		put("desert/villagers", Arrays.asList(
			new Pair<>("village/desert/villagers/nitwit", 1),
			new Pair<>("village/desert/villagers/baby", 1),
			new Pair<>("village/desert/villagers/unemployed", 10)
		));
		put("desert/zombie/villagers", Arrays.asList(
			new Pair<>("village/desert/zombie/villagers/nitwit", 1),
			new Pair<>("village/desert/zombie/villagers/unemployed", 10)
		));
	}};

	public enum VillageType {
		DESERT,
		PLAINS,
		SAVANNA,
		SNOWY,
		TAIGA,
		LEGACY;

		public static VillageType getType(Biome biome, MCVersion version) {
			if(version.isOlderThan(MCVersion.v1_14)) return LEGACY;
			if(Biomes.DESERT.equals(biome) || Biomes.DESERT_HILLS.equals(biome)) {
				return DESERT;
			}
			if(Biomes.SAVANNA.equals(biome)) {
				return SAVANNA;
			}
			if(Biomes.SNOWY_TUNDRA.equals(biome)) {
				return SNOWY;
			}
			if(Biomes.TAIGA.equals(biome)) {
				return TAIGA;
			}
			if(!biome.equals(Biomes.PLAINS)) {
				System.err.println("Biome unknown to village gen : " + biome.getName());
			}
			return PLAINS;
		}
	}
}
