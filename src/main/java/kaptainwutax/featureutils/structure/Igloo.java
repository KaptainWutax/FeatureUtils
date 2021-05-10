package kaptainwutax.featureutils.structure;

import kaptainwutax.biomeutils.biome.Biome;
import kaptainwutax.biomeutils.biome.Biomes;
import kaptainwutax.mcutils.rand.ChunkRand;
import kaptainwutax.mcutils.state.Dimension;
import kaptainwutax.mcutils.util.block.BlockRotation;
import kaptainwutax.mcutils.util.pos.CPos;
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

	public boolean hasBasement(long structureSeed, CPos cPos, ChunkRand rand) {

		if (getVersion().isNewerOrEqualTo(MCVersion.v1_9) && getVersion().isOlderThan(MCVersion.v1_14)) {
			rand.setPopulationSeed(structureSeed, cPos.getX(), cPos.getZ(), this.getVersion());
			// TODO figure how many calls here (ffs)
			BlockRotation rotation=BlockRotation.getRandom(rand);
			return rand.nextDouble()<0.5D;
		}
		if (getVersion().isNewerOrEqualTo(MCVersion.v1_14)){
			rand.setCarverSeed(structureSeed,cPos.getX(),cPos.getZ(),this.getVersion());
			BlockRotation rotation=BlockRotation.getRandom(rand);
			return rand.nextDouble()<0.5D;
		}
		return false;
	}

	@Override
	public boolean isValidDimension(Dimension dimension) {
		return dimension == Dimension.OVERWORLD;
	}

	@Override
	public boolean isValidBiome(Biome biome) {
		return biome == Biomes.SNOWY_TAIGA || biome == Biomes.SNOWY_TUNDRA;
	}

}
