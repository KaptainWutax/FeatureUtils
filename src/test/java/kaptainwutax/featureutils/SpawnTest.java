package kaptainwutax.featureutils;

import kaptainwutax.biomeutils.source.OverworldBiomeSource;
import kaptainwutax.featureutils.misc.SpawnPoint;
import kaptainwutax.mcutils.util.pos.BPos;
import kaptainwutax.mcutils.version.MCVersion;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpawnTest {
	@Test
	@DisplayName("Test Spawn for 1.12")
	public void testSpawn() {
		OverworldBiomeSource bs = new OverworldBiomeSource(MCVersion.v1_12, 4L);
		// wrong its -710, 64, 569 just to illustrate a point
		assertEquals(SpawnPoint.getSpawnPoint(bs), new BPos(-76, 64, 128));
	}
}
