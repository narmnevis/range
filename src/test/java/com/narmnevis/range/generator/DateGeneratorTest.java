package com.narmnevis.range.generator;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.narmnevis.range.AbstractTestRangeContext;
import com.narmnevis.range.RangeContext;

public class DateGeneratorTest {

	@Test
	public void generate() throws Exception {
		DateGenerator g = new DateGenerator();
		RangeContext rc = new AbstractTestRangeContext() {
			@Override
			public Integer getSize() {
				return 500;
			}
		};
		for (int i = 1; i <= rc.getSize(); ++i) {
			assertNotNull(g.generate(rc));
		}
	}

}
