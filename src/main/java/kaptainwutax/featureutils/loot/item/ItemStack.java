package kaptainwutax.featureutils.loot.item;

import java.util.Objects;

public class ItemStack {

    private final Item item;
    private int count;

    public ItemStack(Item item) {
        this(item, 1);
    }

    public ItemStack(Item item, int count) {
        this.item = item;
        this.count = count;
    }

    public Item getItem() {
        return this.item;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "ItemStack{" +
                "item=" + item +
                ", count=" + count +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemStack)) return false;
        ItemStack itemStack = (ItemStack) o;
        return count == itemStack.count && Objects.equals(item, itemStack.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(item, count);
    }
}
