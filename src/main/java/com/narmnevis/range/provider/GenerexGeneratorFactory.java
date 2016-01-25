package com.narmnevis.range.provider;

import com.narmnevis.range.Generator;
import com.narmnevis.range.generator.GenerexGenerator;

public class GenerexGeneratorFactory extends AbstractGeneratorFactory {

	@Override
	public Generator create(String spec) {
		int openning = spec.indexOf('(');
		int closing = spec.lastIndexOf(')');
		if (openning > 0 && closing > 0) {
			String regexp = spec.substring(openning + 1, closing);
			logger.info("Created an instance of regexp generator with: {}", regexp);
			return new GenerexGenerator(regexp);
		}
		logger.error("Cannot create an enum generator with spec: {}", spec);
		return null;
	}

	@Override
	public boolean supports(String spec) {
		return spec.startsWith("regex(");
	}

}
