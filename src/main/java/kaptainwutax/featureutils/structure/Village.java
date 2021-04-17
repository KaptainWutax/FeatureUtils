package kaptainwutax.featureutils.structure;

import kaptainwutax.biomeutils.Biome;
import kaptainwutax.mcutils.state.Dimension;
import kaptainwutax.mcutils.version.MCVersion;
import kaptainwutax.mcutils.version.VersionMap;

public class Village extends OldStructure<Village> {

    public static final VersionMap<OldStructure.Config> CONFIGS = new VersionMap<OldStructure.Config>()
            .add(MCVersion.v1_8, new OldStructure.Config(10387312));

    public Village(MCVersion version) {
        this(CONFIGS.getAsOf(version), version);
    }

    public Village(RegionStructure.Config config, MCVersion version) {
        super(config, version);
    }

    public static String name() {
        return "village";
    }

    @Override
    public boolean isValidDimension(Dimension dimension) {
        return dimension == Dimension.OVERWORLD;
    }

    @Override
    public boolean isValidBiome(Biome biome) {
        if (biome == Biome.PLAINS || biome == Biome.DESERT || biome == Biome.SAVANNA) return true;
        if (biome == Biome.TAIGA && this.getVersion().isNewerOrEqualTo(MCVersion.v1_10)) return true;
        if (biome == Biome.SNOWY_TUNDRA && this.getVersion().isNewerOrEqualTo(MCVersion.v1_14)) return true;
        return false;
    }

}
