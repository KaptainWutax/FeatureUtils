import kaptainwutax.featureutils.loot.LootContext;
import kaptainwutax.featureutils.loot.MCLootTables;
import kaptainwutax.featureutils.loot.item.Item;
import kaptainwutax.seedutils.lcg.rand.JRand;

import java.util.HashMap;
import java.util.Map;

public class LootTest {

	public static void main(String[] args) {
		LootContext context = new LootContext();
		context.setSeed(new JRand(196741084501354L, false).nextLong());

		Map<Item, Integer> itemCounts = new HashMap<>();

		MCLootTables.BURIED_TREASURE_CHEST.generate(context, stack -> {
			itemCounts.put(stack.getItem(), itemCounts.getOrDefault(stack.getItem(), 0) + stack.getCount());
		});

		itemCounts.forEach((item, count) -> {
			System.out.println(item.getName().toUpperCase() + ": " + count);
		});
	}

}
