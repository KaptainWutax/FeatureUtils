package kaptainwutax.featureutils;

import kaptainwutax.biomeutils.source.OverworldBiomeSource;
import kaptainwutax.featureutils.structure.Shipwreck;
import kaptainwutax.seedutils.mc.ChunkRand;
import kaptainwutax.seedutils.mc.MCVersion;
import kaptainwutax.seedutils.mc.pos.CPos;
import kaptainwutax.seedutils.mc.pos.RPos;

public class LootTestShipwreck {
    public static void main(String[] args) {

        test3();
    }


    public static void test3() {
        ChunkRand rand = new ChunkRand();
        long worldSeed = -3713447489682223303L;
        CPos cPos = new CPos(-7, -15);
        System.out.println("Target: " + cPos.toBlockPos());
        MCVersion version = MCVersion.v1_16;
        Shipwreck shipwreck = new Shipwreck(version);
        RPos rPos = cPos.toRegionPos(shipwreck.getSpacing());
        CPos start = shipwreck.getInRegion(worldSeed, rPos.getX(), rPos.getZ(), rand);
        System.out.println("Got: " + start);
        assert cPos.equals(start);

        assert shipwreck.isBeached() == null;
        OverworldBiomeSource biomeSource = new OverworldBiomeSource(version, worldSeed);
        // should always be called
        if (shipwreck.canSpawn(start.getX(), start.getZ(), biomeSource)) System.out.println("OK Biome");
        assert shipwreck.isBeached() != null;
        System.out.println("Is beached " + shipwreck.isBeached());

        System.out.println(shipwreck.getLoot(start, worldSeed, rand, true));

    }

    public static void test2() {
        ChunkRand rand = new ChunkRand();
        long worldSeed = -1656754759468825802L;
        CPos cPos = new CPos(89, -142);
        System.out.println("Target: " + cPos.toBlockPos());
        MCVersion version = MCVersion.v1_16;
        Shipwreck shipwreck = new Shipwreck(version);
        RPos rPos = cPos.toRegionPos(shipwreck.getSpacing());
        CPos start = shipwreck.getInRegion(worldSeed, rPos.getX(), rPos.getZ(), rand);
        System.out.println("Got: " + start);
        assert cPos.equals(start);

        assert shipwreck.isBeached() == null;
        OverworldBiomeSource biomeSource = new OverworldBiomeSource(version, worldSeed);
        // should always be called
        if (shipwreck.canSpawn(start.getX(), start.getZ(), biomeSource)) System.out.println("OK Biome");
        assert shipwreck.isBeached() != null;
        System.out.println("Is beached " + shipwreck.isBeached());

        System.out.println(shipwreck.getLoot(start, worldSeed, rand, false));
    }

    public static void test1() {
        ChunkRand rand = new ChunkRand();
        long worldSeed = 1L;
        CPos cPos = new CPos(55, 49);
        System.out.println("Target: " + cPos.toBlockPos());
        MCVersion version = MCVersion.v1_16;
        Shipwreck shipwreck = new Shipwreck(version);
        RPos rPos = cPos.toRegionPos(shipwreck.getSpacing());
        CPos start = shipwreck.getInRegion(worldSeed, rPos.getX(), rPos.getZ(), rand);
        System.out.println("Got: " + start);
        assert cPos.equals(start);

        assert shipwreck.isBeached() == null;
        OverworldBiomeSource biomeSource = new OverworldBiomeSource(version, worldSeed);
        // should always be called
        if (shipwreck.canSpawn(start.getX(), start.getZ(), biomeSource)) System.out.println("OK Biome");
        assert shipwreck.isBeached() != null;
        System.out.println("Is beached " + shipwreck.isBeached());

        System.out.println(shipwreck.getLoot(start, worldSeed, rand, false));
    }
}
