package kaptainwutax.featureutils.structure;

import com.sun.org.apache.xpath.internal.operations.Bool;
import kaptainwutax.biomeutils.Biome;
import kaptainwutax.seedutils.mc.ChunkRand;
import kaptainwutax.seedutils.mc.Dimension;
import kaptainwutax.seedutils.mc.MCVersion;
import kaptainwutax.seedutils.mc.VersionMap;
import kaptainwutax.seedutils.mc.pos.CPos;

public class Shipwreck extends UniformStructure<Shipwreck> {
    private ChunkRand random = null; // this is an internal one as it will be updated on a need to know basis
    private Boolean isBeached = null;
    private Rotation rotation = null;
    private static final String[] STRUCTURE_LOCATION_BEACHED = new String[] {"shipwreck/with_mast", "shipwreck/sideways_full", "shipwreck/sideways_fronthalf", "shipwreck/sideways_backhalf", "shipwreck/rightsideup_full", "shipwreck/rightsideup_fronthalf", "shipwreck/rightsideup_backhalf", "shipwreck/with_mast_degraded", "shipwreck/rightsideup_full_degraded", "shipwreck/rightsideup_fronthalf_degraded", "shipwreck/rightsideup_backhalf_degraded"};
    private static final String[] STRUCTURE_LOCATION_OCEAN = new String[] {"shipwreck/with_mast", "shipwreck/upsidedown_full", "shipwreck/upsidedown_fronthalf", "shipwreck/upsidedown_backhalf", "shipwreck/sideways_full", "shipwreck/sideways_fronthalf", "shipwreck/sideways_backhalf", "shipwreck/rightsideup_full", "shipwreck/rightsideup_fronthalf", "shipwreck/rightsideup_backhalf", "shipwreck/with_mast_degraded", "shipwreck/upsidedown_full_degraded", "shipwreck/upsidedown_fronthalf_degraded", "shipwreck/upsidedown_backhalf_degraded", "shipwreck/sideways_full_degraded", "shipwreck/sideways_fronthalf_degraded", "shipwreck/sideways_backhalf_degraded", "shipwreck/rightsideup_full_degraded", "shipwreck/rightsideup_fronthalf_degraded", "shipwreck/rightsideup_backhalf_degraded"};

    public static final VersionMap<RegionStructure.Config> CONFIGS = new VersionMap<RegionStructure.Config>()
            .add(MCVersion.v1_13, new RegionStructure.Config(15, 8, 165745295))
            .add(MCVersion.v1_13_1, new RegionStructure.Config(16, 8, 165745295))
            .add(MCVersion.v1_16, new RegionStructure.Config(24, 4, 165745295));

    public Shipwreck(MCVersion version) {
        this(CONFIGS.getAsOf(version), version);
    }

    public Shipwreck(RegionStructure.Config config, MCVersion version) {
        super(config, version);
    }

    /**
     * This is a dangerous utility, we provide it on a need to know basis (don't use it)
     *
     * @return
     */
    public ChunkRand getInternalRandom() {
        return random;
    }

    public String getType(){
        if (isBeached==null) return null;
        if (rotation==null) return null;
        String[] arr=isBeached ? STRUCTURE_LOCATION_BEACHED : STRUCTURE_LOCATION_OCEAN;
        return arr[random.nextInt(arr.length)];
	}

    /**
     * This should be called before any operation related to nbt
     *
     * @param structureSeed
     * @param chunkPos
     * @param version
     * @return
     */
    public Rotation getRotation(long structureSeed, CPos chunkPos, MCVersion version) {
        // first call does the seeding the rest doesn't
        if (rotation == null) {
            random = new ChunkRand();
            random.setCarverSeed(structureSeed, chunkPos.getX(), chunkPos.getZ(), version);
            rotation = Rotation.getRandom(random);
        }
        return rotation;
    }

    @Override
    public boolean canStart(Data<Shipwreck> data, long structureSeed, ChunkRand rand) {
        return super.canStart(data, structureSeed, rand);
    }

    @Override
    public boolean isValidDimension(Dimension dimension) {
        return dimension == Dimension.OVERWORLD;
    }

	/**
	 * This will only work if you call canSpawn before
	 * @return
	 */
	public Boolean isBeached() {
        return isBeached;
    }

    @Override
    public boolean isValidBiome(Biome biome) {
        isBeached = biome == Biome.BEACH || biome == Biome.SNOWY_BEACH;
        return biome.getCategory() == Biome.Category.OCEAN || isBeached;
    }

    public enum Rotation {
        NONE,
        CLOCKWISE_90,
        CLOCKWISE_180,
        COUNTERCLOCKWISE_90;

        public static Rotation getRandom(ChunkRand rand) {
            return values()[rand.nextInt(values().length)];
        }
    }

}
