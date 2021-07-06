package kaptainwutax.featureutils.loot;

import kaptainwutax.featureutils.loot.item.Item;
import kaptainwutax.featureutils.loot.item.ItemStack;
import kaptainwutax.featureutils.structure.generator.Generator;
import kaptainwutax.mcutils.util.pos.BPos;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class ChestContent {
	private final Generator.ILootType lootType;
	private final ChestType chestType;
	private final List<ItemStack> items;
	private final BPos pos;
	private final boolean indexed;

	public ChestContent(Generator.ILootType lootType, List<ItemStack> items, BPos pos) {
		this(lootType, lootType.getChestType(), items, pos);
	}

	public ChestContent(Generator.ILootType lootType, ChestType chestType, List<ItemStack> items, BPos pos) {
		this(lootType, chestType, items, pos, false);
	}

	public ChestContent(Generator.ILootType lootType, List<ItemStack> items, BPos pos, boolean indexed) {
		this(lootType, lootType.getChestType(), items, pos);
	}

	public ChestContent(Generator.ILootType lootType, ChestType chestType, List<ItemStack> items, BPos pos, boolean indexed) {
		this.lootType = lootType;
		this.chestType = chestType;
		this.items = items;
		this.pos = pos;
		this.indexed = indexed;
	}

	public boolean contains(Item item) {
		return this.containsAtLeast(item, 1);
	}

	public boolean containsAtLeast(Item item, int count) {
		return this.getCount(e -> e.getName().equals(item.getName())) >= count;
	}

	public boolean containsExact(Item item) {
		return this.containsExactlyAtLeast(item, 1);
	}

	public boolean containsExactlyAtLeast(Item item, int count) {
		return this.getCount(e -> e.equals(item)) >= count;
	}

	public int getCount(Predicate<Item> predicate) {
		return this.items.stream().filter(Objects::nonNull).filter(e -> predicate.test(e.getItem())).mapToInt(ItemStack::getCount).sum();
	}

	public Generator.ILootType getLootType() {
		return lootType;
	}

	public ChestType getChestType() {
		return chestType;
	}

	public List<ItemStack> getItems() {
		return items;
	}

	public BPos getPos() {
		return pos;
	}

	public boolean isIndexed() {
		return indexed;
	}

	public enum ChestType {
		SINGLE_CHEST(1),
		DOUBLE_CHEST(2),
		UNKNOWN(0);

		public static final int ITEMS_PER_ROW = 9;
		public static final int NUMBER_ROWS = 3;
		private int size;

		ChestType(int size) {
			this.size = size;
		}

		public void setSize(int size) {
			this.size = size;
		}

		public int getSize() {
			return size;
		}

		public int getNumberSlots() {
			return this.getNumberRows() * ITEMS_PER_ROW;
		}

		public int getNumberRows() {
			return this.size * NUMBER_ROWS;
		}
	}
}
