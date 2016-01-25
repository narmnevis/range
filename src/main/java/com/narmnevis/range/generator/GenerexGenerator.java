package com.narmnevis.range.generator;

import com.mifmif.common.regex.Generex;
import com.narmnevis.range.Generator;
import com.narmnevis.range.RangeContext;

public class GenerexGenerator implements Generator {

	private Generex generex;
	
	public GenerexGenerator(String regexp) {
		generex = new Generex(regexp);
	}
	
	@Override
	public Object generate(RangeContext context) {
		return generex.random();
	}

}
