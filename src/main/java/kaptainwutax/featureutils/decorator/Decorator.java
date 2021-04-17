package kaptainwutax.featureutils.decorator;

import kaptainwutax.biomeutils.Biome;
import kaptainwutax.biomeutils.source.BiomeSource;
import kaptainwutax.featureutils.Feature;
import kaptainwutax.mcutils.rand.ChunkRand;
import kaptainwutax.mcutils.version.MCVersion;

import java.util.HashMap;
import java.util.Map;

public abstract class Decorator<C extends Decorator.Config, D extends Decorator.Data<?>> extends Feature<C, D> {

    public Decorator(C config, MCVersion version) {
        super(config, version);
    }

    public int getIndex() {
        return this.getConfig().index;
    }

    public int getStep() {
        return this.getConfig().step;
    }

    public int getDefaultSalt() {
        return this.getConfig().salt;
    }

    public int getSalt(Biome biome) {
        return this.getConfig().getSalt(biome);
    }

    protected void setDecoratorSeed(long structureSeed, int chunkX, int chunkZ, Biome biome, ChunkRand rand) {
        rand.setDecoratorSeed(structureSeed, chunkX << 4, chunkZ << 4, this.getSalt(biome), this.getVersion());
    }

    @Override
    public boolean canStart(D data, long structureSeed, ChunkRand rand) {
        this.setDecoratorSeed(structureSeed, data.chunkX, data.chunkZ, data.biome, rand);
        return true;
    }

    @Override
    public boolean canSpawn(D data, BiomeSource source) {
        Biome biome = this.getVersion().isOlderThan(MCVersion.v1_16)
                ? source.getBiome((data.chunkX << 4) + 8, 0, (data.chunkZ << 4) + 8)
                : source.getBiomeForNoiseGen((data.chunkX << 2) + 2, 0, (data.chunkZ << 2) + 2);
        if (!this.isValidBiome(biome)) return false;
        if (data.biome == null) return true;
        return this.getSalt(biome) == this.getSalt(biome);
    }

    public boolean canSpawn(int chunkX, int chunkZ, BiomeSource source) {
        if (this.getVersion().isOlderThan(MCVersion.v1_16)) {
            return this.isValidBiome(source.getBiome((chunkX << 4) + 8, 0, (chunkZ << 4) + 8));
        }

        return this.isValidBiome(source.getBiomeForNoiseGen((chunkX << 2) + 2, 0, (chunkZ << 2) + 2));
    }

    public abstract boolean isValidBiome(Biome biome);

    public abstract D getData(long structureSeed, int chunkX, int chunkZ, Biome biome, ChunkRand rand);

    public static class Config extends Feature.Config {
        public final int index;
        public final int step;
        public final int salt;

        private Map<Biome, Config> overrides = new HashMap<>();

        public Config(int index, int step) {
            this.index = index;
            this.step = step;
            this.salt = this.step * 10000 + this.index;
        }

        public int getSalt(Biome biome) {
            return this.overrides.getOrDefault(biome, this).salt;
        }

        public Config add(int index, int step, Biome... biomes) {
            for (Biome biome : biomes) {
                this.overrides.put(biome, new Config(index, step));
            }

            return this;
        }
    }

    public static class Data<T extends Decorator<?, ?>> extends Feature.Data<T> {
        public final Biome biome;

        public Data(T feature, int chunkX, int chunkZ, Biome biome) {
            super(feature, chunkX, chunkZ);
            this.biome = biome;
        }
    }

}
