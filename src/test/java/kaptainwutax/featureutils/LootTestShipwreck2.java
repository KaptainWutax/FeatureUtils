package kaptainwutax.featureutils;

import kaptainwutax.biomeutils.source.OverworldBiomeSource;
import kaptainwutax.featureutils.loot.LootContext;
import kaptainwutax.featureutils.loot.MCLootTables;
import kaptainwutax.featureutils.loot.item.ItemStack;
import kaptainwutax.featureutils.structure.Shipwreck;
import kaptainwutax.seedutils.mc.ChunkRand;
import kaptainwutax.seedutils.mc.MCVersion;
import kaptainwutax.seedutils.mc.pos.CPos;
import kaptainwutax.seedutils.mc.pos.RPos;

import java.util.Arrays;
import java.util.List;

public class LootTestShipwreck2 {
    public static void main(String[] args) {
        ChunkRand rand=new ChunkRand();
        long worldSeed=1L;
        CPos cPos=new CPos(-13,-20);
        System.out.println(cPos.toBlockPos());
        MCVersion version=MCVersion.v1_16;
        Shipwreck shipwreck=new Shipwreck(version);
        RPos rPos=cPos.toRegionPos(shipwreck.getSpacing());
        CPos start=shipwreck.getInRegion(worldSeed,rPos.getX(),rPos.getZ(),rand);
        OverworldBiomeSource biomeSource=new OverworldBiomeSource(version,worldSeed);
        // should always be called
        if (shipwreck.canSpawn(start.getX(),start.getZ(),biomeSource)) System.out.println("OK");
        System.out.println(start);
        assert cPos==start;
        System.out.println(shipwreck.isBeached());
        Shipwreck.Rotation rotation=shipwreck.getRotation(worldSeed,cPos,version);
        System.out.println(rotation);
        int salt=40006;
        rand.setDecoratorSeed(worldSeed, cPos.getX() * 16, cPos.getZ() * 16, salt, version);
        if (shipwreck.isBeached()){
            rand.nextInt(3);
        }
        System.out.println(rand.getSeed());
        rand.advance(2);
        long actualLootTableSeedChest3 = rand.nextLong();
        System.out.println(actualLootTableSeedChest3);
        LootContext context3 = new LootContext(actualLootTableSeedChest3);
        List<ItemStack> loot3 = MCLootTables.SHIPWRECK_SUPPLY_CHEST.generate(context3);
        System.out.println(Arrays.toString(loot3.toArray()));

        rand.setDecoratorSeed(worldSeed, cPos.getX() * 16, (cPos.getZ()+1) * 16, salt, version);
        if (shipwreck.isBeached()){
            rand.nextInt(3);
        }
        System.out.println(rand.getSeed());
        //135741450015578

        // LootTableSeed -> {LongNBT@44981} "598389394625329582L"
        // LootTableSeed -> {LongNBT@45047} "6164163727968769825L"
        // those are set in the template but overriden by the data marker
        long lootTableSeedChest1 = rand.nextLong();
        long lootTableSeedChest2 = rand.nextLong();
        System.out.println(lootTableSeedChest1+" "+lootTableSeedChest2);
    // those 2 might be switched around
        long actualLootTableSeedChest1 = rand.nextLong();
        System.out.println(actualLootTableSeedChest1);
        LootContext context1 = new LootContext(actualLootTableSeedChest1);
        List<ItemStack> loot1 = MCLootTables.SHIPWRECK_MAP_CHEST.generate(context1);
        System.out.println(Arrays.toString(loot1.toArray()));

        // 1541918443095176165 TREASURE

        long actualLootTableSeedChest2 = rand.nextLong();
        System.out.println(actualLootTableSeedChest2);
        LootContext context2 = new LootContext(actualLootTableSeedChest2);
        List<ItemStack> loot2 = MCLootTables.SHIPWRECK_TREASURE_CHEST.generate(context2);
        System.out.println(Arrays.toString(loot2.toArray()));

    }
}
