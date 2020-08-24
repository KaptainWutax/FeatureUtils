package kaptainwutax.featureutils.decorator;

import kaptainwutax.biomeutils.Biome;
import kaptainwutax.seedutils.mc.ChunkRand;
import kaptainwutax.seedutils.mc.MCVersion;

public abstract class BiomelessDecorator<C extends BiomelessDecorator.Config, D extends BiomelessDecorator.Data<?>> extends Decorator<C, D> {

    public BiomelessDecorator(C config, MCVersion version) {
        super(config, version);
    }

    @Override
    public int getSalt(Biome biome) {
        return this.getDefaultSalt();
    }

    @Override
    protected void setDecoratorSeed(long structureSeed, int chunkX, int chunkZ, Biome biome, ChunkRand rand) {
        this.setDecoratorSeed(structureSeed, chunkX, chunkZ, rand);
    }

    protected void setDecoratorSeed(long structureSeed, int chunkX, int chunkZ, ChunkRand rand) {
        rand.setDecoratorSeed(structureSeed, chunkX << 4, chunkZ << 4, this.getDefaultSalt(), this.getVersion());
    }

    @Override
    public final D getData(long structureSeed, int chunkX, int chunkZ, Biome biome, ChunkRand rand) {
        return this.getData(structureSeed, chunkX, chunkZ, rand);
    }

    public abstract D getData(long structureSeed, int chunkX, int chunkZ, ChunkRand rand);

    public static class Config extends Decorator.Config {
        public Config(int index, int step) {
            super(index, step);
        }

        @Override
        public Decorator.Config add(int index, int step, Biome... biomes) {
            throw new UnsupportedOperationException("BiomelessDecorator shouldn't use biome overrides");
        }
    }

    public static class Data<T extends Decorator<?, ?>> extends Decorator.Data<T> {
        public Data(T feature, int chunkX, int chunkZ) {
            super(feature, chunkX, chunkZ, null);
        }
    }

}
