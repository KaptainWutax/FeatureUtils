package kaptainwutax.featureutils.structure;

import kaptainwutax.seedutils.mc.ChunkRand;
import kaptainwutax.seedutils.mc.MCVersion;
import kaptainwutax.seedutils.mc.pos.CPos;

public abstract class UniformStructure extends RegionStructure<RegionStructure.Config, RegionStructure.Data<?>> {

	private final int offset;

	public UniformStructure(RegionStructure.Config config, MCVersion version) {
		super(config, version);
		this.offset = this.getSpacing() - this.getSeparation();
	}

	public int getOffset() {
		return this.offset;
	}

	@Override
	public boolean canStart(Data<?> data, long structureSeed, ChunkRand rand) {
		rand.setSeed(data.baseRegionSeed + structureSeed);
		return rand.nextInt(this.offset) == data.offsetX && rand.nextInt(this.offset) == data.offsetZ;
	}

	@Override
	public CPos getInRegion(long structureSeed, int regionX, int regionZ, ChunkRand rand) {
		rand.setRegionSeed(structureSeed, regionX, regionZ, this.getSalt(), this.getVersion());

		return new CPos(
				regionX * this.getSpacing() + rand.nextInt(this.getOffset()),
				regionZ * this.getSpacing() + rand.nextInt(this.getOffset())
		);
	}

}
