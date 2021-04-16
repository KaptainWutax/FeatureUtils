package kaptainwutax.featureutils.loot.function;

import kaptainwutax.featureutils.loot.LootContext;
import kaptainwutax.featureutils.loot.enchantment.Enchantment;
import kaptainwutax.featureutils.loot.enchantment.Enchantments;
import kaptainwutax.featureutils.loot.item.Item;
import kaptainwutax.featureutils.loot.item.ItemStack;
import kaptainwutax.seedutils.util.Pair;

import java.util.HashSet;
import java.util.List;

import static kaptainwutax.featureutils.loot.enchantment.Enchantments.getApplicableEnchantments;
import static kaptainwutax.featureutils.loot.enchantment.Enchantments.getCategories;

public class EnchantRandomlyFunction implements LootFunction {
    private boolean isTreasure;
    private boolean isDiscoverable;

    public EnchantRandomlyFunction() {
        this(true, true);
    }

    public EnchantRandomlyFunction(boolean isTreasure) {
        this(isTreasure, true);
    }

    public EnchantRandomlyFunction(boolean isTreasure, boolean isDiscoverable) {
        this.isTreasure = isTreasure;
        this.isDiscoverable = isDiscoverable;
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
        Item newItem = new Item(baseStack.getItem().getName());
        HashSet<HashSet<String>> applicableCategories = getCategories(baseStack);
        List<Enchantment> applicableEnchantments = getApplicableEnchantments(applicableCategories, this.isTreasure, this.isDiscoverable);
        if (applicableEnchantments.isEmpty()) return baseStack; //TODO fix me this is wrong for size 1 and 0 (LEATHER)
        int enchantNr = context.nextInt(applicableEnchantments.size());
        Enchantment enchantment = applicableEnchantments.get(enchantNr);
        int level = 1;
        if (!(Enchantments.SingleEnchants.contains(enchantment.getName()))) {
            level = context.nextInt(enchantment.getMaxLevel()) + 1;
        }
        newItem.addEnchantment(new Pair<>(enchantment.getName(),level));
        return new ItemStack(newItem, baseStack.getCount());
    }
}
