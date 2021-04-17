package kaptainwutax.featureutils.structure;

import kaptainwutax.biomeutils.Biome;
import kaptainwutax.featureutils.Feature;
import kaptainwutax.mcutils.rand.ChunkRand; import kaptainwutax.mcutils.state.Dimension;
import kaptainwutax.mcutils.version.MCVersion;
import kaptainwutax.mcutils.version.VersionMap;

public class Mineshaft extends Structure<Mineshaft.Config, Feature.Data<?>> {

    public static final VersionMap<Mineshaft.Config> CONFIGS = new VersionMap<Mineshaft.Config>()
            .add(MCVersion.v1_8, new Mineshaft.Config(0.004D));

    public Mineshaft(MCVersion version) {
        this(CONFIGS.getAsOf(version), version);
    }

    public Mineshaft(Mineshaft.Config config, MCVersion version) {
        super(config, version);
    }

    public static String name() {
        return "mineshaft";
    }

    private double getChance() {
        return this.getConfig().chance;
    }

    @Override
    public boolean canStart(Data<?> data, long structureSeed, ChunkRand rand) {
        rand.setCarverSeed(structureSeed, data.chunkX, data.chunkZ, this.getVersion());
        return rand.nextDouble() < this.getChance();
    }

    @Override
    public boolean isValidDimension(Dimension dimension) {
        return dimension == Dimension.OVERWORLD;
    }

    @Override
    public boolean isValidBiome(Biome biome) {
        return biome.getCategory() == Biome.Category.OCEAN
                || biome == Biome.BAMBOO_JUNGLE || biome == Biome.BAMBOO_JUNGLE_HILLS || biome == Biome.BIRCH_FOREST
                || biome == Biome.BIRCH_FOREST_HILLS || biome == Biome.DARK_FOREST || biome == Biome.DARK_FOREST_HILLS
                || biome == Biome.DESERT || biome == Biome.DESERT_HILLS || biome == Biome.DESERT_LAKES
                || biome == Biome.FLOWER_FOREST || biome == Biome.FOREST || biome == Biome.GIANT_SPRUCE_TAIGA
                || biome == Biome.GIANT_SPRUCE_TAIGA_HILLS || biome == Biome.GIANT_TREE_TAIGA || biome == Biome.GIANT_TREE_TAIGA_HILLS
                || biome == Biome.GRAVELLY_MOUNTAINS || biome == Biome.ICE_SPIKES || biome == Biome.JUNGLE
                || biome == Biome.JUNGLE_EDGE || biome == Biome.JUNGLE_HILLS || biome == Biome.MODIFIED_GRAVELLY_MOUNTAINS
                || biome == Biome.MODIFIED_JUNGLE || biome == Biome.MODIFIED_JUNGLE_EDGE || biome == Biome.MOUNTAIN_EDGE
                || biome == Biome.MOUNTAINS || biome == Biome.MUSHROOM_FIELDS || biome == Biome.MUSHROOM_FIELD_SHORE
                || biome == Biome.PLAINS || biome == Biome.SAVANNA || biome == Biome.SAVANNA_PLATEAU
                || biome == Biome.SHATTERED_SAVANNA || biome == Biome.SHATTERED_SAVANNA_PLATEAU || biome == Biome.SNOWY_MOUNTAINS
                || biome == Biome.SNOWY_TAIGA || biome == Biome.SNOWY_TAIGA_HILLS || biome == Biome.SNOWY_TAIGA_MOUNTAINS
                || biome == Biome.SNOWY_TUNDRA || biome == Biome.STONE_SHORE || biome == Biome.SUNFLOWER_PLAINS
                || biome == Biome.TAIGA || biome == Biome.TAIGA_HILLS || biome == Biome.TAIGA_MOUNTAINS
                || biome == Biome.TALL_BIRCH_FOREST || biome == Biome.TALL_BIRCH_HILLS || biome == Biome.WOODED_HILLS
                || biome == Biome.WOODED_MOUNTAINS || biome.getCategory() == Biome.Category.MESA
                //hardcoded checks
                || biome == Biome.BEACH || biome == Biome.FROZEN_RIVER || biome == Biome.RIVER
                || biome == Biome.SNOWY_BEACH || biome == Biome.SWAMP || biome == Biome.SWAMP_HILLS;
    }

    public Feature.Data<Mineshaft> at(int chunkX, int chunkZ) {
        return new Feature.Data<>(this, chunkX, chunkZ);
    }

    public static class Config extends Feature.Config {
        public final double chance;

        public Config(double chance) {
            this.chance = chance;
        }
    }

}
