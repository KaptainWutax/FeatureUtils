package kaptainwutax.featureutils.loot.function;

import kaptainwutax.featureutils.loot.LootContext;
import kaptainwutax.featureutils.loot.item.ItemStack;

public class ApplyDamageFunction implements LootFunction {
    public ApplyDamageFunction() {}

    @Override
    public ItemStack process(ItemStack baseStack, LootContext context) {
        context.advance(1);
        return baseStack;
    }
}
