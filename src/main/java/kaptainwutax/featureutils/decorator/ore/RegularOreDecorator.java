package kaptainwutax.featureutils.decorator.ore;

import kaptainwutax.biomeutils.biome.Biome;
import kaptainwutax.mcutils.block.Blocks;
import kaptainwutax.mcutils.state.Dimension;
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
		float f = rand.nextFloat() * (float)Math.PI;
		float g = (float)this.getSize(biome) / 8.0F;
		int i = ceil(((float)this.getSize(biome) / 16.0F * 2.0F + 1.0F) / 2.0F);
		double d = (double)bPos.getX() + Math.sin(f) * (double)g;
		double e = (double)bPos.getX() - Math.sin(f) * (double)g;
		double h = (double)bPos.getZ() + Math.cos(f) * (double)g;
		double j = (double)bPos.getZ() - Math.cos(f) * (double)g;
		double l = bPos.getY() + rand.nextInt(3) - 2;
		double m = bPos.getY() + rand.nextInt(3) - 2;
		int n = bPos.getX() - ceil(g) - i;
		int o = bPos.getY() - 2 - i;
		int p = bPos.getZ() - ceil(g) - i;
		int q = 2 * (ceil(g) + i);
		int r = 2 * (2 + i);

		for(int s = n; s <= n + q; ++s) {
			for(int t = p; t <= p + q; ++t) {
				if(o <= generator.getFirstHeightInColumn(s, t, TerrainGenerator.OCEAN_FLOOR_WG)) {
					return this.generateVeinPart(generator, biome, rand, d, e, h, j, l, m, n, o, p, q, r);
				}
			}
		}

		return Collections.emptyList();
	}

	private List<BPos> generateVeinPart(TerrainGenerator generator, Biome biome, JRand rand, double startX, double endX, double startZ, double endZ, double startY, double endY, int x, int y, int z, int size, int i) {
		List<BPos> poses = new ArrayList<>();
		BitSet bitSet = new BitSet(size * i * size);
		int k = this.getSize(biome);
		double[] ds = new double[k * 4];

		int n;
		double p;
		double q;
		double r;
		double s;
		for(n = 0; n < k; ++n) {
			float f = (float)n / (float)k;
			p = MathHelper.lerp(f, startX, endX);
			q = MathHelper.lerp(f, startY, endY);
			r = MathHelper.lerp(f, startZ, endZ);
			s = rand.nextDouble() * (double)k / 16.0D;
			double m = ((Math.sin((float)Math.PI * f) + 1.0F) * s + 1.0D) / 2.0D;
			ds[n * 4] = p;
			ds[n * 4 + 1] = q;
			ds[n * 4 + 2] = r;
			ds[n * 4 + 3] = m;
		}

		for(n = 0; n < k - 1; ++n) {
			if(ds[n * 4 + 3] > 0.0D) {
				for(int o = n + 1; o < k; ++o) {
					if(ds[o * 4 + 3] > 0.0D) {
						p = ds[n * 4] - ds[o * 4];
						q = ds[n * 4 + 1] - ds[o * 4 + 1];
						r = ds[n * 4 + 2] - ds[o * 4 + 2];
						s = ds[n * 4 + 3] - ds[o * 4 + 3];
						if(s * s > p * p + q * q + r * r) {
							if(s > 0.0D) {
								ds[o * 4 + 3] = -1.0D;
							} else {
								ds[n * 4 + 3] = -1.0D;
							}
						}
					}
				}
			}
		}

		for(n = 0; n < k; ++n) {
			double u = ds[n * 4 + 3];
			if(u >= 0.0D) {
				double v = ds[n * 4];
				double w = ds[n * 4 + 1];
				double aa = ds[n * 4 + 2];
				int ab = Math.max(MathHelper.floor(v - u), x);
				int ac = Math.max(MathHelper.floor(w - u), y);
				int ad = Math.max(MathHelper.floor(aa - u), z);
				int ae = Math.max(MathHelper.floor(v + u), ab);
				int af = Math.max(MathHelper.floor(w + u), ac);
				int ag = Math.max(MathHelper.floor(aa + u), ad);

				for(int ah = ab; ah <= ae; ++ah) {
					double ai = ((double)ah + 0.5D - v) / u;
					if(ai * ai < 1.0D) {
						for(int aj = ac; aj <= af; ++aj) {
							double ak = ((double)aj + 0.5D - w) / u;
							if(ai * ai + ak * ak < 1.0D) {
								for(int al = ad; al <= ag; ++al) {
									double am = ((double)al + 0.5D - aa) / u;
									if(ai * ai + ak * ak + am * am < 1.0D) {
										int an = ah - x + (aj - y) * size + (al - z) * size * i;
										if(!bitSet.get(an)) {
											bitSet.set(an);
											BPos pos = new BPos(ah, aj, al);
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
