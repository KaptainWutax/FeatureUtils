package kaptainwutax.featureutils.decorator.ore;

import kaptainwutax.biomeutils.biome.Biome;
import kaptainwutax.mcutils.block.Blocks;
import kaptainwutax.mcutils.state.Dimension;
import kaptainwutax.mcutils.util.block.BlockDirection;
import kaptainwutax.mcutils.util.pos.BPos;
import kaptainwutax.mcutils.version.MCVersion;
import kaptainwutax.seedutils.rand.JRand;
import kaptainwutax.terrainutils.TerrainGenerator;

import java.util.ArrayList;
import java.util.List;

public abstract class ScatterOreDecorator<C extends OreDecorator.Config, D extends OreDecorator.Data<?>> extends OreDecorator<C, D> {

	public ScatterOreDecorator(C config, MCVersion version) {
		super(config, version);
	}

	@Override
	public Dimension getValidDimension() {
		return Dimension.NETHER;
	}

	@Override
	protected List<BPos> generateOrePositions(BPos bPos, Biome biome, TerrainGenerator generator, JRand rand) {
		List<BPos> poses = new ArrayList<>();
		int count = rand.nextInt(this.getSize(biome) + 1);

		for(int i = 0; i < count; ++i) {
			BPos startPos = this.getStartPos(rand, bPos, Math.min(i, 7));
			if(this.getReplaceBlocks(biome).contains(generator.getBlockAt(startPos).orElse(Blocks.AIR)) && !this.checkAir(generator, startPos)) {
				poses.add(startPos);
			}
		}

		return poses;
	}

	private BPos getStartPos(JRand rand, BPos pos, int size) {
		int x = this.randomCoord(rand, size);
		int y = this.randomCoord(rand, size);
		int z = this.randomCoord(rand, size);
		return pos.add(x, y, z);
	}

	private int randomCoord(JRand rand, int size) {
		return Math.round((rand.nextFloat() - rand.nextFloat()) * (float)size);
	}

	private boolean checkAir(TerrainGenerator generator, BPos pos) {
		for(BlockDirection direction : BlockDirection.values()) {
			BPos rel = pos.relative(direction);
			if(Blocks.AIR.equals(generator.getBlockAt(rel).orElse(Blocks.AIR))) {
				return true;
			}
		}

		return false;
	}

}
