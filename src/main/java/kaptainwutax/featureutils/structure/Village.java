package kaptainwutax.featureutils.structure;

import kaptainwutax.biomeutils.biome.Biome;
import kaptainwutax.biomeutils.biome.Biomes;
import kaptainwutax.mcutils.rand.ChunkRand;
import kaptainwutax.mcutils.state.Dimension;
import kaptainwutax.mcutils.util.block.BlockRotation;
import kaptainwutax.mcutils.util.pos.CPos;
import kaptainwutax.mcutils.version.MCVersion;
import kaptainwutax.mcutils.version.VersionMap;
import kaptainwutax.terrainutils.utils.MathHelper;

public class Village extends OldStructure<Village> {

	Biome biome=null;

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


	public boolean isZombieVillage(long structureSeed, CPos cPos, ChunkRand rand) {
		rand.setCarverSeed(structureSeed, cPos.getX(), cPos.getZ(), this.getVersion());
		// burn a call (nextInt)
		rand.advance(1);
		if (getVersion().isNewerOrEqualTo(MCVersion.v1_10) && getVersion().isOlderThan(MCVersion.v1_14)) {
			int size=0; // this might change via settings;
			getInt(rand, 2 + size, 4 + size * 2);
			getInt(rand, 0 + size, 1 + size);
			getInt(rand, 0 + size, 2 + size);
			getInt(rand, 2 + size, 5 + size * 3);
			getInt(rand, 0 + size, 2 + size);
			getInt(rand, 1 + size, 4 + size);
			getInt(rand, 2 + size, 4 + size * 2);
			getInt(rand, 0, 1 + size);
			getInt(rand, 0 + size, 3 + size * 2);
			BlockRotation rotation=BlockRotation.getRandom(rand);
			return rand.nextInt(50)==0;
		}
		if (getVersion().isNewerOrEqualTo(MCVersion.v1_14) && biome!=null){
			// we trust for now cubiomes but we will later move that to villageGenerator
			if (Biomes.PLAINS.equals(biome)) {
				return rand.nextInt(204) >= 200;
			} else if (Biomes.DESERT.equals(biome)) {
				return rand.nextInt(250) >= 245;
			} else if (Biomes.SAVANNA.equals(biome)) {
				return rand.nextInt(459) >= 450;
			} else if (Biomes.TAIGA.equals(biome)) {
				return rand.nextInt(100) >= 98;
			} else if (Biomes.SNOWY_TUNDRA.equals(biome)) {
				return rand.nextInt(306) >= 300;
			}
		}
		return false;

	}

	private int getInt(ChunkRand rand,int minimum,int maximum){
		return minimum >= maximum ? minimum : rand.nextInt(maximum - minimum + 1) + minimum;
	}

	@Override
	public boolean isValidDimension(Dimension dimension) {
		return dimension == Dimension.OVERWORLD;
	}

	@Override
	public boolean isValidBiome(Biome biome) {
		this.biome=biome;
		if (biome == Biomes.PLAINS || biome == Biomes.DESERT || biome == Biomes.SAVANNA) return true;
		if (biome == Biomes.TAIGA && this.getVersion().isNewerOrEqualTo(MCVersion.v1_10)) return true;
		return biome == Biomes.SNOWY_TUNDRA && this.getVersion().isNewerOrEqualTo(MCVersion.v1_14);
	}

}
