package kaptainwutax.featureutils.structure;

import kaptainwutax.biomeutils.Biome;
import kaptainwutax.biomeutils.source.BiomeSource;
import kaptainwutax.featureutils.Feature;
import kaptainwutax.seedutils.mc.MCVersion;

import java.util.HashMap;
import java.util.Map;

public abstract class Structure<C extends Feature.Config, D extends Feature.Data<?>> extends Feature<C, D> {

    public static Map<Class<? extends Structure>, String> CLASS_TO_NAME = new HashMap<>();

    static {
        CLASS_TO_NAME.put(BastionRemnant.class, "bastion_remnant");
        CLASS_TO_NAME.put(BuriedTreasure.class, "buried_treasure");
        CLASS_TO_NAME.put(DesertPyramid.class, "desert_pyramid");
        CLASS_TO_NAME.put(EndCity.class, "end_city");
        CLASS_TO_NAME.put(Fortress.class, "fortress");
        CLASS_TO_NAME.put(Igloo.class, "igloo");
        CLASS_TO_NAME.put(JunglePyramid.class, "jungle_pyramid");
        CLASS_TO_NAME.put(Mansion.class, "mansion");
        CLASS_TO_NAME.put(Mineshaft.class, "mineshaft");
        CLASS_TO_NAME.put(Monument.class, "monument");
        CLASS_TO_NAME.put(NetherFossil.class, "nether_fossil");
        CLASS_TO_NAME.put(OceanRuin.class, "ocean_ruin");
        CLASS_TO_NAME.put(PillagerOutpost.class, "pillager_outpost");
        CLASS_TO_NAME.put(RuinedPortal.class, "ruined_portal");
        CLASS_TO_NAME.put(Shipwreck.class, "shipwreck");
        CLASS_TO_NAME.put(Stronghold.class, "stronghold");
        CLASS_TO_NAME.put(SwampHut.class, "swamp_hut");
        CLASS_TO_NAME.put(Village.class, "village");
    }

    public Structure(C config, MCVersion version) {
        super(config, version);
    }

    public static String getName(Class<? extends Structure> structure) {
        return CLASS_TO_NAME.get(structure);
    }

    @Override
    public String getName() {
        return getName(this.getClass());
    }

    public static String name(){
        return "structure";
    }

    @Override
    public final boolean canSpawn(D data, BiomeSource source) {
        return this.canSpawn(data.chunkX, data.chunkZ, source);
    }

    public boolean canSpawn(int chunkX, int chunkZ, BiomeSource source) {
        if (this.getVersion().isOlderThan(MCVersion.v1_16)) {
            return this.isValidBiome(source.getBiome((chunkX << 4) + 9, 0, (chunkZ << 4) + 9));
        }

        return this.isValidBiome(source.getBiomeForNoiseGen((chunkX << 2) + 2, 0, (chunkZ << 2) + 2));
    }

    public abstract boolean isValidBiome(Biome biome);

}
