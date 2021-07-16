package kaptainwutax.featureutils.decorator.ore;

import kaptainwutax.biomeutils.biome.Biome;
import kaptainwutax.mcutils.block.Blocks;
import kaptainwutax.mcutils.state.Dimension;
import kaptainwutax.mcutils.util.math.DistanceMetric;
import kaptainwutax.mcutils.util.pos.BPos;
import kaptainwutax.mcutils.version.MCVersion;
import kaptainwutax.noiseutils.utils.MathHelper;
import kaptainwutax.seedutils.rand.JRand;
import kaptainwutax.terrainutils.TerrainGenerator;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;

public abstract class RegularOreDecorator<C extends OreDecorator.Config, D extends OreDecorator.Data<?>> extends OreDecorator<C, D> {

	public RegularOreDecorator(C config, MCVersion version) {
		super(config, version);
	}

	@Override
	public Dimension getValidDimension() {
		return Dimension.OVERWORLD;
	}

	@Override
	protected List<BPos> generateOrePositions(BPos bPos, Biome biome, TerrainGenerator generator, JRand rand) {
		float angle = rand.nextFloat() * (float)Math.PI;
		float size = (float)this.getSize(biome) / 8.0F;
		int amortizedSize = ceil(((float)this.getSize(biome) / 16.0F * 2.0F + 1.0F) / 2.0F);
		// this is just a way to compute a circle 4 points centered on X,Z
		double offsetXPos = (double)bPos.getX() + Math.sin(angle) * (double)size;
		double offsetXNeg = (double)bPos.getX() - Math.sin(angle) * (double)size;
		double offsetZPos = (double)bPos.getZ() + Math.cos(angle) * (double)size;
		double offsetZNeg = (double)bPos.getZ() - Math.cos(angle) * (double)size;
		double offsetYPos = bPos.getY() + rand.nextInt(3) - 2;
		double offsetYNeg = bPos.getY() + rand.nextInt(3) - 2;
		int startX = bPos.getX() - ceil(size) - amortizedSize;
		int startY = bPos.getY() - 2 - amortizedSize;
		int startZ = bPos.getZ() - ceil(size) - amortizedSize;
		int oreSize = 2 * (ceil(size) + amortizedSize);
		int radius = 2 * (2 + amortizedSize);

		for(int x = startX; x <= startX + oreSize; ++x) {
			for(int z = startZ; z <= startZ + oreSize; ++z) {
				if(startY <= generator.getFirstHeightInColumn(x, z, TerrainGenerator.OCEAN_FLOOR_WG)) {
					return this.generateVeinPart(generator, biome, rand, offsetXPos, offsetXNeg, offsetZPos, offsetZNeg, offsetYPos, offsetYNeg, startX, startY, startZ, oreSize, radius);
				}
			}
		}

		return Collections.emptyList();
	}

	private List<BPos> generateVeinPart(TerrainGenerator generator, Biome biome, JRand rand, double offsetXPos, double offsetXNeg, double offsetZPos, double offsetZNeg, double offsetYPos, double offsetYNeg, int startX, int startY, int startZ, int oreSize, int radius) {
		List<BPos> poses = new ArrayList<>();
		BitSet bitSet = new BitSet(oreSize * radius * oreSize);
		int size = this.getSize(biome);
		double[] store = new double[size * 4];


		for(int i = 0; i < size; ++i) {
			float percent = (float)i / (float)size;
			double x = MathHelper.lerp(percent, offsetXPos, offsetXNeg);
			double y = MathHelper.lerp(percent, offsetYPos, offsetYNeg);
			double z = MathHelper.lerp(percent, offsetZPos, offsetZNeg);
			double length = rand.nextDouble() * (double)size / 16.0D;
			double offset = ((Math.sin((float)Math.PI * percent) + 1.0F) * length + 1.0D) / 2.0D;
			store[i * 4] = x;
			store[i * 4 + 1] = y;
			store[i * 4 + 2] = z;
			store[i * 4 + 3] = offset;
		}

		for(int i = 0; i < size - 1; ++i) {
			if(store[i * 4 + 3] > 0.0D) {
				for(int j = i + 1; j < size; ++j) {
					if(store[j * 4 + 3] > 0.0D) {
						double diffX = store[i * 4] - store[j * 4];
						double diffY = store[i * 4 + 1] - store[j * 4 + 1];
						double diffZ = store[i * 4 + 2] - store[j * 4 + 2];
						double offset = store[i * 4 + 3] - store[j * 4 + 3];
						if(offset * offset > diffX * diffX + diffY * diffY + diffZ * diffZ) {
							if(offset > 0.0D) {
								store[j * 4 + 3] = -1.0D;
							} else {
								store[i * 4 + 3] = -1.0D;
							}
						}
					}
				}
			}
		}

		for(int i = 0; i < size; ++i) {
			double offset = store[i * 4 + 3];
			if(offset >= 0.0D) {
				double x = store[i * 4];
				double y = store[i * 4 + 1];
				double z = store[i * 4 + 2];

				int minX = Math.max(MathHelper.floor(x - offset), startX);
				int minY = Math.max(MathHelper.floor(y - offset), startY);
				int minZ = Math.max(MathHelper.floor(z - offset), startZ);

				int maxX = Math.max(MathHelper.floor(x + offset), minX);
				int maxY = Math.max(MathHelper.floor(y + offset), minY);
				int maxZ = Math.max(MathHelper.floor(z + offset), minZ);

				for(int X = minX; X <= maxX; ++X) {
					double xSlide = ((double)X + 0.5D - x) / offset;
					if(xSlide * xSlide < 1.0D) {
						for(int Y = minY; Y <= maxY; ++Y) {
							double ySlide = ((double)Y + 0.5D - y) / offset;
							if(xSlide * xSlide + ySlide * ySlide < 1.0D) {
								for(int Z = minZ; Z <= maxZ; ++Z) {
									double zSlide = ((double)Z + 0.5D - z) / offset;
									if(xSlide * xSlide + ySlide * ySlide + zSlide * zSlide < 1.0D) {
										int area = X - startX + (Y - startY) * oreSize + (Z - startZ) * oreSize * radius;
										if(!bitSet.get(area)) {
											bitSet.set(area);
											BPos pos = new BPos(X, Y, Z);
											if(generator.getDefaultBlock().equals(generator.getBlockAt(pos).orElse(Blocks.AIR))) {
												poses.add(pos);
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}

		return poses;
	}

	private static int ceil(float f) {
		int i = (int)f;
		return f > (float)i ? i + 1 : i;
	}

}
