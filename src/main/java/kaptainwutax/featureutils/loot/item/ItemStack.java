package kaptainwutax.featureutils.loot.item;

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

}
