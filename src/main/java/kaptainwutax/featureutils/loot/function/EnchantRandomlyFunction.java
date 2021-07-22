package kaptainwutax.featureutils.loot.function;

import java.util.HashSet;
import java.util.List;
import kaptainwutax.featureutils.loot.LootContext;
import kaptainwutax.featureutils.loot.enchantment.Enchantment;
import kaptainwutax.featureutils.loot.enchantment.Enchantments;
import kaptainwutax.featureutils.loot.item.Item;
import kaptainwutax.featureutils.loot.item.ItemStack;
import kaptainwutax.mcutils.util.data.Pair;

public class EnchantRandomlyFunction implements LootFunction {
	private boolean isTreasure;
	private boolean isDiscoverable;
	private List<Enchantment> applicableEnchantments;

	public EnchantRandomlyFunction(Item item) {
		this(item, true, true);
	}

	public EnchantRandomlyFunction(Item item, boolean isTreasure) {
		this(item, isTreasure, true);
	}

	public EnchantRandomlyFunction(Item item, boolean isTreasure, boolean isDiscoverable) {
		this.isTreasure = isTreasure;
		this.isDiscoverable = isDiscoverable;

		HashSet<HashSet<String>> applicableCategories = Enchantments.getCategories(new ItemStack(item, 1));
		this.applicableEnchantments = Enchantments.getApplicableEnchantments(applicableCategories, this.isTreasure, this.isDiscoverable);
	}

	public boolean isTreasure() {
		return isTreasure;
	}

	public void setTreasure(boolean treasure) {
		isTreasure = treasure;
	}

	public boolean isDiscoverable() {
		return isDiscoverable;
	}

	public void setDiscoverable(boolean discoverable) {
		isDiscoverable = discoverable;
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
