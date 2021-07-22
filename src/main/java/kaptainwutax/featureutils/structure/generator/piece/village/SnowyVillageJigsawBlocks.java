package kaptainwutax.featureutils.structure.generator.piece.village;

import kaptainwutax.mcutils.util.data.Pair;
import kaptainwutax.mcutils.util.data.Quad;
import kaptainwutax.mcutils.util.pos.BPos;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class SnowyVillageJigsawBlocks {
	public static final HashMap<String, List<Pair<Quad<String, String, String, String>, BPos>>> JIGSAW_BLOCKS = new HashMap<String, List<Pair<Quad<String, String, String, String>, BPos>>>() {{
		this.put("snowy/snowy_lamp_post_01", Collections.singletonList(
			new Pair<>(new Quad<>("empty", "bottom", "down_south", "spruce_fence"), new BPos(1, 0, 0))
		));
		this.put("snowy/snowy_lamp_post_02", Collections.singletonList(
			new Pair<>(new Quad<>("empty", "bottom", "down_south", "spruce_fence"), new BPos(1, 0, 0))
		));
		this.put("snowy/snowy_lamp_post_03", Collections.singletonList(
			new Pair<>(new Quad<>("empty", "bottom", "down_south", "spruce_fence"), new BPos(1, 0, 1))
		));
		this.put("snowy/houses/snowy_animal_pen_1", Arrays.asList(
			new Pair<>(new Quad<>("empty", "building_entrance", "west_up", "spruce_stairs[facing=east]"), new BPos(0, 0, 5)),
			new Pair<>(new Quad<>("village/common/animals", "bottom", "up_north", "dirt"), new BPos(2, 0, 5))
		));
		this.put("snowy/houses/snowy_animal_pen_2", Arrays.asList(
			new Pair<>(new Quad<>("village/common/animals", "bottom", "up_north", "dirt"), new BPos(2, 0, 4)),
			new Pair<>(new Quad<>("village/common/animals", "bottom", "up_north", "structure_void"), new BPos(6, 0, 3)),
			new Pair<>(new Quad<>("empty", "building_entrance", "north_up", "structure_void"), new BPos(3, 1, 0))
		));
		this.put("snowy/houses/snowy_armorer_house_1", Collections.singletonList(
			new Pair<>(new Quad<>("empty", "building_entrance", "west_up", "spruce_stairs[facing=east]"), new BPos(0, 0, 3))
		));
		this.put("snowy/houses/snowy_armorer_house_2", Collections.singletonList(
			new Pair<>(new Quad<>("empty", "building_entrance", "north_up", "spruce_stairs[facing=south]"), new BPos(3, 0, 0))
		));
		this.put("snowy/houses/snowy_butchers_shop_1", Arrays.asList(
			new Pair<>(new Quad<>("empty", "building_entrance", "north_up", "spruce_stairs[facing=south]"), new BPos(3, 0, 0)),
			new Pair<>(new Quad<>("village/common/butcher_animals", "bottom", "up_north", "dirt"), new BPos(4, 0, 7))
		));
		this.put("snowy/houses/snowy_butchers_shop_2", Arrays.asList(
			new Pair<>(new Quad<>("village/common/butcher_animals", "bottom", "up_north", "dirt"), new BPos(5, 0, 2)),
			new Pair<>(new Quad<>("empty", "building_entrance", "west_up", "snow_block"), new BPos(0, 1, 3))
		));
		this.put("snowy/houses/snowy_cartographer_house_1", Collections.singletonList(
			new Pair<>(new Quad<>("empty", "building_entrance", "west_up", "spruce_stairs[facing=east]"), new BPos(0, 0, 5))
		));
		this.put("snowy/houses/snowy_farm_1", Collections.singletonList(
			new Pair<>(new Quad<>("empty", "building_entrance", "west_up", "stripped_spruce_wood"), new BPos(0, 0, 3))
		));
		this.put("snowy/houses/snowy_farm_2", Collections.singletonList(
			new Pair<>(new Quad<>("empty", "building_entrance", "west_up", "snow_block"), new BPos(0, 0, 3))
		));
		this.put("snowy/houses/snowy_fisher_cottage", Collections.singletonList(
			new Pair<>(new Quad<>("empty", "building_entrance", "south_up", "structure_void"), new BPos(1, 1, 6))
		));
		this.put("snowy/houses/snowy_fletcher_house_1", Collections.singletonList(
			new Pair<>(new Quad<>("empty", "building_entrance", "west_up", "spruce_stairs[facing=east]"), new BPos(0, 0, 3))
		));
		this.put("snowy/houses/snowy_library_1", Collections.singletonList(
			new Pair<>(new Quad<>("empty", "building_entrance", "west_up", "structure_void"), new BPos(0, 0, 3))
		));
		this.put("snowy/houses/snowy_masons_house_1", Collections.singletonList(
			new Pair<>(new Quad<>("empty", "building_entrance", "south_up", "spruce_stairs[facing=north]"), new BPos(4, 0, 8))
		));
		this.put("snowy/houses/snowy_masons_house_2", Collections.singletonList(
			new Pair<>(new Quad<>("empty", "building_entrance", "west_up", "spruce_stairs[facing=east]"), new BPos(0, 0, 4))
		));
		this.put("snowy/houses/snowy_medium_house_1", Arrays.asList(
			new Pair<>(new Quad<>("village/snowy/villagers", "bottom", "up_north", "grass_block"), new BPos(3, 0, 2)),
			new Pair<>(new Quad<>("village/snowy/villagers", "bottom", "up_north", "grass_block"), new BPos(3, 0, 4)),
			new Pair<>(new Quad<>("empty", "building_entrance", "east_up", "snow_block"), new BPos(6, 1, 4))
		));
		this.put("snowy/houses/snowy_medium_house_2", Arrays.asList(
			new Pair<>(new Quad<>("village/snowy/villagers", "bottom", "up_north", "spruce_planks"), new BPos(5, 0, 3)),
			new Pair<>(new Quad<>("empty", "building_entrance", "north_up", "snow_block"), new BPos(7, 0, 0)),
			new Pair<>(new Quad<>("village/snowy/villagers", "bottom", "up_north", "spruce_planks"), new BPos(9, 0, 3))
		));
		this.put("snowy/houses/snowy_medium_house_3", Arrays.asList(
			new Pair<>(new Quad<>("village/snowy/villagers", "bottom", "up_north", "grass_block"), new BPos(2, 0, 2)),
			new Pair<>(new Quad<>("village/snowy/villagers", "bottom", "up_north", "grass_block"), new BPos(2, 0, 4)),
			new Pair<>(new Quad<>("empty", "building_entrance", "west_up", "packed_ice"), new BPos(0, 1, 2))
		));
		this.put("snowy/houses/snowy_shepherds_house_1", Arrays.asList(
			new Pair<>(new Quad<>("empty", "buidling_entrance", "west_up", "structure_void"), new BPos(0, 0, 6)),
			new Pair<>(new Quad<>("village/common/sheep", "bottom", "up_north", "grass_block"), new BPos(1, 0, 2))
		));
		this.put("snowy/houses/snowy_small_house_1", Arrays.asList(
			new Pair<>(new Quad<>("village/snowy/villagers", "bottom", "up_north", "grass_block"), new BPos(3, 0, 2)),
			new Pair<>(new Quad<>("empty", "building_entrance", "south_up", "structure_void"), new BPos(3, 1, 5))
		));
		this.put("snowy/houses/snowy_small_house_2", Arrays.asList(
			new Pair<>(new Quad<>("empty", "building_entrance", "north_up", "spruce_stairs[facing=south]"), new BPos(3, 0, 0)),
			new Pair<>(new Quad<>("village/snowy/villagers", "bottom", "up_north", "spruce_planks"), new BPos(3, 0, 3))
		));
		this.put("snowy/houses/snowy_small_house_3", Arrays.asList(
			new Pair<>(new Quad<>("village/snowy/villagers", "bottom", "up_north", "spruce_planks"), new BPos(3, 0, 2)),
			new Pair<>(new Quad<>("empty", "building_entrance", "south_up", "structure_void"), new BPos(3, 0, 6))
		));
		this.put("snowy/houses/snowy_small_house_4", Arrays.asList(
			new Pair<>(new Quad<>("village/snowy/villagers", "bottom", "up_north", "grass_block"), new BPos(4, 0, 3)),
			new Pair<>(new Quad<>("empty", "building_entrance", "west_up", "structure_void"), new BPos(0, 1, 3))
		));
		this.put("snowy/houses/snowy_small_house_5", Arrays.asList(
			new Pair<>(new Quad<>("village/snowy/villagers", "bottom", "up_north", "grass_block"), new BPos(2, 0, 2)),
			new Pair<>(new Quad<>("empty", "building_entrance", "west_up", "blue_ice"), new BPos(0, 1, 3))
		));
		this.put("snowy/houses/snowy_small_house_6", Arrays.asList(
			new Pair<>(new Quad<>("empty", "building_entrance", "west_up", "spruce_stairs[facing=east]"), new BPos(0, 0, 3)),
			new Pair<>(new Quad<>("village/snowy/villagers", "bottom", "up_north", "spruce_planks"), new BPos(3, 0, 3))
		));
		this.put("snowy/houses/snowy_small_house_7", Arrays.asList(
			new Pair<>(new Quad<>("empty", "building_entrance", "west_up", "spruce_stairs[facing=east]"), new BPos(0, 0, 3)),
			new Pair<>(new Quad<>("village/snowy/villagers", "bottom", "up_north", "stripped_spruce_log"), new BPos(2, 0, 3))
		));
		this.put("snowy/houses/snowy_small_house_8", Arrays.asList(
			new Pair<>(new Quad<>("village/snowy/villagers", "bottom", "up_north", "dirt"), new BPos(2, 0, 2)),
			new Pair<>(new Quad<>("empty", "building_entrance", "west_up", "snow_block"), new BPos(0, 1, 1))
		));
		this.put("snowy/houses/snowy_tannery_1", Collections.singletonList(
			new Pair<>(new Quad<>("empty", "building_entrance", "west_up", "spruce_stairs[facing=east]"), new BPos(0, 0, 4))
		));
		this.put("snowy/houses/snowy_temple_1", Collections.singletonList(
			new Pair<>(new Quad<>("empty", "building_entrance", "west_up", "spruce_stairs[facing=east]"), new BPos(0, 0, 3))
		));
		this.put("snowy/houses/snowy_tool_smith_1", Collections.singletonList(
			new Pair<>(new Quad<>("empty", "building_entrance", "west_up", "structure_void"), new BPos(0, 0, 4))
		));
		this.put("snowy/houses/snowy_weapon_smith_1", Collections.singletonList(
			new Pair<>(new Quad<>("empty", "building_entrance", "west_up", "structure_void"), new BPos(0, 0, 4))
		));
		this.put("snowy/streets/corner_01", Arrays.asList(
			new Pair<>(new Quad<>("village/snowy/decor", "bottom", "up_north", "grass_block"), new BPos(1, 0, 1)),
			new Pair<>(new Quad<>("village/snowy/decor", "bottom", "up_north", "grass_block"), new BPos(1, 0, 6)),
			new Pair<>(new Quad<>("village/common/cats", "bottom", "up_north", "grass_block"), new BPos(1, 0, 10)),
			new Pair<>(new Quad<>("village/snowy/decor", "bottom", "up_north", "grass_block"), new BPos(4, 0, 12)),
			new Pair<>(new Quad<>("village/snowy/decor", "bottom", "up_north", "grass_block"), new BPos(6, 0, 3)),
			new Pair<>(new Quad<>("village/snowy/decor", "bottom", "up_north", "grass_block"), new BPos(9, 0, 9)),
			new Pair<>(new Quad<>("village/snowy/streets", "street", "west_up", "structure_void"), new BPos(0, 1, 8)),
			new Pair<>(new Quad<>("village/snowy/streets", "street", "north_up", "structure_void"), new BPos(3, 1, 0)),
			new Pair<>(new Quad<>("village/snowy/houses", "building_entrance", "east_up", "structure_void"), new BPos(4, 1, 6))
		));
		this.put("snowy/streets/corner_02", Arrays.asList(
			new Pair<>(new Quad<>("village/snowy/streets", "street", "north_up", "structure_void"), new BPos(1, 1, 0)),
			new Pair<>(new Quad<>("village/snowy/houses", "building_entrance", "north_up", "structure_void"), new BPos(9, 1, 12)),
			new Pair<>(new Quad<>("village/snowy/streets", "street", "east_up", "structure_void"), new BPos(15, 1, 14))
		));
		this.put("snowy/streets/corner_03", Arrays.asList(
			new Pair<>(new Quad<>("village/snowy/streets", "street", "south_up", "structure_void"), new BPos(1, 1, 3)),
			new Pair<>(new Quad<>("village/snowy/streets", "street", "east_up", "structure_void"), new BPos(3, 1, 1))
		));
		this.put("snowy/streets/crossroad_01", Arrays.asList(
			new Pair<>(new Quad<>("village/snowy/decor", "bottom", "up_north", "dirt"), new BPos(13, 0, 12)),
			new Pair<>(new Quad<>("village/snowy/houses", "building_entrance", "west_up", "structure_void"), new BPos(7, 1, 3)),
			new Pair<>(new Quad<>("village/snowy/houses", "building_entrance", "west_up", "structure_void"), new BPos(7, 1, 4)),
			new Pair<>(new Quad<>("village/snowy/houses", "building_entrance", "west_up", "structure_void"), new BPos(7, 1, 5)),
			new Pair<>(new Quad<>("village/snowy/houses", "building_entrance", "west_up", "structure_void"), new BPos(7, 1, 6)),
			new Pair<>(new Quad<>("village/snowy/houses", "building_entrance", "west_up", "structure_void"), new BPos(7, 1, 7)),
			new Pair<>(new Quad<>("village/snowy/houses", "building_entrance", "west_up", "structure_void"), new BPos(7, 1, 11)),
			new Pair<>(new Quad<>("village/snowy/houses", "building_entrance", "west_up", "structure_void"), new BPos(7, 1, 12)),
			new Pair<>(new Quad<>("village/snowy/streets", "street", "north_up", "structure_void"), new BPos(8, 1, 0)),
			new Pair<>(new Quad<>("village/snowy/streets", "street", "south_up", "structure_void"), new BPos(8, 1, 15)),
			new Pair<>(new Quad<>("village/snowy/houses", "building_entrance", "east_up", "structure_void"), new BPos(9, 1, 3)),
			new Pair<>(new Quad<>("village/snowy/houses", "building_entrance", "east_up", "structure_void"), new BPos(9, 1, 4)),
			new Pair<>(new Quad<>("village/snowy/streets", "street", "east_up", "structure_void"), new BPos(15, 1, 8))
		));
		this.put("snowy/streets/crossroad_02", Arrays.asList(
			new Pair<>(new Quad<>("village/snowy/decor", "bottom", "up_north", "grass_block"), new BPos(1, 0, 11)),
			new Pair<>(new Quad<>("village/snowy/decor", "bottom", null, "grass_block"), new BPos(3, 0, 5)),
			new Pair<>(new Quad<>("village/snowy/decor", "bottom", null, "grass_block"), new BPos(5, 0, 1)),
			new Pair<>(new Quad<>("village/snowy/decor", "bottom", "up_north", "grass_block"), new BPos(5, 0, 13)),
			new Pair<>(new Quad<>("village/snowy/decor", "bottom", "up_north", "grass_block"), new BPos(8, 0, 8)),
			new Pair<>(new Quad<>("village/snowy/decor", "bottom", null, "grass_block"), new BPos(11, 0, 5)),
			new Pair<>(new Quad<>("village/snowy/decor", "bottom", null, "grass_block"), new BPos(13, 0, 12)),
			new Pair<>(new Quad<>("village/snowy/decor", "bottom", null, "grass_block"), new BPos(14, 0, 2)),
			new Pair<>(new Quad<>("village/snowy/streets", "street", "west_up", "structure_void"), new BPos(0, 1, 8)),
			new Pair<>(new Quad<>("village/snowy/houses", "building_entrance", "north_up", "structure_void"), new BPos(3, 1, 7)),
			new Pair<>(new Quad<>("village/snowy/streets", "street", "north_up", "structure_void"), new BPos(8, 1, 0)),
			new Pair<>(new Quad<>("village/snowy/streets", "street", "south_up", "structure_void"), new BPos(8, 1, 15)),
			new Pair<>(new Quad<>("village/snowy/houses", "building_entrance", "east_up", "structure_void"), new BPos(9, 1, 3)),
			new Pair<>(new Quad<>("village/snowy/houses", "building_entrance", "east_up", "structure_void"), new BPos(9, 1, 12)),
			new Pair<>(new Quad<>("village/snowy/streets", "street", null, "structure_void"), new BPos(15, 1, 8))
		));
		this.put("snowy/streets/crossroad_03", Arrays.asList(
			new Pair<>(new Quad<>("village/snowy/decor", "bottom", null, "grass_block"), new BPos(3, 0, 13)),
			new Pair<>(new Quad<>("village/snowy/decor", "bottom", "up_north", "grass_block"), new BPos(4, 0, 7)),
			new Pair<>(new Quad<>("village/snowy/decor", "bottom", null, "grass_block"), new BPos(8, 0, 11)),
			new Pair<>(new Quad<>("village/snowy/decor", "bottom", "up_north", "grass_block"), new BPos(11, 0, 9)),
			new Pair<>(new Quad<>("village/snowy/streets", "street", "west_up", "structure_void"), new BPos(0, 1, 8)),
			new Pair<>(new Quad<>("village/snowy/streets", "street", "north_up", "structure_void"), new BPos(4, 1, 0)),
			new Pair<>(new Quad<>("empty", "empty", null, "structure_void"), new BPos(4, 1, 7)),
			new Pair<>(new Quad<>("village/snowy/houses", "building_entrance", "south_up", "structure_void"), new BPos(5, 1, 9)),
			new Pair<>(new Quad<>("village/snowy/houses", "building_entrance", "north_up", "structure_void"), new BPos(11, 1, 7)),
			new Pair<>(new Quad<>("village/snowy/streets", "street", null, "structure_void"), new BPos(11, 1, 15)),
			new Pair<>(new Quad<>("village/snowy/streets", "street", "south_up", "structure_void"), new BPos(11, 1, 16)),
			new Pair<>(new Quad<>("village/snowy/streets", "street", "east_up", "structure_void"), new BPos(15, 1, 8))
		));
		this.put("snowy/streets/crossroad_04", Arrays.asList(
			new Pair<>(new Quad<>("village/snowy/streets", "street", "west_up", "structure_void"), new BPos(0, 1, 2)),
			new Pair<>(new Quad<>("village/snowy/streets", "street", "north_up", "structure_void"), new BPos(2, 1, 0)),
			new Pair<>(new Quad<>("village/snowy/streets", "street", "south_up", "structure_void"), new BPos(2, 1, 4))
		));
		this.put("snowy/streets/crossroad_05", Arrays.asList(
			new Pair<>(new Quad<>("village/snowy/streets", "street", "west_up", "structure_void"), new BPos(0, 1, 2)),
			new Pair<>(new Quad<>("village/snowy/streets", "street", "north_up", "structure_void"), new BPos(2, 1, 0)),
			new Pair<>(new Quad<>("village/snowy/streets", "street", "south_up", "structure_void"), new BPos(2, 1, 4)),
			new Pair<>(new Quad<>("village/snowy/streets", "street", "east_up", "structure_void"), new BPos(4, 1, 2))
		));
		this.put("snowy/streets/crossroad_06", Arrays.asList(
			new Pair<>(new Quad<>("village/snowy/decor", "bottom", "up_north", "grass_block"), new BPos(2, 0, 2)),
			new Pair<>(new Quad<>("village/snowy/streets", "street", "west_up", "structure_void"), new BPos(0, 1, 2)),
			new Pair<>(new Quad<>("village/snowy/streets", "street", "north_up", "structure_void"), new BPos(2, 1, 0)),
			new Pair<>(new Quad<>("village/snowy/streets", "street", "south_up", "structure_void"), new BPos(2, 1, 4)),
			new Pair<>(new Quad<>("village/snowy/streets", "street", "east_up", "structure_void"), new BPos(4, 1, 2))
		));
		this.put("snowy/streets/square_01", Arrays.asList(
			new Pair<>(new Quad<>("village/snowy/decor", "bottom", "up_north", "grass_block"), new BPos(14, 0, 5)),
			new Pair<>(new Quad<>("village/snowy/decor", "bottom", "up_north", "structure_void"), new BPos(19, 0, 2)),
			new Pair<>(new Quad<>("village/snowy/houses", "building_entrance", "west_up", "structure_void"), new BPos(11, 1, 5)),
			new Pair<>(new Quad<>("village/snowy/houses", "building_entrance", "south_up", "structure_void"), new BPos(14, 1, 8)),
			new Pair<>(new Quad<>("village/snowy/streets", "street", "north_up", "structure_void"), new BPos(18, 1, 0))
		));
		this.put("snowy/streets/straight_01", Arrays.asList(
			new Pair<>(new Quad<>("village/snowy/decor", "bottom", "up_north", "grass_block"), new BPos(2, 0, 13)),
			new Pair<>(new Quad<>("village/snowy/decor", "bottom", "up_north", "grass_block"), new BPos(4, 0, 7)),
			new Pair<>(new Quad<>("village/snowy/decor", "bottom", "up_north", "grass_block"), new BPos(11, 0, 6)),
			new Pair<>(new Quad<>("village/snowy/decor", "bottom", "up_north", "grass_block"), new BPos(11, 0, 13)),
			new Pair<>(new Quad<>("village/snowy/decor", "bottom", "up_north", "grass_block"), new BPos(12, 0, 4)),
			new Pair<>(new Quad<>("village/snowy/streets", "street", "north_up", "structure_void"), new BPos(7, 1, 0)),
			new Pair<>(new Quad<>("village/snowy/streets", "street", "south_up", "structure_void"), new BPos(7, 1, 15))
		));
		this.put("snowy/streets/straight_02", Arrays.asList(
			new Pair<>(new Quad<>("village/snowy/streets", "street", "north_up", "structure_void"), new BPos(1, 1, 0)),
			new Pair<>(new Quad<>("village/snowy/streets", "street", "south_up", "structure_void"), new BPos(1, 1, 15)),
			new Pair<>(new Quad<>("village/snowy/houses", "building_entrance", "east_up", "structure_void"), new BPos(2, 1, 8))
		));
		this.put("snowy/streets/straight_03", Arrays.asList(
			new Pair<>(new Quad<>("village/snowy/decor", "bottom", "up_north", "grass_block"), new BPos(0, 0, 2)),
			new Pair<>(new Quad<>("village/snowy/decor", "bottom", "up_north", "grass_block"), new BPos(0, 0, 8)),
			new Pair<>(new Quad<>("village/snowy/streets", "street", "north_up", "structure_void"), new BPos(1, 1, 0)),
			new Pair<>(new Quad<>("village/snowy/streets", "street", "south_up", "structure_void"), new BPos(1, 1, 10)),
			new Pair<>(new Quad<>("village/snowy/houses", "building_entrance", "east_up", "structure_void"), new BPos(2, 1, 3)),
			new Pair<>(new Quad<>("village/snowy/houses", "building_entrance", "east_up", "structure_void"), new BPos(2, 1, 4)),
			new Pair<>(new Quad<>("village/snowy/houses", "building_entrance", "east_up", "structure_void"), new BPos(2, 1, 5)),
			new Pair<>(new Quad<>("village/snowy/houses", "building_entrance", "east_up", "structure_void"), new BPos(2, 1, 6)),
			new Pair<>(new Quad<>("village/snowy/houses", "building_entrance", "east_up", "structure_void"), new BPos(2, 1, 7))
		));
		this.put("snowy/streets/straight_04", Arrays.asList(
			new Pair<>(new Quad<>("village/snowy/decor", "bottom", "up_north", "grass_block"), new BPos(0, 0, 3)),
			new Pair<>(new Quad<>("village/common/cats", "bottom", "up_north", "grass_path"), new BPos(1, 0, 6)),
			new Pair<>(new Quad<>("village/snowy/streets", "street", "north_up", "structure_void"), new BPos(1, 1, 0)),
			new Pair<>(new Quad<>("village/snowy/streets", "street", "south_up", "structure_void"), new BPos(1, 1, 8)),
			new Pair<>(new Quad<>("village/snowy/houses", "building_entrance", "east_up", "structure_void"), new BPos(2, 1, 4))
		));
		this.put("snowy/streets/straight_06", Arrays.asList(
			new Pair<>(new Quad<>("village/snowy/decor", "bottom", "up_north", "dirt"), new BPos(8, 0, 3)),
			new Pair<>(new Quad<>("village/snowy/decor", "bottom", "up_north", "grass_block"), new BPos(9, 0, 9)),
			new Pair<>(new Quad<>("village/snowy/decor", "bottom", "up_north", "grass_block"), new BPos(9, 0, 15)),
			new Pair<>(new Quad<>("village/snowy/houses", "building_entrance", "west_up", "structure_void"), new BPos(7, 1, 3)),
			new Pair<>(new Quad<>("village/snowy/houses", "building_entrance", "west_up", "structure_void"), new BPos(7, 1, 4)),
			new Pair<>(new Quad<>("village/snowy/streets", "street", "north_up", "structure_void"), new BPos(8, 1, 0)),
			new Pair<>(new Quad<>("village/snowy/houses", "building_entrance", "west_up", "structure_void"), new BPos(8, 1, 8)),
			new Pair<>(new Quad<>("village/snowy/houses", "building_entrance", "west_up", "structure_void"), new BPos(8, 1, 9)),
			new Pair<>(new Quad<>("village/snowy/houses", "building_entrance", "west_up", "structure_void"), new BPos(8, 1, 14)),
			new Pair<>(new Quad<>("village/snowy/houses", "building_entrance", "west_up", "structure_void"), new BPos(8, 1, 15)),
			new Pair<>(new Quad<>("village/snowy/houses", "building_entrance", "east_up", "structure_void"), new BPos(9, 1, 2)),
			new Pair<>(new Quad<>("village/snowy/houses", "building_entrance", "east_up", "structure_void"), new BPos(9, 1, 3)),
			new Pair<>(new Quad<>("village/snowy/houses", "building_entrance", "east_up", "structure_void"), new BPos(9, 1, 4)),
			new Pair<>(new Quad<>("village/snowy/streets", "street", "south_up", "structure_void"), new BPos(9, 1, 17)),
			new Pair<>(new Quad<>("village/snowy/houses", "building_entrance", "east_up", "structure_void"), new BPos(10, 1, 8)),
			new Pair<>(new Quad<>("village/snowy/houses", "building_entrance", "east_up", "structure_void"), new BPos(10, 1, 9)),
			new Pair<>(new Quad<>("village/snowy/houses", "building_entrance", "east_up", "structure_void"), new BPos(10, 1, 10)),
			new Pair<>(new Quad<>("village/snowy/houses", "building_entrance", "east_up", "structure_void"), new BPos(10, 1, 14)),
			new Pair<>(new Quad<>("village/snowy/houses", "building_entrance", "east_up", "structure_void"), new BPos(10, 1, 15))
		));
		this.put("snowy/streets/straight_08", Arrays.asList(
			new Pair<>(new Quad<>("village/snowy/decor", "bottom", "up_north", "grass_block"), new BPos(1, 0, 3)),
			new Pair<>(new Quad<>("village/snowy/decor", "bottom", "up_north", "grass_block"), new BPos(1, 0, 13)),
			new Pair<>(new Quad<>("village/snowy/streets", "street", "north_up", "structure_void"), new BPos(1, 1, 0)),
			new Pair<>(new Quad<>("village/snowy/streets", "street", "south_up", "structure_void"), new BPos(1, 1, 16)),
			new Pair<>(new Quad<>("village/snowy/houses", "building_entrance", "east_up", "structure_void"), new BPos(2, 1, 7)),
			new Pair<>(new Quad<>("village/snowy/houses", "building_entrance", "east_up", "structure_void"), new BPos(2, 1, 8)),
			new Pair<>(new Quad<>("village/snowy/houses", "building_entrance", "east_up", "structure_void"), new BPos(2, 1, 9)),
			new Pair<>(new Quad<>("village/snowy/houses", "building_entrance", "east_up", "structure_void"), new BPos(2, 1, 10))
		));
		this.put("snowy/streets/turn_01", Arrays.asList(
			new Pair<>(new Quad<>("village/snowy/decor", "bottom", "up_north", "grass_block"), new BPos(8, 0, 1)),
			new Pair<>(new Quad<>("village/snowy/streets", "street", "north_up", "structure_void"), new BPos(5, 1, 0)),
			new Pair<>(new Quad<>("village/snowy/houses", "building_entrance", "west_up", "structure_void"), new BPos(7, 1, 6)),
			new Pair<>(new Quad<>("village/snowy/streets", "street", "south_up", "structure_void"), new BPos(8, 1, 7)),
			new Pair<>(new Quad<>("village/snowy/houses", "building_entrance", "east_up", "structure_void"), new BPos(9, 1, 4))
		));
		this.put("snowy/town_centers/snowy_meeting_point_1", Arrays.asList(
			new Pair<>(new Quad<>("village/common/iron_golem", "bottom", "up_north", "grass_path"), new BPos(1, 0, 2)),
			new Pair<>(new Quad<>("village/snowy/villagers", "bottom", "up_north", "grass_path"), new BPos(1, 0, 4)),
			new Pair<>(new Quad<>("village/snowy/decor", "bottom", "up_north", "grass_block"), new BPos(2, 0, 7)),
			new Pair<>(new Quad<>("village/common/cats", "bottom", "up_north", "grass_block"), new BPos(4, 0, 0)),
			new Pair<>(new Quad<>("village/common/cats", "bottom", "up_north", "grass_block"), new BPos(5, 0, 0)),
			new Pair<>(new Quad<>("village/common/cats", "bottom", "up_north", "grass_block"), new BPos(5, 0, 7)),
			new Pair<>(new Quad<>("village/snowy/villagers", "bottom", "up_north", "grass_path"), new BPos(8, 0, 3)),
			new Pair<>(new Quad<>("village/snowy/decor", "bottom", "up_north", "grass_block"), new BPos(10, 0, 1)),
			new Pair<>(new Quad<>("village/snowy/streets", "street", "west_up", "structure_void"), new BPos(0, 1, 5)),
			new Pair<>(new Quad<>("village/snowy/streets", "street", "north_up", "structure_void"), new BPos(2, 1, 0)),
			new Pair<>(new Quad<>("village/snowy/streets", "street", "south_up", "structure_void"), new BPos(8, 1, 7)),
			new Pair<>(new Quad<>("village/snowy/streets", "street", "east_up", "structure_void"), new BPos(11, 1, 5))
		));
		this.put("snowy/town_centers/snowy_meeting_point_2", Arrays.asList(
			new Pair<>(new Quad<>("village/common/cats", "bottom", "up_north", "grass_block"), new BPos(0, 0, 2)),
			new Pair<>(new Quad<>("village/snowy/villagers", "bottom", "up_north", "grass_path"), new BPos(1, 0, 2)),
			new Pair<>(new Quad<>("village/common/cats", "bottom", "up_north", "grass_block"), new BPos(2, 0, 0)),
			new Pair<>(new Quad<>("village/common/iron_golem", "bottom", "up_north", "grass_path"), new BPos(2, 0, 7)),
			new Pair<>(new Quad<>("village/snowy/villagers", "bottom", "up_north", "grass_path"), new BPos(3, 0, 1)),
			new Pair<>(new Quad<>("village/snowy/streets", "street", "west_up", "structure_void"), new BPos(0, 1, 4)),
			new Pair<>(new Quad<>("village/snowy/streets", "street", "north_up", "structure_void"), new BPos(5, 1, 0)),
			new Pair<>(new Quad<>("village/snowy/streets", "street", "south_up", "structure_void"), new BPos(5, 1, 8)),
			new Pair<>(new Quad<>("village/snowy/streets", "street", "east_up", "structure_void"), new BPos(10, 1, 4))
		));
		this.put("snowy/town_centers/snowy_meeting_point_3", Arrays.asList(
			new Pair<>(new Quad<>("village/common/cats", "bottom", "up_north", "grass_block"), new BPos(0, 0, 1)),
			new Pair<>(new Quad<>("village/common/iron_golem", "bottom", "up_north", "grass_block"), new BPos(1, 0, 0)),
			new Pair<>(new Quad<>("village/snowy/villagers", "bottom", "up_north", "grass_path"), new BPos(1, 0, 1)),
			new Pair<>(new Quad<>("village/snowy/villagers", "bottom", "up_north", "grass_path"), new BPos(1, 0, 5)),
			new Pair<>(new Quad<>("village/common/cats", "bottom", "up_north", "grass_block"), new BPos(6, 0, 6)),
			new Pair<>(new Quad<>("village/snowy/streets", "street", "west_up", "structure_void"), new BPos(0, 1, 3)),
			new Pair<>(new Quad<>("village/snowy/streets", "street", "north_up", "structure_void"), new BPos(3, 1, 0)),
			new Pair<>(new Quad<>("village/snowy/streets", "street", "south_up", "structure_void"), new BPos(3, 1, 6)),
			new Pair<>(new Quad<>("village/snowy/streets", "street", "east_up", "structure_void"), new BPos(6, 1, 3))
		));
		this.put("snowy/villagers/baby", Collections.singletonList(
			new Pair<>(new Quad<>("empty", "bottom", "down_south", "structure_void"), new BPos(0, 0, 0))
		));
		this.put("snowy/villagers/nitwi", Collections.singletonList(
			new Pair<>(new Quad<>("empty", "bottom", "down_south", "structure_void"), new BPos(0, 0, 0))
		));
		this.put("snowy/villagers/unemployed", Collections.singletonList(
			new Pair<>(new Quad<>("empty", "bottom", "down_south", "structure_void"), new BPos(0, 0, 0))
		));
		this.put("snowy/zombie/houses/snowy_medium_house_1", Arrays.asList(
			new Pair<>(new Quad<>("village/snowy/zombie/villagers", "bottom", "up_north", "grass_block"), new BPos(3, 0, 2)),
			new Pair<>(new Quad<>("village/snowy/zombie/villagers", "bottom", "up_north", "grass_block"), new BPos(3, 0, 4)),
			new Pair<>(new Quad<>("empty", "building_entrance", "east_up", "snow_block"), new BPos(6, 1, 4))
		));
		this.put("snowy/zombie/houses/snowy_medium_house_2", Arrays.asList(
			new Pair<>(new Quad<>("village/snowy/zombie/villagers", "bottom", "up_north", "spruce_planks"), new BPos(5, 0, 3)),
			new Pair<>(new Quad<>("empty", "building_entrance", "north_up", "snow_block"), new BPos(7, 0, 0)),
			new Pair<>(new Quad<>("village/snowy/zombie/villagers", "bottom", "up_north", "spruce_planks"), new BPos(9, 0, 3))
		));
		this.put("snowy/zombie/houses/snowy_medium_house_3", Arrays.asList(
			new Pair<>(new Quad<>("village/snowy/zombie/villagers", "bottom", "up_north", "grass_block"), new BPos(2, 0, 2)),
			new Pair<>(new Quad<>("village/snowy/zombie/villagers", "bottom", "up_north", "grass_block"), new BPos(2, 0, 4)),
			new Pair<>(new Quad<>("empty", "building_entrance", "west_up", "packed_ice"), new BPos(0, 1, 2))
		));
		this.put("snowy/zombie/houses/snowy_small_house_1", Arrays.asList(
			new Pair<>(new Quad<>("village/snowy/zombie/villagers", "bottom", "up_north", "grass_block"), new BPos(3, 0, 2)),
			new Pair<>(new Quad<>("empty", "building_entrance", "south_up", "structure_void"), new BPos(3, 1, 5))
		));
		this.put("snowy/zombie/houses/snowy_small_house_2", Arrays.asList(
			new Pair<>(new Quad<>("empty", "building_entrance", "north_up", "spruce_stairs[facing=south]"), new BPos(3, 0, 0)),
			new Pair<>(new Quad<>("village/snowy/zombie/villagers", "bottom", "up_north", "spruce_planks"), new BPos(3, 0, 3))
		));
		this.put("snowy/zombie/houses/snowy_small_house_3", Arrays.asList(
			new Pair<>(new Quad<>("village/snowy/zombie/villagers", "bottom", "up_north", "spruce_planks"), new BPos(3, 0, 2)),
			new Pair<>(new Quad<>("empty", "building_entrance", "south_up", "structure_void"), new BPos(3, 0, 6))
		));
		this.put("snowy/zombie/houses/snowy_small_house_4", Arrays.asList(
			new Pair<>(new Quad<>("village/snowy/zombie/villagers", "bottom", "up_north", "grass_block"), new BPos(4, 0, 3)),
			new Pair<>(new Quad<>("empty", "building_entrance", "west_up", "structure_void"), new BPos(0, 1, 3))
		));
		this.put("snowy/zombie/houses/snowy_small_house_5", Arrays.asList(
			new Pair<>(new Quad<>("village/snowy/zombie/villagers", "bottom", "up_north", "grass_block"), new BPos(2, 0, 2)),
			new Pair<>(new Quad<>("empty", "building_entrance", "west_up", "blue_ice"), new BPos(0, 1, 3))
		));
		this.put("snowy/zombie/houses/snowy_small_house_6", Arrays.asList(
			new Pair<>(new Quad<>("empty", "building_entrance", "west_up", "spruce_stairs[facing=east]"), new BPos(0, 0, 3)),
			new Pair<>(new Quad<>("village/snowy/zombie/villagers", "bottom", "up_north", "spruce_planks"), new BPos(3, 0, 3))
		));
		this.put("snowy/zombie/houses/snowy_small_house_7", Arrays.asList(
			new Pair<>(new Quad<>("empty", "building_entrance", "west_up", "spruce_stairs[facing=east]"), new BPos(0, 0, 3)),
			new Pair<>(new Quad<>("village/snowy/zombie/villagers", "bottom", "up_north", "stripped_spruce_log"), new BPos(2, 0, 3))
		));
		this.put("snowy/zombie/houses/snowy_small_house_8", Collections.singletonList(
			new Pair<>(new Quad<>("empty", "building_entrance", "west_up", "snow_block"), new BPos(0, 0, 1))
		));
		this.put("snowy/zombie/streets/corner_01", Arrays.asList(
			new Pair<>(new Quad<>("village/snowy/zombie/decor", "bottom", "up_north", "grass_block"), new BPos(1, 0, 1)),
			new Pair<>(new Quad<>("village/snowy/zombie/decor", "bottom", "up_north", "grass_block"), new BPos(1, 0, 6)),
			new Pair<>(new Quad<>("village/common/cats", "bottom", "up_north", "grass_block"), new BPos(1, 0, 10)),
			new Pair<>(new Quad<>("village/snowy/zombie/decor", "bottom", "up_north", "grass_block"), new BPos(4, 0, 12)),
			new Pair<>(new Quad<>("village/snowy/zombie/decor", "bottom", "up_north", "grass_block"), new BPos(6, 0, 3)),
			new Pair<>(new Quad<>("village/snowy/zombie/decor", "bottom", "up_north", "grass_block"), new BPos(9, 0, 9)),
			new Pair<>(new Quad<>("village/snowy/zombie/streets", "street", "west_up", "structure_void"), new BPos(0, 1, 8)),
			new Pair<>(new Quad<>("village/snowy/zombie/streets", "street", "north_up", "structure_void"), new BPos(3, 1, 0)),
			new Pair<>(new Quad<>("village/snowy/zombie/houses", "building_entrance", "east_up", "structure_void"), new BPos(4, 1, 6))
		));
		this.put("snowy/zombie/streets/corner_02", Arrays.asList(
			new Pair<>(new Quad<>("village/snowy/zombie/streets", "street", "north_up", "structure_void"), new BPos(1, 1, 0)),
			new Pair<>(new Quad<>("village/snowy/zombie/houses", "building_entrance", "north_up", "structure_void"), new BPos(9, 1, 12)),
			new Pair<>(new Quad<>("village/snowy/zombie/streets", "street", "east_up", "structure_void"), new BPos(15, 1, 14))
		));
		this.put("snowy/zombie/streets/corner_03", Arrays.asList(
			new Pair<>(new Quad<>("village/snowy/zombie/streets", "street", "south_up", "structure_void"), new BPos(1, 1, 3)),
			new Pair<>(new Quad<>("village/snowy/zombie/streets", "street", "east_up", "structure_void"), new BPos(3, 1, 1))
		));
		this.put("snowy/zombie/streets/crossroad_01", Arrays.asList(
			new Pair<>(new Quad<>("village/snowy/zombie/decor", "bottom", "up_north", "dirt"), new BPos(13, 0, 12)),
			new Pair<>(new Quad<>("village/snowy/zombie/houses", "building_entrance", "west_up", "structure_void"), new BPos(7, 1, 3)),
			new Pair<>(new Quad<>("village/snowy/zombie/houses", "building_entrance", "west_up", "structure_void"), new BPos(7, 1, 4)),
			new Pair<>(new Quad<>("village/snowy/zombie/houses", "building_entrance", "west_up", "structure_void"), new BPos(7, 1, 5)),
			new Pair<>(new Quad<>("village/snowy/zombie/houses", "building_entrance", "west_up", "structure_void"), new BPos(7, 1, 6)),
			new Pair<>(new Quad<>("village/snowy/zombie/houses", "building_entrance", "west_up", "structure_void"), new BPos(7, 1, 7)),
			new Pair<>(new Quad<>("village/snowy/zombie/houses", "building_entrance", "west_up", "structure_void"), new BPos(7, 1, 11)),
			new Pair<>(new Quad<>("village/snowy/zombie/houses", "building_entrance", "west_up", "structure_void"), new BPos(7, 1, 12)),
			new Pair<>(new Quad<>("village/snowy/zombie/streets", "street", "north_up", "structure_void"), new BPos(8, 1, 0)),
			new Pair<>(new Quad<>("village/snowy/zombie/streets", "street", "south_up", "structure_void"), new BPos(8, 1, 15)),
			new Pair<>(new Quad<>("village/snowy/zombie/houses", "building_entrance", "east_up", "structure_void"), new BPos(9, 1, 3)),
			new Pair<>(new Quad<>("village/snowy/zombie/houses", "building_entrance", "east_up", "structure_void"), new BPos(9, 1, 4)),
			new Pair<>(new Quad<>("village/snowy/zombie/streets", "street", "east_up", "structure_void"), new BPos(15, 1, 8))
		));
		this.put("snowy/zombie/streets/crossroad_02", Arrays.asList(
			new Pair<>(new Quad<>("village/snowy/zombie/decor", "bottom", "up_north", "grass_block"), new BPos(1, 0, 11)),
			new Pair<>(new Quad<>("village/snowy/zombie/decor", "bottom", null, "grass_block"), new BPos(3, 0, 5)),
			new Pair<>(new Quad<>("village/snowy/zombie/decor", "bottom", null, "grass_block"), new BPos(5, 0, 1)),
			new Pair<>(new Quad<>("village/snowy/zombie/decor", "bottom", "up_north", "grass_block"), new BPos(5, 0, 13)),
			new Pair<>(new Quad<>("village/snowy/zombie/decor", "bottom", "up_north", "grass_block"), new BPos(8, 0, 8)),
			new Pair<>(new Quad<>("village/snowy/zombie/decor", "bottom", null, "grass_block"), new BPos(11, 0, 5)),
			new Pair<>(new Quad<>("village/snowy/zombie/decor", "bottom", null, "grass_block"), new BPos(13, 0, 12)),
			new Pair<>(new Quad<>("village/snowy/zombie/decor", "bottom", null, "grass_block"), new BPos(14, 0, 2)),
			new Pair<>(new Quad<>("village/snowy/zombie/streets", "street", "west_up", "structure_void"), new BPos(0, 1, 8)),
			new Pair<>(new Quad<>("village/snowy/zombie/houses", "building_entrance", "north_up", "structure_void"), new BPos(3, 1, 7)),
			new Pair<>(new Quad<>("village/snowy/zombie/streets", "street", "north_up", "structure_void"), new BPos(8, 1, 0)),
			new Pair<>(new Quad<>("village/snowy/zombie/streets", "street", "south_up", "structure_void"), new BPos(8, 1, 15)),
			new Pair<>(new Quad<>("village/snowy/zombie/houses", "building_entrance", "east_up", "structure_void"), new BPos(9, 1, 3)),
			new Pair<>(new Quad<>("village/snowy/zombie/houses", "building_entrance", "east_up", "structure_void"), new BPos(9, 1, 12)),
			new Pair<>(new Quad<>("village/snowy/zombie/streets", "street", null, "structure_void"), new BPos(15, 1, 8))
		));
		this.put("snowy/zombie/streets/crossroad_03", Arrays.asList(
			new Pair<>(new Quad<>("village/snowy/zombie/decor", "bottom", null, "grass_block"), new BPos(3, 0, 13)),
			new Pair<>(new Quad<>("village/snowy/zombie/decor", "bottom", "up_north", "grass_block"), new BPos(4, 0, 7)),
			new Pair<>(new Quad<>("village/snowy/zombie/decor", "bottom", null, "grass_block"), new BPos(8, 0, 11)),
			new Pair<>(new Quad<>("village/snowy/zombie/decor", "bottom", "up_north", "grass_block"), new BPos(11, 0, 9)),
			new Pair<>(new Quad<>("village/snowy/zombie/streets", "street", "west_up", "structure_void"), new BPos(0, 1, 8)),
			new Pair<>(new Quad<>("village/snowy/zombie/streets", "street", "north_up", "structure_void"), new BPos(4, 1, 0)),
			new Pair<>(new Quad<>("empty", "empty", null, "structure_void"), new BPos(4, 1, 7)),
			new Pair<>(new Quad<>("village/snowy/zombie/houses", "building_entrance", "south_up", "structure_void"), new BPos(5, 1, 9)),
			new Pair<>(new Quad<>("village/snowy/zombie/houses", "building_entrance", "north_up", "structure_void"), new BPos(11, 1, 7)),
			new Pair<>(new Quad<>("village/snowy/zombie/streets", "street", null, "structure_void"), new BPos(11, 1, 15)),
			new Pair<>(new Quad<>("village/snowy/zombie/streets", "street", "south_up", "structure_void"), new BPos(11, 1, 16)),
			new Pair<>(new Quad<>("village/snowy/zombie/streets", "street", "east_up", "structure_void"), new BPos(15, 1, 8))
		));
		this.put("snowy/zombie/streets/crossroad_04", Arrays.asList(
			new Pair<>(new Quad<>("village/snowy/zombie/streets", "street", "west_up", "structure_void"), new BPos(0, 1, 2)),
			new Pair<>(new Quad<>("village/snowy/zombie/streets", "street", "north_up", "structure_void"), new BPos(2, 1, 0)),
			new Pair<>(new Quad<>("village/snowy/zombie/streets", "street", "south_up", "structure_void"), new BPos(2, 1, 4))
		));
		this.put("snowy/zombie/streets/crossroad_05", Arrays.asList(
			new Pair<>(new Quad<>("village/snowy/zombie/streets", "street", "west_up", "structure_void"), new BPos(0, 1, 2)),
			new Pair<>(new Quad<>("village/snowy/zombie/streets", "street", "north_up", "structure_void"), new BPos(2, 1, 0)),
			new Pair<>(new Quad<>("village/snowy/zombie/streets", "street", "south_up", "structure_void"), new BPos(2, 1, 4)),
			new Pair<>(new Quad<>("village/snowy/zombie/streets", "street", "east_up", "structure_void"), new BPos(4, 1, 2))
		));
		this.put("snowy/zombie/streets/crossroad_06", Arrays.asList(
			new Pair<>(new Quad<>("village/snowy/zombie/decor", "bottom", "up_north", "grass_block"), new BPos(2, 0, 2)),
			new Pair<>(new Quad<>("village/snowy/zombie/streets", "street", "west_up", "structure_void"), new BPos(0, 1, 2)),
			new Pair<>(new Quad<>("village/snowy/zombie/streets", "street", "north_up", "structure_void"), new BPos(2, 1, 0)),
			new Pair<>(new Quad<>("village/snowy/zombie/streets", "street", "south_up", "structure_void"), new BPos(2, 1, 4)),
			new Pair<>(new Quad<>("village/snowy/zombie/streets", "street", "east_up", "structure_void"), new BPos(4, 1, 2))
		));
		this.put("snowy/zombie/streets/square_01", Arrays.asList(
			new Pair<>(new Quad<>("village/snowy/zombie/decor", "bottom", "up_north", "grass_block"), new BPos(14, 0, 5)),
			new Pair<>(new Quad<>("village/snowy/zombie/decor", "bottom", "up_north", "structure_void"), new BPos(19, 0, 2)),
			new Pair<>(new Quad<>("village/snowy/zombie/houses", "building_entrance", "west_up", "structure_void"), new BPos(11, 1, 5)),
			new Pair<>(new Quad<>("village/snowy/zombie/houses", "building_entrance", "south_up", "structure_void"), new BPos(14, 1, 8)),
			new Pair<>(new Quad<>("village/snowy/zombie/streets", "street", "north_up", "structure_void"), new BPos(18, 1, 0))
		));
		this.put("snowy/zombie/streets/straight_01", Arrays.asList(
			new Pair<>(new Quad<>("village/snowy/zombie/decor", "bottom", "up_north", "grass_block"), new BPos(2, 0, 13)),
			new Pair<>(new Quad<>("village/snowy/zombie/decor", "bottom", "up_north", "grass_block"), new BPos(4, 0, 7)),
			new Pair<>(new Quad<>("village/snowy/zombie/decor", "bottom", "up_north", "grass_block"), new BPos(11, 0, 6)),
			new Pair<>(new Quad<>("village/snowy/zombie/decor", "bottom", "up_north", "grass_block"), new BPos(11, 0, 13)),
			new Pair<>(new Quad<>("village/snowy/zombie/decor", "bottom", "up_north", "grass_block"), new BPos(12, 0, 4)),
			new Pair<>(new Quad<>("village/snowy/zombie/streets", "street", "north_up", "structure_void"), new BPos(7, 1, 0)),
			new Pair<>(new Quad<>("village/snowy/zombie/streets", "street", "south_up", "structure_void"), new BPos(7, 1, 15))
		));
		this.put("snowy/zombie/streets/straight_02", Arrays.asList(
			new Pair<>(new Quad<>("village/snowy/zombie/streets", "street", "north_up", "structure_void"), new BPos(1, 1, 0)),
			new Pair<>(new Quad<>("village/snowy/zombie/streets", "street", "south_up", "structure_void"), new BPos(1, 1, 15)),
			new Pair<>(new Quad<>("village/snowy/zombie/houses", "building_entrance", "east_up", "structure_void"), new BPos(2, 1, 8))
		));
		this.put("snowy/zombie/streets/straight_03", Arrays.asList(
			new Pair<>(new Quad<>("village/snowy/zombie/decor", "bottom", "up_north", "grass_block"), new BPos(0, 0, 2)),
			new Pair<>(new Quad<>("village/snowy/zombie/decor", "bottom", "up_north", "grass_block"), new BPos(0, 0, 8)),
			new Pair<>(new Quad<>("village/snowy/zombie/streets", "street", "north_up", "structure_void"), new BPos(1, 1, 0)),
			new Pair<>(new Quad<>("village/snowy/zombie/streets", "street", "south_up", "structure_void"), new BPos(1, 1, 10)),
			new Pair<>(new Quad<>("village/snowy/zombie/houses", "building_entrance", "east_up", "structure_void"), new BPos(2, 1, 3)),
			new Pair<>(new Quad<>("village/snowy/zombie/houses", "building_entrance", "east_up", "structure_void"), new BPos(2, 1, 4)),
			new Pair<>(new Quad<>("village/snowy/zombie/houses", "building_entrance", "east_up", "structure_void"), new BPos(2, 1, 5)),
			new Pair<>(new Quad<>("village/snowy/zombie/houses", "building_entrance", "east_up", "structure_void"), new BPos(2, 1, 6)),
			new Pair<>(new Quad<>("village/snowy/zombie/houses", "building_entrance", "east_up", "structure_void"), new BPos(2, 1, 7))
		));
		this.put("snowy/zombie/streets/straight_04", Arrays.asList(
			new Pair<>(new Quad<>("village/snowy/zombie/decor", "bottom", "up_north", "grass_block"), new BPos(0, 0, 3)),
			new Pair<>(new Quad<>("village/common/cats", "bottom", "up_north", "grass_path"), new BPos(1, 0, 6)),
			new Pair<>(new Quad<>("village/snowy/zombie/streets", "street", "north_up", "structure_void"), new BPos(1, 1, 0)),
			new Pair<>(new Quad<>("village/snowy/zombie/streets", "street", "south_up", "structure_void"), new BPos(1, 1, 8)),
			new Pair<>(new Quad<>("village/snowy/zombie/houses", "building_entrance", "east_up", "structure_void"), new BPos(2, 1, 4))
		));
		this.put("snowy/zombie/streets/straight_06", Arrays.asList(
			new Pair<>(new Quad<>("village/snowy/zombie/decor", "bottom", "up_north", "dirt"), new BPos(8, 0, 3)),
			new Pair<>(new Quad<>("village/snowy/zombie/decor", "bottom", "up_north", "grass_block"), new BPos(9, 0, 9)),
			new Pair<>(new Quad<>("village/snowy/zombie/decor", "bottom", "up_north", "grass_block"), new BPos(9, 0, 15)),
			new Pair<>(new Quad<>("village/snowy/zombie/houses", "building_entrance", "west_up", "structure_void"), new BPos(7, 1, 3)),
			new Pair<>(new Quad<>("village/snowy/zombie/houses", "building_entrance", "west_up", "structure_void"), new BPos(7, 1, 4)),
			new Pair<>(new Quad<>("village/snowy/zombie/streets", "street", "north_up", "structure_void"), new BPos(8, 1, 0)),
			new Pair<>(new Quad<>("village/snowy/zombie/houses", "building_entrance", "west_up", "structure_void"), new BPos(8, 1, 8)),
			new Pair<>(new Quad<>("village/snowy/zombie/houses", "building_entrance", "west_up", "structure_void"), new BPos(8, 1, 9)),
			new Pair<>(new Quad<>("village/snowy/zombie/houses", "building_entrance", "west_up", "structure_void"), new BPos(8, 1, 14)),
			new Pair<>(new Quad<>("village/snowy/zombie/houses", "building_entrance", "west_up", "structure_void"), new BPos(8, 1, 15)),
			new Pair<>(new Quad<>("village/snowy/zombie/houses", "building_entrance", "east_up", "structure_void"), new BPos(9, 1, 2)),
			new Pair<>(new Quad<>("village/snowy/zombie/houses", "building_entrance", "east_up", "structure_void"), new BPos(9, 1, 3)),
			new Pair<>(new Quad<>("village/snowy/zombie/houses", "building_entrance", "east_up", "structure_void"), new BPos(9, 1, 4)),
			new Pair<>(new Quad<>("village/snowy/zombie/streets", "street", "south_up", "structure_void"), new BPos(9, 1, 17)),
			new Pair<>(new Quad<>("village/snowy/zombie/houses", "building_entrance", "east_up", "structure_void"), new BPos(10, 1, 8)),
			new Pair<>(new Quad<>("village/snowy/zombie/houses", "building_entrance", "east_up", "structure_void"), new BPos(10, 1, 9)),
			new Pair<>(new Quad<>("village/snowy/zombie/houses", "building_entrance", "east_up", "structure_void"), new BPos(10, 1, 10)),
			new Pair<>(new Quad<>("village/snowy/zombie/houses", "building_entrance", "east_up", "structure_void"), new BPos(10, 1, 14)),
			new Pair<>(new Quad<>("village/snowy/zombie/houses", "building_entrance", "east_up", "structure_void"), new BPos(10, 1, 15))
		));
		this.put("snowy/zombie/streets/straight_08", Arrays.asList(
			new Pair<>(new Quad<>("village/snowy/zombie/decor", "bottom", "up_north", "grass_block"), new BPos(1, 0, 3)),
			new Pair<>(new Quad<>("village/snowy/zombie/decor", "bottom", "up_north", "grass_block"), new BPos(1, 0, 13)),
			new Pair<>(new Quad<>("village/snowy/zombie/streets", "street", "north_up", "structure_void"), new BPos(1, 1, 0)),
			new Pair<>(new Quad<>("village/snowy/zombie/streets", "street", "south_up", "structure_void"), new BPos(1, 1, 16)),
			new Pair<>(new Quad<>("village/snowy/zombie/houses", "building_entrance", "east_up", "structure_void"), new BPos(2, 1, 7)),
			new Pair<>(new Quad<>("village/snowy/zombie/houses", "building_entrance", "east_up", "structure_void"), new BPos(2, 1, 8)),
			new Pair<>(new Quad<>("village/snowy/zombie/houses", "building_entrance", "east_up", "structure_void"), new BPos(2, 1, 9)),
			new Pair<>(new Quad<>("village/snowy/zombie/houses", "building_entrance", "east_up", "structure_void"), new BPos(2, 1, 10))
		));
		this.put("snowy/zombie/streets/turn_01", Arrays.asList(
			new Pair<>(new Quad<>("village/snowy/zombie/decor", "bottom", "up_north", "grass_block"), new BPos(8, 0, 1)),
			new Pair<>(new Quad<>("village/snowy/zombie/streets", "street", "north_up", "structure_void"), new BPos(5, 1, 0)),
			new Pair<>(new Quad<>("village/snowy/zombie/houses", "building_entrance", "west_up", "structure_void"), new BPos(7, 1, 6)),
			new Pair<>(new Quad<>("village/snowy/zombie/streets", "street", "south_up", "structure_void"), new BPos(8, 1, 7)),
			new Pair<>(new Quad<>("village/snowy/zombie/houses", "building_entrance", "east_up", "structure_void"), new BPos(9, 1, 4))
		));
		this.put("snowy/zombie/town_centers/snowy_meeting_point_1", Arrays.asList(
			new Pair<>(new Quad<>("village/snowy/zombie/decor", "bottom", "up_north", "grass_block"), new BPos(2, 0, 7)),
			new Pair<>(new Quad<>("village/common/cats", "bottom", "up_north", "grass_block"), new BPos(4, 0, 0)),
			new Pair<>(new Quad<>("village/common/cats", "bottom", "up_north", "grass_block"), new BPos(5, 0, 0)),
			new Pair<>(new Quad<>("village/common/cats", "bottom", "up_north", "grass_block"), new BPos(5, 0, 7)),
			new Pair<>(new Quad<>("village/snowy/zombie/decor", "bottom", "up_north", "grass_block"), new BPos(10, 0, 1)),
			new Pair<>(new Quad<>("village/snowy/zombie/streets", "street", "west_up", "structure_void"), new BPos(0, 1, 5)),
			new Pair<>(new Quad<>("village/snowy/zombie/streets", "street", "north_up", "structure_void"), new BPos(2, 1, 0)),
			new Pair<>(new Quad<>("village/snowy/zombie/streets", "street", "south_up", "structure_void"), new BPos(8, 1, 7)),
			new Pair<>(new Quad<>("village/snowy/zombie/streets", "street", "east_up", "structure_void"), new BPos(11, 1, 5))
		));
		this.put("snowy/zombie/town_centers/snowy_meeting_point_2", Arrays.asList(
			new Pair<>(new Quad<>("village/common/cats", "bottom", "up_north", "grass_block"), new BPos(0, 0, 2)),
			new Pair<>(new Quad<>("village/common/cats", "bottom", "up_north", "grass_block"), new BPos(2, 0, 0)),
			new Pair<>(new Quad<>("village/snowy/zombie/streets", "street", "west_up", "structure_void"), new BPos(0, 1, 4)),
			new Pair<>(new Quad<>("village/snowy/zombie/streets", "street", "north_up", "structure_void"), new BPos(5, 1, 0)),
			new Pair<>(new Quad<>("village/snowy/zombie/streets", "street", "south_up", "structure_void"), new BPos(5, 1, 8)),
			new Pair<>(new Quad<>("village/snowy/zombie/streets", "street", "east_up", "structure_void"), new BPos(10, 1, 4))
		));
		this.put("snowy/zombie/town_centers/snowy_meeting_point_3", Arrays.asList(
			new Pair<>(new Quad<>("village/common/cats", "bottom", "up_north", "grass_block"), new BPos(0, 0, 1)),
			new Pair<>(new Quad<>("village/common/cats", "bottom", "up_north", "grass_block"), new BPos(6, 0, 6)),
			new Pair<>(new Quad<>("village/snowy/zombie/streets", "street", "west_up", "structure_void"), new BPos(0, 1, 3)),
			new Pair<>(new Quad<>("village/snowy/zombie/streets", "street", "north_up", "structure_void"), new BPos(3, 1, 0)),
			new Pair<>(new Quad<>("village/snowy/zombie/streets", "street", "south_up", "structure_void"), new BPos(3, 1, 6)),
			new Pair<>(new Quad<>("village/snowy/zombie/streets", "street", "east_up", "structure_void"), new BPos(6, 1, 3))
		));
		this.put("snowy/zombie/villagers/nitwi", Collections.singletonList(
			new Pair<>(new Quad<>("empty", "bottom", "down_south", "structure_void"), new BPos(0, 0, 0))
		));
		this.put("snowy/zombie/villagers/unemployed", Collections.singletonList(
			new Pair<>(new Quad<>("empty", "bottom", "down_south", "structure_void"), new BPos(0, 0, 0))
		));
	}};
}
