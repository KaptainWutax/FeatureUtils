package kaptainwutax.featureutils.loot.function;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kaptainwutax.featureutils.loot.LootContext;
import kaptainwutax.featureutils.loot.enchantment.Enchantment;
import kaptainwutax.featureutils.loot.enchantment.EnchantmentInstance;
import kaptainwutax.featureutils.loot.enchantment.Enchantments;
import kaptainwutax.featureutils.loot.item.Item;
import kaptainwutax.featureutils.loot.item.ItemStack;
import kaptainwutax.mathutils.util.Mth;
import kaptainwutax.mcutils.util.data.Pair;

public class EnchantWithLevelsFunction implements LootFunction {
	private static final HashMap<String, Integer> enchantments;
	private final int minLevel;
	private final int maxLevel;
	private final boolean treasure;
	private final boolean discoverable;
	private final ArrayList<EnchantmentInstance>[] availableEnchantmentResults;

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

	public EnchantWithLevelsFunction(Item item, int minLevel, int maxLevel) {
		this(item, minLevel, maxLevel, true, true);
	}

	public EnchantWithLevelsFunction(Item item, int minLevel, int maxLevel, boolean treasure) {
		this(item, minLevel, maxLevel, treasure, true);
	}

	@SuppressWarnings("unchecked")
	public EnchantWithLevelsFunction(Item item, int minLevel, int maxLevel, boolean treasure, boolean discoverable) {
		this.minLevel = minLevel;
		this.maxLevel = maxLevel;
		this.treasure = treasure;
		this.discoverable = discoverable;

		int preprocessMaxLevel = ((int) (maxLevel * 2F));

		this.availableEnchantmentResults = new ArrayList[preprocessMaxLevel];

		for (int level = 0; level < preprocessMaxLevel; level++) {
			ArrayList<EnchantmentInstance> res = new ArrayList<>();
			List<Enchantment> list = Enchantments.getApplicableEnchantments(Enchantments.getCategories(new ItemStack(item)), this.treasure, this.discoverable);
			for(Enchantment enchantment : list) {
				if((!enchantment.isTreasure() || treasure) && enchantment.isDiscoverable() == discoverable) {
					for(int i = enchantment.getMaxLevel(); i > enchantment.getMinLevel() - 1; i--) {
						if(!enchantment.getIsLowerThanMinCost().test(i, level) && !enchantment.getIsHigherThanMaxCost().test(i, level)) {
							res.add(new EnchantmentInstance(enchantment, i));
							break;
						}
					}
				}
			}

			this.availableEnchantmentResults[level] = res;
		}

	}

	public ItemStack process(ItemStack itemStack, LootContext lootContext) {
		return this.enchantItem(lootContext, itemStack, getRandomValueFromBounds(lootContext), this.treasure, this.discoverable);
	}

	public ItemStack enchantItem(LootContext random, ItemStack itemStack, int level, boolean isTreasure, boolean isDiscoverable) {
		List<EnchantmentInstance> list = selectEnchantment(random, itemStack, level, isTreasure, isDiscoverable);
		for(EnchantmentInstance enchantmentInstance : list) {
			itemStack.getItem().addEnchantment(new Pair<>(enchantmentInstance.getName(), enchantmentInstance.getLevel()));
		}
		return itemStack;
	}

	public List<EnchantmentInstance> selectEnchantment(LootContext lootContext, ItemStack itemStack, int level, boolean isTreasure, boolean isDiscoverable) {
		ArrayList<EnchantmentInstance> res = new ArrayList<>();
		Item item = itemStack.getItem();
		int enchantmentValue;
		if(enchantments.containsKey(item.getName())) {
			enchantmentValue = enchantments.get(item.getName());
		} else {
			return res;
		}
		level += 1 + lootContext.nextInt(enchantmentValue / 4 + 1) + lootContext.nextInt(enchantmentValue / 4 + 1);
		float amplifier = (lootContext.nextFloat() + lootContext.nextFloat() - 1.0f) * 0.15f;
		level = Mth.clamp(Math.round((float)level + (float)level * amplifier), 1, Integer.MAX_VALUE);
		ArrayList<EnchantmentInstance> availableEnchantments = getAvailableEnchantmentResults(level);
		if(!availableEnchantments.isEmpty()) {
			res.add(EnchantmentInstance.getRandomItem(lootContext, availableEnchantments));
			while(lootContext.nextInt(50) <= level) {
				Enchantments.filterCompatibleEnchantments(availableEnchantments, res.get(res.size() - 1));
				if(availableEnchantments.isEmpty()) break;
				res.add(EnchantmentInstance.getRandomItem(lootContext, availableEnchantments));
				level /= 2;
			}
		}
		return res;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<EnchantmentInstance> getAvailableEnchantmentResults(int level) {
		return (ArrayList<EnchantmentInstance>) this.availableEnchantmentResults[level].clone();
	}

	public int getRandomValueFromBounds(LootContext lootContext) {
		if(this.minLevel == this.maxLevel) {
			return this.minLevel;
		} else {
			return lootContext.nextInt(this.maxLevel - this.minLevel + 1) + minLevel;
		}
	}
}
