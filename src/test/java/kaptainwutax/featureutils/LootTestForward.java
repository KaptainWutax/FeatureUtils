package kaptainwutax.featureutils;

import kaptainwutax.biomeutils.source.OverworldBiomeSource;
import kaptainwutax.featureutils.loot.LootChest;
import kaptainwutax.featureutils.loot.MCLootTables;
import kaptainwutax.featureutils.loot.item.Items;
import kaptainwutax.featureutils.structure.BuriedTreasure;
import kaptainwutax.featureutils.structure.RegionStructure;
import kaptainwutax.mcutils.rand.ChunkRand;
import kaptainwutax.mcutils.rand.seed.WorldSeed;
import kaptainwutax.mcutils.version.MCVersion;

import static kaptainwutax.featureutils.loot.LootChest.EQUAL_TO;

public class LootTestForward {

    public static final BuriedTreasure BURIED_TREASURE = new BuriedTreasure(MCVersion.v1_16);

    public static LootChest TREASURE_CHEST = new LootChest(
            LootChest.stack(Items.DIAMOND, EQUAL_TO, 2),
            LootChest.stack(Items.COOKED_SALMON, EQUAL_TO, 2),
            LootChest.stack(Items.HEART_OF_THE_SEA, EQUAL_TO, 1),
            LootChest.stack(Items.EMERALD, EQUAL_TO, 8),
            LootChest.stack(Items.TNT, EQUAL_TO, 2),
            LootChest.stack(Items.IRON_INGOT, EQUAL_TO, 8),
            LootChest.stack(Items.GOLD_INGOT, EQUAL_TO, 8),
            LootChest.stack(Items.COOKED_COD, EQUAL_TO, 3)
    );

    public static void main(String[] args) {
        ChunkRand rand = new ChunkRand();
        // go at -7 105 in worldseed 1
        long worldSeed = 1L;
        // warning this will not work in old version, please stick to 1.14+
        MCVersion version = MCVersion.v1_16;
        OverworldBiomeSource source = new OverworldBiomeSource(version, worldSeed);
        for (int chunkX = -200; chunkX < 200; chunkX++) {
            for (int chunkZ = -200; chunkZ < 200; chunkZ++) {
                // get the offset in that chunk for that burried treasure at chunkX and chunkZ
                RegionStructure.Data<BuriedTreasure> treasure = BURIED_TREASURE.at(chunkX, chunkZ);
                // check that the structure can generate in that chunk (it's luck based with a nextfloat)
                if (!treasure.testStart(WorldSeed.toStructureSeed(worldSeed), rand)) continue;
                // test if the biomes are correct at that place
                if (!treasure.testBiome(source)) continue;
                // we get the decoration Seed (used to place all the decorators)
                long decoratorSeed = rand.setPopulationSeed(worldSeed, chunkX * 16, chunkZ * 16, version);
//                CPos cPos=new CPos(chunkX,chunkZ);
//                BPos bPos=cPos.toBlockPos().add(9,0,9);
//                Biome biome=source.getBiome(bPos.getX(),bPos.getY(),bPos.getZ()); // useful only to get the index and step
                // set the feature seed based on the ordinals
                rand.setDecoratorSeed(decoratorSeed, 1, 3, version); //specific ordinals and index for burried treasures
                // we get the loot table seed
                long lootTableSeed = rand.nextLong();
                if (TREASURE_CHEST.testLoot(lootTableSeed, MCLootTables.BURIED_TREASURE_CHEST)) {
                    System.out.printf("Found loot at chunkX: %d chunkZ: %d, posX: %d posZ: %d %n", chunkX, chunkZ, chunkX * 16 + 9, chunkZ * 16 + 9);
                }
            }
        }
    }
}
