package kaptainwutax.featureutils.structure;

import kaptainwutax.biomeutils.Biome;
import kaptainwutax.mcutils.state.Dimension;
import kaptainwutax.mcutils.version.MCVersion;
import kaptainwutax.mcutils.version.VersionMap;

public class Igloo extends OldStructure<Igloo> {

    public static final VersionMap<OldStructure.Config> CONFIGS = new VersionMap<OldStructure.Config>()
            .add(MCVersion.v1_9, new OldStructure.Config(14357617))
            .add(MCVersion.v1_13, new OldStructure.Config(14357618));

    public Igloo(MCVersion version) {
        this(CONFIGS.getAsOf(version), version);
    }

    public Igloo(RegionStructure.Config config, MCVersion version) {
        super(config, version);
    }

    public static String name() {
        return "igloo";
    }

    @Override
    public boolean isValidDimension(Dimension dimension) {
        return dimension == Dimension.OVERWORLD;
    }

    @Override
    public boolean isValidBiome(Biome biome) {
        return biome == Biome.SNOWY_TAIGA || biome == Biome.SNOWY_TUNDRA;
    }

}
