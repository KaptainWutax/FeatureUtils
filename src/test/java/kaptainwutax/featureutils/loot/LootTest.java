package kaptainwutax.featureutils.loot;

import kaptainwutax.featureutils.loot.item.Item;
import kaptainwutax.featureutils.loot.item.Items;
import kaptainwutax.featureutils.structure.generator.EndCityGenerator;
import kaptainwutax.mcutils.version.MCVersion;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LootTest {
	@Test
	public void testListItems() {
		EndCityGenerator endCityGenerator = new EndCityGenerator(MCVersion.v1_16_5);
		Set<Item> itemList = endCityGenerator.getPossibleLootItems();
		Set<Item> items = new HashSet<Item>() {{
			add(Items.DIAMOND);
			add(Items.IRON_INGOT);
			add(Items.GOLD_INGOT);
			add(Items.EMERALD);
			add(Items.BEETROOT_SEEDS);
			add(Items.SADDLE);
			add(Items.IRON_HORSE_ARMOR);
			add(Items.GOLDEN_HORSE_ARMOR);
			add(Items.DIAMOND_HORSE_ARMOR);
			add(Items.DIAMOND_SWORD);
			add(Items.DIAMOND_BOOTS);
			add(Items.DIAMOND_CHESTPLATE);
			add(Items.DIAMOND_LEGGINGS);
			add(Items.DIAMOND_HELMET);
			add(Items.DIAMOND_PICKAXE);
			add(Items.DIAMOND_SHOVEL);
			add(Items.IRON_SWORD);
			add(Items.IRON_BOOTS);
			add(Items.IRON_CHESTPLATE);
			add(Items.IRON_LEGGINGS);
			add(Items.IRON_HELMET);
			add(Items.IRON_PICKAXE);
			add(Items.IRON_SHOVEL);
		}};
		for (Item item:items){
			assertTrue(itemList.remove(item));
		}
		assertTrue(itemList.isEmpty());

	}
}
