package kaptainwutax.featureutils.loot.item;

import java.util.Objects;

public class ItemStack {
    public static final ItemStack EMPTY = new ItemStack(null);
    private final Item item;
    private int count;

    public ItemStack(Item item) {
        this(item, 1);
    }

    public ItemStack(Item item, int count) {
        this.item = item;
        this.count = count;
    }

    public boolean isEmpty(){
        if (this == EMPTY) {
            return true;
        } else if (this.getItem() != null && this.getItem() != Item.AIR) {
            return this.count <= 0;
        } else {
            return true;
        }
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

    public ItemStack split(int count) {
        int splitCount = Math.min(count, this.count);
        ItemStack itemstack = new ItemStack(this.item,this.count);
        itemstack.setCount(splitCount);
        this.setCount(this.count - splitCount);
        return itemstack;
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
