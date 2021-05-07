package kaptainwutax.featureutils.structure.generator.structure;

import kaptainwutax.featureutils.structure.generator.Generator;
import kaptainwutax.mcutils.rand.ChunkRand;
import kaptainwutax.mcutils.util.data.Pair;
import kaptainwutax.mcutils.util.pos.BPos;
import kaptainwutax.mcutils.version.MCVersion;
import kaptainwutax.terrainutils.ChunkGenerator;

import java.util.List;

public class VillageGenerator extends Generator {

	public VillageGenerator(MCVersion version) {
		super(version);
	}

	@Override
	public boolean generate(ChunkGenerator generator, int chunkX, int chunkZ, ChunkRand rand) {
		return true;
	}

	@Override
	public List<Pair<ILootType, BPos>> getChestsPos() {
		return null;
	}

	@Override
	public ILootType[] getLootTypes() {
		return new ILootType[0];
	}
}
