package kaptainwutax.featureutils.structure.device.node;

import kaptainwutax.featureutils.structure.RegionStructure;
import kaptainwutax.featureutils.structure.TriangularStructure;
import kaptainwutax.featureutils.structure.device.CoordChecker;
import kaptainwutax.featureutils.structure.device.ParentInfo;
import kaptainwutax.mathutils.util.Mth;
import kaptainwutax.mcutils.rand.seed.RegionSeed;
import kaptainwutax.seedutils.lcg.LCG;

import java.util.Set;

public class TriangularNode extends Node<RegionStructure.Config> {

	private final int peak;
	private final Analyser analyser;

	protected TriangularNode(RegionStructure.Config config, int regionX, int regionZ, CoordChecker checker) {
		super(config, regionX, regionZ, checker);
		this.peak = this.config.spacing - this.config.separation;
		this.analyser = new Analyser(this);
	}

	protected TriangularNode(TriangularStructure<?> structure, int regionX, int regionZ, CoordChecker checker) {
		this(structure.getConfig(), regionX, regionZ, checker);
	}

	public static TriangularNode head(TriangularStructure<?> structure, int regionX, int regionZ, CoordChecker.Head checker) {
		return new TriangularNode(structure, regionX, regionZ, checker);
	}


	public static TriangularNode head(RegionStructure.Config config, int regionX, int regionZ, CoordChecker.Head checker) {
		return new TriangularNode(config, regionX, regionZ, checker);
	}

	public static TriangularNode node(TriangularStructure<?> structure, int regionX, int regionZ, CoordChecker checker) {
		return new TriangularNode(structure, regionX, regionZ, checker);
	}


	public static TriangularNode node(RegionStructure.Config config, int regionX, int regionZ, CoordChecker checker) {
		return new TriangularNode(config, regionX, regionZ, checker);
	}

	public int getPeak() {
		return this.peak;
	}

	@Override
	public Set<Integer> getLiftingPoints() {
		Set<Integer> points = super.getLiftingPoints();
		if(this.analyser.canLift()) points.add(17 + this.analyser.getBits());
		return points;
	}

	@Override
	public boolean test(long structureSeed, int bits, ParentInfo parent) {
		long regionSeed = structureSeed + RegionSeed.A * this.regionX + RegionSeed.B * this.regionZ + this.config.salt;
		int mask = bits == 48 ? (int)Mth.MASK_32 : this.analyser.getMask();

		regionSeed = LCG.JAVA.nextSeed(regionSeed ^ LCG.JAVA.multiplier);
		int x = (int)(regionSeed >>> 17) % this.getPeak() & mask;
		regionSeed = LCG.JAVA.nextSeed(regionSeed);
		x += (int)(regionSeed >>> 17) % this.getPeak() & mask;
		x /= 2;

		regionSeed = LCG.JAVA.nextSeed(regionSeed);
		int z = (int)(regionSeed >>> 17) % this.getPeak() & mask;
		regionSeed = LCG.JAVA.nextSeed(regionSeed);
		z += (int)(regionSeed >>> 17) % this.getPeak() & mask;
		z /= 2;

		if(bits != 48) {
			x &= this.analyser.getMask();
			z &= this.analyser.getMask();
		}

		//if(bits < 48)System.out.println("[" + structureSeed + "] " + x + ", " + z + " " + this.getPeak());
		if(!this.checker.test(x, z, mask, parent)) return false;
		return super.test(structureSeed, bits, new ParentInfo(parent, x, z, true));
	}

	public static class Analyser extends Node.Analyser<TriangularNode> {
		private boolean canLift;

		private int bits;
		private int mask;

		public Analyser(TriangularNode node) {
			super(node);
			if(Mth.isPowerOf2(this.node.getPeak())) return;
			this.bits = Long.numberOfTrailingZeros(this.node.getPeak());
			if(this.bits == 0) return;
			this.mask = (1 << this.bits) - 1;
			this.canLift = true;
		}

		public boolean canLift() {
			return this.canLift;
		}

		public int getBits() {
			return this.bits;
		}

		public int getMask() {
			return this.mask;
		}
	}

}
