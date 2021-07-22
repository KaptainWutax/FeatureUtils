package kaptainwutax.featureutils.structure.generator.piece.village;

import kaptainwutax.mcutils.block.Block;
import kaptainwutax.mcutils.block.Blocks;
import kaptainwutax.mcutils.util.data.Pair;
import kaptainwutax.mcutils.util.data.Quad;
import kaptainwutax.mcutils.util.pos.BPos;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class PlainsVillageJigsawBlocks {
	public static final HashMap<String, List<Pair<Quad<String, String, String, Block>, BPos>>> JIGSAW_BLOCKS = new HashMap<String, List<Pair<Quad<String, String, String, Block>, BPos>>>() {{

		this.put("plains/plains_lamp_1", Collections.singletonList(
			new Pair<>(new Quad<>("empty", "bottom", "down_south", Blocks.OAK_FENCE), new BPos(1,0,1))
		));
		this.put("plains/houses/plains_accessory_1", Collections.singletonList(
			new Pair<>(new Quad<>("village/plains/streets", "building_entrance", "west_up", Blocks.STRUCTURE_VOID), new BPos(0,0,0))
		));
		this.put("plains/houses/plains_animal_pen_1", Arrays.asList(
			new Pair<>(new Quad<>("village/common/animals", "bottom", "up_north", Blocks.DIRT), new BPos(2,0,1)),
			new Pair<>(new Quad<>("village/plains/trees", "bottom", "up_north", Blocks.DIRT), new BPos(3,0,4)),
			new Pair<>(new Quad<>("empty", "building_entrance", "west_up", Blocks.OAK_FENCE_GATE), new BPos(0,1,2))
		));
		this.put("plains/houses/plains_animal_pen_2", Arrays.asList(
			new Pair<>(new Quad<>("village/common/animals", "bottom", "up_north", Blocks.DIRT), new BPos(2,0,3)),
			new Pair<>(new Quad<>("village/plains/trees", "bottom", "up_north", Blocks.DIRT), new BPos(4,0,2)),
			new Pair<>(new Quad<>("village/common/animals", "bottom", "up_north", Blocks.DIRT), new BPos(4,0,7)),
			new Pair<>(new Quad<>("empty", "building_entrance", "west_up", Blocks.OAK_FENCE_GATE), new BPos(0,1,5))
		));
		this.put("plains/houses/plains_animal_pen_3", Arrays.asList(
			new Pair<>(new Quad<>("village/plains/decor", "bottom", "up_north", Blocks.DIRT), new BPos(0,0,0)),
			new Pair<>(new Quad<>("village/plains/decor", "bottom", "up_north", Blocks.DIRT), new BPos(1,0,10)),
			new Pair<>(new Quad<>("village/common/animals", "bottom", "up_north", Blocks.DIRT), new BPos(2,0,5)),
			new Pair<>(new Quad<>("village/plains/decor", "bottom", "up_north", Blocks.DIRT), new BPos(3,0,7)),
			new Pair<>(new Quad<>("village/common/animals", "bottom", "up_north", Blocks.DIRT), new BPos(4,0,1)),
			new Pair<>(new Quad<>("village/plains/decor", "bottom", "up_north", Blocks.DIRT), new BPos(4,0,3)),
			new Pair<>(new Quad<>("village/common/animals", "bottom", "up_north", Blocks.DIRT), new BPos(5,0,5)),
			new Pair<>(new Quad<>("empty", "building_entrance", "west_up", Blocks.OAK_FENCE_GATE), new BPos(0,1,5))
		));
		this.put("plains/houses/plains_armorer_house_1", Collections.singletonList(
			new Pair<>(new Quad<>("empty", "building_entrance", "west_up", Blocks.COBBLESTONE_STAIRS), new BPos(0,0,4))
		));
		this.put("plains/houses/plains_big_house_1", Arrays.asList(
			new Pair<>(new Quad<>("village/plains/villagers", "bottom", "up_north", Blocks.COBBLESTONE), new BPos(3,0,3)),
			new Pair<>(new Quad<>("village/plains/villagers", "bottom", "up_north", Blocks.COBBLESTONE), new BPos(3,0,8)),
			new Pair<>(new Quad<>("empty", "building_entrance", "west_up", Blocks.STRUCTURE_VOID), new BPos(0,1,5)),
			new Pair<>(new Quad<>("village/plains/villagers", "bottom", "up_north", Blocks.OAK_PLANKS), new BPos(3,4,2)),
			new Pair<>(new Quad<>("village/plains/villagers", "bottom", "up_north", Blocks.OAK_PLANKS), new BPos(3,4,8))
		));
		this.put("plains/houses/plains_butcher_shop_1", Arrays.asList(
			new Pair<>(new Quad<>("village/plains/streets", "building_entrance", "west_up", Blocks.OAK_STAIRS), new BPos(0,0,3)),
			new Pair<>(new Quad<>("village/common/butcher_animals", "bottom", "up_north", Blocks.DIRT), new BPos(6,0,9))
		));
		this.put("plains/houses/plains_butcher_shop_2", Arrays.asList(
			new Pair<>(new Quad<>("village/common/cats", "bottom", "up_north", Blocks.GRASS_BLOCK), new BPos(0,0,0)),
			new Pair<>(new Quad<>("village/plains/decor", "bottom", "up_north", Blocks.DIRT), new BPos(5,0,6)),
			new Pair<>(new Quad<>("village/common/butcher_animals", "bottom", "up_north", Blocks.GRASS_BLOCK), new BPos(12,0,3)),
			new Pair<>(new Quad<>("village/common/cats", "bottom", "up_north", Blocks.GRASS_BLOCK), new BPos(13,0,6)),
			new Pair<>(new Quad<>("village/plains/decor", "bottom", "up_north", Blocks.GRASS_BLOCK), new BPos(14,0,0)),
			new Pair<>(new Quad<>("village/plains/streets", "building_entrance", "west_up", Blocks.STRUCTURE_VOID), new BPos(0,1,3))
		));
		this.put("plains/houses/plains_cartographer_1", Collections.singletonList(
			new Pair<>(new Quad<>("village/plains/streets", "building_entrance", "west_up", Blocks.STRUCTURE_VOID), new BPos(0,1,3))
		));
		this.put("plains/houses/plains_fisher_cottage_1", Collections.singletonList(
			new Pair<>(new Quad<>("empty", "building_entrance", "west_up", Blocks.STRUCTURE_VOID), new BPos(0,2,7))
		));
		this.put("plains/houses/plains_fletcher_house_1", Arrays.asList(
			new Pair<>(new Quad<>("village/plains/decor", "bottom", "up_north", Blocks.DIRT), new BPos(1,0,1)),
			new Pair<>(new Quad<>("empty", "building_entrance", "west_up", Blocks.OAK_FENCE), new BPos(0,1,6))
		));
		this.put("plains/houses/plains_large_farm_1", Collections.singletonList(
			new Pair<>(new Quad<>("village/plains/streets", "building_entrance", "west_up", Blocks.OAK_LOG), new BPos(0,0,4))
		));
		this.put("plains/houses/plains_library_1", Collections.singletonList(
			new Pair<>(new Quad<>("empty", "building_entrance", "west_up", Blocks.COBBLESTONE_STAIRS), new BPos(0,0,8))
		));
		this.put("plains/houses/plains_library_2", Collections.singletonList(
			new Pair<>(new Quad<>("village/plains/streets", "building_entrance", "west_up", Blocks.OAK_FENCE), new BPos(0,1,4))
		));
		this.put("plains/houses/plains_masons_house_1", Collections.singletonList(
			new Pair<>(new Quad<>("village/plains/streets", "building_entrance", "west_up", Blocks.COBBLESTONE_STAIRS), new BPos(0,0,3))
		));
		this.put("plains/houses/plains_medium_house_1", Arrays.asList(
			new Pair<>(new Quad<>("village/plains/decor", "bottom", "up_north", Blocks.DIRT), new BPos(10,0,0)),
			new Pair<>(new Quad<>("empty", "building_entrance", "west_up", Blocks.STRUCTURE_VOID), new BPos(0,1,4)),
			new Pair<>(new Quad<>("village/plains/villagers", "bottom", "up_north", Blocks.OAK_PLANKS), new BPos(4,1,7)),
			new Pair<>(new Quad<>("village/plains/villagers", "bottom", "up_north", Blocks.OAK_PLANKS), new BPos(6,1,6))
		));
		this.put("plains/houses/plains_medium_house_2", Arrays.asList(
			new Pair<>(new Quad<>("village/plains/streets", "building_entrance", "west_up", Blocks.COBBLESTONE_STAIRS), new BPos(0,0,6)),
			new Pair<>(new Quad<>("village/plains/villagers", "bottom", "up_north", Blocks.COBBLESTONE), new BPos(3,0,4)),
			new Pair<>(new Quad<>("village/plains/villagers", "bottom", "up_north", Blocks.COBBLESTONE), new BPos(3,0,8))
		));
		this.put("plains/houses/plains_meeting_point_4", Arrays.asList(
			new Pair<>(new Quad<>("village/plains/decor", "bottom", "up_north", Blocks.DIRT), new BPos(1,0,1)),
			new Pair<>(new Quad<>("village/plains/trees", "bottom", "up_north", Blocks.DIRT), new BPos(7,0,9)),
			new Pair<>(new Quad<>("village/plains/decor", "bottom", "up_north", Blocks.DIRT), new BPos(7,0,13)),
			new Pair<>(new Quad<>("village/plains/decor", "bottom", "up_north", Blocks.DIRT), new BPos(8,0,2)),
			new Pair<>(new Quad<>("empty", "building_entrance", "west_up", Blocks.STRUCTURE_VOID), new BPos(0,1,8))
		));
		this.put("plains/houses/plains_meeting_point_5", Arrays.asList(
			new Pair<>(new Quad<>("village/common/cats", "bottom", "up_north", Blocks.GRASS_PATH), new BPos(2,0,6)),
			new Pair<>(new Quad<>("village/plains/decor", "bottom", "up_north", Blocks.DIRT), new BPos(7,0,2)),
			new Pair<>(new Quad<>("village/plains/decor", "bottom", "up_north", Blocks.DIRT), new BPos(8,0,8)),
			new Pair<>(new Quad<>("village/plains/streets", "building_entrance", "west_up", Blocks.STRUCTURE_VOID), new BPos(0,1,5))
		));
		this.put("plains/houses/plains_shepherds_house_1", Arrays.asList(
			new Pair<>(new Quad<>("village/plains/decor", "bottom", "up_north", Blocks.DIRT), new BPos(0,0,0)),
			new Pair<>(new Quad<>("village/common/sheep", "bottom", "up_north", Blocks.DIRT), new BPos(7,0,5)),
			new Pair<>(new Quad<>("village/plains/decor", "bottom", "up_north", Blocks.DIRT), new BPos(8,0,1)),
			new Pair<>(new Quad<>("empty", "building_entrance", "west_up", Blocks.STRUCTURE_VOID), new BPos(0,1,4))
		));
		this.put("plains/houses/plains_small_farm_1", Collections.singletonList(
			new Pair<>(new Quad<>("village/plains/streets", "building_entrance", "west_up", Blocks.OAK_LOG), new BPos(0,0,4))
		));
		this.put("plains/houses/plains_small_house_1", Arrays.asList(
			new Pair<>(new Quad<>("village/plains/streets", "building_entrance", "west_up", Blocks.OAK_STAIRS), new BPos(0,0,3)),
			new Pair<>(new Quad<>("village/plains/villagers", "bottom", "up_north", Blocks.OAK_PLANKS), new BPos(3,0,3))
		));
		this.put("plains/houses/plains_small_house_2", Arrays.asList(
			new Pair<>(new Quad<>("village/plains/streets", "building_entrance", "west_up", Blocks.OAK_STAIRS), new BPos(0,0,3)),
			new Pair<>(new Quad<>("village/plains/villagers", "bottom", "up_north", Blocks.OAK_PLANKS), new BPos(3,0,3))
		));
		this.put("plains/houses/plains_small_house_3", Arrays.asList(
			new Pair<>(new Quad<>("village/plains/streets", "building_entrance", "west_up", Blocks.OAK_STAIRS), new BPos(0,0,3)),
			new Pair<>(new Quad<>("village/plains/villagers", "bottom", "up_north", Blocks.OAK_PLANKS), new BPos(3,0,3))
		));
		this.put("plains/houses/plains_small_house_4", Arrays.asList(
			new Pair<>(new Quad<>("village/plains/streets", "building_entrance", "west_up", Blocks.OAK_STAIRS), new BPos(0,0,3)),
			new Pair<>(new Quad<>("village/plains/villagers", "bottom", "up_north", Blocks.OAK_PLANKS), new BPos(3,0,3))
		));
		this.put("plains/houses/plains_small_house_5", Arrays.asList(
			new Pair<>(new Quad<>("village/plains/decor", "bottom", "up_north", Blocks.DIRT), new BPos(0,0,1)),
			new Pair<>(new Quad<>("village/plains/decor", "bottom", "up_north", Blocks.DIRT), new BPos(1,0,7)),
			new Pair<>(new Quad<>("village/plains/decor", "bottom", "up_north", Blocks.DIRT), new BPos(8,0,3)),
			new Pair<>(new Quad<>("village/plains/streets", "building_entrance", "west_up", Blocks.COBBLESTONE_STAIRS), new BPos(0,1,4)),
			new Pair<>(new Quad<>("village/plains/villagers", "bottom", "up_north", Blocks.OAK_PLANKS), new BPos(3,1,2))
		));
		this.put("plains/houses/plains_small_house_6", Arrays.asList(
			new Pair<>(new Quad<>("village/plains/streets", "building_entrance", "west_up", Blocks.STRUCTURE_VOID), new BPos(0,0,3)),
			new Pair<>(new Quad<>("village/plains/villagers", "bottom", "up_north", Blocks.COBBLESTONE), new BPos(3,0,4))
		));
		this.put("plains/houses/plains_small_house_7", Arrays.asList(
			new Pair<>(new Quad<>("village/plains/streets", "building_entrance", "west_up", Blocks.COBBLESTONE_STAIRS), new BPos(0,0,4)),
			new Pair<>(new Quad<>("village/plains/villagers", "bottom", "up_north", Blocks.OAK_PLANKS), new BPos(3,0,4))
		));
		this.put("plains/houses/plains_small_house_8", Arrays.asList(
			new Pair<>(new Quad<>("empty", "building_entrance", "west_up", Blocks.COBBLESTONE_STAIRS), new BPos(0,2,4)),
			new Pair<>(new Quad<>("village/plains/villagers", "bottom", "up_north", Blocks.OAK_PLANKS), new BPos(3,2,4))
		));
		this.put("plains/houses/plains_stable_1", Arrays.asList(
			new Pair<>(new Quad<>("village/plains/decor", "bottom", "up_north", Blocks.DIRT), new BPos(0,0,11)),
			new Pair<>(new Quad<>("village/plains/decor", "bottom", "up_north", Blocks.DIRT), new BPos(0,0,15)),
			new Pair<>(new Quad<>("village/common/animals", "bottom", "up_north", Blocks.DIRT), new BPos(2,0,2)),
			new Pair<>(new Quad<>("village/common/animals", "bottom", "up_north", Blocks.DIRT), new BPos(4,0,10)),
			new Pair<>(new Quad<>("village/plains/decor", "bottom", "up_north", Blocks.DIRT), new BPos(6,0,1)),
			new Pair<>(new Quad<>("village/common/cats", "bottom", "up_north", Blocks.GRASS_BLOCK), new BPos(7,0,7)),
			new Pair<>(new Quad<>("village/common/cats", "bottom", "up_north", Blocks.GRASS_BLOCK), new BPos(7,0,14)),
			new Pair<>(new Quad<>("village/plains/streets", "building_entrance", "west_up", Blocks.STRUCTURE_VOID), new BPos(0,1,8))
		));
		this.put("plains/houses/plains_stable_2", Arrays.asList(
			new Pair<>(new Quad<>("village/common/cats", "bottom", "up_north", Blocks.GRASS_BLOCK), new BPos(0,0,3)),
			new Pair<>(new Quad<>("village/common/animals", "bottom", "up_north", Blocks.DIRT), new BPos(3,0,2)),
			new Pair<>(new Quad<>("village/common/animals", "bottom", "up_north", Blocks.DIRT), new BPos(3,0,4)),
			new Pair<>(new Quad<>("village/common/cats", "bottom", "up_north", Blocks.GRASS_BLOCK), new BPos(6,0,1)),
			new Pair<>(new Quad<>("village/plains/streets", "building_entrance", "west_up", Blocks.STRUCTURE_VOID), new BPos(0,1,9))
		));
		this.put("plains/houses/plains_tannery_1", Collections.singletonList(
			new Pair<>(new Quad<>("village/plains/streets", "building_entrance", "west_up", Blocks.STRUCTURE_VOID), new BPos(0,0,7))
		));
		this.put("plains/houses/plains_temple_3", Collections.singletonList(
			new Pair<>(new Quad<>("village/plains/streets", "building_entrance", "west_up", Blocks.COBBLESTONE_STAIRS), new BPos(0,0,3))
		));
		this.put("plains/houses/plains_temple_4", Collections.singletonList(
			new Pair<>(new Quad<>("empty", "building_entrance", "west_up", Blocks.STRUCTURE_VOID), new BPos(0,0,3))
		));
		this.put("plains/houses/plains_tool_smith_1", Collections.singletonList(
			new Pair<>(new Quad<>("empty", "building_entrance", "west_up", Blocks.STRUCTURE_VOID), new BPos(0,0,8))
		));
		this.put("plains/houses/plains_weaponsmith_1", Collections.singletonList(
			new Pair<>(new Quad<>("village/plains/streets", "building_entrance", "west_up", Blocks.COBBLESTONE_STAIRS), new BPos(0,0,3))
		));
		this.put("plains/streets/corner_01", Arrays.asList(
			new Pair<>(new Quad<>("village/plains/decor", "bottom", "up_north", Blocks.GRASS_BLOCK), new BPos(3,0,4)),
			new Pair<>(new Quad<>("village/plains/decor", "bottom", "up_north", Blocks.GRASS_BLOCK), new BPos(3,0,13)),
			new Pair<>(new Quad<>("village/plains/decor", "bottom", "up_north", Blocks.GRASS_BLOCK), new BPos(8,0,12)),
			new Pair<>(new Quad<>("village/plains/decor", "bottom", "up_north", Blocks.GRASS_BLOCK), new BPos(10,0,3)),
			new Pair<>(new Quad<>("village/plains/decor", "bottom", "up_north", Blocks.GRASS_BLOCK), new BPos(13,0,9)),
			new Pair<>(new Quad<>("village/plains/streets", "street", "west_up", Blocks.STRUCTURE_VOID), new BPos(0,1,8)),
			new Pair<>(new Quad<>("village/plains/streets", "street", "north_up", Blocks.STRUCTURE_VOID), new BPos(7,1,0)),
			new Pair<>(new Quad<>("village/plains/houses", "building_entrance", "east_up", Blocks.STRUCTURE_VOID), new BPos(8,1,6))
		));
		this.put("plains/streets/corner_02", Arrays.asList(
			new Pair<>(new Quad<>("village/plains/streets", "street", "north_up", Blocks.STRUCTURE_VOID), new BPos(1,1,0)),
			new Pair<>(new Quad<>("village/plains/houses", "building_entrance", "north_up", Blocks.STRUCTURE_VOID), new BPos(9,1,12)),
			new Pair<>(new Quad<>("village/plains/streets", "street", "east_up", Blocks.STRUCTURE_VOID), new BPos(15,1,14))
		));
		this.put("plains/streets/corner_03", Arrays.asList(
			new Pair<>(new Quad<>("village/plains/streets", "street", "south_up", Blocks.STRUCTURE_VOID), new BPos(1,1,3)),
			new Pair<>(new Quad<>("village/plains/streets", "street", "east_up", Blocks.STRUCTURE_VOID), new BPos(3,1,1))
		));
		this.put("plains/streets/crossroad_01", Arrays.asList(
			new Pair<>(new Quad<>("village/plains/decor", "bottom", "up_north", Blocks.DIRT), new BPos(13,0,12)),
			new Pair<>(new Quad<>("village/plains/houses", "building_entrance", "west_up", Blocks.STRUCTURE_VOID), new BPos(7,1,3)),
			new Pair<>(new Quad<>("village/plains/houses", "building_entrance", "west_up", Blocks.STRUCTURE_VOID), new BPos(7,1,4)),
			new Pair<>(new Quad<>("village/plains/houses", "building_entrance", "west_up", Blocks.STRUCTURE_VOID), new BPos(7,1,5)),
			new Pair<>(new Quad<>("village/plains/houses", "building_entrance", "west_up", Blocks.STRUCTURE_VOID), new BPos(7,1,6)),
			new Pair<>(new Quad<>("village/plains/houses", "building_entrance", "west_up", Blocks.STRUCTURE_VOID), new BPos(7,1,7)),
			new Pair<>(new Quad<>("village/plains/houses", "building_entrance", "west_up", Blocks.STRUCTURE_VOID), new BPos(7,1,11)),
			new Pair<>(new Quad<>("village/plains/houses", "building_entrance", "west_up", Blocks.STRUCTURE_VOID), new BPos(7,1,12)),
			new Pair<>(new Quad<>("village/plains/streets", "street", "north_up", Blocks.STRUCTURE_VOID), new BPos(8,1,0)),
			new Pair<>(new Quad<>("village/plains/streets", "street", "south_up", Blocks.STRUCTURE_VOID), new BPos(8,1,15)),
			new Pair<>(new Quad<>("village/plains/houses", "building_entrance", "east_up", Blocks.STRUCTURE_VOID), new BPos(9,1,3)),
			new Pair<>(new Quad<>("village/plains/houses", "building_entrance", "east_up", Blocks.STRUCTURE_VOID), new BPos(9,1,4)),
			new Pair<>(new Quad<>("village/plains/streets", "street", "east_up", Blocks.STRUCTURE_VOID), new BPos(15,1,8))
		));
		this.put("plains/streets/crossroad_02", Arrays.asList(
			new Pair<>(new Quad<>("village/plains/decor", "bottom", "up_north", Blocks.GRASS_BLOCK), new BPos(3,0,5)),
			new Pair<>(new Quad<>("village/plains/decor", "bottom", "up_north", Blocks.GRASS_BLOCK), new BPos(5,0,1)),
			new Pair<>(new Quad<>("village/plains/decor", "bottom", "up_north", Blocks.GRASS_BLOCK), new BPos(11,0,5)),
			new Pair<>(new Quad<>("village/plains/decor", "bottom", "up_north", Blocks.GRASS_BLOCK), new BPos(13,0,12)),
			new Pair<>(new Quad<>("village/plains/decor", "bottom", "up_north", Blocks.GRASS_BLOCK), new BPos(14,0,2)),
			new Pair<>(new Quad<>("village/plains/streets", "street", "west_up", Blocks.STRUCTURE_VOID), new BPos(0,1,8)),
			new Pair<>(new Quad<>("village/plains/streets", "street", "north_up", Blocks.STRUCTURE_VOID), new BPos(8,1,0)),
			new Pair<>(new Quad<>("village/plains/streets", "street", "south_up", Blocks.STRUCTURE_VOID), new BPos(8,1,15)),
			new Pair<>(new Quad<>("village/plains/streets", "street", "east_up", Blocks.STRUCTURE_VOID), new BPos(15,1,8))
		));
		this.put("plains/streets/crossroad_03", Arrays.asList(
			new Pair<>(new Quad<>("village/plains/decor", "bottom", "up_north", Blocks.GRASS_BLOCK), new BPos(3,0,13)),
			new Pair<>(new Quad<>("village/plains/decor", "bottom", "up_north", Blocks.GRASS_BLOCK), new BPos(8,0,11)),
			new Pair<>(new Quad<>("village/plains/streets", "street", "west_up", Blocks.STRUCTURE_VOID), new BPos(0,1,8)),
			new Pair<>(new Quad<>("village/plains/streets", "street", "north_up", Blocks.STRUCTURE_VOID), new BPos(4,1,0)),
			new Pair<>(new Quad<>("village/plains/houses", "building_entrance", "north_up", Blocks.STRUCTURE_VOID), new BPos(11,1,7)),
			new Pair<>(new Quad<>("village/plains/streets", "street", "south_up", Blocks.STRUCTURE_VOID), new BPos(11,1,15)),
			new Pair<>(new Quad<>("village/plains/streets", "street", "east_up", Blocks.STRUCTURE_VOID), new BPos(15,1,8))
		));
		this.put("plains/streets/crossroad_04", Arrays.asList(
			new Pair<>(new Quad<>("village/plains/streets", "street", "west_up", Blocks.STRUCTURE_VOID), new BPos(0,1,2)),
			new Pair<>(new Quad<>("village/plains/streets", "street", "north_up", Blocks.STRUCTURE_VOID), new BPos(2,1,0)),
			new Pair<>(new Quad<>("village/plains/streets", "street", "south_up", Blocks.STRUCTURE_VOID), new BPos(2,1,4))
		));
		this.put("plains/streets/crossroad_05", Arrays.asList(
			new Pair<>(new Quad<>("village/plains/streets", "street", "west_up", Blocks.STRUCTURE_VOID), new BPos(0,1,2)),
			new Pair<>(new Quad<>("village/plains/streets", "street", "north_up", Blocks.STRUCTURE_VOID), new BPos(2,1,0)),
			new Pair<>(new Quad<>("village/plains/streets", "street", "south_up", Blocks.STRUCTURE_VOID), new BPos(2,1,4)),
			new Pair<>(new Quad<>("village/plains/streets", "street", "east_up", Blocks.STRUCTURE_VOID), new BPos(4,1,2))
		));
		this.put("plains/streets/crossroad_06", Arrays.asList(
			new Pair<>(new Quad<>("village/plains/decor", "bottom", "up_north", Blocks.GRASS_BLOCK), new BPos(2,0,2)),
			new Pair<>(new Quad<>("village/plains/streets", "street", "west_up", Blocks.STRUCTURE_VOID), new BPos(0,1,2)),
			new Pair<>(new Quad<>("village/plains/streets", "street", "north_up", Blocks.STRUCTURE_VOID), new BPos(2,1,0)),
			new Pair<>(new Quad<>("village/plains/streets", "street", "south_up", Blocks.STRUCTURE_VOID), new BPos(2,1,4)),
			new Pair<>(new Quad<>("village/plains/streets", "street", "east_up", Blocks.STRUCTURE_VOID), new BPos(4,1,2))
		));
		this.put("plains/streets/straight_01", Arrays.asList(
			new Pair<>(new Quad<>("village/plains/decor", "bottom", "up_north", Blocks.GRASS_BLOCK), new BPos(2,0,13)),
			new Pair<>(new Quad<>("village/plains/decor", "bottom", "up_north", Blocks.GRASS_BLOCK), new BPos(4,0,7)),
			new Pair<>(new Quad<>("village/plains/decor", "bottom", "up_north", Blocks.GRASS_BLOCK), new BPos(11,0,6)),
			new Pair<>(new Quad<>("village/plains/decor", "bottom", "up_north", Blocks.GRASS_BLOCK), new BPos(11,0,13)),
			new Pair<>(new Quad<>("village/plains/decor", "bottom", "up_north", Blocks.GRASS_BLOCK), new BPos(12,0,4)),
			new Pair<>(new Quad<>("village/plains/streets", "street", "north_up", Blocks.STRUCTURE_VOID), new BPos(7,1,0)),
			new Pair<>(new Quad<>("village/plains/streets", "street", "south_up", Blocks.STRUCTURE_VOID), new BPos(7,1,15))
		));
		this.put("plains/streets/straight_02", Arrays.asList(
			new Pair<>(new Quad<>("village/plains/streets", "street", "north_up", Blocks.STRUCTURE_VOID), new BPos(1,1,0)),
			new Pair<>(new Quad<>("village/plains/streets", "street", "south_up", Blocks.STRUCTURE_VOID), new BPos(1,1,15)),
			new Pair<>(new Quad<>("village/plains/houses", "building_entrance", "east_up", Blocks.STRUCTURE_VOID), new BPos(2,1,8))
		));
		this.put("plains/streets/straight_03", Arrays.asList(
			new Pair<>(new Quad<>("village/plains/streets", "street", "north_up", Blocks.STRUCTURE_VOID), new BPos(1,1,0)),
			new Pair<>(new Quad<>("village/plains/streets", "street", "south_up", Blocks.STRUCTURE_VOID), new BPos(1,1,10)),
			new Pair<>(new Quad<>("village/plains/houses", "building_entrance", "east_up", Blocks.STRUCTURE_VOID), new BPos(2,1,3)),
			new Pair<>(new Quad<>("village/plains/houses", "building_entrance", "east_up", Blocks.STRUCTURE_VOID), new BPos(2,1,4)),
			new Pair<>(new Quad<>("village/plains/houses", "building_entrance", "east_up", Blocks.STRUCTURE_VOID), new BPos(2,1,5)),
			new Pair<>(new Quad<>("village/plains/houses", "building_entrance", "east_up", Blocks.STRUCTURE_VOID), new BPos(2,1,6)),
			new Pair<>(new Quad<>("village/plains/houses", "building_entrance", "east_up", Blocks.STRUCTURE_VOID), new BPos(2,1,7))
		));
		this.put("plains/streets/straight_04", Arrays.asList(
			new Pair<>(new Quad<>("village/plains/streets", "street", "north_up", Blocks.STRUCTURE_VOID), new BPos(1,1,0)),
			new Pair<>(new Quad<>("village/plains/streets", "street", "south_up", Blocks.STRUCTURE_VOID), new BPos(1,1,8)),
			new Pair<>(new Quad<>("village/plains/houses", "building_entrance", "east_up", Blocks.STRUCTURE_VOID), new BPos(2,1,4))
		));
		this.put("plains/streets/straight_05", Arrays.asList(
			new Pair<>(new Quad<>("village/plains/streets", "street", "north_up", Blocks.STRUCTURE_VOID), new BPos(1,1,0)),
			new Pair<>(new Quad<>("village/plains/streets", "street", "south_up", Blocks.STRUCTURE_VOID), new BPos(1,1,16)),
			new Pair<>(new Quad<>("village/plains/houses", "building_entrance", "east_up", Blocks.STRUCTURE_VOID), new BPos(2,1,7)),
			new Pair<>(new Quad<>("village/plains/houses", "building_entrance", "east_up", Blocks.STRUCTURE_VOID), new BPos(2,1,8)),
			new Pair<>(new Quad<>("village/plains/houses", "building_entrance", "east_up", Blocks.STRUCTURE_VOID), new BPos(2,1,9)),
			new Pair<>(new Quad<>("village/plains/houses", "building_entrance", "east_up", Blocks.STRUCTURE_VOID), new BPos(2,1,10))
		));
		this.put("plains/streets/straight_06", Arrays.asList(
			new Pair<>(new Quad<>("village/plains/decor", "bottom", "up_north", Blocks.DIRT), new BPos(8,0,3)),
			new Pair<>(new Quad<>("village/plains/decor", "bottom", "up_north", Blocks.GRASS_BLOCK), new BPos(9,0,9)),
			new Pair<>(new Quad<>("village/plains/decor", "bottom", "up_north", Blocks.GRASS_BLOCK), new BPos(9,0,15)),
			new Pair<>(new Quad<>("village/plains/houses", "building_entrance", "west_up", Blocks.STRUCTURE_VOID), new BPos(7,1,3)),
			new Pair<>(new Quad<>("village/plains/houses", "building_entrance", "west_up", Blocks.STRUCTURE_VOID), new BPos(7,1,4)),
			new Pair<>(new Quad<>("village/plains/streets", "street", "north_up", Blocks.STRUCTURE_VOID), new BPos(8,1,0)),
			new Pair<>(new Quad<>("empty", "empty", null, Blocks.STRUCTURE_VOID), new BPos(8,1,4)),
			new Pair<>(new Quad<>("village/plains/houses", "building_entrance", "west_up", Blocks.STRUCTURE_VOID), new BPos(8,1,8)),
			new Pair<>(new Quad<>("village/plains/houses", "building_entrance", "west_up", Blocks.STRUCTURE_VOID), new BPos(8,1,9)),
			new Pair<>(new Quad<>("village/plains/houses", "building_entrance", "west_up", Blocks.STRUCTURE_VOID), new BPos(8,1,14)),
			new Pair<>(new Quad<>("village/plains/houses", "building_entrance", "west_up", Blocks.STRUCTURE_VOID), new BPos(8,1,15)),
			new Pair<>(new Quad<>("village/plains/houses", "building_entrance", "east_up", Blocks.STRUCTURE_VOID), new BPos(9,1,2)),
			new Pair<>(new Quad<>("village/plains/houses", "building_entrance", "east_up", Blocks.STRUCTURE_VOID), new BPos(9,1,3)),
			new Pair<>(new Quad<>("village/plains/houses", "building_entrance", "east_up", Blocks.STRUCTURE_VOID), new BPos(9,1,4)),
			new Pair<>(new Quad<>("village/plains/streets", "street", "south_up", Blocks.STRUCTURE_VOID), new BPos(9,1,17)),
			new Pair<>(new Quad<>("village/plains/houses", "building_entrance", "east_up", Blocks.STRUCTURE_VOID), new BPos(10,1,8)),
			new Pair<>(new Quad<>("village/plains/houses", "building_entrance", "east_up", Blocks.STRUCTURE_VOID), new BPos(10,1,9)),
			new Pair<>(new Quad<>("village/plains/houses", "building_entrance", "east_up", Blocks.STRUCTURE_VOID), new BPos(10,1,10)),
			new Pair<>(new Quad<>("village/plains/houses", "building_entrance", "east_up", Blocks.STRUCTURE_VOID), new BPos(10,1,14)),
			new Pair<>(new Quad<>("village/plains/houses", "building_entrance", "east_up", Blocks.STRUCTURE_VOID), new BPos(10,1,15))
		));
		this.put("plains/streets/turn_01", Arrays.asList(
			new Pair<>(new Quad<>("village/plains/streets", "street", "north_up", Blocks.STRUCTURE_VOID), new BPos(5,1,0)),
			new Pair<>(new Quad<>("village/plains/houses", "building_entrance", "west_up", Blocks.STRUCTURE_VOID), new BPos(7,1,6)),
			new Pair<>(new Quad<>("village/plains/streets", "street", "south_up", Blocks.STRUCTURE_VOID), new BPos(8,1,7)),
			new Pair<>(new Quad<>("village/plains/houses", "building_entrance", "east_up", Blocks.STRUCTURE_VOID), new BPos(9,1,4))
		));
		this.put("plains/terminators/terminator_01", Collections.singletonList(
			new Pair<>(new Quad<>("empty", "street", "east_up", Blocks.STRUCTURE_VOID), new BPos(1,1,1))
		));
		this.put("plains/terminators/terminator_02", Collections.singletonList(
			new Pair<>(new Quad<>("empty", "street", "east_up", Blocks.STRUCTURE_VOID), new BPos(0,1,0))
		));
		this.put("plains/terminators/terminator_03", Collections.singletonList(
			new Pair<>(new Quad<>("empty", "street", "east_up", Blocks.STRUCTURE_VOID), new BPos(2,1,1))
		));
		this.put("plains/terminators/terminator_04", Collections.singletonList(
			new Pair<>(new Quad<>("empty", "street", "east_up", Blocks.STRUCTURE_VOID), new BPos(3,1,1))
		));
		this.put("plains/town_centers/plains_fountain_01", Arrays.asList(
			new Pair<>(new Quad<>("village/common/iron_golem", "bottom", "up_north", Blocks.GRASS_PATH), new BPos(0,0,6)),
			new Pair<>(new Quad<>("village/common/cats", "bottom", "up_north", Blocks.GRASS_PATH), new BPos(1,0,2)),
			new Pair<>(new Quad<>("village/plains/villagers", "bottom", "up_north", Blocks.GRASS_PATH), new BPos(2,0,1)),
			new Pair<>(new Quad<>("village/common/cats", "bottom", "up_north", Blocks.GRASS_PATH), new BPos(2,0,7)),
			new Pair<>(new Quad<>("village/plains/villagers", "bottom", "up_north", Blocks.GRASS_PATH), new BPos(7,0,2)),
			new Pair<>(new Quad<>("village/plains/villagers", "bottom", "up_north", Blocks.GRASS_PATH), new BPos(7,0,7)),
			new Pair<>(new Quad<>("village/plains/streets", "street", "west_up", Blocks.STRUCTURE_VOID), new BPos(0,1,4)),
			new Pair<>(new Quad<>("village/plains/streets", "street", "north_up", Blocks.STRUCTURE_VOID), new BPos(4,1,0)),
			new Pair<>(new Quad<>("village/plains/streets", "street", "south_up", Blocks.STRUCTURE_VOID), new BPos(4,1,8)),
			new Pair<>(new Quad<>("village/plains/streets", "street", "east_up", Blocks.STRUCTURE_VOID), new BPos(8,1,4))
		));
		this.put("plains/town_centers/plains_meeting_point_1", Arrays.asList(
			new Pair<>(new Quad<>("village/plains/villagers", "bottom", "up_north", Blocks.GRASS_PATH), new BPos(1,0,1)),
			new Pair<>(new Quad<>("village/common/iron_golem", "bottom", "up_north", Blocks.GRASS_PATH), new BPos(1,0,6)),
			new Pair<>(new Quad<>("village/plains/villagers", "bottom", "up_north", Blocks.GRASS_PATH), new BPos(1,0,8)),
			new Pair<>(new Quad<>("village/common/well_bottoms", "bottom", "down_south", Blocks.COBBLESTONE), new BPos(6,0,3)),
			new Pair<>(new Quad<>("village/plains/streets", "street", "west_up", Blocks.STRUCTURE_VOID), new BPos(0,1,4)),
			new Pair<>(new Quad<>("village/common/cats", "bottom", "up_north", Blocks.COBBLESTONE), new BPos(2,1,2)),
			new Pair<>(new Quad<>("village/common/cats", "bottom", "up_north", Blocks.COBBLESTONE), new BPos(2,1,4)),
			new Pair<>(new Quad<>("village/plains/streets", "street", "south_up", Blocks.STRUCTURE_VOID), new BPos(4,1,9)),
			new Pair<>(new Quad<>("village/plains/streets", "street", "north_up", Blocks.STRUCTURE_VOID), new BPos(5,1,0)),
			new Pair<>(new Quad<>("village/common/cats", "bottom", "up_north", Blocks.COBBLESTONE), new BPos(5,1,7)),
			new Pair<>(new Quad<>("village/plains/streets", "street", "east_up", Blocks.STRUCTURE_VOID), new BPos(9,1,5))
		));
		this.put("plains/town_centers/plains_meeting_point_2", Arrays.asList(
			new Pair<>(new Quad<>("village/common/iron_golem", "bottom", "up_north", Blocks.GRASS_BLOCK), new BPos(1,0,4)),
			new Pair<>(new Quad<>("village/plains/villagers", "bottom", "up_north", Blocks.GRASS_BLOCK), new BPos(1,0,6)),
			new Pair<>(new Quad<>("village/common/cats", "bottom", "up_north", Blocks.GRASS_PATH), new BPos(1,0,11)),
			new Pair<>(new Quad<>("village/plains/villagers", "bottom", "up_north", Blocks.GRASS_PATH), new BPos(2,0,8)),
			new Pair<>(new Quad<>("village/plains/villagers", "bottom", "up_north", Blocks.GRASS_PATH), new BPos(2,0,10)),
			new Pair<>(new Quad<>("village/common/cats", "bottom", "up_north", Blocks.GRASS_PATH), new BPos(3,0,4)),
			new Pair<>(new Quad<>("village/common/cats", "bottom", "up_north", Blocks.GRASS_PATH), new BPos(4,0,11)),
			new Pair<>(new Quad<>("village/plains/streets", "street", "west_up", Blocks.STRUCTURE_VOID), new BPos(0,1,7)),
			new Pair<>(new Quad<>("village/plains/streets", "street", "north_up", Blocks.STRUCTURE_VOID), new BPos(5,1,0)),
			new Pair<>(new Quad<>("village/plains/streets", "street", "south_up", Blocks.STRUCTURE_VOID), new BPos(5,1,14)),
			new Pair<>(new Quad<>("village/plains/streets", "street", "east_up", Blocks.STRUCTURE_VOID), new BPos(7,1,3))
		));
		this.put("plains/town_centers/plains_meeting_point_3", Arrays.asList(
			new Pair<>(new Quad<>("village/plains/villagers", "bottom", "up_north", Blocks.GRASS_PATH), new BPos(1,0,2)),
			new Pair<>(new Quad<>("village/common/cats", "bottom", "up_north", Blocks.COBBLESTONE), new BPos(2,0,4)),
			new Pair<>(new Quad<>("village/plains/villagers", "bottom", "up_north", Blocks.GRASS_PATH), new BPos(2,0,6)),
			new Pair<>(new Quad<>("village/common/iron_golem", "bottom", "up_north", Blocks.GRASS_BLOCK), new BPos(2,0,7)),
			new Pair<>(new Quad<>("village/common/cats", "bottom", "up_north", Blocks.COBBLESTONE), new BPos(3,0,3)),
			new Pair<>(new Quad<>("village/plains/streets", "street", "west_up", Blocks.STRUCTURE_VOID), new BPos(0,1,5)),
			new Pair<>(new Quad<>("village/plains/streets", "street", "south_up", Blocks.STRUCTURE_VOID), new BPos(5,1,10)),
			new Pair<>(new Quad<>("village/plains/streets", "street", "east_up", Blocks.STRUCTURE_VOID), new BPos(10,1,5))
		));
		this.put("plains/villagers/baby", Collections.singletonList(
			new Pair<>(new Quad<>("empty", "bottom", "down_south", Blocks.STRUCTURE_VOID), new BPos(0,0,0))
		));
		this.put("plains/villagers/nitwi", Collections.singletonList(
			new Pair<>(new Quad<>("empty", "bottom", "down_south", Blocks.STRUCTURE_VOID), new BPos(0,0,0))
		));
		this.put("plains/villagers/unemployed", Collections.singletonList(
			new Pair<>(new Quad<>("empty", "bottom", "down_south", Blocks.STRUCTURE_VOID), new BPos(0,0,0))
		));
		this.put("plains/zombie/houses/plains_animal_pen_3", Arrays.asList(
			new Pair<>(new Quad<>("village/plains/zombie/decor", "bottom", "up_north", Blocks.DIRT), new BPos(0,0,0)),
			new Pair<>(new Quad<>("village/plains/zombie/decor", "bottom", "up_north", Blocks.DIRT), new BPos(1,0,10)),
			new Pair<>(new Quad<>("village/common/animals", "bottom", "up_north", Blocks.DIRT), new BPos(2,0,5)),
			new Pair<>(new Quad<>("village/plains/zombie/decor", "bottom", "up_north", Blocks.DIRT), new BPos(3,0,7)),
			new Pair<>(new Quad<>("village/common/animals", "bottom", "up_north", Blocks.DIRT), new BPos(4,0,1)),
			new Pair<>(new Quad<>("village/plains/zombie/decor", "bottom", "up_north", Blocks.DIRT), new BPos(4,0,3)),
			new Pair<>(new Quad<>("village/common/animals", "bottom", "up_north", Blocks.DIRT), new BPos(5,0,5)),
			new Pair<>(new Quad<>("empty", "building_entrance", "west_up", Blocks.OAK_FENCE_GATE), new BPos(0,1,5))
		));
		this.put("plains/zombie/houses/plains_big_house_1", Arrays.asList(
			new Pair<>(new Quad<>("village/plains/zombie/villagers", "bottom", "up_north", Blocks.COBBLESTONE), new BPos(3,0,3)),
			new Pair<>(new Quad<>("village/plains/zombie/villagers", "bottom", "up_north", Blocks.COBBLESTONE), new BPos(3,0,8)),
			new Pair<>(new Quad<>("empty", "building_entrance", "west_up", Blocks.STRUCTURE_VOID), new BPos(0,1,5)),
			new Pair<>(new Quad<>("village/plains/zombie/villagers", "bottom", "up_north", Blocks.OAK_PLANKS), new BPos(3,4,2)),
			new Pair<>(new Quad<>("village/plains/zombie/villagers", "bottom", "up_north", Blocks.OAK_PLANKS), new BPos(3,4,8))
		));
		this.put("plains/zombie/houses/plains_butcher_shop_2", Arrays.asList(
			new Pair<>(new Quad<>("village/plains/zombie/decor", "bottom", "up_north", Blocks.DIRT), new BPos(5,0,6)),
			new Pair<>(new Quad<>("village/common/cats", "bottom", "up_north", Blocks.GRASS_BLOCK), new BPos(11,0,6)),
			new Pair<>(new Quad<>("village/common/butcher_animals", "bottom", "up_north", Blocks.DIRT), new BPos(12,0,3)),
			new Pair<>(new Quad<>("village/common/cats", "bottom", "up_north", Blocks.GRASS_BLOCK), new BPos(13,0,6)),
			new Pair<>(new Quad<>("village/plains/zombie/decor", "bottom", "up_north", Blocks.DIRT), new BPos(14,0,0)),
			new Pair<>(new Quad<>("village/plains/zombie/streets", "building_entrance", "west_up", Blocks.STRUCTURE_VOID), new BPos(0,1,3))
		));
		this.put("plains/zombie/houses/plains_fletcher_house_1", Arrays.asList(
			new Pair<>(new Quad<>("village/plains/zombie/decor", "bottom", "up_north", Blocks.DIRT), new BPos(1,0,1)),
			new Pair<>(new Quad<>("empty", "building_entrance", "west_up", Blocks.OAK_FENCE), new BPos(0,1,6))
		));
		this.put("plains/zombie/houses/plains_medium_house_1", Arrays.asList(
			new Pair<>(new Quad<>("village/plains/zombie/decor", "bottom", "up_north", Blocks.DIRT), new BPos(10,0,0)),
			new Pair<>(new Quad<>("empty", "building_entrance", "west_up", Blocks.STRUCTURE_VOID), new BPos(0,1,4)),
			new Pair<>(new Quad<>("village/plains/zombie/villagers", "bottom", "up_north", Blocks.OAK_PLANKS), new BPos(4,1,7)),
			new Pair<>(new Quad<>("village/plains/zombie/villagers", "bottom", "up_north", Blocks.OAK_PLANKS), new BPos(6,1,6))
		));
		this.put("plains/zombie/houses/plains_medium_house_2", Arrays.asList(
			new Pair<>(new Quad<>("village/plains/zombie/streets", "building_entrance", "west_up", Blocks.COBBLESTONE_STAIRS), new BPos(0,0,6)),
			new Pair<>(new Quad<>("village/plains/zombie/villagers", "bottom", "up_north", Blocks.COBBLESTONE), new BPos(3,0,4)),
			new Pair<>(new Quad<>("village/plains/zombie/villagers", "bottom", "up_north", Blocks.COBBLESTONE), new BPos(3,0,8))
		));
		this.put("plains/zombie/houses/plains_meeting_point_4", Arrays.asList(
			new Pair<>(new Quad<>("village/plains/zombie/decor", "bottom", "up_north", Blocks.DIRT), new BPos(1,0,1)),
			new Pair<>(new Quad<>("village/plains/trees", "bottom", "up_north", Blocks.DIRT), new BPos(7,0,9)),
			new Pair<>(new Quad<>("village/plains/zombie/decor", "bottom", "up_north", Blocks.DIRT), new BPos(7,0,13)),
			new Pair<>(new Quad<>("village/plains/zombie/decor", "bottom", "up_north", Blocks.DIRT), new BPos(8,0,2)),
			new Pair<>(new Quad<>("empty", "building_entrance", "west_up", Blocks.STRUCTURE_VOID), new BPos(0,1,8))
		));
		this.put("plains/zombie/houses/plains_meeting_point_5", Arrays.asList(
			new Pair<>(new Quad<>("village/plains/zombie/decor", "bottom", "up_north", Blocks.DIRT), new BPos(7,0,2)),
			new Pair<>(new Quad<>("village/plains/zombie/decor", "bottom", "up_north", Blocks.DIRT), new BPos(8,0,8)),
			new Pair<>(new Quad<>("village/plains/zombie/streets", "building_entrance", "west_up", Blocks.STRUCTURE_VOID), new BPos(0,1,5))
		));
		this.put("plains/zombie/houses/plains_shepherds_house_1", Arrays.asList(
			new Pair<>(new Quad<>("village/plains/zombie/decor", "bottom", "up_north", Blocks.DIRT), new BPos(0,0,0)),
			new Pair<>(new Quad<>("village/common/sheep", "bottom", "up_north", Blocks.DIRT), new BPos(7,0,5)),
			new Pair<>(new Quad<>("village/plains/zombie/decor", "bottom", "up_north", Blocks.DIRT), new BPos(8,0,1)),
			new Pair<>(new Quad<>("empty", "building_entrance", "west_up", Blocks.STRUCTURE_VOID), new BPos(0,1,4))
		));
		this.put("plains/zombie/houses/plains_small_house_1", Arrays.asList(
			new Pair<>(new Quad<>("village/plains/zombie/streets", "building_entrance", "west_up", Blocks.OAK_STAIRS), new BPos(0,0,3)),
			new Pair<>(new Quad<>("village/plains/zombie/villagers", "bottom", "up_north", Blocks.OAK_PLANKS), new BPos(3,0,3))
		));
		this.put("plains/zombie/houses/plains_small_house_2", Arrays.asList(
			new Pair<>(new Quad<>("village/plains/zombie/streets", "building_entrance", "west_up", Blocks.OAK_STAIRS), new BPos(0,0,3)),
			new Pair<>(new Quad<>("village/plains/zombie/villagers", "bottom", "up_north", Blocks.OAK_PLANKS), new BPos(3,0,3))
		));
		this.put("plains/zombie/houses/plains_small_house_3", Arrays.asList(
			new Pair<>(new Quad<>("village/plains/zombie/streets", "building_entrance", "west_up", Blocks.OAK_STAIRS), new BPos(0,0,3)),
			new Pair<>(new Quad<>("village/plains/zombie/villagers", "bottom", "up_north", Blocks.OAK_PLANKS), new BPos(3,0,3))
		));
		this.put("plains/zombie/houses/plains_small_house_4", Arrays.asList(
			new Pair<>(new Quad<>("village/plains/zombie/streets", "building_entrance", "west_up", Blocks.OAK_STAIRS), new BPos(0,0,3)),
			new Pair<>(new Quad<>("village/plains/zombie/villagers", "bottom", "up_north", Blocks.OAK_PLANKS), new BPos(3,0,3))
		));
		this.put("plains/zombie/houses/plains_small_house_5", Arrays.asList(
			new Pair<>(new Quad<>("village/plains/zombie/decor", "bottom", "up_north", Blocks.DIRT), new BPos(0,0,1)),
			new Pair<>(new Quad<>("village/plains/zombie/decor", "bottom", "up_north", Blocks.DIRT), new BPos(1,0,7)),
			new Pair<>(new Quad<>("village/plains/zombie/decor", "bottom", "up_north", Blocks.DIRT), new BPos(8,0,3)),
			new Pair<>(new Quad<>("village/plains/zombie/streets", "building_entrance", "west_up", Blocks.COBBLESTONE_STAIRS), new BPos(0,1,4)),
			new Pair<>(new Quad<>("village/plains/zombie/villagers", "bottom", "up_north", Blocks.OAK_PLANKS), new BPos(3,1,2)),
			new Pair<>(new Quad<>("village/plains/zombie/villagers", "bottom", null, Blocks.OAK_PLANKS), new BPos(3,1,5)),
			new Pair<>(new Quad<>("empty", "empty", null, Blocks.STRUCTURE_VOID), new BPos(0,2,3))
		));
		this.put("plains/zombie/houses/plains_small_house_6", Arrays.asList(
			new Pair<>(new Quad<>("village/plains/zombie/streets", "building_entrance", "west_up", Blocks.STRUCTURE_VOID), new BPos(0,0,3)),
			new Pair<>(new Quad<>("village/plains/zombie/villagers", "bottom", "up_north", Blocks.COBBLESTONE), new BPos(3,0,4))
		));
		this.put("plains/zombie/houses/plains_small_house_7", Arrays.asList(
			new Pair<>(new Quad<>("village/plains/zombie/streets", "building_entrance", "west_up", Blocks.COBBLESTONE_STAIRS), new BPos(0,0,4)),
			new Pair<>(new Quad<>("village/plains/zombie/villagers", "bottom", "up_north", Blocks.OAK_PLANKS), new BPos(3,0,4))
		));
		this.put("plains/zombie/houses/plains_small_house_8", Arrays.asList(
			new Pair<>(new Quad<>("empty", "building_entrance", "west_up", Blocks.COBBLESTONE_STAIRS), new BPos(0,2,4)),
			new Pair<>(new Quad<>("village/plains/zombie/villagers", "bottom", "up_north", Blocks.OAK_PLANKS), new BPos(3,2,4))
		));
		this.put("plains/zombie/houses/plains_stable_1", Arrays.asList(
			new Pair<>(new Quad<>("village/plains/zombie/decor", "bottom", "up_north", Blocks.DIRT), new BPos(0,0,11)),
			new Pair<>(new Quad<>("village/plains/zombie/decor", "bottom", "up_north", Blocks.DIRT), new BPos(0,0,15)),
			new Pair<>(new Quad<>("village/common/animals", "bottom", "up_north", Blocks.DIRT), new BPos(2,0,2)),
			new Pair<>(new Quad<>("village/common/animals", "bottom", "up_north", Blocks.DIRT), new BPos(4,0,10)),
			new Pair<>(new Quad<>("village/plains/zombie/decor", "bottom", "up_north", Blocks.DIRT), new BPos(6,0,1)),
			new Pair<>(new Quad<>("village/common/cats", "bottom", "up_north", Blocks.GRASS_BLOCK), new BPos(8,0,8)),
			new Pair<>(new Quad<>("village/common/cats", "bottom", "up_north", Blocks.GRASS_BLOCK), new BPos(8,0,14)),
			new Pair<>(new Quad<>("village/plains/zombie/streets", "building_entrance", "west_up", Blocks.STRUCTURE_VOID), new BPos(0,1,8))
		));
		this.put("plains/zombie/streets/corner_01", Arrays.asList(
			new Pair<>(new Quad<>("village/plains/zombie/decor", "bottom", "up_north", Blocks.GRASS_BLOCK), new BPos(3,0,4)),
			new Pair<>(new Quad<>("village/plains/zombie/decor", "bottom", "up_north", Blocks.GRASS_BLOCK), new BPos(3,0,13)),
			new Pair<>(new Quad<>("village/plains/zombie/decor", "bottom", "up_north", Blocks.GRASS_BLOCK), new BPos(8,0,12)),
			new Pair<>(new Quad<>("village/plains/zombie/decor", "bottom", "up_north", Blocks.GRASS_BLOCK), new BPos(10,0,3)),
			new Pair<>(new Quad<>("village/plains/zombie/decor", "bottom", "up_north", Blocks.GRASS_BLOCK), new BPos(13,0,9)),
			new Pair<>(new Quad<>("village/plains/zombie/streets", "street", "west_up", Blocks.STRUCTURE_VOID), new BPos(0,1,8)),
			new Pair<>(new Quad<>("village/plains/zombie/streets", "street", "north_up", Blocks.STRUCTURE_VOID), new BPos(7,1,0)),
			new Pair<>(new Quad<>("village/plains/zombie/houses", "building_entrance", "east_up", Blocks.STRUCTURE_VOID), new BPos(8,1,6))
		));
		this.put("plains/zombie/streets/corner_02", Arrays.asList(
			new Pair<>(new Quad<>("village/plains/zombie/streets", "street", "north_up", Blocks.STRUCTURE_VOID), new BPos(1,1,0)),
			new Pair<>(new Quad<>("village/plains/zombie/houses", "building_entrance", "north_up", Blocks.STRUCTURE_VOID), new BPos(9,1,12)),
			new Pair<>(new Quad<>("village/plains/zombie/streets", "street", "east_up", Blocks.STRUCTURE_VOID), new BPos(15,1,14))
		));
		this.put("plains/zombie/streets/corner_03", Arrays.asList(
			new Pair<>(new Quad<>("village/plains/zombie/streets", "street", "south_up", Blocks.STRUCTURE_VOID), new BPos(1,1,3)),
			new Pair<>(new Quad<>("village/plains/zombie/streets", "street", "east_up", Blocks.STRUCTURE_VOID), new BPos(3,1,1))
		));
		this.put("plains/zombie/streets/crossroad_01", Arrays.asList(
			new Pair<>(new Quad<>("village/plains/zombie/decor", "bottom", "up_north", Blocks.DIRT), new BPos(13,0,12)),
			new Pair<>(new Quad<>("village/plains/zombie/houses", "building_entrance", "west_up", Blocks.STRUCTURE_VOID), new BPos(7,1,3)),
			new Pair<>(new Quad<>("village/plains/zombie/houses", "building_entrance", "west_up", Blocks.STRUCTURE_VOID), new BPos(7,1,4)),
			new Pair<>(new Quad<>("village/plains/zombie/houses", "building_entrance", "west_up", Blocks.STRUCTURE_VOID), new BPos(7,1,5)),
			new Pair<>(new Quad<>("village/plains/zombie/houses", "building_entrance", "west_up", Blocks.STRUCTURE_VOID), new BPos(7,1,6)),
			new Pair<>(new Quad<>("village/plains/zombie/houses", "building_entrance", "west_up", Blocks.STRUCTURE_VOID), new BPos(7,1,7)),
			new Pair<>(new Quad<>("village/plains/zombie/houses", "building_entrance", "west_up", Blocks.STRUCTURE_VOID), new BPos(7,1,11)),
			new Pair<>(new Quad<>("village/plains/zombie/houses", "building_entrance", "west_up", Blocks.STRUCTURE_VOID), new BPos(7,1,12)),
			new Pair<>(new Quad<>("village/plains/zombie/streets", "street", "north_up", Blocks.STRUCTURE_VOID), new BPos(8,1,0)),
			new Pair<>(new Quad<>("village/plains/zombie/streets", "street", "south_up", Blocks.STRUCTURE_VOID), new BPos(8,1,15)),
			new Pair<>(new Quad<>("village/plains/zombie/houses", "building_entrance", "east_up", Blocks.STRUCTURE_VOID), new BPos(9,1,3)),
			new Pair<>(new Quad<>("village/plains/zombie/houses", "building_entrance", "east_up", Blocks.STRUCTURE_VOID), new BPos(9,1,4)),
			new Pair<>(new Quad<>("village/plains/zombie/streets", "street", "east_up", Blocks.STRUCTURE_VOID), new BPos(15,1,8))
		));
		this.put("plains/zombie/streets/crossroad_02", Arrays.asList(
			new Pair<>(new Quad<>("village/plains/zombie/decor", "bottom", "up_north", Blocks.GRASS_BLOCK), new BPos(3,0,5)),
			new Pair<>(new Quad<>("village/plains/zombie/decor", "bottom", "up_north", Blocks.GRASS_BLOCK), new BPos(5,0,1)),
			new Pair<>(new Quad<>("village/plains/zombie/decor", "bottom", "up_north", Blocks.GRASS_BLOCK), new BPos(11,0,5)),
			new Pair<>(new Quad<>("village/plains/zombie/decor", "bottom", "up_north", Blocks.GRASS_BLOCK), new BPos(13,0,12)),
			new Pair<>(new Quad<>("village/plains/zombie/decor", "bottom", "up_north", Blocks.GRASS_BLOCK), new BPos(14,0,2)),
			new Pair<>(new Quad<>("village/plains/zombie/streets", "street", "west_up", Blocks.STRUCTURE_VOID), new BPos(0,1,8)),
			new Pair<>(new Quad<>("village/plains/zombie/streets", "street", "north_up", Blocks.STRUCTURE_VOID), new BPos(8,1,0)),
			new Pair<>(new Quad<>("village/plains/zombie/streets", "street", "south_up", Blocks.STRUCTURE_VOID), new BPos(8,1,15)),
			new Pair<>(new Quad<>("village/plains/zombie/streets", "street", "east_up", Blocks.STRUCTURE_VOID), new BPos(15,1,8))
		));
		this.put("plains/zombie/streets/crossroad_03", Arrays.asList(
			new Pair<>(new Quad<>("village/plains/zombie/decor", "bottom", "up_north", Blocks.GRASS_BLOCK), new BPos(3,0,13)),
			new Pair<>(new Quad<>("village/plains/zombie/decor", "bottom", "up_north", Blocks.GRASS_BLOCK), new BPos(8,0,11)),
			new Pair<>(new Quad<>("village/plains/zombie/streets", "street", "west_up", Blocks.STRUCTURE_VOID), new BPos(0,1,8)),
			new Pair<>(new Quad<>("village/plains/zombie/streets", "street", "north_up", Blocks.STRUCTURE_VOID), new BPos(4,1,0)),
			new Pair<>(new Quad<>("village/plains/zombie/houses", "building_entrance", "north_up", Blocks.STRUCTURE_VOID), new BPos(11,1,7)),
			new Pair<>(new Quad<>("village/plains/zombie/streets", "street", "south_up", Blocks.STRUCTURE_VOID), new BPos(11,1,15)),
			new Pair<>(new Quad<>("village/plains/zombie/streets", "street", "east_up", Blocks.STRUCTURE_VOID), new BPos(15,1,8))
		));
		this.put("plains/zombie/streets/crossroad_04", Arrays.asList(
			new Pair<>(new Quad<>("village/plains/zombie/streets", "street", "west_up", Blocks.STRUCTURE_VOID), new BPos(0,1,2)),
			new Pair<>(new Quad<>("village/plains/zombie/streets", "street", "north_up", Blocks.STRUCTURE_VOID), new BPos(2,1,0)),
			new Pair<>(new Quad<>("village/plains/zombie/streets", "street", "south_up", Blocks.STRUCTURE_VOID), new BPos(2,1,4))
		));
		this.put("plains/zombie/streets/crossroad_05", Arrays.asList(
			new Pair<>(new Quad<>("village/plains/zombie/streets", "street", "west_up", Blocks.STRUCTURE_VOID), new BPos(0,1,2)),
			new Pair<>(new Quad<>("village/plains/zombie/streets", "street", "north_up", Blocks.STRUCTURE_VOID), new BPos(2,1,0)),
			new Pair<>(new Quad<>("village/plains/zombie/streets", "street", "south_up", Blocks.STRUCTURE_VOID), new BPos(2,1,4)),
			new Pair<>(new Quad<>("village/plains/zombie/streets", "street", "east_up", Blocks.STRUCTURE_VOID), new BPos(4,1,2))
		));
		this.put("plains/zombie/streets/crossroad_06", Arrays.asList(
			new Pair<>(new Quad<>("village/plains/zombie/decor", "bottom", "up_north", Blocks.GRASS_BLOCK), new BPos(2,0,2)),
			new Pair<>(new Quad<>("village/plains/zombie/streets", "street", "west_up", Blocks.STRUCTURE_VOID), new BPos(0,1,2)),
			new Pair<>(new Quad<>("village/plains/zombie/streets", "street", "north_up", Blocks.STRUCTURE_VOID), new BPos(2,1,0)),
			new Pair<>(new Quad<>("village/plains/zombie/streets", "street", "south_up", Blocks.STRUCTURE_VOID), new BPos(2,1,4)),
			new Pair<>(new Quad<>("village/plains/zombie/streets", "street", "east_up", Blocks.STRUCTURE_VOID), new BPos(4,1,2))
		));
		this.put("plains/zombie/streets/straight_01", Arrays.asList(
			new Pair<>(new Quad<>("village/plains/zombie/decor", "bottom", "up_north", Blocks.GRASS_BLOCK), new BPos(2,0,13)),
			new Pair<>(new Quad<>("village/plains/zombie/decor", "bottom", "up_north", Blocks.GRASS_BLOCK), new BPos(4,0,7)),
			new Pair<>(new Quad<>("village/plains/zombie/decor", "bottom", "up_north", Blocks.GRASS_BLOCK), new BPos(11,0,6)),
			new Pair<>(new Quad<>("village/plains/zombie/decor", "bottom", "up_north", Blocks.GRASS_BLOCK), new BPos(11,0,13)),
			new Pair<>(new Quad<>("village/plains/zombie/decor", "bottom", "up_north", Blocks.GRASS_BLOCK), new BPos(12,0,4)),
			new Pair<>(new Quad<>("village/plains/zombie/streets", "street", "north_up", Blocks.STRUCTURE_VOID), new BPos(7,1,0)),
			new Pair<>(new Quad<>("village/plains/zombie/streets", "street", "south_up", Blocks.STRUCTURE_VOID), new BPos(7,1,15))
		));
		this.put("plains/zombie/streets/straight_02", Arrays.asList(
			new Pair<>(new Quad<>("village/plains/zombie/streets", "street", "north_up", Blocks.STRUCTURE_VOID), new BPos(1,1,0)),
			new Pair<>(new Quad<>("village/plains/zombie/streets", "street", "south_up", Blocks.STRUCTURE_VOID), new BPos(1,1,15)),
			new Pair<>(new Quad<>("village/plains/zombie/houses", "building_entrance", "east_up", Blocks.STRUCTURE_VOID), new BPos(2,1,8))
		));
		this.put("plains/zombie/streets/straight_03", Arrays.asList(
			new Pair<>(new Quad<>("village/plains/zombie/streets", "street", "north_up", Blocks.STRUCTURE_VOID), new BPos(1,1,0)),
			new Pair<>(new Quad<>("village/plains/zombie/streets", "street", "south_up", Blocks.STRUCTURE_VOID), new BPos(1,1,10)),
			new Pair<>(new Quad<>("village/plains/zombie/houses", "building_entrance", "east_up", Blocks.STRUCTURE_VOID), new BPos(2,1,3)),
			new Pair<>(new Quad<>("village/plains/zombie/houses", "building_entrance", "east_up", Blocks.STRUCTURE_VOID), new BPos(2,1,4)),
			new Pair<>(new Quad<>("village/plains/zombie/houses", "building_entrance", "east_up", Blocks.STRUCTURE_VOID), new BPos(2,1,5)),
			new Pair<>(new Quad<>("village/plains/zombie/houses", "building_entrance", "east_up", Blocks.STRUCTURE_VOID), new BPos(2,1,6)),
			new Pair<>(new Quad<>("village/plains/zombie/houses", "building_entrance", "east_up", Blocks.STRUCTURE_VOID), new BPos(2,1,7))
		));
		this.put("plains/zombie/streets/straight_04", Arrays.asList(
			new Pair<>(new Quad<>("village/plains/zombie/streets", "street", "north_up", Blocks.STRUCTURE_VOID), new BPos(1,1,0)),
			new Pair<>(new Quad<>("village/plains/zombie/streets", "street", "south_up", Blocks.STRUCTURE_VOID), new BPos(1,1,8)),
			new Pair<>(new Quad<>("village/plains/zombie/houses", "building_entrance", "east_up", Blocks.STRUCTURE_VOID), new BPos(2,1,4))
		));
		this.put("plains/zombie/streets/straight_05", Arrays.asList(
			new Pair<>(new Quad<>("village/plains/zombie/streets", "street", "north_up", Blocks.STRUCTURE_VOID), new BPos(1,1,0)),
			new Pair<>(new Quad<>("village/plains/zombie/streets", "street", "south_up", Blocks.STRUCTURE_VOID), new BPos(1,1,16)),
			new Pair<>(new Quad<>("village/plains/zombie/houses", "building_entrance", "east_up", Blocks.STRUCTURE_VOID), new BPos(2,1,7)),
			new Pair<>(new Quad<>("village/plains/zombie/houses", "building_entrance", "east_up", Blocks.STRUCTURE_VOID), new BPos(2,1,8)),
			new Pair<>(new Quad<>("village/plains/zombie/houses", "building_entrance", "east_up", Blocks.STRUCTURE_VOID), new BPos(2,1,9)),
			new Pair<>(new Quad<>("village/plains/zombie/houses", "building_entrance", "east_up", Blocks.STRUCTURE_VOID), new BPos(2,1,10))
		));
		this.put("plains/zombie/streets/straight_06", Arrays.asList(
			new Pair<>(new Quad<>("village/plains/zombie/decor", "bottom", "up_north", Blocks.DIRT), new BPos(8,0,3)),
			new Pair<>(new Quad<>("village/plains/zombie/decor", "bottom", "up_north", Blocks.GRASS_BLOCK), new BPos(9,0,9)),
			new Pair<>(new Quad<>("village/plains/zombie/decor", "bottom", "up_north", Blocks.GRASS_BLOCK), new BPos(9,0,15)),
			new Pair<>(new Quad<>("village/plains/zombie/houses", "building_entrance", "west_up", Blocks.STRUCTURE_VOID), new BPos(7,1,3)),
			new Pair<>(new Quad<>("village/plains/zombie/houses", "building_entrance", "west_up", Blocks.STRUCTURE_VOID), new BPos(7,1,4)),
			new Pair<>(new Quad<>("village/plains/zombie/streets", "street", "north_up", Blocks.STRUCTURE_VOID), new BPos(8,1,0)),
			new Pair<>(new Quad<>("empty", "empty", null, Blocks.STRUCTURE_VOID), new BPos(8,1,4)),
			new Pair<>(new Quad<>("village/plains/zombie/houses", "building_entrance", "west_up", Blocks.STRUCTURE_VOID), new BPos(8,1,8)),
			new Pair<>(new Quad<>("village/plains/zombie/houses", "building_entrance", "west_up", Blocks.STRUCTURE_VOID), new BPos(8,1,9)),
			new Pair<>(new Quad<>("village/plains/zombie/houses", "building_entrance", "west_up", Blocks.STRUCTURE_VOID), new BPos(8,1,14)),
			new Pair<>(new Quad<>("village/plains/zombie/houses", "building_entrance", "west_up", Blocks.STRUCTURE_VOID), new BPos(8,1,15)),
			new Pair<>(new Quad<>("village/plains/zombie/houses", "building_entrance", "east_up", Blocks.STRUCTURE_VOID), new BPos(9,1,2)),
			new Pair<>(new Quad<>("village/plains/zombie/houses", "building_entrance", "east_up", Blocks.STRUCTURE_VOID), new BPos(9,1,3)),
			new Pair<>(new Quad<>("village/plains/zombie/houses", "building_entrance", "east_up", Blocks.STRUCTURE_VOID), new BPos(9,1,4)),
			new Pair<>(new Quad<>("village/plains/zombie/streets", "street", "south_up", Blocks.STRUCTURE_VOID), new BPos(9,1,17)),
			new Pair<>(new Quad<>("village/plains/zombie/houses", "building_entrance", "east_up", Blocks.STRUCTURE_VOID), new BPos(10,1,8)),
			new Pair<>(new Quad<>("village/plains/zombie/houses", "building_entrance", "east_up", Blocks.STRUCTURE_VOID), new BPos(10,1,9)),
			new Pair<>(new Quad<>("village/plains/zombie/houses", "building_entrance", "east_up", Blocks.STRUCTURE_VOID), new BPos(10,1,10)),
			new Pair<>(new Quad<>("village/plains/zombie/houses", "building_entrance", "east_up", Blocks.STRUCTURE_VOID), new BPos(10,1,14)),
			new Pair<>(new Quad<>("village/plains/zombie/houses", "building_entrance", "east_up", Blocks.STRUCTURE_VOID), new BPos(10,1,15))
		));
		this.put("plains/zombie/streets/turn_01", Arrays.asList(
			new Pair<>(new Quad<>("village/plains/zombie/streets", "street", "north_up", Blocks.STRUCTURE_VOID), new BPos(5,1,0)),
			new Pair<>(new Quad<>("village/plains/zombie/houses", "building_entrance", "west_up", Blocks.STRUCTURE_VOID), new BPos(7,1,6)),
			new Pair<>(new Quad<>("village/plains/zombie/streets", "street", "south_up", Blocks.STRUCTURE_VOID), new BPos(8,1,7)),
			new Pair<>(new Quad<>("village/plains/zombie/houses", "building_entrance", "east_up", Blocks.STRUCTURE_VOID), new BPos(9,1,4))
		));
		this.put("plains/zombie/town_centers/plains_fountain_01", Arrays.asList(
			new Pair<>(new Quad<>("village/common/cats", "bottom", "up_north", Blocks.GRASS_PATH), new BPos(1,0,2)),
			new Pair<>(new Quad<>("village/common/cats", "bottom", "up_north", Blocks.GRASS_PATH), new BPos(2,0,7)),
			new Pair<>(new Quad<>("village/plains/zombie/streets", "street", "west_up", Blocks.STRUCTURE_VOID), new BPos(0,1,4)),
			new Pair<>(new Quad<>("village/plains/zombie/streets", "street", "north_up", Blocks.STRUCTURE_VOID), new BPos(4,1,0)),
			new Pair<>(new Quad<>("village/plains/zombie/streets", "street", "south_up", Blocks.STRUCTURE_VOID), new BPos(4,1,8)),
			new Pair<>(new Quad<>("village/plains/zombie/streets", "street", "east_up", Blocks.STRUCTURE_VOID), new BPos(8,1,4))
		));
		this.put("plains/zombie/town_centers/plains_meeting_point_1", Arrays.asList(
			new Pair<>(new Quad<>("village/common/well_bottoms", "bottom", "down_south", Blocks.COBBLESTONE), new BPos(6,0,3)),
			new Pair<>(new Quad<>("village/plains/zombie/streets", "street", "west_up", Blocks.STRUCTURE_VOID), new BPos(0,1,4)),
			new Pair<>(new Quad<>("village/common/cats", "bottom", "up_north", Blocks.COBBLESTONE), new BPos(2,1,2)),
			new Pair<>(new Quad<>("village/common/cats", "bottom", "up_north", Blocks.COBBLESTONE), new BPos(2,1,4)),
			new Pair<>(new Quad<>("village/plains/zombie/streets", "street", "south_up", Blocks.STRUCTURE_VOID), new BPos(4,1,9)),
			new Pair<>(new Quad<>("village/plains/zombie/streets", "street", "north_up", Blocks.STRUCTURE_VOID), new BPos(5,1,0)),
			new Pair<>(new Quad<>("village/common/cats", "bottom", "up_north", Blocks.COBBLESTONE), new BPos(5,1,7)),
			new Pair<>(new Quad<>("village/plains/zombie/streets", "street", "east_up", Blocks.STRUCTURE_VOID), new BPos(9,1,5))
		));
		this.put("plains/zombie/town_centers/plains_meeting_point_2", Arrays.asList(
			new Pair<>(new Quad<>("village/common/cats", "bottom", "east_up", Blocks.GRASS_PATH), new BPos(2,0,5)),
			new Pair<>(new Quad<>("village/common/cats", "bottom", "east_up", Blocks.GRASS_BLOCK), new BPos(2,0,9)),
			new Pair<>(new Quad<>("village/plains/zombie/streets", "street", "west_up", Blocks.STRUCTURE_VOID), new BPos(0,1,7)),
			new Pair<>(new Quad<>("village/plains/zombie/streets", "street", "north_up", Blocks.STRUCTURE_VOID), new BPos(5,1,0)),
			new Pair<>(new Quad<>("village/plains/zombie/streets", "street", "south_up", Blocks.STRUCTURE_VOID), new BPos(5,1,14)),
			new Pair<>(new Quad<>("village/plains/zombie/streets", "street", "north_up", Blocks.STRUCTURE_VOID), new BPos(7,1,3))
		));
		this.put("plains/zombie/town_centers/plains_meeting_point_3", Arrays.asList(
			new Pair<>(new Quad<>("village/common/cats", "bottom", "up_north", Blocks.COBBLESTONE), new BPos(2,0,4)),
			new Pair<>(new Quad<>("village/common/cats", "bottom", "up_north", Blocks.COBBLESTONE), new BPos(3,0,3)),
			new Pair<>(new Quad<>("village/plains/zombie/streets", "street", "west_up", Blocks.STRUCTURE_VOID), new BPos(0,1,5)),
			new Pair<>(new Quad<>("village/plains/zombie/streets", "street", "south_up", Blocks.STRUCTURE_VOID), new BPos(5,1,10)),
			new Pair<>(new Quad<>("village/plains/zombie/streets", "street", "east_up", Blocks.STRUCTURE_VOID), new BPos(10,1,5))
		));
		this.put("plains/zombie/villagers/nitwi", Collections.singletonList(
			new Pair<>(new Quad<>("empty", "bottom", "down_south", Blocks.STRUCTURE_VOID), new BPos(0,0,0))
		));
		this.put("plains/zombie/villagers/unemployed", Collections.singletonList(
			new Pair<>(new Quad<>("empty", "bottom", "down_south", Blocks.STRUCTURE_VOID), new BPos(0,0,0))
		));
	}};
}
