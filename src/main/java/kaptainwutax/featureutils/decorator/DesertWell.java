package kaptainwutax.featureutils.decorator;

import kaptainwutax.biomeutils.Biome;
import kaptainwutax.seedutils.mc.ChunkRand;
import kaptainwutax.seedutils.mc.Dimension;
import kaptainwutax.seedutils.mc.MCVersion;
import kaptainwutax.seedutils.mc.VersionMap;

public class DesertWell extends BiomelessDecorator<DesertWell.Config, DesertWell.Data> {

    public static final VersionMap<DesertWell.Config> CONFIGS = new VersionMap<DesertWell.Config>()
            .add(MCVersion.v1_13, new DesertWell.Config(3, 1, 0.001F))
            .add(MCVersion.v1_16, new DesertWell.Config(4, 13, 0.001F));

    public DesertWell(MCVersion version) {
        super(CONFIGS.getAsOf(version), version);
    }

    public DesertWell(DesertWell.Config config) {
        super(config, null);
    }

    @Override
    public String getName() {
        return "desert_well";
    }

    public float getChance() {
        return this.getConfig().chance;
    }

    @Override
    public boolean canStart(DesertWell.Data data, long structureSeed, ChunkRand rand) {
        super.canStart(data, structureSeed, rand);
        if(rand.nextFloat() >= this.getChance())return false;
        if(rand.nextInt(16) != data.offsetX)return false;
        if(rand.nextInt(16) != data.offsetZ)return false;
        return true;
    }

    @Override
    public boolean isValidDimension(Dimension dimension) {
        return dimension == Dimension.OVERWORLD;
    }

    @Override
    public boolean isValidBiome(Biome biome) {
        return biome == Biome.DESERT || biome == Biome.DESERT_HILLS || biome == Biome.DESERT_LAKES;
    }

    @Override
    public Data getData(long structureSeed, int chunkX, int chunkZ, ChunkRand rand) {
        this.setDecoratorSeed(structureSeed, chunkX, chunkZ, rand);
        if(rand.nextFloat() >= this.getChance())return null;
        int blockX = (chunkX << 4) + rand.nextInt(16);
        int blockZ = (chunkZ << 4) + rand.nextInt(16);
        return new DesertWell.Data(this, blockX, blockZ);
    }

    public DesertWell.Data at(int blockX, int blockZ) {
        return new DesertWell.Data(this, blockX, blockZ);
    }

    public static class Config extends BiomelessDecorator.Config {
        public final float chance;

        public Config(int index, int step, float chance) {
            super(index, step);
            this.chance = chance;
        }
    }

    public static class Data extends BiomelessDecorator.Data<DesertWell> {
        public final int blockX;
        public final int blockZ;
        public final int offsetX;
        public final int offsetZ;

        public Data(DesertWell feature, int blockX, int blockZ) {
            super(feature, blockX >> 4, blockZ >> 4);
            this.blockX = blockX;
            this.blockZ = blockZ;
            this.offsetX = blockX & 15;
            this.offsetZ = blockZ & 15;
        }
    }

}
