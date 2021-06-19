package kaptainwutax.featureutils.structure;

import kaptainwutax.biomeutils.biome.Biome;
import kaptainwutax.biomeutils.source.BiomeSource;
import kaptainwutax.featureutils.Feature;
import kaptainwutax.mcutils.util.pos.CPos;
import kaptainwutax.mcutils.version.MCVersion;
import kaptainwutax.terrainutils.TerrainGenerator;

import java.util.HashMap;
import java.util.Map;

public abstract class Structure<C extends Feature.Config, D extends Feature.Data<?>> extends Feature<C, D> {

	protected Biome biome;

	public static Map<Class<? extends Structure>, String> CLASS_TO_NAME = new HashMap<>();

	static {
		CLASS_TO_NAME.put(BastionRemnant.class, "bastion_remnant");
		CLASS_TO_NAME.put(BuriedTreasure.class, "buried_treasure");
		CLASS_TO_NAME.put(DesertPyramid.class, "desert_pyramid");
		CLASS_TO_NAME.put(EndCity.class, "endcity");
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

	public static String name() {
		return "structure";
	}

	@Override
	public String getName() {
		return getName(this.getClass());
	}

	public Biome getBiome() {
		return biome;
	}

	@Override
	public final boolean canSpawn(D data, BiomeSource source) {
		return this.canSpawn(data.chunkX, data.chunkZ, source);
	}

	public boolean canSpawn(CPos cPos, BiomeSource source) {
		return this.canSpawn(cPos.getX(), cPos.getZ(), source);
	}

	public boolean canSpawn(int chunkX, int chunkZ, BiomeSource source) {
		if (this.getVersion().isOlderThan(MCVersion.v1_16)) {
			this.biome = source.getBiome((chunkX << 4) + 9, 0, (chunkZ << 4) + 9);
		} else {
			this.biome = source.getBiomeForNoiseGen((chunkX << 2) + 2, 0, (chunkZ << 2) + 2);
		}
		return this.isValidBiome(this.biome);
	}

	public abstract boolean isValidBiome(Biome biome);


	@Override
	public final boolean canGenerate(D data, TerrainGenerator generator) {
		return this.canGenerate(data.chunkX, data.chunkZ, generator);
	}

	public boolean canGenerate(CPos cPos, TerrainGenerator generator) {
		return this.canGenerate(cPos.getX(), cPos.getZ(), generator);
	}

	public boolean canGenerate(int chunkX, int chunkZ, TerrainGenerator generator) {
		return this.isValidTerrain(generator, chunkX, chunkZ);
	}

	public boolean isValidTerrain(TerrainGenerator generator, int chunkX, int chunkZ) {
		return true;
	}

}
