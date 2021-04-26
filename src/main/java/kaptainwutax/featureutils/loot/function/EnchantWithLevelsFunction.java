package kaptainwutax.featureutils.loot.function;

import kaptainwutax.featureutils.loot.LootContext;
import kaptainwutax.featureutils.loot.enchantment.Enchantment;
import kaptainwutax.featureutils.loot.enchantment.EnchantmentInstance;
import kaptainwutax.featureutils.loot.item.Item;
import kaptainwutax.featureutils.loot.item.ItemStack;
import kaptainwutax.mathutils.util.Mth;
import kaptainwutax.mcutils.util.data.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static kaptainwutax.featureutils.loot.enchantment.EnchantmentInstance.getRandomItem;
import static kaptainwutax.featureutils.loot.enchantment.Enchantments.*;

public class EnchantWithLevelsFunction implements LootFunction {
	private static final HashMap<String, Integer> enchantments;
	private final int minLevel;
	private final int maxLevel;
	private final boolean treasure;
	private final boolean discoverable;

	static {
		enchantments = new HashMap<>();
		enchantments.put("leather_helmet", 15);
		enchantments.put("leather_chestplate", 15);
		enchantments.put("leather_leggings", 15);
		enchantments.put("leather_boots", 15);
		enchantments.put("chainmail_helmet", 12);
		enchantments.put("chainmail_chestplate", 12);
		enchantments.put("chainmail_leggings", 12);
		enchantments.put("chainmail_boots", 12);
		enchantments.put("iron_helmet", 9);
		enchantments.put("iron_chestplate", 9);
		enchantments.put("iron_leggings", 9);
		enchantments.put("iron_boots", 9);
		enchantments.put("golden_helmet", 25);
		enchantments.put("golden_chestplate", 25);
		enchantments.put("golden_leggings", 25);
		enchantments.put("golden_boots", 25);
		enchantments.put("diamond_helmet", 10);
		enchantments.put("diamond_chestplate", 10);
		enchantments.put("diamond_leggings", 10);
		enchantments.put("diamond_boots", 10);
		enchantments.put("turtle_helmet", 9);
		enchantments.put("netherite_helmet", 15);
		enchantments.put("netherite_chestplate", 15);
		enchantments.put("netherite_leggings", 15);
		enchantments.put("netherite_boots", 15);
		enchantments.put("fishing_rod", 1);
		enchantments.put("book", 1);
		enchantments.put("wooden_pickaxe", 15);
		enchantments.put("wooden_axe", 15);
		enchantments.put("wooden_hoe", 15);
		enchantments.put("wooden_shovel", 15);
		enchantments.put("wooden_sword", 15);
		enchantments.put("stone_pickaxe", 5);
		enchantments.put("stone_axe", 5);
		enchantments.put("stone_hoe", 5);
		enchantments.put("stone_shovel", 5);
		enchantments.put("stone_sword", 5);
		enchantments.put("iron_pickaxe", 14);
		enchantments.put("iron_axe", 14);
		enchantments.put("iron_hoe", 14);
		enchantments.put("iron_shovel", 14);
		enchantments.put("iron_sword", 14);
		enchantments.put("golden_pickaxe", 22);
		enchantments.put("golden_axe", 22);
		enchantments.put("golden_hoe", 22);
		enchantments.put("golden_shovel", 22);
		enchantments.put("golden_sword", 22);
		enchantments.put("diamond_pickaxe", 10);
		enchantments.put("diamond_axe", 10);
		enchantments.put("diamond_hoe", 10);
		enchantments.put("diamond_shovel", 10);
		enchantments.put("diamond_sword", 10);
		enchantments.put("netherite_pickaxe", 15);
		enchantments.put("netherite_axe", 15);
		enchantments.put("netherite_hoe", 15);
		enchantments.put("netherite_shovel", 15);
		enchantments.put("netherite_sword", 15);
	}

	public EnchantWithLevelsFunction(int minLevel, int maxLevel) {
		this(minLevel, maxLevel, true, true);
	}

	public EnchantWithLevelsFunction(int minLevel, int maxLevel, boolean treasure) {
		this(minLevel, maxLevel, treasure, true);
	}

	public EnchantWithLevelsFunction(int minLevel, int maxLevel, boolean treasure, boolean discoverable) {
		this.minLevel = minLevel;
		this.maxLevel = maxLevel;
		this.treasure = treasure;
		this.discoverable = discoverable;
	}

	public ItemStack process(ItemStack itemStack, LootContext lootContext) {
		return this.enchantItem(lootContext, itemStack, getRandomValueFromBounds(lootContext), this.treasure, this.discoverable);
	}

	public ItemStack enchantItem(LootContext random, ItemStack itemStack, int n, boolean bl, boolean b2) {
		List<EnchantmentInstance> list = selectEnchantment(random, itemStack, n, bl, b2);
		for (EnchantmentInstance enchantmentInstance : list) {
			itemStack.getItem().addEnchantment(new Pair<>(enchantmentInstance.getName(), enchantmentInstance.getLevel()));
		}
		return itemStack;
	}

	public List<EnchantmentInstance> selectEnchantment(LootContext lootContext, ItemStack itemStack, int n, boolean bl, boolean b2) {
		ArrayList<EnchantmentInstance> arrayList = new ArrayList<>();
		Item item = itemStack.getItem();
		int n2;
		if (enchantments.containsKey(item.getName())) {
			n2 = enchantments.get(item.getName());
		} else {
			return arrayList;
		}
		n += 1 + lootContext.nextInt(n2 / 4 + 1) + lootContext.nextInt(n2 / 4 + 1);
		float f = (lootContext.nextFloat() + lootContext.nextFloat() - 1.0f) * 0.15f;
		List<EnchantmentInstance> list = getAvailableEnchantmentResults(n = Mth.clamp(Math.round((float) n + (float) n * f), 1, Integer.MAX_VALUE), itemStack, bl, b2);
		if (!list.isEmpty()) {
			arrayList.add(getRandomItem(lootContext, list));
			while (lootContext.nextInt(50) <= n) {
				filterCompatibleEnchantments(list, arrayList.get(arrayList.size() - 1));
				if (list.isEmpty()) break;
				arrayList.add(getRandomItem(lootContext, list));
				n /= 2;
			}
		}
		return arrayList;
	}

	public List<EnchantmentInstance> getAvailableEnchantmentResults(int n, ItemStack itemStack, boolean bl, boolean b2) {
		ArrayList<EnchantmentInstance> arrayList = new ArrayList<>();
		List<Enchantment> list = getApplicableEnchantments(getCategories(itemStack), this.treasure, this.discoverable);
		block0:
		for (Enchantment enchantment : list) {
			if ((enchantment.isTreasure() && !bl) || !(enchantment.isDiscoverable() == this.discoverable)) continue;
			for (int i = enchantment.getMaxLevel(); i > enchantment.getMinLevel() - 1; --i) {
				if (enchantment.getIsLowerThanMinCost().test(i, n) || enchantment.getIsHigherThanMaxCost().test(i, n)) continue;
				arrayList.add(new EnchantmentInstance(enchantment, i));
				continue block0;
			}
		}
		return arrayList;
	}

	public int getRandomValueFromBounds(LootContext lootContext) {
		if (this.minLevel == this.maxLevel) {
			return this.minLevel;
		} else {
			return lootContext.nextInt(this.maxLevel - this.minLevel + 1) + minLevel;
		}
	}
}
