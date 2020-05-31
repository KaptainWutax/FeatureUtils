package kaptainwutax.featureutils.structure;

import kaptainwutax.seedutils.mc.ChunkRand;
import kaptainwutax.seedutils.mc.MCVersion;

public class UniformStructure extends RegionStructure<RegionStructure.Config, RegionStructure.Data<?>> {

	private final int offset;

	public UniformStructure(RegionStructure.Config config, MCVersion version) {
		super(config, version);
		this.offset = this.getSpacing() - this.getSeparation();
	}

	public int getOffset() {
		return this.offset;
	}

	@Override
	public boolean test(Data<?> data, long structureSeed, ChunkRand rand) {
		rand.setSeed(data.baseRegionSeed + structureSeed);
		return rand.nextInt(this.offset) == data.offsetX && rand.nextInt(this.offset) == data.offsetZ;
	}

}
