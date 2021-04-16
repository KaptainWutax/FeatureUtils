package kaptainwutax.featureutils.structure;

import kaptainwutax.biomeutils.Biome;
import kaptainwutax.seedutils.mc.ChunkRand;
import kaptainwutax.seedutils.mc.Dimension;
import kaptainwutax.seedutils.mc.MCVersion;
import kaptainwutax.seedutils.mc.VersionMap;
import kaptainwutax.seedutils.mc.pos.CPos;

public class BastionRemnant extends UniformStructure<BastionRemnant> {

    public static final VersionMap<RegionStructure.Config> CONFIGS = new VersionMap<RegionStructure.Config>()
            .add(MCVersion.v1_16, new RegionStructure.Config(30, 4, 30084232))
            .add(MCVersion.v1_16_1, new RegionStructure.Config(27, 4, 30084232));

    public BastionRemnant(MCVersion version) {
        this(CONFIGS.getAsOf(version), version);
    }

    public BastionRemnant(RegionStructure.Config config, MCVersion version) {
        super(config, version);
    }

    public static String name() {
        return "bastion_remnant";
    }

    @Override
    public boolean canStart(Data<BastionRemnant> data, long structureSeed, ChunkRand rand) {
        if (!super.canStart(data, structureSeed, rand)) return false;
        return rand.nextInt(5) >= 2;
    }

    @Override
    public CPos getInRegion(long structureSeed, int regionX, int regionZ, ChunkRand rand) {
        CPos bastion = super.getInRegion(structureSeed, regionX, regionZ, rand);
        return rand.nextInt(5) >= 2 ? bastion : null;
    }

    @Override
    public boolean isValidDimension(Dimension dimension) {
        return dimension == Dimension.NETHER;
    }

    @Override
    public boolean isValidBiome(Biome biome) {
        return biome == Biome.NETHER_WASTES || biome == Biome.SOUL_SAND_VALLEY || biome == Biome.WARPED_FOREST
                || biome == Biome.CRIMSON_FOREST;
    }

}
