package kaptainwutax.featureutils.structure;

import kaptainwutax.seedutils.mc.ChunkRand;
import kaptainwutax.seedutils.mc.MCVersion;
import kaptainwutax.seedutils.mc.VersionMap;
import kaptainwutax.featureutils.Feature;

public class Mineshaft extends Feature<Mineshaft.Config, Feature.Data<?>> {

	public static final VersionMap<Mineshaft.Config> NORMAL_CONFIGS = new VersionMap<Mineshaft.Config>()
			.add(MCVersion.v1_8, new Mineshaft.Config(0.004000000189989805D));

	public static final VersionMap<Mineshaft.Config> MESA_CONFIGS = new VersionMap<Mineshaft.Config>()
			.add(MCVersion.v1_8, new Mineshaft.Config(0.004D));

	public Mineshaft(MCVersion version, Type type) {
		super(type.configs.getAsOf(version), version);
	}

	public Mineshaft(Mineshaft.Config config) {
		super(config, null);
	}

	private double getChance() {
		return this.getConfig().chance;
	}

	@Override
	public boolean test(Data<?> data, long structureSeed, ChunkRand rand) {
		rand.setCarverSeed(structureSeed, data.chunkX, data.chunkZ, this.getVersion());
		return rand.nextDouble() < this.getChance();
	}

	public enum Type {
		NORMAL(Mineshaft.NORMAL_CONFIGS), MESA(Mineshaft.MESA_CONFIGS);

		public final VersionMap<Config> configs;

		Type(VersionMap<Config> configs) {
			this.configs = configs;
		}
	}

	public Feature.Data<Mineshaft> at(int chunkX, int chunkZ) {
		return new Feature.Data<>(this, chunkX, chunkZ);
	}

	public static class Config extends Feature.Config {
		public final double chance;

		public Config(double chance) {
			this.chance = chance;
		}
	}

}
