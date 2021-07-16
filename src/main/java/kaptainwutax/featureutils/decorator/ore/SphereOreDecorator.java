package kaptainwutax.featureutils.decorator.ore;

import kaptainwutax.biomeutils.biome.Biome;
import kaptainwutax.mcutils.block.Block;
import kaptainwutax.mcutils.state.Dimension;
import kaptainwutax.mcutils.util.math.DistanceMetric;
import kaptainwutax.mcutils.util.pos.BPos;
import kaptainwutax.mcutils.version.MCVersion;
import kaptainwutax.seedutils.rand.JRand;
import kaptainwutax.terrainutils.TerrainGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class SphereOreDecorator<C extends OreDecorator.Config, D extends OreDecorator.Data<?>> extends OreDecorator<C, D> {

	public SphereOreDecorator(C config, MCVersion version) {
		super(config, version);
	}

	@Override
	public Dimension getValidDimension() {
		return Dimension.OVERWORLD;
	}


	@Override
	protected List<BPos> generateOrePositions(BPos bPos, Biome biome, TerrainGenerator generator, JRand rand) {
		// we abuse the getSize as the halfHeight (to avoid a weird definition in OreDecorator
		List<BPos> poses = new ArrayList<>();
		int radius = this.getSize(biome);
		for(int x = bPos.getX() - radius; x <= bPos.getX() + radius; x++) {
			for(int z = bPos.getZ() - radius; z <= bPos.getZ() + radius; z++) {
				int distanceCenterX = x - bPos.getX();
				int distanceCenterZ = z - bPos.getZ();
				if(DistanceMetric.EUCLIDEAN_SQ.getDistance(distanceCenterX, 0, distanceCenterZ) <= radius * radius) {
					for(int y = bPos.getY() - this.getSize(biome); y <= bPos.getY() + this.getSize(biome); y++) {
						BPos currentPos = new BPos(x, y, z);
						Optional<Block> current = generator.getBlockAt(currentPos);
						if(!current.isPresent()) {
							continue;
						}
						for(Block block : this.getReplaceBlocks(biome)) {
							if(current.get().equals(block)) {
								poses.add(currentPos);
							}
						}
					}
				}
			}
		}
		return poses;
	}
}
