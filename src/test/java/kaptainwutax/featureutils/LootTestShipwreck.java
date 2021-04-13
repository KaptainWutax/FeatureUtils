package kaptainwutax.featureutils;

import kaptainwutax.biomeutils.source.OverworldBiomeSource;
import kaptainwutax.featureutils.loot.LootContext;
import kaptainwutax.featureutils.loot.MCLootTables;
import kaptainwutax.featureutils.loot.item.ItemStack;
import kaptainwutax.featureutils.structure.Shipwreck;
import kaptainwutax.seedutils.mc.ChunkRand;
import kaptainwutax.seedutils.mc.MCVersion;
import kaptainwutax.seedutils.mc.pos.BPos;
import kaptainwutax.seedutils.mc.pos.CPos;
import kaptainwutax.seedutils.mc.pos.RPos;

import java.util.Arrays;
import java.util.List;

public class LootTestShipwreck {
    public static void main(String[] args) {
        ChunkRand rand = new ChunkRand();
        long worldSeed = 1L;
        BPos bPos = new BPos(64, 90, 128);
//        CPos cPos=new CPos(4,8);
        CPos cPos = bPos.toChunkPos();
        System.out.println("Target: " + cPos.toBlockPos());
        MCVersion version = MCVersion.v1_16;
        Shipwreck shipwreck = new Shipwreck(version);
        RPos rPos = cPos.toRegionPos(shipwreck.getSpacing());
        CPos start = shipwreck.getInRegion(worldSeed, rPos.getX(), rPos.getZ(), rand);
        assert shipwreck.isBeached() == null;
        OverworldBiomeSource biomeSource = new OverworldBiomeSource(version, worldSeed);
        // should always be called
        if (shipwreck.canSpawn(start.getX(), start.getZ(), biomeSource)) System.out.println("OK Biome");
        System.out.println("Got: " + start);
        assert cPos == start;
        System.out.println("Is beached " + shipwreck.isBeached());
        // We are mocking it here
        shipwreck.setBeached(true);
        Shipwreck.Rotation rotation = shipwreck.getRotation(worldSeed, cPos, version);
        System.out.println("Rot: " + rotation);
        System.out.println("Type: " + shipwreck.getType());
        System.out.println("Seed after rot " + shipwreck.getInternalRandom().getSeed());
        int salt = 40006;

        rand.setDecoratorSeed(worldSeed, cPos.getX() * 16, cPos.getZ() * 16, salt, version);
        if (shipwreck.isBeached()) {
            rand.nextInt(3);
        }
        rand.advance(2);
        System.out.println("Supply Chest pre seed: " + rand.getSeed());
        LootContext context3 = new LootContext(rand.nextLong());
        List<ItemStack> loot3 = MCLootTables.SHIPWRECK_SUPPLY_CHEST.generate(context3);
        System.out.println(Arrays.toString(loot3.toArray()));


        CPos rotatedPos = rotation.getRotatedPos(cPos);
        assert rotatedPos != null;
        System.out.println("Rotated pos : "+rotatedPos.toBlockPos());
        rand.setDecoratorSeed(worldSeed, rotatedPos.getX() * 16, rotatedPos.getZ() * 16, salt, version);
        if (shipwreck.isBeached()) {
            rand.nextInt(3);
        }
        rand.advance(4);

        // order might be wrong
        Runnable mapRunnable = () -> {
            System.out.println("Map Chest pre seed: " + rand.getSeed());
            LootContext context1 = new LootContext(rand.nextLong());
            List<ItemStack> loot1 = MCLootTables.SHIPWRECK_MAP_CHEST.generate(context1);
            System.out.println(Arrays.toString(loot1.toArray()));
        };

        Runnable treasureRunnable = () -> {
            System.out.println("Treasure Chest pre seed: " + rand.getSeed());
            LootContext context2 = new LootContext(rand.nextLong());
            List<ItemStack> loot2 = MCLootTables.SHIPWRECK_TREASURE_CHEST.generate(context2);
            System.out.println(Arrays.toString(loot2.toArray()));
        };

        String[] treasureMapType = new String[] {"rightsideup_full"};
        if (Arrays.stream(treasureMapType).anyMatch(s -> shipwreck.getType().endsWith(s))) {
            treasureRunnable.run();
            mapRunnable.run();
        } else {
            mapRunnable.run();
            treasureRunnable.run();
        }

    }
}
