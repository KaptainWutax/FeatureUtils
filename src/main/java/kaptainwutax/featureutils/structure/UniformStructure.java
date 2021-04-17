package kaptainwutax.featureutils.structure;

import kaptainwutax.mcutils.rand.ChunkRand;
import kaptainwutax.mcutils.version.MCVersion;
import kaptainwutax.mcutils.util.pos.CPos;

public abstract class UniformStructure<T extends UniformStructure<T>> extends RegionStructure<RegionStructure.Config, RegionStructure.Data<T>> {

    private final int offset;

    public UniformStructure(RegionStructure.Config config, MCVersion version) {
        super(config, version);
        this.offset = this.getSpacing() - this.getSeparation();
    }

    public static String name() {
        return "uniform_structure";
    }

    public int getOffset() {
        return this.offset;
    }

    @Override
    public boolean canStart(Data<T> data, long structureSeed, ChunkRand rand) {
        rand.setSeed(data.baseRegionSeed + structureSeed);
        return rand.nextInt(this.offset) == data.offsetX && rand.nextInt(this.offset) == data.offsetZ;
    }

    @Override
    public CPos getInRegion(long structureSeed, int regionX, int regionZ, ChunkRand rand) {
        rand.setRegionSeed(structureSeed, regionX, regionZ, this.getSalt(), this.getVersion());

        return new CPos(
                regionX * this.getSpacing() + rand.nextInt(this.getOffset()),
                regionZ * this.getSpacing() + rand.nextInt(this.getOffset())
        );
    }

}
