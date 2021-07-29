package kaptainwutax.featureutils.loot.enchantment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import kaptainwutax.featureutils.loot.item.ItemStack;
import kaptainwutax.mcutils.version.MCVersion;

public class Enchantments {
	// @formatter:off
	private final static HashSet<String> ARMOR_TYPES = new HashSet<>(Arrays.asList("NETHERITE", "DIAMOND", "GOLDEN", "IRON", "LEATHER", "CHAINMAIL"));
	private final static HashSet<String> TOOL_TYPES = new HashSet<>(Arrays.asList("NETHERITE", "DIAMOND", "GOLDEN", "IRON", "STONE","WOODEN"));
	private final static HashSet<String> BOOKS = new HashSet<>(Arrays.asList("ENCHANTED_BOOK", "BOOK"));
	public final static HashSet<String> ARMOR_HEAD = new HashSet<String>() {{
		for (String type : ARMOR_TYPES) {
			add(type + "_HELMET");
		}
		addAll(BOOKS);
		add("TURTLE_HELMET");
	}};
	public final static HashSet<String> ARMOR_CHEST = new HashSet<String>() {{
		for (String type : ARMOR_TYPES) {
			add(type + "_CHESTPLATE");
		}
		addAll(BOOKS);
		add("ELYTRA");
	}};
	public final static HashSet<String> ARMOR_LEGGINGS = new HashSet<String>() {{
		for (String type : ARMOR_TYPES) {
			add(type + "_LEGGINGS");
		}
		addAll(BOOKS);
	}};
	public final static HashSet<String> ARMOR_FEET = new HashSet<String>() {{
		for (String type : ARMOR_TYPES) {
			add(type + "_BOOTS");
		}
		addAll(BOOKS);
	}};
	public final static HashSet<String> ARMOR = new HashSet<String>() {{
		addAll(ARMOR_HEAD);
		addAll(ARMOR_CHEST);
		addAll(ARMOR_LEGGINGS);
		addAll(ARMOR_FEET);
	}};

	public final static HashSet<String> SWORDS = new HashSet<String>() {{
		for (String type : TOOL_TYPES) {
			add(type + "_SWORD");
		}
	}};
	public final static HashSet<String> AXES = new HashSet<String>() {{
		for (String type : TOOL_TYPES) {
			add(type + "_AXE");
		}
	}};
	public final static HashSet<String> HOES = new HashSet<String>() {{
		for (String type : TOOL_TYPES) {
			add(type + "_HOE");
		}
	}};
	public final static HashSet<String> PICKAXES =new HashSet<String>() {{
		for (String type : TOOL_TYPES) {
			add(type + "_PICKAXE");
		}
	}};
	public final static HashSet<String> SHOVELS = new HashSet<String>() {{
		for (String type : TOOL_TYPES) {
			add(type + "_SHOVEL");
		}
	}};
	public final static HashSet<String> BOW = new HashSet<String>() {{
		add("BOW");
		addAll(BOOKS);
	}};
	public final static HashSet<String> CROSSBOW =  new HashSet<String>() {{
		add("CROSSBOW");
		addAll(BOOKS);
	}};
	public final static HashSet<String> FISHING_ROD =  new HashSet<String>() {{
		add("FISHING_ROD");
		addAll(BOOKS);
	}};
	public final static HashSet<String> TRIDENT =  new HashSet<String>() {{
		add("TRIDENT");
		addAll(BOOKS);
	}};
	public final static HashSet<String> BREAKABLE = new HashSet<String>() {{
		addAll(CROSSBOW);
		addAll(BOW);
		addAll(TRIDENT);
		addAll(FISHING_ROD);
		addAll(ARMOR);
		addAll(SWORDS);
		addAll(AXES);
		addAll(HOES);
		addAll(PICKAXES);
		addAll(SHOVELS);
	}};

	public final static HashSet<String> DIGGER = new HashSet<String>() {{
		addAll(HOES);
		addAll(PICKAXES);
		addAll(AXES);
		addAll(SHOVELS);
		addAll(BOOKS);
	}};

	public final static HashSet<String> WEAPON = new HashSet<String>() {{
		addAll(SWORDS);
		addAll(BOOKS);
	}};
	public final static HashSet<String> DAMAGE = new HashSet<String>() {{
		addAll(SWORDS);
		addAll(AXES);
		addAll(BOOKS);
	}};
	public final static HashSet<String> THORNS = new HashSet<String>() {{
		addAll(ARMOR);
		remove("ELYTRA");
	}};
	public final static HashSet<String> VANISHABLE = new HashSet<String>() {{
		addAll(BREAKABLE);
	}};
	public final static HashSet<String> SingleEnchants = new HashSet<>(Arrays.asList("aqua_affinity", "binding_curse", "channeling", "silk_touch", "flame", "infinity", "multishot", "quick_charge", "mending", "vanishing_curse"));
	public final static List<HashSet<String>> allCategories = new ArrayList<>(Arrays.asList(ARMOR, ARMOR_HEAD, ARMOR_CHEST, ARMOR_FEET, BOW, BREAKABLE, CROSSBOW, DIGGER, DAMAGE, FISHING_ROD, TRIDENT, WEAPON, VANISHABLE,THORNS));
	// @formatter:on
	private final static Integer COMMON = 10;
	private final static Integer UNCOMMON = 5;
	private final static Integer RARE = 2;
	private final static Integer VERY_RARE = 1;

	public static final ConcurrentHashMap<MCVersion,List<Enchantment>> CACHE_ENCHANTMENT_REGISTRY=new ConcurrentHashMap<>();

	public static boolean canApply(Enchantment enchantment, ItemStack item) {
		return enchantment.getCategory().contains(item.getItem().getName().toUpperCase());
	}

	public static List<Enchantment> removeAllNull(List<Enchantment> list) {
		list.removeIf(Objects::isNull);
		return list;
	}

	public static List<Enchantment> getFor(MCVersion version) {
		return CACHE_ENCHANTMENT_REGISTRY.computeIfAbsent(version,key->apply(new ArrayList<>(),version));
	}

	public static List<Enchantment> apply(List<Enchantment> enchantments,MCVersion version){
		enchantments.clear();

		enchantments.add(new Enchantment("protection", COMMON, ARMOR, 1, 4, (i, n) -> (n < 1 + (i - 1) * 11), (i, n) -> (n > 1 + (i - 1) * 11 + 11), new HashSet<>(Arrays.asList("protection", "fire_protection", "projectile_protection", "blast_protection"))));
		enchantments.add(new Enchantment("fire_protection", UNCOMMON, ARMOR, 1, 4, (i, n) -> (n < 10 + (i - 1) * 8), (i, n) -> (n > 10 + (i - 1) * 8 + 8), new HashSet<>(Arrays.asList("protection", "fire_protection", "projectile_protection", "blast_protection"))));
		enchantments.add(new Enchantment("feather_falling", UNCOMMON, ARMOR_FEET, 1, 4, (i, n) -> (n < 5 + (i - 1) * 6), (i, n) -> (n > 5 + (i - 1) * 6 + 6), new HashSet<>(Collections.singletonList("feather_falling"))));
		enchantments.add(new Enchantment("blast_protection", RARE, ARMOR, 1, 4, (i, n) -> (n < 5 + (i - 1) * 8), (i, n) -> (n > 5 + (i - 1) * 8 + 8), new HashSet<>(Arrays.asList("protection", "fire_protection", "projectile_protection", "blast_protection"))));
		enchantments.add(new Enchantment("projectile_protection", UNCOMMON, ARMOR, 1, 4, (i, n) -> (n < 3 + (i - 1) * 6), (i, n) -> (n > 3 + (i - 1) * 6 + 6), new HashSet<>(Arrays.asList("protection", "fire_protection", "projectile_protection", "blast_protection"))));
		enchantments.add(new Enchantment("respiration", RARE, ARMOR_HEAD, 1, 3, (i, n) -> (n < 10 * i), (i, n) -> (n > 10 * i + 30), new HashSet<>(Collections.singletonList("respiration"))));
		enchantments.add(new Enchantment("aqua_affinity", RARE, ARMOR_HEAD, 1, 1, (i, n) -> (n < 1), (i, n) -> (n > 41), new HashSet<>(Collections.singletonList("aqua_affinity"))));
		enchantments.add(new Enchantment("thorns", VERY_RARE, THORNS, 1, 3, (i, n) -> (n < 10 + (20 * (i - 1))), (i, n) -> (n > 10 + (20 * (i - 1)) + 50), new HashSet<>(Collections.singletonList("thorns"))));

		if (version.isNewerOrEqualTo(MCVersion.v1_8))
			enchantments.add(new Enchantment("depth_strider", RARE, ARMOR_FEET, 1, 3, (i, n) -> (n < i * 10), (i, n) -> (n > i * 10 + 15), new HashSet<>(Arrays.asList("frost_walker", "depth_strider"))));
		if (version.isNewerOrEqualTo(MCVersion.v1_9))
			enchantments.add(new Enchantment("frost_walker", RARE, ARMOR_FEET, 1, 2, (i, n) -> (n < i * 10), (i, n) -> (n > i * 10 + 15), new HashSet<>(Arrays.asList("frost_walker", "depth_strider")), true));
		if (version.isNewerOrEqualTo(MCVersion.v1_11))
			enchantments.add(new Enchantment("binding_curse", VERY_RARE, ARMOR, 1, 1, (i, n) -> (n < 25), (i, n) -> (n > 50), new HashSet<>(Collections.singletonList("binding_curse")), true));
		if (version.isNewerOrEqualTo(MCVersion.v1_16))
			enchantments.add(new Enchantment("soul_speed", VERY_RARE, ARMOR_FEET, 1, 3, (i, n) -> (n < i * 10), (i, n) -> (n > i * 10 + 15), new HashSet<>(Collections.singletonList("soul_speed")), true, false));

		enchantments.add(new Enchantment("sharpness", COMMON, DAMAGE, 1, 5, (i, n) -> (n < 1 + (i - 1) * 11), (i, n) -> (n > 1 + (i - 1) * 11 + 20), new HashSet<>(Arrays.asList("sharpness", "smite", "bane_of_arthropods"))));
		enchantments.add(new Enchantment("smite", UNCOMMON, DAMAGE, 1, 5, (i, n) -> (n < 5 + (i - 1) * 8), (i, n) -> (n > 5 + (i - 1) * 8 + 20), new HashSet<>(Arrays.asList("sharpness", "smite", "bane_of_arthropods"))));
		enchantments.add(new Enchantment("bane_of_arthropods", UNCOMMON, DAMAGE, 1, 5, (i, n) -> (n < 5 + (i - 1) * 8), (i, n) -> (n > 5 + (i - 1) * 8 + 20), new HashSet<>(Arrays.asList("sharpness", "smite", "bane_of_arthropods"))));
		enchantments.add(new Enchantment("knockback", UNCOMMON, WEAPON, 1, 2, (i, n) -> (n < 5 + 20 * (i - 1)), (i, n) -> (n > 1 + (i * 10) + 50), new HashSet<>(Collections.singletonList("knockback"))));
		enchantments.add(new Enchantment("fire_aspect", RARE, WEAPON, 1, 2, (i, n) -> (n < 10 + 20 * (i - 1)), (i, n) -> (n > 1 + (i * 10) + 50), new HashSet<>(Collections.singletonList("fire_aspect"))));
		enchantments.add(new Enchantment("looting", RARE, WEAPON, 1, 3, (i, n) -> (n < 15 + (i - 1) * 9), (i, n) -> (n > 1 + (i * 10) + 50), new HashSet<>(Arrays.asList("looting", "silk_touch"))));

		if (version.isNewerOrEqualTo(MCVersion.v1_11_1))
			enchantments.add(new Enchantment("sweeping", RARE, WEAPON, 1, 3, (i, n) -> (n < 5 + (i - 1) * 9), (i, n) -> (n > 5 + (i - 1) * 9 + 15), new HashSet<>(Collections.singletonList("sweeping"))));

		enchantments.add(new Enchantment("efficiency", COMMON, DIGGER, 1, 5, (i, n) -> (n < (1 + 10 * (i - 1))), (i, n) -> (n > 1 + (i * 10) + 50), new HashSet<>(Collections.singletonList("efficiency"))));
		enchantments.add(new Enchantment("silk_touch", VERY_RARE, DIGGER, 1, 1, (i, n) -> (n < 15), (i, n) -> (n > 1 + (i * 10) + 50), new HashSet<>(Arrays.asList("fortune", "silk_touch"))));
		enchantments.add(new Enchantment("unbreaking", UNCOMMON, BREAKABLE, 1, 3, (i, n) -> (n < 5 + (i - 1) * 8), (i, n) -> (n > 1 + (i * 10) + 50), new HashSet<>(Collections.singletonList("unbreaking"))));
		enchantments.add(new Enchantment("fortune", RARE, DIGGER, 1, 3, (i, n) -> (n < 15 + (i - 1) * 9), (i, n) -> (n > 1 + (i * 10) + 50), new HashSet<>(Arrays.asList("fortune", "silk_touch"))));
		enchantments.add(new Enchantment("power", COMMON, BOW, 1, 5, (i, n) -> (n < 1 + (i - 1) * 10), (i, n) -> (n > 1 + (i - 1) * 10 + 15), new HashSet<>(Collections.singletonList("power"))));
		enchantments.add(new Enchantment("punch", RARE, BOW, 1, 2, (i, n) -> (n < 12 + (i - 1) * 20), (i, n) -> (n > 12 + (i - 1) * 20 + 25), new HashSet<>(Collections.singletonList("punch"))));
		enchantments.add(new Enchantment("flame", RARE, BOW, 1, 1, (i, n) -> (n < 20), (i, n) -> (n > 50), new HashSet<>(Collections.singletonList("flame"))));
		enchantments.add(new Enchantment("infinity", VERY_RARE, BOW, 1, 1, (i, n) -> (n < 20), (i, n) -> (n > 50), new HashSet<>(Arrays.asList("mending", "infinity"))));

		//The 1.8 is not correct, should be 1.7.2 but we don't have that so good enough
		if (version.isNewerOrEqualTo(MCVersion.v1_8))
			enchantments.add(new Enchantment("luck_of_the_sea", RARE, FISHING_ROD, 1, 3, (i, n) -> (n < 15 + (i - 1) * 9), (i, n) -> (n > 1 + (i * 10) + 50), new HashSet<>(Arrays.asList("luck_of_the_sea", "silk_touch"))));
		if (version.isNewerOrEqualTo(MCVersion.v1_8))
			enchantments.add(new Enchantment("lure", RARE, FISHING_ROD, 1, 3, (i, n) -> (n < 15 + (i - 1) * 9), (i, n) -> (n > 1 + (i * 10) + 50), new HashSet<>(Collections.singletonList("lure"))));
		if (version.isNewerOrEqualTo(MCVersion.v1_13))
			enchantments.add(new Enchantment("loyalty", UNCOMMON, TRIDENT, 1, 3, (i, n) -> (n < 5 + (i * 7)), (i, n) -> (n > 50), new HashSet<>(Arrays.asList("loyalty", "riptide"))));
		if (version.isNewerOrEqualTo(MCVersion.v1_13))
			enchantments.add(new Enchantment("impaling", RARE, TRIDENT, 1, 5, (i, n) -> (n < 1 + (i - 1) * 8), (i, n) -> (n > 1 + (i - 1) * 8 + 20), new HashSet<>(Collections.singletonList("impaling"))));
		if (version.isNewerOrEqualTo(MCVersion.v1_13))
			enchantments.add(new Enchantment("riptide", RARE, TRIDENT, 1, 3, (i, n) -> (n < 10 + (i * 7)), (i, n) -> (n > 50), new HashSet<>(Arrays.asList("riptide", "loyalty", "channeling"))));
		if (version.isNewerOrEqualTo(MCVersion.v1_13))
			enchantments.add(new Enchantment("channeling", VERY_RARE, TRIDENT, 1, 1, (i, n) -> (n < 25), (i, n) -> (n > 50), new HashSet<>(Arrays.asList("channeling", "riptide"))));
		if (version.isNewerOrEqualTo(MCVersion.v1_14))
			enchantments.add(new Enchantment("multishot", RARE, CROSSBOW, 1, 1, (i, n) -> (n < 20), (i, n) -> (n > 50), new HashSet<>(Arrays.asList("multishot", "piercing"))));
		if (version.isNewerOrEqualTo(MCVersion.v1_14))
			enchantments.add(new Enchantment("quick_charge", UNCOMMON, CROSSBOW, 1, 3, (i, n) -> (n < 12 + (i - 1) * 20), (i, n) -> (n > 50), new HashSet<>(Collections.singletonList("quick_charge"))));
		if (version.isNewerOrEqualTo(MCVersion.v1_14))
			enchantments.add(new Enchantment("piercing", COMMON, CROSSBOW, 1, 4, (i, n) -> (n < 1 + (i - 1) * 10), (i, n) -> (n > 50), new HashSet<>(Arrays.asList("multishot", "piercing"))));

		enchantments.add(new Enchantment("mending", RARE, BREAKABLE, 1, 1, (i, n) -> (n < i * 25), (i, n) -> (n > i * 25 + 50), new HashSet<>(Arrays.asList("mending", "infinity")), true));
		enchantments.add(new Enchantment("vanishing_curse", VERY_RARE, VANISHABLE, 1, 1, (i, n) -> (n < 25), (i, n) -> (n > 50), new HashSet<>(Collections.singletonList("vanishing_curse")), true));
		return enchantments;
	}

	public static HashSet<HashSet<String>> getCategories(ItemStack baseStack) {
		HashSet<HashSet<String>> applicableCategories = new HashSet<>();
		for(HashSet<String> category : allCategories) {
			if(category.contains(baseStack.getItem().getName().toUpperCase())) {
				applicableCategories.add(category);
			}
		}
		return applicableCategories;
	}

	public static List<Enchantment> getApplicableEnchantments(List<Enchantment> enchantments,HashSet<HashSet<String>> applicableCategories) {
		return getApplicableEnchantments(enchantments,applicableCategories, false, true);
	}

	public static List<Enchantment> getApplicableEnchantments(List<Enchantment> enchantments,HashSet<HashSet<String>> applicableCategories, boolean isTreasure) {
		return getApplicableEnchantments(enchantments,applicableCategories, isTreasure, true);
	}

	public static List<Enchantment> getApplicableEnchantments(List<Enchantment> enchantments,HashSet<HashSet<String>> applicableCategories, boolean isTreasure, boolean isDiscoverable) {
		List<Enchantment> applicableEnchantments = new ArrayList<>();
		List<String> applicableEnchantmentNames = new ArrayList<>();
		for(Enchantment currentEnchantment :enchantments) {
			if((!currentEnchantment.isTreasure() || isTreasure) && (currentEnchantment.isDiscoverable() == isDiscoverable)) {
				if(applicableCategories.contains(currentEnchantment.getCategory())) {
					if(!(applicableEnchantmentNames.contains(currentEnchantment.getName()))) {
						applicableEnchantments.add(currentEnchantment);
						applicableEnchantmentNames.add(currentEnchantment.getName());
					}
				}
			}
		}
		return applicableEnchantments;
	}

	public static void filterCompatibleEnchantments(ArrayList<EnchantmentInstance> list, EnchantmentInstance instance) {
		list.removeIf(e->e.getIncompatible().contains(instance.getName()) || instance.getIncompatible().contains(e.getName()));
	}

	public Enchantment getEnchantment(List<Enchantment> enchantments,String name) {
		for(Enchantment enchantment : enchantments) {
			if(enchantment.getName().equals(name)) {
				return enchantment;
			}
		}
		return null;
	}
}
