package com.narmnevis.range.generator;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;

import org.junit.Test;

import com.google.common.collect.Lists;
import com.narmnevis.range.ConcurrentRangeContext;
import com.narmnevis.range.Randomizer;
import com.narmnevis.range.RangeContext;
import com.narmnevis.range.config.RangeConfig;
import com.narmnevis.range.random.ListRandomizer;

public class EnumGeneratorTest {

	private static final int SIZE = 100;

	@Test
	public void generateWithNoRandomizer() throws Exception {
		EnumGenerator g = new EnumGenerator(Lists.newArrayList("a", "b", "c", "d"));
		RangeConfig config = new RangeConfig();
		config.setData(new HashMap<String, String>());
		config.setLimit(SIZE);
		RangeContext context = new ConcurrentRangeContext(config);
		for (int i = 0; i < SIZE; i++) {
			assertNotNull(g.generate(context));
		}
	}

	@Test
	public void generateWithRandomizer() throws Exception {
		Randomizer r = new ListRandomizer(Lists.newArrayList(0.1, 0.3, 0.05, 0.6));
		EnumGenerator g = new EnumGenerator(Lists.newArrayList("a", "b", "c", "d"));
		g.setRandomizer(r);
		RangeConfig config = new RangeConfig();
		config.setData(new HashMap<String, String>());
		config.setLimit(SIZE);
		RangeContext context = new ConcurrentRangeContext(config);
		for (int i = 0; i < SIZE; i++) {
			Object o = g.generate(context);
			assertNotNull(o);
		}
	}

	@Test
	public void generateWithRandomizerWithNegativeProbs() throws Exception {
		Randomizer r = new ListRandomizer(Lists.newArrayList(-0.1, 0.3, -0.05, 0.6));
		EnumGenerator g = new EnumGenerator(Lists.newArrayList("a", "b", "c", "d"));
		g.setRandomizer(r);
		RangeConfig config = new RangeConfig();
		config.setData(new HashMap<String, String>());
		config.setLimit(SIZE);
		RangeContext context = new ConcurrentRangeContext(config);
		for (int i = 0; i < SIZE; i++) {
			Object o = g.generate(context);
			assertNotNull(o);
		}
	}

	@Test
	public void generateWithRandomizerWithZeroProbs() throws Exception {
		Randomizer r = new ListRandomizer(Lists.newArrayList(-0.1, 0.0, -0.05, 0.6));
		EnumGenerator g = new EnumGenerator(Lists.newArrayList("a", "b", "c", "d"));
		g.setRandomizer(r);
		RangeConfig config = new RangeConfig();
		config.setData(new HashMap<String, String>());
		config.setLimit(SIZE);
		RangeContext context = new ConcurrentRangeContext(config);
		for (int i = 0; i < SIZE; i++) {
			Object o = g.generate(context);
			assertNotNull(o);
		}
	}

}
