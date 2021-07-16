package kaptainwutax.featureutils.decorator.ore.overworld;

import kaptainwutax.biomeutils.biome.Biome;
import kaptainwutax.biomeutils.biome.Biomes;
import kaptainwutax.featureutils.decorator.ore.HeightProvider;
import kaptainwutax.featureutils.decorator.ore.OreDecorator;
import kaptainwutax.mcutils.block.Blocks;
import kaptainwutax.mcutils.state.Dimension;
import kaptainwutax.mcutils.util.pos.BPos;
import kaptainwutax.mcutils.version.MCVersion;
import kaptainwutax.mcutils.version.VersionMap;
import kaptainwutax.seedutils.rand.JRand;
import kaptainwutax.terrainutils.TerrainGenerator;

import java.util.ArrayList;
import java.util.List;

public class EmeraldOre extends OreDecorator<OreDecorator.Config, OreDecorator.Data<EmeraldOre>> {

	public static final VersionMap<Config> CONFIGS = new VersionMap<Config>()
		.add(MCVersion.v1_13, new Config(14, 4, 1, 1, HeightProvider.custom(r -> r.nextInt(28) + 4), Blocks.EMERALD_ORE, STONE))
		.add(MCVersion.v1_16, new Config(14, 6, 1, 1, HeightProvider.custom(r -> r.nextInt(28) + 4), Blocks.EMERALD_ORE, STONE))
		.add(MCVersion.v1_17, new Config(17, 6, 1, 1, HeightProvider.uniformRange(4, 31), Blocks.EMERALD_ORE, BASE_STONE_OVERWORLD));

	public EmeraldOre(MCVersion version) {
		super(CONFIGS.getAsOf(version), version);
	}

	@Override
	public String getName() {
		return name();
	}

	public static String name() {
		return "emerald_ore";
	}

	@Override
	protected BPos generateBasePosition(int chunkX, int chunkZ, Biome biome, JRand rand) {
		return new BPos(chunkX << 4, 0, chunkZ << 4);
	}

	@Override
	public Dimension getValidDimension() {
		return Dimension.OVERWORLD;
	}

	@Override
	public boolean isValidBiome(Biome biome) {
		return biome == Biomes.MOUNTAINS || biome == Biomes.MOUNTAIN_EDGE || biome == Biomes.WOODED_MOUNTAINS || biome == Biomes.GRAVELLY_MOUNTAINS || biome == Biomes.MODIFIED_GRAVELLY_MOUNTAINS;
	}

	@Override
	protected List<BPos> generateOrePositions(BPos bPos, Biome biome, TerrainGenerator generator, JRand rand) {
		List<BPos> poses = new ArrayList<>();
		int count;
		if(this.getVersion().isNewerOrEqualTo(MCVersion.v1_17_1)) {
			count = nextBetween(rand, 3, 8);
		} else if(this.getVersion().isNewerOrEqualTo(MCVersion.v1_17)) {
			count = nextBetween(rand, 6, 24);
		} else {
			count = 3 + rand.nextInt(6);
		}
		for(int i = 0; i < count; i++) {
			if(this.getVersion().isNewerOrEqualTo(MCVersion.v1_15)) {
				int x = bPos.getX() + rand.nextInt(16);
				int z = bPos.getZ() + rand.nextInt(16);
				int y = this.getHeightProvider(biome).getY(rand);
				poses.add(new BPos(x, y, z));
			} else {
				int x = bPos.getX() + rand.nextInt(16);
				int y = this.getHeightProvider(biome).getY(rand);
				int z = bPos.getZ() + rand.nextInt(16);
				poses.add(new BPos(x, y, z));
			}
		}
		return poses;
	}

	private static int nextBetween(JRand rand, int min, int max) {
		return rand.nextInt(max - min + 1) + min;
	}

}
