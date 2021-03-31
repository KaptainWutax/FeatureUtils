package kaptainwutax.featureutils;

import kaptainwutax.featureutils.loot.LootContext;
import kaptainwutax.featureutils.loot.MCLootTables;

public class LootTest {

	public static void main(String[] args) {
		LootContext context = new LootContext(1234L);

		MCLootTables.BURIED_TREASURE_CHEST.generate(context).forEach(stack ->
			System.out.println(stack.getItem().getName().toUpperCase() + ": " + stack.getCount())
		);
	}

}
