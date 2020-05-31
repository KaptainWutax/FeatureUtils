package kaptainwutax.featureutils.structure;

import kaptainwutax.seedutils.mc.ChunkRand;
import kaptainwutax.seedutils.mc.MCVersion;
import kaptainwutax.seedutils.mc.pos.CPos;

public class TriangularStructure extends RegionStructure<RegionStructure.Config, RegionStructure.Data<?>> {

	private final int peak;

	public TriangularStructure(RegionStructure.Config config, MCVersion version) {
		super(config, version);
		this.peak = this.getSpacing() - this.getSeparation();
	}

	public int getPeak() {
		return this.peak;
	}

	@Override
	public boolean test(Data<?> data, long structureSeed, ChunkRand rand) {
		rand.setSeed(data.baseRegionSeed + structureSeed);
		return (rand.nextInt(this.peak) + rand.nextInt(this.peak)) / 2 == data.offsetX
				&& (rand.nextInt(this.peak) + rand.nextInt(this.peak)) / 2 == data.offsetZ;
	}

	@Override
	public CPos getInRegion(long structureSeed, int regionX, int regionZ, ChunkRand rand) {
		rand.setRegionSeed(structureSeed, regionX, regionZ, this.getSalt(), this.getVersion());

		return new CPos(
				regionX * this.getSpacing() + (rand.nextInt(this.peak) + rand.nextInt(this.peak)) / 2,
				regionZ * this.getSpacing() + (rand.nextInt(this.peak) + rand.nextInt(this.peak)) / 2
		);
	}

}
