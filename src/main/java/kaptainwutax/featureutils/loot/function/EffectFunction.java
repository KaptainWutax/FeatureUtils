package kaptainwutax.featureutils.loot.function;


import kaptainwutax.featureutils.loot.LootContext;
import kaptainwutax.featureutils.loot.effect.Effect;
import kaptainwutax.featureutils.loot.item.Item;
import kaptainwutax.featureutils.loot.item.ItemStack;
import kaptainwutax.featureutils.loot.item.Items;
import kaptainwutax.featureutils.loot.roll.UniformRoll;
import kaptainwutax.mcutils.util.data.Pair;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class EffectFunction implements LootFunction {
	private final LinkedHashMap<Effect, UniformRoll> effects = new LinkedHashMap<>();
	private final HashMap<Effect, UniformRoll> random_effect = new HashMap<>(); // this is in case minecraft change it

	public EffectFunction() {

	}

	@SafeVarargs
	public EffectFunction(Pair<Effect, UniformRoll>... effects) {
		for(Pair<Effect, UniformRoll> effect : effects) {
			this.apply(effect);
		}
	}

	public static EffectFunction builder() {
		return new EffectFunction();
	}

	public static <T> T nthElement(Iterable<T> data, int n) {
		int index = 0;
		for(T element : data) {
			if(index == n) {
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
		this.random_effect.put(effect.getFirst(), effect.getSecond());
		this.effects.put(effect.getFirst(), effect.getSecond());
		return this;
	}

	public Map<Effect, UniformRoll> getEffects() {
		return effects;
	}

	@Override
	public ItemStack process(ItemStack baseStack, LootContext context) {
		Item newItem = new Item(baseStack.getItem().getName());
		if(newItem.equalsName(Items.SUSPICIOUS_STEW) && !this.getEffects().isEmpty()) {
			int i = context.nextInt(this.getEffects().size());
			Map.Entry<Effect, UniformRoll> entry = nthElement(this.getEffects().entrySet(), i); // mojang why? (this is not ordered so we hardcoded the order...
			assert entry != null;
			Effect effect = entry.getKey();
			int duration = entry.getValue().getCount(context);
			if(!effect.isInstantenous()) {
				duration *= 20;
			}
			newItem.addEffect(new Pair<>(effect, duration));
		}
		return new ItemStack(newItem, baseStack.getCount());
	}
}
