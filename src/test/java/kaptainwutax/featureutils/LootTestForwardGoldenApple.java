package kaptainwutax.featureutils;

import kaptainwutax.biomeutils.source.OverworldBiomeSource;
import kaptainwutax.featureutils.loot.LootContext;
import kaptainwutax.featureutils.loot.MCLootTables;
import kaptainwutax.featureutils.loot.item.ItemStack;
import kaptainwutax.featureutils.loot.item.Items;
import kaptainwutax.featureutils.structure.DesertPyramid;
import kaptainwutax.featureutils.structure.RegionStructure;
import kaptainwutax.mcutils.rand.ChunkRand;
import kaptainwutax.mcutils.rand.seed.WorldSeed;
import kaptainwutax.mcutils.version.MCVersion;

import java.util.List;

public class LootTestForwardGoldenApple {
    public static final DesertPyramid DESERT_TEMPLE = new DesertPyramid(MCVersion.v1_16);

    public static void main(String[] args) {
        ChunkRand rand = new ChunkRand();
        // go at /tp @p 12512 90 25344 in worldseed 1
        long worldSeed = 1L;
        // warning this will not work in old version, please stick to 1.14+
        MCVersion version = MCVersion.v1_16;
        OverworldBiomeSource source = new OverworldBiomeSource(version, worldSeed);
        for (int chunkX = -2000; chunkX < 2000; chunkX++) {
            for (int chunkZ = -2000; chunkZ < 2000; chunkZ++) {
                // get the offset in that chunk for that burried treasure at chunkX and chunkZ
                RegionStructure.Data<?> desertPyramidData = DESERT_TEMPLE.at(chunkX, chunkZ);
                // check that the structure can generate in that chunk (it's luck based with a nextfloat)
                if (!desertPyramidData.testStart(WorldSeed.toStructureSeed(worldSeed), rand)) continue;
                // test if the biomes are correct at that place
                if (!desertPyramidData.testBiome(source)) continue;
                // we get the decoration Seed (used to place all the decorators)
                rand.setDecoratorSeed(worldSeed, chunkX * 16, chunkZ * 16, 40003, version);
                // we get the loot table seed
                int sum = 0;
                long lootTableSeed = rand.nextLong();
                LootContext context = new LootContext(lootTableSeed);
                List<ItemStack> loot1 = MCLootTables.DESERT_PYRAMID_CHEST.generate(context);
                boolean b1 = loot1.contains(new ItemStack(Items.ENCHANTED_GOLDEN_APPLE));

                lootTableSeed = rand.nextLong();
                context = new LootContext(lootTableSeed);
                List<ItemStack> loot2 = MCLootTables.DESERT_PYRAMID_CHEST.generate(context);
                boolean b2 = loot2.contains(new ItemStack(Items.ENCHANTED_GOLDEN_APPLE));

                lootTableSeed = rand.nextLong();
                context = new LootContext(lootTableSeed);
                List<ItemStack> loot3 = MCLootTables.DESERT_PYRAMID_CHEST.generate(context);
                boolean b3 = loot3.contains(new ItemStack(Items.ENCHANTED_GOLDEN_APPLE));

                lootTableSeed = rand.nextLong();
                context = new LootContext(lootTableSeed);
                List<ItemStack> loot4 = MCLootTables.DESERT_PYRAMID_CHEST.generate(context);
                boolean b4 = loot4.contains(new ItemStack(Items.ENCHANTED_GOLDEN_APPLE));
                sum = (b1 ? 1 : 0) + (b2 ? 1 : 0) + (b3 ? 1 : 0) + (b4 ? 1 : 0);
                // boolean b= loot.stream().anyMatch(itemStack -> itemStack.getItem() == Item.ENCHANTED_GOLDEN_APPLE);
                if (sum >= 3) {
                    System.out.printf("/tp @p %d 90 %d%n", chunkX * 16, +chunkZ * 16);
                    // System.out.println(Arrays.toString(loot.toArray()));
                }
            }
        }
    }
}
