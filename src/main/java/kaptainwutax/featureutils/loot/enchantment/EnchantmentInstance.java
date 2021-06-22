package kaptainwutax.featureutils.loot.enchantment;

import kaptainwutax.featureutils.loot.LootContext;

import java.util.HashSet;
import java.util.List;
import java.util.function.BiPredicate;

public class EnchantmentInstance extends Enchantment {
	private int level;

	public EnchantmentInstance(String name, Integer rarity, HashSet<String> category, Integer minLevel, Integer maxLevel, Integer level, HashSet<String> incompatible) {
		super(name, rarity, category, minLevel, maxLevel, (n, i) -> (n < 1 + (i * 10)), (n, i) -> (n > (6 + (i * 10))), incompatible);
		this.level = level;
	}

	public EnchantmentInstance(String name, Integer rarity, HashSet<String> category, Integer minLevel, Integer maxLevel, BiPredicate<Integer, Integer> minCost, BiPredicate<Integer, Integer> maxCost, int level, HashSet<String> incompatible, boolean isTreasure, boolean isDiscoverable) {
		super(name, rarity, category, minLevel, maxLevel, minCost, maxCost, incompatible, isTreasure, isDiscoverable);
		this.level = level;
	}

	public EnchantmentInstance(Enchantment e, int level) {
		this(e.getName(), e.getRarity(), e.getCategory(), e.getMinLevel(), e.getMaxLevel(), e.getIsLowerThanMinCost(), e.getIsHigherThanMaxCost(), level, e.getIncompatible(), e.isTreasure(), e.isDiscoverable());
	}

	public static int getTotalWeight(List<EnchantmentInstance> list) {
		int weightSum = 0;
		for(EnchantmentInstance weighedRandomInstance : list) {
			weightSum += weighedRandomInstance.getRarity();
		}
		return weightSum;
	}

	public static EnchantmentInstance getRandomItem(LootContext random, List<EnchantmentInstance> list, int weightSum) {
		return getWeightedItem(list, random.nextInt(weightSum));
	}

	public static EnchantmentInstance getWeightedItem(List<EnchantmentInstance> list, int n) {
		for(EnchantmentInstance weighedRandomItem : list) {
			if((n -= weighedRandomItem.getRarity()) >= 0) continue;
			return weighedRandomItem;
		}
		return null;
	}

	public static EnchantmentInstance getRandomItem(LootContext random, List<EnchantmentInstance> list) {
		return getRandomItem(random, list, getTotalWeight(list));
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

}
