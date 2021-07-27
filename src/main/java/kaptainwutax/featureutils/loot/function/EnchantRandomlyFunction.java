package kaptainwutax.featureutils.loot.function;

import kaptainwutax.featureutils.loot.LootContext;
import kaptainwutax.featureutils.loot.enchantment.Enchantment;
import kaptainwutax.featureutils.loot.enchantment.Enchantments;
import kaptainwutax.featureutils.loot.item.Item;
import kaptainwutax.featureutils.loot.item.ItemStack;
import kaptainwutax.mcutils.util.data.Pair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class EnchantRandomlyFunction extends EnchantmentFunction {
	private List<Enchantment> applicableEnchantments = new ArrayList<>();

	public EnchantRandomlyFunction(Item item) {
		super(item);
	}

	public EnchantRandomlyFunction(Item item, boolean isTreasure) {
		super(item, isTreasure);
	}

	public EnchantRandomlyFunction(Item item, boolean isTreasure, boolean isDiscoverable) {
		super(item, isTreasure, isDiscoverable);
	}


	public EnchantmentFunction applyEnchantment(List<Enchantment> enchantments) {
		HashSet<HashSet<String>> applicableCategories = Enchantments.getCategories(new ItemStack(item, 1));
		this.applicableEnchantments = Enchantments.getApplicableEnchantments(enchantments, applicableCategories, this.isTreasure, this.isDiscoverable);
		return this;
	}

	@Override
	public ItemStack process(ItemStack baseStack, LootContext context) {
		Item newItem = baseStack.getItem();
		if(applicableEnchantments.isEmpty()) return baseStack;
		int enchantNr = context.nextInt(applicableEnchantments.size());
		Enchantment enchantment = applicableEnchantments.get(enchantNr);
		int level = 1;
		if(!(Enchantments.SingleEnchants.contains(enchantment.getName()))) {
			level = context.nextInt(enchantment.getMaxLevel()) + 1;
		}
		newItem.addEnchantment(new Pair<>(enchantment.getName(), level));
		return new ItemStack(newItem, baseStack.getCount());
	}
}
