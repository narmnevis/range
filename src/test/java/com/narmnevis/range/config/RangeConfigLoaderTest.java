package com.narmnevis.range.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;

import org.junit.Test;

public class RangeConfigLoaderTest {

	@Test
	public void loadRangeConfig() throws Exception {
		URL url = getClass().getResource("/range-config-example.json");
		RangeConfigLoader loader = new RangeConfigLoader(Files.newInputStream(new File(url.toURI()).toPath()));
		RangeConfig config = loader.loadRangeConfig();
		assertNotNull(config);
		Integer limit = 5;
		assertEquals(limit, config.getLimit());
		assertTrue(config.getData().keySet().size() > 0);
		assertTrue(config.getRandomizers().keySet().size() > 0);
	}

}
