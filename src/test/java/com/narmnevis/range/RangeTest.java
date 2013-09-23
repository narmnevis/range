package com.narmnevis.range;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.net.URL;

import org.junit.Test;

import com.google.common.collect.Iterators;

public class RangeTest {

	@Test
	public void generate() throws Exception {
		URL url = getClass().getResource("/range-config-example.json");
		Range range = new Range().withConfiguration(new File(url.toURI()).toString());
		Data data = range.generate();
		assertNotNull(data);
		assertEquals(5, Iterators.size(data.iterator()));
	}

}