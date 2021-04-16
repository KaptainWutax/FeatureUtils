package kaptainwutax.featureutils.loot.item;

import kaptainwutax.featureutils.loot.effect.Effect;
import kaptainwutax.seedutils.util.Pair;

import java.util.ArrayList;
import java.util.Objects;

@SuppressWarnings("unused")
public class Item {

    private final String name;
    private ArrayList<Pair<String, Integer>> enchantments;
    private ArrayList<Pair<Effect, Integer>> effects;

    public Item(String name) {
        this(name, new ArrayList<>(), new ArrayList<>());
    }

    public Item(String name, ArrayList<Pair<String, Integer>> enchantments, ArrayList<Pair<Effect, Integer>> effects) {
        this.name = name;
        this.enchantments = enchantments;
        this.effects = effects;
    }

    public ArrayList<Pair<Effect, Integer>> getEffects() {
        return effects;
    }

    public void setEffects(ArrayList<Pair<Effect, Integer>> effects) {
        this.effects = effects;
    }

    public void addEffect(Pair<Effect, Integer> effect) {
        this.effects.add(effect);
    }

    public ArrayList<Pair<String, Integer>> getEnchantments() {
        return enchantments;
    }

    public void setEnchantments(ArrayList<Pair<String, Integer>> enchantments) {
        this.enchantments = enchantments;
    }

    public void addEnchantment(Pair<String, Integer> enchantment) {
        this.enchantments.add(enchantment);
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", enchantments=" + enchantments +
                ", effects=" + effects +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;

        boolean sameEnchantment = item.enchantments.size() == this.enchantments.size();
        for (Pair<String, Integer> enchantment : this.enchantments) {
            if (!item.enchantments.contains(enchantment)) {
                sameEnchantment = false;
                break;
            }
        }

        boolean sameEffect = item.effects.size() == this.effects.size();
        for (Pair<Effect, Integer> effect : this.effects) {
            if (!item.effects.contains(effect)) {
                sameEffect = false;
                break;
            }
        }

        return Objects.equals(name, item.name) && sameEnchantment && sameEffect;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, enchantments);
    }
}
