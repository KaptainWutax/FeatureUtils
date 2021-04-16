package kaptainwutax.featureutils.loot.function;


import kaptainwutax.featureutils.loot.LootContext;
import kaptainwutax.featureutils.loot.effect.Effect;
import kaptainwutax.featureutils.loot.item.Item;
import kaptainwutax.featureutils.loot.item.ItemStack;
import kaptainwutax.featureutils.loot.item.Items;
import kaptainwutax.featureutils.loot.roll.UniformRoll;
import kaptainwutax.seedutils.util.Pair;

import java.util.LinkedHashMap;
import java.util.Map;

public class EffectFunction implements LootFunction {
    public static String[] INSTANTANEOUS_EFFECTS = new String[] {"HEAL", "HARM", "SATURATION"};
    private final LinkedHashMap<Effect, UniformRoll> effects = new LinkedHashMap<>();

    public EffectFunction() {

    }

    @SafeVarargs
    public EffectFunction(Pair<Effect, UniformRoll>... effects) {
        for (Pair<Effect, UniformRoll> effect : effects) {
            this.apply(effect);
        }
    }

    public static EffectFunction builder() {
        return new EffectFunction();
    }

    public static <T> T nthElement(Iterable<T> data, int n) {
        int index = 0;
        for (T element : data) {
            if (index == n) {
                return element;
            }
            index++;
        }
        return null;
    }

    public EffectFunction apply(Effect effect, float min, float max) {
        return this.apply(new Pair<>(effect, new UniformRoll(min, max)));
    }

    @SuppressWarnings("unchecked")
    public EffectFunction apply(Pair<Effect, UniformRoll> effect) {
        if (effect.getFirst() instanceof Effect.InstantEffect) { // I hate to hardcode those
            LinkedHashMap<Effect, UniformRoll> temp = (LinkedHashMap<Effect, UniformRoll>) effects.clone();
            effects.clear();
            effects.put(effect.getFirst(), effect.getSecond());
            effects.putAll(temp);
        } else {
            this.effects.put(effect.getFirst(), effect.getSecond());
        }

        return this;
    }

    public Map<Effect, UniformRoll> getEffects() {
        return effects;
    }

    @Override
    public ItemStack process(ItemStack baseStack, LootContext context) {
        Item newItem = new Item(baseStack.getItem().getName());
        if (newItem.getName().equals(Items.SUSPICIOUS_STEW.getName()) && !this.getEffects().isEmpty()) {
            int i = context.nextInt(this.getEffects().size());
            Map.Entry<Effect, UniformRoll> entry = nthElement(this.getEffects().entrySet(), i); // mojang why? (this is not ordered so we hardcoded the order...
            assert entry != null;
            Effect effect = entry.getKey();
            int duration = entry.getValue().getCount(context);
            if (!effect.isInstantenous()) {
                duration *= 20;
            }
            newItem.addEffect(new Pair<>(effect, duration));
        }
        return new ItemStack(newItem, baseStack.getCount());
    }
}
